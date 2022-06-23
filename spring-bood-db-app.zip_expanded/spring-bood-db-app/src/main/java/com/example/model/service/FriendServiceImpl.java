package com.example.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.beans.Friend;
import com.example.model.dao.FriendRepository;

@Service
public class FriendServiceImpl implements FriendService{
	
	@Autowired
	private FriendRepository FriendDao;
	
	@Override
	public Friend addFriend(int profileIdRef, Friend friend) {
		friend.setProfileIdRef(profileIdRef);		
		friend.setId(profileIdRef+1);
		return FriendDao.save(friend);
	}
}
