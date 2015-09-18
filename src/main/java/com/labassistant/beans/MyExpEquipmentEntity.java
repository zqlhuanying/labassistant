package com.labassistant.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 我的实验设备表
 * @author zql
 * @date 2015/09/17
 * 
 * MyExpEquipmentID			唯一ID
 * MyExpID					我的实验ID
 * ExpInstructionID			实验说明书ID
 * EquipmentID				设备ID
 * SupplierID				供应商ID
 * 
 */
@Table(name = "t_myexpequipment")
@Entity
public class MyExpEquipmentEntity extends ToStringBase {

	private static final long serialVersionUID = -2142356175022706285L;

	private String myExpEquipmentID;
	private String myExpID;
	private String expInstructionID;
	private String equipmentID;
	private String supplierID;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	public String getMyExpEquipmentID() {
		return myExpEquipmentID;
	}
	
	public void setMyExpEquipmentID(String myExpEquipmentID) {
		this.myExpEquipmentID = myExpEquipmentID;
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
	
	public String getEquipmentID() {
		return equipmentID;
	}
	
	public void setEquipmentID(String equipmentID) {
		this.equipmentID = equipmentID;
	}
	
	public String getSupplierID() {
		return supplierID;
	}
	
	public void setSupplierID(String supplierID) {
		this.supplierID = supplierID;
	}
	
}
