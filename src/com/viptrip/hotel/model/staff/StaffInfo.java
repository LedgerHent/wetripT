package com.viptrip.hotel.model.staff;

import java.util.List;


public class StaffInfo {
	//新增的时候，该值没有意义，默认为-1，其他时候，有有效值
	public Integer id;
	public String name;
	public String mobile;
	public String email;
	//1=成人，2=儿童 
	public Integer type;
	//0-未知，1=男，2=女，3-保密
	public Integer sex;
	public String birthday;
	public List<Ids> ids;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	public List<Ids> getIds() {
		return ids;
	}
	public void setIds(List<Ids> ids) {
		this.ids = ids;
	}
	
	
}
