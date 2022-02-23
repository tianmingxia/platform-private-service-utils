package cn.iald.platform.vo;

import lombok.Data;

import java.util.List;

/**
 * @description: Jwt用户Vo
 * @author: wangyc
 * @create: 2020-10-30 13:36
 **/
@Data
public class JwtUserVo {
	/**
	 * 第三方传值
	 */
	private Object userInfo;
	/**
	 * 用户id
	 */
	private Long userId;

	/**
	 * 真实姓名
	 */
	private String realname;

	/**
	 * 登录名
	 */
	private String username;

	/**
	 * 昵称
	 */
	private String nickname;

	/**
	 * 登录Ip
	 */
	private String loginIp;

	/**
	 * 当前用户指定系统角色id
	 */
	private List<Long> roleIds;
}
