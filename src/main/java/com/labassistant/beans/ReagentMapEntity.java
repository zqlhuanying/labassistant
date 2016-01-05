package com.labassistant.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

/**
 * 试剂厂商对应表
 * @author zql
 * @date 2015/09/17
 * 
 * ReagentMapID			试剂供应商关联ID
 * ReagentID			试剂ID
 * SupplierID			供应商ID
 * 
 */
@Table(name = "t_reagentmap")
@Entity
public class ReagentMapEntity extends ToStringBase {

	private static final long serialVersionUID = 8500032004250259327L;
	
	private String reagentMapID;
	private String reagentID;
	private String supplierID;
    private Date updateTime;

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	public String getReagentMapID() {
		return reagentMapID;
	}
	
	public void setReagentMapID(String reagentMapID) {
		this.reagentMapID = reagentMapID;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
