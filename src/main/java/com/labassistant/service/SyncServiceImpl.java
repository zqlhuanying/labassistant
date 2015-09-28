package com.labassistant.service;

import java.io.Serializable;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.labassistant.dao.service.BaseAbstractService;

/**
 * 同步数据服务
 * @author zql
 * @date 2015/09/25
 */
@Service
public class SyncServiceImpl extends BaseAbstractService implements SyncService {

	@Override
	public void syncData(Map<String, Object> map, String tableName){
		String sql = createSql(map, tableName);
		saveOrUpdateBySql(sql);
	}
	
	/**
	 * 根据Map的key来获取对应的表名
	 * 对传递来的参数的命名有一定的限制
	 * @param key
	 * @return
	 */
	@Override
	public String getTableName(String key){
		return "t_" + key;
	}
	
	/**
	 * 根据表名获取对应的主键
	 * 表名和主键的命名有一定的要求
	 * @param tableName
	 * @return
	 */
	private String getTableID(String tableName){
		String str = tableName.substring(2);
		return str + "ID";
	}
	
	/**
	 * 根据map创建sql语句,map为字段=字段值
	 * @param map
	 * @return 
	 */
	private String createSql(Map<String, Object> map, String tableName){
		String id = getTableID(tableName);
		boolean isExist = isExists(tableName, (Serializable)map.get(id));
		if(isExist){
			return createUpdateSql(map, tableName);
		} else {
			return createInsertSql(map, tableName);
		}
	}
	
	private String createInsertSql(Map<String, Object> map, String tableName){
		StringBuffer sb = new StringBuffer();
		sb.append("insert into " + tableName);
		sb.append("(");
		for(Map.Entry<String, Object> entry : map.entrySet()){
			sb.append(entry.getKey() + ",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(")");
		
		sb.append("values");
		sb.append("(");
		for(Map.Entry<String, Object> entry : map.entrySet()){
			sb.append("'" + entry.getValue() + "'" + ",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(")");
		
		return sb.toString();
	}
	
	private String createUpdateSql(Map<String, Object> map, String tableName){
		String id = getTableID(tableName);
		Object idValue = map.get(id);
		StringBuffer sb = new StringBuffer();
		sb.append(" update " + tableName);
		sb.append(" set ");
		for(Map.Entry<String, Object> entry : map.entrySet()){
			if(!id.equals(entry.getKey())){
				sb.append(entry.getKey());
				sb.append("=");
				sb.append("'" + entry.getValue() + "'");
				sb.append(",");
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(" where ");
		sb.append(id);
		sb.append("=");
		sb.append("'" + idValue + "'");

		return sb.toString();
	}
	
	private Boolean isExists(String tableName, Serializable id){
		String sql = "select 1 from " + tableName + " where " + getTableID(tableName) + " = ?";
		return getCount(sql, false, id) > 0;
	}
}
