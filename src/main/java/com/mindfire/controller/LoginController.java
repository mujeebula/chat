package com.mindfire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mindfire.repository.UserRepository;

@Controller
public class LoginController {

	@Autowired
	UserRepository userRepository;

	@RequestMapping(value = "/log", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}

/*	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user") User user) {
		ModelAndView mav = null;
		user = userRepository.findByUserNameAndPassword(user.getUserName(), user.getPassword());
		if (null != user) {
			mav = new ModelAndView("welcome");
		} else {
			mav = new ModelAndView("login");
		}
		return mav;
	}*/

/*	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Response login(@RequestBody User user) {
		if (userRepository.findByUserNameAndPassword(user.getUserName(), user.getPassword()) != null) {
			return new Response("HOME");
		} else {
			return new Response("LOGIN");
		}
	}*/
}