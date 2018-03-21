package com.viptrip.wetrip.model.base;

import java.util.List;



@SuppressWarnings("serial")
public class Response_PersonMessage extends Response_IdAndName{
	
	public String mobile;
	
	public String email;
	
	public Integer type;//员工类型 1=成人，2=儿童
	
	public Integer sex;//员工性别 0-未知，1=男，2=女，3-保密
	
	public String birthday;//员工生日 1900-01-01
	
	public List<Response_CertificateMessage> ids;//员工证件

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public List<Response_CertificateMessage> getIds() {
		return ids;
	}

	public void setIds(List<Response_CertificateMessage> ids) {
		this.ids = ids;
	}

	
	
}
