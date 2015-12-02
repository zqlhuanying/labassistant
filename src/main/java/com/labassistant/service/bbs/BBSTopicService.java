package com.labassistant.service.bbs;

import java.util.List;

import com.labassistant.beans.BBSTopicEntity;
import com.labassistant.beans.Pagination;
import com.labassistant.dao.service.IBaseAbstractService;

/**
 * 
 * @author zql
 * @date 2015/10/13
 */
public interface BBSTopicService extends IBaseAbstractService<BBSTopicEntity> {

	public List<BBSTopicEntity> getTopics(String moduleID);
	
	public Pagination<BBSTopicEntity> searchTopics(String moduleID, String searchString, String lastTopicID, int pageSize);
	
	public void releaseTopic(BBSTopicEntity topic);
}
