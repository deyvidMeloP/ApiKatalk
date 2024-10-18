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

@Entity
@Data
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
    private Long userId;
	
	@Column(name = "user_uid")
    private String userUid;
	
	@Column(name = "user_email")
    private String userEmail;
	
	@Column(name = "user_username")
    private String userUsername;
	
	@Column(name = "user_Status")
    private int userStatus;
	
	
}
