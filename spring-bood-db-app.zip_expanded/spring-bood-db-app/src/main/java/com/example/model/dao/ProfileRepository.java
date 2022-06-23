package com.example.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.beans.Profile;

//profile repository performs operations on profile entity + primary key type is integer -- we get all of the methods from JpaRepository

public interface ProfileRepository extends JpaRepository<Profile, Integer>{

}
