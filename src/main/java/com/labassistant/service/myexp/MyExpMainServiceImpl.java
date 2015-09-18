package com.labassistant.service.myexp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.labassistant.beans.MyExpMainEntity;
import com.labassistant.dao.service.BaseAbstractService;

/**
 * 我的实验主表服务类
 * @author zql
 * @date 2015/09/16
 */
@Service
public class MyExpMainServiceImpl extends BaseAbstractService<MyExpMainEntity> implements
		MyExpMainService {

	/**
	 * 根据实验ID获取一条记录，目的是获得说明书ID
	 * @param myExpID
	 * @return
	 */
	@Override
	public MyExpMainEntity getByExpID(String myExpID){
		String hql = "from MyExpMainEntity where myExpID = ?";
		return (MyExpMainEntity)findOneByHql(hql, myExpID);
	}
	
	/**
	 * 根据实验ID获取相关记录
	 */
	@Override
	public MyExpMainEntity get(String myExpID){
		return get(myExpID);
	}
	
	/**
	 * 获取已完成的实验
	 * @param userID
	 * @return
	 */
	@Override
	public List<MyExpMainEntity> getComplete(String userID){
		return getMyExp(userID, 2);
	}

	/**
	 * 获取正在进行的实验
	 * @param userID
	 * @return
	 */
	@Override
	public List<MyExpMainEntity> getDoing(String userID){
		return getMyExp(userID, 0);
	}
	
	private List<MyExpMainEntity> getMyExp(String userID, int expState){
		String hql = "from MyExpMainEntity where userID = ? and expState = ?";
		List<MyExpMainEntity> lists = findListByHql(hql, userID, expState);
		return lists;
	}
}
