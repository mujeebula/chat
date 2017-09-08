package com.mindfire.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindfire.entity.Message;

/*
 * MessageRepository.java
 * 
 * Repository for stored messages
 * @author
 *
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{
	List<Message> findAllByConversationId(Long conversationId);
}
