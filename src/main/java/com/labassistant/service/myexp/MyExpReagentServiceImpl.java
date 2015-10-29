package com.labassistant.service.myexp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labassistant.beans.MyExpReagentEntity;
import com.labassistant.dao.service.BaseAbstractService;
import com.labassistant.service.common.ReagentService;

/**
 * 我的实验试剂服务
 * @author zql
 * @date 2015/09/21
 */
@Service
public class MyExpReagentServiceImpl extends
		BaseAbstractService<MyExpReagentEntity> implements MyExpReagentService {

	@Autowired
	private ReagentService reagentService;
	
	@Override
	public List<MyExpReagentEntity> getMyExpReagents(String myExpID){
		String hql = "from MyExpReagentEntity where myExpID = ?";
		return findListByHql(hql, myExpID);
	}

	@Override 
	public List<String> getAllReagentsName(String myExpID){
		List<String> result = new ArrayList<String>();
		List<MyExpReagentEntity> reagents = getMyExpReagents(myExpID);
		if(reagents != null){
			for(MyExpReagentEntity reagent : reagents){
				result.add(reagentService.get(reagent.getReagentID()).getReagentName());
			}
		}
		return result;
	}
}
