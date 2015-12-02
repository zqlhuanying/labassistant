package com.labassistant.utils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.labassistant.beans.ToStringBase;

public final class CommonUtil {

	public static Object saveNull(Object obj){
		return obj == null ? "" : obj;
	}
	
	/**
	 * 以 templateMap 模板为基础，合并两个map
	 * 若 map 中出现 templateMap 没有的关键字，忽略
	 * 否则用 map 中的 Value 覆盖 templateMap 中相对应的 Value
	 * @param templateMap
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> unionMap(Map<String, Object> templateMap, Map<String, Object> map){
		if(map == null){
			return templateMap;
		}
		
		for(Iterator<? extends Map.Entry<String, Object>> iter = templateMap.entrySet().iterator(); iter.hasNext();){
			Map.Entry<String, Object> e = iter.next();
			String k = e.getKey();
			Object v = e.getValue();
			Object m = map.get(k);
			
			// 若 v 是 map 类型，则递归
			if(Map.class.isAssignableFrom(v.getClass())){
				templateMap.put(k, unionMap((Map<String, Object>)v, tryConvert(m)));
				continue;
			}
			
			// 若 v 是 list 类型
			if(List.class.isAssignableFrom(v.getClass())){
				List<Object> vv = ((List<Object>)v);
				List<Object> mm = (List<Object>)m;
				Class<?> class_in_v = getInnerClass(vv);
				if(Map.class.isAssignableFrom(class_in_v)){
					for(int index = 0; index < mm.size(); index ++){
						vv.add(unionMap((Map<String, Object>)vv.get(0), tryConvert(mm.get(index))));
					}
					vv.remove(0);
					templateMap.put(k, vv);
				}
				continue;
			}
			
			// else
			if(map.get(k) != null){
				templateMap.put(k, map.get(k));
			}
		}
		return templateMap;
	}
	
	/**
	 * 返回 list 中元素的类型, 且长度不为空
	 * 要求 list 中所有元素的类型必须相同
	 * @param list
	 * @return
	 */
	private static Class<?> getInnerClass(List<?> list){
		if(list != null && list.size() > 0){
			return list.get(0).getClass();
		}
		return null;
	}
	
	/**
	 * 如果对象 o 是 JavaBean 尝试转换成 map
	 * 因为 模板的 map 是由 json 字符串转换而来，是没有 JavaBean 对象的
	 * 所以 尝试去转换
	 * @param o JavaBean or Map Object
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static Map<String, Object> tryConvert(Object o){
		assert Map.class.isAssignableFrom(o.getClass()) ||
				ToStringBase.class.isAssignableFrom(o.getClass());
		// ToStringBase 是本项目中所有 JavaBean 的父类
		if(ToStringBase.class.isAssignableFrom(o.getClass())){
			return (Map<String, Object>)JSONUtil.bean2Map(o);
		}
		return (Map<String, Object>) o;
	}
}
