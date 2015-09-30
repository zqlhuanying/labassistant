package com.labassistant.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * 用户类
 * @author zql
 * @date 2015/09/07
 *
 *
 * UserID			唯一ID
 * NickName			用户昵称
 * Pwd				用户密码
 * eMail			邮箱
 * TelNo			手机号
 * ProvinceID		省份
 * CityID			城市
 * CollegeID		院校
 * MajorID			专业
 * EducationID		学历
 * TitleID			职称
 * nState			用户状态	0-正常，1-禁用
 * nSource			注册来源	0-注册，1-微信，2-微博，3-QQ
 * 
 */
@Table(name = "t_user")
@Entity
public class SysUserEntity extends ToStringBase{

	private static final long serialVersionUID = 7305392489449935819L;
	
	private String userID;
	private String nickName;
	private String pwd;
	private String eMail;
	private String telNo;
	private String provinceID;
	private String cityID;
	private String collegeID;
	private String majorID;
	private String educationID;
	private String titleID;
	private int nState;
	private int nSource;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	public String getUserID() {
		return userID;
	}
	
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public String getNickName() {
		return nickName;
	}
	
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String geteMail() {
		return eMail;
	}
	
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	
	public String getTelNo() {
		return telNo;
	}
	
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	
	public String getProvinceID() {
		return provinceID;
	}
	
	public void setProvinceID(String provinceID) {
		this.provinceID = provinceID;
	}
	
	public String getCityID() {
		return cityID;
	}
	
	public void setCityID(String cityID) {
		this.cityID = cityID;
	}
	
	public String getCollegeID() {
		return collegeID;
	}
	
	public void setCollegeID(String collegeID) {
		this.collegeID = collegeID;
	}
	
	public String getMajorID() {
		return majorID;
	}
	
	public void setMajorID(String majorID) {
		this.majorID = majorID;
	}
	
	public String getEducationID() {
		return educationID;
	}
	
	public void setEducationID(String educationID) {
		this.educationID = educationID;
	}
	
	public String getTitleID() {
		return titleID;
	}
	
	public void setTitleID(String titleID) {
		this.titleID = titleID;
	}
	
	public int getnState() {
		return nState;
	}
	
	public void setnState(int nState) {
		this.nState = nState;
	}
	
	public int getnSource() {
		return nSource;
	}
	
	public void setnSource(int nSource) {
		this.nSource = nSource;
	}
	
}
