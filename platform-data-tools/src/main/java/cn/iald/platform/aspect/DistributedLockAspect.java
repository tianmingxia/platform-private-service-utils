package cn.iald.platform.aspect;

import cn.hutool.core.util.StrUtil;
import cn.iald.platform.annotation.DistributedLock;
import cn.iald.platform.enums.SysCodeEnum;
import cn.iald.platform.util.MessageUtil;
import cn.iald.platform.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * 分布式锁切面
 *
 * @author wangyc
 * @date 2021/06/13 10:43:00
 **/
@Slf4j
@Aspect
@Component
public class DistributedLockAspect {

    @Value("${spring.application.name:}")
    private String applicationName;

    @Resource
    private HttpServletRequest request;

    @Resource
    private RedissonClient redissonClient;

    /**
     * SpringEL表达式开头
     */
    private static final String EL_FIRST = "#";

    @Pointcut("@annotation(cn.iald.platform.annotation.DistributedLock)")
    public void redisLockAspect() {
    }

    @Around("redisLockAspect()")
    public Object redisLockAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object object = null;
        RLock lock = null;
        String lockKey = "";
        log.info("redisLockAspect start ");
        try {
            DistributedLock distributedLock = getDistributedLock(proceedingJoinPoint);
            lockKey = getLockKey(proceedingJoinPoint, distributedLock);
            lock = redissonClient.getLock(lockKey);
            if (lock.isLocked() && distributedLock.endBool()) {
                return ResponseUtil.error(SysCodeEnum.REPEAT.getCode(), MessageUtil.getMessage(distributedLock.endMsg()
                        , request));
            }
            boolean lockCheck = lock.tryLock(distributedLock.time(), distributedLock.timeUnit());
            log.info(LocalDateTime.now() + " lock locked lockCheck-{} {},ThreadLocal-{}", lockCheck, lockKey
                    , Thread.currentThread().getId());
            if (lockCheck) {
                object = proceedingJoinPoint.proceed();
            }
        } finally {
            // 释放锁资源(如果该线程还持有该锁，那么释放该锁。如果该线程不持有该锁，说明该线程的锁已到过期时间，自动释放锁)
            if (lock != null && lock.isHeldByCurrentThread() && lock.isLocked()) {
                lock.unlock();
                log.info(LocalDateTime.now() + "lock unlocked {},ThreadLocal-{}", lockKey
                        , Thread.currentThread().getId());
            }
        }
        return object;
    }

    /**
     * 获取缓存key
     *
     * @param proceedingJoinPoint
     * @param distributedLock
     * @return
     */
    private String getLockKey(ProceedingJoinPoint proceedingJoinPoint, DistributedLock distributedLock) {
        StringBuilder stringBuilder = new StringBuilder(applicationName).append(":").append(distributedLock.cacheName());
        if (StrUtil.isBlank(distributedLock.cacheKey())) {
            return stringBuilder.toString();
        }
        if (!distributedLock.cacheKey().contains(EL_FIRST)) {
            stringBuilder.append(":").append(distributedLock.cacheKey());
            return stringBuilder.toString();
        }
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Object[] args = proceedingJoinPoint.getArgs();
        //获取被拦截方法参数名列表(使用Spring支持类库)
        LocalVariableTableParameterNameDiscoverer localVariableTable = new LocalVariableTableParameterNameDiscoverer();
        String[] paraNameArr = localVariableTable.getParameterNames(method);
        SpelExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        //把方法参数放入SpringEL上下文中
        for (int i = 0; i < paraNameArr.length; i++) {
            context.setVariable(paraNameArr[i], args[i]);
        }
        String[] keyArr = distributedLock.cacheKey().split(",");
        stringBuilder.append(":").append(parser.parseExpression(keyArr[0]).getValue(context).toString());
        if (keyArr.length == 1) {
            return stringBuilder.toString();
        }
        for (int i = 1, len = keyArr.length; i < len; i++) {
            stringBuilder.append("_").append(parser.parseExpression(keyArr[i]).getValue(context).toString());
        }
        return stringBuilder.toString();
    }

    /**
     * 获取分布式锁信息
     *
     * @param proceedingJoinPoint
     * @return
     */
    private DistributedLock getDistributedLock(ProceedingJoinPoint proceedingJoinPoint) {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        DistributedLock distributedLock = methodSignature.getMethod().getAnnotation(DistributedLock.class);
        return distributedLock;
    }


}
