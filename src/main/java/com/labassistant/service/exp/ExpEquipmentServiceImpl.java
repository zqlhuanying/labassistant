package com.labassistant.service.exp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.labassistant.beans.ExpConsumableEntity;
import com.labassistant.beans.ExpEquipmentEntity;
import com.labassistant.dao.service.BaseAbstractService;

/**
 * 实验设备服务
 * @author zql
 * @date 2015/09/17
 */
@Service
public class ExpEquipmentServiceImpl extends
		BaseAbstractService<ExpEquipmentEntity> implements ExpEquipmentService {

	/**
	 * 根据实验说明书号，获取全部设备
	 * @param expInstructionID 实验说明书号
	 * @return
	 */
	@Override
	public List<ExpEquipmentEntity> getExpEquipmentLists(String expInstructionID){
		String hql = "from ExpEquipmentEntity where expInstructionID = ?";
		List<ExpEquipmentEntity> lists = findListByHql(hql, expInstructionID);
		return lists;
	}
}
