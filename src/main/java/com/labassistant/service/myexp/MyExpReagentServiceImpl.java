package com.labassistant.service.myexp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.labassistant.beans.MyExpReagentEntity;
import com.labassistant.dao.service.BaseAbstractService;

/**
 * 我的实验试剂服务
 * @author zql
 * @date 2015/09/21
 */
@Service
public class MyExpReagentServiceImpl extends
		BaseAbstractService<MyExpReagentEntity> implements MyExpReagentService {

	@Override
	public List<MyExpReagentEntity> getMyExpReagents(String myExpID){
		String hql = "from MyExpReagentEntity where myExpID = ?";
		return findListByHql(hql, myExpID);
	}

}
