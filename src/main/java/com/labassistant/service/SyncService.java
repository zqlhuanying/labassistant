package com.labassistant.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.labassistant.dao.service.IBaseAbstractService;

/**
 * 
 * @author zql
 * @date 2015/09/25
 */
public interface SyncService extends IBaseAbstractService {

	public void pushMyExp(HttpServletRequest request, Map<String, Object> map, String tableName);
	
	public Map<String, Object> pullCommon();
	
	public Map<String, Object> pullAllDatas(String userID);
	
	public String getTableName(String key);
}
