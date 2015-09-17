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
 * SupplierType		供应商分类	0-试剂供应商，1-耗材供应商，2-设备供应商
 * Contacts			联系人
 * TelNo			联系电话
 * MobilePhone		移动电话
 * eMail			eMail地址
 * Address			厂址或办公地址
 * 
 */
@Table(name = "t_supplier")
@Entity
public class SupplierEntity extends ToStringBase {

	private static final long serialVersionUID = -5999344872009840808L;
	
	private String supplierID;
	private String supplierName;
	private int supplierType;
	private String contacts;
	private String telNo;
	private String mobilePhone;
	private String eMail;
	private String address;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	public String getSupplierID() {
		return supplierID;
	}
	
	public void setSupplierID(String supplierID) {
		this.supplierID = supplierID;
	}
	
	public String getSupplierName() {
		return supplierName;
	}
	
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public int getSupplierType() {
		return supplierType;
	}

	public void setSupplierType(int supplierType) {
		this.supplierType = supplierType;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
