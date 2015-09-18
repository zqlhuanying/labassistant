package com.labassistant.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 实验大类表
 * @author jimmie
 * @date 2015/09/17
 * 
 * ExpCategoryID			实验大类ID
 * ExpCategoryName			实验大类名称
 * 
 */
@Table(name = "t_expcategory")
@Entity
public class ExpCategoryEntity  extends ToStringBase {

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	private String expCategoryID;
	
	@Column(length=100)
	private String expCategoryName;
	
	public String getExpCategoryID() {
		return expCategoryID;
	}
	
	public void setExpCategoryID(String expCategoryID) {
		this.expCategoryID = expCategoryID;
	}
	
	public String getExpCategoryName() {
		return expCategoryName;
	}
	
	public void setExpCategoryName(String expCategoryName) {
		this.expCategoryName = expCategoryName;
	}
	
	
}
