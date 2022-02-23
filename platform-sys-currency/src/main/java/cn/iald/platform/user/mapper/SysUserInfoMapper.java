package cn.iald.platform.user.mapper;

import cn.iald.platform.user.entity.SysUserInfoEntity;
import cn.iald.platform.user.vo.SysUserInfoVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 用户信息表Mapper
 *
 * @author wangyc
 * @version 2020年12月14日 10:16:58 初始创建
 */
@Mapper
public interface SysUserInfoMapper {

	/**
     * 新增用户信息表数据
     *
     * @param sysUserInfoEntity
     * @return
     */
    int save(SysUserInfoEntity sysUserInfoEntity);

    /**
     * 根据id查询用户信息表数据
     *
     * @param id
     * @return
     */
    SysUserInfoEntity getObjectById(Object id);

    /**
     * 更新用户信息表数据
     *
     * @param sysUserInfoEntity
     * @return
     */
    int update(SysUserInfoEntity sysUserInfoEntity);

    /**
     * 根据id查询用户名称
     *
     * @param id
     * @return
     */
    String getUserNameById(Object id);

    /**
     * 根据条件查询用户信息表实体列表
     *
     * @param paramMap
     * @return
     */
    List<SysUserInfoEntity> listForParam(Map<String, Object> paramMap);

    /**
     * 根据条件查询用户信息表Vo列表
     *
     * @param paramMap
     * @return
     */
    List<SysUserInfoVo> listVoForParam(Map<String, Object> paramMap);
}