package com.example.demo.Service;

import org.springframework.stereotype.Service;

import com.example.demo.ChatEntity.FriendEntity;
import com.example.demo.ChatRepository.FriendRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Service
public class FriendService {
	
	 @Autowired
	 private SimpMessagingTemplate messageTemp;
	 
	 @Autowired
	 private FriendRepository friendRepo;
	 
	 public void invitFriend(FriendEntity friendEntity){
		 
		 messageTemp.convertAndSend("/topic/friendInvite/"+ friendEntity.getFriendUid(), friendEntity);
		 
		 
	 }
	 
	 public FriendEntity changeRequest(FriendEntity friendEntity) {
		 
		 return friendRepo.findRequest(friendEntity.getUserUid(), friendEntity.getFriendUid());
		 
	 }


}
