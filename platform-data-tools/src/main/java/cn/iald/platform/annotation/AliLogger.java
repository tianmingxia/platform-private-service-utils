package cn.iald.platform.annotation;

import java.lang.annotation.*;

/**
 * 阿里日志记录
 *
 * @author wangyc
 * @date 2021/06/03 17:31:04
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AliLogger {

    String className() default "";

    String value() default "";
}
