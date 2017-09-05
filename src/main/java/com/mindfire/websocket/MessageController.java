package com.mindfire.websocket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import com.mindfire.entity.Contact;
import com.mindfire.entity.Message;
import com.mindfire.entity.User;
import com.mindfire.repository.ContactRepository;
import com.mindfire.repository.MessageRepository;
import com.mindfire.repository.UserRepository;
import com.mindfire.util.Keyword;
import com.mindfire.util.SenderReceiver;

/**
 * This controller handles all the incoming socket mappings.
 */

@Controller
public class MessageController {

	// user name , sessionId
	private Map<String, String> hm = new HashMap<>();

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	/**
	 * This method is called when a user is logged in to send all its contact
	 * and details.
	 * @param user Logged in user
	 * @param sessionId SessionId of the user
	 * @throws Exception
	 */
	
	@MessageMapping("/init")
	public void init(User user, @Header("simpSessionId") String sessionId) throws Exception {
		//put this user into HashMap along with sessionId
		hm.put(user.getUsername(), sessionId);
		user = userRepository.findByUsername(user.getUsername());
		System.out.println("Logged in:" + user.toString());
		List<Contact> contacts = contactRepository.findByUserId(user.getUserId());
		List<User> users = new ArrayList<>();
		for (Contact contact : contacts) {
			users.add(userRepository.findByUserId(contact.getContactId()));
		}
		System.out.println("All Contacts :" + users.toString());
		simpMessagingTemplate.convertAndSend("/topic/contacts-" + hm.get(user.getUsername()), users);
		simpMessagingTemplate.convertAndSend("/topic/userDetails-" + hm.get(user.getUsername()), user);
	}
	
	/**
	 * This method returns all the messages exchanged between Sender and Receiver in
	 * sorted order.
	 * @param senderReceiver Pair of user names for sender and receiver
	 * @param sessionId SessionId of the user
	 * @throws Exception
	 */

	@MessageMapping("/getAllMessages")
	public void getAllMessages(SenderReceiver senderReceiver, @Header("simpSessionId") String sessionId)
			throws Exception {
		System.out.println("Sender Receiver :" + senderReceiver.toString());
		User user = userRepository.findByUsername(senderReceiver.getSendername());
		User receiver = userRepository.findByUsername(senderReceiver.getReceivername());
		System.out.println(user.toString());
		System.out.println(receiver.toString());
		List<Long> l1 = new ArrayList<>();
		l1.add(user.getId());
		l1.add(receiver.getId());
		List<Message> messages = messageRepository.findAllBySenderIdInAndReceiverIdInOrderByCreated(l1, l1);
		System.out.println(messages);
		simpMessagingTemplate.convertAndSend("/topic/allConversationMessage-" + hm.get(user.getUsername()), messages);
	}
	
	/**
	 * Used to search the user name database.
	 * It returns all the contacts whose user name contains the keyword.
	 * @param keyword Keyword for search query
	 * @param sessionId sessionId of the user
	 * @throws Exception
	 */
	
	@MessageMapping("/search")
	public void search(Keyword keyword, @Header("simpSessionId") String sessionId)
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

	/**
	 * This method handles the private message send to a user.
	 * @param message Message to be sent
	 */
	
	@MessageMapping("/message")
	public void messageHandler(Message message) {
		System.out.println("Received Message:" + message.toString());
		User receiver = userRepository.findOne(message.getReceiverId());
		User sender = userRepository.findOne(message.getSenderId());
		message = messageRepository.save(message);
		simpMessagingTemplate.convertAndSend("/topic/privateMessage-" + hm.get(receiver.getUsername()), message);
		simpMessagingTemplate.convertAndSend("/topic/privateMessage-" + hm.get(sender.getUsername()), message);
		System.out.println("Sending to :" + "/topic/privateMessage-" + hm.get(receiver.getUsername()));
	}
	
	/**
	 * It adds a contact in database.
	 * @param contact Contact to be added
	 */
	
	@MessageMapping("/addContact")
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
	}
}