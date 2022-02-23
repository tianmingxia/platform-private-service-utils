package cn.iald.platform.login.service;

import cn.iald.platform.login.dto.LoginNoTokenDto;
import cn.iald.platform.login.vo.LoginUserNoTokenVo;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 系统角色接口
 * @author: wangyc
 * @create: 2020-12-12 19:26
 **/
public interface SysLoginService {

	/**
	 * 登录接口
	 *
	 * @param loginNoTokenDto
	 * @return
	 */
	LoginUserNoTokenVo login(LoginNoTokenDto loginNoTokenDto);
}
