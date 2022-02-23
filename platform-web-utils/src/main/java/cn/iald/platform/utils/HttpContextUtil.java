package cn.iald.platform.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * request上下文工具
 *
 * @author wangyc
 * @date 2021/06/28 16:46:01
 **/
public class HttpContextUtil {

    /**
     * 获取 request
     *
     * @return
     */
    public static HttpServletRequest getHttpServletRequest() {
        HttpServletRequest request = null;
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            request = ((ServletRequestAttributes) requestAttributes).getRequest();
        }
        return request;
    }
}
