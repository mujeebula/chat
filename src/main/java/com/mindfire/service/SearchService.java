package com.mindfire.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindfire.dto.KeywordDTO;
import com.mindfire.entity.User;
import com.mindfire.repository.UserRepository;

/*
 * SearchService.java
 * 
 * Used for searching user database
 */
@Service
public class SearchService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * This method searches the user database repository
	 * 
	 * @param keyword
	 *            keyword to search
	 * @return list of found users
	 */
	public List<User> search(KeywordDTO keyword) {
		try {
			User user = userRepository.findByUsername(keyword.getUsername());
			List<User> searchResult = userRepository.findByUsernameContaining(keyword.getKeyword());
			// remove current user from allUsers
			for (int i = 0; i < searchResult.size(); i++) {
				if (searchResult.get(i).getUserId() == user.getUserId()) {
					searchResult.remove(i);
					break;
				}
			}
			return searchResult;
		} catch (Exception ex) {
			throw ex;
		}
	}

}
