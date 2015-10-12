package com.labassistant.utils;

import com.encryptcore.EnCryptCore;
import org.apache.commons.codec.digest.DigestUtils;

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
