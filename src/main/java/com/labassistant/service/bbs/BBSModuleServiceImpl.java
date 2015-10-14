package com.labassistant.service.bbs;

import java.util.List;

import org.springframework.stereotype.Service;

import com.labassistant.beans.BBSModuleEntity;
import com.labassistant.dao.service.BaseAbstractService;

/**
 * BBS模块
 * @author zql
 * @date 2015/10/13
 */
@Service
public class BBSModuleServiceImpl extends BaseAbstractService<BBSModuleEntity>
		implements BBSModuleService {

	@Override
	public List<BBSModuleEntity> getModules(){
		String hql = "from BBSModuleEntity where allowShow = 1";
		return findListByHql(hql);
	}
}
