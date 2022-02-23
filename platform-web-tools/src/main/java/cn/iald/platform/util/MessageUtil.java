package cn.iald.platform.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 消息工具类
 *
 * @author wangyc
 * @date 2021-06-06 11:12:06
 **/
public class MessageUtil {

    private static MessageSource messageSource;

    /**
     * 初始化
     *
     * @return
     */
    private static MessageSource initMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(3600);
        return messageSource;
    }

    /**
     * 返回提示语
     *
     * @param code
     * @param request
     * @return
     */
    public static String getMessage(String code, HttpServletRequest request) {
        if (messageSource == null) {
            messageSource = initMessageSource();
        }
        if (request != null) {
            return messageSource.getMessage(code, null, RequestContextUtils.getLocale(request));
        }
        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }
}
