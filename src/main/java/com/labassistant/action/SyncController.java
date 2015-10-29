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
	
	// 上传我的实验部分
	@RequestMapping(value = "pushMyExp", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> pushMyExp(HttpServletRequest request, String json){
		setErrorMsg(request, "同步我的实验失败");
		Map<String, Object>  map = new HashMap<String, Object>();
		Map<String, Object> requestMap = JSONUtil.json2Map(json);
		//requestMap = (Map)requestMap.get("data");
		
		// requestMap 中的Value有可能是数组，即Object有可能是数组
		Iterator<Map.Entry<String, Object>> iterator = requestMap.entrySet().iterator();
		while(iterator.hasNext()){
			Map.Entry<String, Object> entry = iterator.next();
			String tableName = syncService.getTableName(entry.getKey());
			if(entry.getValue().getClass() == ArrayList.class){
				for(Map<String, Object> innerMap : (ArrayList<Map<String, Object>>)entry.getValue()){
					// TODO request最好不要传递，最好能在上下文中获得
					syncService.pushMyExp(request, innerMap, tableName);
				}
			} else {
				syncService.pushMyExp(request, (Map<String, Object>)entry.getValue(), tableName);
			}
		}
		map.putAll(retSuccess());
		map.put("data", "");
		return map;
	}
	
	// 下推试剂/试剂厂商对应表等公共部分
	@RequestMapping(value = "pullCommon", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> pullCommon(HttpServletRequest request){
		setErrorMsg(request, "推送失败");
		Map<String, Object>  map = new HashMap<String, Object>();
		Map<String, Object>  innerMap = syncService.pullCommon();
		map.putAll(retSuccess());
		map.put("data", innerMap);
		return map;
	}
	
	@RequestMapping(value = "pullAllDatas", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> pullAllDatas(HttpServletRequest request, String userID){
		setErrorMsg(request, "推送失败");
		Map<String, Object>  map = new HashMap<String, Object>();
		Map<String, Object>  innerMap = syncService.pullAllDatas(userID);
		map.putAll(retSuccess());
		map.put("data", innerMap);
		return map;
	}
}
