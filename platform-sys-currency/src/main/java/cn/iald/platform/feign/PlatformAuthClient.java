package cn.iald.platform.feign;

import cn.iald.platform.login.dto.LoginNoTokenDto;
import cn.iald.platform.role.dto.client.PsRoleDelNoTokenDto;
import cn.iald.platform.role.dto.client.PsRoleEditNoTokenDto;
import cn.iald.platform.role.dto.client.PsRoleQueryDto;
import cn.iald.platform.role.dto.client.PsRoleSaveNoTokenDto;
import cn.iald.platform.user.dto.client.*;
import cn.iald.platform.vo.ResponseVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description: 平台权限管理客户端
 * @author: wangyc
 * @create: 2020-12-12 19:16
 **/
@FeignClient(name = "pca")
public interface PlatformAuthClient {

	/**
	 * 新增角色
	 *
	 * @param psRoleSaveDto
	 * @return
	 */
	@RequestMapping(value = "/noToken/internal/saveRole", method = RequestMethod.POST)
	ResponseVo saveRole(@RequestBody PsRoleSaveNoTokenDto psRoleSaveDto);

	/**
	 * 修改角色
	 *
	 * @param psRoleEditDto
	 * @return
	 */
	@RequestMapping(value = "/noToken/internal/editRole", method = RequestMethod.POST)
	ResponseVo editRole(@RequestBody PsRoleEditNoTokenDto psRoleEditDto);

	/**
	 * 删除角色
	 *
	 * @param psRoleDelDto
	 * @return
	 */
	@RequestMapping(value = "/noToken/internal/delRole", method = RequestMethod.POST)
	ResponseVo delRole(@RequestBody PsRoleDelNoTokenDto psRoleDelDto);

	/**
	 * 根据角色主键获取信息
	 *
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = "/internal/findRoleByKey", method = RequestMethod.GET)
	ResponseVo findRoleByKey(@RequestParam Long roleId);

	/**
	 * 根据查询条件查询角色列表
	 *
	 * @param psRoleQueryDto
	 * @return
	 */
	@RequestMapping(value = "/internal/findRoles", method = RequestMethod.POST)
	ResponseVo findRoles(@RequestBody PsRoleQueryDto psRoleQueryDto);

	/**
	 * 添加用户
	 *
	 * @param psUserSaveDto
	 * @return
	 */
	@RequestMapping(value = "/noToken/internal/saveUser", method = RequestMethod.POST)
	ResponseVo saveUser(@RequestBody PsUserSaveNoTokenDto psUserSaveDto);

	/**
	 * 编辑用户
	 *
	 * @param psUserEditDto
	 * @return
	 */
	@RequestMapping(value = "/noToken/internal/editUser", method = RequestMethod.POST)
	ResponseVo editUser(@RequestBody PsUserEditNoTokenDto psUserEditDto);

	/**
	 * 查询用户信息列表
	 *
	 * @param psUserQueryDto
	 * @return
	 */
	@RequestMapping(value = "/noToken/internal/findUsers", method = RequestMethod.POST)
	ResponseVo findUsers(@RequestBody PsUserQueryDto psUserQueryDto);

	/**
	 * 根据主键获取完整的用户信息
	 *
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/noToken/internal/findUserByKey", method = RequestMethod.GET)
	ResponseVo findUserByKey(@RequestParam Long userId);

	/**
	 * 给用户分配指定系统角色
	 *
	 * @param psUserRoleSaveDto
	 * @return
	 */
	@RequestMapping(value = "/internal/saveUserRoleBySys", method = RequestMethod.POST)
	ResponseVo saveUserRoleBySys(@RequestBody PsUserRoleSaveDto psUserRoleSaveDto);

	/**
	 * 获取用户的角色信息
	 *
	 * @param psUserRoleQueryDto
	 * @return
	 */
	@RequestMapping(value = "/internal/findUserRole", method = RequestMethod.POST)
	ResponseVo findUserRole(@RequestBody PsUserRoleQueryDto psUserRoleQueryDto);

	/**
	 * 获取用户与满足要求的所有角色的对应关系
	 *
	 * @param psUserRoleQueryDto
	 * @return
	 */
	@RequestMapping(value = "/noToken/internal/findAllRole", method = RequestMethod.POST)
	ResponseVo findAllRole(@RequestBody PsUserRoleQueryDto psUserRoleQueryDto);

	/**
	 * 用户登录
	 *
	 * @param loginNoTokenDto
	 * @return
	 */
	@RequestMapping(value = "/noToken/internal/login", method = RequestMethod.POST)
	ResponseVo login(@RequestBody LoginNoTokenDto loginNoTokenDto);
}
