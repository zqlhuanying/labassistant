package com.labassistant.utils;

import java.io.IOException;
import java.io.InputStream;

import com.encryptcore.EnCryptCore;
import org.apache.commons.codec.digest.DigestUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 
 * @author zql
 * @date 2015/9/2
 * 
 */
public final class EncryptUtil {

	private static final String key = "labassistant-encrpt-!@#";
	private static final EnCryptCore enCryptCore = new EnCryptCore();
	
	/**
	 * 工具类不允许实列化
	 */
	private EncryptUtil(){}
	
	public static String encode(String content){
		return enCryptCore.encrypt(key, content);
	}
	
	public static String decode(String content){
		return enCryptCore.decrypt(key, content);
	}
	
	public static String BASE64Encode(InputStream in) throws IOException{
		byte[] buffer = new byte[in.available()];
        in.read(buffer);
        in.close();
        return new BASE64Encoder().encode(buffer);
	}
	
	public static byte[] BASE64Decode(String str) throws IOException{
		return new BASE64Decoder().decodeBuffer(str);
	}
	
	/**
	 * 消息摘要，用于单向加密，如密码
	 */
	public static String MD5Digest(String content){
		return DigestUtils.md5Hex(content);
	}
	
	public static String SHADigest(String content){
		return DigestUtils.sha1Hex(content);
	}
	
	public static void main(String[] args){
		// 测试加解密
		String content = "d8d10cf4215d4145a0d4d5521f75c9d3";
		String miwen = encode(content);
		String mingwen = decode(miwen);
		System.out.println(miwen);
		assert mingwen == content;
		
		// 测试消息摘要
		String digestContent = "12345678";
		System.out.println(MD5Digest(digestContent));
		System.out.println(SHADigest(digestContent));
	}
}
