package com.labassistant.action;

import java.util.HashMap;
import java.util.Map;

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

/**
 * 用户登录
 * 
 * @author zql
 * @date 2015/09/07
 */
@Controller
public class LoginController extends BaseController {

	@Autowired
	private SysUserService sysUserService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> login(HttpServletRequest request,
			SysUserEntity user) {
		setErrorMsg(request, "用户名或密码错误");
		Map<String, String> map = new HashMap<String, String>();
		SysUserEntity sysUser = sysUserService.login(user.getNickName(),
				user.getPwd());
		// 查无此人
		if (sysUser == null) {
			throw new MyRuntimeException("用户名或密码错误");
		}
		logger.info("查询到登录用户" + sysUser.getNickName());
		map.putAll(retSuccess());
		map.put("userID", sysUser.getUserID());
		return map;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> register(HttpServletRequest request,
			SysUserEntity user) {
		//setErrorMsg(request, "注册失败");
		sysUserService.register(user);
		return retSuccess();
	}
	
	@RequestMapping(value = "/findPwd", method = RequestMethod.POST)
	@ResponseBody
	// 虽然设置了是整个实体类，只需要eMail字段 
	public Map<String, Object> findPwd(HttpServletRequest request, SysUserEntity user) throws Exception {
		setErrorMsg(request, "发送邮件失败");
		Map<String, Object> map = new HashMap<String, Object>();
		sysUserService.sendFindPwdMail(user);
		map.putAll(retSuccess());
		map.put("data", "");
		return map;
	}
	
	@RequestMapping(value = "/getLost", method = RequestMethod.GET)
	public String getLostView(HttpServletRequest request, String ser) {
		return "/getLost";
	}
	
	@RequestMapping(value = "/getLost", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getLost(HttpServletRequest request, SysUserEntity user, String ser) {
		//setErrorMsg(request, "找回密码失败");
		Map<String, Object> map = new HashMap<String, Object>();
		sysUserService.resetPwd(user, ser);
		map.putAll(retSuccess());
		map.put("data", "");
		return map;
	}
}
