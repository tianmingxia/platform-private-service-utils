package cn.iald.platform.feign.dto;

import lombok.Data;

/**
 * @author lht
 */
@Data
public class TokenDto {
    /**
     * id
     */
    private String client_id;
    /**
     * 秘钥
     */
    private String client_secret;
    /**
     * 请求类型
     */
    private String grant_type;
    /**
     * 用戶名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
}
