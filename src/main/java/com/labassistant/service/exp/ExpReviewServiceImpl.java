package com.labassistant.service.exp;

import java.io.Serializable;
import java.util.*;

import com.labassistant.beans.ExpReviewDetailOfOptEntity;
import com.labassistant.service.SyncService;
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
    @Autowired
    private ExpReviewDetailOfOptService expReviewDetailOfOptService;
    @Autowired
    private ExpInstructionsMainService expInstructionsMainService;
    @Autowired
    private SyncService syncService;
	
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
				Map<String, Object> innerMap = new HashMap<String, Object>();
                String expReviewOptName = expReviewOptService.get(expReviewDetail.getExpReviewOptID()).getExpReviewOptName();
				innerMap.put("expReviewOptName", expReviewOptName);
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
	@SuppressWarnings("unchecked")
	@Override
	public void responseReview(String reviewJson){
		Map<String, Object> requestMap = (Map<String, Object>)JSONUtil.json2Map(reviewJson);

        // 更新实验评论表
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
		Serializable expReviewPk = null;
		if(isReviewed){
			update(expReview);
			expReviewPk = expReview.getExpReviewID();
		} else {
			expReviewPk = save(expReview);
		}

        // 更新实验评论详细表
		List<Map<String, Object>> expReviewDetails = (List<Map<String, Object>>)requestMap.get("expReviewDetails");
		
		for(Map<String, Object> expReviewDetail : expReviewDetails){
			ExpReviewDetailEntity expReviewDetailEntity = new ExpReviewDetailEntity();
			String expReviewOptID = (String)expReviewDetail.get("expReviewOptID");
			boolean isReviewedForDetail = expReviewDetailService.isReviewed((String)expReviewPk, expReviewOptID);
			if(isReviewedForDetail){
				expReviewDetailEntity = expReviewDetailService.getExpReviewDetail((String)expReviewPk, expReviewOptID);
			}
			expReviewDetailEntity.setExpReviewID((String)expReviewPk);
			expReviewDetailEntity.setExpReviewOptID(expReviewOptID);
			expReviewDetailEntity.setExpReviewOptScore((Integer)expReviewDetail.get("expReviewOptScore"));
			if(isReviewedForDetail){
				expReviewDetailService.update(expReviewDetailEntity);
                expReviewDetailEntity.getExpReviewDetailID();
			} else{
				expReviewDetailService.save(expReviewDetailEntity);
			}

            // 更新实验评论低分项明细表
            List<Map<String, Object>> expReviewDetailOfOpts = (List<Map<String, Object>>)expReviewDetail.get("expReviewDetailOfOpts");
            for(Map<String, Object> expReviewDetailOfOpt : expReviewDetailOfOpts){
                ExpReviewDetailOfOptEntity expReviewDetailOfOptEntity = new ExpReviewDetailOfOptEntity();
                String itemID = (String)expReviewDetailOfOpt.get("itemID");
                boolean isReviewedForDetailOpt = expReviewDetailOfOptService.isReviewed((String)expReviewPk, expReviewOptID, itemID);
                if(isReviewedForDetailOpt){
                    expReviewDetailOfOptEntity = expReviewDetailOfOptService.getExpReviewDetailOfOpt((String)expReviewPk, expReviewOptID, itemID);
                }
                expReviewDetailOfOptEntity.setExpReviewID((String)expReviewPk);
                expReviewDetailOfOptEntity.setExpReviewOptID(expReviewOptID);
                expReviewDetailOfOptEntity.setItemID(itemID);
                expReviewDetailOfOptEntity.setItemName((String)expReviewDetailOfOpt.get("itemName"));
                expReviewDetailOfOptEntity.setSupplierID((String)expReviewDetailOfOpt.get("supplierID"));
                expReviewDetailOfOptEntity.setItemScore((Integer)expReviewDetailOfOpt.get("itemScore"));
                expReviewDetailOfOptEntity.setExpReviewDetailOfOptDesc((String)expReviewDetailOfOpt.get("desc"));
                if(isReviewedForDetailOpt){
                    expReviewDetailOfOptService.update(expReviewDetailOfOptEntity);
                } else {
                    expReviewDetailOfOptService.save(expReviewDetailOfOptEntity);
                }
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
