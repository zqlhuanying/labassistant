package com.labassistant.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.labassistant.beans.ExpInstructionsMainEntity;
import com.labassistant.beans.ExpProcessEntity;
import com.labassistant.beans.ExpReagentEntity;
import com.labassistant.beans.MyExpMainEntity;
import com.labassistant.beans.MyExpProcessEntity;
import com.labassistant.common.BaseController;
import com.labassistant.constants.LabConstant;
import com.labassistant.service.ExpInstructionsMainService;
import com.labassistant.service.ExpProcessService;
import com.labassistant.service.ExpReagentService;
import com.labassistant.service.MyExpMainService;
import com.labassistant.service.MyExpProcessService;
import com.labassistant.utils.DateUtil;

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
	@Autowired
	private ExpProcessService expProcessService;
	
	
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
				innerMap.put("myExpID", myExpMainList.getMyExpID());
				innerMap.put("expInstructionID", instruction.getExpInstructionID());
				innerMap.put("experimentName", instruction.getExperimentName());
				innerMap.put("expState", String.valueOf((myExpMainList.getExpState())));
				object.add(innerMap);
			}
		}
		map.put("completes", object);
		return map;
	}
	
	@RequestMapping(value = "/getDoing")
	@ResponseBody
	public Map<String, List<Object>> getDoing(HttpServletRequest request, String userID){
		setErrorMsg(request, "获取正在进行的实验列表出错");
		Map<String, List<Object>>  map = new HashMap<String, List<Object>>();
		List<Object> object = new ArrayList<Object>();
		Map<String, String> innerMap = new HashMap<String, String>();
		List<MyExpMainEntity> myExpMainLists = myExpMainService.getDoing(userID);
		for(MyExpMainEntity myExpMainList : myExpMainLists){
			ExpInstructionsMainEntity instruction = getInstructionByExp(myExpMainList);
			if(instruction != null){
				innerMap.put("myExpID", myExpMainList.getMyExpID());
				innerMap.put("expInstructionID", instruction.getExpInstructionID());
				innerMap.put("experimentName", instruction.getExperimentName());
				innerMap.put("expState", String.valueOf((myExpMainList.getExpState())));
				object.add(innerMap);
			}
		}
		map.put("Doings", object);
		return map;
	}
	
	@RequestMapping(value = "/getAllProcessExceptComplete")
	@ResponseBody
	public Map<String, List<Object>> getAllProcessExceptComplete(HttpServletRequest request, String userID, String myExpID, String expInstructionID, String expState){
		Map<String, List<Object>>  map = new HashMap<String, List<Object>>();
		if(!LabConstant.COMPLETED.equals(expState)){
			setErrorMsg(request, "获取实验下所有步骤出错");			
			List<Object> object = new ArrayList<Object>();
			Map<String, String> innerMap = new LinkedHashMap<String, String>();
			ExpInstructionsMainEntity expInStruction = expInstructionsMainService.get(expInstructionID);
			List<ExpProcessEntity> lists = expProcessService.getProcessLists(expInstructionID);
			if(lists != null && expInStruction != null){
				innerMap.put("experimentName", expInStruction.getExperimentName());
				for(ExpProcessEntity list : lists){
					innerMap.put(String.valueOf(list.getStepNum()), list.getExpSetpDesc());
				}
				object.add(innerMap);
				map.put("myExp", object);
			}
		}
		return map;
	}
	
	// todo 结构略有不同
	@RequestMapping(value = "/getHotInstructions")
	@ResponseBody
	public List<Object> getHotInstructions(HttpServletRequest request, String returnLimit){
		setErrorMsg(request, "无法获取热门说明书");
		List<Object> object = new ArrayList<Object>();
		if(returnLimit != null && !"".equals(returnLimit)){
			expInstructionsMainService.setReturnLimit(Integer.parseInt(returnLimit));
		}
		List<Map<String, Object>> lists = expInstructionsMainService.getHotInstructions();
		if(lists != null){
			for(Map<String, Object> list : lists){
				Map<String, Object> innerMap = new LinkedHashMap<String, Object>();
				innerMap.put("expInstructionID", list.get("expinstructionid"));
				innerMap.put("experimentName", list.get("experimentname"));
				innerMap.put("downloadCount", list.get("downloadcount"));
				innerMap.put("reviewCount", list.get("reviewcount"));
				innerMap.put("nDaysAgo", String.valueOf(DateUtil.diff((Date)list.get("createdate"), new Date())));
				object.add(innerMap);
			}
		}
		return object;
	}
	
	@RequestMapping(value = "/getInstructionDetail")
	@ResponseBody
	public List<Object> getInstructionDetail(HttpServletRequest request, String expInstructionID){
		setErrorMsg(request, "获取说明书详细信息出错");
		List<Object> object = new ArrayList<Object>();
		ExpInstructionsMainEntity expInstruction = expInstructionsMainService.get(expInstructionID);
		boolean isDownload = expInstructionsMainService.isDownload(expInstructionID);
		if(expInstruction != null){
			Map<String, Object> innerMap = new LinkedHashMap<String, Object>();
			innerMap.put("expInstructionID", expInstruction.getExpInstructionID());
			innerMap.put("experimentName", expInstruction.getExperimentName());
			if(expInstruction.getProvideUser() != null 
					&& !"".equals(expInstruction.getProvideUser())) innerMap.put("provideUser", expInstruction.getProvideUser());
			if(expInstruction.getSupplierName() != null
					&& !"".equals(expInstruction.getSupplierName())) innerMap.put("supplierName", expInstruction.getSupplierName());
			innerMap.put("productNum", expInstruction.getProductNum());
			if(isDownload) innerMap.put("instructState", "已下载");
			else innerMap.put("instructState", "未下载");
			object.add(innerMap);
		}
		return object;
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
