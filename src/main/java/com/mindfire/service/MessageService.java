package com.mindfire.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.mindfire.dto.ConversationDTO;
import com.mindfire.dto.ConversationMessageDTO;
import com.mindfire.dto.MessageDTO;
import com.mindfire.entity.ConversationParticipant;
import com.mindfire.entity.Message;
import com.mindfire.entity.User;
import com.mindfire.repository.ConversationParticipantRepository;
import com.mindfire.repository.MessageDAO;
import com.mindfire.repository.MessageRepository;
import com.mindfire.repository.UserRepository;

/*
 * MessageService.java
 * 
 * Service to handle messages sent by user
 * @author
 *
 */
@Service
public class MessageService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ConversationParticipantRepository conversationParticipantRepository;

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@Autowired
	private SessionService sessionService;

	@Autowired
	private MessageDAO messageDAO;

	/**
	 * This method sends the message to all the users involved in this conversation
	 * 
	 * @param messageDTO
	 */
	public void send(MessageDTO messageDTO) {
		try {
			// send this message to all the participants of the conversation
			Message message = new Message();
			message.setConversationId(messageDTO.getConversationId());
			message.setTextMessage(messageDTO.getTextMessage());
			message = messageRepository.saveAndFlush(message);
			List<ConversationParticipant> conversationParticipants = conversationParticipantRepository
					.findAllByConversationId(messageDTO.getConversationId());
			for (ConversationParticipant conversationParticipant : conversationParticipants) {
				User user = userRepository.findOne(conversationParticipant.getUserId());
				if (conversationParticipant.getUserId() == messageDTO.getSenderId()) {
					message.setSenderParticipantId(conversationParticipant.getUserId());
					simpMessagingTemplate.convertAndSend(
							"/topic/conversation-message-" + sessionService.getSessionId(user.getUsername()), message);
					message.setSenderParticipantId(conversationParticipant.getParticipantId());
				} else {
					simpMessagingTemplate.convertAndSend(
							"/topic/conversation-message-" + sessionService.getSessionId(user.getUsername()), message);
				}
			}
			messageRepository.save(message);
		} catch (Exception ex) {
			throw ex;
		}
	}

	/**
	 * This method sends all the messages belonging to a particular conversation
	 * 
	 * @param conversationDTO
	 */
	public void sendConversationMessages(ConversationDTO conversationDTO) {
		List<ConversationMessageDTO> allMessages = messageDAO.getAllMessages(conversationDTO.getConversationId()); // messageRepository.findAllByConversationId(conversationDTO.getConversationId());
		simpMessagingTemplate.convertAndSend(
				"/topic/conversation-message-" + sessionService.getSessionId(conversationDTO.getName()), allMessages);
	}

}
