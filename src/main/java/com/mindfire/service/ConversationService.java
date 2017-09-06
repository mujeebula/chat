package com.mindfire.service;

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

/**
 * Service which handles 
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

	public List<ConversationMessageDTO> getAllMessages(ConversationDTO conversationDTO) {
		List<ConversationMessageDTO> allMessage = messageDAO.getAllMessages(conversationDTO.getConversationId());
		return allMessage;
	}

	public void getAllConversations(User user) {
		simpMessagingTemplate.convertAndSend("/topic/userDetails-" + sessionService.getSessionId(user.getUsername()),
				user);
		// send all conversation
		List<ConversationIdAndNameDTO> conversationIdAndName = conversationIdAndNameDAO.getAll(user.getUserId());
		simpMessagingTemplate.convertAndSend("/topic/conversations-" + sessionService.getSessionId(user.getUsername()),
				conversationIdAndName);

	}

}
