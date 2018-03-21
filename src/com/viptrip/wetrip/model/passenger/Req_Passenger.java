package com.viptrip.wetrip.model.passenger;

import java.util.List;

import com.viptrip.wetrip.model.base.Req_NameMobileAndEmail;


public class Req_Passenger extends Req_NameMobileAndEmail{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7250213088073416711L;
	public Integer id;
	
    public Integer type;//员工类型 1=成人，2=儿童,3=婴儿
	
	public Integer sex;//员工性别 0-未知，1=男，2=女，3-保密
	
	public String birthday;//员工生日 1900-01-01
	
	public List<ReqData_CertificateMessage> ids;  //员工证件
	


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

	public List<ReqData_CertificateMessage> getIds() {
		return ids;
	}

	public void setIds(List<ReqData_CertificateMessage> ids) {
		this.ids = ids;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	

}
