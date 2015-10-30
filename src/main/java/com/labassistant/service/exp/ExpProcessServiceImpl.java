package com.labassistant.service.exp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.labassistant.beans.ExpStepEntity;
import com.labassistant.dao.service.BaseAbstractService;

/**
 * 实验步骤服务
 * 
 * @author zql
 * @date 2015/09/14
 */
@Service
public class ExpProcessServiceImpl extends
		BaseAbstractService<ExpStepEntity> implements ExpProcessService {

	/**
	 * 根据实验说明书号，获取全部实验步骤
	 * @param expInstructionID 实验说明书号
	 * @return
	 */
	@Override
	public List<ExpStepEntity> getProcessLists(String expInstructionID){
		String hql = "from ExpStepEntity where expInstructionID = ? order by stepNum";
		List<ExpStepEntity> lists = findListByHql(hql, expInstructionID);
		return lists;
	}
	
	/**
	 * 获取某实验下的特定步骤
	 * @param expInstructionID 实验说明书号
	 * @param stepNum 步骤编号
	 * @return
	 */
	@Override
	public ExpStepEntity getProcess(String expInstructionID, int stepNum){
		String hql = "from ExpStepEntity where expInstructionID = ? and stepNum = ?";
		ExpStepEntity list = findOneByHql(hql, expInstructionID, stepNum);
		return list;
	}
}
