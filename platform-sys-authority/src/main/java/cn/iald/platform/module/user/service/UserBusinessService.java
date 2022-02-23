package cn.iald.platform.module.user.service;

import cn.iald.platform.module.internal.pojo.dto.BusinessUserDTO;
import cn.iald.platform.module.internal.pojo.vo.BusinessUserVO;
import cn.iald.platform.module.user.pojo.vo.UserBusinessVO;

/**
 * 经营主体用户表Service
 *
 * @author wangyc
 * @date 2021/07/12 17:47:50
 */
public interface UserBusinessService {

    /**
     * 根据主键获取经营主体用户表数据
     *
     * @param userId
     * @return
     */
    UserBusinessVO getUserBusiness(Long userId);

    /**
     * 检查经营主体用户是否可登录系统
     *
     * @param businessUserDTO
     * @return
     * @throws Exception
     */
    BusinessUserVO checkBusinessUser(BusinessUserDTO businessUserDTO) throws Exception;
}
