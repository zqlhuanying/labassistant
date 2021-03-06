package com.labassistant.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.labassistant.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.labassistant.beans.ExpInstructionEntity;
import com.labassistant.beans.ExpReviewDetailEntity;
import com.labassistant.beans.ExpReviewOptEntity;
import com.labassistant.beans.ExpStepEntity;
import com.labassistant.beans.ExpReagentEntity;
import com.labassistant.beans.ExpReviewEntity;
import com.labassistant.beans.MyExpInstructionEntity;
import com.labassistant.beans.MyExpEntity;
import com.labassistant.beans.MyExpPlanEntity;
import com.labassistant.beans.MyExpProcessEntity;
import com.labassistant.beans.ReagentEntity;
import com.labassistant.beans.ReagentLevelOneEntity;
import com.labassistant.beans.ReagentLevelTwoEntity;
import com.labassistant.beans.SupplierEntity;
import com.labassistant.common.BaseController;
import com.labassistant.constants.LabConstant;
import com.labassistant.constants.ReturnJson;
import com.labassistant.service.common.ReagentLevelOneService;
import com.labassistant.service.common.ReagentLevelTwoService;
import com.labassistant.service.common.ReagentService;
import com.labassistant.service.common.SupplierService;
import com.labassistant.service.exp.ExpConsumableService;
import com.labassistant.service.exp.ExpEquipmentService;
import com.labassistant.service.exp.ExpInstructionsMainService;
import com.labassistant.service.exp.ExpProcessService;
import com.labassistant.service.exp.ExpReagentService;
import com.labassistant.service.exp.ExpReviewDetailService;
import com.labassistant.service.exp.ExpReviewOptService;
import com.labassistant.service.exp.ExpReviewService;
import com.labassistant.service.myexp.MyExpInstructionService;
import com.labassistant.service.myexp.MyExpMainService;
import com.labassistant.service.myexp.MyExpPlanService;
import com.labassistant.service.myexp.MyExpProcessService;
import com.labassistant.utils.CommonUtil;
import com.labassistant.utils.DateUtil;

import static com.labassistant.utils.CommonUtil.saveNull;

/**
 * 实验控制类
 * @author zql
 * @date 2015/09/15
 */
@Controller
@RequestMapping(value = "/lab")
public class LabController extends BaseController {

    @Autowired
    private SysUserService sysUserService;
	@Autowired
	private SupplierService supplierService;
	@Autowired
	private ReagentService reagentService;
	@Autowired
	private ReagentLevelOneService reagentLevelOneService;
	@Autowired
	private ReagentLevelTwoService reagentLevelTwoService;
	@Autowired
	private ExpInstructionsMainService expInstructionsMainService;
	@Autowired
	private ExpProcessService expProcessService;
	@Autowired
	private ExpReagentService expReagentService;
	@Autowired
	private ExpConsumableService expConsumableService;
	@Autowired
	private ExpEquipmentService expEquipmentService;
	@Autowired
	private ExpReviewService expReviewService;
	@Autowired
	private ExpReviewDetailService expReviewDetailService;
	@Autowired
	private ExpReviewOptService expReviewOptService;
	@Autowired
	private MyExpMainService myExpMainService;
	@Autowired
	private MyExpInstructionService myExpInstructionService;
	@Autowired
	private MyExpPlanService myExpPlanService;
	@Autowired
	private MyExpProcessService myExpProcessService;

	@RequestMapping(value = "/allReagents")
	@ResponseBody
	public Map<String, Object> getAllReagents(HttpServletRequest request, String expInstructionID){
		setErrorMsg(request, "获取实验所有试剂出错");
		Map<String, Object> map = new HashMap<String, Object>();
		List<ExpReagentEntity> lists = new ArrayList<ExpReagentEntity>();
		lists = expReagentService.getExpReagentLists(expInstructionID);
		map.putAll(retSuccess());
		map.put("data", lists);
		return map;
	}

	/*@RequestMapping(value = "/allConsums")
	@ResponseBody
	public Map<String, Object> getAllConsums(HttpServletRequest request, String myExpID){
		setErrorMsg(request, "获取实验所有耗材出错");
		Map<String, Object> map = new HashMap<String, Object>();
		List<ExpConsumableEntity> lists = new ArrayList<ExpConsumableEntity>();
		MyExpEntity myExp = myExpMainService.getByExpID(myExpID);
		if(myExp != null){
			lists = expConsumableService.getExpConsumableLists(myExp.getExpInstructionID());
		}
		map.putAll(retSuccess());
		map.put("data", lists);
		return map;
	}*/

