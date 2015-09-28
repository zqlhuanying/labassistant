package com.labassistant.service.myexp;

import java.util.List;

import com.labassistant.beans.MyExpMainEntity;
import com.labassistant.dao.service.IBaseAbstractService;

/**
 * 
 * @author zql
 * @date 2015/09/16
 */
public interface MyExpMainService extends IBaseAbstractService<MyExpMainEntity> {

	public MyExpMainEntity getByExpID(String myExpID);
	
	public List<MyExpMainEntity> getComplete(String userID);
	
	public List<MyExpMainEntity> getDoing(String userID);
}
