package com.labassistant.service.myexp;

import java.util.List;

import com.labassistant.beans.MyExpInstructionEntity;
import com.labassistant.dao.service.IBaseAbstractService;

/**
 * 
 * @author zql
 * @date 2015/09/18
 */
public interface MyExpInstructionService extends
		IBaseAbstractService<MyExpInstructionEntity> {

	public boolean isDownload(String userID, String expInstructionID);
	
	public List<MyExpInstructionEntity> getMyExpInstructions(String userID);
}
