package com.mindfire.dto;

import java.util.Date;

/*
 * ConversationMessageDTO.java
 * 
 * A DTO used to send the message to user
 * @author
 */
public class ConversationMessageDTO  {

	private Long messageId;
	private Long conversationId;
	private Long senderParticipantId;
	private String textMessage;
    private Date createdAt;
    
    

	public ConversationMessageDTO(Long messageId, Long conversationId, Long senderParticipantId, String textMessage,
			Date createdAt) {
		this.messageId = messageId;
		this.conversationId = conversationId;
		this.senderParticipantId = senderParticipantId;
		this.textMessage = textMessage;
		this.createdAt = createdAt;
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public Long getConversationId() {
		return conversationId;
	}

	public void setConversationId(Long conversationId) {
		this.conversationId = conversationId;
	}

	public Long getSenderParticipantId() {
		return senderParticipantId;
	}

	public void setSenderParticipantId(Long senderParticipantId) {
		this.senderParticipantId = senderParticipantId;
	}

	public String getTextMessage() {
		return textMessage;
	}

	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "ConversationMessageDTO [messageId=" + messageId + ", conversationId=" + conversationId
				+ ", senderParticipantId=" + senderParticipantId + ", textMessage=" + textMessage + ", createdAt="
				+ createdAt + "]";
	}
}
