package com.labassistant.service.common;

import java.util.List;

import com.labassistant.beans.EquipmentMapEntity;
import com.labassistant.dao.service.IBaseAbstractService;

/**
 * 
 * @author zql
 * @date 2015/10/09
 */
public interface EquipmentMapService extends
		IBaseAbstractService<EquipmentMapEntity> {

	public List<EquipmentMapEntity> getListByEquipmentID(String equipmentID);
}
