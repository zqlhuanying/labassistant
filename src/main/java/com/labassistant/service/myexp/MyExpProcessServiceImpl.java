package com.labassistant.service.myexp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.labassistant.beans.MyExpProcessEntity;
import com.labassistant.dao.service.BaseAbstractService;

/**
 * 我的实验步骤服务类
 * @author zql
 * @date 2015/09/16
 */
@Service
public class MyExpProcessServiceImpl extends BaseAbstractService<MyExpProcessEntity> implements
		MyExpProcessService {

	/**
	 * 根据实验ID获取相关记录
	 * @param myExpID
	 * @return
	 */
	@Override
	public List<MyExpProcessEntity> getList(String myExpID){
		String hql = "from MyExpProcessEntity where myExpID = ?";
		List<MyExpProcessEntity> lists = findListByHql(hql, myExpID);
		return lists;
	}
	
	/**
	 * 根据实验ID获取一条记录，目的是获得说明书ID
	 * @param myExpID
	 * @return
	 */
	@Override
	public MyExpProcessEntity getByExpID(String myExpID){
		String hql = "from MyExpProcessEntity where myExpID = ?";
		return (MyExpProcessEntity)findOneByHql(hql, myExpID);
	}

	/**
	 * 根据说明书ID获取一条记录，目的是获得实验ID
	 * @param myExpID
	 * @return
	 */
	@Override
	public MyExpProcessEntity getByInstructionID(String expInstructionID){
		String hql = "from MyExpProcessEntity where expInstructionID = ?";
		return (MyExpProcessEntity)findOneByHql(hql, expInstructionID);
	}
	
	/**
	 * 根据说明书ID，判断是否存在
	 */
	@Override
	public boolean isExists(String expInstructionID){
		String hql = "from MyExpProcessEntity where expInstructionID = ?";
		return getCount(hql, true, expInstructionID) > 0;
	}
}
