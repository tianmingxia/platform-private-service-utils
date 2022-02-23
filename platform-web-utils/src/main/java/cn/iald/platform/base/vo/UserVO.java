package cn.iald.platform.base.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * 用户信息VO
 *
 * @author wangyc
 * @date 2021-06-05 14:53:00
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO implements Serializable {
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
