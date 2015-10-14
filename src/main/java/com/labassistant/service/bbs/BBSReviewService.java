package com.labassistant.service.bbs;

import java.util.List;

import com.labassistant.beans.BBSReviewEntity;
import com.labassistant.dao.service.IBaseAbstractService;

/**
 * 
 * @author zql
 * @date 2015/10/14
 */
public interface BBSReviewService extends IBaseAbstractService<BBSReviewEntity> {

	public List<BBSReviewEntity> getReviews(String topicID);
	
	public void responseReview(BBSReviewEntity review);
}
