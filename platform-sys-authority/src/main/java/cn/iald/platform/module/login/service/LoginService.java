package cn.iald.platform.module.login.service;

import cn.iald.platform.module.login.pojo.dto.LoginKeyDTO;
import cn.iald.platform.module.login.pojo.dto.LoginNoTokenDTO;
import cn.iald.platform.module.login.pojo.vo.BusinessLoginVO;

/**
 * 登录Service
 *
 * @author wangyc
 * @date 2021/07/12 18:00:17
 **/
public interface LoginService {

    /**
     * 基于用户名密码登录经营主体用户
     *
     * @param loginNoTokenDTO
     * @return
     */
    BusinessLoginVO getBusinessUserPwsLogin(LoginNoTokenDTO loginNoTokenDTO);

    /**
     * 自动登录系统
     *
     * @param loginKeyDTO
     * @return
     * @throws Exception
     */
    BusinessLoginVO getBusinessUserKey(LoginKeyDTO loginKeyDTO) throws Exception;
}
