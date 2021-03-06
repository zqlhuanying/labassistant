package com.labassistant.dao.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.labassistant.beans.Pagination;

/**
 * 通用DAO服务
 * @author zql
 * @date 2015/09/07
 * @param <T>
 */
public interface IBaseAbstractService<T> {

	/**
	 * 通过sql语句来保存或更新
	 * @param entity
	 */
	public Serializable saveOrUpdateBySql (String sql, Object... parameters);
	
	/**
	 * 保存泛型指向的实体
	 * @param entity
	 */
	public <X> Serializable save (X entity) ;

	/**
	 * 批量保存泛型指向的实体（只适合插入少量数据）
	 * @param entitys
	 * @param <X>
	 * @return
	 */
	public <X> void saveList (List<X> entitys);

	/**
	 * 新增或保存实体
	 * @param entity
	 */
	public <X> void saveOrUpdate(X entity) ;
	
	/**
	 * 删除记录
	 * @param 
	 */
	public void delete (Serializable pk) ;
	
	/**
	 * 删除记录
	 * @param 
	 */
	public <X> void delete (Class<X> entityClass, Serializable pk) ;
	
	/**
	 * 删除记录
	 * @param 
	 */
	public <X> void deleteEntity (X entity) ;
	
	/**
	 * 
	 * @param entities
	 * @throws DataAccessException
	 */
	public <X> void deleteAll(Collection<X> entities) ;
	
	/**
	 * 
	 * 清空表内的数据
	 */
	public <X> void deleteAll(Class<?> entityClass);
	
	/**
	 * 更新单条记录
	 * @param entity
	 */
	public <X> void update (X entity) ;

	
	/**
	 * 
	 * @param id
	 * @return 泛型指向之外的实
	 * @throws HibernateException
	 * @throws SQLException
	 */
	public T load(Serializable id);
	
	/**
	 * 
	 * @param entityClass
	 * @param id
	 * @return 泛型指向之外的实
	 * @throws HibernateException
	 * @throws SQLException
	 */
	public <X> X load(Class<X> entityClass, Serializable id);
	
	/**
	 * 
	 * @param id
	 * @return 泛型指向之外的实
	 * @throws HibernateException
	 * @throws SQLException
	 */
	public T get(Serializable id);
	
	/**
	 * 
	 * @param entityClass
	 * @param id
	 * @return 泛型指向之外的实
	 * @throws HibernateException
	 * @throws SQLException
	 */
	public <X> X get(Class<X> entityClass, Serializable id);
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
	public Map findOneBySql(String sql, Object... parameters);
	
	/**
	 * 查询列表记录，根据泛型查询该对象纪录列表
	 * @param entityClass
	 * @return
	 */
	public <X> List<X> findList(Class<X> entityClass);
	
	/**
	 * 查询列表记录，根据泛型查询该对象纪录列表
	 */
	public List<T> findList();
	
	/**
	 * 查询列表记录
	 * @param hql
	 * @param parameters
	 * @return 返回列表数据 X 可以是实体对象或者Map类型
	 */
	public <X> List<X> findListByHql (String hql, Object... parameters);
	
	/**
	 * 查询列表记录
	 * @param sql
	 * @param parameters
	 * @return 返回列表数据 X 可以是实体对象或者Map类型
	 */
	public <X> List<X> findListBySql (String sql, Object... parameters);
	
	/**
	 * 根据sql查询多条记录
	 * @param parameters
	 * @return 
	 * @throws HibernateException
	 * @throws SQLException
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
	 * 分页查询
	 * @param hql
	 * @param parameters
	 * @return	返回list分页数据 X 可以是实体对象或者Map类型
	 * @throws HibernateException
	 * @throws SQLException
	 */
	public <X> Pagination<X> pageByHql (String hql, Object... parameters);
}
