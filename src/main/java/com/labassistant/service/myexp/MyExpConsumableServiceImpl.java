package com.labassistant.service.myexp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.labassistant.beans.MyExpConsumableEntity;
import com.labassistant.beans.MyExpReagentEntity;
import com.labassistant.dao.service.BaseAbstractService;

/**
 * 
 * @author zql
 * @date 2015/10/09
 */
@Service
public class MyExpConsumableServiceImpl extends
		BaseAbstractService<MyExpConsumableEntity> implements
		MyExpConsumableService {

	@Override
	public List<MyExpConsumableEntity> getMyExpConsumables(String myExpID){
		String hql = "from MyExpConsumableEntity where myExpID = ?";
		return findListByHql(hql, myExpID);
	}
}
