package com.labassistant.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.labassistant.common.BaseController;
import com.labassistant.service.SyncService;

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
		
		syncService.pushMyExp(request, json);
		
		map.putAll(retSuccess());
		map.put("data", "");
		return map;
	}
	
	// 上传实验说明书部分
	@RequestMapping(value = "pushExpInstruction", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> pushExpInstruction(HttpServletRequest request, String json){
		setErrorMsg(request, "同步实验说明书失败");
		Map<String, Object>  map = new HashMap<String, Object>();
		
		syncService.pushExpInstruction(request, json);
		
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
