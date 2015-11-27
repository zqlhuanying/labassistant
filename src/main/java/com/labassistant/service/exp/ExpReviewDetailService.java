package com.labassistant.service.exp;

import java.util.List;

import com.labassistant.beans.ExpReviewDetailEntity;
import com.labassistant.dao.service.IBaseAbstractService;

/**
 * 
 * @author zql
 * @date 2015/11/02
 */
public interface ExpReviewDetailService extends
		IBaseAbstractService<ExpReviewDetailEntity> {

	public List<ExpReviewDetailEntity> getExpReviewDetails(String expReviewID);
	
	public ExpReviewDetailEntity getExpReviewDetail(String expReviewID, String expReviewOptID);
	
	public boolean isReviewed(String expReviewID, String expReviewOptID);
}
