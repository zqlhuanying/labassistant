package com.labassistant.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 实验评论详细分类表
 * @author zql
 * @date 2015/11/02
 * 
 * ExpReviewDetailID			实验评论详细分类ID	
 * ExpReviewID					评论ID
 * ExpReviewOptID				评论项ID
 * ExpReviewOptScore			评论项得分
 * 
 */
@Table(name = "t_expreviewdetail")
@Entity
public class ExpReviewDetailEntity extends ToStringBase {

	private static final long serialVersionUID = 1357342245117479830L;

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	private String expReviewDetailID;
	private String expReviewID;
	private String expReviewOptID;
	private Integer expReviewOptScore;
	
	public String getExpReviewDetailID() {
		return expReviewDetailID;
	}
	public void setExpReviewDetailID(String expReviewDetailID) {
		this.expReviewDetailID = expReviewDetailID;
	}
	public String getExpReviewID() {
		return expReviewID;
	}
	public void setExpReviewID(String expReviewID) {
		this.expReviewID = expReviewID;
	}
	public String getExpReviewOptID() {
		return expReviewOptID;
	}
	public void setExpReviewOptID(String expReviewOptID) {
		this.expReviewOptID = expReviewOptID;
	}
	public Integer getExpReviewOptScore() {
		return expReviewOptScore;
	}
	public void setExpReviewOptScore(Integer expReviewOptScore) {
		this.expReviewOptScore = expReviewOptScore;
	}
	
	
}
