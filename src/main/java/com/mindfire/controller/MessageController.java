package com.mindfire.controller;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.mindfire.service.MessageService;
import com.mindfire.dto.MessageDTO;

/*
 * MessageController.java
 * 
 * This controller handles all the incoming socket message mappings.
 * 
 * Handles individual messages sent to a particular conversation
 *
 */
@Controller
public class MessageController {

	@Autowired
	private MessageService messageService;

	/**
	 * Sends the message to desired conversation
	 * 
	 * @param messageDTO
	 */
	@MessageMapping("/send")
	public void send(MessageDTO messageDTO) {
		try {
			messageService.send(messageDTO);
		} catch (Exception ex) {
			String error = "";
			if (ex instanceof SQLException) {
				error = "Got some Database errror:" + ex.getMessage();
				Logger.getLogger(MessageController.class.getName()).log(Level.SEVERE, error);
			} else {
				error = "Somethig went wrong" + ex.getMessage();
				Logger.getLogger(MessageController.class.getName()).log(Level.SEVERE, error);
			}
		}
	}
}