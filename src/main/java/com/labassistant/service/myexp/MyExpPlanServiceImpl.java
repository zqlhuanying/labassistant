package com.labassistant.service.myexp;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.labassistant.beans.MyExpPlanEntity;
import com.labassistant.dao.service.BaseAbstractService;
import com.labassistant.utils.DateUtil;

/**
 * 我的实验计划服务
 * @author zql
 * @date 2015/09/21
 */
@Service
public class MyExpPlanServiceImpl extends BaseAbstractService<MyExpPlanEntity>
		implements MyExpPlanService {

	@Override
	public List<MyExpPlanEntity> getPlan(String userID, Date date){
		String hql = "from MyExpPlanEntity where userID = ? and planDate = ?";
		List<MyExpPlanEntity> lists = findListByHql(hql, userID, date);
		return lists;
	}
	
	@Override
	public void setPlan(MyExpPlanEntity plan){
		plan.setPlanOfYear(DateUtil.getYear(plan.getPlanDate()));
		plan.setPlanOfDate(DateUtil.getMonth(plan.getPlanDate()));
		save(plan);
	}
}
