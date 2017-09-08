package com.mindfire.entity;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

import com.mindfire.dto.ConversationIdAndNameDTO;

/*
 * User.java
 * 
 * Entity for user details
 * @author
 *
 */
@Entity
/*
 * A custom query used to get the name of the conversation
 * In case of group conversation it will return group name other wise 
 * it will return the other participants user name
 */
@SqlResultSetMapping(name = "ConversationIdAndNameQuery", classes= {
		@ConstructorResult(targetClass=ConversationIdAndNameDTO.class, columns= {
				@ColumnResult(name="conversation_id", type=Long.class),
				@ColumnResult(name="name", type=String.class)
		})
})
public class User extends AbstractTimestampEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	private String firstName;
	private String lastName;
	@Column(unique = true, nullable = false)
	private String username;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", username="
				+ username + "]";
	}
}
