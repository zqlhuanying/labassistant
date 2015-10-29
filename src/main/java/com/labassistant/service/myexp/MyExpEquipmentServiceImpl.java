package com.labassistant.service.myexp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labassistant.beans.MyExpEquipmentEntity;
import com.labassistant.dao.service.BaseAbstractService;
import com.labassistant.service.common.EquipmentService;

/**
 * 我的实验设备服务
 * @author zql
 * @date 2015/10/09
 */
@Service
public class MyExpEquipmentServiceImpl extends
		BaseAbstractService<MyExpEquipmentEntity> implements
		MyExpEquipmentService {

	@Autowired
	private EquipmentService equipmentService;
	
	@Override
	public List<MyExpEquipmentEntity> getMyExpEquipments(String myExpID){
		String hql = "from MyExpEquipmentEntity where myExpID = ?";
		return findListByHql(hql, myExpID);
	}
	
	@Override
	public List<String> getAllEquipmentsName(String myExpID){
		List<String> result = new ArrayList<String>();
		List<MyExpEquipmentEntity> equipments = getMyExpEquipments(myExpID);
		if(equipments != null){
			for(MyExpEquipmentEntity equipment : equipments){
				result.add(equipmentService.get(equipment.getEquipmentID()).getEquipmentName());
			}
		}
		return result;
	}
}
