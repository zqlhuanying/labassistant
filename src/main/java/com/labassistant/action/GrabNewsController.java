package com.labassistant.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.labassistant.common.BaseController;
import com.labassistant.service.GrabNewsService;

/**
 * 抓取新闻
 * @author zql
 * @date 2015/09/28
 */
@Controller
@RequestMapping(value = "/grabNews")
public class GrabNewsController extends BaseController {

	@Autowired
	private GrabNewsService grabNewsService;
	
	@RequestMapping(value = "")
	@ResponseBody
	public Map<String, Object> grabNews(HttpServletRequest request){
		setErrorMsg(request, "获取新闻列表失败");
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> object = grabNewsService.grabNews();
		map.putAll(retSuccess());
		map.put("data", object);
		return map;
	}
}
