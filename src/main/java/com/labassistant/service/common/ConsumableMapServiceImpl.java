package com.labassistant.service.common;

import java.util.List;

import org.springframework.stereotype.Service;

import com.labassistant.beans.ConsumableMapEntity;
import com.labassistant.dao.service.BaseAbstractService;

/**
 * 耗材厂商对应
 * @author zql
 * @date 2015/10/09
 */
@Service
public class ConsumableMapServiceImpl extends
		BaseAbstractService<ConsumableMapEntity> implements
		ConsumableMapService {

	/**
	 * 获取耗材所对应的厂商
	 * @param consumableID
	 * @return
	 */
	@Override
	public List<ConsumableMapEntity> getListByConsumableID(String consumableID){
		String hql = "from ConsumableMapEntity where consumableID = ?";
		List<ConsumableMapEntity> lists = findListByHql(hql, consumableID);
		return lists;
	}
}
