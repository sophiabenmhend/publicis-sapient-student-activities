package com.example.model.service;

import org.springframework.stereotype.Service;

import com.example.model.beans.Friend;

@Service
public interface FriendService {
	
	public Friend addFriend(int profileIdRef, Friend friend);

}
