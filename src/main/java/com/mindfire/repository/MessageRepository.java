package com.mindfire.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mindfire.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
	List<Message> findByReceiverIdOrderByCreated(Long receiverId);
	List<Message> findBySenderIdAndReceiverIdOrderByCreated(Long senderId, Long receiverId);
	List<Message> findAllBySenderIdInAndReceiverIdInOrderByCreated(List<Long> id, List<Long> id2);
}