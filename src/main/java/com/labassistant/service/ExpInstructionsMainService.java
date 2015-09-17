package com.labassistant.service;

import java.util.List;
import java.util.Map;

import com.labassistant.beans.ExpInstructionsMainEntity;
import com.labassistant.dao.service.IBaseAbstractService;

/**
 * 
 * @author zql
 * @date 2015/09/16
 */
public interface ExpInstructionsMainService extends IBaseAbstractService<ExpInstructionsMainEntity> {

	public List<Map<String, Object>> getHotInstructions();
	
	public boolean isDownload(String expInstructionID);
	
	public int getReturnLimit();

	public void setReturnLimit(int returnLimit);
}
