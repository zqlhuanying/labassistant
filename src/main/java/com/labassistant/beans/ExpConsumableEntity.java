package com.labassistant.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 实验耗材表
 * @author zql
 * @date 2015/09/17
 * 
 * ExpConsumableID			唯一ID
 * ExpInstructionID			实验说明书ID
 * ConsumableID				对应实验所需耗材ID
 * ConsumableName			耗材名
 * ConsumableType			规格
 * ConsumableCount			数量
 * ConsumableFactory		厂商
 * 
 */
@Table(name = "t_expconsumable")
@Entity
public class ExpConsumableEntity extends ToStringBase {

	private static final long serialVersionUID = 2209070756141918327L;
	
	private String expConsumableID;
	private String expInstructionID;
	private String consumableID;
	private String consumableName;
	private String consumableType;
	private int consumableCount;
	private String consumableFactory;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	public String getExpConsumableID() {
		return expConsumableID;
	}
	
	public void setExpConsumableID(String expConsumableID) {
		this.expConsumableID = expConsumableID;
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
	
	public String getConsumableName() {
		return consumableName;
	}

	public void setConsumableName(String consumableName) {
		this.consumableName = consumableName;
	}

	public String getConsumableType() {
		return consumableType;
	}
	
	public void setConsumableType(String consumableType) {
		this.consumableType = consumableType;
	}
	
	public int getConsumableCount() {
		return consumableCount;
	}
	
	public void setConsumableCount(int consumableCount) {
		this.consumableCount = consumableCount;
	}
	
	public String getConsumableFactory() {
		return consumableFactory;
	}
	
	public void setConsumableFactory(String consumableFactory) {
		this.consumableFactory = consumableFactory;
	}

}
