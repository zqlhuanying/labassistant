package com.labassistant.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 我的实验步骤表
 * @author zql
 * @date 2015/09/15
 * 
 * MyExpProcessID			唯一ID
 * MyExpID					我的实验ID
 * ExpInstructionID			实验说明书ID
 * ExpStepID				实验步骤ID
 * StepNum					实验步骤编号
 * ExpStepDesc				步骤描述
 * ExpStepTime				步骤耗时
 * IsUseTimer				是否启动了计时器
 * ProcessMemo				实验步骤备注
 * 
 */
@Table(name = "t_myexpprocess")
@Entity
public class MyExpProcessEntity extends ToStringBase {

	private static final long serialVersionUID = -7978969959890503974L;

	private String myExpProcessID;
	private String myExpID;
	private String expInstructionID;
	private String expStepID;
	private int stepNum;
	private String expStepDesc;
	private float expStepTime;
	private int isUseTimer;
	private String processMemo;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	public String getMyExpProcessID() {
		return myExpProcessID;
	}
	
	public void setMyExpProcessID(String myExpProcessID) {
		this.myExpProcessID = myExpProcessID;
	}
	
	public String getMyExpID() {
		return myExpID;
	}
	
	public void setMyExpID(String myExpID) {
		this.myExpID = myExpID;
	}
	
	public String getExpInstructionID() {
		return expInstructionID;
	}
	
	public void setExpInstructionID(String expInstructionID) {
		this.expInstructionID = expInstructionID;
	}
	
	public String getExpStepID() {
		return expStepID;
	}
	
	public void setExpStepID(String expStepID) {
		this.expStepID = expStepID;
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
	
	public int getIsUseTimer() {
		return isUseTimer;
	}
	
	public void setIsUseTimer(int isUseTimer) {
		this.isUseTimer = isUseTimer;
	}
	
	public String getProcessMemo() {
		return processMemo;
	}
	
	public void setProcessMemo(String processMemo) {
		this.processMemo = processMemo;
	}
}
