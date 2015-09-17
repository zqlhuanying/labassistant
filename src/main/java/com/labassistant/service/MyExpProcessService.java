package com.labassistant.service;

import java.util.List;

import com.labassistant.beans.MyExpProcessEntity;
import com.labassistant.dao.service.IBaseAbstractService;

/**
 * 
 * @author zql
 * @date 2015/09/16
 */
public interface MyExpProcessService extends IBaseAbstractService<MyExpProcessEntity> {

	public MyExpProcessEntity getByExpID(String myExpID);

}
