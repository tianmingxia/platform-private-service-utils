package cn.iald.platform.jwtconfig;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.iald.platform.exception.TokenException;
import cn.iald.platform.vo.JwtUserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @description: token中用户信息工具类
 * @author: wangyc
 * @create: 2020-10-30 16:13
 **/
@Slf4j
public class JwtUserUtils {
	/**
	 * 获取用户id
	 *
	 * @return
	 */
	public static Long getUserId() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal == null) {
			throw new TokenException("无法识别当前用户");
		}
		try {
			JSONObject object = JSONUtil.parseObj(principal);
			return object.getLong("userId");
		} catch (Exception e) {
			log.error("解析token获取当前用户id异常" + e.getMessage());
			throw new TokenException("解析token获取当前用户id异常");
		}
	}

	/**
	 * 获取用户存入信息
	 * @return
	 */
	public static Object getUserInfo() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal == null) {
			throw new TokenException("无法识别当前用户");
		}
		try {
			JSONObject object = JSONUtil.parseObj(principal);
			System.out.println(object.toString());
			return object.getObj("userInfo");
		} catch (Exception e) {
			log.error("解析token获取当前用户信息异常" + e.getMessage());
			throw new TokenException("解析token获取当前用户存入异常");
		}
	}

	/**
	 * 获取token中的用户信息
	 *
	 * @return
	 */
	public static JwtUserVo getJwtUserVo() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal == null) {
			throw new TokenException("无法识别当前用户");
		}
		try {
			return JSONUtil.toBean(principal.toString(), JwtUserVo.class);
		} catch (Exception e) {
			log.error("解析token获取当前用户信息异常" + e.getMessage());
			throw new TokenException("解析token获取当前用户信息异常");
		}
	}
}
