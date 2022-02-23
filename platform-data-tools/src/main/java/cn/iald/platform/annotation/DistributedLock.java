package cn.iald.platform.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 分布式锁
 *
 * @author wangyc
 * @date 2021/06/13 10:40:38
 **/
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributedLock {

    /**
     * 缓存名称
     *
     * @return
     */
    String cacheName() default "";

    /**
     * 缓存key
     *
     * @return
     */
    String cacheKey() default "";

    /**
     * 正在使用时，冲突请求是否立即结束
     *
     * @return
     */
    boolean endBool() default true;

    /**
     * 正在使用时，冲突请求立即结束提示语
     *
     * @return
     */
    String endMsg() default "";

    /**
     * 释放时间
     *
     * @return
     */
    long time() default 60;

    /**
     * 时间单位
     *
     * @return
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;
}