package com.example.demo.ChatRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.ChatEntity.FriendEntity;

public interface FriendRepository extends JpaRepository<FriendEntity, Long>{

	@Query("SELECT u FROM FriendEntity u WHERE u.userUid = :uid OR u.friendUid = :uid ORDER BY u.friendId")
	List<FriendEntity> findByUid(@Param("uid") String uid);
	
	@Query("SELECT u FROM FriendEntity u WHERE u.userUid = :uidUser AND u.friendUid = :uidFriend")
	FriendEntity findRequest(@Param("uidUser")String uidUser, @Param("uidFriend")String uidFriend);
	
}
