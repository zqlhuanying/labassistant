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
 * ReviewOptID					评论项ID
 * ReviewOptScore				评论项得分
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
	private String reviewOptID;
	private Integer reviewOptScore;
	
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
	public String getReviewOptID() {
		return reviewOptID;
	}
	public void setReviewOptID(String reviewOptID) {
		this.reviewOptID = reviewOptID;
	}
	public Integer getReviewOptScore() {
		return reviewOptScore;
	}
	public void setReviewOptScore(Integer reviewOptScore) {
		this.reviewOptScore = reviewOptScore;
	}
	
}
