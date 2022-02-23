package cn.iald.platform.utils;

import org.apache.commons.lang3.ArrayUtils;

import java.math.BigDecimal;

/**
 * @description: BigDecimal工具类
 * @author: wangyc
 * @create: 2021-01-13 15:08
 **/
public class BigDecimalUtils {

	/**
	 * 10的常量字符串
	 */
	public static final String TEN = "10";

	/**
	 * 100的常量字符串
	 */
	public static final String HUNDRED = "100";

	/**
	 * 10000的常量字符串
	 */
	public static final String TEN_THOUSAND = "10000";

	/**
	 * 乘法
	 *
	 * @param scale        四舍五入后保留精度,精度为null返回原值
	 * @param multiplicand 被乘数
	 * @param multiplier   乘数
	 * @return
	 */
	public static BigDecimal multiply(Integer scale, String multiplicand, String... multiplier) {
		BigDecimal result = new BigDecimal(multiplicand);
		if (ArrayUtils.isNotEmpty(multiplier)) {
			for (String temp : multiplier) {
				result = result.multiply(new BigDecimal(temp));
			}
		}
		return scale == null ? result : result.setScale(scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 乘法
	 *
	 * @param scale        指定最终返回值舍入模式后保留精度,精度为null返回原值
	 * @param roundingMode 指定最终返回值舍入模式，例如BigDecimal.ROUND_HALF_UP
	 * @param multiplicand 被乘数
	 * @param multiplier   乘数
	 * @return
	 */
	public static BigDecimal multiply(Integer scale, int roundingMode, String multiplicand, String... multiplier) {
		BigDecimal result = new BigDecimal(multiplicand);
		if (ArrayUtils.isNotEmpty(multiplier)) {
			for (String temp : multiplier) {
				result = result.multiply(new BigDecimal(temp));
			}
		}
		return scale == null ? result : result.setScale(scale, roundingMode);
	}

	/**
	 * 除法
	 *
	 * @param scale    四舍五入后保留精度,精度为null返回原值
	 * @param dividend 被除数
	 * @param divisor  除数
	 * @return
	 */
	public static BigDecimal divide(Integer scale, String dividend, String... divisor) throws ArithmeticException {
		BigDecimal result = new BigDecimal(dividend);
		if (ArrayUtils.isNotEmpty(divisor)) {
			for (String temp : divisor) {
				result = result.divide(new BigDecimal(temp), 11, BigDecimal.ROUND_HALF_EVEN);
			}
		}
		return scale == null ? result : result.setScale(scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 除法
	 *
	 * @param scale        指定最终返回值舍入模式后保留精度,精度为null返回原值
	 * @param roundingMode 指定最终返回值舍入模式，例如BigDecimal.ROUND_HALF_UP
	 * @param dividend     被除数
	 * @param divisor      除数
	 * @return
	 */
	public static BigDecimal divide(Integer scale, int roundingMode, String dividend, String... divisor) throws ArithmeticException {
		BigDecimal result = new BigDecimal(dividend);
		if (ArrayUtils.isNotEmpty(divisor)) {
			for (String temp : divisor) {
				result = result.divide(new BigDecimal(temp), 11, BigDecimal.ROUND_HALF_EVEN);
			}
		}
		return scale == null ? result : result.setScale(scale, roundingMode);
	}

	/**
	 * 减法
	 *
	 * @param scale      四舍五入后保留精度,精度为null返回原值
	 * @param subtract   被减数
	 * @param subtrahend 减数
	 * @return
	 */
	public static BigDecimal subtract(Integer scale, String subtract, String... subtrahend) throws ArithmeticException {
		BigDecimal result = new BigDecimal(subtract);
		if (ArrayUtils.isNotEmpty(subtrahend)) {
			for (String temp : subtrahend) {
				result = result.subtract(new BigDecimal(temp));
			}
		}
		return scale == null ? result : result.setScale(scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 减法
	 *
	 * @param scale        指定最终返回值舍入模式后保留精度,精度为null返回原值
	 * @param roundingMode 指定最终返回值舍入模式，例如BigDecimal.ROUND_HALF_UP
	 * @param subtract     被减数
	 * @param subtrahend   减数
	 * @return
	 */
	public static BigDecimal subtract(Integer scale, int roundingMode, String subtract, String... subtrahend) throws ArithmeticException {
		BigDecimal result = new BigDecimal(subtract);
		if (ArrayUtils.isNotEmpty(subtrahend)) {
			for (String temp : subtrahend) {
				result = result.subtract(new BigDecimal(temp));
			}
		}
		return scale == null ? result : result.setScale(scale, roundingMode);
	}

	/**
	 * 加法
	 *
	 * @param scale  四舍五入后保留精度,精度为null返回原值
	 * @param addend 加数
	 * @param augend 被加数
	 * @return
	 */
	public static BigDecimal add(Integer scale, String addend, String... augend) throws ArithmeticException {
		BigDecimal result = new BigDecimal(addend);
		if (ArrayUtils.isNotEmpty(augend)) {
			for (String temp : augend) {
				result = result.add(new BigDecimal(temp));
			}
		}
		return scale == null ? result : result.setScale(scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 加法
	 *
	 * @param scale        指定最终返回值舍入模式后保留精度,精度为null返回原值
	 * @param roundingMode 指定最终返回值舍入模式，例如BigDecimal.ROUND_HALF_UP
	 * @param addend       加数
	 * @param augend       被加数
	 * @return
	 */
	public static BigDecimal add(Integer scale, int roundingMode, String addend, String... augend) throws ArithmeticException {
		BigDecimal result = new BigDecimal(addend);
		if (ArrayUtils.isNotEmpty(augend)) {
			for (String temp : augend) {
				result = result.add(new BigDecimal(temp));
			}
		}
		return scale == null ? result : result.setScale(scale, roundingMode);
	}
}
