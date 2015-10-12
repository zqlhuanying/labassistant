package com.labassistant.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labassistant.beans.MyExpInstructionEntity;
import com.labassistant.beans.MyExpMainEntity;
import com.labassistant.dao.service.BaseAbstractService;
import com.labassistant.service.common.ConsumableMapService;
import com.labassistant.service.common.ConsumableService;
import com.labassistant.service.common.EquipmentMapService;
import com.labassistant.service.common.EquipmentService;
import com.labassistant.service.common.ReagentMapService;
import com.labassistant.service.common.ReagentService;
import com.labassistant.service.common.SupplierService;
import com.labassistant.service.exp.ExpInstructionsMainService;
import com.labassistant.service.myexp.MyExpConsumableService;
import com.labassistant.service.myexp.MyExpEquipmentService;
import com.labassistant.service.myexp.MyExpInstructionService;
import com.labassistant.service.myexp.MyExpMainService;
import com.labassistant.service.myexp.MyExpPlanService;
import com.labassistant.service.myexp.MyExpProcessAttchService;
import com.labassistant.service.myexp.MyExpProcessService;
import com.labassistant.service.myexp.MyExpReagentService;

/**
 * 同步数据服务
 * @author zql
 * @date 2015/09/25
 */
@Service
public class SyncServiceImpl extends BaseAbstractService implements SyncService {

	@Autowired
	private ReagentService reagentService;
	@Autowired
	private ReagentMapService reagentMapService;
	@Autowired
	private ConsumableService consumableService;
	@Autowired
	private ConsumableMapService consumableMapService;
	@Autowired
	private EquipmentService equipmentService;
	@Autowired
	private EquipmentMapService equipmentMapService;
	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private ExpInstructionsMainService expInstructionsMainService;
	
	@Autowired
	private MyExpInstructionService myExpInstructionService;
	@Autowired
	private MyExpMainService myExpMainService;
	@Autowired
	private MyExpReagentService myExpReagentService;
	@Autowired
	private MyExpConsumableService myExpConsumableService;
	@Autowired
	private MyExpEquipmentService myExpEquipmentService;
	@Autowired
	private MyExpProcessService myExpProcessService;
	@Autowired
	private MyExpProcessAttchService myExpProcessAttchService;
	@Autowired
	private MyExpPlanService myExpPlanService;
	
	@Override
	public void pushMyExp(Map<String, Object> map, String tableName){
		String sql = createSql(map, tableName);
		saveOrUpdateBySql(sql);
	}
	
	/**
	 * 推送公共部分的数据，如试剂/试剂对应厂商，耗材/耗材对应厂商等
	 */
	@Override
	public Map<String, Object> pullCommon(){
		Map<String, Object> map = new HashMap<String, Object>();
		// 试剂
		map.put("reagent", reagentService.findList());
		// 试剂厂商
		map.put("reagentMap", reagentMapService.findList());
		// 耗材
		map.put("consumable", consumableService.findList());
		// 耗材厂商
		map.put("consumableMap", consumableMapService.findList());
		// 设备
		map.put("equipment", equipmentService.findList());
		// 设备厂商
		map.put("equipmentMap", equipmentMapService.findList());
		// 厂商
		map.put("supplier", supplierService.findList());
		return map;
	}
	
	/**
	 * 若数据丢失，推送以前所有已经保存的数据
	 */
	@Override
	public Map<String, Object> pullAllDatas(String userID){
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> objects = new ArrayList<Object>();
		List<MyExpInstructionEntity> myExpInstructions = myExpInstructionService.getMyExpInstructions(userID);
		if(myExpInstructions != null){
			for(MyExpInstructionEntity myExpInstruction : myExpInstructions){
				objects.add(expInstructionsMainService.downloadInstruction(myExpInstruction.getExpInstructionID()));
			}
		}
		map.put("expInstructions", objects);
		map.putAll(pullMyExp(userID));
		return map;
	}
	
	private Map<String, Object> pullMyExp(String userID){
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> objects = new ArrayList<Object>();
		List<MyExpMainEntity> myExps= myExpMainService.getByUserID(userID);
		if(myExps != null){
			for(MyExpMainEntity myExp : myExps){
				Map<String, Object> innerMap = new HashMap<String, Object>();
				// 我的实验
				innerMap.put("myExp", myExp);
				// 我的实验试剂
				innerMap.put("myExpReagent", myExpReagentService.getMyExpReagents(myExp.getMyExpID()));
				// 我的实验耗材
				innerMap.put("myExpConsumable", myExpConsumableService.getMyExpConsumables(myExp.getMyExpID()));
				// 我的实验设备
				innerMap.put("myExpEquipment", myExpEquipmentService.getMyExpEquipments(myExp.getMyExpID()));
				// 我的实验步骤
				innerMap.put("myExpProcess", myExpProcessService.getList(myExp.getMyExpID()));
				// 我的实验步骤附件
				innerMap.put("myExpProcessAttch", myExpProcessAttchService.getMyExpProcessAttches(myExp.getMyExpID()));
				objects.add(innerMap);
			}
			map.put("myExps", objects);
			// 我的实验计划
			map.put("myExpPlan", myExpPlanService.getAllPlans(userID));
		}
		return map;
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
