package com.labassistant.service.myexp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.labassistant.beans.MyExpProcessAttchEntity;
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

	/**
	 * 
	 * @param myExpID		必须有值
	 * @param expStepID		可以为空或NULL
	 * @return
	 */
	@Override
	public List<MyExpProcessAttchEntity> getMyExpProcessAttches(String myExpID, String expStepID){
		List<Object> params = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("from MyExpProcessAttchEntity where myExpID = ? ");
		params.add(myExpID);
		if(StringUtils.isNotBlank(expStepID)){
			hql.append("and expStepID = ? ");
			params.add(expStepID);
		}
		return findListByHql(hql.toString(), params);
	}
	
	/*public List<MyExpProcessAttchEntity> getMyExpProcessAttches(String myExpID){
		String hql = "from MyExpProcessAttchEntity where myExpID = ?";
		return findListByHql(hql, myExpID);
	}*/
}
