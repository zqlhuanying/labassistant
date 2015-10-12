package com.labassistant.service;

import java.util.Map;

import com.labassistant.dao.service.IBaseAbstractService;

/**
 * 
 * @author zql
 * @date 2015/09/25
 */
public interface SyncService extends IBaseAbstractService {

	public void pushMyExp(Map<String, Object> map, String tableName);
	
	public Map<String, Object> pullCommon();
	
	public Map<String, Object> pullAllDatas(String userID);
	
	public String getTableName(String key);
}
