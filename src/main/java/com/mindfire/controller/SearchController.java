package com.mindfire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.mindfire.dto.KeywordDTO;
import com.mindfire.entity.User;
import com.mindfire.service.SearchService;
import com.mindfire.service.SessionService;

@Controller
public class SearchController {

	
	@Autowired
	private SearchService searchService;
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	@Autowired
	private SessionService sessionService;
	
	@MessageMapping("/search")
	public void search(KeywordDTO keyword, @Header("simpSessionId") String sessionId)
			throws Exception {
		System.out.println("Keyword:" + keyword.toString());
		List<User> searchResult = searchService.search(keyword);
		simpMessagingTemplate.convertAndSend("/topic/search-" + 
				sessionService.getSessionId(keyword.getUsername()), searchResult);
	}
}
