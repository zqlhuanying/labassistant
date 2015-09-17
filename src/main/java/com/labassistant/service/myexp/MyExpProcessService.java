package com.labassistant.service.myexp;

import java.util.List;

import com.labassistant.beans.MyExpProcessEntity;
import com.labassistant.dao.service.IBaseAbstractService;

/**
 * 
 * @author zql
 * @date 2015/09/16
 */
public interface MyExpProcessService extends IBaseAbstractService<MyExpProcessEntity> {

	public List<MyExpProcessEntity> getList(String myExpID);
	
	public MyExpProcessEntity getByExpID(String myExpID);

	public boolean isExists(String expInstructionID);
	
	public MyExpProcessEntity getByInstructionID(String expInstructionID);
}
