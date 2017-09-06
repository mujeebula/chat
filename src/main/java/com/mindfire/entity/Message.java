package com.mindfire.entity;

import java.util.Date;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.mindfire.dto.ConversationMessageDTO;

/**
 * Entity for messages exchanged among all users
 * @author
 *
 */
@Entity
/*
 * A custom query to get messages exchanged in a particular conversation based on conversation id
 */
@SqlResultSetMapping(name = "ConversationMessagesQuery", classes = {
		@ConstructorResult(targetClass = ConversationMessageDTO.class, columns = {
				@ColumnResult(name = "message_id", type = Long.class),
				@ColumnResult(name = "conversation_id", type = Long.class),
				@ColumnResult(name = "participant_id", type = Long.class),
				@ColumnResult(name = "text_message", type = String.class),
				@ColumnResult(name = "created_at", type = Date.class) }) })
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long messageId;
	private Long conversationId;
	private Long senderParticipantId;
	private String textMessage;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@PrePersist
	protected void onCreate() {
		createdAt = new Date();
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
		return "Message [messageId=" + messageId + ", conversationId=" + conversationId + ", senderParticipantId="
				+ senderParticipantId + ", textMessage=" + textMessage + ", createdAt=" + createdAt + "]";
	}
}
