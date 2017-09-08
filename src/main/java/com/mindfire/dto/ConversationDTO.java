package com.mindfire.dto;

/*
 * ConversationDTO.java
 * 
 * Used for receiving details of conversation of which user needs the messages
 * @author
 *
 */
public class ConversationDTO {

	private Long conversationId;
	private String name;
	
	public Long getConversationId() {
		return conversationId;
	}
	public void setConversationId(Long conversationId) {
		this.conversationId = conversationId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "ConversationDTO [conversationId=" + conversationId + ", name=" + name + "]";
	}

}
