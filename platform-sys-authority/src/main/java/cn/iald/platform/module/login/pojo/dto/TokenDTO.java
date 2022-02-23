package cn.iald.platform.module.login.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * TokenDTO
 *
 * @author wangyc
 * @date 2021/06/15 14:59:00
 */
@Data
public class TokenDTO implements Serializable {


    private static final long serialVersionUID = 1224527348612104492L;
    /**
     * id
     */
    private String clientId;

    /**
     * 秘钥
     */
    private String clientSecret;

    /**
     * 授权类型
     */
    private String grantType;

    /**
     * 用戶名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
