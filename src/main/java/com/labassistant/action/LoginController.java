package com.labassistant.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.labassistant.beans.SysUserEntity;
import com.labassistant.common.BaseController;
import com.labassistant.exception.MyRuntimeException;
import com.labassistant.service.SysUserService;

import java.util.Map;

/**
 * 用户登录
 * @author zql
 * @date 2015/09/07
 */
@Controller
public class LoginController extends BaseController {

	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> login(HttpServletRequest request, SysUserEntity user){
		setErrorMsg(request, "用户名或密码错误");
		SysUserEntity sysUser = sysUserService.login(user.getUsername(), user.getPwd());
		// 查无此人
		if(sysUser == null){
			throw new MyRuntimeException("用户名或密码错误");
		}
		logger.info("查询到登录用户" + sysUser.getUsername());
		return retSuccess();
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> register(HttpServletRequest request, SysUserEntity user){
		setErrorMsg(request, "注册失败");
		sysUserService.register(user);
		return retSuccess();
	}
}
