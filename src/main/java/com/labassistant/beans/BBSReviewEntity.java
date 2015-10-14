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

/**
 * BBS评论表
 * @author zql
 * @date 2015/10/13
 * 
 * ReviewID				评论ID
 * TopicID				主题ID
 * ModuleID				模块ID
 * ReviewerID			评论者ID
 * Reviewer				评论者名称
 * ReviewDetail			评论内容
 * ParentReviewID		上一级评论ID
 * ReviewDateTime		评论时间
 * 
 */
@Table(name = "t_bbsreview")
@Entity
public class BBSReviewEntity extends ToStringBase {

	private static final long serialVersionUID = -3477153904363482860L;

	private String reviewID;
	private String topicID;
	private String moduleID;
	private String reviewerID;
	private String reviewer;
	private String reviewDetail;
	private String parentReviewID;
	private Date reviewDateTime;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	public String getReviewID() {
		return reviewID;
	}
	public void setReviewID(String reviewID) {
		this.reviewID = reviewID;
	}
	public String getTopicID() {
		return topicID;
	}
	public void setTopicID(String topicID) {
		this.topicID = topicID;
	}
	public String getModuleID() {
		return moduleID;
	}
	public void setModuleID(String moduleID) {
		this.moduleID = moduleID;
	}
	public String getReviewerID() {
		return reviewerID;
	}
	public void setReviewerID(String reviewerID) {
		this.reviewerID = reviewerID;
	}
	public String getReviewer() {
		return reviewer;
	}
	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}
	public String getReviewDetail() {
		return reviewDetail;
	}
	public void setReviewDetail(String reviewDetail) {
		this.reviewDetail = reviewDetail;
	}
	public String getParentReviewID() {
		return parentReviewID;
	}
	public void setParentReviewID(String parentReviewID) {
		this.parentReviewID = parentReviewID;
	}
	@Column(columnDefinition="DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	public Date getReviewDateTime() {
		return reviewDateTime;
	}
	public void setReviewDateTime(Date reviewDateTime) {
		this.reviewDateTime = reviewDateTime;
	}
	
}
