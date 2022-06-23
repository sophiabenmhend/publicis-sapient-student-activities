package com.example.model.service;

import java.util.List;

import com.example.exceptions.ProfileNotFoundException;
import com.example.model.beans.Profile;

//interface for business layer -- implemented by developer

public interface ProfileService {
	//stores profile + creates returned profile
	public Profile storeProfile(Profile profile);
	//returns all profiles in the List<Profile>
	public List<Profile> fetchProfile();
	
	public Profile fetchProfile(int id) throws ProfileNotFoundException;
	
	public Profile changePhone(int id, long phone) throws ProfileNotFoundException;
	
	public Profile deleteFriend(int id, int id2) throws ProfileNotFoundException;
}
