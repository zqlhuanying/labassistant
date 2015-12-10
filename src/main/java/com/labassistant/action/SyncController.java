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
import com.labassistant.service.exp.ExpInstructionsMainService;

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
	@Autowired
	private ExpInstructionsMainService expInstructionsMainService;
	
	// 上传我的实验部分
	@RequestMapping(value = "pushMyExp", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> pushMyExp(HttpServletRequest request, String json){
		setErrorMsg(request, "同步我的实验失败");
		Map<String, Object>  map = new HashMap<String, Object>();
		
		syncService.pushMyExp(json);
		
		map.putAll(retSuccess());
		map.put("data", "");
		return map;
	}
	
	// 上传实验说明书部分
	@RequestMapping(value = "pushExpInstruction", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> pushExpInstruction(HttpServletRequest request, String json, String expInstructionID, String userID, int allowDownload){
		setErrorMsg(request, "同步实验说明书失败");
		Map<String, Object>  map = new HashMap<String, Object>();

//		if(expInstructionsMainService.isPublic(expInstructionID) ||
//				!expInstructionsMainService.isOwn(expInstructionID, userID)){
//			map.put("code", "2");
//			map.put("msg", "没有权限提交说明书，有可能这份说明书已成为标准或不属于你");
//			return map;
//		}
		
		syncService.pushExpInstruction(json, expInstructionID, userID, allowDownload);
		
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
