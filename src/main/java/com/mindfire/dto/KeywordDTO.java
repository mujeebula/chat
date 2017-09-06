package com.mindfire.dto;
/**
 * DTO to get the details of search keyword
 * @author
 *
 */
public class KeywordDTO {

	private String keyword;
	private String username;
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public String toString() {
		return "Keyword [keyword=" + keyword + ", username=" + username + "]";
	}
}
