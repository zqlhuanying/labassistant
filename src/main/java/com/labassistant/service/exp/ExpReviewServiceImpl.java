package com.labassistant.service.exp;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.labassistant.beans.ExpReviewEntity;
import com.labassistant.beans.SysUserEntity;
import com.labassistant.dao.service.BaseAbstractService;
import com.labassistant.utils.DateUtil;

/**
 * 实验评论服务
 * @author zql
 * @date 2015/09/22
 */
@Service
public class ExpReviewServiceImpl extends BaseAbstractService<ExpReviewEntity>
		implements ExpReviewService {

	/**
	 * 获取实验评论
	 */
	@Override
	public List<ExpReviewEntity> getReviews(String expInstructionID){
		String hql = "from ExpReviewEntity where expInstructionID = ?";
		List<ExpReviewEntity> lists = findListByHql(hql, expInstructionID);
		return lists;
	}
	
	/**
	 * 对实验进行评论
	 */
	@Override
	public void responseReview(ExpReviewEntity expReview){
		Date now = new Date();
		expReview.setReviewDate(DateUtil.str2Date("yyyy-MM-dd", DateUtil.formatDate("yyyy-MM-dd", now)));
		expReview.setReviewYear(DateUtil.getYear());
		expReview.setReviewMonth(DateUtil.getMonth());
		save(expReview);
	}
	
	@Override
	public int agreeOrNot(String expReviewID, Boolean isAgree){
		if(isAgree){
			return reviewAgree(expReviewID);
		} else {
			return reviewDisagree(expReviewID);
		}
	}
	
	/**
	 * 点赞
	 */
	public int reviewAgree(String expReviewID){
		ExpReviewEntity expReview = get(expReviewID);
		int agreeCount = expReview.getAgreeCount() + 1;
		expReview.setAgreeCount(expReview.getAgreeCount() + 1);
		update(expReview);
		return agreeCount;
	}
	
	/**
	 * 点不攒
	 * @param expReviewID
	 */
	public int reviewDisagree(String expReviewID){
		ExpReviewEntity expReview = get(expReviewID);
		int disagreeCount = expReview.getDisagreeCount() + 1;
		expReview.setDisagreeCount(expReview.getDisagreeCount() + 1);
		update(expReview);
		return disagreeCount;
	}
}
