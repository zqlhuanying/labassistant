package com.labassistant.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 我的实验试剂表
 * @author zql
 * @date 2015/09/17
 * 
 * MyExpReagentID		唯一ID
 * MyExpID				我的实验ID
 * ExpInstructionID		实验说明书ID
 * ReagentID			试剂ID
 * SupplierID			供应商ID
 * 
 */
@Table(name = "t_myexpreagent")
@Entity
public class MyExpReagentEntity extends ToStringBase {

	private static final long serialVersionUID = 9028836112510519225L;

	private String myExpReagentID;
	private String myExpID;
	private String expInstructionID;
	private String reagentID;
	private String supplierID;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	public String getMyExpReagentID() {
		return myExpReagentID;
	}
	
	public void setMyExpReagentID(String myExpReagentID) {
		this.myExpReagentID = myExpReagentID;
	}
	
	public String getMyExpID() {
		return myExpID;
	}
	
	public void setMyExpID(String myExpID) {
		this.myExpID = myExpID;
	}
	
	public String getExpInstructionID() {
		return expInstructionID;
	}
	
	public void setExpInstructionID(String expInstructionID) {
		this.expInstructionID = expInstructionID;
	}
	
	public String getReagentID() {
		return reagentID;
	}
	
	public void setReagentID(String reagentID) {
		this.reagentID = reagentID;
	}
	
	public String getSupplierID() {
		return supplierID;
	}
	
	public void setSupplierID(String supplierID) {
		this.supplierID = supplierID;
	}
	
}
