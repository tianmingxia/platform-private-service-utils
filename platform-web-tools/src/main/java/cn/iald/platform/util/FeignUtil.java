package cn.iald.platform.util;

import cn.iald.platform.base.vo.ResponseVO;
import cn.iald.platform.enums.SysCodeEnum;

/**
 * openFeign工具
 *
 * @author wangyc
 * @date 2021/06/24 16:05:14
 **/
public class FeignUtil {

    /**
     * feign调用异常提示
     *
     * @param throwable
     * @return
     */
    public static ResponseVO error(Throwable throwable) {
        return ResponseUtil.error(SysCodeEnum.CLIENT.getCode()
                , MessageUtil.getMessage("web.feign.error", HttpContextUtil.getHttpServletRequest())
                , throwable.getMessage());
    }
}
