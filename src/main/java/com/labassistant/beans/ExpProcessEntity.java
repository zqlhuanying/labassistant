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
 * ExpStepID			实验步骤ID
 * ExpInstructionID		实验说明书ID
 * StepNum				实验步骤编号
 * ExpStepDesc			步骤描述
 * ExpStepTime			步骤耗时表
 * 
 */
@Table(name = "t_expstep")
@Entity
public class ExpProcessEntity extends ToStringBase {

	private static final long serialVersionUID = -6112041639410639172L;

	private String expStepID;
	private String expInstructionID;
	private int stepNum;
	private String expStepDesc;
	private float expStepTime;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	public String getExpStepID() {
		return expStepID;
	}
	
	public void setExpStepID(String expStepID) {
		this.expStepID = expStepID;
	}
	
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
	
	public String getExpStepDesc() {
		return expStepDesc;
	}
	
	public void setExpStepDesc(String expStepDesc) {
		this.expStepDesc = expStepDesc;
	}
	
	public float getExpStepTime() {
		return expStepTime;
	}
	
	public void setExpStepTime(float expStepTime) {
		this.expStepTime = expStepTime;
	}
	
}
