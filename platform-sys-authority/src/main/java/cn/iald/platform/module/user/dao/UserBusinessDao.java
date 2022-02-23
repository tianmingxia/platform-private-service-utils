package cn.iald.platform.module.user.dao;

import cn.iald.platform.module.user.pojo.entity.UserBusinessDO;
import cn.iald.platform.mybatis.MybatisRedisCache;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * 经营主体用户表Dao
 *
 * @author wangyc
 * @date 2021/07/12 17:47:50
 */
@CacheNamespace(implementation = MybatisRedisCache.class, eviction = MybatisRedisCache.class)
public interface UserBusinessDao extends BaseMapper<UserBusinessDO> {

}
