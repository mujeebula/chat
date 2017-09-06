package com.mindfire.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.mindfire.dto.ConversationMessageDTO;

/**
 * A Data Access Object for messages retrieval
 * @author
 *
 */
@Repository
public class MessageDAO {
	
	public static final String QUERY = "select \n" + 
			"    m.message_id,\n" + 
			"    m.conversation_id,\n" + 
			"    cp.user_id as participant_id,\n" + 
			"    m.text_message,\n" + 
			"    m.created_at\n" + 
			"from\n" + 
			"    message as m\n" + 
			"        inner join\n" + 
			"    conversation_participant as cp ON m.sender_participant_id = cp.participant_id\n" + 
			"where m.conversation_id = ?1 order by m.created_at";
	
	protected EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public List<ConversationMessageDTO> getAllMessages(Long conversationId) {
		if (entityManager != null) {
			Query query = entityManager.createNativeQuery(QUERY, "ConversationMessagesQuery");
			query.setParameter(1, conversationId);
			List<ConversationMessageDTO> list = (List<ConversationMessageDTO>) query.getResultList();
			System.out.println(list);
			return list;
		}else {
			System.out.println("null");
			return null;
		}
	}

}
