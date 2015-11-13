package com.labassistant.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 实验评论项表
 * @author zql
 * @date 2015/11/02
 * 
 * ExpReviewOptID			评论项ID
 * ExpReviewOptName			评论项描述
 * 
 */
@Table(name = "t_expreviewopt")
@Entity
public class ExpReviewOptEntity extends ToStringBase {

	private static final long serialVersionUID = 6928317745763516308L;

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	private String expReviewOptID;
	private String expReviewOptName;
	
	public String getExpReviewOptID() {
		return expReviewOptID;
	}
	public void setExpReviewOptID(String expReviewOptID) {
		this.expReviewOptID = expReviewOptID;
	}
	public String getExpReviewOptName() {
		return expReviewOptName;
	}
	public void setExpReviewOptName(String expReviewOptName) {
		this.expReviewOptName = expReviewOptName;
	}
	
	
}
