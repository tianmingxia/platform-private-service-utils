package cn.iald.platform.module.role.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.StrUtil;
import cn.iald.platform.common.constant.LoginConstant;
import cn.iald.platform.constant.FlagConstant;
import cn.iald.platform.constant.StatusConstant;
import cn.iald.platform.module.menu.dao.MenuDao;
import cn.iald.platform.module.menu.pojo.entity.MenuDO;
import cn.iald.platform.module.role.dao.RoleMenuDao;
import cn.iald.platform.module.role.pojo.entity.RoleMenuDO;
import cn.iald.platform.module.role.pojo.vo.RoleMenuVO;
import cn.iald.platform.module.role.service.RoleMenuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 角色接口实现类
 *
 * @author wangyc
 * @date 2021/07/12 17:16:55
 **/
@Slf4j
@Service
public class RoleMenuServiceImpl implements RoleMenuService {

    @Resource
    private MenuDao menuDao;

    @Resource
    private RoleMenuDao roleMenuDao;

    @Override
    public RoleMenuVO findRoleMenuVO(List<Long> roleIdList, boolean isTreeBool) {
        if (CollUtil.isEmpty(roleIdList)) {
            return null;
        }
        // 获取指定系统的有效菜单
        QueryWrapper<MenuDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(MenuDO::getStatus, StatusConstant.STATUS_VALID)
                .eq(MenuDO::getDelFlag, FlagConstant.FLAG_NO)
                .orderByAsc(MenuDO::getSortNo);
        List<MenuDO> menuDOList = this.menuDao.selectList(queryWrapper);
        if (CollUtil.isEmpty(menuDOList)) {
            return null;
        }
        // 获取角色对应的菜单信息
        List<RoleMenuDO> roleMenuList = new ArrayList<>();
        List<RoleMenuDO> tempRoleMenuList;
        QueryWrapper<RoleMenuDO> queryRoleMenuWrapper;
        for (Long roleId : roleIdList) {
            queryRoleMenuWrapper = new QueryWrapper<>();
            queryRoleMenuWrapper.lambda().eq(RoleMenuDO::getRoleId, roleId);
            tempRoleMenuList = this.roleMenuDao.selectList(queryRoleMenuWrapper);
            if (CollUtil.isNotEmpty(tempRoleMenuList)) {
                CollUtil.addAllIfNotContains(roleMenuList, tempRoleMenuList);
            }
        }
        if (CollUtil.isEmpty(roleMenuList)) {
            return null;
        }
        List<Long> menuIds = roleMenuList.stream().map(RoleMenuDO::getMenuId).filter(Objects::nonNull)
                .collect(Collectors.toList());
        RoleMenuVO roleMenuVO = new RoleMenuVO();
        // 组装菜单树
        if (isTreeBool) {
            List<CharSequence> menuIdList = new ArrayList<>();
            List<Tree<String>> treeList = TreeUtil.build(menuDOList, "0", (treeNode, tree) -> {
                tree.setId(String.valueOf(treeNode.getMenuId()));
                tree.setParentId(String.valueOf(treeNode.getParentId()));
                tree.setName(String.valueOf(treeNode.getMenuId()));
            });
            for (Tree<String> strTree : treeList) {
                for (Long menuId : menuIds) {
                    List<CharSequence> pIdList = strTree.getParentsName(String.valueOf(menuId), true);
                    CollUtil.addAllIfNotContains(menuIdList, pIdList);
                }
            }
            final List<CharSequence> menuIdListTemp = menuIdList.stream().distinct().collect(Collectors.toList());
            List<MenuDO> menuList = menuDOList.stream()
                    .filter(menuDO -> (menuIdListTemp.contains(String.valueOf(menuDO.getMenuId())))
                            && !(LoginConstant.MENU_TYPE_BTN.equals(menuDO.getMenuType())))
                    .collect(Collectors.toList());
            TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
            treeNodeConfig.setIdKey("menuId").setChildrenKey("children").setParentIdKey("parentId").setNameKey("menuName");
            treeList = TreeUtil.build(menuList, "0", treeNodeConfig, (treeNode, tree) -> {
                tree.setId(String.valueOf(treeNode.getMenuId()));
                tree.setParentId(String.valueOf(treeNode.getParentId()));
                tree.setName(treeNode.getMenuName());
                tree.putExtra("menuType", treeNode.getMenuType());
                tree.putExtra("menuUrl", treeNode.getMenuUrl());
                tree.putExtra("menuUrlEx", treeNode.getMenuUrlEx());
                tree.putExtra("menuIco", treeNode.getMenuIco());
                tree.putExtra("remark", treeNode.getRemark());
            });
            roleMenuVO.setMenuTreeList(treeList);
        }
        // 符合要求的菜单code
        List<String> menuCodeList = menuDOList.stream()
                .filter(menuDO -> CollUtil.contains(menuIds, menuDO.getMenuId()))
                .map(MenuDO::getMenuCode)
                .filter(menuCode -> StrUtil.isNotBlank(menuCode) && menuCode.contains(LoginConstant.AUTH_FLAG))
                .collect(Collectors.toList());
        roleMenuVO.setMenuCodeList(menuCodeList);
        return roleMenuVO;
    }
}
