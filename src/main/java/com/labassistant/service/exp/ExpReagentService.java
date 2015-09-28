package com.labassistant.service.exp;

import java.util.List;

import com.labassistant.beans.ExpReagentEntity;
import com.labassistant.dao.service.IBaseAbstractService;

/**
 * 
 * @author zql
 * @date 2015/09/14
 */
public interface ExpReagentService extends IBaseAbstractService<ExpReagentEntity>{

	public List<ExpReagentEntity> getExpReagentLists(String expInstructionID);
	
	public List<Object> getExpReagentAndAmount(String expInstructionID);
	
	public List<Object> getExpReagentAndSupplierName(String expInstructionID);
}
