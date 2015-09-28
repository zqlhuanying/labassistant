package com.labassistant.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    public static String sendGet(String url, String param) {
        String result = "";
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
                result += line;
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
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
    	OutputStream out = null;
    	DataOutputStream out2 = null;
    	OutputStreamWriter out1 = null;
    	FileOutputStream out3 = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            //URLConnection conn = realUrl.openConnection();
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();  
            // 设置通用的请求属性
            conn.setRequestMethod("POST");
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("Content-type", "multipart/form-data");
            //conn.setRequestProperty("Content-type", "application/octet-stream");
            //conn.setRequestProperty("Content-type", "image/png");
            //conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = conn.getOutputStream();
            out1 = new OutputStreamWriter(out);
            out2 = new DataOutputStream(out);
            //out3 = new FileOutputStream(out);
            // 发送请求参数
            //out1.
            //out2.write(param.toString().getBytes());
            // 发送二进制流
            //File img = new File("F:\\实验助手\\Document\\png\\0登录.png");
            //out2.write(img)
            InputStream imgIn = new DataInputStream(new FileInputStream("F:\\实验助手\\Document\\png\\0登录.png"));
            int bytes = 0;  
            byte[] bufferOut = new byte[1024];  
            while ((bytes = imgIn.read(bufferOut)) != -1) {  
                out2.write(bufferOut, 0, bytes);  
            }  
            imgIn.close();
            // flush输出流的缓冲,有时候不起作用，数据仍没有发送出去，可能是因为post时必须设置content-length，否则发送的数据没有达到默认的长度，就还是不会发送数据，因此必须close才行
            out.flush();
            out.close();
            out1.close();
            out2.close();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }    

	public static void main(String[] args) {
		try{
			Map<String, InputStream> map = new HashMap<String, InputStream>();
			File img = new File("F:\\实验助手\\Document\\png\\0登录.png");
			InputStream in = new FileInputStream("F:\\实验助手\\Document\\png\\0登录.png");
			map.put("0", in);
			String httpAgrs = "myExpID=4039c681494b994701494b99aba51236&expStepID=4028c681494b994701494b00bab60000&file=";
			//String postAgrs = "imgMap['0']" + in;
			//String httpAgrs = "imgName=0登录.png";
			//发送 GET 请求
	        //String s=sendGet("http://172.18.0.55:8080/LabAssistant/upload/perExpProcessImgs", httpAgrs);
	        //System.out.println(s);
	        
	        //发送 POST 请求
	        String sr=sendPost("http://172.18.0.55:8080/LabAssistant/upload/perExpProcessImgs", httpAgrs);
	        System.out.println(sr);
		} catch (Exception e){
			e.printStackTrace();
		}
		

	}

}
