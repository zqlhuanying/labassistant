package com.labassistant.action;

import java.util.List;

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
	public List<ExpCategoryEntity> getExpCategory(HttpServletRequest request){
		setErrorMsg(request, "获取实验一级分类出错");
		return expCategoryService.getAllExpCategory();
	}
	
	@RequestMapping(value = "/getSubCategoryByPID")
	@ResponseBody
	public List<ExpSubCategoryEntity> getSubCategoryByPID(HttpServletRequest request,String expCategoryID){
		setErrorMsg(request, "获取实验二级分类出错");

		return expSubCategoryService.getSubCategoryByParentID(expCategoryID);
	}
}
