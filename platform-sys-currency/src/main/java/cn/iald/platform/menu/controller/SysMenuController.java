package cn.iald.platform.menu.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.StrUtil;
import cn.iald.platform.common.constants.AuthConstants;
import cn.iald.platform.constants.SysConstants;
import cn.iald.platform.enums.SysErrorEnum;
import cn.iald.platform.jwtconfig.JwtUserUtils;
import cn.iald.platform.menu.dto.SysMenuInfoEditDto;
import cn.iald.platform.menu.dto.SysMenuInfoQueryDto;
import cn.iald.platform.menu.dto.SysMenuInfoSaveDto;
import cn.iald.platform.menu.entity.SysMenuInfoEntity;
import cn.iald.platform.menu.service.SysMenuInfoService;
import cn.iald.platform.menu.vo.SysMenuInfoVo;
import cn.iald.platform.utils.ResponseUtils;
import cn.iald.platform.vo.ResponseVo;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 菜单信息表Controller
 *
 * @author wangyc
 * @version 2020年12月10日 17:53:47 初始创建
 */
@Slf4j
@RestController
@RequestMapping("/sysMenu")
public class SysMenuController {

	/**
	 * 所属系统名称
	 */
	@Value("${systemName:}")
	private String systemName;

	@Autowired
	private SysMenuInfoService sysMenuInfoService;

	/**
	 * 新增菜单
	 *
	 * @param sysMenuInfoSaveDto
	 * @return
	 */
	@PostMapping("/save")
	@PreAuthorize(AuthConstants.PS_AUTH_MENU_SAVE)
	public ResponseVo save(@RequestBody @Valid SysMenuInfoSaveDto sysMenuInfoSaveDto) {
		// 校验是否已具有指定编码
		SysMenuInfoQueryDto sysMenuInfoQueryDto = new SysMenuInfoQueryDto();
		sysMenuInfoQueryDto.setMenuCode(sysMenuInfoSaveDto.getMenuCode());
		sysMenuInfoQueryDto.setSystemId(sysMenuInfoSaveDto.getSystemId());
		Long resCnt = this.sysMenuInfoService.listCnt(sysMenuInfoQueryDto);
		if (resCnt > 0) {
			return ResponseUtils.error(SysErrorEnum.REPEAT.getCode(), "菜单编码已被使用");
		}
		// 校验层级
		this.sysMenuInfoService.checkMenu(sysMenuInfoSaveDto.getMenuType(), sysMenuInfoSaveDto.getParentId());
		sysMenuInfoSaveDto.setCreateBy(JwtUserUtils.getUserId());
		sysMenuInfoSaveDto.setUpdateBy(JwtUserUtils.getUserId());
		return ResponseUtils.success(this.sysMenuInfoService.save(sysMenuInfoSaveDto));
	}

	/**
	 * 修改菜单
	 *
	 * @param sysMenuInfoEditDto
	 * @return
	 */
	@PostMapping("/edit")
	@PreAuthorize(AuthConstants.PS_AUTH_MENU_EDIT)
	public ResponseVo edit(@RequestBody @Valid SysMenuInfoEditDto sysMenuInfoEditDto) {
		// 校验是否已具有指定编码
		SysMenuInfoQueryDto sysMenuInfoQueryDto = new SysMenuInfoQueryDto();
		sysMenuInfoQueryDto.setMenuCode(sysMenuInfoEditDto.getMenuCode());
		sysMenuInfoQueryDto.setMenuId(sysMenuInfoEditDto.getMenuId());
		sysMenuInfoQueryDto.setSystemId(sysMenuInfoEditDto.getSystemId());
		Long resCnt = this.sysMenuInfoService.listCnt(sysMenuInfoQueryDto);
		if (resCnt > 0) {
			return ResponseUtils.error("菜单编码已被使用");
		}
		if (sysMenuInfoEditDto.getMenuId().equals(sysMenuInfoEditDto.getParentId())) {
			return ResponseUtils.error("菜单不能作为自己的父级节点");
		}
		// 校验层级
		this.sysMenuInfoService.checkMenu(sysMenuInfoEditDto.getMenuType(), sysMenuInfoEditDto.getParentId());
		sysMenuInfoEditDto.setUpdateBy(JwtUserUtils.getUserId());
		return ResponseUtils.success(this.sysMenuInfoService.update(sysMenuInfoEditDto));
	}

	/**
	 * 根据菜单主键获取信息
	 *
	 * @param menuId
	 * @return
	 */
	@GetMapping("/findByKey")
	@PreAuthorize(AuthConstants.PS_AUTH_MENU_SHOW)
	public ResponseVo findByKey(Long menuId) {
		if (menuId == null) {
			return ResponseUtils.error("菜单主键不能为空");
		}
		SysMenuInfoEntity sysMenuInfoEntity = this.sysMenuInfoService.findByKey(menuId);
		if (sysMenuInfoEntity == null) {
			return ResponseUtils.error("未查询到对应菜单");
		}
		return ResponseUtils.success(sysMenuInfoEntity);
	}

