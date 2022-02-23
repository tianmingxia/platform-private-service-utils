package cn.iald.platform.common.feign;

import cn.iald.platform.base.vo.ResponseVO;
import cn.iald.platform.module.login.pojo.dto.LoginNoTokenDTO;
import cn.iald.platform.module.user.pojo.dto.UserRoleQueryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @description: 平台权限管理客户端
 * @author: wangyc
 * @create: 2020-12-12 19:16
 **/
@FeignClient(name = "pca")
public interface PlatformAuthClient {

    /**
     * 用户登录
     *
     * @param loginNoTokenDTO
     * @return
     */
    @PostMapping(value = "/noToken/internal/login")
    ResponseVO login(@RequestBody LoginNoTokenDTO loginNoTokenDTO);

    /**
     * 获取用户的角色信息
     *
     * @param userRoleQueryDTO
     * @return
     */
    @PostMapping(value = "/internal/findUserRole")
    ResponseVO findUserRole(@RequestBody UserRoleQueryDTO userRoleQueryDTO);
}
