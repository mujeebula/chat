package com.mindfire.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.mindfire.dto.ConversationDTO;
import com.mindfire.dto.ConversationIdAndNameDTO;
import com.mindfire.dto.ConversationMessageDTO;
import com.mindfire.entity.User;
import com.mindfire.repository.ConversationIdAndNameDAO;
import com.mindfire.repository.MessageDAO;

/*
 * ConversationService.java
 * 
 * Service which handles the task to send all the conversations for a particular user.
 * @author
 *
 */
@Service
public class ConversationService {

	@Autowired
	private ConversationIdAndNameDAO conversationIdAndNameDAO;

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@Autowired
	private SessionService sessionService;

	@Autowired
	private MessageDAO messageDAO;

	/**
	 * Sends all the messages in a particular conversation
	 * 
	 * @param conversationDTO
	 * @return
	 */
	public List<ConversationMessageDTO> getAllMessages(ConversationDTO conversationDTO) {
		try {
			List<ConversationMessageDTO> allMessage = messageDAO.getAllMessages(conversationDTO.getConversationId());
			return allMessage;
		} catch (Exception ex) {
			throw ex;
		}
	}

	/**
	 * Returns all the conversations user is involved in
	 * 
	 * @param user
	 */
	public void getAllConversations(User user) {
		try {
			simpMessagingTemplate
					.convertAndSend("/topic/userDetails-" + sessionService.getSessionId(user.getUsername()), user);
			// send all conversation
			List<ConversationIdAndNameDTO> conversationIdAndName = conversationIdAndNameDAO.getAll(user.getUserId());
			simpMessagingTemplate.convertAndSend(
					"/topic/conversations-" + sessionService.getSessionId(user.getUsername()), conversationIdAndName);
		} catch (Exception ex) {
			throw ex;
		}
	}
}
