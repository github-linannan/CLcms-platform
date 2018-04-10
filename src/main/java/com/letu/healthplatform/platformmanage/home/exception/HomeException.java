package com.letu.healthplatform.platformmanage.home.exception;

public class HomeException extends RuntimeException {
	protected String key;
	public HomeException(String key){
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
