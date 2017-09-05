package com.mindfire.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindfire.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
	List<User> findByUsernameContaining(String data);
	User findByUserId(Long contactId);
	List<User> findByUsernameContainingAndUsernameNotIn(String keyword, boolean add);
}
