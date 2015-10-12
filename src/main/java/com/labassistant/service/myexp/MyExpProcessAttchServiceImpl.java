package com.labassistant.service.myexp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.labassistant.beans.MyExpProcessAttchEntity;
import com.labassistant.beans.MyExpReagentEntity;
import com.labassistant.dao.service.BaseAbstractService;

/**
 * 我的实验步骤附件服务
 * @author zql
 * @date 2015/09/23
 */
@Service
public class MyExpProcessAttchServiceImpl extends
		BaseAbstractService<MyExpProcessAttchEntity> implements
		MyExpProcessAttchService {

	@Override
	public List<MyExpProcessAttchEntity> getMyExpProcessAttches(String myExpID){
		String hql = "from MyExpProcessAttchEntity where myExpID = ?";
		return findListByHql(hql, myExpID);
	}
}
