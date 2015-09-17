package com.labassistant.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labassistant.beans.ExpReagentEntity;
import com.labassistant.dao.service.BaseAbstractService;

/**
 * 实验试剂服务
 * @author zql
 * @date 2015/09/14
 */
@Service
public class ExpReagentServiceImpl extends BaseAbstractService<ExpReagentEntity> implements
		ExpReagentService {
	
	@Autowired
	private ReagentService reagentService;
	
	@Autowired
	private SupplierService supplierService;
	
	/**
	 * 获取说明书下所有的试剂
	 */
	@Override
	public List<ExpReagentEntity> getExpReagentLists(String expInstructionID){
		String hql = "from ExpReagentEntity where expInstructionID = ?";
		List<ExpReagentEntity> lists = findListByHql(hql, expInstructionID);		// 此方法 若查询不到相应的记录 会返回NULL
		return lists;
	}

	/**
	 * 获取说明书下所有试剂和试剂对应的用量
	 */
	@Override 
	public Map<String, Integer> getExpReagentAndAmount(String expInstructionID){
		List<ExpReagentEntity> lists = getExpReagentLists(expInstructionID);
		Map<String, Integer> map = new HashMap<String, Integer>();
		if(lists != null){
			for(ExpReagentEntity list : lists){
				map.put(list.getReagentName(), list.getUseAmount());
			}
			return map;
		}
		return null;
	}
	
	/**
	 * 获取说明书下所有试剂和试剂对应的提供商
	 */
	@Override
	public Map<String, String> getExpReagentAndSupplierName(String expInstructionID){
		List<ExpReagentEntity> lists = getExpReagentLists(expInstructionID);
		Map<String, String> map = new HashMap<String, String>();
		if(lists != null){
			for(ExpReagentEntity list : lists){
				map.put(list.getReagentName(), supplierService.get(reagentService.get((list.getReagentID())).getSupplierID()).getSupplierName());
			}
			return map;
		}
		return null;
	}
}
