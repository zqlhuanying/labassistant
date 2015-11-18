package com.labassistant.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.labassistant.beans.TitleEntity;
import com.labassistant.common.BaseController;
import com.labassistant.constants.AppConfig;
import com.labassistant.exception.MyRuntimeException;
import com.labassistant.service.CityService;
import com.labassistant.service.CollegeService;
import com.labassistant.service.EducationService;
import com.labassistant.service.MajorService;
import com.labassistant.service.ProvinceService;
import com.labassistant.service.SysUserService;
import com.labassistant.service.TitleService;
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
	@Autowired
	private TitleService titleService;
	
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
		innerMap.put("icon", AppConfig.DOMAIN_PAGE + sysUser.getIcon());
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
		TitleEntity title = titleService.get(sysUser.getTitleID());
		innerMap.put("title", title != null ? title : "");

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
		
		if(sysUserService.validUsername(user.getNickName())){
			throw new MyRuntimeException("用户名已存在");
		}
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
	
	@RequestMapping(value = "/provinceAndCity")
	@ResponseBody
	public Map<String, Object> provinceAndCity(HttpServletRequest request){
		setErrorMsg(request, "获取省市对应列表失败");
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Object> object = provinceService.provinceAndCity();
		
		map.putAll(retSuccess());
		map.put("data", object);
		return map;
	}
	
	@RequestMapping(value = "/colleges")
	@ResponseBody
	// 虽然设置了整个实体类，但只需要provinceID，cityID字段
	public Map<String, Object> colleges(HttpServletRequest request, CollegeEntity theCollege){
		setErrorMsg(request, "获取院校列表失败");
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> object = new ArrayList<Object>();
		
		List<CollegeEntity> colleges = collegeService.getColleges(theCollege.getProvinceID(), theCollege.getCityID());
		if(colleges != null){
			for(CollegeEntity college : colleges){
				Map<String, String> innerMap = new HashMap<String, String>();
				innerMap.put("collegeID", college.getCollegeID());
				innerMap.put("collegeName", college.getCollegeName());
				object.add(innerMap);
			}
		}
		map.putAll(retSuccess());
		map.put("data", object);
		return map;
	}
	
	@RequestMapping(value = "/majors")
	@ResponseBody
	public Map<String, Object> majors(HttpServletRequest request){
		setErrorMsg(request, "获取专业列表失败");
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<MajorEntity> object = majorService.findList();

		map.putAll(retSuccess());
		map.put("data", object);
		return map;
	}
	
	@RequestMapping(value = "/educations")
	@ResponseBody
	public Map<String, Object> educations(HttpServletRequest request){
		setErrorMsg(request, "获取学历列表失败");
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<EducationEntity> object = educationService.findList();

		map.putAll(retSuccess());
		map.put("data", object);
		return map;
	}
	
	@RequestMapping(value = "/titles")
	@ResponseBody
	public Map<String, Object> titles(HttpServletRequest request){
		setErrorMsg(request, "获取职称列表失败");
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<TitleEntity> object = titleService.findList();

		map.putAll(retSuccess());
		map.put("data", object);
		return map;
	}
}
