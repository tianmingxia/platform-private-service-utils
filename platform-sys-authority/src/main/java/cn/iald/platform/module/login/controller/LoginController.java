package cn.iald.platform.module.login.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.iald.platform.base.vo.ResponseVO;
import cn.iald.platform.common.constant.LoginConstant;
import cn.iald.platform.module.login.pojo.dto.LoginDTO;
import cn.iald.platform.module.login.pojo.dto.LoginKeyDTO;
import cn.iald.platform.module.login.pojo.dto.LoginNoTokenDTO;
import cn.iald.platform.module.login.pojo.vo.BusinessLoginVO;
import cn.iald.platform.module.login.service.LoginService;
import cn.iald.platform.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 经营主体用户表Controller
 *
 * @author wangyc
 * @date 2021/07/12 17:47:50
 */
@Slf4j
@RestController
@RequestMapping("/v1/login")
public class LoginController {

    @Resource
    private LoginService loginService;

    /**
     * 经营主体用户登录
     *
     * @param loginDTO
     * @param request
     * @return
     */
    @PostMapping("/business")
    public ResponseVO businessLogin(@RequestBody @Valid LoginDTO loginDTO, HttpServletRequest request) {
        LoginNoTokenDTO loginNoTokenDTO = new LoginNoTokenDTO();
        BeanUtil.copyProperties(loginDTO, loginNoTokenDTO);
        loginNoTokenDTO.setLoginIp(request.getHeader(LoginConstant.LOGIN_IP));
        BusinessLoginVO businessLoginVO = this.loginService.getBusinessUserPwsLogin(loginNoTokenDTO);
        if (StrUtil.isNotBlank(businessLoginVO.getToken())) {
            businessLoginVO.setToken(LoginConstant.TOKEN_PREFIX + businessLoginVO.getToken());
        }
        return ResponseUtil.success(businessLoginVO);
    }

    /**
     * 自动登录系统
     *
     * @param key
     * @param request
     * @return
     * @throws Exception
     */
    @GetMapping("/business/{key}")
    public ResponseVO businessKeyLogin(@PathVariable("key") String key, HttpServletRequest request) throws Exception {
        LoginKeyDTO loginKeyDTO = new LoginKeyDTO();
        loginKeyDTO.setKey(key);
        loginKeyDTO.setLoginIp(request.getHeader(LoginConstant.LOGIN_IP));
        BusinessLoginVO businessLoginVO = this.loginService.getBusinessUserKey(loginKeyDTO);
        if (StrUtil.isNotBlank(businessLoginVO.getToken())) {
            businessLoginVO.setToken(LoginConstant.TOKEN_PREFIX + businessLoginVO.getToken());
        }
        return ResponseUtil.success(businessLoginVO);
    }
}
