package com.labassistant.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.labassistant.common.BaseController;
import com.labassistant.scripts.ImportCity;

@Controller
@RequestMapping(value = "/script")
public class ScriptController extends BaseController {

	@Autowired
	private ImportCity importCity;
	
	@RequestMapping(value = "city")
	@ResponseBody
	public Map<String, Object> importCity(HttpServletRequest request){
		setErrorMsg(request, "导入省份城市数据失败");
		Map<String, Object> map = new HashMap<String, Object>();
		//String path = request.getSession().getServletContext().getRealPath("/resource") + "/" + "city.json";
		String path = getClass().getResource("/resource").getPath() + "city.json";
		importCity.importCity(path);
		map.putAll(retSuccess());
		map.put("data", "");
		return map;
	}
}
