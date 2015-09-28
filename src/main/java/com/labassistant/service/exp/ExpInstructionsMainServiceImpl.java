package com.labassistant.service.exp;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labassistant.beans.ExpInstructionsMainEntity;
import com.labassistant.dao.service.BaseAbstractService;
import com.labassistant.service.myexp.MyExpInstructionService;

/**
 * 实验说明书主表
 * @author zql
 * @date 2015/09/16
 */
@Service
public class ExpInstructionsMainServiceImpl extends BaseAbstractService<ExpInstructionsMainEntity>
		implements ExpInstructionsMainService {

	@Autowired
	private MyExpInstructionService myExpInstructionService;
	
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
	 * 判断说明书是否已存在
	 */
	@Override
	public boolean isExists(String expInstructionID){
		String hql = "from ExpInstructionsMainEntity where expInstructionID = ?";
		return getCount(hql, true, expInstructionID) > 0;
	}
	
	/**
	 * 判断说明书是否已下载
	 */
	@Override
	public boolean isDownload(String userID, String expInstructionID){
		return myExpInstructionService.isDownload(userID, expInstructionID);
	}
	
	@Override
	public int getReturnLimit() {
		return returnLimit;
	}

	@Override
	public void setReturnLimit(int returnLimit) {
		this.returnLimit = returnLimit;
	}

	/**
	 * add by jimmie
	 * 根据指定的子类ID返回说明书列表
	 * @return
	 */
	@Override
	public List getInstructionsBySubCategoryID(String expSubCategoryID) {
		
		String hql = "from ExpInstructionsMainEntity where expSubCategoryID = ?";
		
		return findListByHql(hql,expSubCategoryID);
	}

	@Override
	public List getInstructionsByFilter(String filterStr) {
		
		String hql = "from ExpInstructionsMainEntity where filterStr like ?";
		
		filterStr = "%|" + filterStr + "|%";
		
		System.out.println("filterStr:" + filterStr);
	
		
		return findListByHql(hql,filterStr);
	}

}
