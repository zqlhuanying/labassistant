package com.labassistant.service.myexp;

import java.util.List;

import com.labassistant.beans.MyExpConsumableEntity;
import com.labassistant.dao.service.IBaseAbstractService;

/**
 * 
 * @author zql
 * @date 2015/10/09
 */
public interface MyExpConsumableService extends
		IBaseAbstractService<MyExpConsumableEntity> {

	public List<MyExpConsumableEntity> getMyExpConsumables(String myExpID);
	
	public List<String> getAllConsumablesName(String myExpID);
}
