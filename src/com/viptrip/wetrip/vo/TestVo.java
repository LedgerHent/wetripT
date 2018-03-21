package com.viptrip.wetrip.vo;

import com.viptrip.wetrip.vo.base.BaseVo;

public class TestVo implements BaseVo{

	private static final long serialVersionUID = 4865584552613467993L;
	
	private String name;
	private String pwd;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	
}
