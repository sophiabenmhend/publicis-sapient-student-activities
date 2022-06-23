package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exceptions.ProfileNotFoundException;
import com.example.model.beans.Friend;
import com.example.model.beans.Profile;
import com.example.model.service.FriendService;
import com.example.model.service.ProfileService;

@RestController
@RequestMapping("/profile")
public class ProfileController {

	
		
		@Autowired
		private ProfileService profileService;
		
		@Autowired
		private FriendService friendService;
		
		@PostMapping
		public ResponseEntity<Object> store(@RequestBody Profile profile) {
			Profile storedProfile = profileService.storeProfile(profile);
			return ResponseEntity.status(HttpStatus.CREATED).body(storedProfile);			
		}
		
		@GetMapping
		public ResponseEntity<Object> getProfiles() {
			List<Profile> list = profileService.fetchProfile();
			return ResponseEntity.status(HttpStatus.OK).body(list);
		}
		
		@GetMapping("/{id}")
		public ResponseEntity<Object> getProfile(@PathVariable("id")int id) {
			try {
				Profile profile = profileService.fetchProfile(id);
				return ResponseEntity.status(HttpStatus.OK).body(profile);
			} catch (ProfileNotFoundException e) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("error", e.getMessage());
				map.put("status", "404");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
				}
		}
		
		@PutMapping("/{id}/{phone}")
		public ResponseEntity<Object> changePhone(@PathVariable("id")int id, @PathVariable("phone")long phone) throws ProfileNotFoundException{
			Map<String, String> map = new HashMap<String, String>();
			try {
				Profile profile = profileService.changePhone(id, phone);
				map.put("phone", Long.toString(profile.getPhone()));
				return ResponseEntity.status(HttpStatus.OK).body(map);
			} catch (ProfileNotFoundException e) {
				map.put("error", e.getMessage());
				map.put("status", "404");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
			}
		}
		
		@DeleteMapping("/{id}/{id2}")
		public ResponseEntity<Object> deleteFriend(@PathVariable("id")int id, @PathVariable("id2")int id2) throws ProfileNotFoundException{
			Map<String, String> map = new HashMap<String, String>();
			try {
				Profile profile = profileService.deleteFriend(id, id2);
				map.put("friend deleted's id ", ""+id2);
				return ResponseEntity.status(HttpStatus.OK).body(map);
			} catch (ProfileNotFoundException e) {
				map.put("error", e.getMessage());
				map.put("status", "404");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
			}
		}
		
		@PostMapping("/{profileId}")
		public ResponseEntity<Object> addFriend(@RequestBody Friend friend, @PathVariable("profileId") int id) {
			Friend createdFriend = friendService.addFriend(id, friend);
			Map<String, String> map = new HashMap<String, String>();
			map.put("message", "Added "+createdFriend.getName());
			map.put("descritipon", "Added to profile id: "+createdFriend.getProfileIdRef());
			return ResponseEntity.status(HttpStatus.OK).body(map);
		}
		
}
