package com.labassistant.service.bbs;

import java.util.List;

import com.labassistant.beans.BBSModuleEntity;
import com.labassistant.dao.service.IBaseAbstractService;

/**
 * 
 * @author zql
 * @date 2015/10/13
 */
public interface BBSModuleService extends IBaseAbstractService<BBSModuleEntity> {

	public List<BBSModuleEntity> getModules();
}
