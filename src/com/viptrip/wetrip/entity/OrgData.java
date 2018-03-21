/**
 * 
 */
package com.viptrip.wetrip.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 李洪锋
 * @datetime 2016-6-21
 * @class AppOrgData.java
 */
public class OrgData {
	
	
	private Long orgid;
	private String orgname;
	private List child= new ArrayList();
	public Long getOrgid() {
		return orgid;
	}
	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	
	public List getChild() {
		return child;
	}
	public void setChild(List child) {
		this.child = child;
	}
	
	
}
