package com.labassistant.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.labassistant.beans.ExpInstructionsMainEntity;
import com.labassistant.beans.ExpReagentEntity;
import com.labassistant.beans.MyExpMainEntity;
import com.labassistant.beans.MyExpProcessEntity;
import com.labassistant.common.BaseController;
import com.labassistant.service.ExpInstructionsMainService;
import com.labassistant.service.ExpReagentService;
import com.labassistant.service.MyExpMainService;
import com.labassistant.service.MyExpProcessService;

/**
 * 实验控制类
 * @author zql
 * @date 2015/09/15
 */
@Controller
@RequestMapping(value = "/lab")
public class LabController extends BaseController {

	@Autowired
	private ExpReagentService expReagentService;
	@Autowired
	private MyExpMainService myExpMainService;
	@Autowired
	private MyExpProcessService myExpProcessService;
	@Autowired
	private ExpInstructionsMainService expInstructionsMainService;
	
	@RequestMapping(value = "/allReagents")
	@ResponseBody
	public List<ExpReagentEntity> getReagents(HttpServletRequest request, String expInstructionID){
		setErrorMsg(request, "获取实验所有试剂出错");
		return expReagentService.getExpReagentLists(expInstructionID);
	}
	
	@RequestMapping(value = "/perAmount")
	@ResponseBody
	public Map<String, Integer> getPerAmount(HttpServletRequest request, String expInstructionID){
		setErrorMsg(request, "获取实验试剂及对应的用量出错");
		return expReagentService.getExpReagentAndAmount(expInstructionID);
	}
	
	@RequestMapping(value = "/reagentAndSupplier")
	@ResponseBody
	public Map<String, String> getReagentAndSupplier(HttpServletRequest request, String expInstructionID){
		setErrorMsg(request, "获取实验试剂及对应的提供商出错");
		return expReagentService.getExpReagentAndSupplierName(expInstructionID);
	}
	
	@RequestMapping(value = "/getComplete")
	@ResponseBody
	public Map<String, List<Object>> getComplete(HttpServletRequest request, String userID){
		setErrorMsg(request, "获取已完成实验列表出错");
		Map<String, List<Object>>  map = new HashMap<String, List<Object>>();
		List<Object> object = new ArrayList<Object>();
		Map<String, String> innerMap = new HashMap<String, String>();
		List<MyExpMainEntity> myExpMainLists = myExpMainService.getComplete(userID);
		for(MyExpMainEntity myExpMainList : myExpMainLists){
			ExpInstructionsMainEntity instruction = getInstructionByExp(myExpMainList);
			if(instruction != null){
				innerMap.put("labID", myExpMainList.getMyExpID());
				innerMap.put("instructionName", instruction.getExperimentName());
				object.add(innerMap);
			}
		}
		map.put("complete", object);
		return map;
	}
	
	@RequestMapping(value = "/getUnComplete")
	@ResponseBody
	public Map<String, List<Object>> getUnComplete(HttpServletRequest request, String userID){
		setErrorMsg(request, "获取正在进行的实验列表出错");
		Map<String, List<Object>>  map = new HashMap<String, List<Object>>();
		List<Object> object = new ArrayList<Object>();
		Map<String, String> innerMap = new HashMap<String, String>();
		List<MyExpMainEntity> myExpMainLists = myExpMainService.getUnComplete(userID);
		for(MyExpMainEntity myExpMainList : myExpMainLists){
			ExpInstructionsMainEntity instruction = getInstructionByExp(myExpMainList);
			if(instruction != null){
				innerMap.put("labID", myExpMainList.getMyExpID());
				innerMap.put("instructionName", instruction.getExperimentName());
				object.add(innerMap);
			}
		}
		map.put("unComplete", object);
		return map;
	}
	
	/**
	 * 获取所做实验所对应的说明书信息
	 * @param myExp
	 * @return
	 */
	private ExpInstructionsMainEntity getInstructionByExp(MyExpMainEntity myExp){
		MyExpProcessEntity myExpProcess = myExpProcessService.getByExpID(myExp.getMyExpID());
		if(myExpProcess != null){
			return expInstructionsMainService.get(myExpProcess.getExpInstructionID());
		}
		return null;
	}
}
