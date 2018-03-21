package com.viptrip.register.model;


/**
 * 用户注册基本信息model
 * @author Administrator
 *
 */
public class RegisterUserInfoModel {
	private String username;	//人员姓名
	private Long orgid;			//机构ID
	private String email;		//邮箱
	private String phone;		//手机号码
	private String idcard;		//身份证
	private String passport;	//护照号
	private String pwd;	  //用户密码
	private String passportEnd;	//护照有效期结束
	private String passportEn;	//护照英文名
	private String nationalityTwo;	//国籍二字码
	

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getOrgid() {
		return orgid;
	}
	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getPassport() {
		return passport;
	}
	public void setPassport(String passport) {
		this.passport = passport;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	
	public String getPassportEnd() {
		return passportEnd;
	}
	public void setPassportEnd(String passportEnd) {
		this.passportEnd = passportEnd;
	}

	public String getNationalityTwo() {
		return nationalityTwo;
	}
	public void setNationalityTwo(String nationalityTwo) {
		this.nationalityTwo = nationalityTwo;
	}
	public String getPassportEn() {
		return passportEn;
	}
	public void setPassportEn(String passportEn) {
		this.passportEn = passportEn;
	}
	
	
	
}
