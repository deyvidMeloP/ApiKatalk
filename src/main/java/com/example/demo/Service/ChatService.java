package com.example.demo.Service;

import org.springframework.stereotype.Service;

import com.example.demo.ChatRepository.ChatRepository;
import com.example.demo.ChatRepository.FriendRepository;
import com.example.demo.ChatRepository.UserRepository;
import com.example.demo.ChatEntity.CandidateEntity;
import com.example.demo.ChatEntity.ChatEntity;
import com.example.demo.ChatEntity.FriendEntity;
import com.example.demo.ChatEntity.RTCEntity;
import com.example.demo.ChatEntity.UserEntity;
import com.example.demo.ChatEntity.interromperCall;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Service
public class ChatService {  
    
    @Autowired
	private ChatRepository chatRepository;
    
    @Autowired
	private UserRepository userRepository;
    
    @Autowired
    private FriendRepository friendRepo;
	
	public List<UserEntity> getUsername(String username){
		return userRepository.findByUsername(username);
	}
	
	public List<ChatEntity> getMessageAll(String uid){
		
		return chatRepository.findByUid(uid);
	}

    
	@Autowired
	private UserRepository userRepo;
	
	public List<UserEntity> getAllUser(){
		
		return userRepo.findAll();
	}
	
	
	 @Autowired
	 private SimpMessagingTemplate messagingTemplate;

	 public void sendNotification(ChatEntity chatEntity) {
	    System.out.println(chatEntity.getMessageChat());
	    messagingTemplate.convertAndSend("/topic/notifications/"+ chatEntity.getUserGet(), chatEntity);
	 }
	    
	
	public void cancelCall(interromperCall cancelCall) {
		System.out.println("/topic/cancelCall/"+cancelCall.getUid());
		messagingTemplate.convertAndSend("/topic/cancelCall/"+cancelCall.getUid(), cancelCall);
	
	}
	    
	public void returnOffer (RTCEntity rtc) {
		
		System.out.println("offer aqui /topic/getOffer/"+rtc.getCall().getDate());
		
		messagingTemplate.convertAndSend("/topic/getOffer/"+rtc.getCall().getGetUid(), rtc);
		
	}
	
	public void returnAnswer (RTCEntity rtc) {
		System.out.println("resposta enviada /topic/answerCall/"+rtc.getCall().getSendUid()+ "/"+rtc.getCall().getGetUid());
		messagingTemplate.convertAndSend("/topic/answerCall/"+rtc.getCall().getSendUid()+ "/"+rtc.getCall().getGetUid(), rtc);
		
	}
	
	public void returnCandidateA(CandidateEntity cdt) {
	
		System.out.println("candidato enviada");
		messagingTemplate.convertAndSend("/topic/getCandidateA/"+ cdt.getCall().getGetUid() ,cdt);
		
	}
	
	public void returnCandidateB(CandidateEntity cdt) {
		
		System.out.println("candidato enviada");
		messagingTemplate.convertAndSend("/topic/getCandidateB/"+ cdt.getCall().getSendUid() ,cdt);
		
	}
	
	
	public List<ChatEntity> messageSendGet(String uid, String uidGet){
		return chatRepository.findSendGetMessage(uid, uidGet);
	}
	
	public List<ChatEntity> messageGet(String uid){
		return chatRepository.findGetMessage(uid);
	}
	
	public List<ChatEntity> messageSend(String uid){
		return chatRepository.findSendMessage(uid);
	}
	
	public List<ChatEntity> messageGroup(String uid){
		
		return chatRepository.findByUid(uid);
	}
	
	public List<ChatEntity> messageAllGroup(String uid){
		return chatRepository.findByUid(uid);
	}
	
	
	public List<FriendEntity> getFriendListAll() {
	
		return friendRepo.findAll();
	}
	
	public List<FriendEntity> getFriendList(String uid) {
		
		return friendRepo.findByUid(uid);
	}
	
}

