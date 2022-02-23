package cn.iald.platform.module.user.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户-角色表QueryDto类
 *
 * @author wangyc
 * @version 2020年10月21日 15:02:58 初始创建
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleQueryDTO implements Serializable {

    private static final long serialVersionUID = -1615541737885020281L;
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 角色所属系统id
     */
    private Integer systemId;

}
