package com.labassistant.service.common;

import java.util.List;

import com.labassistant.beans.ConsumableMapEntity;
import com.labassistant.dao.service.IBaseAbstractService;

/**
 * 
 * @author zql
 * @date 2015/10/09
 */
public interface ConsumableMapService extends
		IBaseAbstractService<ConsumableMapEntity> {

	public List<ConsumableMapEntity> getListByConsumableID(String consumableID);
}
