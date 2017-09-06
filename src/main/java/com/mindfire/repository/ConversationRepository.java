package com.mindfire.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindfire.entity.Conversation;
import com.mindfire.entity.Message;

/**
 * Repository for Conversation Entity
 * @author
 *
 */
@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {

	List<Message> findAllByconversationIdOrderByCreatedAt(Long conversationId);
}