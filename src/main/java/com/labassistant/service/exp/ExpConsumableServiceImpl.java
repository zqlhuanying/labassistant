package com.labassistant.service.exp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.labassistant.beans.ExpConsumableEntity;
import com.labassistant.dao.service.BaseAbstractService;

/**
 * 实验耗材服务
 * @author zql
 * @date 2015/09/17
 */
@Service
public class ExpConsumableServiceImpl extends
		BaseAbstractService<ExpConsumableEntity> implements
		ExpConsumableService {

	/**
	 * 根据实验说明书号，获取全部耗材
	 * @param expInstructionID 实验说明书号
	 * @return
	 */
	@Override
	public List<ExpConsumableEntity> getExpConsumableLists(String expInstructionID){
		String hql = "from ExpConsumableEntity where expInstructionID = ?";
		List<ExpConsumableEntity> lists = findListByHql(hql, expInstructionID);
		return lists;
	}
}
