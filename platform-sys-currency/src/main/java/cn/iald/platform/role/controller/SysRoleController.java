package cn.iald.platform.role.controller;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.iald.platform.common.constants.AuthConstants;
import cn.iald.platform.jwtconfig.JwtUserUtils;
import cn.iald.platform.role.dto.SysRoleMenuSaveDto;
import cn.iald.platform.role.dto.client.*;
import cn.iald.platform.role.service.SysRoleService;
import cn.iald.platform.role.vo.SysRoleMenuVo;
import cn.iald.platform.utils.ResponseUtils;
import cn.iald.platform.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 系统角色Controller
 * @author: wangyc
 * @create: 2020-12-12 17:48
 **/
@Slf4j
@RestController
@RequestMapping("/sysRole")
public class SysRoleController {

	/**
	 * 所属平台id
	 */
	@Value("${platformId:}")
	private Integer platformId;

	/**
	 * 所属系统id
	 */
	@Value("${systemId:}")
	private Integer systemId;

	@Autowired
	private SysRoleService sysRoleService;

	/**
	 * 新增角色
	 *
	 * @param psRoleSaveDto
	 * @return
	 */
	@PostMapping("/save")
	@PreAuthorize(AuthConstants.PS_AUTH_ROLE_SAVE)
	public ResponseVo save(@RequestBody @Valid PsRoleSaveNoTokenDto psRoleSaveDto) {
		psRoleSaveDto.setPlatformId(platformId);
		psRoleSaveDto.setSystemId(systemId);
		psRoleSaveDto.setCreateBy(JwtUserUtils.getUserId());
		psRoleSaveDto.setUpdateBy(JwtUserUtils.getUserId());
		return this.sysRoleService.save(psRoleSaveDto);
	}

	/**
	 * 修改角色
	 *
	 * @param psRoleEditDto
	 * @return
	 */
	@PostMapping("/edit")
	@PreAuthorize(AuthConstants.PS_AUTH_ROLE_EDIT)
	public ResponseVo edit(@RequestBody @Valid PsRoleEditNoTokenDto psRoleEditDto) {
		psRoleEditDto.setPlatformId(platformId);
		psRoleEditDto.setSystemId(systemId);
		psRoleEditDto.setUpdateBy(JwtUserUtils.getUserId());
		return this.sysRoleService.edit(psRoleEditDto);
	}

	/**
	 * 删除角色
	 *
	 * @param roleId
	 * @return
	 */
	@GetMapping("/del")
	@PreAuthorize(AuthConstants.PS_AUTH_ROLE_EDIT)
	public ResponseVo del(Long roleId) {
		PsRoleDelNoTokenDto psRoleDelDto = new PsRoleDelNoTokenDto();
		psRoleDelDto.setRoleId(roleId);
		psRoleDelDto.setSystemId(systemId);
		psRoleDelDto.setUpdateBy(JwtUserUtils.getUserId());
		return this.sysRoleService.del(psRoleDelDto);
	}

	/**
	 * 根据角色主键获取信息
	 *
	 * @param roleId
	 * @return
	 */
	@GetMapping("/findByKey")
	@PreAuthorize(AuthConstants.PS_AUTH_ROLE_SHOW)
	public ResponseVo findByKey(Long roleId) {
		return this.sysRoleService.findRoleByKey(roleId);
	}

	/**
	 * 根据查询条件查询角色列表
	 *
	 * @param psRoleQueryDto
	 * @return
	 */
	@PostMapping("/findList")
	@PreAuthorize(AuthConstants.PS_AUTH_ROLE_SHOW)
	public ResponseVo findList(@RequestBody @Valid PsRoleQueryDto psRoleQueryDto) {
		psRoleQueryDto.setSystemId(systemId);
		return this.sysRoleService.findRoles(psRoleQueryDto);
	}

	/**
	 * 角色分配菜单
	 *
	 * @param sysRoleMenuSaveDto
	 * @return
	 */
	@PostMapping("/saveRoleMenu")
	@PreAuthorize(AuthConstants.PS_AUTH_ROLE_EDIT)
	public ResponseVo saveRoleMenu(@RequestBody @Valid SysRoleMenuSaveDto sysRoleMenuSaveDto) {
		return ResponseUtils.success(this.sysRoleService.saveRoleMenu(sysRoleMenuSaveDto));
	}

	/**
	 * 根据角色id查询分配的菜单信息
	 *
	 * @param roleId
	 * @return
	 */
	@GetMapping("/findMenuByRoleId")
	@PreAuthorize(AuthConstants.PS_AUTH_ROLE_SHOW)
	public ResponseVo findMenuByRoleId(Long roleId) {
		if (roleId == null) {
			return ResponseUtils.error("角色主键不能为空");
		}
		return ResponseUtils.success(this.sysRoleService.findMenuByRoleId(roleId));
	}

	/**
	 * 根据角色id查询分配的菜单树信息
	 *
	 * @param roleId
	 * @return
	 */
	@GetMapping("/findMenuTreeByRoleId")
	@PreAuthorize(AuthConstants.PS_AUTH_ROLE_SHOW)
	public ResponseVo findMenuTreeByRoleId(Long roleId) {
		if (roleId == null) {
			return ResponseUtils.error("角色主键不能为空");
		}
		List<SysRoleMenuVo> sysRoleMenuVos = this.sysRoleService.findMenuTreeByRoleId(roleId);
		TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
		treeNodeConfig.setIdKey("menuId").setChildrenKey("children").setParentIdKey("parentId").setNameKey("menuName");
		List<Tree<String>> treeList = TreeUtil.build(sysRoleMenuVos, "0", treeNodeConfig, (treeNode, tree) -> {
			tree.setId(String.valueOf(treeNode.getMenuId()));
			tree.setParentId(String.valueOf(treeNode.getParentId()));
			tree.setName(treeNode.getMenuName());
			// 扩展属性 ...
			tree.putExtra("roleId", treeNode.getRoleId());
			tree.putExtra("checked", treeNode.getChecked());
			tree.putExtra("createTime", treeNode.getCreateTime());
			tree.putExtra("menuType", treeNode.getMenuType());
			tree.putExtra("menuIco", treeNode.getMenuIco());
			tree.putExtra("status", treeNode.getStatus());
		});
		return ResponseUtils.success(treeList);
	}
}
