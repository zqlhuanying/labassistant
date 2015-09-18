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
import com.labassistant.service.ReagentMapService;
import com.labassistant.service.SupplierService;

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
		Map<String, Object> map = new HashMap<String, Object>();
		List<ExpReagentEntity> lists = getExpReagentLists(expInstructionID);
		if(lists != null){
			for(ExpReagentEntity list : lists){
				map.put("reagentName", list.getReagentName());
				map.put("useAmount", list.getUseAmount());
				object.add(map);
			}
		}
		return object;
	}
	
	/**
	 * 获取说明书下所有试剂和试剂对应的提供商
	 */
	@Override
	public List<Object> getExpReagentAndSupplierName(String expInstructionID){
		List<Object> object = new ArrayList<Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		List<ExpReagentEntity> expReagentLists = getExpReagentLists(expInstructionID);
		if(expReagentLists != null){
			for(ExpReagentEntity expReagent : expReagentLists){
				List<String> list = new ArrayList<String>();
				List<ReagentMapEntity> mapLists = reagentMapService.getListByReagentID(expReagent.getReagentID());
				if(mapLists != null){
					for(ReagentMapEntity mapList : mapLists){
						list.add(supplierService.get(mapList.getSupplierID()).getSupplierName());
					}
				}
				map.put("reagentName", expReagent.getReagentName());
				map.put("supplierName", list);
				object.add(map);
			}
		}
		return object;
	}
}