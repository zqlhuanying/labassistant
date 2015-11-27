package com.labassistant.dao.service;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.labassistant.beans.Pagination;
import com.labassistant.dao.IBaseDao;
import com.labassistant.exception.MyRuntimeException;

/**
 * sercice 通用服务实现类
 * @author zql
 * @date 2015/09/07
 */
public class BaseAbstractService<T> implements IBaseAbstractService<T> {

	@Autowired
	private IBaseDao baseDao;

	private Class<T> getEntityClass() {
		Type parentType = getClass().getGenericSuperclass();
		if (parentType instanceof ParameterizedType) {
			return (Class<T>) ((ParameterizedType) parentType)
					.getActualTypeArguments()[0];
		}
		throw new MyRuntimeException("未指定实体类型！");
	}

	@Override
	public Serializable saveOrUpdateBySql (String sql, Object... parameters){
		return baseDao.saveOrUpdateBySql(sql, parameters);
	}
	
	@Override
	public <X> Serializable save(X entity) {
		return baseDao.save(entity);
	}

	/**
	 * 批量保存泛型指向的实体（只适合插入少量数据）
	 * @param entitys
	 * @return
	 */
	@Override
	public <X> void saveList(List<X> entitys) {
		baseDao.saveList(entitys);
	}

	@Override
	public <X> void saveOrUpdate(X entity) {
		baseDao.saveOrUpdate(entity);
	}

	@Override
	public <X> void delete(Class<X> entityClass, Serializable pk) {
		baseDao.delete(entityClass, pk);
	}

	@Override
	public void delete(Serializable pk) {
		baseDao.delete(getEntityClass(), pk);
	}

	@Override
	public <X> void deleteEntity(X entity) {
		baseDao.delete(entity);
	}

	@Override
	public <X> void deleteAll(Collection<X> entities) {
		baseDao.deleteAll(entities);
	}

	@Override
	public <X> void deleteAll(Class<?> entityClass){
		baseDao.deleteAll(entityClass);
	}
	
	@Override
	public <X> void update(X entity) {
		baseDao.update(entity);
	}
	
	@Override
	public T load(Serializable id) {
		return baseDao.load(getEntityClass(), id);
	}

	@Override
	public <X> X load(Class<X> entityClass, Serializable id) {
		return (X) baseDao.load(entityClass, id);
	}

	@Override
	public T get(Serializable id) {
		return baseDao.get(getEntityClass(), id);
	}

	@Override
	public <X> X get(Class<X> entityClass, Serializable id) {
		return (X) baseDao.get(entityClass, id);
	}
	
	@Override
	public <X> X findOneByHql(String hql, Object... parameters) {
		return baseDao.findOneByHql(hql, parameters);
	}

	@Override
	public Map findOneBySql(String sql, Object... parameters) {
		return baseDao.findOneBySql(sql, parameters);
	}

	@Override
	public <X> List<X> findList(Class<X> entityClass) {
		return baseDao.findList(entityClass);
	}
	
	@Override
	public List<T> findList() {
		return (List<T>) baseDao.findList(getEntityClass());
	}
	
	@Override
	public <X> List<X> findListByHql(String hql, Object... parameters) {
		return baseDao.findListByHql(hql, parameters);
	}

	@Override
	public <X> List<X> findListBySql(String sql, Object... parameters) {
		return baseDao.findListBySql(sql, parameters);
	}
	
	@Override
	public List<Map<String, Object>> findListMapBySql(String sql, Object... parameters) {
		return baseDao.findListMapBySql(sql, parameters);
	}

	@Override
	public <X> X findUniqueByHql(String hql, Object... parameters) {
		return baseDao.findUniqueByHql(hql, parameters);
	}

	@Override
	public <X> X findUniqueBySql(String sql, Object... parameters) {
		return baseDao.findUniqueBySql(sql, parameters);
	}

	@Override
	public int getCount(String ql, boolean isHql, Object... parameters) {
		return baseDao.getCount(ql, isHql, parameters);
	}
	
	@Override
	public <X> Pagination<X> pageByHql(String hql, Object... parameters) {
		return baseDao.pageByHql(hql, parameters);
	}
}
