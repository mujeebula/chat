package com.mindfire.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindfire.dto.KeywordDTO;
import com.mindfire.entity.User;
import com.mindfire.repository.UserRepository;

@Service
public class SearchService {


	@Autowired
	private UserRepository userRepository;
	
	public List<User> search(KeywordDTO keyword) {
		User user = userRepository.findByUsername(keyword.getUsername());
		List<User> searchResult = userRepository.findByUsernameContaining(keyword.getKeyword());
		//remove current user from allUsers
		for(int i = 0; i < searchResult.size(); i++) {
			if(searchResult.get(i).getUserId() == user.getUserId()) {
				searchResult.remove(i);
				break;
			}
		}
		return searchResult;
	}

}
