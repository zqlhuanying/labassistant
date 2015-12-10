package com.labassistant.service.common;

import com.labassistant.beans.ReagentEntity;
import com.labassistant.dao.service.IBaseAbstractService;

import java.util.List;

/**
 * 
 * @author zql
 * @date 2015/09/15
 */
public interface ReagentService extends IBaseAbstractService<ReagentEntity> {

    public List<ReagentEntity> getReagents(String levelOneID, String levelTwoID);

    public List<ReagentEntity> search(String name);

}
