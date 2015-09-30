package com.labassistant.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.labassistant.beans.CollegeEntity;
import com.labassistant.dao.service.BaseAbstractService;

/**
 * 院校
 * @author zql
 * @date 2015/09/29
 */
@Service
public class CollegeServiceImpl extends BaseAbstractService<CollegeEntity>
		implements CollegeService {

	@Override
	public List<CollegeEntity> getColleges(String provinceID, String cityID){
		List<Object> params = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" from CollegeEntity where ");
		if(StringUtils.isNotBlank(provinceID)){
			sb.append(" and provinceID = ?");
			params.add(provinceID);
		}
		if(StringUtils.isNotBlank(cityID)){
			sb.append(" and cityID = ?");
			params.add(cityID);
		}
		String queryHql = sb.toString();
		// 若params中没有参数，则表示全表查询
        if(params.size() == 0){
            queryHql = queryHql.replace("where", "");
        } else {
            queryHql = queryHql.replaceFirst("and", "");
        }
		List<CollegeEntity> lists = findListByHql(queryHql, params);
		return lists;
	}
}
