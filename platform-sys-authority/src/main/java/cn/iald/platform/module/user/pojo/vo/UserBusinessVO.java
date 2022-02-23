package cn.iald.platform.module.user.pojo.vo;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 经营主体用户表VO类
 *
 * @author wangyc
 * @date 2021/07/12 17:47:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBusinessVO implements Serializable {

    private static final long serialVersionUID = -1023376721907456116L;
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 所属经营主体id
     */
    private Long businessId;

    /**
     * 登录名（唯一）
     */
    private String username;

    /**
     * 密码（密文）
     */
    private String password;

    /**
     * 真实姓名（加密）
     */
    private String realname;

    /**
     * 身份证号（加密）
     */
    private String idCard;

    /**
     * 性别
     */
    private Integer sexType;

    /**
     * 民族编码
     */
    private String nationCode;

    /**
     * 籍贯
     */
    private String homeTown;

    /**
     * 户籍地
     */
    private String domicile;

    /**
     * 暂住地
     */
    private String tempResidence;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否管理员标志
     */
    private Integer adminFlag;

    /**
     * 状态（1有效 -1禁用 ）
     */
    private Integer status;

    /**
     * 是否删除
     */
    private Integer delFlag;

    /**
     * 创建人
     */
    private Long createBy;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 修改人
     */
    private Long updateBy;

    /**
     * 修改时间
     */
    private Long updateTime;

}
