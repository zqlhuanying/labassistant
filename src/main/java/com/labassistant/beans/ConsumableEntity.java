package com.labassistant.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

/**
 * 耗材表
 * @author zql
 * @date 2015/09/21
 * 
 * ConsumableID			耗材ID
 * ConsumableName		耗材名称
 * ConsumableType		规格
 */
@Table(name = "t_consumable")
@Entity
public class ConsumableEntity extends ToStringBase {

	private static final long serialVersionUID = -4344292601287168997L;

	private String consumableID;
	private String consumableName;
	private String consumableType;
    private Date updateTime;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
