package cn.iald.platform.enums;

/**
 * 系统返回结果编码枚举
 *
 * @author wangyc
 * @date 2020-11-13 16:35:00
 */
public enum SysCodeEnum {
    /**
     * 通用成功提示
     */
    SUCCESS("0", "处理成功"),
    /**
     * 通用异常提示
     */
    ERROR("-1", "程序异常"),
    /**
     * TOKEN权限不足
     */
    TOKEN_ACCESS_DENIED("401", "TOKEN权限不足"),
    /**
     * token异常，需重新获取
     */
    TOKEN_EXPIRE("1400", "token异常，需重新获取"),
    /**
     * token过期
     */
    TOKEN_EXPIRED("1401", "访问令牌已过期"),
    /**
     * 数据已使用
     */
    REPEAT("2001", "数据已使用"),
    /**
     * 符合要求的数据不存在
     */
    NULL("2002", "符合要求的数据不存在"),
    /**
     * 数据已逻辑删除
     */
    DEL("2003", "数据已逻辑删除"),
    /**
     * 数据已禁用
     */
    DISABLE("2004", "数据已禁用"),
    /**
     * 密码错误
     */
    PWD("2005", "密码错误"),
    /**
     * 密码错误
     */
    EXPIRE("2006", "数据已过期"),
    /**
     * 用户名已存在
     */
    USER_NAME_EXIST("A0111", "用户名已存在"),
    /**
     * 手机格式校验失败
     */
    PHONE_CHECK_FAILURE("A0151", "手机格式校验失败"),
    /**
     * 地址格式校验失败
     */
    ADDRESS_CHECK_FAILURE("A0152", "地址格式校验失败"),
    /**
     * 邮箱格式校验失败
     */
    EMAIL_CHECK_FAILURE("A0153", "邮箱格式校验失败"),
    /**
     * 用户账户不存在
     */
    USER_NULL("A0201", "用户账户不存在"),
    /**
     * 用户账户被冻结
     */
    USER_FREEZE("A0202", "用户账户被冻结"),
    /**
     * 用户账户已作废
     */
    USER_INVALID("A0203", "用户账户已作废"),
    /**
     * 用户密码错误
     */
    PWD_ERROR("A0210", "用户密码错误"),
    /**
     * 用户输入密码错误次数超限
     */
    PWD_ERROR_OVERRUN("A0211", "用户输入密码错误次数超限"),
    /**
     * 用户身份校验失败
     */
    USER_CHECK_FAILURE("A0220", "用户身份校验失败"),
    /**
     * 用户登录已过期
     */
    USER_LOGIN_EXPIRED("A0230", "用户登录已过期"),
    /**
     * 用户验证码尝试次数超限
     */
    USER_CHECK_CODE_OVERRUN("A0241", "用户验证码尝试次数超限"),
    /**
     * 访问未授权
     */
    UNAUTHORIZED_ACCESS("A0301", "访问未授权"),
    /**
     * 授权已过期
     */
    AUTHORIZATION_EXPIRED("A0311", "授权已过期"),
    /**
     * 无权限使用 API
     */
    NO_PERMISSION_TO_USE_API("A0312", "无权限使用 API"),
    /**
     * 黑名单用户
     */
    BLACKLIST_USER("A0321", "黑名单用户"),
    /**
     * 账号被冻结
     */
    ACCOUNT_FREEZE("A0322", "账号被冻结"),
    /**
     * 非法 IP 地址
     */
    ILLEGAL_IP("A0323", "非法 IP 地址"),
    /**
     * 网关访问受限
     */
    GATEWAY_ACCESS("A0324", "网关访问受限"),
    /**
     * 系统执行出错
     */
    SYSTEM_ERROR("B0001", "系统执行出错"),
    /**
     * 系统执行超时
     */
    SYSTEM_TIMEOUT("B0100", "系统执行超时"),
    /**
     * 系统订单处理超时
     */
    SYSTEM_ORDER_TIMEOUT("B0101", "系统订单处理超时"),
    /**
     * 调用客户端异常（调用第三方服务出错）
     */
    CLIENT("C0001", "调用客户端异常（调用第三方服务出错）"),
    /**
     * **中间件服务出错
     */
    MIDDLEWARE_SERVICE("C0100", "**中间件服务出错"),
    /**
     * 不支持的数据格式
     */
    UNSUPPORTED_DATA_FORMAT("C0134", "不支持的数据格式"),
    /**
     * 短信提醒服务失败
     */
    SMS_NOTIFICATION("C0501", "短信提醒服务失败");

    /**
     * 异常编码
     */
    private String code;

    /**
     * 异常描述
     */
    private String errorMsg;

    public String getCode() {
        return this.code;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    SysCodeEnum(String code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
    }
}
