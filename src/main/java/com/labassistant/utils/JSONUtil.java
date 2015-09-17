package com.labassistant.utils;

import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.labassistant.exception.MyRuntimeException;

/**
 * json操作工具类
 * @author zql
 * @date 2015/09/08
 */
public final class JSONUtil {

	// 不允许实例化
	private JSONUtil(){}
	
	/**
	 * 将Map转换成json
	 * @param map 需要转换的对象
	 * @return String
	 */
	public static String map2Json(Map map){
		requireNotNull(map, "map is null");
		try{
			return new ObjectMapper().writeValueAsString(map);
		} catch (Exception ex){
			throw new MyRuntimeException("json转换异常");
		}
	}
	
	/**
	 * 将JSON转换成Map
	 * @param json 需要转换的对象
	 * @return Map
	 */
	public static Map json2Map(String json){
		requireNotNull(json, "json is null");
		try{
			return new ObjectMapper().readValue(json, Map.class);
		} catch (Exception ex){
			throw new MyRuntimeException("json转换异常");
		}
	}
	
	/**
     * 判断对象是否为空，如果为空，直接抛出异常
     * @param object 需要检查的对象
     * @param errorMessage 异常信息
     * @return 非空的对象
     */
    private static Object requireNotNull(Object object, String errorMessage) {
        if(null == object) {
            throw new NullPointerException(errorMessage);
        }
        return object;
    }
    
    //test
    public static void main(String[] args){
    	//String json = "{'unComplete':[{'labID':'4039c681494b994701494b99aba51237','instructionName':'ELISA检测血清TNF-b浓度'}]}";
    	//Map m = json2Map(json);
    	//System.out.println(m.get("unComplete"));
    }
}
