package cn.iald.platform.role.entity;

import java.sql.Timestamp;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色-菜单关联表实体类
 *
 * @author wangyc
 * @version 2020年12月14日 09:57:33 初始创建
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRoleMenuEntity implements Serializable {

	/**
	 * 主键
	 */
	private Long roleMenuId;

	/**
	 * 角色id
	 */
	private Long roleId;

	/**
	 * 角色名称
	 */
	private String roleName;

	/**
	 * 菜单id
	 */
	private Long menuId;

	/**
	 * 赋权时间
	 */
	private Timestamp createTime;

}