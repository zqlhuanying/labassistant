package com.labassistant.service.exp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.labassistant.beans.ExpSubCategoryEntity;
import com.labassistant.dao.service.BaseAbstractService;

@Service
public class ExpSubCategoryServiceImpl extends BaseAbstractService<ExpSubCategoryEntity>
				implements ExpSubCategoryService{

	@Override
	public List<ExpSubCategoryEntity> getSubCategoryByParentID(
			String sCategortID) {
		
		String hql = "from ExpSubCategoryEntity Where expCategoryID = ?";
		
		List<ExpSubCategoryEntity> lists = findListByHql(hql,sCategortID);
		
		return lists;
	}

}
