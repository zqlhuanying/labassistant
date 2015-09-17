package com.labassistant.service;

import java.util.List;

import com.labassistant.beans.ReagentMapEntity;
import com.labassistant.dao.service.IBaseAbstractService;

/**
 * 
 * @author zql
 * @date 2015/09/17
 */
public interface ReagentMapService extends IBaseAbstractService<ReagentMapEntity> {

	public List<ReagentMapEntity> getListByReagentID(String reagentID);
	
	public boolean isSuggestion(String reagentID, String supplierID);
	
	public List<ReagentMapEntity> getSuggestionSupplier(String reagentID);
}
