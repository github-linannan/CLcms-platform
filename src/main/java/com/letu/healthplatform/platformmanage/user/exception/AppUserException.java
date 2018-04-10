package com.letu.healthplatform.platformmanage.user.exception;

public class AppUserException extends RuntimeException {
	protected String key;
	public AppUserException(String key){
		super(key);
		this.key=key;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
}
