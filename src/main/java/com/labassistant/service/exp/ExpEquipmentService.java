package com.labassistant.service.exp;

import java.util.List;

import com.labassistant.beans.ExpEquipmentEntity;
import com.labassistant.dao.service.IBaseAbstractService;

/**
 * 
 * @author zql
 * @date 2015/09/17
 */
public interface ExpEquipmentService extends
		IBaseAbstractService<ExpEquipmentEntity> {

    public ExpEquipmentEntity getExpEquipment(String expInstructionID, String equipmentID);

	public List<ExpEquipmentEntity> getExpEquipmentLists(String expInstructionID);
	
	public List<Object> getExpEquipmentAndSupplier(String expInstructionID);
}
