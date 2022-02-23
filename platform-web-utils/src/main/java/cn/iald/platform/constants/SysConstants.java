package cn.iald.platform.constants;

/**
 * @description: 系统常量类
 * @author: wangyc
 * @create: 2020-10-16 10:52
 **/
public class SysConstants {

	/**
	 * 通用状态-有效
	 * 2020-5-21 废弃，后续不在使用
	 * 替换值：StatusConstant.STATUS_VALID
	 */
	@Deprecated
	public static final Integer STATUS_VALID = 1;

	/**
	 * 通用状态-禁用
	 * 2020-5-21 废弃，后续不在使用
	 * 替换值：StatusConstant.STATUS_DISABLE
	 */
	@Deprecated
	public static final Integer STATUS_DISABLE = -1;

	/**
	 * 通用状态-删除
	 * 2020-5-21 彻底废弃，后续不在使用
	 */
	@Deprecated
	public static final Integer STATUS_DEL = 0;

	/**
	 * 通用标志-是
	 * 2020-5-21 废弃，后续不在使用
	 * 替换值：FlagConstant.FLAG_YES
	 */
	@Deprecated
	public static final Integer FLAG_YES = 1;

	/**
	 * 通用标志-否
	 * 2020-5-21 废弃，后续不在使用
	 * 替换值：FlagConstant.FLAG_NO
	 */
	@Deprecated
	public static final Integer FLAG_NO = 0;

	/**
	 * 性别-男
	 * 2020-5-21 废弃，后续不在使用
	 * 替换值：TypeConstant.SEX_TYPE_MAN
	 */
	@Deprecated
	public static final Integer SEX_MAN = 1;

	/**
	 * 性别-女
	 * 2020-5-21 废弃，后续不在使用
	 * 替换值：TypeConstant.SEX_TYPE_WOMAN
	 */
	@Deprecated
	public static final Integer SEX_WOMAN = 2;

	/**
	 * 性别-未知
	 * 2020-5-21 废弃，后续不在使用
	 * 替换值：TypeConstant.SEX_TYPE_UNKNOWN
	 */
	@Deprecated
	public static final Integer SEX_UNKNOWN = 3;

	/**
	 * 获取数据粒度 1-指定类型的数据本身
	 * 2020-5-21 废弃，后续不在使用
	 * 替换值：FlagConstant.CHILD_FLAG_SELF
	 */
	@Deprecated
	public static final String CHILDFLAG1 = "1";

	/**
	 * 获取数据粒度 2-指定类型的数据及其直属子节点
	 * 2020-5-21 废弃，后续不在使用
	 * 替换值：FlagConstant.CHILD_FLAG_FIRST
	 */
	@Deprecated
	public static final String CHILDFLAG2 = "2";

	/**
	 * 获取数据粒度 3-指定类型的数据及其下辖所有节点
	 * 2020-5-21 废弃，后续不在使用
	 * 替换值：FlagConstant.CHILD_FLAG_ALL
	 */
	@Deprecated
	public static final String CHILDFLAG3 = "3";

	/**
	 * 区域类型province-省
	 * 2020-5-21 废弃，后续不在使用
	 * 替换值：TypeConstant.PROVINCE
	 */
	@Deprecated
	public static final String PROVINCE = "province";

	/**
	 * 区域类型city-市
	 * 2020-5-21 废弃，后续不在使用
	 * 替换值：TypeConstant.CITY
	 */
	@Deprecated
	public static final String CITY = "city";

	/**
	 * 区域类型area-区县
	 * 2020-5-21 废弃，后续不在使用
	 * 替换值：TypeConstant.AREA
	 */
	@Deprecated
	public static final String AREA = "area";

	/**
	 * 经营主体类型-个人
	 */
	public static final Integer BUSINESS_PERSONAL = 1;

	/**
	 * 经营主体类型-企业
	 */
	public static final Integer BUSINESS_ENTERPRISE = 2;

	/**
	 * 经营主体开通状态-上架
	 */
	public static final Integer BUSINESS_UP = 1;

	/**
	 * 经营主体开通状态-下架
	 */
	public static final Integer BUSINESS_DOWN = 0;

	/**
	 * 房间地址类型-小区
	 */
	public static final Integer ROOM_COMMUNITY = 0;

	/**
	 * 房间地址类型-楼栋
	 */
	public static final Integer ROOM_BUILDING = 1;

	/**
	 * 房间地址类型-单元
	 */
	public static final Integer ROOM_UNIT = 2;

	/**
	 * 房间地址类型-楼层
	 */
	public static final Integer ROOM_FLOOR = 3;

	/**
	 * 设备类型-开阳
	 */
	public static final Integer MACHINE_KAIYANG = 1;

	/**
	 * 设备类型-沃安
	 */
	public static final Integer MACHINE_WOAN = 2;

	/**
	 * 上传文件临时目录
	 */
	public static final String TEMP_FILE_PATH = "/tempImg/file";

	/**
	 * 上传文件网络扩展地址
	 */
	public static final String UPLOAD_URL = "upload";

	/**
	 * 上传文件网络服务器返回值分隔符
	 */
	public static final String SPLIT_STR = "MD5:";

	/**
	 * In最大数量
	 */
	public static final Integer IN_MAX = 1000;
}
