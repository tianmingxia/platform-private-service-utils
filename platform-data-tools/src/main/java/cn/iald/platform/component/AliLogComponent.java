package cn.iald.platform.component;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import cn.iald.platform.annotation.AliLogger;
import cn.iald.platform.util.HttpContextUtil;
import com.aliyun.openservices.log.Client;
import com.aliyun.openservices.log.common.LogItem;
import com.aliyun.openservices.log.request.PutLogsRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Vector;

/**
 * 阿里云日志发送
 *
 * @author wangyc
 * @date 2020-11-06 15:29
 **/
@Slf4j
@Component
public class AliLogComponent {

    @Resource
    private TaskExecutor taskExecutor;

    @Value("${aliLog.logEndpoint:}")
    private String logEndpoint;

    @Value("${aliLog.logAccessKey:}")
    private String logAccessKey;

    @Value("${aliLog.logAccessKeySecret:}")
    private String logAccessKeySecret;

    @Value("${aliLog.logProject:}")
    private String logProject;

    @Value("${aliLog.logLibrary:}")
    private String logLibrary;

    /**
     * 异步上传阿里云日志
     *
     * @param className 接口访问url
     * @param inPut     传递的参数
     * @param outPut    返回的参数
     */
    public void sendPoliceLog(String className, String inPut, String outPut) {
        taskExecutor.execute(() -> {
            try {
                Client client = new Client(logEndpoint, logAccessKey, logAccessKeySecret);
                // 写入日志
                String topic = "";
                String source = "";
                Vector<LogItem> logGroup = new Vector<>();
                LogItem logItem = new LogItem((int) (System.currentTimeMillis() / 1000));
                logItem.PushBack("Time", new Timestamp(System.currentTimeMillis()) + "");
                logItem.PushBack("ClassName", className);
                logItem.PushBack("InPut", inPut);
                logItem.PushBack("OutPut", outPut);
                logGroup.add(logItem);
                PutLogsRequest req = new PutLogsRequest(logProject, logLibrary, topic, source, logGroup);
                client.PutLogs(req);
                log.info(System.currentTimeMillis() + ":阿里云日志发送[" + className + "],[" + inPut + "],[" + outPut + "]");
            } catch (Exception e) {
                log.error("上传阿里云日志异常：" + e.getMessage(), e);
            }
        });
    }


    /**
     * 切面日常日志
     *
     * @param joinPoint
     * @param resMsg
     */
    public void sendPoliceLog(JoinPoint joinPoint, String resMsg) {
        try {
            // 请求参数
            Object[] objs = joinPoint.getArgs();
            String ip = null;
            HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
            if (request != null) {
                ip = request.getHeader("LoginIp");
            }
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Method method = methodSignature.getMethod();
            AliLogger aliLogger = method.getAnnotation(AliLogger.class);
            String className = aliLogger.className();
            String value = aliLogger.value();
            this.sendPoliceLog(className, StrUtil.format("{}-[ThreadLocal-{}]-[ThreadName-{}]-{}-请求参数:{}",
                    (StrUtil.isEmpty(ip) ? "" : ip), Thread.currentThread().getId(),Thread.currentThread().getName()
                    , value, (objs != null ? JSONUtil.toJsonStr(objs) : "")),
                    resMsg);
        } catch (Exception e) {
            log.error("提交阿里云日志异常" + e.getMessage(), e);
        }
    }
}
