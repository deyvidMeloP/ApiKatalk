package com.example.demo.ChatEntity;

import java.time.LocalDateTime;

public class CallEntity {
	
	private String sendUid;
	private String getUid;
	private LocalDateTime date;
	  
	public String getSendUid() {
		return sendUid;
	}
	public void setSendUid(String sendUid) {
		this.sendUid = sendUid;
	}
	public String getGetUid() {
		return getUid;
	}
	public void setGetUid(String getUid) {
		this.getUid = getUid;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}  
	  

}
