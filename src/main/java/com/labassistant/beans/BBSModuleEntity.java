package com.labassistant.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * BBS模块表
 * @author zql
 * @date 2015/10/13
 * 
 * ModuleID				模块ID
 * ModuleName			模块名称
 * ModuleImgPath		模块图标路径
 * AllowShow			模块是否显示
 * 
 */
@Table(name = "t_bbsmodule")
@Entity
public class BBSModuleEntity extends ToStringBase {

	private static final long serialVersionUID = 567715108299709245L;

	private String moduleID;
	private String moduleName;
	private String moduleImgPath;
	private int allowShow;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	public String getModuleID() {
		return moduleID;
	}
	public void setModuleID(String moduleID) {
		this.moduleID = moduleID;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getModuleImgPath() {
		return moduleImgPath;
	}
	public void setModuleImgPath(String moduleImgPath) {
		this.moduleImgPath = moduleImgPath;
	}
	public int getAllowShow() {
		return allowShow;
	}
	public void setAllowShow(int allowShow) {
		this.allowShow = allowShow;
	}
	
}
