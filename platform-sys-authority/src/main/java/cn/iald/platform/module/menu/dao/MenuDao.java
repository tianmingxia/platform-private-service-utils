package cn.iald.platform.module.menu.dao;

import cn.iald.platform.module.menu.pojo.entity.MenuDO;
import cn.iald.platform.mybatis.MybatisRedisCache;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * 菜单表Dao
 *
 * @author wangyc
 * @date 2021/06/11 18:07:45
 */
@CacheNamespace(implementation = MybatisRedisCache.class, eviction = MybatisRedisCache.class)
public interface MenuDao extends BaseMapper<MenuDO> {

}
