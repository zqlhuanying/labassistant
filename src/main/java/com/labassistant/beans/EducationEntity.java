package com.labassistant.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 学历表
 * @author zql
 * @date 2015/09/21
 * 
 * EducationID			学历ID
 * EducationName		学历名称
 *
 */
@Table(name = "t_education")
@Entity
public class EducationEntity extends ToStringBase {

	private static final long serialVersionUID = -2660071352797399344L;

	private String educationID;
	private String educationName;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	public String getEducationID() {
		return educationID;
	}
	public void setEducationID(String educationID) {
		this.educationID = educationID;
	}
	public String getEducationName() {
		return educationName;
	}
	public void setEducationName(String educationName) {
		this.educationName = educationName;
	}
}
