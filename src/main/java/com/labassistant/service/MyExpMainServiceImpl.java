package com.labassistant.service;

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
	public List<MyExpMainEntity> getUnComplete(String userID){
		return getMyExp(userID, 0);
	}
	
	private List<MyExpMainEntity> getMyExp(String userID, int expState){
		String hql = "from MyExpMainEntity where userID = ? and expState = ?";
		List<MyExpMainEntity> lists = findListByHql(hql, userID, expState);
		return lists;
	}
}
