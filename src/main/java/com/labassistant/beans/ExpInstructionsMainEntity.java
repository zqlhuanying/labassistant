package com.labassistant.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 实验说明书主表
 * @author zql
 * @date 2015/09/16
 * 
 * ExpInstructionID			实验说明书ID
 * ExperimentName			实验说明书名称
 * ExperimentDesc			实验说明书简介
 * ExperimentTheory			实验说明书原理
 * ProvideUser				说明书提供者
 * SupplierID				供应商ID
 * SupplierName				供应商名称
 * ProductNum				货号
 * ExpCategoryID			所属实验大类
 * ExpSubCategoryID			所属实验子类
 * CreateDate				创建日期
 * ExpVersion				说明书版本
 * AllowDownload			是否允许下载
 * FilterStr				组合搜索字段
 * ReviewCount				评论数量
 * DownloadCount			下载数量
 * 
 */
@Table(name = "t_expinstructionsmain")
@Entity
public class ExpInstructionsMainEntity extends ToStringBase {

	private static final long serialVersionUID = 2518684466082301664L;
	
	private String expInstructionID;
	private String experimentName;
	private String experimentDesc;
	private String experimentTheory;
	private String provideUser;
	private String supplierID;
	private String supplierName;
	private String productNum;
	private String expCategoryID;
	private String expSubCategoryID;
	private Date createDate;
	private int expVersion;
	private int allowDownload;
	private String filterStr;
	private int reviewCount;
	private int downloadCount;
	
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
	
	public String getExperimentName() {
		return experimentName;
	}
	
	public void setExperimentName(String experimentName) {
		this.experimentName = experimentName;
	}
	
	public String getExperimentDesc() {
		return experimentDesc;
	}
	
	public void setExperimentDesc(String experimentDesc) {
		this.experimentDesc = experimentDesc;
	}
	
	public String getExperimentTheory() {
		return experimentTheory;
	}
	
	public void setExperimentTheory(String experimentTheory) {
		this.experimentTheory = experimentTheory;
	}
	
	public String getProvideUser() {
		return provideUser;
	}
	
	public void setProvideUser(String provideUser) {
		this.provideUser = provideUser;
	}
	
	public String getSupplierID() {
		return supplierID;
	}
	
	public void setSupplierID(String supplierID) {
		this.supplierID = supplierID;
	}
	
	public String getSupplierName() {
		return supplierName;
	}
	
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	public String getProductNum() {
		return productNum;
	}
	
	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}
	
	public String getExpCategoryID() {
		return expCategoryID;
	}
	
	public void setExpCategoryID(String expCategoryID) {
		this.expCategoryID = expCategoryID;
	}
	
	public String getExpSubCategoryID() {
		return expSubCategoryID;
	}
	
	public void setExpSubCategoryID(String expSubCategoryID) {
		this.expSubCategoryID = expSubCategoryID;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public int getExpVersion() {
		return expVersion;
	}
	
	public void setExpVersion(int expVersion) {
		this.expVersion = expVersion;
	}
	
	public int getAllowDownload() {
		return allowDownload;
	}
	
	public void setAllowDownload(int allowDownload) {
		this.allowDownload = allowDownload;
	}
	
	public String getFilterStr() {
		return filterStr;
	}
	
	public void setFilterStr(String filterStr) {
		this.filterStr = filterStr;
	}
	
	public int getReviewCount() {
		return reviewCount;
	}
	
	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}
	
	public int getDownloadCount() {
		return downloadCount;
	}
	
	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}

}
