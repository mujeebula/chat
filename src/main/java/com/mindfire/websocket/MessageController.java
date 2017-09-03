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

	@MessageMapping("/init")
	public void init(User user, @Header("simpSessionId") String sessionId) throws Exception {
		hm.put(user.getUsername(), sessionId);
		System.out.println(user.getUsername());
		user = userRepository.findByUsername(user.getUsername());
		System.out.println(user.toString());
		List<Contact> contacts = contactRepository.findByUserId(user.getId());
		List<User> users = new ArrayList<>();
		for (Contact contact : contacts) {
			users.add(userRepository.findOne(contact.getContactId()));
		}
		System.out.println("All Contacts :" + users.toString());
		simpMessagingTemplate.convertAndSend("/topic/contacts-" + hm.get(user.getUsername()), users);
		simpMessagingTemplate.convertAndSend("/topic/userDetails-" + hm.get(user.getUsername()), user);
	}

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
		simpMessagingTemplate.convertAndSend("/topic/greeting-" + hm.get(user.getUsername()), messages);
	}
	
	@MessageMapping("/search")
	public void search(Keyword keyword, @Header("simpSessionId") String sessionId)
			throws Exception {
		System.out.println("Keyword:" + keyword.toString());
		User user = userRepository.findByUsername(keyword.getUsername());
		List<User> users = userRepository.findByUsernameContaining(keyword.getKeyword());
		System.out.println(users.toString());
		simpMessagingTemplate.convertAndSend("/topic/search-" + hm.get(user.getUsername()), users);
	}

	@MessageMapping("/message")
	public void messageHandler(Message message) {
		System.out.println(message.toString());
		User user = userRepository.findOne(message.getReceiverId());
		simpMessagingTemplate.convertAndSend("/topic/greeting-" + hm.get(user.getUsername()), message);
		System.out.println("Sending to :" + "/topic/greeting-" + hm.get(user.getUsername()));
		messageRepository.save(message);
	}
}