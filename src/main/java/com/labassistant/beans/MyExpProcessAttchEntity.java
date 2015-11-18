package com.labassistant.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.labassistant.annotation.MyAnnotation;

/**
 * 我的实验步骤附件表
 * @author zql
 * @date 2015/09/23
 * 
 * MyExpProcessAttchID			唯一ID
 * MyExpID						我的实验ID
 * ExpInstructionID				实验说明书ID
 * ExpStepID					实验步骤ID
 * AttchmentName				附件名称
 * AttchmentLocation			附件本地存放路径
 * AttchmentServerPath			附件服务器存放路径
 * IsUpload						是否已经上传
 * Title						图片的标题
 * Description					图片的描述
 * imgStream					图片二进制流，经过BASE64编码后的字符串
 */
@Table(name = "t_myexpprocessattch")
@Entity
public class MyExpProcessAttchEntity extends ToStringBase {

	private static final long serialVersionUID = 3289413740965221786L;

	private String myExpProcessAttchID;
	private String myExpID;
	private String expInstructionID;
	private String expStepID;
	private String attchmentName;
	private String attchmentLocation;
	@MyAnnotation
	private String attchmentServerPath;
	@MyAnnotation
	private Integer isUpload;
	private String title;
	private String description;
	private String imgStream;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	public String getMyExpProcessAttchID() {
		return myExpProcessAttchID;
	}
	public void setMyExpProcessAttchID(String myExpProcessAttchID) {
		this.myExpProcessAttchID = myExpProcessAttchID;
	}
	public String getMyExpID() {
		return myExpID;
	}
	public void setMyExpID(String myExpID) {
		this.myExpID = myExpID;
	}
	public String getExpInstructionID() {
		return expInstructionID;
	}
	public void setExpInstructionID(String expInstructionID) {
		this.expInstructionID = expInstructionID;
	}
	public String getExpStepID() {
		return expStepID;
	}
	public void setExpStepID(String expStepID) {
		this.expStepID = expStepID;
	}
	public String getAttchmentName() {
		return attchmentName;
	}
	public void setAttchmentName(String attchmentName) {
		this.attchmentName = attchmentName;
	}
	public String getAttchmentLocation() {
		return attchmentLocation;
	}
	public void setAttchmentLocation(String attchmentLocation) {
		this.attchmentLocation = attchmentLocation;
	}
	public String getAttchmentServerPath() {
		return attchmentServerPath;
	}
	public void setAttchmentServerPath(String attchmentServerPath) {
		this.attchmentServerPath = attchmentServerPath;
	}
	public Integer getIsUpload() {
		return isUpload;
	}
	public void setIsUpload(Integer isUpload) {
		this.isUpload = isUpload;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Transient
	public String getImgStream() {
		return imgStream;
	}
}
