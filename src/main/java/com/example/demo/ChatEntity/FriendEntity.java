package com.example.demo.ChatEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "friendlist")
@AllArgsConstructor
@NoArgsConstructor
public class FriendEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "friend_id")
	private Long friendId;
	
	@Column(name = "user_Uid")
	private String userUid;
	
	@Column(name = "friend_Uid")
	private String friendUid;
	
	@Column(name = "friend_status")
	private String friendStatus;
	
	@Column(name = "friend_Date")
	private String friendDate;
}
