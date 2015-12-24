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

import com.labassistant.beans.ExpCategoryEntity;
import com.labassistant.common.BaseController;
import com.labassistant.service.exp.ExpCategoryService;
import com.labassistant.service.exp.ExpSubCategoryService;



@Controller
@RequestMapping(value = "/expCategory")
public class ExpCategoryController  extends BaseController {
	
	@Autowired
	private ExpCategoryService expCategoryService;
	
	@Autowired
	private ExpSubCategoryService expSubCategoryService;	
	
	
	@RequestMapping(value = "/allExpCategory")
	@ResponseBody
	// For Android
	public Map<String, Object> getExpCategory(HttpServletRequest request){
		setErrorMsg(request, "获取实验一级分类出错");
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.putAll(retSuccess());
		map.put("data", expCategoryService.getAllExpCategory());
		
		return map;
	}
	
	@RequestMapping(value = "/getSubCategoryByPID")
	@ResponseBody
	// For Android
	public Map<String, Object> getSubCategoryByPID(HttpServletRequest request,String expCategoryID){
		setErrorMsg(request, "获取实验二级分类出错");

		Map<String, Object> map = new HashMap<String, Object>();
		
		map.putAll(retSuccess());
		map.put("data", expSubCategoryService.getSubCategoryByParentID(expCategoryID, -1).getRows());
		
		return map;
	}
	
	@RequestMapping(value = "/getAllCategory")
	@ResponseBody
	// For IOS
	public Map<String, Object> getAllCategory(HttpServletRequest request){
		setErrorMsg(request, "获取实验分类出错");
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> object = new ArrayList<Object>();
		
		List<ExpCategoryEntity> expCategories = expCategoryService.getAllExpCategory();
		if(expCategories != null){
			for(ExpCategoryEntity expCategory : expCategories){
				Map<String, Object> innerMap = new HashMap<String, Object>();
				innerMap.put("expCategoryID", expCategory.getExpCategoryID());
				innerMap.put("expCategoryName", expCategory.getExpCategoryName());
				innerMap.put("expSubCategories", expSubCategoryService.getSubCategoryByParentID(expCategory.getExpCategoryID(), 6).getRows() == null ? 
												"" : expSubCategoryService.getSubCategoryByParentID(expCategory.getExpCategoryID(), 6).getRows());
				object.add(innerMap);
			}
		}
		
		map.putAll(retSuccess());
		map.put("data", object);
		return map;
	}
}
