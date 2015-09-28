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

	public void perProcessMemo(MyExpProcessEntity myExpProcess);
	
	public MyExpProcessEntity getPerProcessMemo(String myExpID, String expStepID);
}
