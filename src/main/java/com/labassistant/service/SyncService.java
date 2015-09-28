package com.labassistant.service;

import java.util.Map;

import com.labassistant.dao.service.IBaseAbstractService;

/**
 * 
 * @author zql
 * @date 2015/09/25
 */
public interface SyncService extends IBaseAbstractService {

	public void syncData(Map<String, Object> map, String tableName);
	
	public String getTableName(String key);
}
