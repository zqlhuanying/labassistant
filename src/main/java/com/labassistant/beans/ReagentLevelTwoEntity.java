package com.labassistant.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 试剂二级分类表
 * @author zql
 * @date 2015/09/15
 * 
 * LevelTwoSortID		试剂二级分类ID
 * LevelTwoSortName		试剂二级分类名称
 * LevelOneSortID		试剂一级分类ID
 * 
 */
@Table(name = "t_reagentleveltwo")
@Entity
public class ReagentLevelTwoEntity extends ToStringBase {

	private static final long serialVersionUID = 6791084130141725100L;
	
	private String levelTwoSortID;
	private String levelTwoSortName;
	private String levelOneSortID;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	public String getLevelTwoSortID() {
		return levelTwoSortID;
	}
	
	public void setLevelTwoSortID(String levelTwoSortID) {
		this.levelTwoSortID = levelTwoSortID;
	}
	
	public String getLevelTwoSortName() {
		return levelTwoSortName;
	}
	
	public void setLevelTwoSortName(String levelTwoSortName) {
		this.levelTwoSortName = levelTwoSortName;
	}
	
	public String getLevelOneSortID() {
		return levelOneSortID;
	}
	
	public void setLevelOneSortID(String levelOneSortID) {
		this.levelOneSortID = levelOneSortID;
	}
	
}
