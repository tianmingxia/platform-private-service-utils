package cn.iald.platform.login.controller;

import cn.iald.platform.common.constants.AuthConstants;
import cn.iald.platform.login.dto.LoginNoTokenDto;
import cn.iald.platform.login.service.SysLoginService;
import cn.iald.platform.utils.ResponseUtils;
import cn.iald.platform.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @description: 系统登录Controller
 * @author: wangyc
 * @create: 2020-12-12 17:49
 **/
@Slf4j
@RestController
public class SysLoginController {

	/**
	 * 所属系统id
	 */
	@Value("${systemId:}")
	private Integer systemId;

	@Autowired
	private SysLoginService sysLoginService;

	/**
	 * 登录接口
	 *
	 * @param loginNoTokenDto
	 * @param request
	 * @return
	 */
	@PostMapping("/login")
	public ResponseVo login(@RequestBody @Valid LoginNoTokenDto loginNoTokenDto, HttpServletRequest request) {
		loginNoTokenDto.setSystemId(systemId);
		loginNoTokenDto.setLoginIp(request.getHeader(AuthConstants.LOGINIP));
		return ResponseUtils.success(this.sysLoginService.login(loginNoTokenDto));
	}
}
