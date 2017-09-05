package com.mindfire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mindfire.entity.User;
import com.mindfire.repository.UserRepository;

@RestController
public class SignupController {

	/*@Autowired
	UserRepository userRepository;

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public Response signup(@RequestBody User user) {
		User user1 = userRepository.save(user);
		return user1 != null ? new Response("OK") : new Response("FAIL");
	}

	// call this using AJAX to check user name availability
	@RequestMapping(value = "/validateUsername/{userName}", method = RequestMethod.POST)
	public Response isUserNameUnique(@PathVariable String username) {
		User user = userRepository.findByUsername(username);
		System.out.println(username + "," + user);
		if (user == null) {
			return new Response("OK");
		} else {
			return new Response("FAIL");
		}
	}*/
}
