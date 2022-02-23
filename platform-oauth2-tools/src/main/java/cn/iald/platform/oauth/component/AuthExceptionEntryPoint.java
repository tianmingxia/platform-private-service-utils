package cn.iald.platform.oauth.component;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import cn.iald.platform.base.vo.ResponseVO;
import cn.iald.platform.enums.SysCodeEnum;
import cn.iald.platform.util.MessageUtil;
import cn.iald.platform.util.ResponseUtil;
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
    public void commence(HttpServletRequest request, HttpServletResponse httpServletResponse, AuthenticationException e)
            throws IOException {
        log.error("token异常" + e.getMessage(), e);
        httpServletResponse.setStatus(HttpStatus.OK.value());
        httpServletResponse.setHeader("Content-Type", "application/json;charset=UTF-8");
        ResponseVO responseVO = ResponseUtil.error(SysCodeEnum.TOKEN_ACCESS_DENIED.getCode()
                , MessageUtil.getMessage("token.auth.permissions", request) + "," + e.getMessage()
                , e.getMessage());
        if (e.getMessage().indexOf(ACCESS_TOKEN_EXPIRED) > -1) {
            responseVO = ResponseUtil.error(SysCodeEnum.TOKEN_EXPIRED.getCode()
                    , MessageUtil.getMessage("token.expired", request), e.getMessage());
            AntPathMatcher antPathMatcher = new AntPathMatcher();
            String url = request.getRequestURI();
            String[] expiredListUrlArr;
            if (StrUtil.isNotBlank(expiredListUrl)) {
                expiredListUrl = expiredListUrl.replace(" ", "");
                expiredListUrlArr = expiredListUrl.split(",");
            } else {
                expiredListUrlArr = new String[]{"/internal/**"};
            }
            for (String regex : expiredListUrlArr) {
                if (antPathMatcher.match(regex, url)) {
                    responseVO = ResponseUtil.error(SysCodeEnum.TOKEN_EXPIRED.getCode()
                            , MessageUtil.getMessage("token.notNeeded.expired", request), e.getMessage());
                }
            }
        }
        httpServletResponse.getWriter().println(JSONUtil.toJsonStr(responseVO));
    }
}
