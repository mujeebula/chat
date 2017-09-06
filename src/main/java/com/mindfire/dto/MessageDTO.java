package com.mindfire.dto;

/**
 * It is used to receive the message details sent by the user
 * @author
 *
 */
public class MessageDTO {

	private Long conversationId;
	private String textMessage;
	private Long senderId;
	
	
	
	public Long getConversationId() {
		return conversationId;
	}
	public void setConversationId(Long conversationId) {
		this.conversationId = conversationId;
	}
	public String getTextMessage() {
		return textMessage;
	}
	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
	}
	public Long getSenderId() {
		return senderId;
	}
	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}
}
