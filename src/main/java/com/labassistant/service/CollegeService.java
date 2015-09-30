package com.labassistant.service;

import java.util.List;

import com.labassistant.beans.CollegeEntity;
import com.labassistant.dao.service.IBaseAbstractService;

/**
 * 
 * @author zql
 * @date 2015/09/29
 */
public interface CollegeService extends IBaseAbstractService<CollegeEntity> {

	public List<CollegeEntity> getColleges(String provinceID, String cityID);
}
