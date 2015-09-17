package com.labassistant.service;

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
	 * 根据实验ID获取实验对应的步骤，只获取一条记录，因只需要知道说明书ID
	 * @param myExpID
	 * @return
	 */
	@Override
	public MyExpProcessEntity getByExpID(String myExpID){
		String hql = "from MyExpProcessEntity where myExpID = ?";
		return (MyExpProcessEntity)findOneByHql(hql, myExpID);
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
