package com.labassistant.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 我的实验主表
 * 
 * @author zql
 * @date 2015/09/15
 * 
 * MyExpID				我的实验ID
 * UserID				创建者ID
 * CreateTime			创建时间
 * CreateYear			创建年份
 * CreateMonth			创建月份
 * FinishTime			完成时间
 * ExpVersion			实验版本
 * IsReviewed			是否已评价
 * IsCreateReport		是否已生成实验报告
 * IsUpload				是否已经上载到服务器
 * ReportName			实验报告名称
 * ReportLocation		实验报告本地路径
 * ReportServerPath		实验报告服务器端路径
 * ExpState				实验状态	0-进行中，1-暂停中，2-已完成，3-已生成报告
 * ExpMemo				实验备注
 * 
 */
@Table(name = "t_myexpmain")
@Entity
public class MyExpMainEntity extends ToStringBase {

	private static final long serialVersionUID = -4489449065670136874L;

	private String myExpID;
	private String userID;
	private Date createTime;
	private int createYear;
	private int createMonth;
	private Date finishTime;
	private int expVersion;
	private int isReviewed;
	private int isCreateReport;
	private int isUpload;
	private String reportName;
	private String reportLocation;
	private String reportServerPath;
	private int expState;
	private String expMeno;

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	public String getMyExpID() {
		return myExpID;
	}

	public void setMyExpID(String myExpID) {
		this.myExpID = myExpID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getCreateYear() {
		return createYear;
	}

	public void setCreateYear(int createYear) {
		this.createYear = createYear;
	}

	public int getCreateMonth() {
		return createMonth;
	}

	public void setCreateMonth(int createMonth) {
		this.createMonth = createMonth;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public int getExpVersion() {
		return expVersion;
	}

	public void setExpVersion(int expVersion) {
		this.expVersion = expVersion;
	}

	public int getIsReviewed() {
		return isReviewed;
	}

	public void setIsReviewed(int isReviewed) {
		this.isReviewed = isReviewed;
	}

	public int getIsCreateReport() {
		return isCreateReport;
	}

	public void setIsCreateReport(int isCreateReport) {
		this.isCreateReport = isCreateReport;
	}

	public int getIsUpload() {
		return isUpload;
	}

	public void setIsUpload(int isUpload) {
		this.isUpload = isUpload;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getReportLocation() {
		return reportLocation;
	}

	public void setReportLocation(String reportLocation) {
		this.reportLocation = reportLocation;
	}

	public String getReportServerPath() {
		return reportServerPath;
	}

	public void setReportServerPath(String reportServerPath) {
		this.reportServerPath = reportServerPath;
	}

	public int getExpState() {
		return expState;
	}

	public void setExpState(int expState) {
		this.expState = expState;
	}

	public String getExpMeno() {
		return expMeno;
	}

	public void setExpMeno(String expMeno) {
		this.expMeno = expMeno;
	}

}
