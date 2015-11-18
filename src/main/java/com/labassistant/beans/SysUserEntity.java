package com.labassistant.beans;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.labassistant.annotation.MyAnnotation;


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
 * access_token		第三方登录时的凭证
 * f_validCode		找回密码时的验证码
 * f_timestamp		找回密码时的时间戳
 * Icon				用户头像URL地址
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
	private String access_token;
	private String f_validCode;
	private Timestamp f_timestamp;
	@MyAnnotation
	private String icon;
	private String iconStream;
	private String iconName;
	
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

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getF_validCode() {
		return f_validCode;
	}

	public void setF_validCode(String f_validCode) {
		this.f_validCode = f_validCode;
	}

	public Timestamp getF_timestamp() {
		return f_timestamp;
	}

	public void setF_timestamp(Timestamp f_timestamp) {
		this.f_timestamp = f_timestamp;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Transient
	public String getIconStream() {
		return iconStream;
	}

	public void setIconStream(String iconStream) {
		this.iconStream = iconStream;
	}

	@Transient
	public String getIconName() {
		return iconName;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}
	
}
