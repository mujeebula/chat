package com.mindfire.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;

/*
 * SessionService.java
 * 
 * This service stores the sessionId of the user in HasMap
 * @author
 *
 */
@Controller
public class SessionService {

	public static Map<String, String> usernameSessionId = new HashMap<>();
	
	/**
	 * Adds a user into session
	 * @param username user to add
	 * @param sessionId sessionId of the user
	 */
	public void addUser(String username, String sessionId) {
		usernameSessionId.put(username, sessionId);
	}

	/**
	 * Get the sessionId for the given user
	 * @param username 
	 * @return sessionId
	 */
	public String getSessionId(String username) {
		return usernameSessionId.get(username);
	}
}
