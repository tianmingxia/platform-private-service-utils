package cn.iald.platform.login.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 角色对应菜单Vo
 * @author: wangyc
 * @create: 2020-10-23 20:28
 **/
@Data
public class LoginRoleMenuVo implements Serializable {
	/**
	 * 主键
	 */
	private Long menuId;

	/**
	 * 菜单编码
	 */
	private String menuCode;

	/**
	 * 类型（1模块 2菜单 3接口 4按钮）
	 */
	private Integer menuType;

	/**
	 * 上级菜单
	 */
	private Long parentId;
}
