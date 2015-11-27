package com.labassistant.service.exp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.labassistant.beans.ExpReviewDetailEntity;
import com.labassistant.dao.service.BaseAbstractService;

/**
 * 
 * @author zql
 * @date 2015/11/02
 */
@Service
public class ExpReviewDetailServiceImpl extends
		BaseAbstractService<ExpReviewDetailEntity> implements
		ExpReviewDetailService {

	@Override
	public List<ExpReviewDetailEntity> getExpReviewDetails(String expReviewID){
		String hql = "from ExpReviewDetailEntity where expReviewID = ?";
		return findListByHql(hql, expReviewID);
	}
	
	@Override
	public ExpReviewDetailEntity getExpReviewDetail(String expReviewID, String expReviewOptID){
		String hql = "from ExpReviewDetailEntity where expReviewID = ? and expReviewOptID = ?";
		return findOneByHql(hql, expReviewID, expReviewOptID);
	}
	
	@Override
	public boolean isReviewed(String expReviewID, String expReviewOptID){
		String hql = "from ExpReviewDetailEntity where expReviewID = ? and expReviewOptID = ?";
		return getCount(hql, true, expReviewID, expReviewOptID) > 0;
	}
}
