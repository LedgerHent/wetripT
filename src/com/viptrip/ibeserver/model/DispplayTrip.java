package com.viptrip.ibeserver.model;

import java.io.Serializable;
import java.util.Date;

public class DispplayTrip implements Cloneable,Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8357633503808616623L;
	// 航班号
	private String airline;
	// 起飞地点
	private String orgcity;
	// 抵达地点
	private String detcity;
	// 起飞日期（含时间）
	private Date startdate;
	// 起飞时刻
	private String starttime;
	// 到达日期（含时间）
	private Date arrvidate;
	// 到达时刻
	private String arrvitime;
	// 舱位
	private String cangwei;
	// 餐食有无，有为Y，无为N
	private String food;
	// 航班代码共享
	private String airlineshare;
	// 机型
	private String planetype;
	// 登机航站楼
	private String orgterm;
	// 到达航站楼
	private String detterm;
	// 价格
	private Double price;
	// 机场税
	private Double taxfee;
	// 燃油税
	private Double fueltax;
	// 折扣率
	private Double discountrate;
	// 截止日期
	private String enddate;
	// 规则代码，
	// 这个属性在查询航信接口时未使用，用于获取“运价基础”（fareBasis），此属性中包含舱位代码及价格的规则（给景鸿的特惠产品），编码根据产品不同而不同
	private String rulecode;
	// 剩余座位
	private String seatsLeft;
	// Y舱票面价
	private Double yPrice;
	
	private Double aprice;        //A舱票面价
	private Double fprice;        //F舱票面价
	
	// 公布运价
	private Double pubPrice;
	// 舱位等级
	private String cbClass;
	// 优惠价格
	private Double rebatePrice;
	//RT_ADT_A_PEK_SHA_2017-04-15*2017-04-25|CA1234#F1-PUBLIC*CA1235#F-AGREEMENT_MU538#C-PRIVATE
	private String mapKey;
	// MyEnum.PriceAgreementTypeCode
	private String agreementTypeCode;
	
	private String cangweiDesc;
	// 中转标志，如果有中转，此属性有值
	private String ffstr; // 格式为：抵达城市三字码_抵达时间yyyyMMdd HH:mm_出发时间yyyyMMdd HH:mm

	private String excessinfo;//超标类型： 1：不是最低价超标  2：金额在预定权限超标  3：折扣在预定权限超标  4：舱位不在预定权限超标
