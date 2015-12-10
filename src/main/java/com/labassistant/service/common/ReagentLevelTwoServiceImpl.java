package com.labassistant.service.common;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.labassistant.beans.ReagentLevelTwoEntity;
import com.labassistant.dao.service.BaseAbstractService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReagentLevelTwoServiceImpl extends
		BaseAbstractService<ReagentLevelTwoEntity> implements
		ReagentLevelTwoService {

    @Override
    public List<ReagentLevelTwoEntity> getLevelTwo(String levelOneID){
        List<Object> params = new ArrayList<Object>();
        StringBuffer sb = new StringBuffer();
        sb.append(" from ReagentLevelTwoEntity ");
        if(StringUtils.isNotBlank(levelOneID)){
            sb.append(" where levelOneSortID = ?");
            params.add(levelOneID);
        }
        return findListByHql(sb.toString(), params);
    }
}
