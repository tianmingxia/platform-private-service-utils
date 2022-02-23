package cn.iald.platform.module.role.service;

import cn.iald.platform.module.role.pojo.vo.RoleMenuVO;

import java.util.List;

/**
 * 角色表Service
 *
 * @author wangyc
 * @date 2021/06/15 09:54:22
 */
public interface RoleMenuService {

    /**
     * 获取角分配的菜单信息
     *
     * @param roleIdList
     * @param isTreeBool
     * @return
     */
    RoleMenuVO findRoleMenuVO(List<Long> roleIdList, boolean isTreeBool);

}
