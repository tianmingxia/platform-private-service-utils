package cn.iald.platform.module.user.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 用户-角色表Vo类
 *
 * @author wangyc
 * @version 2020年10月21日 15:02:58 初始创建
 */
@Data
public class UserRoleVO implements Serializable {

    private static final long serialVersionUID = 4984974654691278527L;
    /**
     * 主键
     */
    private Long userRoleId;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 赋权时间
     */
    private Timestamp createTime;

}
