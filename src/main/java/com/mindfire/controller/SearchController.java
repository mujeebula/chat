package com.mindfire.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.mindfire.dto.KeywordDTO;
import com.mindfire.entity.User;
import com.mindfire.service.SearchService;
import com.mindfire.service.SessionService;

/*
 * SearchController.java
 * 
 * It handles the search queries for user directory
 */
@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@Autowired
	private SessionService sessionService;

	/**
	 * Sends all the results matching the given keyword
	 * 
	 * @param keyword
	 * @param sessionId
	 * @throws Exception
	 */
	@MessageMapping("/search")
	public void search(KeywordDTO keyword, @Header("simpSessionId") String sessionId) {
		try {
			List<User> searchResult = searchService.search(keyword);
			simpMessagingTemplate.convertAndSend("/topic/search-" + sessionService.getSessionId(keyword.getUsername()),
					searchResult);
		} catch (Exception ex) {
			String error = "";
			if (ex instanceof SQLException) {
				error = "Got some Database errror:" + ex.getMessage();
				Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, error);
			} else {
				error = "Somethig went wrong" + ex.getMessage();
				Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, error);
			}
		}
	}
}
