package com.example.demo.ChatRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.ChatEntity.*;

public interface ChatRepository extends JpaRepository<ChatEntity, Long>{
	
	@Query("SELECT u FROM ChatEntity u WHERE u.userSend = :uid OR u.userGet = :uid ORDER BY u.dateChat ASC")
	List<ChatEntity> findByUid(@Param("uid") String uid);

	@Query("SELECT u FROM ChatEntity u WHERE u.userSend = :uid AND u.userGet = :uidGet ORDER BY u.dateChat ASC")
	List<ChatEntity> findSendGetMessage(@Param("uid") String uid, @Param("uidGet") String uidGet);
	
	@Query("SELECT u FROM ChatEntity u WHERE u.userGet = :uid ORDER BY u.dateChat ASC")
	List<ChatEntity> findGetMessage(@Param("uid") String uid);
	
	@Query("SELECT u FROM ChatEntity u WHERE u.userSend = :uid ORDER BY u.dateChat ASC")
	List<ChatEntity> findSendMessage(@Param("uid") String uid);
	

}
