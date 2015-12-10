package com.labassistant.service.common;

import com.labassistant.beans.ConsumableEntity;
import com.labassistant.dao.service.IBaseAbstractService;

import java.util.List;

/**
 * 
 * @author zql
 * @date 2015/10/09
 */
public interface ConsumableService extends
		IBaseAbstractService<ConsumableEntity> {

    public List<ConsumableEntity> search(String name);

	
}
