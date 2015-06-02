package com.anontech.utils;

public class LoginFailedException extends RuntimeException{

	@Override
	public String getMessage() {
		return "Unable to find Login Info";
	}

}
