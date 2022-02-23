package cn.iald.platform.module.login.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import cn.iald.platform.base.vo.ResponseVO;
import cn.iald.platform.base.vo.UserVO;
import cn.iald.platform.common.constant.LoginConstant;
import cn.iald.platform.common.feign.OauthClient;
import cn.iald.platform.common.feign.PlatformAuthClient;
import cn.iald.platform.constant.ResponseConstant;
import cn.iald.platform.constant.StatusConstant;
import cn.iald.platform.enums.SysCodeEnum;
import cn.iald.platform.exception.BusinessException;
import cn.iald.platform.module.login.pojo.dto.LoginKeyDTO;
import cn.iald.platform.module.login.pojo.dto.LoginNoTokenDTO;
import cn.iald.platform.module.login.pojo.dto.TokenDTO;
import cn.iald.platform.module.login.pojo.vo.BusinessLoginVO;
import cn.iald.platform.module.login.pojo.vo.TokenVO;
import cn.iald.platform.module.login.service.LoginService;
import cn.iald.platform.module.role.pojo.vo.RoleMenuVO;
import cn.iald.platform.module.role.service.RoleMenuService;
import cn.iald.platform.module.user.dao.UserBusinessDao;
import cn.iald.platform.module.user.pojo.dto.UserRoleQueryDTO;
import cn.iald.platform.module.user.pojo.entity.UserBusinessDO;
import cn.iald.platform.module.user.pojo.vo.UserRoleVO;
import cn.iald.platform.oauth.util.OauthUtil;
import cn.iald.platform.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 登录实现类
 *
 * @author wangyc
 * @date 2021/07/12 18:01:11
 **/
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private PlatformAuthClient platformAuthClient;

    @Resource
    private OauthClient oauthClient;

    @Resource
    private UserBusinessDao userBusinessDao;

    @Resource
    private HttpServletRequest request;

    @Resource
    private RoleMenuService roleMenuService;

    @Value("${systemId}")
    private Integer systemId;

    @Value("${clientId}")
    private String clientId;

    @Value("${clientSecret}")
    private String clientSecret;

    /**
     * 自动登录key数组长度
     */
    private static final int KEY_ARR_LEN = 2;

    @Override
    public BusinessLoginVO getBusinessUserPwsLogin(LoginNoTokenDTO loginNoTokenDTO) {
        loginNoTokenDTO.setSystemId(systemId);
        ResponseVO responseVO = this.platformAuthClient.login(loginNoTokenDTO);
        if (ResponseConstant.ERROR.equals(responseVO.getStatus())) {
            throw new BusinessException(responseVO.getCode(), responseVO.getErrorMsg());
        }
        BusinessLoginVO businessLoginVO = ResponseUtil.toBean(responseVO, BusinessLoginVO.class);
        if (businessLoginVO == null) {
            throw new BusinessException(SysCodeEnum.NULL.getCode()
                    , MessageUtil.getMessage("user.bind.none", request));
        }
        // 当前系统该用户状态
        UserBusinessDO userBusinessDO = this.userBusinessDao.selectById(businessLoginVO.getUserId());
        if (userBusinessDO == null) {
            throw new BusinessException(SysCodeEnum.NULL.getCode()
                    , MessageUtil.getMessage("user.bind.error", request));
        }
        if (!StatusConstant.STATUS_VALID.equals(userBusinessDO.getStatus())) {
            throw new BusinessException(SysCodeEnum.DISABLE.getCode()
                    , MessageUtil.getMessage("user.bind.disable", request));
        }
        businessLoginVO.setBusinessId(userBusinessDO.getBusinessId());
        if (CollUtil.isEmpty(businessLoginVO.getRoleIds())) {
            throw new BusinessException(SysCodeEnum.DISABLE.getCode()
                    , MessageUtil.getMessage("user.role.none", request));
        }
        // 对应菜单code
        RoleMenuVO roleMenuVO = this.roleMenuService.findRoleMenuVO(businessLoginVO.getRoleIds(), true);
        if (roleMenuVO == null || CollUtil.isEmpty(roleMenuVO.getMenuCodeList())) {
            throw new BusinessException(SysCodeEnum.DISABLE.getCode()
                    , MessageUtil.getMessage("user.role.none", request));
        }
        List<String> menuCodeList = roleMenuVO.getMenuCodeList();
        UserVO userVO = new UserVO();
        BeanUtil.copyProperties(userBusinessDO, userVO);
        userVO.setLoginIp(loginNoTokenDTO.getLoginIp());
        userVO.setMenuCodes(menuCodeList);
        userVO.setRoleIds(businessLoginVO.getRoleIds());
        responseVO = this.oauthClient.subUserInfo(JSONUtil.toJsonStr(userVO));
        if (ResponseConstant.ERROR.equals(responseVO.getStatus())) {
            throw new BusinessException(responseVO.getErrorMsg());
        }
        String jwtId = String.valueOf(responseVO.getData());
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setClientId(clientId);
        tokenDTO.setClientSecret(clientSecret);
        tokenDTO.setGrantType(LoginConstant.GRANT_TYPE_PWD);
        tokenDTO.setUsername(jwtId);
        tokenDTO.setPassword(loginNoTokenDTO.getPassword());
        long begin = System.currentTimeMillis();
        Map<String, ?> tokenMap = this.oauthClient.token(BeanUtil.beanToMap(tokenDTO, true, false));
        TokenVO tokenVO = BeanUtil.toBean(tokenMap, TokenVO.class);
        log.info("token:" + JSONUtil.toJsonStr(tokenVO));
        businessLoginVO.setToken(tokenVO.getAccessToken());
        businessLoginVO.setMenuCodeList(menuCodeList);
        businessLoginVO.setMenuTreeList(roleMenuVO.getMenuTreeList());
        businessLoginVO.setRoleIds(null);
        log.info("获取token时间" + (System.currentTimeMillis() - begin));
        return businessLoginVO;
    }

    @Override
    public BusinessLoginVO getBusinessUserKey(LoginKeyDTO loginKeyDTO) throws Exception {
        String key;
        try {
            key = AesUtil.decrypt(loginKeyDTO.getKey());
        } catch (Exception e) {
            throw new BusinessException(SysCodeEnum.USER_CHECK_FAILURE.getCode()
                    , MessageUtil.getMessage("user.check.failure", request), e.getMessage());
        }
        String[] keyArr = null;
        if (StrUtil.isNotBlank(key)) {
            keyArr = key.split(LoginConstant.AUTH_FLAG);
        }
        if (ArrayUtil.isEmpty(keyArr) || keyArr.length != KEY_ARR_LEN) {
            throw new BusinessException(SysCodeEnum.NULL.getCode()
                    , MessageUtil.getMessage("user.check.failure", request));
        }
        String userIdStr = keyArr[0];
        String authorization = keyArr[1];
        // 当前系统该用户状态
        UserBusinessDO userBusinessDO = this.userBusinessDao.selectById(Long.valueOf(userIdStr));
        if (userBusinessDO == null) {
            throw new BusinessException(SysCodeEnum.NULL.getCode()
                    , MessageUtil.getMessage("user.bind.error", request));
        }
        if (!StatusConstant.STATUS_VALID.equals(userBusinessDO.getStatus())) {
            throw new BusinessException(SysCodeEnum.DISABLE.getCode()
                    , MessageUtil.getMessage("user.bind.disable", request));
        }
        // 获取用户对应的角色
        UserRoleQueryDTO userRoleQueryDTO = new UserRoleQueryDTO(Long.valueOf(userIdStr), systemId);
        ResponseVO responseVO = this.platformAuthClient.findUserRole(userRoleQueryDTO);
        List<UserRoleVO> userRoleVOList = null;
        if (SysCodeEnum.SUCCESS.getCode().equals(responseVO.getCode())) {
            Object data = responseVO.getData();
            if (ObjUtil.isJsonArray(data)) {
                userRoleVOList = JSONUtil.toList(JSONUtil.parseArray(data), UserRoleVO.class);
            }
        }
        if (CollUtil.isEmpty(userRoleVOList)) {
            throw new BusinessException(SysCodeEnum.DISABLE.getCode()
                    , MessageUtil.getMessage("user.role.none", request));
        }
        String authorizationTemp = ShaUtil.encrypt(StrUtil.format("{}{}{}", userBusinessDO.getUsername()
                , LoginConstant.AUTH_FLAG, userBusinessDO.getPassword()));
        if (!authorizationTemp.equals(authorization)) {
            throw new BusinessException(SysCodeEnum.PWD.getCode()
                    , MessageUtil.getMessage("user.pwd.error", request));
        }
        List<Long> roleIdList = userRoleVOList.stream().map(UserRoleVO::getRoleId).filter(Objects::nonNull)
                .collect(Collectors.toList());
        // 对应菜单code
        RoleMenuVO roleMenuVO = this.roleMenuService.findRoleMenuVO(roleIdList, true);
        if (roleMenuVO == null || CollUtil.isEmpty(roleMenuVO.getMenuCodeList())) {
            throw new BusinessException(SysCodeEnum.DISABLE.getCode()
                    , MessageUtil.getMessage("user.role.none", request));
        }
        List<String> menuCodeList = roleMenuVO.getMenuCodeList();
        UserVO userVO = new UserVO();
        BeanUtil.copyProperties(userBusinessDO, userVO);
        userVO.setLoginIp(loginKeyDTO.getLoginIp());
        userVO.setMenuCodes(menuCodeList);
        userVO.setRoleIds(roleIdList);
        userVO.setPassword(OauthUtil.encodePwd(LoginConstant.DEF_PWD));
        responseVO = this.oauthClient.subUserInfo(JSONUtil.toJsonStr(userVO));
        if (ResponseConstant.ERROR.equals(responseVO.getStatus())) {
            throw new BusinessException(responseVO.getErrorMsg());
        }
        String jwtId = String.valueOf(responseVO.getData());
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setClientId(clientId);
        tokenDTO.setClientSecret(clientSecret);
        tokenDTO.setGrantType(LoginConstant.GRANT_TYPE_PWD);
        tokenDTO.setUsername(jwtId);
        tokenDTO.setPassword(LoginConstant.DEF_PWD);
        long begin = System.currentTimeMillis();
        Map<String, ?> tokenMap = this.oauthClient.token(BeanUtil.beanToMap(tokenDTO, true, false));
        TokenVO tokenVO = BeanUtil.toBean(tokenMap, TokenVO.class);
        log.info("token:" + JSONUtil.toJsonStr(tokenVO));
        BusinessLoginVO businessLoginVO = new BusinessLoginVO();
        BeanUtil.copyProperties(userBusinessDO, businessLoginVO);
        if (StrUtil.isNotBlank(userBusinessDO.getRealname())) {
            businessLoginVO.setRealname(AesUtil.decrypt(userBusinessDO.getRealname()));
        }
        businessLoginVO.setToken(tokenVO.getAccessToken());
        businessLoginVO.setMenuCodeList(menuCodeList);
        businessLoginVO.setMenuTreeList(roleMenuVO.getMenuTreeList());
        log.info("获取token时间" + (System.currentTimeMillis() - begin));
        return businessLoginVO;
    }
}
