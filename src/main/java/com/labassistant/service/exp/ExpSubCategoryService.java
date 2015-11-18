package com.labassistant.service.exp;

import com.labassistant.beans.ExpSubCategoryEntity;
import com.labassistant.beans.Pagination;
import com.labassistant.dao.service.IBaseAbstractService;

public interface ExpSubCategoryService extends IBaseAbstractService<ExpSubCategoryEntity> {

	public Pagination<ExpSubCategoryEntity> getSubCategoryByParentID(String sCategortID, int limit); 
}
