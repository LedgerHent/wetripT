package com.viptrip.hotelHtml5.vo.tmc;

import java.util.List;

@SuppressWarnings("serial")
public class OrgFramework implements JhData{
	
	private String id;
	private String name;
	private List<OrgFramework> child;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<OrgFramework> getChild() {
		return child;
	}
	public void setChild(List<OrgFramework> child) {
		this.child = child;
	}

}
