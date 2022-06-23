package com.example.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exceptions.ProfileNotFoundException;
import com.example.model.beans.Friend;
import com.example.model.beans.Profile;
import com.example.model.dao.FriendRepository;
import com.example.model.dao.ProfileRepository;

@Service
public class ProfileServiceImpl implements ProfileService{

	@Autowired
	private ProfileRepository profileDao;
	
	@Autowired
	private FriendRepository FriendDao;
	
	@Transactional
	@Override
	public Profile storeProfile(Profile profile) {
		Profile createdProfile = profileDao.save(profile);
		return createdProfile;
	}

	@Override
	public List<Profile> fetchProfile() {
		List<Profile> list = profileDao.findAll();
		return list;
	}

	@Override
	public Profile fetchProfile(int id) throws ProfileNotFoundException {
		Profile profile = profileDao.findById(id).orElse(null);
		if(profile == null) {
			throw new ProfileNotFoundException("Profile with an id "+id+" is not found!");
		}
		List<Friend> friends = FriendDao.getFriendsFromProfile(id);
		profile.setFriends(friends);
		return profile;
	}

	@Override
	public Profile changePhone(int id, long phone) throws ProfileNotFoundException {
		Profile profile = profileDao.findById(id).orElse(null);
		if(profile == null) {
			throw new ProfileNotFoundException("Profile with an id "+id+" is not found!");
		}
		profile.setPhone(phone);
		profile = profileDao.save(profile);
		return profile;
	}

	@Override
	public Profile deleteFriend(int id, int id2) throws ProfileNotFoundException {
		Profile profile = profileDao.findById(id).orElse(null);
		if(profile == null) {
			throw new ProfileNotFoundException("Profile with an id "+id+" is not found!");
		}
		//delete the friend
		List<Friend> friends = FriendDao.getFriendsFromProfile(id);
		for(Friend f : friends) {
			if(f.getId() == id2) {
				friends.remove(f);
				profile.setFriends(friends);
			}
		}
		profile = profileDao.save(profile);
		return profile;
	}

}
