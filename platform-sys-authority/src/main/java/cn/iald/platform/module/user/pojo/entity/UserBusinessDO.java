package cn.iald.platform.module.user.pojo.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 经营主体用户表DO类
 *
 * @author wangyc
 * @date 2021/07/12 17:47:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "sys_user_business")
public class UserBusinessDO implements Serializable {

    private static final long serialVersionUID = 311320309497429666L;
    /**
     * 用户id
     */
    @TableId(value = "user_id")
    private Long userId;

    /**
     * 所属经营主体id
     */
    @TableField(value = "business_id")
    private Long businessId;

    /**
     * 登录名（唯一）
     */
    @TableField(value = "username")
    private String username;

    /**
     * 密码（密文）
     */
    @TableField(value = "password")
    private String password;

    /**
     * 真实姓名（加密）
     */
    @TableField(value = "realname")
    private String realname;

    /**
     * 身份证号（加密）
     */
    @TableField(value = "id_card")
    private String idCard;

    /**
     * 性别
     */
    @TableField(value = "sex_type")
    private Integer sexType;

    /**
     * 民族编码
     */
    @TableField(value = "nation_code")
    private String nationCode;

    /**
     * 籍贯
     */
    @TableField(value = "home_town")
    private String homeTown;

    /**
     * 户籍地
     */
    @TableField(value = "domicile")
    private String domicile;

    /**
     * 暂住地
     */
    @TableField(value = "temp_residence")
    private String tempResidence;

    /**
     * 手机号
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 是否管理员标志
     */
    @TableField(value = "is_admin_flag")
    private Integer adminFlag;

    /**
     * 状态（1有效 -1禁用 ）
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 是否删除
     */
    @TableField(value = "is_del_flag")
    private Integer delFlag;

    /**
     * 创建人
     */
    @TableField(value = "create_by")
    private Long createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Long createTime;

    /**
     * 修改人
     */
    @TableField(value = "update_by")
    private Long updateBy;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Long updateTime;

}
