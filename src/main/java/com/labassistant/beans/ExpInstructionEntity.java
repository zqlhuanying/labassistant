package com.labassistant.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.labassistant.annotation.MyAnnotation;

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
@Table(name = "t_expinstruction")
@Entity
public class ExpInstructionEntity extends ToStringBase {

	private static final long serialVersionUID = 2518684466082301664L;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	private String expInstructionID;
	
	@Column(length=200)
	private String experimentName;
	
	@Column(length=1000)
	private String experimentDesc;
	
	@Lob
	@Column(name="experimentTheory", columnDefinition="TEXT")
	private String experimentTheory;
	
	@Column(length=40)
	private String provideUser;
	
	@Column(length=32)
	private String supplierID;
	
	@Column(length=40)
	private String supplierName;
	
	@Column(length=40)
	private String productNum;
	
	@Column(length=32)
	private String expCategoryID;
	
	@Column(length=32)
	private String expSubCategoryID;
	
	@Column(columnDefinition="DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  
	private Date createDate;
	
	@Column(columnDefinition="Integer")
	private Integer expVersion;
	
	@Column(columnDefinition="Integer")
	private Integer allowDownload;
	
	@Column(length=400)
	private String filterStr;
	
	@Column(columnDefinition="Integer")
	private Integer reviewCount;
	
	@Column(columnDefinition="Integer")
	@MyAnnotation
	private Integer downloadCount;

    @Transient
    private String expCategoryName;
    @Transient
    private String expSubCategoryName;
	
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
	
	public Integer getExpVersion() {
		return expVersion;
	}
	
	public void setExpVersion(Integer expVersion) {
		this.expVersion = expVersion;
	}
	
	public Integer getAllowDownload() {
		return allowDownload;
	}
	
	public void setAllowDownload(Integer allowDownload) {
		this.allowDownload = allowDownload;
	}
	
	public String getFilterStr() {
		return filterStr;
	}
	
	public void setFilterStr(String filterStr) {
		this.filterStr = filterStr;
	}
	
	public Integer getReviewCount() {
		return reviewCount;
	}
	
	public void setReviewCount(Integer reviewCount) {
		this.reviewCount = reviewCount;
	}
	
	public Integer getDownloadCount() {
		return downloadCount;
	}
	
	public void setDownloadCount(Integer downloadCount) {
		this.downloadCount = downloadCount;
	}

    public String getExpCategoryName() {
        return expCategoryName;
    }

    public void setExpCategoryName(String expCategoryName) {
        this.expCategoryName = expCategoryName;
    }

    public String getExpSubCategoryName() {
        return expSubCategoryName;
    }

    public void setExpSubCategoryName(String expSubCategoryName) {
        this.expSubCategoryName = expSubCategoryName;
    }
}
