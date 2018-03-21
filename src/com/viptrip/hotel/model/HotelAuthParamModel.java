package com.viptrip.hotel.model;

/**
 * 酒店对接首次认证参数model
 * @author Administrator
 *
 */
public class HotelAuthParamModel {
	private int userId;
	private String token;
	private int menuId;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
}
