package com.labassistant.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.labassistant.beans.CityEntity;
import com.labassistant.beans.CollegeEntity;
import com.labassistant.beans.EducationEntity;
import com.labassistant.beans.MajorEntity;
import com.labassistant.beans.ProvinceEntity;
import com.labassistant.beans.SysUserEntity;
import com.labassistant.common.BaseController;
import com.labassistant.exception.MyRuntimeException;
import com.labassistant.service.CityService;
import com.labassistant.service.CollegeService;
import com.labassistant.service.EducationService;
import com.labassistant.service.MajorService;
import com.labassistant.service.ProvinceService;
import com.labassistant.service.SysUserService;
import com.labassistant.utils.EncryptUtil;

/**
 * 我的信息
 * @author zql
 * @date 2015/09/29
 */
@Controller
@RequestMapping(value = "myInfo")
public class MyInfoController extends BaseController {
	
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private ProvinceService provinceService;
	@Autowired
	private CityService cityService;
	@Autowired
	private CollegeService collegeService;
	@Autowired
	private MajorService majorService;
	@Autowired
	private EducationService educationService;
	
	@RequestMapping(value = "/basic")
	@ResponseBody
	public Map<String, Object> myBasicInfo(HttpServletRequest request, SysUserEntity user){
		setErrorMsg(request, "获取个人基本信息失败");
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> innerMap = new HashMap<String, Object>();
		SysUserEntity sysUser = sysUserService.get(user.getUserID());
		
		innerMap.put("nickName", sysUser.getNickName());
		innerMap.put("email", sysUser.geteMail());
		innerMap.put("telNo", sysUser.getTelNo());
		ProvinceEntity province = provinceService.get(sysUser.getProvinceID());
		innerMap.put("province", province != null ? province : "");
		CityEntity city = cityService.get(sysUser.getCityID());
		innerMap.put("city", city != null ? city : "");
		CollegeEntity college = collegeService.get(sysUser.getCollegeID());
		innerMap.put("college", college != null ? college : "");
		MajorEntity major = majorService.get(sysUser.getMajorID());
		innerMap.put("major", major != null ? major : "");
		EducationEntity education = educationService.get(sysUser.getEducationID());
		innerMap.put("education", education != null ? education : "");

		map.putAll(retSuccess());
		map.put("data", innerMap);
		return map;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	// 不允许修改用户名和密码
	public Map<String, Object> editBasicInfo(HttpServletRequest request, SysUserEntity user){
		//setErrorMsg(request, "修改个人基本信息失败");
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 将原始的用户名和密码覆盖user中的用户名和密码，可以实现不允许修改用户名和密码的功能，就算修改了，也不会有效果
		// 只是防止测试时误操作的一个方法，最后根据需要应注释
		// TODO 需注释
		SysUserEntity sysUser = sysUserService.get(user.getUserID());
		user.setNickName(sysUser.getNickName());
		user.setPwd(sysUser.getPwd());
		
		if(sysUserService.validEmail(user.geteMail())){
			throw new MyRuntimeException("邮箱已被占用");
		}
		if(sysUserService.validTelephone(user.getTelNo())){
			throw new MyRuntimeException("手机号已被使用");
		}
		sysUserService.update(user);
		map.putAll(retSuccess());
		map.put("data", "");
		return map;
	}
	
	@RequestMapping(value = "/changePwd", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> changePassword(HttpServletRequest request, String userID, String oldPwd, String newPwd){
		setErrorMsg(request, "修改密码失败");
		Map<String, Object> map = new HashMap<String, Object>();
		
		SysUserEntity sysUser = sysUserService.get(userID);
		if(!EncryptUtil.MD5Digest(oldPwd).equals(sysUser.getPwd())){
			throw new MyRuntimeException("原始密码有误");
		}
		sysUser.setPwd(EncryptUtil.MD5Digest(newPwd));
		sysUserService.update(sysUser);
		map.putAll(retSuccess());
		map.put("data", "");
		return map;
	}
}
