package com.mindfire.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;

@Controller
public class SessionService {

	public static Map<String, String> usernameSessionId = new HashMap<>();
	
	public void addUser(String username, String sessionId) {
		usernameSessionId.put(username, sessionId);
	}

	public String getSessionId(String username) {
		return usernameSessionId.get(username);
	}
}
