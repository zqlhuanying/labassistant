package com.labassistant.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 试剂一级分类表
 * @author zql
 * @date 2015/09/15
 * 
 * LevelOneSortID		试剂一级分类ID
 * LevelOneSortName		试剂一级分类名称
 *
 */
@Table(name = "t_reagentlevelone")
@Entity
public class ReagentLevelOneEntity extends ToStringBase {

	private static final long serialVersionUID = 4769286418317028465L;
	
	private int levelOneSortID;
	private String levelOneSortName;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	public int getLevelOneSortID() {
		return levelOneSortID;
	}
	
	public void setLevelOneSortID(int levelOneSortID) {
		this.levelOneSortID = levelOneSortID;
	}
	
	public String getLevelOneSortName() {
		return levelOneSortName;
	}
	
	public void setLevelOneSortName(String levelOneSortName) {
		this.levelOneSortName = levelOneSortName;
	}
	
}
