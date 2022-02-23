package cn.iald.platform.role.vo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * 角色-菜单关联表Vo类
 *
 * @author wangyc
 * @version 2020年12月14日 09:57:33 初始创建
 */
@Data
public class SysRoleMenuVo implements Serializable {

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

	/**
	 * 是否已赋权
	 */
	private Integer checked;

	/**
	 * 菜单编码，具有唯一性，同一系列权限的菜单使用同一编码，方便权限控制
	 */
	private String menuCode;

	/**
	 * 菜单名称
	 */
	private String menuName;

	/**
	 * 类型（1模块 2菜单 3接口 4按钮）
	 */
	private Integer menuType;

	/**
	 * 上级菜单
	 */
	private Long parentId;

	/**
	 * 菜单图标
	 */
	private String menuIco;

	/**
	 * 状态（1有效 -1禁用）
	 */
	private Integer status;

	/**
	 * 下辖子节点
	 */
	private List<SysRoleMenuVo> children;

}