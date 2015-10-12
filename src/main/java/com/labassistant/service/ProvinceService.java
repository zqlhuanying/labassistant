package com.labassistant.service;

import java.util.List;

import com.labassistant.beans.ProvinceEntity;
import com.labassistant.dao.service.IBaseAbstractService;

/**
 * 
 * @author zql
 * @date 2015/09/29
 */
public interface ProvinceService extends IBaseAbstractService<ProvinceEntity> {

	public List<Object> provinceAndCity();
}
