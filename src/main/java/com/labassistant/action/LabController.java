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
import com.labassistant.beans.MyExpInstructionEntity;
import com.labassistant.beans.MyExpMainEntity;
import com.labassistant.beans.MyExpProcessEntity;
import com.labassistant.common.BaseController;
import com.labassistant.constants.LabConstant;
import com.labassistant.service.exp.ExpConsumableService;
import com.labassistant.service.exp.ExpEquipmentService;
import com.labassistant.service.exp.ExpInstructionsMainService;
import com.labassistant.service.exp.ExpProcessService;
import com.labassistant.service.exp.ExpReagentService;
import com.labassistant.service.myexp.MyExpInstructionService;
import com.labassistant.service.myexp.MyExpMainService;
import com.labassistant.service.myexp.MyExpProcessService;
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
	private MyExpInstructionService myExpInstructionService;
	@Autowired
	private MyExpProcessService myExpProcessService;
	@Autowired
	private ExpInstructionsMainService expInstructionsMainService;
	@Autowired
	private ExpProcessService expProcessService;
	@Autowired
	private ExpConsumableService expConsumableService;
	@Autowired
	private ExpEquipmentService expEquipmentService;
	
	@RequestMapping(value = "/allReagents")
	@ResponseBody
	public Map<String, Object> getAllReagents(HttpServletRequest request, String myExpID){
		setErrorMsg(request, "获取实验所有试剂出错");
		Map<String, Object> map = new HashMap<String, Object>();
		List<ExpReagentEntity> lists = new ArrayList<ExpReagentEntity>();
		MyExpMainEntity myExp = myExpMainService.getByExpID(myExpID);
		if(myExp != null){
			lists = expReagentService.getExpReagentLists(myExp.getExpInstructionID());
		}
		map.putAll(retSuccess());
		map.put("data", lists);
		return map;
	}
	
	@RequestMapping(value = "/perAmount")
	@ResponseBody
	public Map<String, Object> getPerAmount(HttpServletRequest request, String myExpID){
		setErrorMsg(request, "获取实验试剂及对应的用量出错");
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> object = new ArrayList<Object>();
		MyExpMainEntity myExp = myExpMainService.getByExpID(myExpID);
		if(myExp != null){
			object = expReagentService.getExpReagentAndAmount(myExp.getExpInstructionID());
		}
		map.putAll(retSuccess());
		map.put("data", object);
		return map;
	}
	
	@RequestMapping(value = "/reagentAndSupplier")
	@ResponseBody
	public Map<String, Object> getReagentAndSupplier(HttpServletRequest request, String myExpID){
		setErrorMsg(request, "获取实验试剂及对应的提供商出错");
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> object = new ArrayList<Object>();
		MyExpMainEntity myExp = myExpMainService.getByExpID(myExpID);
		if(myExp != null){
			object = expReagentService.getExpReagentAndSupplierName(myExp.getExpInstructionID());
		}
		map.putAll(retSuccess());
		map.put("data", object);
		return map;
	}
	
	@RequestMapping(value = "/getComplete")
	@ResponseBody
	public Map<String, Object> getComplete(HttpServletRequest request, String userID){
		setErrorMsg(request, "获取已完成实验列表出错");
		Map<String, Object>  map = new HashMap<String, Object>();
		List<Object> object = new ArrayList<Object>();
		Map<String, String> innerMap = new HashMap<String, String>();
		List<MyExpMainEntity> myExpMainLists = myExpMainService.getComplete(userID);
		for(MyExpMainEntity myExpMainList : myExpMainLists){
			ExpInstructionsMainEntity instruction = expInstructionsMainService.get(myExpMainList.getExpInstructionID());
			if(instruction != null){
				innerMap.put("myExpID", myExpMainList.getMyExpID());
				innerMap.put("expInstructionID", instruction.getExpInstructionID());
				innerMap.put("experimentName", instruction.getExperimentName());
				innerMap.put("expState", String.valueOf((myExpMainList.getExpState())));
				object.add(innerMap);
			}
		}
		map.putAll(retSuccess());
		map.put("data", object);
		return map;
	}
	
	@RequestMapping(value = "/getDoing")
	@ResponseBody
	public Map<String, Object> getDoing(HttpServletRequest request, String userID){
		setErrorMsg(request, "获取正在进行的实验列表出错");
		Map<String, Object>  map = new HashMap<String, Object>();
		List<Object> object = new ArrayList<Object>();
		Map<String, String> innerMap = new HashMap<String, String>();
		List<MyExpMainEntity> myExpMainLists = myExpMainService.getDoing(userID);
		for(MyExpMainEntity myExpMainList : myExpMainLists){
			ExpInstructionsMainEntity instruction = expInstructionsMainService.get(myExpMainList.getExpInstructionID());
			if(instruction != null){
				innerMap.put("myExpID", myExpMainList.getMyExpID());
				innerMap.put("expInstructionID", instruction.getExpInstructionID());
				innerMap.put("experimentName", instruction.getExperimentName());
				innerMap.put("expState", String.valueOf((myExpMainList.getExpState())));
				object.add(innerMap);
			}
		}
		map.putAll(retSuccess());
		map.put("data", object);
		return map;
	}
	
	@RequestMapping(value = "/getAllProcessExceptComplete")
	@ResponseBody
	public Map<String, Object> getAllProcessExceptComplete(HttpServletRequest request, String userID, String myExpID, String expInstructionID, String expState){
		Map<String, Object>  map = new HashMap<String, Object>();
		Map<String, Object> innerMap = new HashMap<String, Object>();
		List<Object> steps = new ArrayList<Object>();
		if(!LabConstant.COMPLETED.equals(expState)){
			setErrorMsg(request, "获取实验下所有步骤出错");			
			ExpInstructionsMainEntity expInStruction = expInstructionsMainService.get(expInstructionID);
			List<ExpProcessEntity> lists = expProcessService.getProcessLists(expInstructionID);
			if(lists != null && expInStruction != null){
				for(ExpProcessEntity list : lists){
					Map<String, String> stepMap = new LinkedHashMap<String, String>();
					stepMap.put("stepNum", String.valueOf(list.getStepNum()));
					stepMap.put("stepDesc", list.getExpStepDesc());
					steps.add(stepMap);
				}
				innerMap.put("experimentName", expInStruction.getExperimentName());
				innerMap.put("steps", steps);
			}
		}
		map.putAll(retSuccess());
		map.put("data", innerMap);
		return map;
	}
	
	// todo 结构略有不同
	@RequestMapping(value = "/getHotInstructions")
	@ResponseBody
	public Map<String, Object> getHotInstructions(HttpServletRequest request, String returnLimit){
		setErrorMsg(request, "无法获取热门说明书");
		Map<String, Object>  map = new HashMap<String, Object>();
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
		map.putAll(retSuccess());
		map.put("data", object);
		return map;
	}
	
	@RequestMapping(value = "/getInstructionDetail")
	@ResponseBody
	public Map<String, Object> getInstructionDetail(HttpServletRequest request,String userID, String expInstructionID){
		setErrorMsg(request, "获取说明书详细信息出错");
		Map<String, Object>  map = new HashMap<String, Object>();
		Map<String, Object> innerMap = new LinkedHashMap<String, Object>();
		//List<Object> object = new ArrayList<Object>();
		ExpInstructionsMainEntity expInstruction = expInstructionsMainService.get(expInstructionID);
		boolean isDownload = expInstructionsMainService.isDownload(userID, expInstructionID);
		if(expInstruction != null){
			innerMap.put("expInstructionID", expInstruction.getExpInstructionID());
			innerMap.put("experimentName", expInstruction.getExperimentName());
			if(expInstruction.getProvideUser() != null 
					&& !"".equals(expInstruction.getProvideUser())) innerMap.put("provideUser", expInstruction.getProvideUser());
			if(expInstruction.getSupplierName() != null
					&& !"".equals(expInstruction.getSupplierName())) innerMap.put("supplierName", expInstruction.getSupplierName());
			innerMap.put("productNum", expInstruction.getProductNum());
			if(isDownload) innerMap.put("instructState", "已下载");
			else innerMap.put("instructState", "未下载");
			//object.add(innerMap);
		}
		map.putAll(retSuccess());
		map.put("data", innerMap);
		return map;
	}
	
	/**
	 * 下载实验说明书便是将说明书相关的表以json的格式传递到终端，并保存到终端
	 * 同时更新说明书表和我的说明书表
	 * @param request
	 * @param expInstructionID
	 * @return
	 */
	@RequestMapping(value = "/downloadInstruction")
	@ResponseBody
	public Map<String, Object> downloadInstruction(HttpServletRequest request, String userID, String expInstructionID){
		setErrorMsg(request, "下载说明书出错");
		Map<String, Object>  map = new HashMap<String, Object>();
		//List<Object> object = new ArrayList<Object>();
		Map<String, Object> innerMap = new LinkedHashMap<String, Object>();
		// 封装实验主表的数据
		ExpInstructionsMainEntity expInstruction = expInstructionsMainService.get(expInstructionID);
		innerMap.put("expInstructionMain", expInstruction);
		// 封装实验步骤表的数据
		innerMap.put("expProcess", expProcessService.getProcessLists(expInstructionID));
		// 封装实验试剂表的数据
		innerMap.put("expReagent", expReagentService.getExpReagentLists(expInstructionID));
		// 封装实验耗材表的数据
		innerMap.put("expConsumable", expConsumableService.getExpConsumableLists(expInstructionID));
		// 封装实验设备表的数据
		innerMap.put("expEquipment", expEquipmentService.getExpEquipmentLists(expInstructionID));
		//object.add(innerMap);
		map.putAll(retSuccess());
		map.put("data", innerMap);
		
		// 更新说明书表
		expInstruction.setDownloadCount(expInstruction.getDownloadCount() + 1);
		expInstructionsMainService.update(expInstruction);
		// 更新我的说明书表
		MyExpInstructionEntity myExpInstruction = new MyExpInstructionEntity();
		myExpInstruction.setExpInstructionID(expInstructionID);
		myExpInstruction.setUserID(userID);
		myExpInstruction.setDownloadTime(DateUtil.str2Date(DateUtil.formatDate(new Date())));
		myExpInstructionService.save(myExpInstruction);
		return map;
	}
	
	/**
	 * 获取所做实验所对应的说明书信息
	 * @param myExp
	 * @return
	 */
	/*private ExpInstructionsMainEntity getInstructionByExp(MyExpMainEntity myExp){
		MyExpMainEntity myExpProcess = myExpMainService.getByExpID(myExp.getMyExpID());
		if(myExpProcess != null){
			return expInstructionsMainService.get(myExpProcess.getExpInstructionID());
		}
		return null;
	}*/
}
