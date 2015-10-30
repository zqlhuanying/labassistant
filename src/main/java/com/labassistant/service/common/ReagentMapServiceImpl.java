package com.labassistant.service.common;

import java.util.List;

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
		String hql = "from ReagentMapEntity where reagentID = ?";
		List<ReagentMapEntity> lists = findListByHql(hql, reagentID);
		return lists;
	}

	
}
