package com.mindfire.dto;
/*
 * ConversationIdAndNameDTO.java
 * 
 * It is used to send the list of conversation user is involved in
 * @author
 */
public class ConversationIdAndNameDTO {
	private Long conversationId;
	private String name;
	
	public ConversationIdAndNameDTO() {}
	
	public ConversationIdAndNameDTO(Long id, String name) {
		this.conversationId = id;
		this.name = name;
	}

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
}
