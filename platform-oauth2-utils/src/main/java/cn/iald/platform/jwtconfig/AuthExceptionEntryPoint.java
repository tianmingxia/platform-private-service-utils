package cn.iald.platform.jwtconfig;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import cn.iald.platform.enums.SysErrorEnum;
import cn.iald.platform.utils.ResponseUtils;
import cn.iald.platform.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 无效 token 异常类重写
 * @author: wangyc
 * @create: 2020-10-30 14:33
 **/
@Slf4j
@Component
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {

	/**
	 * token过期正常使用接口
	 */
	@Value("${oauth.expired-list-url:}")
	private String expiredListUrl;

	/**
	 * token过期标记
	 */
	public static final String ACCESS_TOKEN_EXPIRED = "Access token expired";

	@Override
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
	                     AuthenticationException e) throws IOException {
		log.error("token异常" + e.getMessage(), e);
		httpServletResponse.setStatus(HttpStatus.OK.value());
		httpServletResponse.setHeader("Content-Type", "application/json;charset=UTF-8");
		ResponseVo responseVo = ResponseUtils.error(SysErrorEnum.TOKEN_ACCESS_DENIED.getCode(), "权限不足，" + e.getMessage());
		if (e.getMessage().indexOf(ACCESS_TOKEN_EXPIRED) > -1) {
			responseVo = ResponseUtils.error(SysErrorEnum.TOKEN_EXPIRED.getCode(), "访问令牌已过期");
			AntPathMatcher antPathMatcher = new AntPathMatcher();
			String url = httpServletRequest.getRequestURI();
			String[] expiredListUrlArr;
			if (StrUtil.isNotBlank(expiredListUrl)) {
				expiredListUrlArr = expiredListUrl.split(",");
			} else {
				expiredListUrlArr = new String[]{"/internal/**"};
			}
			for (String regex : expiredListUrlArr) {
				if (antPathMatcher.match(regex, url)) {
					responseVo = ResponseUtils.error(SysErrorEnum.TOKEN_EXPIRED.getCode(), "当前接口不需访问令牌，但传入一个已过期的访问令牌");
				}
			}
		}
		httpServletResponse.getWriter().println(JSONUtil.toJsonStr(responseVo));
	}
}
