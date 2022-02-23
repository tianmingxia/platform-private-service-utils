package cn.iald.platform.exception;

/**
 * Token异常
 *
 * @author wangyc
 * @date 2020-10-23 17:51:00
 **/
public class TokenException extends RuntimeException {
    public TokenException(String message) {
        super(message);
    }
}
