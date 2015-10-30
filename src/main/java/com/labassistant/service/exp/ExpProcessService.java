package com.labassistant.service.exp;

import java.util.List;

import com.labassistant.beans.ExpStepEntity;
import com.labassistant.dao.service.IBaseAbstractService;

/**
 * 
 * @author zql
 * @date 2015/09/14
 */
public interface ExpProcessService extends IBaseAbstractService<ExpStepEntity>{

	public List<ExpStepEntity> getProcessLists(String expInstructionID);
	
	public ExpStepEntity getProcess(String expInstructionID, int stepNum);
}
