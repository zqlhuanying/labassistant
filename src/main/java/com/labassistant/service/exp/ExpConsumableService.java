package com.labassistant.service.exp;

import java.util.List;

import com.labassistant.beans.ExpConsumableEntity;
import com.labassistant.dao.service.IBaseAbstractService;

/**
 * 
 * @author zql
 * @date 2015/09/17
 */
public interface ExpConsumableService extends IBaseAbstractService<ExpConsumableEntity> {

	public List<ExpConsumableEntity> getExpConsumableLists(String expInstructionID);
	
	public List<Object> getExpConsumableAndSupplier(String expInstructionID);
}
