package com.mindfire.controller;

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

/**
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
	 * This method is called when a user is logged in to send all its conversations and
	 * user details.
	 * 
	 * @param user
	 *            Logged in user
	 * @param sessionId
	 *            SessionId of the user
	 * @throws Exception
	 */

	@MessageMapping("/getConversations")
	public void init(User user, @Header("simpSessionId") String sessionId) {
		// put this user into HashMap along with sessionId
		System.out.println("Logged in user :" + user.getUsername() + ":" + sessionId);
		sessionService.addUser(user.getUsername(), sessionId);
		user = userRepository.findByUsername(user.getUsername());
		System.out.println("Logged in:" + user.toString());
		conversationService.getAllConversations(user);
	}

	/**
	 * This method returns all the messages exchanged in
	 * a conversation in sorted order
	 * 
	 * @param senderReceiver
	 *            Pair of user names for sender and receiver
	 * @param sessionId
	 *            SessionId of the user
	 * @throws Exception
	 */
	@MessageMapping("/getConversationMessages")
	public void getConversationMessages(ConversationDTO conversationDTO) throws Exception {
		System.out.println(conversationDTO);
		messageService.sendConversationMessages(conversationDTO);
	}
}
