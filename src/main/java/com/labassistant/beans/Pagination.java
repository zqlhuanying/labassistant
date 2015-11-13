package com.labassistant.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页对象
 * @author zql
 * @date 2015/11/13
 */
public class Pagination<T> extends ToStringBase {

	/**
	 * 说明：根据上一页最后一条记录的uuid查找到当前行，返回此行接下去的N行
	 */
	
	
	private static final long serialVersionUID = 6401038468085474734L;
	
	private List<T> rows = new ArrayList<T>();				// 存放结果集
	
	public Pagination(List<T> _rows){
		rows = _rows;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}	
}
