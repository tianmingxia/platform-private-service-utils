package cn.iald.platform.common.constants;

/**
 * @description: 权限管理常量类
 * @author: wangyc
 * @create: 2020-10-20 14:10
 **/
public class AuthConstants {

	/**
	 * 登录IPkey
	 */
	public static final String LOGINIP = "LoginIp";

	/**
	 * 菜单类型（1模块 2菜单 3接口 4按钮）
	 */
	public static final Integer MENU_TYPE1 = 1;

	/**
	 * 菜单类型（1模块 2菜单 3接口 4按钮）
	 */
	public static final Integer MENU_TYPE2 = 2;

	/**
	 * 菜单类型（1模块 2菜单 3接口 4按钮）
	 */
	public static final Integer MENU_TYPE3 = 3;

	/**
	 * 菜单类型（1模块 2菜单 3接口 4按钮）
	 */
	public static final Integer MENU_TYPE4 = 4;

	/**
	 * 菜单顶级父节点
	 */
	public static final Long MENU_PARENT_ID = 0L;

	/**
	 * 菜单类型（1模块 2菜单 3接口 4按钮）
	 */
	public static final String MENU_TYPE1_STR = "1";

	/**
	 * 菜单类型（1模块 2菜单 3接口 4按钮）
	 */
	public static final String MENU_TYPE2_STR = "2";

	/**
	 * 菜单类型（1模块 2菜单 3接口 4按钮）
	 */
	public static final String MENU_TYPE3_STR = "3";

	/**
	 * 菜单类型（1模块 2菜单 3接口 4按钮）
	 */
	public static final String MENU_TYPE4_STR = "4";

	/**
	 * 鉴权服务鉴权方式
	 */
	public static final String GRANT_TYPE = "password";

	/**
	 * 菜单新增
	 */
	public static final String PS_AUTH_MENU_SAVE = "hasAuthority('ps_auth_menu:save')";

	/**
	 * 菜单编辑
	 */
	public static final String PS_AUTH_MENU_EDIT = "hasAuthority('ps_auth_menu:edit')";

	/**
	 * 菜单查看
	 */
	public static final String PS_AUTH_MENU_SHOW = "hasAuthority('ps_auth_menu:show')";

	/**
	 * 角色新增
	 */
	public static final String PS_AUTH_ROLE_SAVE = "hasAuthority('ps_auth_role:save')";

	/**
	 * 角色编辑
	 */
	public static final String PS_AUTH_ROLE_EDIT = "hasAuthority('ps_auth_role:edit')";

	/**
	 * 角色查看
	 */
	public static final String PS_AUTH_ROLE_SHOW = "hasAuthority('ps_auth_role:show')";

	/**
	 * 用户新增
	 */
	public static final String PS_AUTH_USER_SAVE = "hasAuthority('ps_auth_user:save')";

	/**
	 * 用户编辑
	 */
	public static final String PS_AUTH_USER_EDIT = "hasAuthority('ps_auth_user:edit')";

	/**
	 * 用户查看
	 */
	public static final String PS_AUTH_USER_SHOW = "hasAuthority('ps_auth_user:show')";
}
