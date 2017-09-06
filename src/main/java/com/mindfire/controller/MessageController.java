package com.mindfire.controller;

/**
 * Handles individual messages send to a particular conversation
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.mindfire.service.MessageService;
import com.mindfire.dto.MessageDTO;

/**
 * This controller handles all the incoming socket mappings.
 */

@Controller
public class MessageController {
	
	@Autowired
	private MessageService messageService;

	
	/**
	 * Sends the message to desired conversation
	 * @param messageDTO
	 */
	@MessageMapping("/send")
	public void send(MessageDTO messageDTO) {
		messageService.send(messageDTO);
	}
	
	
	/**
	 * This method is called when a user is logged in to send all its contact
	 * and details.
	 * @param user Logged in user
	 * @param sessionId SessionId of the user
	 * @throws Exception
	 */
	
	/*@MessageMapping("/init")
	public void init(User user, @Header("simpSessionId") String sessionId) {
		
		//put this user into HashMap along with sessionId
		usernameSessionId.put(user.getUsername(), sessionId);
		user = userRepository.findByUsername(user.getUsername());
		System.out.println("Logged in:" + user.toString());
		simpMessagingTemplate.convertAndSend("/topic/userDetails-" + 
				usernameSessionId.get(user.getUsername()), user);
		//send all conversation also
		List<ConversationIdAndName> conversationIdAndName = conversationIdAndNameDAO.getAll(user.getUserId());
		simpMessagingTemplate.convertAndSend("/topic/allConversationMessage-" + 
				usernameSessionId.get(user.getUsername()), conversationIdAndName);
	}*/
	
	/**
	 * Used to search the user name database.
	 * It returns all the contacts whose user name contains the keyword.
	 * @param keyword Keyword for search query
	 * @param sessionId sessionId of the user
	 * @throws Exception
	 */
	/*@MessageMapping("/search")
	public void search(KeywordDTO keyword, @Header("simpSessionId") String sessionId)
			throws Exception {
		System.out.println("Keyword:" + keyword.toString());
		User user = userRepository.findByUsername(keyword.getUsername());
		List<User> allUsers = userRepository.findByUsernameContaining(keyword.getKeyword());
		//remove current user from allUsers
		for(int i = 0; i < allUsers.size(); i++) {
			if(allUsers.get(i).getUserId() == user.getUserId()) {
				allUsers.remove(i);
				break;
			}
		}
		//TODO convert it into sql
		List<Contact> userContacts = contactRepository.findByUserId(user.getUserId());
		List<User> filteredUsers = new ArrayList<>();
		for (Contact contact : userContacts) {
			filteredUsers.add(userRepository.findByUserId(contact.getContactId()));
		}
		//remove contacts from allUsers
		for(int i = 0; i < filteredUsers.size(); i++) {
			for(int j = 0; j < allUsers.size(); j++) {
				if(filteredUsers.get(i).getId() == allUsers.get(j).getId()) {
					allUsers.remove(j);
				}
			}
		}
		System.out.println(allUsers.toString());
		simpMessagingTemplate.convertAndSend("/topic/search-" + hm.get(user.getUsername()), allUsers);
	}
*/
	/**
	 * This method handles the private message send to a user.
	 * @param message Message to be sent
	 */
	
	/*@MessageMapping("/message")
	public void messageHandler(Message message) {
		System.out.println("Received Message:" + message.toString());
		User receiver = userRepository.findOne(message.getReceiverId());
		User sender = userRepository.findOne(message.getSenderId());
		message = messageRepository.save(message);
		simpMessagingTemplate.convertAndSend("/topic/privateMessage-" + hm.get(receiver.getUsername()), message);
		simpMessagingTemplate.convertAndSend("/topic/privateMessage-" + hm.get(sender.getUsername()), message);
		System.out.println("Sending to :" + "/topic/privateMessage-" + hm.get(receiver.getUsername()));
	}*/
	
	/**
	 * It adds a contact in database.
	 * @param contact Contact to be added
	 */
	
	/*@MessageMapping("/addContact")
	public void addContact(Contact contact) {
		contactRepository.save(contact);
		contactRepository.save(new Contact(contact.getContactId(), contact.getUserId()));
		
		User newContact = userRepository.findOne(contact.getContactId());
		List<Contact> contacts = contactRepository.findByUserId(newContact.getId());
		List<User> users = new ArrayList<>();
		for (Contact c : contacts) {
			users.add(userRepository.findOne(c.getContactId()));
		}
		simpMessagingTemplate.convertAndSend("/topic/contacts-" + hm.get(newContact.getUsername()), users);
	}*/
}