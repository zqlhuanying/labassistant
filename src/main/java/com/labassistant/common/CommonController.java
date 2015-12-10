package com.labassistant.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.labassistant.exception.MyRuntimeException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.labassistant.constants.AppConfig;

/**
 * 
 * @author zql
 * @date 2015/09/08
 */
@Controller
@RequestMapping(value = "/common")
public class CommonController extends BaseController {

	/**
	 * 捕获错误信息
     * 显示顺序：1、自定义异常信息 2、AppConfig.REQUEST_ERROR_MSG_KEY 3、系统异常信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/error")
	@ResponseBody
	public Map<String, String> error(HttpServletRequest request){
		Exception e = (Exception) request.getAttribute("ex");
		logger.error("request error", e);
        Map<String, String> map = new HashMap<String, String>();
        String errorMsg = (String) request.getAttribute(AppConfig.REQUEST_ERROR_MSG_KEY);
        if(e.getClass().equals(MyRuntimeException.class)){
            errorMsg = e.getMessage();
        }
        if (StringUtils.isBlank(errorMsg)) {
            //errorMsg = "操作失败";
        	errorMsg = e.getMessage();
        }
        map.put("code", "0");
        map.put("msg", errorMsg);
        return map;
	}
}
