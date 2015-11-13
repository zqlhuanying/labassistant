package com.labassistant.service.myexp;

import java.util.List;

import com.labassistant.beans.MyExpEntity;
import com.labassistant.dao.service.IBaseAbstractService;

/**
 * 
 * @author zql
 * @date 2015/09/16
 */
public interface MyExpMainService extends IBaseAbstractService<MyExpEntity> {

	public MyExpEntity getByExpID(String myExpID);
	
	public List<MyExpEntity> getByUserID(String userID);
	
	public List<MyExpEntity> getPdfs();
	
	public List<MyExpEntity> getComplete(String userID);
	
	public List<MyExpEntity> getDoing(String userID);
}
