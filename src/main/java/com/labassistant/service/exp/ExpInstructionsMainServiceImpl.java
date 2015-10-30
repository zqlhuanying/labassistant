package com.labassistant.service.exp;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labassistant.beans.ExpInstructionEntity;
import com.labassistant.dao.service.BaseAbstractService;
import com.labassistant.service.myexp.MyExpInstructionService;

/**
 * 实验说明书主表
 * @author zql
 * @date 2015/09/16
 */
@Service
public class ExpInstructionsMainServiceImpl extends BaseAbstractService<ExpInstructionEntity>
		implements ExpInstructionsMainService {

	@Autowired
	private MyExpInstructionService myExpInstructionService;
	@Autowired
	private ExpProcessService expProcessService;
	@Autowired
	private ExpConsumableService expConsumableService;
	@Autowired
	private ExpEquipmentService expEquipmentService;
	@Autowired
	private ExpReagentService expReagentService;
	
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
		// TODO
		String sql = "select * from t_expinstruction order by downloadcount desc limit ?";
		List<Map<String, Object>> lists = findListMapBySql(sql, returnLimit);
		/*List<ExpInstructionEntity> returnLists = new ArrayList<ExpInstructionEntity>();
		System.out.println(lists);
		for(Map<String, Object> list : lists){
			returnLists.add(JSONUtil.map2Bean(list, ExpInstructionEntity.class));
		}*/
		return lists;
	}

	/**
	 * 下载说明书
	 */
	@Override
	public Map<String, Object> downloadInstruction(String expInstructionID){
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		// 封装实验主表的数据
		ExpInstructionEntity expInstruction = get(expInstructionID);
		map.put("expInstructionMain", expInstruction);
		// 封装实验步骤表的数据
		map.put("expProcess", expProcessService.getProcessLists(expInstructionID));
		// 封装实验试剂表的数据
		map.put("expReagent", expReagentService.getExpReagentLists(expInstructionID));
		// 封装实验耗材表的数据
		map.put("expConsumable", expConsumableService.getExpConsumableLists(expInstructionID));
		// 封装实验设备表的数据
		map.put("expEquipment", expEquipmentService.getExpEquipmentLists(expInstructionID));
		return map;
	}
	
	/**
	 * 判断说明书是否已发布
	 */
	@Override
	public boolean isPublic(String expInstructionID){
		String hql = "from ExpInstructionEntity where expInstructionID = ? and allowDownload = 1";
		return getCount(hql, true, expInstructionID) > 0;
	}
	
	/**
	 * 判断说明书是否允许下载
	 */
	@Override
	public boolean isAllowDownload(String userID, String expInstructionID){
		String hql = "from ExpInstructionEntity where expInstructionID = ? and provideUser = ? and allowDownload = 0";
		boolean isOwn = getCount(hql, true, expInstructionID, userID) > 0;
		boolean isPublic = isPublic(expInstructionID);
		return isPublic || isOwn;
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
		
		String hql = "from ExpInstructionEntity where expSubCategoryID = ?";
		
		return findListByHql(hql,expSubCategoryID);
	}

	@Override
	public List getInstructionsByFilter(String filterStr) {
		
		String hql = "from ExpInstructionEntity where filterStr like ?";
		
		filterStr = "%" + filterStr + "%";
		
		System.out.println("filterStr:" + filterStr);
	
		
		return findListByHql(hql,filterStr);
	}

}
