package cn.iald.platform.constant;

/**
 * 调度工具常量
 *
 * @author wangyc
 * @date 2021/07/21 09:32:08
 **/
public class XxlJobConstant {

    /**
     * 请求成功返回的code
     */
    public static final int SUCCESS_CODE = 200;

    /**
     * 登录接口
     */
    public static final String JOB_ADMIN_LOGIN = "{}/login?userName={}&password={}";

    /**
     * 登录cookie
     */
    public static final String XXL_JOB_LOGIN_IDENTITY = "XXL_JOB_LOGIN_IDENTITY";

    /**
     * 新增任务
     */
    public static final String JOB_INFO_ADD = "{}/jobinfo/add";

    /**
     * 执行一次任务
     */
    public static final String JOB_INFO_TRIGGER = "{}/jobinfo/trigger";

    /**
     * 根据任务id获取任务
     */
    public static final String JOB_INFO_GET = "{}/jobinfo/getJobInfo?id={}";

    /**
     * 修改任务
     */
    public static final String JOB_INFO_UPDATE = "{}/jobinfo/update";

    /**
     * 删除任务
     */
    public static final String JOB_INFO_DELETE = "{}/jobinfo/delete?id={}";

    /**
     * 开始任务
     */
    public static final String JOB_INFO_START = "{}/jobinfo/start?id={}";

    /**
     * 停止任务
     */
    public static final String JOB_INFO_STOP = "{}/jobinfo/stop?id={}";

    /**
     * 调度类型-NONE(无)
     */
    public static final String SCHEDULE_TYPE_NONE = "NONE";

    /**
     * 调度类型-CRON
     */
    public static final String SCHEDULE_TYPE_CRON = "CRON";

    /**
     * 调度类型-固定速度
     */
    public static final String SCHEDULE_TYPE_FIX_RATE = "FIX_RATE";

    /**
     * 调度过期策略-忽略
     */
    public static final String MISFIRE_STRATEGY_DO_NOTHING = "DO_NOTHING";

    /**
     * 执行器路由策略-第一个
     */
    public static final String EXECUTOR_ROUTE_STRATEGY_FIRST = "FIRST";

    /**
     * 执行器路由策略-HASH（一致性HASH）：每个任务按照Hash算法固定选择某一台机器，且所有任务均匀散列在不同机器上
     */
    public static final String EXECUTOR_ROUTE_STRATEGY_CONSISTENT_HASH = "CONSISTENT_HASH";

    /**
     * 阻塞处理策略-单机串行
     */
    public static final String EXECUTOR_BLOCK_STRATEGY_SERIAL_EXECUTION = "SERIAL_EXECUTION";

    /**
     * 参数分隔符
     */
    public static final String PARAM_SPLIT = ",";
}
