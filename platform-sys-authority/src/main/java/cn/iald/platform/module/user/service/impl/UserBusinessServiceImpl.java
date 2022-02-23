package cn.iald.platform.module.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.iald.platform.common.constant.LoginConstant;
import cn.iald.platform.constant.FlagConstant;
import cn.iald.platform.constant.StatusConstant;
import cn.iald.platform.enums.SysCodeEnum;
import cn.iald.platform.exception.BusinessException;
import cn.iald.platform.module.internal.pojo.dto.BusinessUserDTO;
import cn.iald.platform.module.internal.pojo.vo.BusinessUserVO;
import cn.iald.platform.module.user.dao.UserBusinessDao;
import cn.iald.platform.module.user.pojo.entity.UserBusinessDO;
import cn.iald.platform.module.user.pojo.vo.UserBusinessVO;
import cn.iald.platform.module.user.service.UserBusinessService;
import cn.iald.platform.oauth.util.OauthUtil;
import cn.iald.platform.util.AesUtil;
import cn.iald.platform.util.MessageUtil;
import cn.iald.platform.util.ShaUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 经营主体用户表Service实现
 *
 * @author wangyc
 * @date 2021/07/12 17:47:50
 */
@Service
public class UserBusinessServiceImpl implements UserBusinessService {

    @Resource
    private HttpServletRequest request;

    @Resource
    private UserBusinessDao userBusinessDao;

    @Override
    public UserBusinessVO getUserBusiness(Long userId) {
        if (userId == null) {
            throw new BusinessException(MessageUtil.getMessage("user.userId.null", request));
        }
        UserBusinessDO userBusinessDO = this.userBusinessDao.selectById(userId);
        if (userBusinessDO != null) {
            UserBusinessVO userBusinessVO = new UserBusinessVO();
            BeanUtil.copyProperties(userBusinessDO, userBusinessVO);
            return userBusinessVO;
        }
        throw new BusinessException(SysCodeEnum.NULL.getCode(), MessageUtil.getMessage("user.none", request));
    }

    @Override
    public BusinessUserVO checkBusinessUser(BusinessUserDTO businessUserDTO) throws Exception {
        QueryWrapper<UserBusinessDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserBusinessDO::getUsername, businessUserDTO.getUsername())
                .eq(UserBusinessDO::getDelFlag, FlagConstant.FLAG_NO);
        List<UserBusinessDO> userBusinessDOList = this.userBusinessDao.selectList(queryWrapper);
        if (CollUtil.isEmpty(userBusinessDOList)) {
            throw new BusinessException(SysCodeEnum.NULL.getCode()
                    , MessageUtil.getMessage("user.bind.error", request));
        }
        UserBusinessDO userBusinessDO = userBusinessDOList.get(0);
        if (!OauthUtil.matchesPwd(businessUserDTO.getPassword(), userBusinessDO.getPassword())) {
            throw new BusinessException(SysCodeEnum.PWD.getCode()
                    , MessageUtil.getMessage("user.pwd.error", request));
        }
        if (!StatusConstant.STATUS_VALID.equals(userBusinessDO.getStatus())) {
            throw new BusinessException(SysCodeEnum.DISABLE.getCode()
                    , MessageUtil.getMessage("user.bind.disable", request));
        }
        String authorization = ShaUtil.encrypt(StrUtil.format("{}{}{}", businessUserDTO.getUsername()
                , LoginConstant.AUTH_FLAG, userBusinessDO.getPassword()));
        String key = AesUtil.encrypt(StrUtil.format("{}{}{}", userBusinessDO.getUserId()
                , LoginConstant.AUTH_FLAG, authorization));
        return new BusinessUserVO(FlagConstant.FLAG_YES, key);
    }
}
