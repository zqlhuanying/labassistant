package com.labassistant.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 专业表
 * @author zql
 * @date 2015/09/21
 * 
 * MajorID			专业ID
 * MajorName		专业名称
 *
 */
@Table(name = "t_major")
@Entity
public class MajorEntity extends ToStringBase {

	private static final long serialVersionUID = -3191695329007921987L;

	private String majorID;
	private String majorName;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	public String getMajorID() {
		return majorID;
	}
	public void setMajorID(String majorID) {
		this.majorID = majorID;
	}
	public String getMajorName() {
		return majorName;
	}
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
}