	@RequestMapping(value = "/perAmount")
	@ResponseBody
	public Map<String, Object> getPerAmount(HttpServletRequest request, String expInstructionID){
		setErrorMsg(request, "获取实验试剂及对应的用量出错");
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> object = new ArrayList<Object>();
		object = expReagentService.getExpReagentAndAmount(expInstructionID);
		map.putAll(retSuccess());
		map.put("data", object);
		return map;
	}

	@RequestMapping(value = "/reagentAndSupplier")
	@ResponseBody
	public Map<String, Object> getReagentAndSupplier(HttpServletRequest request, String expInstructionID){
		setErrorMsg(request, "获取实验试剂及对应的提供商出错");
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> object = new ArrayList<Object>();
		object = expReagentService.getExpReagentAndSupplier(expInstructionID);
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
		List<MyExpEntity> myExpMainLists = myExpMainService.getComplete(userID);
		for(MyExpEntity myExpMainList : myExpMainLists){
			ExpInstructionEntity instruction = expInstructionsMainService.get(myExpMainList.getExpInstructionID());
			if(instruction != null){
				Map<String, String> innerMap = new HashMap<String, String>();
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
		List<MyExpEntity> myExpMainLists = myExpMainService.getDoing(userID);
		for(MyExpEntity myExpMainList : myExpMainLists){
			ExpInstructionEntity instruction = expInstructionsMainService.get(myExpMainList.getExpInstructionID());
			if(instruction != null){
				Map<String, String> innerMap = new HashMap<String, String>();
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
			ExpInstructionEntity expInstruction = expInstructionsMainService.get(expInstructionID);
			List<ExpStepEntity> lists = expProcessService.getProcessLists(expInstructionID);
			if(lists != null && expInstruction != null){
				for(ExpStepEntity list : lists){
					Map<String, String> stepMap = new LinkedHashMap<String, String>();
					stepMap.put("stepNum", String.valueOf(list.getStepNum()));
					stepMap.put("stepDesc", list.getExpStepDesc());
					steps.add(stepMap);
				}
				innerMap.put("experimentName", expInstruction.getExperimentName());
				innerMap.put("steps", steps);
			}
		}
		map.putAll(retSuccess());
		map.put("data", innerMap);
		return map;
	}

	@RequestMapping(value = "/expReagentDetail")
	@ResponseBody
	public Map<String, Object> expReagentDetail(HttpServletRequest request, String expInstructionID, String reagentID){
		setErrorMsg(request, "获取试剂详细信息出错");
		Map<String, Object>  map = new HashMap<String, Object>();
		Map<String, Object> innerMap = new HashMap<String, Object>();

		ReagentEntity reagent = reagentService.get(reagentID);
		if(reagent != null){
			ReagentLevelOneEntity reagentLevelOneEntity = reagentLevelOneService.get(reagent.getLevelOneSortID());
			ReagentLevelTwoEntity reagentLevelTwoEntity = reagentLevelTwoService.get(reagent.getLevelTwoSortID());
			SupplierEntity supplier = supplierService.get(expReagentService.getSuggestionSupplier(expInstructionID, reagentID));

			innerMap.put("reagentName", saveNull(reagent.getReagentName()));
			innerMap.put("chemicalName", saveNull(reagent.getChemicalName()));
			innerMap.put("supplier", supplier == null ?
									"" : supplier.getSupplierName());
			innerMap.put("levelOne", reagentLevelOneEntity == null ?
									"" :  reagentLevelOneEntity.getLevelOneSortName());
			innerMap.put("levelTwo", reagentLevelTwoEntity == null ?
									"" : reagentLevelTwoEntity.getLevelTwoSortName());
			innerMap.put("originPlace", saveNull(reagent.getOriginPlace()));
			innerMap.put("productNo", saveNull(reagent.getProductNo()));
			innerMap.put("agents", saveNull(reagent.getAgents()));
			innerMap.put("specification", saveNull(reagent.getSpecification()));
			innerMap.put("casNo", saveNull(reagent.getCasNo()));
			innerMap.put("memo", saveNull(reagent.getMemo()));
		}
		CommonUtil.unionMap(ReturnJson.EXPREAGENTDETAILJSON, innerMap);
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
		ExpInstructionEntity expInstruction = expInstructionsMainService.get(expInstructionID);
		List<ExpStepEntity> expProcesses = expProcessService.getProcessLists(expInstructionID);
		//List<ExpReagentEntity> expReagents = expReagentService.getExpReagentLists(expInstructionID);
		//List<ExpEquipmentEntity> expEquipments = expEquipmentService.getExpEquipmentLists(expInstructionID);
		//boolean isDownload = expInstructionsMainService.isDownload(userID, expInstructionID);
		if(expInstruction != null){
			innerMap.put("expInstructionID", expInstruction.getExpInstructionID());
			innerMap.put("experimentName", expInstruction.getExperimentName());
			if(expInstruction.getProvideUser() != null
					&& !"".equals(expInstruction.getProvideUser())) innerMap.put("provideUser", expInstruction.getProvideUser());
			if(expInstruction.getSupplierName() != null
					&& !"".equals(expInstruction.getSupplierName())) innerMap.put("supplierName", expInstruction.getSupplierName());
			innerMap.put("productNum", expInstruction.getProductNum());

			// 实验简介
			innerMap.put("experimentDesc", expInstruction.getExperimentDesc());
			// 实验原理
			innerMap.put("experimentTheory", expInstruction.getExperimentTheory());
			// 实验准备
			// 实验试剂
			innerMap.put("expReagents", expReagentService.getExpReagentAndSupplier(expInstructionID));
			// 实验耗材
			innerMap.put("expConsumables", expConsumableService.getExpConsumableAndSupplier(expInstructionID));
			// 实验设备
			innerMap.put("expEquipments", expEquipmentService.getExpEquipmentAndSupplier(expInstructionID));

			// 实验步骤
			innerMap.put("steps", expProcesses);

			// 实验评论
			innerMap.put("reviews", expReviewService.getReviewList(expInstructionID, null, 10));
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
		if(expInstructionsMainService.isAllowDownload(userID, expInstructionID)){
			ExpInstructionEntity expInstruction = expInstructionsMainService.get(expInstructionID);
			Map<String, Object> innerMap = expInstructionsMainService.downloadInstruction(expInstructionID);
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
		} else {
			map.put("code", "0");
			map.put("msg", "不允许下载");
		}
		return map;
	}

	@RequestMapping(value = "/perProcessMemo")
	@ResponseBody
	// 虽然设置了是整个实体类，但主要的是myExpID，expStepID，processMemo三个字段
	public Map<String, Object> perProcessMemo(HttpServletRequest request, MyExpProcessEntity myExpProcess){
		setErrorMsg(request, "设置实验步骤备注失败");
		Map<String, Object>  map = new HashMap<String, Object>();
		myExpProcessService.perProcessMemo(myExpProcess);
		map.putAll(retSuccess());
		map.put("data", "");
		return map;
	}

	@RequestMapping(value = "/getPerProcessMemo")
	@ResponseBody
	public Map<String, Object> getPerProcessMemo(HttpServletRequest request, String myExpID, String expStepID){
		setErrorMsg(request, "获取实验步骤备注失败");
		Map<String, Object>  map = new HashMap<String, Object>();
		Map<String, String> innerMap = new HashMap<String, String>();
		MyExpProcessEntity myExpProcess = myExpProcessService.getPerProcessMemo(myExpID, expStepID);
		if(myExpProcess != null){
			innerMap.put("expStepID", myExpProcess.getExpStepID());
			innerMap.put("processMemo", myExpProcess.getProcessMemo());
		}
		map.putAll(retSuccess());
		map.put("data", innerMap);
		return map;
	}

	@RequestMapping(value = "/reviewList")
	@ResponseBody
	public Map<String, Object> getReviewList(HttpServletRequest request, String expInstructionID, String lastExpReviewID, int pageSize){
		setErrorMsg(request, "获取评论列表失败");
		Map<String, Object>  map = new HashMap<String, Object>();

		List<Object> object = expReviewService.getReviewList(expInstructionID, lastExpReviewID, pageSize);

		map.putAll(retSuccess());
		map.put("data", object);
		return map;
	}

	@RequestMapping(value = "/reviewDetail")
	@ResponseBody
	public Map<String, Object> getReviewDetail(HttpServletRequest request, String expReviewID){
		setErrorMsg(request, "获取评论详细信息失败");
		Map<String, Object>  map = new HashMap<String, Object>();

		Map<String, Object> innerMap = expReviewService.getReviewDetail(expReviewID);

		CommonUtil.unionMap(ReturnJson.REVIEWDETAILJSON, innerMap);
		map.putAll(retSuccess());
		map.put("data", innerMap);
		return map;
	}

	@RequestMapping(value = "/reviewOptional", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> reviewOptional(HttpServletRequest request, String expInstructionJson, String expInstructionID, String userID, int allowDownload){
		setErrorMsg(request, "获取评论项失败");
		Map<String, Object>  map = new HashMap<String, Object>();

		List<Object> expReviewOpts = expReviewOptService.expReviewOpt(expInstructionJson, expInstructionID, userID, allowDownload);

		map.putAll(retSuccess());
		map.put("data", expReviewOpts);
		return map;
	}

	@RequestMapping(value = "/responseReview", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> responseReview(HttpServletRequest request, String reviewJson){
		setErrorMsg(request, "对实验进行评论失败");
		Map<String, Object>  map = new HashMap<String, Object>();
		expReviewService.responseReview(reviewJson);
		map.putAll(retSuccess());
		map.put("data", "");
		return map;
	}
	
	@RequestMapping(value = "/deleteReview")
	@ResponseBody
	// 虽然设置了是整个实体类，但主要的是expReviewID字段
	public Map<String, Object> deleteReview(HttpServletRequest request, ExpReviewEntity expReview){
		setErrorMsg(request, "删除评论失败");
		Map<String, Object>  map = new HashMap<String, Object>();
		expReviewService.deleteEntity(expReview);
		List<ExpReviewDetailEntity> expReviewDetails = expReviewDetailService.getExpReviewDetails(expReview.getExpReviewID());
		expReviewDetailService.deleteAll(expReviewDetails);
		map.putAll(retSuccess());
		map.put("data", "");
		return map;
	}
	
	@RequestMapping(value = "/isReviewed")
	@ResponseBody
	public Map<String, Object> isReviewed(HttpServletRequest request, String userID, String myExpID){
		setErrorMsg(request, "查询是否已评论失败");
		Map<String, Object>  map = new HashMap<String, Object>();
		
		Map<String, String> innerMap = new HashMap<String, String>();
		MyExpEntity myExp = myExpMainService.getByExpID(myExpID);
		boolean isReviewed = false;
		if(myExp != null){
			isReviewed = expReviewService.isReviewed(userID, myExp.getExpInstructionID());
		}
		 
		innerMap.put("isReviewed", isReviewed ? "1" : "0");
		
		map.putAll(retSuccess());
		map.put("data", innerMap);
		return map;
	}
	
	@RequestMapping(value = "/reviewAgree")
	@ResponseBody
	public Map<String, Object> reviewAgree(HttpServletRequest request, String expReviewID){
		setErrorMsg(request, "点赞同失败");
		Map<String, Object>  map = new HashMap<String, Object>();
		Map<String, Integer>  innerMap = new HashMap<String, Integer>();
		int agreeCount = expReviewService.agreeOrNot(expReviewID, true);
		innerMap.put("agreeCount", agreeCount);
		map.putAll(retSuccess());
		map.put("data", innerMap);
		return map;
	}
	
	@RequestMapping(value = "/reviewDisagree")
	@ResponseBody
	public Map<String, Object> reviewDisagree(HttpServletRequest request, String expReviewID){
		setErrorMsg(request, "点不赞同失败");
		Map<String, Object>  map = new HashMap<String, Object>();
		Map<String, Integer>  innerMap = new HashMap<String, Integer>();
		int disagreeCount = expReviewService.agreeOrNot(expReviewID, false);
		innerMap.put("disagreeCount", disagreeCount);
		map.putAll(retSuccess());
		map.put("data", innerMap);
		return map;
	}
	
	@RequestMapping(value = "/getMyExpPlan")
	@ResponseBody
	public Map<String, Object> getMyExpPlan(HttpServletRequest request, String userID, String date){
		setErrorMsg(request, "获取实验计划失败");
		Map<String, Object>  map = new HashMap<String, Object>();
		List<Object> lists = new ArrayList<Object>();
		List<MyExpPlanEntity> myExpPlans = myExpPlanService.getPlans(userID, DateUtil.str2Date(LabConstant.DATEFORMAT, date));
		if(myExpPlans != null){
			for(MyExpPlanEntity myExpPlan : myExpPlans){
				Map<String, String>  innerMap = new HashMap<String, String>();
				innerMap.put("myExpPlanID", myExpPlan.getMyExpPlanID());
				innerMap.put("expInstructionID", myExpPlan.getExpInstructionID());
				innerMap.put("experimentName", myExpPlan.getExperimentName());
				lists.add(innerMap);
			}
		}
		map.putAll(retSuccess());
		map.put("data", lists);
		return map;
	}
	
	@RequestMapping(value = "/setMyExpPlan")
	@ResponseBody
	// 虽然设置了是整个实体类，但主要的是userID，expInstructionID[,experimentName]等字段,date
	public Map<String, Object> setMyExpPlan(HttpServletRequest request, MyExpPlanEntity myExpPlan, String date){
		setErrorMsg(request, "设置实验计划失败");
		Map<String, Object>  map = new HashMap<String, Object>();
		if(StringUtils.isBlank(myExpPlan.getExperimentName())){
			ExpInstructionEntity expInstruction = expInstructionsMainService.get(myExpPlan.getExpInstructionID());
			myExpPlan.setExperimentName(expInstruction.getExperimentName());
		}
		Date planDate = DateUtil.str2Date(LabConstant.DATEFORMAT, date);
		myExpPlan.setPlanDate(planDate);
		myExpPlanService.setPlan(myExpPlan);
		map.putAll(retSuccess());
		map.put("data", "");
		return map;
	}
	
	@RequestMapping(value = "/deleteMyExpPlan")
	@ResponseBody
	// 虽然设置了是整个实体类，但主要的是myExpPlanID字段
	public Map<String, Object> deleteMyExpPlan(HttpServletRequest request, MyExpPlanEntity myExpPlan){
		setErrorMsg(request, "删除实验计划失败");
		Map<String, Object>  map = new HashMap<String, Object>();
		myExpPlanService.deleteEntity(myExpPlan);
		map.putAll(retSuccess());
		map.put("data", "");
		return map;
	}
	
	/**
	 * add by jimmie
	 * 获取指定实验子类的说明书列表
	 * @param request
	 * @param expInstructionID
	 * @return
	 */
	@RequestMapping(value = "/getInstructionsBySubCategoryID")
	@ResponseBody
	public Map<String, Object> getInstructionsBySubCategoryID(HttpServletRequest request, String userID, String expSubCategoryID){		
		setErrorMsg(request, "获取实验列表出错");
		Map<String, Object>  map = new HashMap<String, Object>();
        List<Object> l = new ArrayList<Object>();

		List<ExpInstructionEntity> lists = expInstructionsMainService.getInstructionsBySubCategoryID(userID, expSubCategoryID);

        for(ExpInstructionEntity expInstruction : lists){
            Map<String, Object>  innerMap = new HashMap<String, Object>();
            String providerID = expInstruction.getProvideUser();
            String supplierName = expInstruction.getSupplierName();
            StringBuffer sb = new StringBuffer();
            if(StringUtils.isNotBlank(providerID)){
                sb.append(sysUserService.get(providerID).getNickName());
                if(StringUtils.isNotBlank(supplierName)) sb.append("/");
            }
            if(StringUtils.isNotBlank(supplierName)){
                sb.append(supplierName);
            }


            innerMap.put("expInstructionID", expInstruction.getExpInstructionID());
            innerMap.put("downloadCount", expInstruction.getDownloadCount());
            innerMap.put("reviewCount", expInstruction.getReviewCount());
            innerMap.put("experimentName", expInstruction.getExperimentName());
            innerMap.put("provideUser", sb.toString());
            l.add(innerMap);
        }
		map.putAll(retSuccess());
		map.put("data", l);
		
		return map; 
	}
	
	/**
	 * add by jimmie
	 * 根据输入条件筛选实验说明书
	 * @param request
	 * @param filterStr
	 * @return
	 */
	@RequestMapping(value = "/getInstructionsByFilter")
	@ResponseBody
	public Map<String, Object> getInstructionsByFilter(HttpServletRequest request, String userID, String filterStr){
		setErrorMsg(request, "获取实验列表出错");
		Map<String, Object>  map = new HashMap<String, Object>();				
		
		List<ExpInstructionEntity> lists = expInstructionsMainService.getInstructionsByFilter(userID, filterStr);
		
		map.putAll(retSuccess());
		map.put("data", lists);
		return map; 
	}
}
