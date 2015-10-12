package com.labassistant.service.myexp;

import java.util.List;

import com.labassistant.beans.MyExpReagentEntity;
import com.labassistant.dao.service.IBaseAbstractService;

/**
 * 
 * @author zql
 * @date 2015/09/21
 */
public interface MyExpReagentService extends
		IBaseAbstractService<MyExpReagentEntity> {

	public List<MyExpReagentEntity> getMyExpReagents(String myExpID);
}
