package com.labassistant.service;

import java.util.List;

import com.labassistant.beans.MapEntity;
import com.labassistant.dao.service.IBaseAbstractService;

/**
 * 
 * @author zql
 * @date 2015/09/14
 */
public interface MapService extends IBaseAbstractService<MapEntity>{

	public List<Object> getAround(MapEntity theUser);
	
	public void setReagent(String userID, String reagent);
}
