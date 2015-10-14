package com.labassistant.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 实验子类表
 * @author jimmie
 * @date 2015/09/17
 * 
 * ExpSubCategoryID			实验子类ID
 * ExpSubCategoryName		实验子类名称
 * ExpCategoryID			对应实验大类ID
 * 
 */

@Table(name = "t_expsubcategory")
@Entity
public class ExpSubCategoryEntity extends ToStringBase {
	
	private static final long serialVersionUID = 9053586459882051426L;

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	private String expSubCategoryID;
	
	@Column(length=100)
	private String expSubCategoryName;
	
	@Column(length=100)
	private String expCategoryID;

	public String getExpSubCategoryID() {
		return expSubCategoryID;
	}

	public void setExpSubCategoryID(String expSubCategoryID) {
		this.expSubCategoryID = expSubCategoryID;
	}

	public String getExpSubCategoryName() {
		return expSubCategoryName;
	}

	public void setExpSubCategoryName(String expSubCategoryName) {
		this.expSubCategoryName = expSubCategoryName;
	}

	public String getExpCategoryID() {
		return expCategoryID;
	}

	public void setExpCategoryID(String expCategoryID) {
		this.expCategoryID = expCategoryID;
	}	
	
	
}
