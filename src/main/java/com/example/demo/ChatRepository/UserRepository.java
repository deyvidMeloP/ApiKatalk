package com.example.demo.ChatRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.ChatEntity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
	
	@Query("SELECT u FROM UserEntity u WHERE u.userUsername = :username")
	  List<UserEntity> findByUsername(@Param("username") String username);
	
	@Query("SELECT u FROM UserEntity u WHERE u.userUid = :uid")
	  UserEntity findByUid(@Param("uid") String uid);
}
