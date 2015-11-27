package com.labassistant.service.exp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labassistant.beans.ExpReviewDetailEntity;
import com.labassistant.beans.ExpReviewEntity;
import com.labassistant.beans.Pagination;
import com.labassistant.constants.LabConstant;
import com.labassistant.context.PaginationContext;
import com.labassistant.dao.service.BaseAbstractService;
import com.labassistant.service.SysUserService;
import com.labassistant.utils.DateUtil;
import com.labassistant.utils.JSONUtil;

/**
 * 实验评论服务
 * @author zql
 * @date 2015/09/22
 */
@Service
public class ExpReviewServiceImpl extends BaseAbstractService<ExpReviewEntity>
		implements ExpReviewService {

	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private ExpReviewDetailService expReviewDetailService;
	@Autowired
	private ExpReviewOptService expReviewOptService;
	
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
	 * 获取实验评论
	 */
	@Override
	public ExpReviewEntity getReview(String userID, String expInstructionID){
		String hql = "from ExpReviewEntity where reviewerID = ? and expInstructionID = ?";
		return findOneByHql(hql, userID, expInstructionID);
	}
	
	/**
	 * 获取实验评论
	 */
	@Override
	public List<Object> getReviewList(String expInstructionID, String lastExpReviewID, int pageSize){
		List<Object> object = new ArrayList<Object>();
		Pagination<ExpReviewEntity> reviews = getPage(expInstructionID, lastExpReviewID, pageSize);
		if(reviews != null){
			for(ExpReviewEntity review : reviews.getRows()){
				Map<String, Object> innerMap = new HashMap<String, Object>();
				innerMap.put("expReviewID", review.getExpReviewID());
				innerMap.put("reviewDate", DateUtil.formatDate(LabConstant.DATEFORMAT, review.getReviewDate()));
				//innerMap.put("reviewInfo", review.getReviewInfo());
				innerMap.put("expScore", review.getExpScore());
				innerMap.put("nickName", sysUserService.get(review.getReviewerID()).getNickName());
				innerMap.put("agreeCount", review.getAgreeCount());
				innerMap.put("disagreeCount", review.getDisagreeCount());
				object.add(innerMap);
			}
		}
		return object;
	}
	
	/**
	 * 获取实验评论详细信息
	 */
	@Override
	public Map<String, Object> getReviewDetail(String expReviewID){
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> object = new ArrayList<Object>();
		ExpReviewEntity expReview = get(expReviewID);
		map.put("reviewInfo", expReview.getReviewInfo());
		List<ExpReviewDetailEntity> expReviewDetails = expReviewDetailService.getExpReviewDetails(expReviewID);
		if(expReviewDetails != null && expReviewDetails.size() > 0){
			for(ExpReviewDetailEntity expReviewDetail : expReviewDetails){
				Map<String, String> innerMap = new HashMap<String, String>();
				innerMap.put("expReviewOptName", expReviewOptService.get(expReviewDetail.getExpReviewOptID()).getExpReviewOptName());
				innerMap.put("reviewOptScore", String.valueOf(expReviewDetail.getExpReviewOptScore()));
				object.add(innerMap);
			}
		}
		map.put("reviewOpts", object);
		return map;
	}
	
	/**
	 * 对实验进行评论
	 */
	@Override
	public void responseReview(String json){
		Map<String, Object> requestMap = (Map<String, Object>)JSONUtil.json2Map(json);
		
		String expInstructionID = (String)requestMap.get("expInstructionID");
		String reviewerID = (String)requestMap.get("reviewerID");
		boolean isReviewed = isReviewed(reviewerID, expInstructionID); 
		ExpReviewEntity expReview = new ExpReviewEntity();
		if(isReviewed){
			expReview = getReview(reviewerID, expInstructionID);
		}
		Date now = new Date();
		expReview.setReviewDate(DateUtil.str2Date(LabConstant.DATEFORMAT, DateUtil.formatDate(LabConstant.DATEFORMAT, now)));
		expReview.setReviewYear(DateUtil.getYear(now));
		expReview.setReviewMonth(DateUtil.getMonth(now));
		expReview.setExpInstructionID(expInstructionID);
		expReview.setReviewerID(reviewerID);
		expReview.setReviewInfo((String)requestMap.get("reviewInfo"));
		expReview.setExpScore((Integer)requestMap.get("expScore"));
		Serializable pk = null;
		if(isReviewed){
			update(expReview);
			pk = expReview.getExpReviewID();
		} else {
			pk = save(expReview);
		}
		
		List<Map<String, Object>> expReviewDetails = (List<Map<String, Object>>)requestMap.get("expReviewDetails");
		
		for(Map<String, Object> expReviewDetail : expReviewDetails){
			ExpReviewDetailEntity expReviewDetailEntity = new ExpReviewDetailEntity();
			String expReviewOptID = (String)expReviewDetail.get("expReviewOptID");
			boolean isReviewedForDetail = expReviewDetailService.isReviewed((String)pk, expReviewOptID);
			if(isReviewedForDetail){
				expReviewDetailEntity = expReviewDetailService.getExpReviewDetail((String)pk, expReviewOptID);
			}
			expReviewDetailEntity.setExpReviewID((String)pk);
			expReviewDetailEntity.setExpReviewOptID(expReviewOptID);
			expReviewDetailEntity.setExpReviewOptScore((Integer)expReviewDetail.get("expReviewOptScore"));
			if(isReviewedForDetail){
				expReviewDetailService.update(expReviewDetailEntity);
			} else{
				expReviewDetailService.save(expReviewDetailEntity);
			}
		}
	}
	
	@Override
	public boolean isReviewed(String userID, String expInstructionID){
		String hql = "from ExpReviewEntity where expInstructionID = ? and reviewerID = ?";
		return getCount(hql, true, expInstructionID, userID) > 0;
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
	
	private Pagination<ExpReviewEntity> getPage(String expInstructionID, String lastExpReviewID, int pageSize){
		// 获取 lastExpReviewID 在数据库中的行号
		StringBuffer offsetHql = new StringBuffer(); 
		offsetHql.append("select sum(1) from ExpReviewEntity where expInstructionID = ?");
		offsetHql.append(" and reviewDate >= (select reviewDate from ExpReviewEntity where expreviewID = ?)");	
		Long offset = findOneByHql(offsetHql.toString(), expInstructionID, lastExpReviewID);
		if(offset == null){
			offset = 0L;
		}
		
		// 获取接下去的pageSize的记录数
		PaginationContext.setOffset(offset.intValue());
		PaginationContext.setPagesize(pageSize);
		String pageHql = "from ExpReviewEntity order by reviewDate desc";
		return pageByHql(pageHql);
	}
}
