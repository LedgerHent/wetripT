package com.viptrip.wetrip.model;

import java.util.List;

import net.sf.json.JSONArray;

import com.viptrip.wetrip.model.base.Response_Base;
import com.viptrip.wetrip.model.employees.ListEmployee;


@SuppressWarnings("serial")
public class Response_ListStaff extends Response_Base{

	public List<ListEmployee> data;//员工信息列表


	@Override
	public String toString() {
		return "Response_ListStaff [data=" + data + ", status=" + status
				+ ", getData()=" + getData() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	public List<ListEmployee> getData() {
		return data;
	}
	public void setData(List<ListEmployee> data) {
		this.data = data;
	}


}
