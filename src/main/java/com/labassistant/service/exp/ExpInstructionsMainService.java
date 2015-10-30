package com.labassistant.service.exp;

import java.util.List;
import java.util.Map;

import com.labassistant.beans.ExpInstructionEntity;
import com.labassistant.dao.service.IBaseAbstractService;

/**
 * 
 * @author zql
 * @date 2015/09/16
 */
public interface ExpInstructionsMainService extends IBaseAbstractService<ExpInstructionEntity> {

	public List<Map<String, Object>> getHotInstructions();
	
	public Map<String, Object> downloadInstruction(String expInstructionID);
	
	public boolean isPublic(String expInstructionID);
	
	public boolean isAllowDownload(String userID, String expInstructionID);
	
	public int getReturnLimit();

	public void setReturnLimit(int returnLimit);
	
	public List getInstructionsBySubCategoryID(String expSubCategoryID);
	
	public List getInstructionsByFilter(String filterStr);
}
