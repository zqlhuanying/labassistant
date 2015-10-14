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

	/**
	 * 判断某试剂的供应商是否为推荐的供应商
	 * @param reagentID
	 * @param supplierID
	 * @return
	 */
	@Override
	public boolean isSuggestion(String reagentID, String supplierID){
		String hql = "from ReagentMapEntity where reagentID = ? and supplierID = ?";
		ReagentMapEntity reagentMap = findOneByHql(hql, reagentID, supplierID);
		return reagentMap.getIsSuggestion() > 0;
	}
	
	/**
	 * 获取试剂的建议提供商
	 * @param reagentID
	 * @return
	 */
	@Override
	public ReagentMapEntity getSuggestionSupplier(String reagentID){
		List<ReagentMapEntity> lists = getListByReagentID(reagentID);
		ReagentMapEntity result = null;
		if(lists != null){
			for(ReagentMapEntity list : lists){
				if(isSuggestion(reagentID, list.getSupplierID())){
					result = list;
					break;
				}
			}
		}
		return result;
	}
}
