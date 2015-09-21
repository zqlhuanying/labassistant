package com.labassistant.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 我的实验计划表
 * @author zql
 * @date 2015/09/21
 * 
 * MyExpPlanID			我的实验计划ID
 * UserID				用户ID
 * PlanDate				计划日期
 * PlanOfYear			计划年份
 * PlanOfDate			计划月份
 * ExpInstructionID		实验说明书ID
 * ExperimentName		实验说明书名称
 * 
 *
 */
@Table(name = "t_myexpplan")
@Entity
public class MyExpPlanEntity extends ToStringBase {

	private static final long serialVersionUID = 7019270117162881228L;

	private String myExpPlanID;
	private String userID;
	private Date planDate;
	private int planOfYear;
	private int planOfDate;
	private String expInstructionID;
	private String experimentName;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	public String getMyExpPlanID() {
		return myExpPlanID;
	}
	public void setMyExpPlanID(String myExpPlanID) {
		this.myExpPlanID = myExpPlanID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public Date getPlanDate() {
		return planDate;
	}
	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}
	public int getPlanOfYear() {
		return planOfYear;
	}
	public void setPlanOfYear(int planOfYear) {
		this.planOfYear = planOfYear;
	}
	public int getPlanOfDate() {
		return planOfDate;
	}
	public void setPlanOfDate(int planOfDate) {
		this.planOfDate = planOfDate;
	}
	public String getExpInstructionID() {
		return expInstructionID;
	}
	public void setExpInstructionID(String expInstructionID) {
		this.expInstructionID = expInstructionID;
	}
	public String getExperimentName() {
		return experimentName;
	}
	public void setExperimentName(String experimentName) {
		this.experimentName = experimentName;
	}
}
