package com.labassistant.service.myexp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labassistant.beans.MyExpConsumableEntity;
import com.labassistant.dao.service.BaseAbstractService;
import com.labassistant.service.common.ConsumableService;

/**
 * 
 * @author zql
 * @date 2015/10/09
 */
@Service
public class MyExpConsumableServiceImpl extends
		BaseAbstractService<MyExpConsumableEntity> implements
		MyExpConsumableService {

	@Autowired
	private ConsumableService consumableService;
	
	@Override
	public List<MyExpConsumableEntity> getMyExpConsumables(String myExpID){
		String hql = "from MyExpConsumableEntity where myExpID = ?";
		return findListByHql(hql, myExpID);
	}
	
	@Override
	public List<String> getAllConsumablesName(String myExpID){
		List<String> result = new ArrayList<String>();
		List<MyExpConsumableEntity> consumables = getMyExpConsumables(myExpID);
		if(consumables != null){
			for(MyExpConsumableEntity consumable : consumables){
				result.add(consumableService.get(consumable.getConsumableID()).getConsumableName());
			}
		}
		return result;
	}
}
