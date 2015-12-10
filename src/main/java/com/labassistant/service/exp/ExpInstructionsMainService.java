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

    public boolean isExist(String expInstructionID);

	public boolean isPublic(String expInstructionID);
	
	public boolean isOwn(String expInstructionID, String userID);
	
	public boolean isAllowDownload(String userID, String expInstructionID);
	
	public int getReturnLimit();

	public void setReturnLimit(int returnLimit);
	
	public List<ExpInstructionEntity> getInstructionsBySubCategoryID(String userID, String expSubCategoryID);
	
	public List<ExpInstructionEntity> getInstructionsByFilter(String userID, String filterStr);
}
