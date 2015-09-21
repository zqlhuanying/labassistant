package com.labassistant.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 学生导师对应表
 * @author zql
 * @date 2015/09/21
 * 
 * UserTeacherMapID			唯一ID
 * UserID					用户ID
 * TeacherID				导师ID
 *
 */
@Table(name = "t_userteachermap")
@Entity
public class UserTeacherMapEntity extends ToStringBase {

	private static final long serialVersionUID = 599827527840187301L;

	private String userTeacherMapID;
	private String userID;
	private String teacherID;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	public String getUserTeacherMapID() {
		return userTeacherMapID;
	}
	public void setUserTeacherMapID(String userTeacherMapID) {
		this.userTeacherMapID = userTeacherMapID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getTeacherID() {
		return teacherID;
	}
	public void setTeacherID(String teacherID) {
		this.teacherID = teacherID;
	}
}
