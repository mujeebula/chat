package com.mindfire.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
 * Conversation.java
 * 
 * An entity which stores the detail of the conversation
 * @author
 *
 */
@Entity
public class Conversation extends AbstractTimestampEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long conversationId;
	private Long createdBy;
	public Long getConversationId() {
		return conversationId;
	}
	public void setConversationId(Long conversationId) {
		this.conversationId = conversationId;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	@Override
	public String toString() {
		return "Conversation [conversationId=" + conversationId + ", createdBy=" + createdBy + "]";
	}
	
}
