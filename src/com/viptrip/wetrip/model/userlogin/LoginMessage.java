package com.viptrip.wetrip.model.userlogin;


public class LoginMessage {
	public Long platformId;//平台id
	public UserModel user;//用户信息
	public Long getPlatformId() {
		return platformId;
	}
	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}
	public UserModel getUser() {
		return user;
	}
	public void setUser(UserModel user) {
		this.user = user;
	}
	
}
