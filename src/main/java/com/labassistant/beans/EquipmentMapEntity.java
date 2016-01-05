package com.labassistant.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

/**
 * 设备厂商对应表
 * @author zql
 * @date 2015/09/21
 * 
 * EquipmentMapID			试剂供应商关联ID
 * EquipmentID				设备ID
 * SupplierID				供应商ID
 */
@Table(name = "t_equipmentmap")
@Entity
public class EquipmentMapEntity extends ToStringBase {

	private static final long serialVersionUID = -3447136830691510351L;

	private String equipmentMapID;
	private String equipmentID;
	private String supplierID;
    private Date updateTime;

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	public String getEquipmentMapID() {
		return equipmentMapID;
	}
	public void setEquipmentMapID(String equipmentMapID) {
		this.equipmentMapID = equipmentMapID;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
