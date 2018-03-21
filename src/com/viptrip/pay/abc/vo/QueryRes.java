package com.viptrip.pay.abc.vo;

import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;


public class QueryRes<E extends QueryResObj> {
	
	
	private String PayTypeID;
	private String OrderNo;
	private String OrderDate;
	private String OrderTime;
	private String Status;
	private  E data;

	private List<ProductItem> list;

	public QueryRes() {
		super();
		
	}
	
	public QueryRes(String payTypeID, String orderNo, String orderDate,
			String orderTime, String status, E data) {
		PayTypeID = payTypeID;
		OrderNo = orderNo;
		OrderDate = orderDate;
		OrderTime = orderTime;
		Status = status;
		this.data = data;
	}

	
	public static void main(String[] args) {
		QueryRes<QueryResDivided> res = new QueryRes<QueryResDivided>();
		res.setData(new QueryResDivided());
		Class<? extends QueryRes> class1 = res.getClass();
		Type[] types1 = class1.getGenericInterfaces();
		Type types2 = class1.getGenericSuperclass();
		System.out.println();
		
	}

	public List<ProductItem> getList() {
		return list;
	}

	public void setList(List<ProductItem> list) {
		this.list = list;
	}

	public String getPayTypeID() {
		return PayTypeID;
	}
	public void setPayTypeID(String payTypeID) {
		PayTypeID = payTypeID;
	}
	public String getOrderNo() {
		return OrderNo;
	}
	public void setOrderNo(String orderNo) {
		OrderNo = orderNo;
	}
	public String getOrderDate() {
		return OrderDate;
	}
	public void setOrderDate(String orderDate) {
		OrderDate = orderDate;
	}
	public String getOrderTime() {
		return OrderTime;
	}
	public void setOrderTime(String orderTime) {
		OrderTime = orderTime;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public E getData() {
		return data;
	}
	public void setData(E data) {
		this.data = data;
	}
	
	
	
	
}
