package com.labassistant.service.common;

import java.util.List;

import org.springframework.stereotype.Service;

import com.labassistant.beans.EquipmentMapEntity;
import com.labassistant.dao.service.BaseAbstractService;

/**
 * 设备厂商对应
 * @author zql
 * @date 2015/10/09
 */
@Service
public class EquipmentMapServiceImpl extends
		BaseAbstractService<EquipmentMapEntity> implements EquipmentMapService {

	/**
	 * 获取设备所对应的厂商
	 * @param equipmentID
	 * @return
	 */
	@Override
	public List<EquipmentMapEntity> getListByEquipmentID(String equipmentID){
		String hql = "from EquipmentMapEntity where equipmentID = ?";
		List<EquipmentMapEntity> lists = findListByHql(hql, equipmentID);
		return lists;
	}
}
