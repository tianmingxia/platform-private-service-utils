package cn.iald.platform.user.controller;

import cn.hutool.core.util.StrUtil;
import cn.iald.platform.common.constants.AuthConstants;
import cn.iald.platform.jwtconfig.JwtUserUtils;
import cn.iald.platform.user.dto.SysUserEditStatusDto;
import cn.iald.platform.user.dto.SysUserInfoEditDto;
import cn.iald.platform.user.dto.SysUserInfoSaveDto;
import cn.iald.platform.user.dto.client.PsUserQueryDto;
import cn.iald.platform.user.dto.client.PsUserRoleQueryDto;
import cn.iald.platform.user.dto.client.PsUserRoleSaveDto;
import cn.iald.platform.user.service.SysUserInfoService;
import cn.iald.platform.utils.ResponseUtils;
import cn.iald.platform.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @description: 系统用户Controller
 * @author: wangyc
 * @create: 2020-12-12 17:49
 **/
@Slf4j
@RestController
@RequestMapping("/sysUser")
public class SysUserController {
	/**
	 * 所属系统id
	 */
	@Value("${systemId:}")
	private Integer systemId;

	@Autowired
	private SysUserInfoService sysUserInfoService;

	/**
	 * 添加用户
	 *
	 * @param sysUserInfoSaveDto
	 * @return
	 */
	@PostMapping("/save")
	@PreAuthorize(AuthConstants.PS_AUTH_USER_SAVE)
	public ResponseVo save(@RequestBody @Valid SysUserInfoSaveDto sysUserInfoSaveDto) {
		sysUserInfoSaveDto.setSystemId(systemId);
		sysUserInfoSaveDto.setCreateBy(JwtUserUtils.getUserId());
		sysUserInfoSaveDto.setUpdateBy(JwtUserUtils.getUserId());
		return this.sysUserInfoService.saveUser(sysUserInfoSaveDto);
	}

	/**
	 * 编辑用户
	 *
	 * @param sysUserInfoEditDto
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/edit")
	@PreAuthorize(AuthConstants.PS_AUTH_USER_EDIT)
	public ResponseVo edit(@RequestBody @Valid SysUserInfoEditDto sysUserInfoEditDto) {
		sysUserInfoEditDto.setUpdateBy(JwtUserUtils.getUserId());
		return this.sysUserInfoService.updateUser(sysUserInfoEditDto);
	}

	/**
	 * 删除用户,调整为禁用
	 *
	 * @param sysUserEditStatusDto
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/editStatus")
	@PreAuthorize(AuthConstants.PS_AUTH_USER_EDIT)
	public ResponseVo editStatus(@RequestBody @Valid SysUserEditStatusDto sysUserEditStatusDto) {
		sysUserEditStatusDto.setUpdateBy(JwtUserUtils.getUserId());
		return ResponseUtils.success(this.sysUserInfoService.editStatus(sysUserEditStatusDto));
	}

	/**
	 * 根据查询条件分页查询用户列表
	 *
	 * @param psUserQueryDto
	 * @return
	 */
	@PostMapping("/findList")
	@PreAuthorize(AuthConstants.PS_AUTH_USER_SHOW)
	public ResponseVo findList(@RequestBody @Valid PsUserQueryDto psUserQueryDto) {
		if (psUserQueryDto.getRoleId() != null || StrUtil.isNotBlank(psUserQueryDto.getRoleName())) {
			psUserQueryDto.setSystemId(systemId);
		}
		return this.sysUserInfoService.findList(psUserQueryDto);
	}

	/**
	 * 根据主键获取完整的用户信息
	 *
	 * @param userId
	 * @return
	 */
	@GetMapping("/findByKey")
	@PreAuthorize(AuthConstants.PS_AUTH_USER_SHOW)
	public ResponseVo findByKey(Long userId) {
		if (userId == null) {
			return ResponseUtils.error("用户主键不能为空");
		}
		return ResponseUtils.success(this.sysUserInfoService.findByUserId(userId));
	}

	/**
	 * 给用户分配指定系统角色
	 *
	 * @param psUserRoleSaveDto
	 * @return
	 */
	@PostMapping("/saveUserRoleBySys")
	@PreAuthorize(AuthConstants.PS_AUTH_USER_EDIT)
	public ResponseVo saveUserRoleBySys(@RequestBody @Valid PsUserRoleSaveDto psUserRoleSaveDto) {
		psUserRoleSaveDto.setSystemId(systemId);
		return this.sysUserInfoService.saveUserRoleBySys(psUserRoleSaveDto);
	}

	/**
	 * 获取用户的角色信息
	 *
	 * @param psUserRoleQueryDto
	 * @return
	 */
	@PostMapping("/findUserRole")
	@PreAuthorize(AuthConstants.PS_AUTH_USER_SHOW)
	public ResponseVo findUserRole(@RequestBody @Valid PsUserRoleQueryDto psUserRoleQueryDto) {
		psUserRoleQueryDto.setSystemId(systemId);
		return this.sysUserInfoService.findUserRole(psUserRoleQueryDto);
	}

	/**
	 * 获取用户与所有角色的对应关系
	 *
	 * @param psUserRoleQueryDto
	 * @return
	 */
	@PostMapping("/findAllRole")
	@PreAuthorize(AuthConstants.PS_AUTH_USER_SHOW)
	public ResponseVo findAllRole(@RequestBody @Valid PsUserRoleQueryDto psUserRoleQueryDto) {
		psUserRoleQueryDto.setSystemId(systemId);
		return this.sysUserInfoService.findAllRole(psUserRoleQueryDto);
	}
}
