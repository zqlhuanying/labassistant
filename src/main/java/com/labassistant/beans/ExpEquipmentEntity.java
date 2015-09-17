package com.labassistant.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 实验设备表
 * @author zql
 * @date 2015/09/17
 * 
 * ExpEquipmentID			唯一ID
 * ExpInstructionID			实验说明书ID
 * EquipmentID				设备ID
 * EquipmentName			设备名称
 * EquipmentFactory			设备厂商
 * 
 */
@Table(name = "t_expequipment")
@Entity
public class ExpEquipmentEntity extends ToStringBase {

	private static final long serialVersionUID = 341512673198624365L;

	private String expEquipmentID;
	private String expInstructionID;
	private String equipmentID;
	private String equipmentName;
	private String equipmentFactory;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	public String getExpEquipmentID() {
		return expEquipmentID;
	}
	
	public void setExpEquipmentID(String expEquipmentID) {
		this.expEquipmentID = expEquipmentID;
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
	
	public String getEquipmentName() {
		return equipmentName;
	}
	
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	
	public String getEquipmentFactory() {
		return equipmentFactory;
	}
	
	public void setEquipmentFactory(String equipmentFactory) {
		this.equipmentFactory = equipmentFactory;
	}
	
}
