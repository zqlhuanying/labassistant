package com.labassistant.common;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.labassistant.constants.AppConfig;

/**
 * 控制器基类
 * @author zql
 * @date 2015/09/07
 */
public class BaseController {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * 操作成功时返回的信息
	 * @return
	 */
	protected Map<String, String> retSuccess(){
		return retSuccess("操作成功！");
	}
	
	protected Map<String, String> retSuccess(String msg){
		Map<String, String> res = new HashMap<String, String>();
		res.put("code", "1");
		res.put("msg", msg);
		return res;
	}
	
	/**
	 * 设置异常信息，当操作出现异常时，将被org.springframework.web.servlet.handler.SimpleMappingExceptionResolve
	 * （在配置文件中配置）捕获，并被处理
	 * @param request
	 * @param msg
	 */
	protected void setErrorMsg(HttpServletRequest request, String msg){
		request.setAttribute(AppConfig.REQUEST_ERROR_MSG_KEY, msg);
	}
	
	/**
     * 写出数据
     * 
     * @param res 输出的字符串
     * @throws Exception
     */
    protected void write(String res, HttpServletResponse response) throws Exception {
        Writer writer = null;
        try {
            res = (null == res ? "" : res);
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            writer = response.getWriter();
            logger.debug("输出JSON字符串：" + res);
            writer.write(res);
        } catch (IOException e) {
            logger.error("输出JSON字符串异常");
            throw new Exception("write json string error");
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    logger.error("关闭输出流异常,无法关闭会导致内存溢出");
                }
            }
        }
    }
}
