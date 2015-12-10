package com.labassistant.service.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.labassistant.beans.ReagentMapEntity;
import com.labassistant.dao.service.BaseAbstractService;

/**
 * 试剂厂商对应服务类
 * @author zql
 * @date 2015/09/17
 */
@Service
public class ReagentMapServiceImpl extends
		BaseAbstractService<ReagentMapEntity> implements ReagentMapService {

	/**
	 * 获取试剂所对应的厂商
	 * @param reagentID
	 * @return
	 */
	@Override
	public List<ReagentMapEntity> getListByReagentID(String reagentID){
        List<Object> params = new ArrayList<Object>();
        StringBuffer sb = new StringBuffer();
        sb.append(" from ReagentMapEntity ");
        if(StringUtils.isNotBlank(reagentID)){
            sb.append(" where reagentID = ?");
            params.add(reagentID);
        }
        return findListByHql(sb.toString(), params);
	}
}
