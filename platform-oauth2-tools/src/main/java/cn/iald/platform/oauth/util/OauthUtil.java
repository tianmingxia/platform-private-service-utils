package cn.iald.platform.oauth.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @description: Oauth工具类
 * @author: wangyc
 * @create: 2021-04-27 13:35
 **/
public class OauthUtil {

	/**
	 * 密码加密
	 *
	 * @param pwd 待加密数据
	 * @return
	 */
	public static String encodePwd(String pwd) {
		return new BCryptPasswordEncoder().encode(pwd);
	}

	/**
	 * 比对密码是否正确
	 *
	 * @param pwd       待加密数据
	 * @param encodePwd 已加密数据
	 * @return
	 */
	public static boolean matchesPwd(String pwd, String encodePwd) {
		return new BCryptPasswordEncoder().matches(pwd, encodePwd);
	}
}
