package cn.iald.platform.login.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @description: 其他系统登录接口
 * @author: wangyc
 * @create: 2020-10-22 12:54
 **/
@Data
public class LoginNoTokenDto {

	/**
	 * 登录名
	 */
	@NotEmpty(message = "登录名不能为空")
	private String username;

	/**
	 * 密码
	 */
	@NotEmpty(message = "密码不能为空")
	private String password;

	/**
	 * 要登录系统id
	 */
	private Integer systemId;

	/**
	 * 登录Ip
	 */
	private String loginIp;
}
