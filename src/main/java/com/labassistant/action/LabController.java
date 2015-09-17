package com.labassistant.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.labassistant.beans.ExpReagentEntity;
import com.labassistant.common.BaseController;
import com.labassistant.service.ExpReagentService;

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
}
