package com.labassistant.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * BBS主题表
 * @author zql
 * @date 2015/10/13
 * 
 * TopicID				主题ID
 * ModuleID				模块ID
 * TopicName			主题名称
 * TopicDetail			主题内容
 * TopicCreatorID		主题创建者ID
 * TopicCreator			主题创建者
 * CreateDateTime		创建时间
 * ReviewCount			评论数量
 * 
 */
@Table(name = "t_bbstopic")
@Entity
public class BBSTopicEntity extends ToStringBase {

	private static final long serialVersionUID = 5510766057706521944L;

	private String topicID;
	private String moduleID;
	private String topicName;
	private String topicDetail;
	private String topicCreatorID;
	private String topicCreator;
	private Date createDateTime;
	private int reviewCount;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	public String getTopicID() {
		return topicID;
	}
	public void setTopicID(String topicID) {
		this.topicID = topicID;
	}
	public String getModuleID() {
		return moduleID;
	}
	public void setModuleID(String moduleID) {
		this.moduleID = moduleID;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public String getTopicDetail() {
		return topicDetail;
	}
	public void setTopicDetail(String topicDetail) {
		this.topicDetail = topicDetail;
	}
	public String getTopicCreatorID() {
		return topicCreatorID;
	}
	public void setTopicCreatorID(String topicCreatorID) {
		this.topicCreatorID = topicCreatorID;
	}
	public String getTopicCreator() {
		return topicCreator;
	}
	public void setTopicCreator(String topicCreator) {
		this.topicCreator = topicCreator;
	}
	@Column(columnDefinition="DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	public int getReviewCount() {
		return reviewCount;
	}
	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}
	
}
