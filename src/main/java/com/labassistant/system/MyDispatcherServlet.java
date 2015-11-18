package com.labassistant.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.DispatcherServlet;

import com.labassistant.context.MySystemContext;

/**
 * 自定义spring mvc DispatcherServlet，功能扩展
 * @author zql
 * @date 2015/11/18
 */
public class MyDispatcherServlet extends DispatcherServlet {

	private static final long serialVersionUID = -4611856235591025570L;
	
	@Override
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//将request放到MySystemContext，方便在javaBean中调用
		MySystemContext.setMyRequest(request);
		super.doService(request, response);
	}

}
