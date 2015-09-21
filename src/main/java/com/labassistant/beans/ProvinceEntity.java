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
 * ProvinceID			省份ID
 * ProvinceName			省份名称
 *
 */
@Table(name = "t_province")
@Entity
public class ProvinceEntity extends ToStringBase {

	private static final long serialVersionUID = 4501949983027802499L;
	
	private String provinceID;
	private String provinceName;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	public String getProvinceID() {
		return provinceID;
	}
	
	public void setProvinceID(String provinceID) {
		this.provinceID = provinceID;
	}
	
	public String getProvinceName() {
		return provinceName;
	}
	
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

}
