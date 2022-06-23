package com.example.exceptions;

@SuppressWarnings("serial")
public class ProfileNotFoundException extends Exception{

	public ProfileNotFoundException() {
		super();
	}

	public ProfileNotFoundException(String message) {
		super(message);
	}
	
}
