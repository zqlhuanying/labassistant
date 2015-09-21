package com.labassistant.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 省份表
 * @author zql
 * @date 2015/09/21
 * 
 * CollegeID			院校ID
 * CollegeName			院校名称
 * CityID				城市ID
 * ProvinceID			省份ID
 *
 */
@Table(name = "t_college")
@Entity
public class CollegeEntity extends ToStringBase {

	private static final long serialVersionUID = 4376033477583814368L;

	private String collegeID;
	private String collegeName;
	private String cityID;
	private String provinceID;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	public String getCollegeID() {
		return collegeID;
	}
	public void setCollegeID(String collegeID) {
		this.collegeID = collegeID;
	}
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public String getCityID() {
		return cityID;
	}
	public void setCityID(String cityID) {
		this.cityID = cityID;
	}
	public String getProvinceID() {
		return provinceID;
	}
	public void setProvinceID(String provinceID) {
		this.provinceID = provinceID;
	}
}
