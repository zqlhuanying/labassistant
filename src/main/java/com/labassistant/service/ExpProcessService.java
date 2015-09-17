package com.labassistant.service;

import java.util.List;

import com.labassistant.beans.ExpProcessEntity;
import com.labassistant.dao.service.IBaseAbstractService;

/**
 * 
 * @author zql
 * @date 2015/09/14
 */
public interface ExpProcessService extends IBaseAbstractService<ExpProcessEntity>{

	public List<ExpProcessEntity> getProcessLists(String expInstructionID);
	
	public ExpProcessEntity getProcess(String expInstructionID, int stepNum);
}
