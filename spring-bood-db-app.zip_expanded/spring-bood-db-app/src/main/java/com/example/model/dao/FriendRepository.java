package com.example.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.model.beans.Friend;

public interface FriendRepository extends JpaRepository<Friend, Integer> {
	
	@Query("select f from Friend f where profileIdRef = ?1")
	public List<Friend> getFriendsFromProfile(int profileId);
}
