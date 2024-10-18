package com.example.demo.ChatController;
import java.util.Map;

import java.util.TreeMap;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.HttpStatus;  


import com.example.demo.Service.ChatService;

import com.example.demo.ChatEntity.ChatEntity;
import com.example.demo.ChatEntity.FriendEntity;
import com.example.demo.ChatEntity.UserEntity;
import com.example.demo.ChatEntity.RTCEntity;
import com.example.demo.ChatEntity.interromperCall;
import com.example.demo.ChatRepository.ChatRepository;
import com.example.demo.ChatRepository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.ChatEntity.CandidateEntity;
/*netstat -ano | findstr :8080*/
@RestController
@CrossOrigin(origins = "*") 
public class ChatController {
	
	LinkedHashMap<String, ChatEntity> hash = new LinkedHashMap<>();

	@Autowired
	private ChatService productService;
	
	@Autowired
	private ChatRepository chatRepository;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired 
	private ChatService chatService;
	
	private SimpMessagingTemplate messagingTemplate;
	
	
	
	/*@PostMapping("/addProduct")
	public Product postDetails(@RequestBody Product product) {
	
		return productService.saveDetails(product);
	
	}*/
	 @Autowired
	    private ChatService notificationService;

	    @MessageMapping("/sendMessage")
	    @SendTo("/topic/no")
	    public ChatEntity sendMessage(ChatEntity chatEntity) {
	        // Adicione um log para verificar o conteúdo da mensagem recebida
	        System.out.println("Mensagem recebida para envio: " + chatEntity);
	        
	        chatEntity.setUserSend(chatEntity.getUserSend());
	        chatEntity.setUserGet(chatEntity.getUserGet());
	        chatEntity.setMessageChat(chatEntity.getMessageChat());
	        chatEntity.setDateChat(chatEntity.getDateChat());
	        
	        
	        // Envie uma notificação adicional
	        notificationService.sendNotification(chatEntity);
	        
	        return chatEntity;
	    }
	    
	@Autowired
	private ChatService notification;
	
	    @MessageMapping("/sendOffer")
	    public void sendOffer(RTCEntity rtc) {
		
			notification.returnOffer(rtc);
			
	   }
	    
	    @MessageMapping("/sendAnswer")
		public RTCEntity sendAnswerCall(RTCEntity rtc) {
			System.out.println("resposta recebida");
				notification.returnAnswer(rtc);
				return rtc;
			
		}
	    
	    @MessageMapping("/sendCandidateA")
		public CandidateEntity sendCandidateA(CandidateEntity cdt) {
			System.out.println("candidato recebido recebida");
				notification.returnCandidateA(cdt);
				return cdt;	
		}
	    
	    @MessageMapping("/sendCandidateB")
		public CandidateEntity sendCandidateB(CandidateEntity cdt) {
			System.out.println("candidato recebido recebida");
				notification.returnCandidateB(cdt);
				return cdt;	
		}
	    
	    
	    @MessageMapping("/cancelCall")
	    public interromperCall cancelCall(interromperCall cancelCall) {
	    	
	    	notification.cancelCall(cancelCall);
	    	
	    	return cancelCall;
	   
	    	
	    }
	 
	 /*@RequestMapping("/health")
	    @ResponseBody
	    public String healthCheck() {
	        return "Application is running";
	    }*/
	 
	 
		@GetMapping("chat/gt/{username}")
		public List<UserEntity> getChat(
			@PathVariable("username") String username)
		
		{
			
			return chatService.getUsername(username);
			
		}
		
		//todas as mensagens de um usuário especifico
		@GetMapping("chat/getChat/{uid}")
		public List<ChatEntity>getMessageAll(
				@PathVariable("uid") String uid)
		{
			hash.clear();
			return chatService.getMessageAll(uid);
			
		}
		
		
		@GetMapping("/chat/GetUser/{uid}")
		public UserEntity getUser(@PathVariable("uid") String uid) {
			return userRepo.findByUid(uid);
			
		}
	
	@PutMapping("/chat/{id}")
	public ResponseEntity<ChatEntity> updateOrCreateChat(
		@PathVariable("id") Long id,
		@RequestBody ChatEntity chatEntity) {
			
		  return chatRepository.findById(id).
				  map(existingChat -> {
					existingChat.setIdChat(id);
					existingChat.setUserSend(existingChat.getUserSend());		            
					existingChat.setMessageChat(chatEntity.getMessageChat());
		            
					existingChat.setDateChat(chatEntity.getDateChat());
		            
					ChatEntity updatedChat = chatRepository.save(existingChat);
		            
					return ResponseEntity.ok(updatedChat);
		        })
		        
				.orElseGet(() -> {	            
		            chatEntity.setIdChat(id); 
		            ChatEntity newChat = chatRepository.save(chatEntity);
		            return ResponseEntity.status(HttpStatus.CREATED).body(newChat);
		        });
	}
	
