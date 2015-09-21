package com.labassistant.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 耗材厂商对应表
 * @author zql
 * @date 2015/09/21
 * 
 * ConsumableMapID		耗材供应商关联ID
 * ConsumableID			耗材ID	
 * SupplierID			供应商ID
 * IsSuggestion			是否建议供应商
 *
 */
@Table(name = "t_consumablemap")
@Entity
public class ConsumableMapEntity extends ToStringBase {

	private static final long serialVersionUID = 7416141965628258540L;

	private String consumableMapID;
	private String consumableID;
	private String supplierID;
	private int isSuggestion;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	public String getConsumableMapID() {
		return consumableMapID;
	}
	public void setConsumableMapID(String consumableMapID) {
		this.consumableMapID = consumableMapID;
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
	public int getIsSuggestion() {
		return isSuggestion;
	}
	public void setIsSuggestion(int isSuggestion) {
		this.isSuggestion = isSuggestion;
	}
}
