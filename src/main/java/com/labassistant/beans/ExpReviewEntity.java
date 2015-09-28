package com.labassistant.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 实验评论表
 * @author zql
 * @date 2015/09/22
 * 
 * ExpReviewID					评论ID
 * ExpInstructionID				实验说明书ID
 * ReviewerID					评论者ID
 * ReviewDate					评论日期
 * ReviewYear					评论年度
 * ReviewMonth					评论月份
 * ExpScore						总评分
 * ReviewInfo					评论内容
 * AgreeCount					赞同数
 * DisagreeCount				不赞同数
 * 
 */
@Table(name = "t_expreview")
@Entity
public class ExpReviewEntity extends ToStringBase {

	private static final long serialVersionUID = 5820429471065517149L;

	private String expReviewID;
	private String expInstructionID;
	private String reviewerID;
	private Date reviewDate;
	private int reviewYear;
	private int reviewMonth;
	private int expScore;
	private String reviewInfo;
	private int agreeCount;
	private int disagreeCount;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	public String getExpReviewID() {
		return expReviewID;
	}
	public void setExpReviewID(String expReviewID) {
		this.expReviewID = expReviewID;
	}
	public String getExpInstructionID() {
		return expInstructionID;
	}
	public void setExpInstructionID(String expInstructionID) {
		this.expInstructionID = expInstructionID;
	}
	public String getReviewerID() {
		return reviewerID;
	}
	public void setReviewerID(String reviewerID) {
		this.reviewerID = reviewerID;
	}
	public Date getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}
	public int getReviewYear() {
		return reviewYear;
	}
	public void setReviewYear(int reviewYear) {
		this.reviewYear = reviewYear;
	}
	public int getReviewMonth() {
		return reviewMonth;
	}
	public void setReviewMonth(int reviewMonth) {
		this.reviewMonth = reviewMonth;
	}
	public int getExpScore() {
		return expScore;
	}
	public void setExpScore(int expScore) {
		this.expScore = expScore;
	}
	public String getReviewInfo() {
		return reviewInfo;
	}
	public void setReviewInfo(String reviewInfo) {
		this.reviewInfo = reviewInfo;
	}
	public int getAgreeCount() {
		return agreeCount;
	}
	public void setAgreeCount(int agreeCount) {
		this.agreeCount = agreeCount;
	}
	public int getDisagreeCount() {
		return disagreeCount;
	}
	public void setDisagreeCount(int disagreeCount) {
		this.disagreeCount = disagreeCount;
	}
}
