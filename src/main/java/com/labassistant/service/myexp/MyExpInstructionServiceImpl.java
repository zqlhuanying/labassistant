package com.labassistant.service.myexp;


import java.util.List;

import org.springframework.stereotype.Service;

import com.labassistant.beans.MyExpInstructionEntity;
import com.labassistant.dao.service.BaseAbstractService;

/**
 * 我的说明书服务
 * @author zql
 * @date 2015/09/18
 */
@Service
public class MyExpInstructionServiceImpl extends
		BaseAbstractService<MyExpInstructionEntity> implements
		MyExpInstructionService {

	/**
	 * 判断说明书是否已下载
	 * @param expInstructionID
	 * @return
	 */
	@Override
	public boolean isDownload(String userID, String expInstructionID){
		String hql = "from MyExpInstructionEntity where expInstructionID = ? and userID = ?";
		return getCount(hql, true, expInstructionID, userID) > 0;
	}

	@Override
	public List<MyExpInstructionEntity> getMyExpInstructions(String userID){
		String hql = "from MyExpInstructionEntity where userID = ?";
		return findListByHql(hql, userID);
	}
}