	/**
	 * 根据查询条件获取菜单列表
	 *
	 * @param sysMenuInfoQueryDto
	 * @return
	 */
	@PostMapping("/findList")
	@PreAuthorize(AuthConstants.PS_AUTH_MENU_SHOW)
	public ResponseVo findList(@RequestBody @Valid SysMenuInfoQueryDto sysMenuInfoQueryDto) {
		// 编码/名称/父节点都为空，默认0
		if (StrUtil.isBlank(sysMenuInfoQueryDto.getMenuCode()) && StrUtil.isBlank(sysMenuInfoQueryDto.getMenuName())
				&& sysMenuInfoQueryDto.getParentId() == null) {
			sysMenuInfoQueryDto.setParentId(AuthConstants.MENU_PARENT_ID);
		}
		return ResponseUtils.success(this.sysMenuInfoService.findList(sysMenuInfoQueryDto));
	}

	/**
	 * 删除菜单
	 *
	 * @param menuId
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/del")
	@PreAuthorize(AuthConstants.PS_AUTH_MENU_EDIT)
	public ResponseVo del(Long menuId) {
		if (menuId == null) {
			return ResponseUtils.error("菜单主键不能为空");
		}
		SysMenuInfoEntity sysMenuInfoEntity = this.sysMenuInfoService.findByKey(menuId);
		if (sysMenuInfoEntity == null) {
			return ResponseUtils.error("不存在对应的菜单信息");
		}
		if (sysMenuInfoEntity.getSubFlag()) {
			return ResponseUtils.error("菜单含有下级菜单，不能删除");
		}
		// 菜单是否在角色中使用
		if (sysMenuInfoService.findRoleMenuCnt(menuId) > 0) {
			// 获取使用该菜单的角色名称
			List<String> roleNames = this.sysMenuInfoService.findRoleNameByMenuId(menuId);
			return ResponseUtils.error("菜单正在角色" + StrUtil.toString(roleNames) + "中使用，不能删除");
		}
		return ResponseUtils.success(this.sysMenuInfoService.del(menuId, JwtUserUtils.getUserId()));
	}

	/**
	 * 获取系统整个菜单树
	 *
	 * @param sysMenuInfoQueryDto
	 * @return
	 */
	@PostMapping("/findTreeAll")
	public ResponseVo findTreeAll(@RequestBody SysMenuInfoQueryDto sysMenuInfoQueryDto) {
		sysMenuInfoQueryDto.setChildFlag(SysConstants.CHILDFLAG1);
		List<SysMenuInfoVo> sysMenuInfoVos = this.sysMenuInfoService.findList(sysMenuInfoQueryDto);
		if (CollUtil.isNotEmpty(sysMenuInfoVos)) {
			SysMenuInfoVo sysMenuInfoVo = new SysMenuInfoVo();
			sysMenuInfoVo.setMenuId(Long.valueOf(0));
			sysMenuInfoVo.setParentId(Long.valueOf(-1));
			sysMenuInfoVo.setMenuName(systemName);
			sysMenuInfoVos.add(sysMenuInfoVo);
		}
		TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
		treeNodeConfig.setIdKey("menuId").setChildrenKey("children").setParentIdKey("parentId").setNameKey("menuName");
		List<Tree<String>> treeList = TreeUtil.build(sysMenuInfoVos, "-1", treeNodeConfig, (treeNode, tree) -> {
			tree.setId(String.valueOf(treeNode.getMenuId()));
			tree.setParentId(String.valueOf(treeNode.getParentId()));
			tree.setName(treeNode.getMenuName());
			// 扩展属性
			tree.putExtra("menuType", treeNode.getMenuType());
			tree.putExtra("menuIco", treeNode.getMenuIco());
			tree.putExtra("status", treeNode.getStatus());
		});
		return ResponseUtils.success(treeList);
	}

	/**
	 * 根据查询条件获取菜单列表(不含下级菜单)
	 *
	 * @param sysMenuInfoQueryDto
	 * @return
	 */
	@PostMapping("/findMenuList")
	@PreAuthorize(AuthConstants.PS_AUTH_MENU_SHOW)
	public ResponseVo findMenuList(@RequestBody SysMenuInfoQueryDto sysMenuInfoQueryDto) {
		sysMenuInfoQueryDto.setChildFlag(SysConstants.CHILDFLAG1);
		if (SysConstants.STATUS_VALID.equals(sysMenuInfoQueryDto.getPageFlag())) {
			PageInfo<SysMenuInfoEntity> pageInfo = this.sysMenuInfoService.findListByPage(sysMenuInfoQueryDto);
			return ResponseUtils.success(pageInfo.getTotal(), pageInfo.getList());
		}
		return ResponseUtils.success(this.sysMenuInfoService.findList(sysMenuInfoQueryDto));
	}
}
