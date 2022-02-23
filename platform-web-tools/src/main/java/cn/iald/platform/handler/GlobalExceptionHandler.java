package cn.iald.platform.handler;

import cn.iald.platform.base.vo.ResponseVO;
import cn.iald.platform.enums.SysCodeEnum;
import cn.iald.platform.exception.BusinessException;
import cn.iald.platform.exception.TokenException;
import cn.iald.platform.util.MessageUtil;
import cn.iald.platform.util.ResponseUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 *
 * @author Administrator
 * @date 2020-10-23 17:51:00
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Resource
    private HttpServletRequest request;

    private static final String ACCESS = "AccessDeniedException";

    @ExceptionHandler(value = BindException.class)
    public ResponseVO methodValidExceptionHandler(BindException e) {
        return ResponseUtil.error(e.getBindingResult().getFieldError().getDefaultMessage(), e.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseVO methodValidExceptionHandler(MethodArgumentNotValidException e) {
        return ResponseUtil.error(e.getBindingResult().getFieldError().getDefaultMessage(), e.getMessage());
    }

    @ExceptionHandler(value = JsonProcessingException.class)
    public ResponseVO jsonProcessingExceptionHandler(JsonProcessingException e) {
        log.error("JSON数据处理异常：" + e.getMessage(), e);
        return ResponseUtil.error(MessageUtil.getMessage("json.exception.msg", request), e.getMessage());
    }

    @ExceptionHandler(value = BusinessException.class)
    public ResponseVO businessExceptionHandler(BusinessException e) {
        return ResponseUtil.error(e.getCode(), e.getErrorMsg(), e.getMessage());
    }

    @ExceptionHandler(value = TokenException.class)
    public ResponseVO tokenExceptionHandler(TokenException e) {
        return ResponseUtil.error(SysCodeEnum.TOKEN_EXPIRE.getCode(), e.getMessage(), e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseVO exceptionHandler(Exception e) {
        if (e.getClass().toString().indexOf(ACCESS) > -1) {
            return ResponseUtil.error(SysCodeEnum.TOKEN_ACCESS_DENIED.getCode(), e.getMessage(), e.getMessage());
        }
        log.error("程序异常：" + e.getMessage(), e);
        return ResponseUtil.error(SysCodeEnum.ERROR.getCode(), MessageUtil.getMessage("web.exception.msg", request)
                , e.getMessage());
    }
}
