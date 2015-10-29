package com.labassistant.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.misc.BASE64Decoder;

import com.labassistant.beans.MyExpInstructionEntity;
import com.labassistant.beans.MyExpMainEntity;
import com.labassistant.constants.LabConstant;
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
import com.labassistant.utils.Uploader;

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
	public void pushMyExp(HttpServletRequest request, Map<String, Object> map, String tableName){
		Map<String, Object> tmp = map;
		// 将我的实验步骤附件表(MyExpProcessAttch)单独对待，因涉及到图片上传
		if("t_myExpProcessAttch".equals(tableName)){
			tmp = processAttch(request,map);
		}
		String sql = createSql(tmp, tableName);
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
				innerMap.put("myExpProcessAttch", myExpProcessAttchService.getMyExpProcessAttches(myExp.getMyExpID(), null));
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
	
	// 处理我的实验步骤附件表
	private Map<String, Object> processAttch(HttpServletRequest request, Map<String, Object> map){
		InputStream inputStream = null;
		try{
			byte[] img = new BASE64Decoder().decodeBuffer((String)map.get("imgStream"));
			inputStream = new ByteArrayInputStream(img);
		} catch (IOException e) {
			System.out.println("处理图片二进制流失败");
			e.printStackTrace();
		}
		Uploader upload = new Uploader(request);
		upload.setSavePath("upload/images");
		String imgName = (String)map.get("attchmentName");
		if(StringUtils.isNotBlank(imgName)){
			imgName = "1.jpg";
		}
		upload.setOriginalName(imgName);
		upload.setInputStream(inputStream);
		try {
			upload.upload();
		} catch (Exception e) {
			System.out.println("上传图片失败");
			e.printStackTrace();
		}
		
		// 更新map
		map.put("attchmentName", upload.getFileName());
		map.put("attchmentServerPath", upload.getUrl());
		map.put("isUpload", 1);
		map.remove("imgStream");
		return map;
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
			Object value = entry.getValue();
			
			// 在mysql中date或datetime类型不能插入空字符串，所以将空字符串转换成null
			if(isDateClassAndBlank(entry.getKey(), entry.getValue())){
				value = null;
			}
			
			// 修改我的实验主表(MyExp)中的isUpload字段
			if("t_myExp".equals(tableName) && "isUpload".equals(entry.getKey())){
				value = 1;
			}
			
			if(value == null) sb.append(value);
			else sb.append("'" + value + "'");
			sb.append(",");
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
				Object value = entry.getValue();
				// 在mysql中date或datetime类型不能插入空字符串，所以将空字符串转换成null
				if(isDateClassAndBlank(entry.getKey(), entry.getValue())){
					value = null;
				}
				sb.append(entry.getKey());
				sb.append("=");
				if(value == null) sb.append(value);
				else sb.append("'" + value + "'");
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
	
	// 判断是否是Date类型并且为空字符串
	// 暂时根据字段名进行判断
	// TODO 
	private Boolean isDateClassAndBlank(String key, Object value){
		if(value == ""){
			if(LabConstant.MYEXP_CREATETIME.equals(key) ||
					LabConstant.MYEXP_FINISHTIME.equals(key) ||
					LabConstant.MYEXPPLAN_PLANDATE.equals(key)){
				return true;
			}
		}
		return false;
	}
	
	private Boolean isExists(String tableName, Serializable id){
		String sql = "select 1 from " + tableName + " where " + getTableID(tableName) + " = ?";
		return getCount(sql, false, id) > 0;
	}
}
