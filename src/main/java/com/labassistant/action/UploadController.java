package com.labassistant.action;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.labassistant.common.BaseController;
import com.labassistant.utils.Uploader;

/**
 * 上传
 * @author zql
 * @date 2015/09/21
 */
@Controller
@RequestMapping(value = "/upload")
public class UploadController extends BaseController {

	@RequestMapping(value = "")
	@ResponseBody
	public void upload(HttpServletRequest request){
		try{
			setErrorMsg(request, "上传失败");
			Uploader upload = new Uploader(request);
			upload.setOriginalName("实验助手 V1.4.doc");
			upload.setInputStream(new FileInputStream(new File("F:\\实验助手 V1.4.doc")));
			upload.upload();
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
}
