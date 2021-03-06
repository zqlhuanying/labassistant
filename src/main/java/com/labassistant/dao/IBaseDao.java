package com.labassistant.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.labassistant.beans.Pagination;

/**
 * 数据库接口
 * @author zql
 * @date 2015/09/07
 */
public interface IBaseDao {
	
	/**
	 * 通过sql语句来保存或更新
	 * @param entity
	 */
	public Serializable saveOrUpdateBySql (String sql, Object... parameters);
	
	/**
	 * 保存泛型指向的实体
	 * @param entity
	 */
	public <X> Serializable save (X entity);

	/**
	 * 批量保存泛型指向的实体
	 * @param entitys
	 * @param <X>
	 * @return
	 */
	public <X> void saveList (List<X> entitys);

	/**
	 * 新增或保存
	 * @param entity
	 */
	public <X> void saveOrUpdate(X entity);
	
	/**
	 * 删除记录
	 * @param 
	 */
	public <X> void delete (Class<X> entityClass, Serializable pk);
	
	/**
	 * 删除记录
	 * @param 
	 */
	public <X> void delete (X entity);

	/**
	 * 
	 * @param entities
	 */
	public <X> void deleteAll(Collection<X> entities);
	
	/**
	 * 
	 * 清空表内的数据
	 */
	public <X> void deleteAll(Class<?> entityClass);
	
	/**
	 * 更新单条记录
	 * @param entity
	 */
	public <X> void update (X entity);
	
	/**
	 *
	 * @param entityClass
	 * @param pk
	 * @param <X>
	 * @return
	 */
	public <X> X load(Class<X> entityClass, Serializable pk);
	
	/**
	 *
	 * @param entityClass
	 * @param pk
	 * @param <X>
	 * @return
	 */
	public <X> X get(Class<X> entityClass, Serializable pk);
	
	/**
	 * 根据hql查询单条记录
	 * @param hql
	 * @param parameters
	 * @return 返回 X可以是实体或者Map
	 */
	public <X> X findOneByHql(String hql, Object... parameters);
	
	/**
	 * 根据hql查询单条记录
	 * @param sql
	 * @param parameters
	 * @return 返回map
	 */
	public Map<String, Object> findOneBySql(String sql, Object... parameters);

	/**
	 * 查询所有记录，根据泛型查询该对象纪录列表
	 * @param entityClass
	 * @param <X>
	 * @return
	 */
	public <X> List<X> findList(Class<X> entityClass);
	
	/**
	 * 查询所有记录
	 * @param hql
	 * @param parameters
	 * @return 返回列表数据 X 可以是实体对象或者Map类型
	 */
	public <X> List<X> findListByHql (String hql, Object... parameters);

	/**
	 * 查询所有记录
	 * @param sql
	 * @param parameters
	 * @return 返回列表数据 X 可以是实体对象或者Map类型
	 */
	public <X> List<X> findListBySql (String sql, Object... parameters);
	
	/**
	 * 根据sql查询多条记录
	 * @param sql
	 * @param parameters
	 * @return
	 */
	public List<Map<String, Object>> findListMapBySql(String sql, Object... parameters);
	
	/**
	 * 将查询单条记录
	 * @param hql
	 * @param parameters
	 * @return	
	 */
	public <X> X findUniqueByHql(String hql, Object... parameters);
	
	/**
	 * 将查询单条记录
	 * @param sql
	 * @param parameters
	 * @return	
	 */
	public <X> X findUniqueBySql(String sql, Object... parameters);
	
	/**
	 * 获取总记录数
	 * @param ql hql / sql
	 * @param parameters
	 * @return
	 */
	public int getCount(String ql, boolean isHql, Object... parameters);

	/**
	 * * 分页查询
	 * @param hql
	 * @param parameters
	 * @param <X>
	 * @return 返回list分页数据 X 可以是实体对象或者Map类型
	 */
	public <X> Pagination<X> pageByHql (String hql, Object... parameters);
}
