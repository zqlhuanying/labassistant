package com.labassistant.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.Assert;

import com.labassistant.annotation.MyAnnotation;
import com.labassistant.beans.ToStringBase;
import com.labassistant.exception.MyRuntimeException;

/**
 * 封装对JavaBean的一些操作
 * @author zql
 * @date 2015/20/29
 */
public final class BeanUtil {

	/**
	 * 获取bean信息
	 * @param beanName
	 * @return
	 * @throws MyRuntimeException
	 * @throws IntrospectionException
	 */
	public static BeanInfo getBeanInfo(String beanName) throws MyRuntimeException, IntrospectionException{
		return Introspector.getBeanInfo(getBeanClass(beanName));
	}
	
	/**
	 * 获取字段名
	 * @param beanInfo
	 * @return
	 */
	public static List<String> getFieldsName(BeanInfo beanInfo){
		List<String> result = new ArrayList<String>();
		PropertyDescriptor[] properties = beanInfo.getPropertyDescriptors();
		for(PropertyDescriptor property : properties){
			if(!"class".equals(property.getName())){
				result.add(property.getName());
			}
		}
		return result;
	}
	
	/**
	 * 获取字段的类型
	 * @param beanInfo
	 * @param fieldName
	 * @return
	 */
	public static Class<?> getFieldClass(BeanInfo beanInfo, String fieldName){
		Assert.notNull(beanInfo);
		PropertyDescriptor[] properties = beanInfo.getPropertyDescriptors();
		for(PropertyDescriptor property : properties){
			if(fieldName.equals(property.getName())){
				return property.getPropertyType();
			}
		}
		return null;
	}
	
	/**
	 * 获取字段和对应的类型
	 * @param beanInfo
	 * @return
	 */
	public static Map<String, Class<?>> getFieldsMap(BeanInfo beanInfo){
		Map<String, Class<?>> field = new HashMap<String, Class<?>>();
		PropertyDescriptor[] properties = beanInfo.getPropertyDescriptors();
		for(PropertyDescriptor property : properties){
			if(!"class".equals(property.getName())){				
				field.put(property.getName(), property.getPropertyType());
			}
		}
		return field;
	}
	
	/**
	 * 判断是否有annotation
	 * @param beanName
	 * @param fieldName
	 * @param annotationClass
	 * @return
	 */
	public static Boolean hasTheAnnotation(String beanName, String fieldName, Class<? extends Annotation> annotationClass){
		Field[] fields = getBeanClass(beanName).getDeclaredFields();
		for(Field field : fields){
			if(field.getName().equals(fieldName))
				return field.isAnnotationPresent(annotationClass);
		}
		return false;
	}
	
	/**
	 * 获取bean的class
	 * @param beanName 查找的类
	 * @return
	 * @throws MyRuntimeException
	 */
	private static Class<?> getBeanClass(String beanName) throws MyRuntimeException{
		Assert.notNull(beanName);
		// 首字母大写
		char[] cs = beanName.toCharArray();
		cs[0] -= 32;
		beanName = String.valueOf(cs) + "Entity";
		
		// 获取javaBean所在的完整路径，即包名.类名
		String packageName = new ToStringBase().getClass().getPackage().getName();
		String fullBeanPath = packageName + "." + beanName;
		
		try {
			return Class.forName(fullBeanPath);
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
			throw new MyRuntimeException("未找到指定的类");
		}
	}
	
	// test
	public static void main(String[] args){
		try {
			BeanInfo beanInfo = BeanUtil.getBeanInfo("myExpProcessAttch");
			List<String> fieldsName = BeanUtil.getFieldsName(beanInfo);
			for(String name : fieldsName){
				System.out.println(name);
			}
			
			Map<String, Class<?>> fields = BeanUtil.getFieldsMap(beanInfo);
			for(Map.Entry<String, Class<?>> field : fields.entrySet()){
				System.out.println(field.getKey() + ":" + field.getValue());
			}
			
			System.out.println(BeanUtil.hasTheAnnotation("expInstruction", "downloadCount", MyAnnotation.class));
		} catch (MyRuntimeException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
	}
}
