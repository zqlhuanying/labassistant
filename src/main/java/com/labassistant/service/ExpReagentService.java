package com.labassistant.service;

import java.util.List;
import java.util.Map;

import com.labassistant.beans.ExpReagentEntity;
import com.labassistant.dao.service.IBaseAbstractService;

/**
 * 
 * @author zql
 * @date 2015/09/14
 */
public interface ExpReagentService extends IBaseAbstractService<ExpReagentEntity>{

	public List<ExpReagentEntity> getExpReagentLists(String expInstructionID);
	
	public Map<String, Integer> getExpReagentAndAmount(String expInstructionID);
	
	public Map<String, String> getExpReagentAndSupplierName(String expInstructionID);
}
