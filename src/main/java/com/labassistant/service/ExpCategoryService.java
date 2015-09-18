package com.labassistant.service;

import java.util.List;

import com.labassistant.beans.ExpCategoryEntity;
import com.labassistant.dao.service.IBaseAbstractService;

public interface ExpCategoryService extends IBaseAbstractService<ExpCategoryEntity> {

	public List<ExpCategoryEntity> getAllExpCategory();
	
	
}
