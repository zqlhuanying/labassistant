package com.labassistant.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 我的实验耗材表
 * @author zql
 * @date 2015/09/17
 * 
 * MyExpConsumableID		唯一ID
 * MyExpID					我的实验ID
 * ExpInstructionID			实验说明书ID
 * ConsumableID				耗材ID
 * SupplierID				供应商ID
 * 
 */
@Table(name = "t_myexpconsumable")
@Entity
public class MyExpConsumableEntity extends ToStringBase {

	private static final long serialVersionUID = -621027322816247354L;

	private String myExpConsumableID;
	private String myExpID;
	private String expInstructionID;
	private String consumableID;
	private String supplierID;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	public String getMyExpConsumableID() {
		return myExpConsumableID;
	}
	
	public void setMyExpConsumableID(String myExpConsumableID) {
		this.myExpConsumableID = myExpConsumableID;
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
	
	public String getConsumableID() {
		return consumableID;
	}
	
	public void setConsumableID(String consumableID) {
		this.consumableID = consumableID;
	}
	
	public String getSupplierID() {
		return supplierID;
	}
	
	public void setSupplierID(String supplierID) {
		this.supplierID = supplierID;
	}
	
}
