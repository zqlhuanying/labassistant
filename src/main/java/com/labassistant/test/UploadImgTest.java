package com.labassistant.test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.labassistant.utils.JSONUtil;

import sun.misc.BASE64Encoder;

/**
 * HTTP POST请求报文格式分析与Java模拟实现文件上传
 * @author zql
 * @date 2015/09/24
 */
public class UploadImgTest {

	/**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static void sendGet(String url, String param) {
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            //application/octet-stream
            //connection.setRequestProperty("Content-type", "image/png");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
            	System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    
    /**
     * 向指定URL发送Post方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 键值对 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static void sendPost(String url, List<Map<String, String>> param) {
        uploadFile(url, new ArrayList<String>(), param);
    }
    
	public static void uploadFile(String url, List<String> fileNames, List<Map<String, String>> others) {
        try {
        	  // 换行符
        	  final String newLine = "\r\n";
			  final String boundaryPrefix = "--";
			  // 定义数据分隔线
			  String BOUNDARY = "========7d4a6d158c9";
			  // 服务器的域名
			  URL realUrl = new URL(url);
			  HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
			  // 设置为POST情
			  conn.setRequestMethod("POST");
			  // 发送POST请求必须设置如下两行
			  conn.setDoOutput(true);
			  conn.setDoInput(true);
			  conn.setUseCaches(false);
			  // 设置请求头参数
			  conn.setRequestProperty("connection", "Keep-Alive");
			  conn.setRequestProperty("Charsert", "UTF-8");
			  conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
			  OutputStream out = new DataOutputStream(conn.getOutputStream());
			  
			  // 添加其他普通参数
			  for(Map<String, String> other : others){
				  for (Map.Entry<String, String> entry : other.entrySet()) {  
					  
					  StringBuffer s = new StringBuffer();
					  s.append(boundaryPrefix);
					  s.append(BOUNDARY);
					  s.append(newLine);
					  //参数名myExpID可以随意修改
					  s.append("Content-Disposition: form-data;name=" + entry.getKey() + ";"
					          + "\"" + newLine);
					  s.append("Content-Type:text/plain;charset=UTF-8");
					  // 参数头设置完以后需要两个换行，然后才是参数内容
					  s.append(newLine);
					  s.append(newLine);
					  // 将参数头的数据写入到输出流中
					  out.write(s.toString().getBytes());
					  // 参数内容
					  out.write(entry.getValue().getBytes());
					  out.write(newLine.getBytes());  
					  
					} 
			  }
			  /*for(int i = 0; i < otherArgNames.length; i++){
				  StringBuffer others = new StringBuffer();
				  others.append(boundaryPrefix);
				  others.append(BOUNDARY);
				  others.append(newLine);
				  //参数名myExpID可以随意修改
				  others.append("Content-Disposition: form-data;name=" + otherArgNames[i]+ ";"
				          + "\"" + newLine);
				  others.append("Content-Type:text/plain;charset=UTF-8");
				  // 参数头设置完以后需要两个换行，然后才是参数内容
				  others.append(newLine);
				  others.append(newLine);
				  // 将参数头的数据写入到输出流中
				  out.write(others.toString().getBytes());
				  // 参数内容
				  out.write(otherArgValues[i].getBytes());
				  out.write(newLine.getBytes());
			  }*/

			  // 上传文件, 支持多文件上传
			  for(String fileName : fileNames){
				  File file = new File(fileName);
				  StringBuilder sb = new StringBuilder();
				  sb.append(boundaryPrefix);
				  sb.append(BOUNDARY);
				  sb.append(newLine);
				  // 文件参数,photo参数名可以随意修改
				  sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + fileName
				          + "\"" + newLine);
				  sb.append("Content-Type:application/octet-stream");
				  // 参数头设置完以后需要两个换行，然后才是参数内容
				  sb.append(newLine);
				  sb.append(newLine);
				  // 将参数头的数据写入到输出流中
				  out.write(sb.toString().getBytes());
				  // 数据输入流,用于读取文件数据
				  DataInputStream in = new DataInputStream(new FileInputStream(
				          file));
				  byte[] bufferOut = new byte[1024];
				  int bytes = 0;
				  // 每次读1KB数据,并且将文件数据写入到输出流中
				  while ((bytes = in.read(bufferOut)) != -1) {
				      out.write(bufferOut, 0, bytes);
				  }
				  // 最后添加换行
				  out.write(newLine.getBytes());
				  in.close();
			  }
			  
			  // 定义最后数据分隔线，即--加上BOUNDARY再加上--。
			  byte[] end_data = (newLine + boundaryPrefix + BOUNDARY + boundaryPrefix + newLine)
			          .getBytes();
			  // 写上结尾标识
			  out.write(end_data);
			  
			  // flush输出流的缓冲,有时候不起作用，数据仍没有发送出去，可能是因为post时必须设置content-length，否则发送的数据没有达到默认的长度，就还是不会发送数据，因此必须close才行
			  out.flush();
			  out.close();
			  
			  // 定义BufferedReader输入流来读取URL的响应
			  BufferedReader reader = new BufferedReader(new InputStreamReader(
				        conn.getInputStream()));
			  String line = null;
			  while ((line = reader.readLine()) != null) {
				   System.out.println(line);
			  }
        } catch (Exception e) {
		  System.out.println("发送POST请求出现异常！" + e);
		  e.printStackTrace();
        }
    }
	
	public static void main(String[] args){
		/*String url = "http://172.18.0.55:8080/LabAssistant/upload/perExpProcessImgs";
		List<String> files = new ArrayList<String>();
		files.add("F:\\实验助手\\Document\\png\\1注册.png");
		files.add("F:\\实验助手\\Document\\png\\2说明书.png");
		//String fileName = "F:\\实验助手\\Document\\png\\1注册.png";
		//String myExpID = "4039c681494b994701494b99aba51236";
		//String expStepID = "4028c681494b994701494b00bab60000";
		//String[] names = new String[]{"myExpID", "expStepID"};
		//String[] values = new String[]{"4039c681494b994701494b99aba51236", "4028c681494b994701494b00bab60000"};
		List<Map<String, String>> lists = new ArrayList<Map<String, String>>();
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("myExpID", "4039c681494b994701494b99aba51236");
		lists.add(map1);
		Map<String, String> map2 = new HashMap<String, String>();
		map1.put("expStepID", "4028c681494b994701494b00bab60000");
		lists.add(map2);
		uploadFile(url, files, lists);*/
		
		try{
            String url = "http://172.18.1.55:8080/LabAssistant/myInfo/edit";
            List<Map<String, String>> params = new ArrayList<Map<String, String>>();
            Map<String, String> img = new HashMap<String, String>();
            String path = "F:\\2.jpg";
            FileInputStream fin = new FileInputStream(new File(path));
            byte[] buffer = new byte[fin.available()];
            fin.read(buffer);
            fin.close();
            
            img.put("iconStream", new BASE64Encoder().encode(buffer));
            
            Map<String, String> others = new HashMap<String, String>();
            //others.put("nickName", "me4");
            others.put("userID", "2c9281b751192b330151192b9f330000");
            //others.put("pwd", "12345678");
            //others.put("eMail", "11123@qq.com");
            params.add(others);
            params.add(img);
            sendPost(url, params);
        } catch (Exception e){
            e.printStackTrace();
        }
	}
}
