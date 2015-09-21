package com.labassistant.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 职称表
 * @author zql
 * @date 2015/09/21
 * 
 * TitleID			职称ID
 * TitleName		职称名
 *
 */
@Table(name = "t_title")
@Entity
public class TitleEntity extends ToStringBase {

	private static final long serialVersionUID = -3919482876649577331L;

	private String titleID;
	private String titleName;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	public String getTitleID() {
		return titleID;
	}
	public void setTitleID(String titleID) {
		this.titleID = titleID;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
}
