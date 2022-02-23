package cn.iald.platform.aspect;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import cn.iald.platform.util.HttpContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 记录请求日志
 *
 * @author wangyc
 * @date 2021/06/03 17:31:04
 **/
@Slf4j
@Aspect
@Component
public class LogAspect {

    @Pointcut("execution(* cn.iald..*.*Controller.*(..))")
    public void controllerLogPointCut() {
    }

    @AfterReturning(pointcut = "controllerLogPointCut()", returning = "retVal")
    public void controllerLogReturnOutput(JoinPoint joinPoint, Object retVal) {
        try {
            this.logOutPut(joinPoint, StrUtil.format("执行结果:[{}]", (retVal != null ? JSONUtil.toJsonStr(retVal) : "")));
        } catch (Exception e) {
            log.info("记录请求日志异常" + e.getMessage());
        }
    }

    @AfterThrowing(pointcut = "controllerLogPointCut()", throwing = "ex")
    public void controllerLogThrowOutput(JoinPoint joinPoint, Throwable ex) {
        try {
            this.logOutPut(joinPoint, StrUtil.format("抛出异常:[{}]", ex.getMessage()));
        } catch (Exception e) {
            log.info("记录请求日志异常" + e.getMessage());
        }
    }

    /**
     * 输出日志
     *
     * @param joinPoint
     * @param resMsg
     */
    private void logOutPut(JoinPoint joinPoint, String resMsg) {
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Object target = joinPoint.getTarget();
            // 方法名称
            String methodName = signature.getMethod().getName();
            // 类名称
            String className = target.getClass().getName();
            // 请求参数
            Object[] objs = joinPoint.getArgs();
            String ip = null;
            HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
            if (request != null) {
                ip = request.getHeader("LoginIp");
            }
            log.info("[{}]：发起请求,类名称:[{}},方法名称:[{}],请求参数:{},{}",
                    (StrUtil.isEmpty(ip) ? "" : ip), className, methodName, (objs != null ? JSONUtil.toJsonStr(objs) : ""), resMsg);
        } catch (Exception e) {
            log.info("记录请求日志异常" + e.getMessage());
        }
    }
}
