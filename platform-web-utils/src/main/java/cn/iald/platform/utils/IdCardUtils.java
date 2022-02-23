package cn.iald.platform.utils;

import cn.iald.platform.constants.SysConstants;

/**
 * @description: 身份证工具类
 * @author: wangyc
 * @create: 2020-11-16 15:08
 **/
public class IdCardUtils {
	public static final Integer ID_CARD_LEN15 = 15;

	public static final Integer SEX_ID_SURPLUS = 2;

	/**
	 * 获取性别
	 *
	 * @param idCard
	 * @return
	 */
	public static Integer getSexType(String idCard) {
		Integer sexId;
		if (idCard.length() == ID_CARD_LEN15) {
			sexId = Integer.parseInt(idCard.substring(14, 15));
		} else {
			sexId = Integer.parseInt(idCard.substring(16, 17));
		}
		if (sexId % SEX_ID_SURPLUS == 0) {
			return SysConstants.SEX_WOMAN;
		}
		return SysConstants.SEX_MAN;
	}

	/**
	 * 获取省编码
	 *
	 * @param idCard
	 * @return
	 */
	public static String getProvince(String idCard) {
		return idCard.substring(0, 2);
	}

	/**
	 * 获取市编码
	 *
	 * @param idCard
	 * @return
	 */
	public static String getCity(String idCard) {
		return idCard.substring(0, 4);
	}

	/**
	 * 获取区县编码
	 *
	 * @param idCard
	 * @return
	 */
	public static String getArea(String idCard) {
		return idCard.substring(0, 6);
	}

	/**
	 * 获取生日
	 *
	 * @param idCard
	 * @return
	 */
	public static String getBirthDay(String idCard) {
		return idCard.substring(10, 14);
	}
}
