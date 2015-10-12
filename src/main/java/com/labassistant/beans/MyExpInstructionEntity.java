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
 * 
 * @author zql
 * @date 2015/09/18
 * 
 * MyExpInstructionID		我的说明书ID
 * ExpInstructionID			实验说明书ID
 * UserID					创建者ID
 * DownloadTime				下载时间
 * 
 */
@Table(name = "t_myexpinstruction")
@Entity
public class MyExpInstructionEntity extends ToStringBase {

	private static final long serialVersionUID = -4522008665378311669L;

	private String myExpInstructionID;
	private String expInstructionID;
	private String userID;
	private Date downloadTime;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	public String getMyExpInstructionID() {
		return myExpInstructionID;
	}
	
	public void setMyExpInstructionID(String myExpInstructionID) {
		this.myExpInstructionID = myExpInstructionID;
	}
	
	public String getExpInstructionID() {
		return expInstructionID;
	}
	
	public void setExpInstructionID(String expInstructionID) {
		this.expInstructionID = expInstructionID;
	}
	
	public String getUserID() {
		return userID;
	}
	
	public void setUserID(String userID) {
		this.userID = userID;
	}
	@Column(columnDefinition="DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")  
	public Date getDownloadTime() {
		return downloadTime;
	}
	
	public void setDownloadTime(Date downloadTime) {
		this.downloadTime = downloadTime;
	}
	
}
