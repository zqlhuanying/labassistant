package com.labassistant.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.labassistant.beans.BBSModuleEntity;
import com.labassistant.beans.BBSReviewEntity;
import com.labassistant.beans.BBSTopicEntity;
import com.labassistant.common.BaseController;
import com.labassistant.constants.AppConfig;
import com.labassistant.constants.ReturnJson;
import com.labassistant.service.SysUserService;
import com.labassistant.service.bbs.BBSModuleService;
import com.labassistant.service.bbs.BBSReviewService;
import com.labassistant.service.bbs.BBSTopicService;
import com.labassistant.utils.CommonUtil;
import com.labassistant.utils.DateUtil;

/**
 * BBS
 * @author zql
 * @date 2015/10/13
 */
@Controller
@RequestMapping(value = "/bbs")
public class BBSController extends BaseController {

	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private BBSModuleService bbsModuleService;
	@Autowired
	private BBSTopicService bbsTopicService;
	@Autowired
	private BBSReviewService bbsReviewService;
	
	@RequestMapping(value = "/modules")
	@ResponseBody
	public Map<String, Object> getModules(HttpServletRequest request){
		setErrorMsg(request, "获取BBS模块失败");
		Map<String, Object> map = new HashMap<String, Object>();
		List<BBSModuleEntity> modules = bbsModuleService.getModules();
		map.putAll(retSuccess());
		map.put("data", modules);
		return map;
	}
	
	@RequestMapping(value = "/topics")
	@ResponseBody
	public Map<String, Object> getTopics(HttpServletRequest request, String moduleID){
		setErrorMsg(request, "获取BBS主题失败");
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> objects = new ArrayList<Object>();
		List<BBSTopicEntity> topics = bbsTopicService.getTopics(moduleID);
		if(topics != null){
			for(BBSTopicEntity topic : topics){
				Map<String, Object> innerMap = new HashMap<String, Object>();
				innerMap.put("topicID", topic.getTopicID());
				innerMap.put("topicName", topic.getTopicName());
				innerMap.put("topicCreator", topic.getTopicCreator());
				innerMap.put("createDateTime", DateUtil.formatDate(topic.getCreateDateTime()));
				innerMap.put("reviewCount", topic.getReviewCount());
				objects.add(innerMap);
			}
		}
		map.putAll(retSuccess());
		map.put("data", objects);
		return map;
	}
	
	@RequestMapping(value = "/searchTopics")
	@ResponseBody
	public Map<String, Object> searchTopics(HttpServletRequest request, String moduleID, String searchString, String lastTopicID){
		setErrorMsg(request, "搜索BBS主题失败");
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> objects = new ArrayList<Object>();
		List<BBSTopicEntity> topics = bbsTopicService.searchTopics(moduleID, searchString, lastTopicID, 10).getRows();
		if(topics != null){
			for(BBSTopicEntity topic : topics){
				Map<String, Object> innerMap = new HashMap<String, Object>();
				innerMap.put("topicID", topic.getTopicID());
				innerMap.put("topicName", topic.getTopicName());
				innerMap.put("topicCreator", topic.getTopicCreator());
				innerMap.put("createDateTime", DateUtil.formatDate(topic.getCreateDateTime()));
				innerMap.put("reviewCount", topic.getReviewCount());
				objects.add(innerMap);
			}
		}
		map.putAll(retSuccess());
		map.put("data", objects);
		return map;
	}
	
	@RequestMapping(value = "/releaseTopic", method = RequestMethod.POST)
	@ResponseBody
	// 虽然设置了整个实体类，但需要的是moduleID,topicName,topicDetail,topicCreatorID
	public Map<String, Object> releaseTopic(HttpServletRequest request, BBSTopicEntity topic){
		setErrorMsg(request, "发布BBS主题失败");
		Map<String, Object> map = new HashMap<String, Object>();
		bbsTopicService.releaseTopic(topic);
		map.putAll(retSuccess());
		map.put("data", "");
		return map;
	}
	
	@RequestMapping(value = "/reviews")
	@ResponseBody
	public Map<String, Object> reviews(HttpServletRequest request, String topicID){
		setErrorMsg(request, "获取BBS主题下的评论列表失败");
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> map1 = new HashMap<String, Object>();
		List<Object> objects = new ArrayList<Object>();
		BBSTopicEntity topic = bbsTopicService.get(topicID);
		if(topic != null){
			Map<String, String> topicMap = new HashMap<String, String>();
			topicMap.put("topicID", topic.getTopicID());
			topicMap.put("topicName", topic.getTopicName());
			topicMap.put("topicDetail", topic.getTopicDetail());
			map1.put("topic", topicMap);
		}
		List<BBSReviewEntity> reviews = bbsReviewService.getReviews(topicID);
		if(reviews != null){
			for(BBSReviewEntity review : reviews){
				Map<String, Object> innerMap = new HashMap<String, Object>();
				String iconUrl = sysUserService.get(review.getReviewerID()).getIcon();
				innerMap.put("reviewID", review.getReviewID());
				innerMap.put("reviewer", review.getReviewer());
				innerMap.put("parentReviewID", review.getParentReviewID());
				innerMap.put("parentReviewer", StringUtils.isNotBlank(review.getParentReviewID()) ?
												bbsReviewService.get(review.getParentReviewID()).getReviewer() :
													"");
				innerMap.put("icon", StringUtils.isNotBlank(iconUrl) ? AppConfig.DOMAIN_PAGE + "/" + iconUrl :
										"");
				innerMap.put("reviewDetail", review.getReviewDetail());
				innerMap.put("reviewDateTime", DateUtil.formatDate(review.getReviewDateTime()));
				objects.add(innerMap);
			}
			map1.put("reviews", objects);
		}
		CommonUtil.unionMap(ReturnJson.REVIEWS, map1);
		map.putAll(retSuccess());
		map.put("data", map1);
		return map;
	}
	
	@RequestMapping(value = "/responseReview", method = RequestMethod.POST)
	@ResponseBody
	// 虽然设置了整个实体类，但需要的是topicID,reviewerID,reviewDetail,parentReviewID
	public Map<String, Object> responseReview(HttpServletRequest request, BBSReviewEntity review){
		setErrorMsg(request, "回复某评论失败");
		Map<String, Object> map = new HashMap<String, Object>();
		bbsReviewService.responseReview(review);
		map.putAll(retSuccess());
		map.put("data", "");
		return map;
	}
}
