package com.labassistant.context;

import javax.servlet.http.HttpServletRequest;

public class MySystemContext {
	
	private static ThreadLocal<HttpServletRequest> myRequest = new ThreadLocal<HttpServletRequest>();

	public static HttpServletRequest getMyRequest() {
		return myRequest.get();
	}

	public static void setMyRequest(HttpServletRequest request) {
		myRequest.set(request);
	}
	
}
