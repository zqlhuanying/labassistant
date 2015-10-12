package com.labassistant.service.myexp;

import java.util.Date;
import java.util.List;

import com.labassistant.beans.MyExpPlanEntity;
import com.labassistant.dao.service.IBaseAbstractService;

/**
 * 
 * @author zql
 * @date 2015/09/21
 */
public interface MyExpPlanService extends IBaseAbstractService<MyExpPlanEntity> {

	public List<MyExpPlanEntity> getAllPlans(String userID);
	
	public List<MyExpPlanEntity> getPlans(String userID, Date date);
	
	public void setPlan(MyExpPlanEntity plan);
}
