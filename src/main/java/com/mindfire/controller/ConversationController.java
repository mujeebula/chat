package com.mindfire.controller;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.mindfire.dto.ConversationDTO;
import com.mindfire.entity.User;
import com.mindfire.repository.UserRepository;
import com.mindfire.service.ConversationService;
import com.mindfire.service.MessageService;
import com.mindfire.service.SessionService;

/*
 * ConversationController.java
 * 
 * It sends all the conversations to the user and their corresponding messages.
 * @author
 *
 */
@Controller
public class ConversationController {

	@Autowired
	private SessionService sessionService;

	@Autowired
	private ConversationService conversationService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MessageService messageService;

	/**
	 * This method is called when a user is logged in to send all its conversations
	 * and user details.
	 * 
	 * @param user
	 *            Logged in user
	 * @param sessionId
	 *            SessionId of the user
	 * @throws Exception
	 */
	@MessageMapping("/getConversations")
	public void init(User user, @Header("simpSessionId") String sessionId) {
		try {
			// put this user into HashMap along with sessionId
			sessionService.addUser(user.getUsername(), sessionId);
			user = userRepository.findByUsername(user.getUsername());
			conversationService.getAllConversations(user);
		} catch (Exception ex) {
			String error = "";
			if (ex instanceof SQLException) {
				error = "Got some Database errror:" + ex.getMessage();
				Logger.getLogger(ConversationController.class.getName()).log(Level.SEVERE, error);
			} else {
				error = "Somethig went wrong" + ex.getMessage();
				Logger.getLogger(ConversationController.class.getName()).log(Level.SEVERE, error);
			}
		} finally {

		}
	}

	/**
	 * This method returns all the messages exchanged in a conversation in sorted
	 * order
	 * 
	 * @param ConversationDTO
	 *            for which message to send
	 */
	@MessageMapping("/getConversationMessages")
	public void getConversationMessages(ConversationDTO conversationDTO) throws Exception {
		messageService.sendConversationMessages(conversationDTO);
	}
}
