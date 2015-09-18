package com.labassistant.service.myexp;

import java.util.List;

import org.springframework.stereotype.Service;

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
	
}
