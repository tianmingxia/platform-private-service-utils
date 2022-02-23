package cn.iald.psb.common;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author lht
 */
@Data
public class RPData {
	private UploadData uploadData;
	private String config;

	@Data
	public static class UploadData {
		/**
		 * 主键
		 */
		private String id;
		/**
		 * 发证机关
		 */
		private String grantdept;

		/**
		 * 身份证地址
		 */
		private String address;

		/**
		 * 民族id
		 */
		private Integer nation;
		/**
		 * 民族名称
		 */
		private String nationName;

		/**
		 * 性别  0:女 ：男
		 */
		private Integer sex;

		/**
		 * 名字
		 */
		private String personName;

		/**
		 * 以身份证为唯一标识
		 */
		private String idCode;

		/**
		 * id身份证
		 */
		private String idImage;

		/**
		 * 英文名
		 */
		private String englishName;
		/**
		 * 英文姓
		 */
		private String englishSurname;

		/**
		 * 身份类型（身份证，2居留证）3：护照
		 */
		private Integer idType;

		/**
		 * //身份证有效期开始时间
		 */
		private String idCodeValidStart;

		/**
		 * //身份证有效期结束时间
		 */
		private String idCodeValidEnd;

		/**
		 * //是否无证入住人员
		 */
		private Integer personType;

		/**
		 * //图片采集照片
		 */
		private String cjImage;

		/**
		 * //相识度
		 */
		private String xsd;
		/**
		 * //人证是否为同一人0：是  ：否
		 */
		private Integer oneperson;
		/**
		 * //房间号
		 */
		private String roomNum;
		/**
		 * //手机号
		 */
		private String phone;
		/**
		 * 公安返回值
		 */
		private String returnValue;

		//外国人私有属性
		/**
		 * //签证类型，请参见签证类型字典
		 */
		private String visaType;
		/**
		 * //入境日期，格式：YYYYMMDD
		 */
		private Timestamp entryTime;
		/**
		 * //入境口岸 ，请参见入境口岸字典
		 */
		private String entryAddress;
		/**
		 * //护照附加页
		 */
		private String passportAttachmentOne;
		/**
		 * //护照附加页2
		 */
		private String passportAttachmentTwo;
		/**
		 * //护照附加页3
		 */
		private String passportAttachmentThree;
		/**
		 * //护照附加页4
		 */
		private String passportAttachmentFour;
		/**
		 * /签证号码，参见签证类型字典，证件类型为“普通护照”时必填
		 */
		private String visaNumber;
		/**
		 * //签证有效期，格式：YYYYMMDD ，证件类型为“普通护照” 时必填
		 */
		private Timestamp visaValidPeriod;
		/**
		 * //何来何去，格式：何地来代码-何地去代码（此处代码为标准6位行政区划代码）
		 */
		private String whereToGo;
		/**
		 * //来华事由，参考停留事由字典
		 */
		private String content;
		/**
		 * //团队号
		 */
		private String teamNumber;
		/**
		 * //预离店日期，格式：YYYYMMDDHHMMSS
		 */
		private Timestamp pREcheckOutTime;
		/**
		 * //生日，格式：YYYYMMDD
		 */
		private Timestamp birthday;
		/**
		 * //是否是外国人  是，0，不是
		 */
		private String foreigner;
		/**
		 * //证件类型，根据证件类型枚举
		 */
		private String certificateType;
		/**
		 * //证件号码
		 */
		private String certificateCode;
		/**
		 * //证件名称
		 */
		private String certificateName;
		/**
		 * //国籍id
		 */
		private String nationalityCode;
		/**
		 * 上传时间
		 */
		private Timestamp uploadTime;

		/**
		 * 采集机器的编码
		 */
		private String machineCode;
		/**
		 * 经营主体id
		 */
		private String businessId;
		/**
		 * 房间id
		 */
		private String roomNumId;
	}

}
