package com.mindfire.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
 * ConversationParticipant.java
 * 
 * Mapping table used to store the detail of users involved in a particular conversation
 * @author
 *
 */
@Entity
public class ConversationParticipant {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long participantId;
	private Long conversationId;
	private Long userId;
	
	public Long getParticipantId() {
		return participantId;
	}
	public void setParticipantId(Long participantId) {
		this.participantId = participantId;
	}
	public Long getConversationId() {
		return conversationId;
	}
	public void setConversationId(Long conversationId) {
		this.conversationId = conversationId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
