package com.viptrip.wetrip.model.policy;

import java.util.List;

import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.entity.policy.Bookpower;


public class BookpowerVO {
	private Integer bookpowerId;
	private Integer userId;
	private Integer traveltype;
	private Integer powerCode;
	private List<TAcUser> user;
	
	

	public BookpowerVO() {
	}
	
	public BookpowerVO(Bookpower bp,List<TAcUser> user) {
		if(null!=bp){
			this.bookpowerId = bp.getBookpowerId();
			this.userId = bp.getUserId();
			this.traveltype = bp.getTraveltype();
			this.powerCode = bp.getPowerCode();
			this.user = user;
		}
	}
	

	public BookpowerVO(Integer bookpowerId, Integer userId, Integer traveltype,
			Integer powerCode, List<TAcUser> user) {
		this.bookpowerId = bookpowerId;
		this.userId = userId;
		this.traveltype = traveltype;
		this.powerCode = powerCode;
		this.user = user;
	}


	public Integer getBookpowerId() {
		return bookpowerId;
	}

	public void setBookpowerId(Integer bookpowerId) {
		this.bookpowerId = bookpowerId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getTraveltype() {
		return traveltype;
	}

	public void setTraveltype(Integer traveltype) {
		this.traveltype = traveltype;
	}

	public Integer getPowerCode() {
		return powerCode;
	}

	public void setPowerCode(Integer powerCode) {
		this.powerCode = powerCode;
	}

	public List<TAcUser> getUser() {
		return user;
	}

	public void setUser(List<TAcUser> user) {
		this.user = user;
	}

}
