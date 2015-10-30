package com.labassistant.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.labassistant.beans.MyExpEntity;
import com.labassistant.beans.MyExpProcessAttchEntity;
import com.labassistant.common.BaseController;
import com.labassistant.constants.AppConfig;
import com.labassistant.service.myexp.MyExpMainService;
import com.labassistant.service.myexp.MyExpProcessAttchService;
import com.labassistant.utils.Uploader;

/**
 * 上传
 * @author zql
 * @date 2015/09/21
 */
@Controller
@RequestMapping(value = "/upload")
public class UploadController extends BaseController {

	@Autowired
	private MyExpMainService myExpMainService;
	@Autowired
	private MyExpProcessAttchService myExpProcessAttchService;
	
	@RequestMapping(value = "/perExpProcessImgs", method = RequestMethod.POST)
	@ResponseBody
	// 虽然设置了是整个实体类，但主要的是myExpID，expStepID字段
	public Map<String, Object> perExpProcessImgs(HttpServletRequest request, 
			MyExpProcessAttchEntity myExpProcessAttch, @RequestParam("file") CommonsMultipartFile[] imgs){
		//myExpProcessAttch.setMyExpID("4039c681494b994701494b99aba51236");
		//myExpProcessAttch.setExpStepID("4028c681494b994701494b00bab60000");
		setErrorMsg(request, "上传图片失败");
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> object = new ArrayList<Object>();
		MyExpEntity myExp = myExpMainService.getByExpID(myExpProcessAttch.getMyExpID());
		try{
			//System.out.println(request.getInputStream());
			myExpProcessAttch.setExpInstructionID(myExp.getExpInstructionID());
			Uploader upload = new Uploader(request);
			for(CommonsMultipartFile img : imgs){
				// 上传图片
				String imgName = img.getOriginalFilename();
				// 设置保存的路径
				upload.setSavePath("upload/images");
				upload.setOriginalName(imgName);
				upload.setInputStream(img.getInputStream());
				upload.upload();
				// 更新我的实验步骤附件表
				myExpProcessAttch.setAttchmentName(imgName);
				myExpProcessAttch.setAttchmentServerPath(upload.getUrl());
				myExpProcessAttch.setIsUpload(1);
				Serializable myExpProcessAttchID = myExpProcessAttchService.save(myExpProcessAttch);
				// 返回的数据
				Map<String, String> innerMap = new LinkedHashMap<String, String>();
				innerMap.put("myExpProcessAttchID", (String)myExpProcessAttchID);
				innerMap.put("imgUrl", AppConfig.DOMAIN_PAGE + "/" + upload.getUrl());
				object.add(innerMap);
			}			
		} catch (Exception e){
			e.printStackTrace();
		}
		map.putAll(retSuccess());
		map.put("data", object);
		return map;
	}
}
