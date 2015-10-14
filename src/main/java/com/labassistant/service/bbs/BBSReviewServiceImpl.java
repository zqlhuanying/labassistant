package com.labassistant.service.bbs;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labassistant.beans.BBSReviewEntity;
import com.labassistant.beans.BBSTopicEntity;
import com.labassistant.dao.service.BaseAbstractService;
import com.labassistant.service.SysUserService;
import com.labassistant.utils.DateUtil;

/**
 * BBS评论
 * @author zql
 * @date 2015/10/14
 */
@Service
public class BBSReviewServiceImpl extends BaseAbstractService<BBSReviewEntity>
		implements BBSReviewService {

	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private BBSTopicService bbsTopicService;
	
	@Override
	public List<BBSReviewEntity> getReviews(String topicID){
		String hql = "from BBSReviewEntity where topicID = ? order by reviewDateTime desc";
		return findListByHql(hql, topicID);
	}
	
	@Override
	public void responseReview(BBSReviewEntity review){
		BBSTopicEntity topic = bbsTopicService.get(review.getTopicID());
		review.setModuleID(topic.getModuleID());
		review.setReviewer(sysUserService.get(review.getReviewerID()).getNickName());
		review.setReviewDateTime(DateUtil.str2Date(DateUtil.formatDate(new Date())));
		if(StringUtils.isBlank(review.getParentReviewID())){
			review.setParentReviewID("");
		}
		save(review);
		
		// 更新主题表中的评论数
		Integer reviewCount = topic.getReviewCount();
		synchronized (reviewCount) {
			reviewCount = reviewCount + 1;
			topic.setReviewCount(reviewCount);
			update(topic);
		}
	}
}
