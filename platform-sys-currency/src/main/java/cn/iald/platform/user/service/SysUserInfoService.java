package cn.iald.platform.user.service;

import cn.iald.platform.user.dto.SysUserEditStatusDto;
import cn.iald.platform.user.dto.SysUserInfoEditDto;
import cn.iald.platform.user.dto.SysUserInfoSaveDto;
import cn.iald.platform.user.dto.client.PsUserQueryDto;
import cn.iald.platform.user.dto.client.PsUserRoleQueryDto;
import cn.iald.platform.user.dto.client.PsUserRoleSaveDto;
import cn.iald.platform.user.entity.SysUserInfoEntity;
import cn.iald.platform.user.vo.PsUserVo;
import cn.iald.platform.vo.ResponseVo;

/**
 * 用户信息表Service
 *
 * @author wangyc
 * @version 2020年12月14日 10:16:58 初始创建
 */
public interface SysUserInfoService {

	/**
	 * 新增用户信息表数据
	 *
	 * @param sysUserInfoEntity
	 * @return
	 */
	Long save(SysUserInfoEntity sysUserInfoEntity);

	/**
	 * 新增用户信息
	 *
	 * @param sysUserInfoSaveDto
	 * @return
	 */
	ResponseVo saveUser(SysUserInfoSaveDto sysUserInfoSaveDto);

	/**
	 * 修改用户信息表数据
	 *
	 * @param sysUserInfoEditDto
	 * @return
	 */
	int update(SysUserInfoEditDto sysUserInfoEditDto);

	/**
	 * 修改用户信息表数据
	 *
	 * @param sysUserInfoEditDto
	 * @return
	 */
	ResponseVo updateUser(SysUserInfoEditDto sysUserInfoEditDto);

	/**
	 * 根据id查询用户名称
	 *
	 * @param id
	 * @return
	 */
	String getUserNameById(Object id);

	/**
	 * 根据主键获取用户信息表数据
	 *
	 * @param userId
	 * @return
	 */
	SysUserInfoEntity findByKey(Object userId);

	/**
	 * 配置中心用户信息查询
	 *
	 * @param psUserQueryDto
	 * @return
	 */
	ResponseVo findList(PsUserQueryDto psUserQueryDto);

	/**
	 * 根据主键获取完整的用户信息
	 *
	 * @param userId
	 * @return
	 */
	PsUserVo findByUserId(Long userId);

	/**
	 * 给用户分配指定系统角色
	 *
	 * @param psUserRoleSaveDto
	 * @return
	 */
	ResponseVo saveUserRoleBySys(PsUserRoleSaveDto psUserRoleSaveDto);

	/**
	 * 获取用户的角色信息
	 *
	 * @param psUserRoleQueryDto
	 * @return
	 */
	ResponseVo findUserRole(PsUserRoleQueryDto psUserRoleQueryDto);

	/**
	 * 获取用户与满足要求的所有角色的对应关系
	 *
	 * @param psUserRoleQueryDto
	 * @return
	 */
	ResponseVo findAllRole(PsUserRoleQueryDto psUserRoleQueryDto);

	/**
	 * 启用/禁用
	 *
	 * @param sysUserEditStatusDto
	 * @return
	 */
	int editStatus(SysUserEditStatusDto sysUserEditStatusDto);

	/**
	 * 同步用户到本地
	 *
	 * @param userId
	 */
	void pullUser(Long userId);
}
