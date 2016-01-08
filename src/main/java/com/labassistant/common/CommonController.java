package com.labassistant.common;

import java.beans.IntrospectionException;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.labassistant.beans.VersionEntity;
import com.labassistant.constants.ReturnJson;
import com.labassistant.exception.MyRuntimeException;
import com.labassistant.utils.BeanUtil;
import com.labassistant.utils.CommonUtil;
import com.labassistant.utils.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.labassistant.constants.AppConfig;

/**
 * 
 * @author zql
 * @date 2015/09/08
 */
@Controller
@RequestMapping(value = "/common")
public class CommonController extends BaseController {

    @RequestMapping(value = "loginOut")
    @ResponseBody
    public Map<String, String> loginOut(HttpServletRequest request){
        Map<String, String> map = new HashMap<String, String>();
        map.put("code", "0");
        map.put("msg", "请重新登陆");
        return map;
    }

    @RequestMapping(value = "checkVersion")
    @ResponseBody
    public Map<String, Object> checkVersion(HttpServletRequest request){
        setErrorMsg(request, "检查版本失败");
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> innerMap = new HashMap<String, Object>();

        String apkRoot = request.getSession().getServletContext().getRealPath("/WEB-INF/apk");
        String versionDesPath = apkRoot + File.separator + "version.txt";
        VersionEntity version = checkVersion(versionDesPath);
        String apkUrl = AppConfig.DOMAIN_PAGE + File.separator + "apk" + File.separator + version.getUrl() + File.separator + version.getApk();
        String descUrl = apkRoot + File.separator + version.getUrl() + File.separator + version.getDesc();
        String desc = FileUtil.read(descUrl);
        innerMap.put("version", version.getVersion());
        innerMap.put("url", FileUtil.toURLPath(apkUrl));
        innerMap.put("content", desc);

        CommonUtil.unionMap(ReturnJson.CHECKVERSION, innerMap);
        map.putAll(retSuccess());
        map.put("data", innerMap);
        return map;
    }

	/**
	 * 捕获错误信息
     * 显示顺序：1、自定义异常信息 2、AppConfig.REQUEST_ERROR_MSG_KEY 3、系统异常信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/error")
	@ResponseBody
	public Map<String, String> error(HttpServletRequest request){
		Exception e = (Exception) request.getAttribute("ex");
		logger.error("request error", e);
        Map<String, String> map = new HashMap<String, String>();
        String errorMsg = (String) request.getAttribute(AppConfig.REQUEST_ERROR_MSG_KEY);
        if(e.getClass().equals(MyRuntimeException.class)){
            errorMsg = e.getMessage();
        }
        if (StringUtils.isBlank(errorMsg)) {
            //errorMsg = "操作失败";
        	errorMsg = e.getMessage();
        }
        map.put("code", "0");
        map.put("msg", errorMsg);
        return map;
	}

    private VersionEntity checkVersion(String path){
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(new File(path)));
        } catch (FileNotFoundException e) {
            System.out.println("文件不存在");
            e.printStackTrace();
            return null;
        }

        List<VersionEntity> versions = new ArrayList<VersionEntity>();
        VersionEntity version0 = new VersionEntity();

        String line = null;
        try {
            while ((line = reader.readLine()) != null){
                boolean isStart = line.startsWith("=");
                boolean isEnd = line.endsWith("=");
                if(isStart || isEnd){
                    if(checkNonEmpty(version0)){
                        versions.add(version0);
                    }
                    version0 = new VersionEntity();
                    continue;
                }
                resovleLine(version0, line);
            }
        } catch (IOException e) {
            System.out.println("读取文件失败");
            e.printStackTrace();
            return null;
        }
        // 增加最后一个Version
        if(checkNonEmpty(version0)){
            versions.add(version0);
        }
        // 排序 获取最新的版本信息
        Collections.sort(versions, new Comparator<VersionEntity>() {
            @Override
            public int compare(VersionEntity o1, VersionEntity o2) {
                return Integer.parseInt(o2.getVersion()) - Integer.parseInt(o1.getVersion());
            }
        });
        return versions.get(0);
    }

    private void resovleLine(VersionEntity version, String line) {
        int indexOfLeft = line.indexOf("[");
        int indexOfRight = line.indexOf("]");
        int indexOfEq = line.indexOf("=");
        if(indexOfLeft >= 0 && indexOfRight > indexOfLeft + 1 && indexOfEq > indexOfRight){
            String name = line.substring(indexOfLeft + 1, indexOfRight).trim();
            String value = line.substring(indexOfEq + 1).trim();
            try {
                Method setMethod = BeanUtil.getFieldSetMethod(BeanUtil.getBeanInfo("version"), name);
                setMethod.invoke(version, value);
            } catch (IntrospectionException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 若 version 有一个字段为空，则返回false
     * @param version
     * @return
     */
    private Boolean checkNonEmpty(VersionEntity version){
        boolean isNonEmpty = true;
        if(StringUtils.isBlank(version.getVersion())){
            isNonEmpty = false;
        }
        if(StringUtils.isBlank(version.getUrl())){
            isNonEmpty = false;
        }
        if(StringUtils.isBlank(version.getApk())){
            isNonEmpty = false;
        }
        if(StringUtils.isBlank(version.getDesc())){
            isNonEmpty = false;
        }
        return isNonEmpty;
    }
}
