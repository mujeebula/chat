package com.mindfire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mindfire.entity.Message;
import com.mindfire.repository.MessageRepository;

@RestController
public class MessagesController {
	
	@Autowired
	private MessageRepository messageRepository;
	
	@RequestMapping(value="/addMessage", method=RequestMethod.POST)
	public void addMessage(@RequestBody Message message) {
		System.out.println("Received msg:" + message.toString());
		messageRepository.save(message);
	}

}
