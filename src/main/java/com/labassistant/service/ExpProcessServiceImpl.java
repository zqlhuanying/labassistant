package com.labassistant.service;

import java.util.List;

import com.labassistant.beans.ExpProcessEntity;
import com.labassistant.dao.service.BaseAbstractService;

/**
 * 实验步骤服务
 * 
 * @author zql
 * @date 2015/09/14
 */
public class ExpProcessServiceImpl extends
		BaseAbstractService<ExpProcessEntity> implements ExpProcessService {

	/**
	 * 根据实验说明书号，获取全部实验步骤
	 * @param expInstructionID 实验说明书号
	 * @return
	 */
	@Override
	public List<ExpProcessEntity> getProcessLists(String expInstructionID){
		String hql = "from ExpProcessEntity where expInstructionID = ?";
		List<ExpProcessEntity> lists = findListByHql(hql, expInstructionID);
		return lists;
	}
	
	/**
	 * 获取某实验下的特定步骤
	 * @param expInstructionID 实验说明书号
	 * @param stepNum 步骤编号
	 * @return
	 */
	@Override
	public ExpProcessEntity getProcess(String expInstructionID, int stepNum){
		String hql = "from ExpProcessEntity where expInstructionID = ? and stepNum = ?";
		ExpProcessEntity list = findOneByHql(hql, expInstructionID, stepNum);
		return list;
	}
}
