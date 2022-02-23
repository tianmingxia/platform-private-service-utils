package cn.iald.platform.module.internal.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 经营主体用户校验DTO
 *
 * @author wangyc
 * @date 2021/07/15 16:52:34
 **/
@Data
public class BusinessUserDTO implements Serializable {
    private static final long serialVersionUID = -6326407624304458924L;

    /**
     * 登录名
     */
    @NotEmpty
    private String username;

    /**
     * 密码
     */
    @NotEmpty
    private String password;
}

