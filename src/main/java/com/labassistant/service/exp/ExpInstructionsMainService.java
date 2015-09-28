package com.labassistant.service.exp;

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
	
	public boolean isExists(String expInstructionID);
	
	public boolean isDownload(String userID, String expInstructionID);
	
	public int getReturnLimit();

	public void setReturnLimit(int returnLimit);
	
	public List getInstructionsBySubCategoryID(String expSubCategoryID);
	
	public List getInstructionsByFilter(String filterStr);
}
