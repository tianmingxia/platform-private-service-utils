package cn.iald.platform.aspect;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import cn.iald.platform.component.AliLogComponent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 记录请求日志
 *
 * @author wangyc
 * @date 2021/06/03 17:31:04
 **/
@Slf4j
@Aspect
@Component
public class AliLoggerAspect {

    @Resource
    private AliLogComponent aliLogComponent;


    @Pointcut("@annotation(cn.iald.platform.annotation.AliLogger)")
    public void aliLogPointCut() {
    }

    @AfterReturning(pointcut = "aliLogPointCut()", returning = "retVal")
    public void aliLogReturnOutput(JoinPoint joinPoint, Object retVal) {
        try {
            aliLogComponent.sendPoliceLog(joinPoint, StrUtil.format("执行结果:[{}]", (retVal != null ? JSONUtil.toJsonStr(retVal) : "")));
        } catch (Exception e) {
            log.error("提交阿里云日志异常" + e.getMessage(), e);
        }
    }

    @AfterThrowing(pointcut = "aliLogPointCut()", throwing = "ex")
    public void aliLogThrowingOutput(JoinPoint joinPoint, Throwable ex) {
        try {
            aliLogComponent.sendPoliceLog(joinPoint, StrUtil.format("抛出异常:[{}]", ex.getMessage()));
        } catch (Exception e) {
            log.error("提交阿里云日志异常" + e.getMessage(), e);
        }
    }
}
