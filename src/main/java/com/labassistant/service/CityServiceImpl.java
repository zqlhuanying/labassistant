package com.labassistant.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.labassistant.beans.CityEntity;
import com.labassistant.dao.service.BaseAbstractService;

/**
 * 城市
 * @author zql
 * @date 2015/09/29
 */
@Service
public class CityServiceImpl extends BaseAbstractService<CityEntity> implements
		CityService {

	@Override
	public List<CityEntity> getCities(String provinceID){
		List<Object> params = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" from CityEntity ");
		if(StringUtils.isNotBlank(provinceID)){
			sb.append(" where provinceID = ?");
			params.add(provinceID);
		}
		List<CityEntity> lists = findListByHql(sb.toString(), params);
		return lists;
	}
}
