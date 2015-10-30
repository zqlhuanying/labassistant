package com.labassistant.service.exp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labassistant.beans.ConsumableMapEntity;
import com.labassistant.beans.ExpConsumableEntity;
import com.labassistant.dao.service.BaseAbstractService;
import com.labassistant.service.common.ConsumableMapService;
import com.labassistant.service.common.SupplierService;

/**
 * 实验耗材服务
 * @author zql
 * @date 2015/09/17
 */
@Service
public class ExpConsumableServiceImpl extends
		BaseAbstractService<ExpConsumableEntity> implements
		ExpConsumableService {

	@Autowired
	private ConsumableMapService consumableMapService;
	@Autowired
	private SupplierService supplierService;
	
	/**
	 * 根据实验说明书号，获取全部耗材
	 * @param expInstructionID 实验说明书号
	 * @return
	 */
	@Override
	public List<ExpConsumableEntity> getExpConsumableLists(String expInstructionID){
		String hql = "from ExpConsumableEntity where expInstructionID = ?";
		List<ExpConsumableEntity> lists = findListByHql(hql, expInstructionID);
		return lists;
	}
	
	/**
	 * 获取说明书下所有耗材和耗材对应的提供商
	 * 将建议的供应商放在第一个位置
	 */
	@Override
	public List<Object> getExpConsumableAndSupplier(String expInstructionID){
		List<Object> object = new ArrayList<Object>();
		List<ExpConsumableEntity> expConsumableLists = getExpConsumableLists(expInstructionID);
		if(expConsumableLists != null){
			for(ExpConsumableEntity expConsumable : expConsumableLists){
				Map<String, Object> map = new HashMap<String, Object>();
				List<Object> list = new ArrayList<Object>();
				String suggestionSupplierID = getSuggestionSupplier(expConsumable);
				List<ConsumableMapEntity> mapLists = consumableMapService.getListByConsumableID(expConsumable.getConsumableID());
				if(mapLists != null){
					for(ConsumableMapEntity mapList : mapLists){
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
				map.put("expConsumableID", expConsumable.getExpConsumableID());
				map.put("consumableID", expConsumable.getConsumableID());
				map.put("consumableName", expConsumable.getConsumableName());
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
	private String getSuggestionSupplier(ExpConsumableEntity expConsumable){
		return expConsumable.getSupplierID();
	}
}
