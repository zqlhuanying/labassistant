package com.labassistant.service.bbs;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labassistant.beans.BBSTopicEntity;
import com.labassistant.beans.Pagination;
import com.labassistant.context.PaginationContext;
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
	public Pagination<BBSTopicEntity> searchTopics(String moduleID, String searchString, String lastTopicID, int pageSize){
		//String hql = "from BBSTopicEntity where moduleID = ? and topicName like ? order by createDateTime desc";
		//return findListByHql(hql, moduleID, "%" + searchString + "%");
		// 获取 lastTopicID 在数据库中的行号
		StringBuffer offsetHql = new StringBuffer(); 
		offsetHql.append("select sum(1) from BBSTopicEntity where moduleID = ? and topicName like ? ");
		offsetHql.append(" and createDateTime >= (select createDateTime from BBSTopicEntity where topicID = ?)");	
		Long offset = findOneByHql(offsetHql.toString(), moduleID, "%" + searchString + "%", lastTopicID);
		if(offset == null){
			offset = 0L;
		}
				
		// 获取接下去的pageSize的记录数
		PaginationContext.setOffset(offset.intValue());
		PaginationContext.setPagesize(pageSize);
		String pageHql = "from BBSTopicEntity where moduleID = ? and topicName like ? order by createDateTime desc";
		return pageByHql(pageHql, moduleID, "%" + searchString + "%");
	}
	
	@Override
	public void releaseTopic(BBSTopicEntity topic){
		topic.setTopicCreator(sysUserService.get(topic.getTopicCreatorID()).getNickName());
		topic.setCreateDateTime(DateUtil.str2Date(DateUtil.formatDate(new Date())));
		topic.setReviewCount(0);
		save(topic);
	}
}
