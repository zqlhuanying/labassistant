package com.labassistant.service.myexp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.labassistant.beans.MyExpEntity;
import com.labassistant.dao.service.BaseAbstractService;

/**
 * 我的实验主表服务类
 * @author zql
 * @date 2015/09/16
 */
@Service
public class MyExpMainServiceImpl extends BaseAbstractService<MyExpEntity> implements
		MyExpMainService {

	/**
	 * 根据实验ID获取一条记录，目的是获得说明书ID
	 * @param myExpID
	 * @return
	 */
	@Override
	public MyExpEntity getByExpID(String myExpID){
		String hql = "from MyExpEntity where myExpID = ?";
		return (MyExpEntity)findOneByHql(hql, myExpID);
	}
	
	/**
	 * 获取用户所做的实验
	 */
	@Override
	public List<MyExpEntity> getByUserID(String userID){
		String hql ="from MyExpEntity where userID = ?";
		return findListByHql(hql, userID);
	}
	
	/**
	 * 获取用户已生成的PDF
	 */
	@Override
	public List<MyExpEntity> getPdfs(){
		String hql ="from MyExpEntity where isCreateReport = 1 order by reportName desc";
		return findListByHql(hql);
	}
	
	/**
	 * 获取已完成的实验
	 * @param userID
	 * @return
	 */
	@Override
	public List<MyExpEntity> getComplete(String userID){
		return getMyExp(userID, 2);
	}

	/**
	 * 获取正在进行的实验
	 * @param userID
	 * @return
	 */
	@Override
	public List<MyExpEntity> getDoing(String userID){
		return getMyExp(userID, 0);
	}
	
	private List<MyExpEntity> getMyExp(String userID, int expState){
		String hql = "from MyExpEntity where userID = ? and expState = ?";
		List<MyExpEntity> lists = findListByHql(hql, userID, expState);
		return lists;
	}
}
