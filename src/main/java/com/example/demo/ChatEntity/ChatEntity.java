package com.example.demo.ChatEntity;

import jakarta.persistence.Entity;

import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;


@Entity
@Data

@Table(name = "conversation")
@NoArgsConstructor
@AllArgsConstructor

public class ChatEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id_chat")
    private Long idChat;
	
	@Column(name = "user_send")
	private String userSend;
	
	@Column(name = "message")
	private String messageChat;
	
	@Column(name = "user_get")
	private String userGet;
	
	
	@Column(name = "msg_date")
	private  LocalDateTime dateChat;

	
	public Long idChat() {
		return idChat;
	}
	
	public String userSend() {
		return userSend;
	}
	
	public String user_get() {
		return userGet;
	}
	
	public String messageChat() {
		return messageChat;
	}
	
	public LocalDateTime dateChat() {
		return dateChat;
	}
	

}
