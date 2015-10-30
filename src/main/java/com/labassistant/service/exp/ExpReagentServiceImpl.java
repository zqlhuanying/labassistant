package com.labassistant.service.exp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labassistant.beans.ExpReagentEntity;
import com.labassistant.beans.ReagentMapEntity;
import com.labassistant.dao.service.BaseAbstractService;
import com.labassistant.service.common.ReagentMapService;
import com.labassistant.service.common.SupplierService;


/**
 * 实验试剂服务
 * @author zql
 * @date 2015/09/14
 */
@Service
public class ExpReagentServiceImpl extends BaseAbstractService<ExpReagentEntity> implements
		ExpReagentService {
	
	@Autowired
	private ReagentMapService reagentMapService;
	
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
	public List<Object> getExpReagentAndAmount(String expInstructionID){
		List<Object> object = new ArrayList<Object>();
		List<ExpReagentEntity> lists = getExpReagentLists(expInstructionID);
		if(lists != null){
			for(ExpReagentEntity list : lists){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("reagentName", list.getReagentName());
				map.put("useAmount", list.getUseAmount());
				object.add(map);
			}
		}
		return object;
	}
	
	/**
	 * 获取说明书下所有试剂和试剂对应的提供商
	 * 将建议的供应商放在第一个位置
	 */
	@Override
	public List<Object> getExpReagentAndSupplier(String expInstructionID){
		List<Object> object = new ArrayList<Object>();
		List<ExpReagentEntity> expReagentLists = getExpReagentLists(expInstructionID);
		if(expReagentLists != null){
			for(ExpReagentEntity expReagent : expReagentLists){
				Map<String, Object> map = new HashMap<String, Object>();
				List<Object> list = new ArrayList<Object>();
				String suggestionSupplierID = getSuggestionSupplier(expReagent);
				List<ReagentMapEntity> mapLists = reagentMapService.getListByReagentID(expReagent.getReagentID());
				if(mapLists != null){
					for(ReagentMapEntity mapList : mapLists){
						Map<String, Object> innerMap = new HashMap<String, Object>();
						innerMap.put("supplierID", mapList.getSupplierID());
						innerMap.put("supplierName", supplierService.get(mapList.getSupplierID()).getSupplierName());
						if(mapList.getSupplierID().equals(suggestionSupplierID)){
							list.add(0,innerMap);
						} else {
							list.add(innerMap);
						}
					}
				}
				map.put("expReagentID", expReagent.getExpReagentID());
				map.put("reagentID", expReagent.getReagentID());
				map.put("reagentName", expReagent.getReagentName());
				map.put("suppliers", list);
				object.add(map);
			}
		}
		return object;
	}
	
	/**
	 * 获取建议或默认的供应商ID
	 * @return
	 */
	private String getSuggestionSupplier(ExpReagentEntity expReagent){
		return expReagent.getSupplierID();
	}
}
