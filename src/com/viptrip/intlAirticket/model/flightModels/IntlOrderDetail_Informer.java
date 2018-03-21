package com.viptrip.intlAirticket.model.flightModels;

public class IntlOrderDetail_Informer {
    
    public Integer id;// 编号 整型数字

    public String name;// 姓名 字符串

    public String email;// 邮箱 字符串

    public String tel;// 电话 字符串

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
    
    
}
