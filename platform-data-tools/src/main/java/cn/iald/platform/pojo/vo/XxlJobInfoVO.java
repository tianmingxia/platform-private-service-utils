package cn.iald.platform.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * XxlJobVO
 *
 * @author wangyc
 * @date 2021/07/20 17:43:30
 **/
@Data
public class XxlJobInfoVO implements Serializable {

    private static final long serialVersionUID = 6418572502955408531L;
    /**
     * 任务主键ID
     */
    private Integer id;

    /**
     * 执行器主键ID
     */
    private Integer jobGroup;

    /**
     * 任务描述
     */
    private String jobDesc;

    /**
     * 负责人
     */
    private String author;

    /**
     * 报警邮件
     */
    private String alarmEmail;

    /**
     * 调度类型
     */
    private String scheduleType;

    /**
     * 调度配置，值含义取决于调度类型
     */
    private String scheduleConf;

    /**
     * 调度过期策略
     */
    private String misfireStrategy;

    /**
     * 执行器路由策略
     */
    private String executorRouteStrategy;

    /**
     * 执行器，任务Handler名称
     */
    private String executorHandler;

    /**
     * 执行器，任务参数
     */
    private String executorParam;

    /**
     * 阻塞处理策略
     */
    private String executorBlockStrategy;

    /**
     * 任务执行超时时间，单位秒
     */
    private Integer executorTimeout;

    /**
     * 失败重试次数
     */
    private Integer executorFailRetryCount;

    /**
     * GLUE类型	#com.xxl.job.core.glue.GlueTypeEnum
     */
    private String glueType;

    /**
     * GLUE源代码
     */
    private String glueSource;

    /**
     * GLUE备注
     */
    private String glueRemark;

    /**
     * 子任务ID，多个逗号分隔
     */
    private String childJobId;

    /**
     * 调度状态：0-停止，1-运行
     */
    private Integer triggerStatus;

    /**
     * 上次调度时间
     */
    private Long triggerLastTime;

    /**
     * 下次调度时间
     */
    private Long triggerNextTime;
}
