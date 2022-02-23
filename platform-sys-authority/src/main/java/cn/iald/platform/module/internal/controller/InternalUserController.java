package cn.iald.platform.module.internal.controller;

import cn.iald.platform.base.vo.ResponseVO;
import cn.iald.platform.module.internal.pojo.dto.BusinessUserDTO;
import cn.iald.platform.module.user.service.UserBusinessService;
import cn.iald.platform.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 经营主体用户表Controller
 *
 * @author wangyc
 * @date 2021/07/12 17:47:50
 */
@Slf4j
@RestController
@RequestMapping("/v1/internal/user")
public class InternalUserController {

    @Resource
    private UserBusinessService userBusinessService;

    /**
     * 查经营主体用户是否可登录系统
     *
     * @param businessUserDTO
     * @return
     * @throws Exception
     */
    @PostMapping("/checkBusinessUser")
    public ResponseVO checkBusinessUser(@RequestBody @Valid BusinessUserDTO businessUserDTO) throws Exception {
        return ResponseUtil.success(this.userBusinessService.checkBusinessUser(businessUserDTO));
    }
}
