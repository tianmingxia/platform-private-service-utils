package cn.iald.platform.exception;

import cn.iald.platform.enums.SysCodeEnum;

/**
 * 业务异常
 *
 * @author wangyc
 * @date 2020-10-23 17:51:00
 **/
public class BusinessException extends RuntimeException {

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误提示语
     */
    private String errorMsg;

    public BusinessException(String message) {
        super(message);
        this.code = SysCodeEnum.ERROR.getCode();
        this.errorMsg = message;
    }

    /**
     * 构建异常返回值
     *
     * @param code     错误码
     * @param errorMsg 错误提示语
     */
    public BusinessException(String code, String errorMsg) {
        super("");
        this.code = code;
        this.errorMsg = errorMsg;
    }

    /**
     * 构建异常返回值
     *
     * @param code     错误码
     * @param errorMsg 错误提示语
     * @param message  异常信息
     */
    public BusinessException(String code, String errorMsg, String message) {
        super(message);
        this.code = code;
        this.errorMsg = errorMsg;
    }

    /**
     * 返回错误码
     *
     * @return
     */
    public String getCode() {
        return this.code;
    }

    /**
     * 返回错误提示语
     *
     * @return
     */
    public String getErrorMsg() {
        return this.errorMsg;
    }
}
