package com.labassistant.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 城市表
 * @author zql
 * @date 2015/09/21
 * 
 * CityID			城市ID
 * ProvinceID		省份ID
 * CityName			城市名称
 * 
 */
@Table(name = "t_city")
@Entity
public class CityEntity extends ToStringBase {

	private static final long serialVersionUID = -8571033330842866310L;

	private String cityID;
	private String provinceID;
	private String cityName;
	
	@Id
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
	
	public String getCityName() {
		return cityName;
	}
	
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
}
