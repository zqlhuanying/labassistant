package com.labassistant.service.myexp;

import java.util.List;

import com.labassistant.beans.MyExpEquipmentEntity;
import com.labassistant.dao.service.IBaseAbstractService;

/**
 * 
 * @author zql
 * @date 2015/10/09
 */
public interface MyExpEquipmentService extends
		IBaseAbstractService<MyExpEquipmentEntity> {

	public List<MyExpEquipmentEntity> getMyExpEquipments(String myExpID);
	
	public List<String> getAllEquipmentsName(String myExpID);
}
