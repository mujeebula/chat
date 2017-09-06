package com.mindfire.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindfire.entity.ConversationParticipant;

/**
 * Repository for ConversationParticipant entity
 * @author
 *
 */
public interface ConversationParticipantRepository extends JpaRepository<ConversationParticipant, Long>{

	List<ConversationParticipantRepository> findAllByUserIdNotIn(Long userId, List<Long> ids);

	List<ConversationParticipant> findAllByConversationId(Long conversationId);

}
