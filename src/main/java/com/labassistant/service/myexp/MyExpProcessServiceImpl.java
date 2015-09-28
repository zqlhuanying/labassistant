package com.labassistant.service.myexp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labassistant.beans.MyExpMainEntity;
import com.labassistant.beans.MyExpProcessEntity;
import com.labassistant.dao.service.BaseAbstractService;

/**
 * 我的实验步骤服务类
 * @author zql
 * @date 2015/09/16
 */
@Service
public class MyExpProcessServiceImpl extends BaseAbstractService<MyExpProcessEntity> implements
		MyExpProcessService {

	@Autowired
	MyExpMainService myExpMainService;
	
	/**
	 * 根据实验ID获取相关记录
	 * @param myExpID
	 * @return
	 */
	@Override
	public List<MyExpProcessEntity> getList(String myExpID){
		String hql = "from MyExpProcessEntity where myExpID = ?";
		List<MyExpProcessEntity> lists = findListByHql(hql, myExpID);
		return lists;
	}
	
	/**
	 * 设置实验步骤备注
	 */
	@Override
	public void perProcessMemo(MyExpProcessEntity myExpProcess){
		String hql = "from MyExpProcessEntity where myExpID = ? and expStepID = ?";
		MyExpProcessEntity expProcess = findOneByHql(hql, myExpProcess.getMyExpID(), myExpProcess.getExpStepID());
		if(expProcess != null){
			expProcess.setProcessMemo(myExpProcess.getProcessMemo());
			update(expProcess);
		} else {
			// 还需要设置说明书ID
			MyExpMainEntity myExpMainEntity = myExpMainService.getByExpID(myExpProcess.getMyExpID());
			myExpProcess.setExpInstructionID(myExpMainEntity.getExpInstructionID());
			save(myExpProcess);
		}
	}
	
	/**
	 * 获取实验步骤备注
	 */
	@Override
	public MyExpProcessEntity getPerProcessMemo(String myExpID, String expStepID){
		String hql = "from MyExpProcessEntity where myExpID = ? and expStepID = ?";
		return findOneByHql(hql, myExpID, expStepID);
	}
}
