package com.labassistant.service.exp;

import org.springframework.stereotype.Service;

import com.labassistant.beans.ExpSubCategoryEntity;
import com.labassistant.beans.Pagination;
import com.labassistant.context.PaginationContext;
import com.labassistant.dao.service.BaseAbstractService;

@Service
public class ExpSubCategoryServiceImpl extends BaseAbstractService<ExpSubCategoryEntity>
				implements ExpSubCategoryService{

	@Override
	public Pagination<ExpSubCategoryEntity> getSubCategoryByParentID(String sCategortID, int limit) {

		String hql = "from ExpSubCategoryEntity Where expCategoryID = ? ";

		if(limit > 0){
			PaginationContext.setPagesize(limit);
		}
		
		Pagination<ExpSubCategoryEntity> lists = pageByHql(hql, sCategortID);
		
		return lists;
	}

}
