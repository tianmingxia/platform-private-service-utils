package cn.iald.platform.base;

import java.util.List;

/**
 * 基础Mapper
 *
 * @author wangyc
 */
public interface BaseMapper<T> {
	/**
	 * 新增
	 *
	 * @param t
	 * @return
	 */
	int save(T t);

	/**
	 * 根据id查询详情
	 *
	 * @param id
	 * @return
	 */
	T getObjectById(Object id);

	/**
	 * 更新
	 *
	 * @param t
	 * @return
	 */
	int update(T t);

	/**
	 * 根据条件查询列表
	 *
	 * @param t
	 * @return
	 */
	List<T> listForParam(T t);
}
