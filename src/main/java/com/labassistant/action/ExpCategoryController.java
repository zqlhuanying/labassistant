package com.labassistant.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.labassistant.beans.ExpCategoryEntity;
import com.labassistant.beans.ExpSubCategoryEntity;
import com.labassistant.common.BaseController;
import com.labassistant.service.ExpCategoryService;
import com.labassistant.service.ExpSubCategoryService;


@Controller
@RequestMapping(value = "/expCategory")
public class ExpCategoryController  extends BaseController {
	
	@Autowired
	private ExpCategoryService expCategoryService;
	
	@Autowired
	private ExpSubCategoryService expSubCategoryService;	
	
	
	@RequestMapping(value = "/allExpCategory")
	@ResponseBody
	public Map<String, Object> getExpCategory(HttpServletRequest request){
		setErrorMsg(request, "获取实验一级分类出错");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.putAll(retSuccess());
		map.put("data", expCategoryService.getAllExpCategory());
		
		return map;
	}
	
	@RequestMapping(value = "/getSubCategoryByPID")
	@ResponseBody
	public Map<String, Object> getSubCategoryByPID(HttpServletRequest request,String expCategoryID){
		setErrorMsg(request, "获取实验二级分类出错");

		Map<String, Object> map = new HashMap<String, Object>();
		
		map.putAll(retSuccess());
		map.put("data", expSubCategoryService.getSubCategoryByParentID(expCategoryID));
		
		return map;
	}
}
