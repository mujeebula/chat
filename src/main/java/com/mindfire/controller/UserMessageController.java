package com.mindfire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mindfire.entity.Message;
import com.mindfire.repository.MessageRepository;
import com.mindfire.util.Response;

@RestController
public class UserMessageController {

	@Autowired
	private MessageRepository messageRepository;
	
	//send message to a user Message has senderId , receiverId
	@RequestMapping(value = "/send")
	public Response send(@RequestBody Message message) {
		message = messageRepository.save(message);
		return message == null ? new Response("FAIL") : new Response("OK");
	}

	@RequestMapping(value = "/receive/{receiverId}", method = RequestMethod.POST)
	public List<Message> receive(@PathVariable("receiverId") Long receiverId) {
		List<Message> msgs = messageRepository.findByReceiverIdOrderByCreated(receiverId);
		return msgs;
	}

	// receive all messages from user A to user B
	@RequestMapping(value = "/receive/{receiverId}/{senderId}", method = RequestMethod.POST)
	public List<Message> receiveFromUser(@PathVariable("senderId") Long senderId,
			@PathVariable("receiverId") Long receiverId) {
		List<Message> msgs = messageRepository.findBySenderIdAndReceiverIdOrderByCreated(senderId, receiverId);
		return msgs;
	}
}