//  5：  航司不在预定权限超标  6：航线不在预定权限超标 7：航班不在预定权限超标
  
	private String showRule;//10：隐藏  20：正常显示 21：超标显示
  
	public DispplayTrip(String airline, String orgcity, String detcity,
			Date startdate, String starttime, Date arrvidate, String arrvitime,
			String cangwei, String food, String airlineshare, String planetype,
			String orgterm, String detterm, Double price, Double taxfee,
			Double fueltax, Double discountrate, String enddate,
			String rulecode, String seatsLeft, String cangweiDesc,
			Double yPrice, String ffstr,Double pubPrice,Double rebatePrice,
			String cbClass,String agreementTypeCode,String mapKey,String excessinfo,String showRule) {
		
		this.mapKey=mapKey;
		this.agreementTypeCode=agreementTypeCode;
		this.cbClass=cbClass;
		this.rebatePrice=rebatePrice;
		this.pubPrice=pubPrice;
		this.airline = airline;
		this.orgcity = orgcity;
		this.detcity = detcity;
		this.startdate = startdate;
		this.starttime = starttime;
		this.arrvidate = arrvidate;
		this.arrvitime = arrvitime;
		this.cangwei = cangwei;
		this.food = food;
		this.airlineshare = airlineshare;
		this.planetype = planetype;
		this.orgterm = orgterm;
		this.detterm = detterm;
		this.price = price;
		this.taxfee = taxfee;
		this.fueltax = fueltax;
		this.discountrate = discountrate;
		this.enddate = enddate;
		this.rulecode = rulecode;
		this.seatsLeft = seatsLeft;
		this.cangweiDesc = cangweiDesc;
		this.yPrice = yPrice;
		this.ffstr = ffstr;
		this.excessinfo=excessinfo;
		this.showRule=showRule;
	}

	public DispplayTrip() {

	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public String getOrgcity() {
		return orgcity;
	}

	public void setOrgcity(String orgcity) {
		this.orgcity = orgcity;
	}

	public String getDetcity() {
		return detcity;
	}

	public void setDetcity(String detcity) {
		this.detcity = detcity;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public Date getArrvidate() {
		return arrvidate;
	}

	public void setArrvidate(Date arrvidate) {
		this.arrvidate = arrvidate;
	}

	public String getArrvitime() {
		return arrvitime;
	}

	public void setArrvitime(String arrvitime) {
		this.arrvitime = arrvitime;
	}

	public String getCangwei() {
		return cangwei;
	}

	public void setCangwei(String cangwei) {
		this.cangwei = cangwei;
	}

	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
	}

	public String getAirlineshare() {
		return airlineshare;
	}

	public void setAirlineshare(String airlineshare) {
		this.airlineshare = airlineshare;
	}

	public String getPlanetype() {
		return planetype;
	}

	public void setPlanetype(String planetype) {
		this.planetype = planetype;
	}

	public String getOrgterm() {
		return orgterm;
	}

	public void setOrgterm(String orgterm) {
		this.orgterm = orgterm;
	}

	public String getDetterm() {
		return detterm;
	}

	public void setDetterm(String detterm) {
		this.detterm = detterm;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getTaxfee() {
		return taxfee;
	}

	public void setTaxfee(Double taxfee) {
		this.taxfee = taxfee;
	}

	public Double getFueltax() {
		return fueltax;
	}

	public void setFueltax(Double fueltax) {
		this.fueltax = fueltax;
	}

	public Double getDiscountrate() {
		return discountrate;
	}

	public void setDiscountrate(Double discountrate) {
		this.discountrate = discountrate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getRulecode() {
		return rulecode;
	}

	public void setRulecode(String rulecode) {
		this.rulecode = rulecode;
	}

	public String getSeatsLeft() {
		return seatsLeft;
	}

	public void setSeatsLeft(String seatsLeft) {
		this.seatsLeft = seatsLeft;
	}

	public Double getyPrice() {
		return yPrice;
	}

	public void setyPrice(Double yPrice) {
		this.yPrice = yPrice;
	}

	public String getCangweiDesc() {
		return cangweiDesc;
	}

	public void setCangweiDesc(String cangweiDesc) {
		this.cangweiDesc = cangweiDesc;
	}

	public String getFfstr() {
		return ffstr;
	}

	public void setFfstr(String ffstr) {
		this.ffstr = ffstr;
	}

	public Double getPubPrice() {
		return pubPrice;
	}

	public void setPubPrice(Double pubPrice) {
		this.pubPrice = pubPrice;
	}

	public String getCbClass() {
		return cbClass;
	}

	public void setCbClass(String cbClass) {
		this.cbClass = cbClass;
	}

	public Double getRebatePrice() {
		return rebatePrice;
	}

	public void setRebatePrice(Double rebatePrice) {
		this.rebatePrice = rebatePrice;
	}

	public String getAgreementTypeCode() {
		return agreementTypeCode;
	}

	public void setAgreementTypeCode(String agreementTypeCode) {
		this.agreementTypeCode = agreementTypeCode;
	}

	public String getMapKey() {
		return mapKey;
	}

	public void setMapKey(String mapKey) {
		this.mapKey = mapKey;
	}
	
    public Double getAprice() {
        return aprice;
    }

    public void setAprice(Double aprice) {
        this.aprice = aprice;
    }

    public Double getFprice() {
        return fprice;
    }

    public void setFprice(Double fprice) {
        this.fprice = fprice;
    }

    public String getExcessinfo() {
        return excessinfo;
    }

    public void setExcessinfo(String excessinfo) {
        this.excessinfo = excessinfo;
    }

    public String getShowRule() {
        return showRule;
    }

    public void setShowRule(String showRule) {
        this.showRule = showRule;
    }

    @Override
    public Object clone(){
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
