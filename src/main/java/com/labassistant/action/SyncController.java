package com.labassistant.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.labassistant.common.BaseController;
import com.labassistant.service.SyncService;
import com.labassistant.utils.JSONUtil;

/**
 * 同步数据
 * @author zql
 * @date 2015/09/25
 */
@Controller
@RequestMapping(value = "/sync")
public class SyncController extends BaseController {

	@Autowired
	private SyncService syncService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> syncData(HttpServletRequest request, String json){
		setErrorMsg(request, "同步失败");
		Map<String, Object>  map = new HashMap<String, Object>();
		Map<String, Object> requestMap = JSONUtil.json2Map(json);
		requestMap = (Map)requestMap.get("data");
		
		// requestMap 中的Value有可能是数组，即Object有可能是数组
		Iterator<Map.Entry<String, Object>> iterator = requestMap.entrySet().iterator();
		while(iterator.hasNext()){
			Map.Entry<String, Object> entry = iterator.next();
			String tableName = syncService.getTableName(entry.getKey());
			if(entry.getValue().getClass() == ArrayList.class){
				for(Map<String, Object> innerMap : (ArrayList<Map<String, Object>>)entry.getValue()){
					syncService.syncData(innerMap, tableName);
				}
			} else {
				syncService.syncData((Map<String, Object>)entry.getValue(), tableName);
			}
		}
		map.putAll(retSuccess());
		map.put("data", "");
		return map;
	}
}
