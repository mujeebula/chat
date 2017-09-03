package com.mindfire.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindfire.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsernameAndPassword(String username, String password);

	User findByUsername(String username);

	List<User> findByUsernameContaining(String data);
}