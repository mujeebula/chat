package com.mindfire.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Message{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long senderId;
	private Long receiverId;
	private boolean toUserOrGroup;
	private String content;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated", nullable = false)
    private Date updated;

    public String getContent() {
		return content;
	}

    public Date getCreated() {
		return created;
	}

	public Long getId() {
		return id;
	}

	public Long getReceiverId() {
		return receiverId;
	}

	public Long getSenderId() {
		return senderId;
	}

	public boolean getToUserOrGroup() {
		return toUserOrGroup;
	}

	public Date getUpdated() {
		return updated;
	}

	@PrePersist
    protected void onCreate() {
    updated = created = new Date();
    }

	@PreUpdate
    protected void onUpdate() {
    updated = new Date();
    }

	public void setContent(String content) {
		this.content = content;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	public void setToUserOrGroup(boolean toUserOrGroup) {
		this.toUserOrGroup = toUserOrGroup;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", senderId=" + senderId + ", receiverId=" + receiverId + ", toUserOrGroup="
				+ toUserOrGroup + ", content=" + content + "]";
	}

}