	@PutMapping("/register/{id}")
	public ResponseEntity<UserEntity> registerUser(
		@PathVariable("id") Long id,
		@RequestBody UserEntity userEntity){
		
		userEntity.setUserId(id);
		userEntity.setUserEmail(userEntity.getUserEmail());
		System.out.println(userEntity.getUserEmail());
		userEntity.setUserUid(userEntity.getUserUid());
		userEntity.setUserUsername(userEntity.getUserUsername());
		userEntity.setUserStatus(userEntity.getUserStatus());
		
		UserEntity updateUser = userRepo.save(userEntity);
	
		return ResponseEntity.ok(updateUser);
		
	}

	
	@GetMapping("chat/getAllUser")
	public List<UserEntity> getAllUser(){
		return chatService.getAllUser();
	}
	
	
	@GetMapping("sendMessage/{uid}/{uidGet}")
	public List<ChatEntity> messageSendGet(
			@PathVariable("uid")String uid, @PathVariable("uidGet") String uidGet)
	{
		return chatService.messageSendGet(uid, uidGet);
	}
	
	
	
	@GetMapping("chat/getMessage/{uid}")
	public List<ChatEntity> messageGet(@PathVariable("uid") String uid){
		
		return chatService.messageGet(uid);
	
	}
	
	@GetMapping("chat/sendMessage/{uid}")
	public List<ChatEntity> messageSend(@PathVariable("uid") String uid){
		
		return chatService.messageSend(uid);
	
	}
	
	@GetMapping("chat/groupMessage/{uid}")
	public LinkedHashMap<String, ChatEntity> messageGroup(@PathVariable("uid") String uid){
		
		List<ChatEntity> groupMessage = chatService.messageGroup(uid);
		
		for(ChatEntity chat: groupMessage) {
			
			String send = chat.getUserSend(), get = chat.getUserGet();
			
			if(send.equals(uid)) {
				
				hash.put(get, chat);
				
			}
			
			else if(get.equals(uid)) {
				hash.put(send, chat);
			}
				
			
		}
		return hash;
	
	}
	
	
	@GetMapping("chat/groupAllMessage/{uid}")
	public LinkedHashMap<String, List<ChatEntity>> messageAllGroup(@PathVariable("uid") String uid){
		
		LinkedHashMap<String, List<ChatEntity>> linkedChat = new LinkedHashMap<>();
		
		List<ChatEntity>chatEntity = chatService.messageAllGroup(uid);
		
		for(ChatEntity chat: chatEntity) {
			
			List<ChatEntity> aux = new ArrayList<>();
			
			if(uid.equals(chat.getUserSend())) {
				
				if(linkedChat.containsKey(chat.getUserGet())) {
				
					aux = linkedChat.get(chat.getUserGet());
					aux.add(chat);
				}
				
				else {
					aux.add(chat);
				}
				
			}
			
			else if(uid.equals(chat.getUserGet())) {
				
				if(linkedChat.containsKey(chat.getUserSend())) {
					
					aux = linkedChat.get(chat.getUserSend());
					aux.add(chat);
				}
				
				else {
					aux.add(chat);
				}
				
			}
			
			linkedChat.put(chat.getUserGet(), aux);
			
		}
		
		return linkedChat;
		
		
	}

	
	/*@MessageMapping("/sendMessage")
    @SendTo("/topic/no")
    public ChatEntity sendMessage(ChatEntity chatEntity) {
        // Adicione um log para verificar o conteúdo da mensagem recebida
        System.out.println("Mensagem recebida para envio: " + chatEntity);
        
        chatEntity.setUserSend(chatEntity.getUserSend());
        chatEntity.setUserGet(chatEntity.getUserGet());
        chatEntity.setMessageChat(chatEntity.getMessageChat());
        chatEntity.setDateChat(chatEntity.getDateChat());
        
        
        // Envie uma notificação adicional
        notificationService.sendNotification(chatEntity);
        
        return chatEntity;
    }*/
	
	@GetMapping("chat/FriendListAll")
	public LinkedHashMap<String, List<FriendEntity>> getFriendListAll() {
		
	LinkedHashMap<String, List<FriendEntity>> linkedFriend = new LinkedHashMap<>();
	
	List<FriendEntity> friendEntity = chatService.getFriendListAll();
	List<FriendEntity> aux = new ArrayList<>();
	
	for(FriendEntity friend: friendEntity) {
		aux.clear();
		
		if(linkedFriend.containsKey(friend.getUserUid())) {
			
			aux = linkedFriend.get(friend.getUserUid());
			
		}
		
		aux.add(friend);
		linkedFriend.put(friend.getUserUid(), aux);
	}
	
	
		return linkedFriend;
	}
	
		
	@GetMapping("chat/FriendList/{uid}")
	public LinkedHashMap<String, FriendEntity> getFriendList(@PathVariable("uid") String uid) {
		System.out.println("uid"+ uid);
		List<FriendEntity> friendList = chatService.getFriendList(uid);
		List<FriendEntity> aux = new ArrayList<>();
		LinkedHashMap<String, FriendEntity> linkedList = new LinkedHashMap<>();
		
		for(FriendEntity list: friendList) {
			aux.clear();
			if(list.getUserUid().equals(uid)) {
				
				linkedList.put(list.getFriendUid(), list);
				
			}
			
			else if(list.getFriendUid().equals(uid)) {

				linkedList.put(list.getUserUid(), list);
				
			}
			
		}
		
		
		return linkedList;
		
	}
	
	@GetMapping("chat/UserSort")
	public LinkedHashMap<String, UserEntity> getUserSort() {
		
		List<UserEntity> userAll = chatService.getAllUser();
		
		LinkedHashMap<String, UserEntity> linkedUser = new LinkedHashMap<>();
		
		for(UserEntity user: userAll) {

			linkedUser.put(user.getUserUsername(), user);			
			
		}
		
		return linkedUser;	
	}
	
	
}
