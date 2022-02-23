package cn.iald.platform.role.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 角色-菜单表SaveDto类
 *
 * @author wangyc
 * @version 2020年10月18日 17:01:23 初始创建
 */
@Data
public class SysRoleMenuSaveDto {

	/**
	 * 角色主键
	 */
	@NotNull(message = "角色主键不能为空")
	private Long roleId;

	/**
	 * 角色主键
	 */
	@NotEmpty(message = "角色名称不能为空")
	private String roleName;

	/**
	 * 菜单主键列表
	 */
	private List<Long> menuIdList;
}