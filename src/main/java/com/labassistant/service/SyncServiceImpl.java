package com.labassistant.service;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.*;
import java.util.Map.Entry;

import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;

import com.labassistant.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labassistant.annotation.MyAnnotation;
import com.labassistant.beans.MyExpInstructionEntity;
import com.labassistant.beans.MyExpEntity;
import com.labassistant.context.MySystemContext;
import com.labassistant.dao.service.BaseAbstractService;
import com.labassistant.exception.MyRuntimeException;
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
	public void pushMyExp(String json){
        String fitJson = fitJson(json);
		Map<String, Object> requestMap = JSONUtil.json2Map(fitJson);
		//requestMap = (Map)requestMap.get("data");
		
		// requestMap 中的Value有可能是数组，即Object有可能是数组
		Iterator<Map.Entry<String, Object>> iterator = requestMap.entrySet().iterator();
		while(iterator.hasNext()){
			Map.Entry<String, Object> entry = iterator.next();
			String tableName = getTableName(entry.getKey());
			if(entry.getValue().getClass() == ArrayList.class){
				for(Map<String, Object> innerMap : (ArrayList<Map<String, Object>>)entry.getValue()){
					pushMyExp(innerMap, tableName);
				}
			} else {
				pushMyExp((Map<String, Object>)entry.getValue(), tableName);
			}
		}
	}
	
	@Override
	public void pushExpInstruction(String json, String expInstructionID, String userID, int allowDownload){
        if(expInstructionsMainService.isExist(expInstructionID) &&
                (expInstructionsMainService.isPublic(expInstructionID) ||
                !expInstructionsMainService.isOwn(expInstructionID, userID))){
            throw new MyRuntimeException("没有权限提交说明书，有可能这份说明书已成为标准或不属于你");
        }
        String fitJson = fitJson(json);
		Map<String, Object> requestMap = JSONUtil.json2Map(fitJson);
		//requestMap = (Map)requestMap.get("data");

		// requestMap 中的Value有可能是数组，即Object有可能是数组
		Iterator<Map.Entry<String, Object>> iterator = requestMap.entrySet().iterator();
		while(iterator.hasNext()){
			Map.Entry<String, Object> entry = iterator.next();
			String tableName = getTableName(entry.getKey());
			if(entry.getValue().getClass() == ArrayList.class){
				for(Map<String, Object> innerMap : (ArrayList<Map<String, Object>>)entry.getValue()){
					pushExpInstruction(innerMap, allowDownload, tableName);
				}
			} else {
				pushExpInstruction((Map<String, Object>)entry.getValue(), allowDownload, tableName);
			}
		}
	}

	/**
	 * 同步数据
	 * @param map
	 * @param tableName
	 */
	private void sync(Map<String, Object> map, String tableName){
        List<String> sqls = new ArrayList<String>();
		createSql(sqls, map, tableName);
        //String[] sqls = sql.split(";");
        for(String s : sqls){
            saveOrUpdateBySql(s);
        }
	}
	
	private void pushMyExp(Map<String, Object> map, String tableName){	
		checkNonAvailable(map, getBeanName(tableName));
		checkNameOnlyInServer(map, getBeanName(tableName));
		// 将我的实验步骤附件表(MyExpProcessAttch)单独对待，因涉及到图片上传
		if("t_myExpProcessAttch".equals(tableName)){
			processAttch(map);
		}
		sync(map, tableName);
	}
	
	private void pushExpInstruction(Map<String, Object> map, int allowDownload, String tableName){	
		checkNonAvailable(map, getBeanName(tableName));
		checkNameOnlyInServer(map, getBeanName(tableName));
		
		// 将实验说明书主表(ExpInstruction)单独对待，因要修改allowDownload字段
		if("t_expInstruction".equals(tableName)){
			processExpInstruction(map, allowDownload);
		}
		sync(map, tableName);
	}
	
	/**
	 * 推送公共部分的数据，如试剂/试剂对应厂商，耗材/耗材对应厂商等
	 */
	@Override
	public Map<String, Object> pullCommon(Date date){
		Map<String, Object> map = new HashMap<String, Object>();
		// 试剂
		map.put("reagent", reagentService.getReagentList(date));
		// 试剂厂商
		map.put("reagentMap", reagentMapService.getReagentMapList(date));
		// 耗材
		map.put("consumable", consumableService.getConsumableList(date));
		// 耗材厂商
		map.put("consumableMap", consumableMapService.getConsumableMapList(date));
		// 设备
		map.put("equipment", equipmentService.getEquipmentList(date));
		// 设备厂商
		map.put("equipmentMap", equipmentMapService.getEquipmentMapList(date));
		// 厂商
		map.put("supplier", supplierService.getSupplierList(date));

        map.put("current", DateUtil.formatDate(new Date()));
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
		List<MyExpEntity> myExps= myExpMainService.getByUserID(userID);
		if(myExps != null){
			for(MyExpEntity myExp : myExps){
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
	 * 根据getTableName函数所获得的表名，逆向得到key
	 * @param tableName
	 * @return
	 */
	private String getBeanName(String tableName){
		return tableName.substring(2);
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
	 * 过滤map中不属于beanName所代表的实体类的key
	 * @param map
	 * @throws IntrospectionException 
	 * @throws MyRuntimeException 
	 */
	private void checkNonAvailable(Map<String, Object> map, String beanName){
		List<String> fields = new ArrayList<String>();
		try {
			fields = BeanUtil.getFieldsName(BeanUtil.getBeanInfo(beanName));
		} catch (MyRuntimeException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		
		// Map.Entry 不允许这样删除
		/*for(Map.Entry<String, Object> entry : map.entrySet()){
			if(!in(entry.getKey(), fields)){
				map.remove(entry.getKey());
			}
		}*/
		
		Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<String, Object> entry = iterator.next();
			if(!in(entry.getKey(), fields)){
				iterator.remove();
			}
		}
	}
	
	/**
	 * 过滤map中只允许在服务器端修改的参数
	 * @param map
	 * @param beanName
	 */
	private void checkNameOnlyInServer(Map<String, Object> map, String beanName){
		List<String> fields = new ArrayList<String>();
		try {
			fields = BeanUtil.getFieldsName(BeanUtil.getBeanInfo(beanName));
		} catch (MyRuntimeException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		
		// Map.Entry 不允许这样删除
		/*for(Map.Entry<String, Object> entry : map.entrySet()){
			if(!in(entry.getKey(), fields)){
				map.remove(entry.getKey());
			}
		}*/
		
		Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<String, Object> entry = iterator.next();
			if(BeanUtil.hasTheAnnotation(beanName, entry.getKey(), MyAnnotation.class) ||
                    BeanUtil.hasTheAnnotation(beanName, entry.getKey(), Transient.class)){
				iterator.remove();
			}
		}
	}
	
	private Boolean in(String key, List<String> list){
		for(String str : list){
			if(str.equals(key)){
				return true;
			}
		}
		return false;
	}
	
	// 处理我的实验步骤附件表
	private void processAttch(Map<String, Object> map){
		InputStream inputStream = null;
		String imgString = (String)map.get("imgStream");
		if(StringUtils.isNotBlank(imgString)){
			try{
				byte[] img = EncryptUtil.BASE64Decode(imgString);
				inputStream = new ByteArrayInputStream(img);
			} catch (IOException e) {
				System.out.println("处理图片二进制流失败");
				e.printStackTrace();
			}
			Uploader upload = new Uploader(MySystemContext.getMyRequest());
			upload.setSavePath("upload/images");
			String imgName = (String)map.get("attchmentName");
			if(StringUtils.isBlank(imgName)){
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
		}
	}
	
	// 处理我的说明书主表
	private void processExpInstruction(Map<String, Object> map, int allowDownload){
		map.put("allowDownload", allowDownload);
	}
	
	/**
	 * 根据map创建sql语句,map为字段=字段值
	 * @param map
	 * @return 
	 */
	private void createSql(List<String> sqls, Map<String, Object> map, String tableName){
		String id = getTableID(tableName);
		boolean isExist = isExists(tableName, id, (Serializable)map.get(id));
		if(isExist){
			sqls.add(createUpdateSql(map, tableName));
		} else {
            sqls.add(createInsertSql(map, tableName));
            // 如果是新增 试剂/耗材/设备 的话，还需要修改 试剂表/试剂厂商对应表（供应商表也有可能修改），耗材/设备 类同
            if("t_expReagent".equals(tableName) ||
                    "t_expConsumable".equals(tableName) ||
                    "t_expEquipment".equals(tableName)){
                emergenceInsert(sqls, map, tableName);
            }
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
			if(isDateClassAndBlank(entry.getKey(), entry.getValue(), tableName)){
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
				if(isDateClassAndBlank(entry.getKey(), entry.getValue(), tableName)){
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
	
	// 判断是否是Date类型并且为空字符串(或"null"或"(null)",客户端的特殊情况)
	private Boolean isDateClassAndBlank(String key, Object value, String tableName){
		BeanInfo beanInfo = null;
		try {
			beanInfo = BeanUtil.getBeanInfo(getBeanName(tableName));
		} catch (MyRuntimeException e) {
			System.out.println("获取JavaBean信息出错");
			e.printStackTrace();
		} catch (IntrospectionException e) {
			System.out.println("获取JavaBean信息出错");
			e.printStackTrace();
		}
		
		if(value == "" || "null".equals(value) || "(null)".equals(value)){
			if(BeanUtil.getFieldClass(beanInfo, key) == Date.class){
				return true;
			}
		}
		return false;
	}
	
	private Boolean isExists(String tableName, Serializable field, Serializable value){
		String sql = "select 1 from " + tableName + " where " + field + " = ?";
		return getCount(sql, false, value) > 0;
	}

    private void emergenceInsert(List<String> sqls, Map<String, Object> map, String tableName){
        StringBuffer sb = new StringBuffer();
        Map<String, Object> m = new HashMap<String, Object>();
        String supplierID = (String)map.get("supplierID");
        String supplierName = (String)map.get("supplierName");
        if("t_expReagent".equals(tableName)){
            emergenceExpReagent(sqls, map);
            m.put("supplierType", 0);
        }
        if("t_expConsumable".equals(tableName)){
            emergenceExpConsumable(sqls, map);
            m.put("supplierType", 1);
        }
        if("t_expEquipment".equals(tableName)){
            emergenceExpEquipment(sqls, map);
            m.put("supplierType", 2);
        }
        if(sb.length() <= 1){
            sb.delete(0, 1);
        }
        if(StringUtils.isNotBlank(supplierID) && !isExists("t_supplier", "supplierid", supplierID)){
            m.put("supplierID", supplierID);
            m.put("supplierName", supplierName);
            m.put("updateTime", DateUtil.formatDate(new Date()));
            sqls.add(createInsertSql(m, "t_supplier"));
        }
    }

    private void emergenceExpReagent(List<String> sqls, Map<String, Object> map){
        String reagentID = (String)map.get("reagentID");
        String reagentName = (String)map.get("reagentName");
        String levelOneID = (String)map.get("levelOneSortID");
        String levelTwoID = (String)map.get("levelTwoSortID");
        String supplierID = (String)map.get("supplierID");
        if(!isExists("t_reagent", "reagentid", reagentID)){
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("reagentID", reagentID);
            m.put("reagentName", reagentName);
            m.put("levelOneSortID", levelOneID);
            m.put("levelTwoSortID", levelTwoID);
            m.put("updateTime", DateUtil.formatDate(new Date()));
            // 创建插入 t_reagent 表的 sql 语句
            sqls.add(createInsertSql(m, "t_reagent"));

            m.clear();
            m.put("reagentMapID", uuid());
            m.put("reagentID", reagentID);
            m.put("supplierID", supplierID);
            m.put("updateTime", DateUtil.formatDate(new Date()));
            // 创建插入 t_reagentmap 表的 sql 语句
            sqls.add(createInsertSql(m, "t_reagentMap"));
        }
    }

    private void emergenceExpConsumable(List<String> sqls, Map<String, Object> map){
        String consumableID = (String)map.get("consumableID");
        String consumableName = (String)map.get("consumableName");
        String supplierID = (String)map.get("supplierID");
        if(!isExists("t_consumable", "consumableid", consumableID)){
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("consumableID", consumableID);
            m.put("consumableName", consumableName);
            m.put("updateTime", DateUtil.formatDate(new Date()));
            // 创建插入 t_consumable 表的 sql 语句
            sqls.add(createInsertSql(m, "t_consumable"));

            m.clear();
            m.put("consumableMapID", uuid());
            m.put("consumableID", consumableID);
            m.put("supplierID", supplierID);
            m.put("updateTime", DateUtil.formatDate(new Date()));
            // 创建插入 t_consumablemap 表的 sql 语句
            sqls.add(createInsertSql(m, "t_consumableMap"));
        }
    }

    private void emergenceExpEquipment(List<String> sqls, Map<String, Object> map){
        String equipmentID = (String)map.get("equipmentID");
        String equipmentName = (String)map.get("equipmentName");
        String supplierID = (String)map.get("supplierID");
        if(!isExists("t_equipment", "equipmentid", equipmentID)){
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("equipmentID", equipmentID);
            m.put("equipmentName", equipmentName);
            m.put("updateTime", DateUtil.formatDate(new Date()));
            // 创建插入 t_equipment 表的 sql 语句
            sqls.add(createInsertSql(m, "t_equipment"));

            m.clear();
            m.put("equipmentMapID", uuid());
            m.put("equipmentID", equipmentID);
            m.put("supplierID", supplierID);
            m.put("updateTime", DateUtil.formatDate(new Date()));
            // 创建插入 t_equipmentmap 表的 sql 语句
            sqls.add(createInsertSql(m, "t_equipmentMap"));
        }
    }
    /**
     * 生成uuid
     * @return
     */
    private String uuid(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * Jacson 不能处理 \n 等特殊字符，所以先删除这些特殊字符
     * @param json
     * @return
     */
    private String fitJson(String json){
        String[] del = new String[]{"\n"};
        for (String d : del){
            json = json.replace(d, "");
        }
        return json;
    }
}
