package com.labassistant.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.labassistant.constants.AppConfig;

/**
 * 文件或图片上传或下载
 * @author zql
 * @date 2015/09/21
 */
public class Uploader {

	private static Logger logger = Logger.getLogger(Uploader.class);
	
	// 保存的路径，默认是upload文件夹下
	private String savePath = "upload";
	// 输出文件地址 是相对地址
    private String url = "";
    // 上传文件名
    private String fileName = "";
    // 文件类型
    private String type = "";
    // 原始文件名
    private String originalName = "";
    // 文件大小
    private String size = "";
    // 上传的文件数据
    private InputStream inputStream = null;
	
	// 可以用于获取根路径等信息
	private HttpServletRequest request;
	
	public Uploader(HttpServletRequest request){
		this.request = request;
	}
	
	public void upload() throws Exception{
		if(this.inputStream == null){
			logger.info("inputstream is null");
			throw new NullPointerException("data is null");
		}
		try{
			String savePath = getFolder(this.savePath);
			this.fileName = getName(this.originalName);
			this.type = getFileExt(this.originalName);
			this.url = savePath + "/" + this.fileName;
			
			// 输出数据
			FileOutputStream fout = new FileOutputStream(getPhysicalPathByRoot(this.url));
			BufferedInputStream bin = new BufferedInputStream(this.inputStream);
			byte[] buff = new byte[128];
            int count = -1;

            while ((count = bin.read(buff)) != -1) {
                fout.write(buff, 0, count);
            }

            bin.close();
            fout.close();
            
		} catch(Exception e){
			e.printStackTrace();
			logger.info("upload is failed");
		}
		
	}
	
	public FileOutputStream download() throws Exception{
		if(this.url == null){
			logger.info("url is null");
			throw new NullPointerException("url is null");
		}
		try{
			if(this.url.startsWith("http://")){
				this.url = this.url.substring(AppConfig.DOMAIN_PAGE.length());
			}
			String savePath = getPhysicalPathByRoot(this.url);
			return new FileOutputStream(new File(savePath));
		} catch (Exception e){
			e.printStackTrace();
			logger.info("download is failed");
			return null;
		}
	}
	
	/**
	 * 创建保存的目录,根据创建的日期来创建子目录
	 * @param path
	 * @return
	 */
	private String getFolder(String path){
		path = path + "/" + DateUtil.formatDate("yyyyMMdd", new Date());
		File dir = new File(getPhysicalPathByRoot(path));
		FileUtil.makeDirectory(dir);
		return path;
	}
	/**
     * 根据传入的虚拟路径获取物理路径
     * 
     * @param path
     * @return
     */
    private String getPhysicalPathByRoot(String path) {
        String realPath = this.request.getSession().getServletContext().getRealPath("/");
        return new File(realPath).getPath() + "/" + path;
    }

    /**
     * 获取文件扩展名
     * 
     * @return string
     */
    private String getFileExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
    
    /**
     * 依据原始文件名生成新文件名
     * 
     * @return
     */
    private String getName(String originalName) {
        Random random = new Random();
        return random.nextInt(10000)
                + System.currentTimeMillis() + this.getFileExt(originalName);
    }
    
	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getUrl() {
		return url;
	}

	public String getFileName() {
		return fileName;
	}

	public String getType() {
		return type;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public String getSize() {
		return size;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
       
}
