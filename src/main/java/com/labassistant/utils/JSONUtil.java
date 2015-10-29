package com.labassistant.utils;

import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.labassistant.beans.ExpInstructionsMainEntity;
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
	 * 将Bean转换成json
	 * @param bean 需要转换的对象
	 * @return String
	 */
	public static <T> String bean2Json(T bean){
		requireNotNull(bean, "bean is null");
		try{
			return new ObjectMapper().writeValueAsString(bean);
		} catch (Exception ex){
			throw new MyRuntimeException("json转换异常");
		}
	}
	
	/**
	 * 将JSON转换成JavaBean
	 * @param json 需要转换的对象
	 * @return JavaBean对象
	 */
	public static <T> T json2Bean(String json, Class<T> beanClass){
		requireNotNull(json, "json is null");
		try{
			return new ObjectMapper().readValue(json, beanClass);
		} catch (Exception ex){
			throw new MyRuntimeException("json转换异常");
		}
	}
	
	/**
	 * 将Bean转换成Map
	 * @param bean 需要转换的对象
	 * @return Map
	 */
	public static <T> Map Bean2Map(T bean){
		try{
			return json2Map(bean2Json(bean));
		} catch (Exception ex){
			throw new MyRuntimeException("json转换异常");
		}
	}
	
	/**
	 * 将Map转换成JavaBean
	 * @param map 需要转换的对象
	 * @return JavaBean对象
	 */
	public static <T> T map2Bean(Map map, Class<T> beanClass){
		try{
			System.out.println(map2Json(map));
			return json2Bean(map2Json(map), beanClass);
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
    	//String json = "{'experimentdesc':'我也不知道','suppliername':'海尔','expcategoryid':'1','createdate':'2015-09-16','reviewcount':0,'provideuser':'','experimenttheory':'还是不知道','expsubcategoryid':'11','expinstructionid':'4028c681494b994701494b99bab61111','downloadcount':20,'allowdownload':1,'experimentname':'ELISA检测血清TNF-b浓度','expversion':1,'filterstr':'','productnum':'hhd','supplierid':'1001'}";
    	//Map m = json2Map(json);
    	//System.out.println(json2Bean(json, ExpInstructionsMainEntity.class));
    	String json = "{\"imgStream\":\"1\"}";
    	System.out.println(json);
    	System.out.println(json2Map(json));
    }
}
