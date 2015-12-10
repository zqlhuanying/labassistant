package com.labassistant.service.exp;

import java.util.List;
import java.util.Map;

import com.labassistant.beans.ExpReviewEntity;
import com.labassistant.dao.service.IBaseAbstractService;

/**
 * 
 * @author zql
 * @date 2015/09/22
 */
public interface ExpReviewService extends IBaseAbstractService<ExpReviewEntity> {

	public List<ExpReviewEntity> getReviews(String expInstructionID);
	
	public ExpReviewEntity getReview(String userID, String expInstructionID);
	
	public List<Object> getReviewList(String expInstructionID, String lastExpReviewID, int pageSize);
	
	public Map<String, Object> getReviewDetail(String expReviewID);
	
	public void responseReview(String reviewJson);
	
	public boolean isReviewed(String userID, String expInstructionID);
	
	public int agreeOrNot(String expReviewID, Boolean isAgree);
}
