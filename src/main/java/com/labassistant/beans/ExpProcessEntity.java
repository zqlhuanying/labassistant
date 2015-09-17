package com.labassistant.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 实验步骤表
 * @author zql
 * @date 2015/09/14
 * 
 * 
 * ExpInstructionID			实验说明书ID
 * StepNum					实验步骤编号
 * StepDetail 				实验步骤说明
 * 
 */
@Table(name = "t_expprocess")
@Entity
public class ExpProcessEntity extends ToStringBase {

	private static final long serialVersionUID = -6112041639410639172L;

	private String expInstructionID;
	private int stepNum;
	private String stepDetail;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	public String getExpInstructionID() {
		return expInstructionID;
	}

	public void setExpInstructionID(String expInstructionID) {
		this.expInstructionID = expInstructionID;
	}
	
	public int getStepNum() {
		return stepNum;
	}
	
	public void setStepNum(int stepNum) {
		this.stepNum = stepNum;
	}
	
	public String getStepDetail() {
		return stepDetail;
	}
	
	public void setStepDetail(String stepDetail) {
		this.stepDetail = stepDetail;
	}
	
}
