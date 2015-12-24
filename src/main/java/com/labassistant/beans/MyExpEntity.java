package com.labassistant.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.labassistant.annotation.MyAnnotation;

/**
 * 我的实验主表
 * 
 * @author zql
 * @date 2015/09/15
 * 
 * MyExpID				我的实验ID
 * ExpInstructionID		实验说明书ID
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
 * ProjectName          项目名称
 * ResearchName         研究课题名称
 * TaskName             研究任务名称
 */
@Table(name = "t_myexp")
@Entity
public class MyExpEntity extends ToStringBase {

	private static final long serialVersionUID = -4489449065670136874L;

	private String myExpID;
	private String expInstructionID;
	private String userID;
	private Date createTime;
	private int createYear;
	private int createMonth;
	private Date finishTime;
	private int expVersion;
	private int isReviewed;
	@MyAnnotation
	private Integer isCreateReport;
	@MyAnnotation
	private Integer isUpload;
	@MyAnnotation
	private String reportName;
	private String reportLocation;
	@MyAnnotation
	private String reportServerPath;
	private int expState;
	private String expMeno;
    private String projectName;
    private String researchName;
    private String taskName;

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

	public String getExpInstructionID() {
		return expInstructionID;
	}
	
	public void setExpInstructionID(String expInstructionID) {
		this.expInstructionID = expInstructionID;
	}
	
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	@Column(columnDefinition="DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")  
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

	@Column(columnDefinition="DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")  
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

	public Integer getIsCreateReport() {
		return isCreateReport;
	}

	public void setIsCreateReport(Integer isCreateReport) {
		this.isCreateReport = isCreateReport;
	}

	public Integer getIsUpload() {
		return isUpload;
	}

	public void setIsUpload(Integer isUpload) {
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getResearchName() {
        return researchName;
    }

    public void setResearchName(String researchName) {
        this.researchName = researchName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
