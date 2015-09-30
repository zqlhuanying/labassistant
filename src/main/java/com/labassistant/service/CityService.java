package com.labassistant.service;

import java.util.List;

import com.labassistant.beans.CityEntity;
import com.labassistant.dao.service.IBaseAbstractService;

/**
 * 
 * @author zql
 * @date 2015/09/29
 */
public interface CityService extends IBaseAbstractService<CityEntity> {

	public List<CityEntity> getCities(String provinceID);
}
