package com.labassistant.service.myexp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.labassistant.beans.MyExpConsumableEntity;
import com.labassistant.beans.MyExpEquipmentEntity;
import com.labassistant.dao.service.BaseAbstractService;

/**
 * 我的实验设备服务
 * @author zql
 * @date 2015/10/09
 */
@Service
public class MyExpEquipmentServiceImpl extends
		BaseAbstractService<MyExpEquipmentEntity> implements
		MyExpEquipmentService {

	@Override
	public List<MyExpEquipmentEntity> getMyExpEquipments(String myExpID){
		String hql = "from MyExpEquipmentEntity where myExpID = ?";
		return findListByHql(hql, myExpID);
	}
}
