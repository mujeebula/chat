package com.mindfire.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.mindfire.dto.ConversationIdAndNameDTO;

/**
 * A Data Access Object to retrieve the result of the custom query.
 * @author
 *
 */
@Repository
public class ConversationIdAndNameDAO {

	public static final String QUERY = "select \n" + "    c.conversation_id,\n" + "    (CASE\n" + "        WHEN\n"
			+ "            (SELECT \n" + "                    COUNT(*)\n" + "                FROM\n"
			+ "                    conversation_participant\n" + "                where\n"
			+ "                    conversation_id = p.conversation_id) > 2\n" + "        THEN\n"
			+ "            c.name\n" + "        ELSE (SELECT \n" + "                username\n" + "            from\n"
			+ "                user\n" + "            where\n" + "                user_id = (select \n"
			+ "                        user_id\n" + "                    from\n"
			+ "                        conversation_participant\n" + "                    where\n"
			+ "                        user_id != ?1\n"
			+ "                            AND conversation_id = p.conversation_id))\n" + "    END) as 'name'\n"
			+ "from\n" + "    conversation c\n" + "        inner join\n"
			+ "    conversation_participant p ON c.conversation_id = p.conversation_id\n" + "where\n"
			+ "    p.user_id = ?1";

	protected EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<ConversationIdAndNameDTO> getAll(Long userId) {
		if (entityManager != null) {
			Query query = entityManager.createNativeQuery(QUERY, "ConversationIdAndNameQuery");
			query.setParameter(1, userId);
			List<ConversationIdAndNameDTO> list = (List<ConversationIdAndNameDTO>) query.getResultList();
			System.out.println(list);
			return list;
		}else {
			System.out.println("Entity manager null");
			return null;
		}
	}

}
