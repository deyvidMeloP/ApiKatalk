package com.example.demo.ChatController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ChatEntity.FriendEntity;
import com.example.demo.ChatRepository.FriendRepository;
import com.example.demo.Service.ChatService;
import com.example.demo.Service.FriendService;

@RestController
@CrossOrigin(origins = "*") 
public class FriendController {
	
	
	@Autowired
	private FriendService friendService;
	
	@Autowired
	private FriendRepository friendRepo;
	
	@Autowired
	private ChatService chatService;
	
	@PutMapping("katalk/addFriend")
	public void addFriend(@RequestBody FriendEntity newFriend) {

		System.out.println(newFriend.toString());
		friendService.invitFriend(newFriend);
		
		//friendRepo.save(newFriend);
		
	}
	
	@PutMapping("katalk/changeRequest")
	public void changeRequest(@RequestBody FriendEntity newRequest) {
		
		FriendEntity oldRequest = friendService.changeRequest(newRequest);
		oldRequest.setFriendStatus(newRequest.getFriendStatus());
		oldRequest.setFriendDate(newRequest.getFriendDate());

		friendRepo.save(oldRequest);
		
		
	}

	/*@GetMapping("katalk/FriendSortList/{uid}")
	public LinkedHashMap<String, FriendEntity> getFriendList(@PathVariable("uid") String uid) {
		
		List<FriendEntity> friendList = chatService.getFriendList(uid);
		List<FriendEntity> aux = new ArrayList<>();
		LinkedHashMap<String, FriendEntity> linkedList = new LinkedHashMap<>();
		
		for(FriendEntity list: friendList) {
			aux.clear();
			if(list.getUserUid().equals(uid)) {
				
				linkedList.put(list.getFriendUid(), list);
				
			}
			
		}
		
		
		return linkedList;
		
	}*/
	
}
