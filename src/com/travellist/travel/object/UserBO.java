package com.travellist.travel.object;

public class UserBO {
	long id;
	String accessToken;
	String tokenSecret;
	String screenId;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getScreenId() {
		return screenId;
	}
	public void setScreenId(String screenId) {
		this.screenId = screenId;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getTokenSecret() {
		return tokenSecret;
	}
	public void setTokenSecret(String tokenSecret) {
		this.tokenSecret = tokenSecret;
	}
	@Override
	public String toString() {
		return "UserBO [id=" + id + ", accessToken=" + accessToken
				+ ", tokenSecret=" + tokenSecret + "]";
	}
}


