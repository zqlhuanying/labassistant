package com.labassistant.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * BBS主题附件表
 * @author zql
 * @date 2015/10/13
 * 
 * TopicAttchID			主题附件ID
 * TopicID				主题ID
 * AttchPath			附件存储路径
 * AttchTimeStamp		附件插入时间戳
 * 
 */
@Table(name = "t_bbstopicattch")
@Entity
public class BBSTopicAttchEntity extends ToStringBase {

	private static final long serialVersionUID = -8905041413256331268L;

	private String topicAttchID;
	private String topicID;
	private String attchPath;
	private String attchTimeStamp;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	public String getTopicAttchID() {
		return topicAttchID;
	}
	public void setTopicAttchID(String topicAttchID) {
		this.topicAttchID = topicAttchID;
	}
	public String getTopicID() {
		return topicID;
	}
	public void setTopicID(String topicID) {
		this.topicID = topicID;
	}
	public String getAttchPath() {
		return attchPath;
	}
	public void setAttchPath(String attchPath) {
		this.attchPath = attchPath;
	}
	public String getAttchTimeStamp() {
		return attchTimeStamp;
	}
	public void setAttchTimeStamp(String attchTimeStamp) {
		this.attchTimeStamp = attchTimeStamp;
	}
	
	
}
