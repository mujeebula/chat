package com.mindfire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mindfire.entity.Contact;
import com.mindfire.repository.ContactRepository;

@RestController
public class ContactController {
	
	@Autowired
	private ContactRepository contactRepository;
	
	@RequestMapping(value="/addContact", method=RequestMethod.POST)
	public void addContact(@RequestBody Contact contact) {
		contactRepository.save(contact);
	}
}
