package cn.iald.platform.vo;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @description: 用户信息VO
 * @author: wangyc
 * @create: 2020-10-26 10:14
 **/
@Data
public class UserVo {
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
	 * 密码
	 */
	private String password;

	/**
	 * 昵称
	 */
	private String nickname;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 最后一次登录时间
	 */
	private Timestamp lastTime;

	/**
	 * 状态（1有效 -1禁止登录 0删除 ）
	 */
	private Integer status;

	/**
	 * 当前用户菜单权限
	 */
	private List<String> menuCodes;

	/**
	 * 当前用户指定系统角色id
	 */
	private List<Long> roleIds;

	/**
	 * 登录Ip
	 */
	private String loginIp;

	/**
	 * id
	 */
	private String clientId;

	/**
	 * 对称秘钥
	 */
	private String signingKey;
}
