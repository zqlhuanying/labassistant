package com.labassistant.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.labassistant.annotation.MyAnnotation;

/**
 * 实验评论附件表
 * @author zql
 * @date 2015/11/02
 * 
 * ExpReviewAttchID					评论ID
 * AttchmentName					附件原始文件名
 * AttchmentLocation				附件所在本地磁盘路径
 * AttchmentServerPath				附件所在服务器磁盘路径
 * 
 */
@Table(name = "t_expreviewattch")
@Entity
public class ExpReviewAttchEntity extends ToStringBase {

	private static final long serialVersionUID = -8142665993550165447L;

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	private String expReviewAttchID;
	private String attchmentName;
	private String attchmentLocation;
	@MyAnnotation
	private String attchmentServerPath;
	
	public String getExpReviewAttchID() {
		return expReviewAttchID;
	}
	public void setExpReviewAttchID(String expReviewAttchID) {
		this.expReviewAttchID = expReviewAttchID;
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
	
	
}
