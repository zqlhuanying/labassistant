package com.labassistant.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 供应商表
 * @author zql
 * @date 2015/09/15
 * 
 * SupplierID		供应商ID
 * SupplierName		供应商名称
 *
 */
@Table(name = "t_supplier")
@Entity
public class SupplierEntity extends ToStringBase {

	private static final long serialVersionUID = -5999344872009840808L;
	
	private int supplierID;
	private String supplierName;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	public int getSupplierID() {
		return supplierID;
	}
	
	public void setSupplierID(int supplierID) {
		this.supplierID = supplierID;
	}
	
	public String getSupplierName() {
		return supplierName;
	}
	
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
}
