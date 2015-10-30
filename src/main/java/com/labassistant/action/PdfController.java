package com.labassistant.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itextpdf.text.DocumentException;
import com.labassistant.beans.MyExpEntity;
import com.labassistant.common.BaseController;
import com.labassistant.service.SyncService;
import com.labassistant.service.ToPDFService;
import com.labassistant.service.myexp.MyExpMainService;
import com.labassistant.utils.DateUtil;
import com.labassistant.utils.FileUtil;

/**
 * 
 * @author zql
 * @date 2015/10/27
 */
@Controller
@RequestMapping(value = "/pdf")
public class PdfController extends BaseController {

	@Autowired
	private ToPDFService toPDFService;
	@Autowired
	private MyExpMainService myExpMainService;
	@Autowired
	private SyncService syncService;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> toPdf(HttpServletRequest request, String json, String myExpID) throws DocumentException, IOException{
		setErrorMsg(request, "生成PDF出错");
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 先同步数据
		syncService.pushMyExp(request, json);
		
		// 生成PDF
		String savePath = "/upload/pdf";
		String pdfName = getName();
		String relativePath = getFolder(request, savePath) + "/" + pdfName;
		String physicalPathPrefix = getPhysicalPathByRoot(request, "");
		
		String pdfPath = physicalPathPrefix + relativePath;
		//String pdfPath = "F:\\1.pdf";
		toPDFService.toPdf(FileUtil.toFilePath(pdfPath), myExpID);
		
		// 更新我的实验主表
		MyExpEntity myExp = myExpMainService.get(myExpID);
		myExp.setIsCreateReport(1);
		myExp.setReportName(pdfName);
		myExp.setReportServerPath(FileUtil.toURLPath(relativePath));
		myExpMainService.update(myExp);
		
		map.putAll(retSuccess());
		map.put("data", "");
		return map;
	}
	
	/**
	 * 创建保存的目录,根据创建的日期来创建子目录
	 * @param path
	 * @return
	 */
	private String getFolder(HttpServletRequest request, String path){
		path = path + "/" + DateUtil.formatDate("yyyyMMdd", new Date());
		File dir = new File(getPhysicalPathByRoot(request, path));
		FileUtil.makeDirectory(dir);
		return path;
	}
	
	/**
     * 根据传入的虚拟路径获取物理路径
     * 
     * @param path
     * @return
     */
    private String getPhysicalPathByRoot(HttpServletRequest request, String path) {
        String realPath = request.getSession().getServletContext().getRealPath("/");
        if(StringUtils.isNotBlank(path)){
        	return new File(realPath).getPath() + "/" + path;
        } else {
        	return new File(realPath).getPath();
		}
    }
    
    /**
     * 依据原始文件名生成新文件名
     * 
     * @return
     */
    private String getName() {
        Random random = new Random();
        return random.nextInt(10000)
                + System.currentTimeMillis() + ".pdf";
    }
}
