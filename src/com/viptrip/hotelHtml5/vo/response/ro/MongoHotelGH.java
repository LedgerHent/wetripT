package com.viptrip.hotelHtml5.vo.response.ro;

import java.io.Serializable;

public class MongoHotelGH implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String  ghId;                //集团id
    private String  ghCnName;            //集团中文名
    private String  ghEnName;            //集团英文名
	public String getGhId() {
		return ghId;
	}
	public void setGhId(String ghId) {
		this.ghId = ghId;
	}
	public String getGhCnName() {
		return ghCnName;
	}
	public void setGhCnName(String ghCnName) {
		this.ghCnName = ghCnName;
	}
	public String getGhEnName() {
		return ghEnName;
	}
	public void setGhEnName(String ghEnName) {
		this.ghEnName = ghEnName;
	}
}
