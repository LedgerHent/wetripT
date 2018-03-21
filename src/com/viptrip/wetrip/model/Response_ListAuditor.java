package com.viptrip.wetrip.model;

import java.util.List;

import net.sf.json.JSONArray;

import com.viptrip.wetrip.model.base.Response_Base;
import com.viptrip.wetrip.model.employees.Auditor;

@SuppressWarnings("serial")
public class Response_ListAuditor extends Response_Base{

	public List<Auditor> data;//审核员信息列表

	@Override
	public String toString() {
		return "Response_ListAuditor [data=" + data + ", status=" + status
				+ ", getData()=" + getData() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	public List<Auditor> getData() {
		return data;
	}
	public void setData(List<Auditor> data) {
		this.data = data;
	}
}
