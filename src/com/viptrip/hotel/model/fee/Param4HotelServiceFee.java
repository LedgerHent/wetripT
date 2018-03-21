package com.viptrip.hotel.model.fee;

public class Param4HotelServiceFee {
	public int nightRooms;//总间夜数
	public Double amount;//总间夜数
	public int protocolType;//协议类别，1-企业自有协议预订服务费，2-景鸿代开发协议预订服务费，3-景鸿协议预订服务费，4-变更服务费 （协议酒店传1，平台酒店传2）
	public int payType;//预订服务费付款方式，0-默认（变更服务费传此值），1-预付，2-现付。
	public String time;//请求时间，计算服务费以此时间为准，15:30，冒号为英文冒号
}
