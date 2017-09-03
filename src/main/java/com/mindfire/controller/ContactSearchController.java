package com.mindfire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mindfire.entity.SearchContact;
import com.mindfire.entity.User;
import com.mindfire.repository.UserRepository;

@RestController
public class ContactSearchController {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = "/contactSearch", method=RequestMethod.POST)
	public List<User> contactSearch(@RequestBody SearchContact contact) {
		return userRepository.findByUsernameContaining(contact.data);
	}
}
