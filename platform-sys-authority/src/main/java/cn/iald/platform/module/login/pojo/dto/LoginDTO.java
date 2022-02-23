package cn.iald.platform.module.login.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 登录DTO
 *
 * @author wangyc
 * @date 2021/06/21 15:07:12
 **/
@Data
public class LoginDTO implements Serializable {

    private static final long serialVersionUID = -350643213149455432L;
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
