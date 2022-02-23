package cn.iald.platform.role.service;

import cn.iald.platform.role.dto.SysRoleMenuSaveDto;
import cn.iald.platform.role.dto.client.*;
import cn.iald.platform.role.entity.SysRoleMenuEntity;
import cn.iald.platform.role.vo.SysRoleMenuVo;
import cn.iald.platform.vo.ResponseVo;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @description: 系统角色接口
 * @author: wangyc
 * @create: 2020-12-12 19:26
 **/
public interface SysRoleService {

	/**
	 * 新增角色
	 *
	 * @param psRoleSaveDto
	 * @return
	 */
	ResponseVo save(PsRoleSaveNoTokenDto psRoleSaveDto);

	/**
	 * 修改角色
	 *
	 * @param psRoleEditDto
	 * @return
	 */
	ResponseVo edit(PsRoleEditNoTokenDto psRoleEditDto);

	/**
	 * 删除角色
	 *
	 * @param psRoleDelDto
	 * @return
	 */
	ResponseVo del(PsRoleDelNoTokenDto psRoleDelDto);

	/**
	 * 根据角色主键获取信息
	 *
	 * @param roleId
	 * @return
	 */
	ResponseVo findRoleByKey(Long roleId);

	/**
	 * 根据查询条件查询角色列表
	 *
	 * @param psRoleQueryDto
	 * @return
	 */
	ResponseVo findRoles(PsRoleQueryDto psRoleQueryDto);

	/**
	 * 角色分配菜单
	 *
	 * @param sysRoleMenuSaveDto
	 * @return
	 */
	int saveRoleMenu(SysRoleMenuSaveDto sysRoleMenuSaveDto);

	/**
	 * 根据角色id查询分配的菜单信息
	 *
	 * @param roleId
	 * @return
	 */
	List<SysRoleMenuEntity> findMenuByRoleId(Long roleId);

	/**
	 * 根据角色id查询分配的菜单树信息信息
	 *
	 * @param roleId
	 * @return
	 */
	List<SysRoleMenuVo> findMenuTreeByRoleId(Long roleId);
}
