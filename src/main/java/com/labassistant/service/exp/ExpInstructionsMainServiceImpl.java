package com.labassistant.service.exp;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labassistant.beans.ExpInstructionsMainEntity;
import com.labassistant.dao.service.BaseAbstractService;
import com.labassistant.service.myexp.MyExpProcessService;
import com.labassistant.utils.JSONUtil;

/**
 * 实验说明书主表
 * @author zql
 * @date 2015/09/16
 */
@Service
public class ExpInstructionsMainServiceImpl extends BaseAbstractService<ExpInstructionsMainEntity>
		implements ExpInstructionsMainService {

	@Autowired
	private MyExpProcessService myExpProcessService;
	
	private int returnLimit;	// 设置返回的数据记录
	
	public ExpInstructionsMainServiceImpl(){
		returnLimit = 10;
	}
	
	/**
	 * 获取热门的说明书
	 * 根据下载量来反映热门程度
	 * 无法解决map向javabean的转换
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getHotInstructions(){
		// todo
		String sql = "select * from t_expinstructionsmain order by downloadcount desc limit ?";
		List<Map<String, Object>> lists = findListMapBySql(sql, returnLimit);
		/*List<ExpInstructionsMainEntity> returnLists = new ArrayList<ExpInstructionsMainEntity>();
		System.out.println(lists);
		for(Map<String, Object> list : lists){
			returnLists.add(JSONUtil.map2Bean(list, ExpInstructionsMainEntity.class));
		}*/
		return lists;
	}

	/**
	 * 判断说明书是否已下载
	 */
	@Override
	public boolean isDownload(String expInstructionID){
		return myExpProcessService.isExists(expInstructionID);
	}
	
	@Override
	public int getReturnLimit() {
		return returnLimit;
	}

	@Override
	public void setReturnLimit(int returnLimit) {
		this.returnLimit = returnLimit;
	}

}
