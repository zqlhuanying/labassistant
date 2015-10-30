package com.labassistant.service.exp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labassistant.beans.EquipmentMapEntity;
import com.labassistant.beans.ExpEquipmentEntity;
import com.labassistant.dao.service.BaseAbstractService;
import com.labassistant.service.common.EquipmentMapService;
import com.labassistant.service.common.SupplierService;

/**
 * 实验设备服务
 * @author zql
 * @date 2015/09/17
 */
@Service
public class ExpEquipmentServiceImpl extends
		BaseAbstractService<ExpEquipmentEntity> implements ExpEquipmentService {

	@Autowired
	private EquipmentMapService equipmentMapService;
	@Autowired
	private SupplierService supplierService;
	
	/**
	 * 根据实验说明书号，获取全部设备
	 * @param expInstructionID 实验说明书号
	 * @return
	 */
	@Override
	public List<ExpEquipmentEntity> getExpEquipmentLists(String expInstructionID){
		String hql = "from ExpEquipmentEntity where expInstructionID = ?";
		List<ExpEquipmentEntity> lists = findListByHql(hql, expInstructionID);
		return lists;
	}
	
	/**
	 * 获取说明书下所有设备和设备对应的提供商
	 * 将建议的供应商放在第一个位置
	 */
	@Override
	public List<Object> getExpEquipmentAndSupplier(String expInstructionID){
		List<Object> object = new ArrayList<Object>();
		List<ExpEquipmentEntity> expEquipmentLists = getExpEquipmentLists(expInstructionID);
		if(expEquipmentLists != null){
			for(ExpEquipmentEntity expEquipment : expEquipmentLists){
				Map<String, Object> map = new HashMap<String, Object>();
				List<Object> list = new ArrayList<Object>();
				String suggestionSupplierID = getSuggestionSupplier(expEquipment);
				List<EquipmentMapEntity> mapLists = equipmentMapService.getListByEquipmentID(expEquipment.getEquipmentID());
				if(mapLists != null){
					for(EquipmentMapEntity mapList : mapLists){
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
				map.put("expEquipmentID", expEquipment.getExpEquipmentID());
				map.put("equipmentID", expEquipment.getEquipmentID());
				map.put("equipmentName", expEquipment.getEquipmentName());
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
	private String getSuggestionSupplier(ExpEquipmentEntity expEquipment){
		return expEquipment.getSupplierID();
	}
}
