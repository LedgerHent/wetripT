package com.viptrip.intlAirticket.model.flightModels;

public class IntlOrderDetail_Passenger {
    
    public Integer id;// 乘机人id 整型数字

    public String name;// 乘机人姓名 字符串

    public String tel;// 电话 字符串

    public String email;// Email 字符串

    public String orgid;// 费用归属 字符串

    public String project;// 项目号 字符串

    public Integer idType;// 证件类型 整型数字

    public String idNum;// 证件号码 字符串

    public Integer insuranceNum;// 保险数量 整型数字

    public Double insurancePrince;// 保险价格 双精度数字

    public Double taxPrice;// 税总价 双精度数字

    public Double ticketPrice;// 票价格 双精度数字

    public Double servicePrice;// 服务费 双精度数字

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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public Integer getIdType() {
		return idType;
	}

	public void setIdType(Integer idType) {
		this.idType = idType;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public Integer getInsuranceNum() {
		return insuranceNum;
	}

	public void setInsuranceNum(Integer insuranceNum) {
		this.insuranceNum = insuranceNum;
	}

	public Double getInsurancePrince() {
		return insurancePrince;
	}

	public void setInsurancePrince(Double insurancePrince) {
		this.insurancePrince = insurancePrince;
	}

	public Double getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(Double taxPrice) {
		this.taxPrice = taxPrice;
	}

	public Double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(Double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public Double getServicePrice() {
		return servicePrice;
	}

	public void setServicePrice(Double servicePrice) {
		this.servicePrice = servicePrice;
	}
    
    

    
}
