package com.labassistant.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.labassistant.beans.ExpCategoryEntity;
import com.labassistant.dao.service.BaseAbstractService;

@Service
public class ExpCategoryServiceImpl extends BaseAbstractService<ExpCategoryEntity> 
			implements ExpCategoryService{

	@Override
	public List<ExpCategoryEntity> getAllExpCategory() {
		
		String hql = "from ExpCategoryEntity";
		
		List<ExpCategoryEntity> lists = findListByHql(hql);
		
		return lists;
	}

}
