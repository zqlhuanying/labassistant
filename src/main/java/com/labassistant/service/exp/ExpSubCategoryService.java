package com.labassistant.service.exp;

import java.util.List;

import com.labassistant.beans.ExpSubCategoryEntity;
import com.labassistant.dao.service.IBaseAbstractService;

public interface ExpSubCategoryService extends IBaseAbstractService<ExpSubCategoryEntity> {

	public List<ExpSubCategoryEntity> getSubCategoryByParentID(String sCategortID); 
}
