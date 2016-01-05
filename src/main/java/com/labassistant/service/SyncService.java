package com.labassistant.service;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.labassistant.dao.service.IBaseAbstractService;

/**
 * 
 * @author zql
 * @date 2015/09/25
 */
public interface SyncService extends IBaseAbstractService {

	public void pushMyExp(String json);
	
    public void pushExpInstruction(String json, String expInstructionID, String userID, int allowDownload);

	public Map<String, Object> pullCommon(Date date);
	
	public Map<String, Object> pullAllDatas(String userID);
	
	public String getTableName(String key);
}
