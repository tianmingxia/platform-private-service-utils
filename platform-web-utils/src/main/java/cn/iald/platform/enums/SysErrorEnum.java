package cn.iald.platform.enums;

/**
 * @description: 异常编码枚举
 * @author: wangyc
 */
public enum SysErrorEnum {
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
	EXPIRE("2006", "数据已过期");

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

	SysErrorEnum(String code, String errorMsg) {
		this.code = code;
		this.errorMsg = errorMsg;
	}
}
