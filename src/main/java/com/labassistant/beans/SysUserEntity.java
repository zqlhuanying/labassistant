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
	
	private String userId;
	private String nickName;
	private String pwd;
	private String eMail;
	private String telNo;
	private int provinceId;
	private int cityId;
	private int collegeId;
	private int majorId;
	private int educationId;
	private int titleId;
	private int nState;
	private int nSource;
	
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
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
	
	public int getProvinceId() {
		return provinceId;
	}
	
	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}
	
	public int getCityId() {
		return cityId;
	}
	
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	
	public int getCollegeId() {
		return collegeId;
	}
	
	public void setCollegeId(int collegeId) {
		this.collegeId = collegeId;
	}
	
	public int getMajorId() {
		return majorId;
	}
	
	public void setMajorId(int majorId) {
		this.majorId = majorId;
	}
	
	public int getEducationId() {
		return educationId;
	}
	
	public void setEducationId(int educationId) {
		this.educationId = educationId;
	}
	
	public int getTitleId() {
		return titleId;
	}
	
	public void setTitleId(int titleId) {
		this.titleId = titleId;
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
