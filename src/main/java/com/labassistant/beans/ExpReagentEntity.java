package com.labassistant.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 实验试剂表
 * @author zql
 * @date 2015/09/14
 * 
 * ExpReagentID				唯一标识
 * ExpInstructionID			实验说明书ID
 * ReagentID				试剂ID
 * ReagentName				试剂名称
 * ReagentCommonName		试剂通用名称
 * CreateMethod				配置方法
 * ReagentSpec				规格
 * UseAmount				用量
 * SupplierID				供应商ID
 */
@Table(name = "t_expreagent")
@Entity
public class ExpReagentEntity extends ToStringBase {

	private static final long serialVersionUID = 5107703477384147931L;

	private String expReagentID;
	private String expInstructionID;
	private String reagentID;
	private String reagentName;
	private String reagentCommonName;
	private String CreateMethod;
	private String reagentSpec;
	private int useAmount;
	private String supplierID;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	public String getExpReagentID() {
		return expReagentID;
	}

	public void setExpReagentID(String expReagentID) {
		this.expReagentID = expReagentID;
	}
	
	public String getExpInstructionID() {
		return expInstructionID;
	}

	public void setExpInstructionID(String expInstructionID) {
		this.expInstructionID = expInstructionID;
	}

	public String getReagentID() {
		return reagentID;
	}

	public void setReagentID(String reagentID) {
		this.reagentID = reagentID;
	}
	
	public String getReagentName() {
		return reagentName;
	}
	
	public void setReagentName(String reagentName) {
		this.reagentName = reagentName;
	}
	
	public String getReagentCommonName() {
		return reagentCommonName;
	}
	
	public void setReagentCommonName(String reagentCommonName) {
		this.reagentCommonName = reagentCommonName;
	}
	
	public String getCreateMethod() {
		return CreateMethod;
	}
	
	public void setCreateMethod(String createMethod) {
		CreateMethod = createMethod;
	}
	
	public String getReagentSpec() {
		return reagentSpec;
	}
	
	public void setReagentSpec(String reagentSpec) {
		this.reagentSpec = reagentSpec;
	}
	
	public int getUseAmount() {
		return useAmount;
	}
	
	public void setUseAmount(int useAmount) {
		this.useAmount = useAmount;
	}
	
	public String getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(String supplierID) {
		this.supplierID = supplierID;
	}
}
