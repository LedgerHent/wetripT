package com.viptrip.wetrip.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "T_AC_INSURANCE")
public class TAcInsurance{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1693081858048012273L;
	
	@Id
	private Integer id;		//顺序号，保险编号
	
	@Column(name = "USER_ID")
	private int userID;		//操作人ID
	
	@Column(name = "NAME")
	private String name;	//操作人
	
	@Column(name = "OPERATING_DATE")
	private Date date;	//操作时间
	
	@Column(name = "FLOOR_PRICE")
	private double floorPrice;	//保险底价
	
	@Column(name = "PRICE")
	private double price;		//保险卖价
	
	@Column(name = "NOTE")
	private String note;	//保险描述
	
	@Column(name = "UP_DOWN_LINE")
	private String upDownLine;	//上下线：0-下线    1-上线
	
	@Column(name = "MAX_COUNT")
	private int maxCount;	//单人购买保险最大数量
	
	
	
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getFloorPrice() {
		return floorPrice;
	}
	public void setFloorPrice(double floorPrice) {
		this.floorPrice = floorPrice;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getUpDownLine() {
		return upDownLine;
	}
	public void setUpDownLine(String upDownLine) {
		this.upDownLine = upDownLine;
	}
	public int getMaxCount() {
		return maxCount;
	}
	public void setMaxCount(int maxCount) {
		this.maxCount = maxCount;
	}
	@Override
	public String toString() {
		return "TAcInsurance [id=" + id + ", userID=" + userID + ", name="
				+ name + ", date=" + date + ", floorPrice=" + floorPrice
				+ ", price=" + price + ", note=" + note + ", upDownLine="
				+ upDownLine + ", maxCount=" + maxCount + "]";
	}
	
	
}
