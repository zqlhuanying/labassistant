package com.labassistant.service.bbs;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labassistant.beans.BBSTopicEntity;
import com.labassistant.dao.service.BaseAbstractService;
import com.labassistant.service.SysUserService;
import com.labassistant.utils.DateUtil;

/**
 * BBS主题
 * @author zql
 * @date 2015/10/13
 */
@Service
public class BBSTopicServiceImpl extends BaseAbstractService<BBSTopicEntity>
		implements BBSTopicService {

	@Autowired
	private SysUserService sysUserService;
	
	@Override
	public List<BBSTopicEntity> getTopics(String moduleID){
		String hql = "from BBSTopicEntity where moduleID = ? order by createDateTime desc";
		return findListByHql(hql, moduleID);
	}
	
	@Override
	public List<BBSTopicEntity> searchTopics(String moduleID, String searchString){
		String hql = "from BBSTopicEntity where moduleID = ? and topicName like ? order by createDateTime desc";
		return findListByHql(hql, moduleID, "%" + searchString + "%");
	}
	
	@Override
	public void releaseTopic(BBSTopicEntity topic){
		topic.setTopicCreator(sysUserService.get(topic.getTopicCreatorID()).getNickName());
		topic.setCreateDateTime(DateUtil.str2Date(DateUtil.formatDate(new Date())));
		topic.setReviewCount(0);
		save(topic);
	}
}
