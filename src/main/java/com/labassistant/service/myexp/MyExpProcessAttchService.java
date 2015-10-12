package com.labassistant.service.myexp;

import java.util.List;

import com.labassistant.beans.MyExpProcessAttchEntity;
import com.labassistant.dao.service.IBaseAbstractService;

/**
 * 
 * @author zql
 * @date 2015/09/23
 */
public interface MyExpProcessAttchService extends
		IBaseAbstractService<MyExpProcessAttchEntity> {

	public List<MyExpProcessAttchEntity> getMyExpProcessAttches(String myExpID);
}
