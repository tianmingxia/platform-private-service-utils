package cn.iald.platform.module.role.dao;

import cn.iald.platform.module.role.pojo.entity.RoleMenuDO;
import cn.iald.platform.mybatis.MybatisRedisCache;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * 角色-菜单表Dao
 *
 * @author wangyc
 * @date 2021/06/15 09:54:22
 */
@CacheNamespace(implementation = MybatisRedisCache.class, eviction = MybatisRedisCache.class)
public interface RoleMenuDao extends BaseMapper<RoleMenuDO> {
}
