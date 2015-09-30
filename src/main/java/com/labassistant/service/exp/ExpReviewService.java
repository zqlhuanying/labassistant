package com.labassistant.service.exp;

import java.util.List;

import com.labassistant.beans.ExpReviewEntity;
import com.labassistant.dao.service.IBaseAbstractService;

/**
 * 
 * @author zql
 * @date 2015/09/22
 */
public interface ExpReviewService extends IBaseAbstractService<ExpReviewEntity> {

	public List<ExpReviewEntity> getReviews(String expInstructionID);
	
	public void responseReview(ExpReviewEntity expReview);
	
	public int agreeOrNot(String expReviewID, Boolean isAgree);
}
