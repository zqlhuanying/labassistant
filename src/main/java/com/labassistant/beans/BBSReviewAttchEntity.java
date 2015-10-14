package com.labassistant.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * BBS评论附件表
 * @author zql
 * @date 2015/10/13
 * 
 * ReviewAttchID			评论附件ID
 * ReviewID					评论ID
 * AttchPath				附件存储路径
 * AttchTimeStamp			附件插入时间戳
 * 
 */
@Table(name = "t_bbsreviewattch")
@Entity
public class BBSReviewAttchEntity extends ToStringBase {

	private static final long serialVersionUID = 8890696193780681108L;

	private String reviewAttchID;
	private String reviewID;
	private String attchPath;
	private String attchTimeStamp;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	public String getReviewAttchID() {
		return reviewAttchID;
	}
	public void setReviewAttchID(String reviewAttchID) {
		this.reviewAttchID = reviewAttchID;
	}
	public String getReviewID() {
		return reviewID;
	}
	public void setReviewID(String reviewID) {
		this.reviewID = reviewID;
	}
	public String getAttchPath() {
		return attchPath;
	}
	public void setAttchPath(String attchPath) {
		this.attchPath = attchPath;
	}
	public String getAttchTimeStamp() {
		return attchTimeStamp;
	}
	public void setAttchTimeStamp(String attchTimeStamp) {
		this.attchTimeStamp = attchTimeStamp;
	}
	
	
}
