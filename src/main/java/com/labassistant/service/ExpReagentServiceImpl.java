package com.labassistant.service;

import java.util.List;

import com.labassistant.beans.ExpReagentEntity;
import com.labassistant.dao.service.BaseAbstractService;

/**
 * 实验试剂服务
 * @author zql
 * @date 2015/09/14
 */
public class ExpReagentServiceImpl extends BaseAbstractService<ExpReagentEntity> implements
		ExpReagentService {
	
	@Override
	public List<ExpReagentEntity> getExpReagentLists(String expInstructionID){
		String hql = "from ExpReagentEntity where expInstructionID = ?";
		List<ExpReagentEntity> lists = findListByHql(hql, expInstructionID);		// 此方法 若查询不到相应的记录 会返回NULL
		return lists;
	}

}
