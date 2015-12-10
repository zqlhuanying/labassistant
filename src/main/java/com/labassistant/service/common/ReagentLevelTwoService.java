package com.labassistant.service.common;

import com.labassistant.beans.ReagentLevelTwoEntity;
import com.labassistant.dao.service.IBaseAbstractService;

import java.util.List;

public interface ReagentLevelTwoService extends
		IBaseAbstractService<ReagentLevelTwoEntity> {

    public List<ReagentLevelTwoEntity> getLevelTwo(String levelOne);
}
