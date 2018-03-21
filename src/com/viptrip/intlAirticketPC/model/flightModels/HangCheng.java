package com.viptrip.intlAirticketPC.model.flightModels;

import java.util.List;

public class HangCheng implements Cloneable{
	
	private HangBan hangBan_fl;
	private List<HangBan> hangbanList;
	
	private String totalPrice;	//总价（不含税），往返对应的是打包总价在回程list中显示。
	private String totalTaxPrice;//总价 含税
	private String transitCount; //中转次数，0表示直飞无中转
	private int zNumber;//座位
	
	private String flightKey;//查询更多舱位、退改签使用的KEY
	
	private String submitOption;//提交订单用参数列
	
	private String tuiGaiSign;//退改信息
	
	private List<HangCheng> huichengList;
	
	private List<HangCheng> moreHangchengList;
	
	private String mapKey;
	private String seatsLeft; 

	@Override  
    public HangCheng clone() throws CloneNotSupportedException  
    {  
        return (HangCheng)super.clone();  
    }  
	public HangBan getHangBan_fl() {
		return hangBan_fl;
	}

	public void setHangBan_fl(HangBan hangBan_fl) {
		this.hangBan_fl = hangBan_fl;
	}

	public List<HangBan> getHangbanList() {
		return hangbanList;
	}

	public void setHangbanList(List<HangBan> hangbanList) {
		this.hangbanList = hangbanList;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getTransitCount() {
		return transitCount;
	}

	public void setTransitCount(String transitCount) {
		this.transitCount = transitCount;
	}

	public List<HangCheng> getHuichengList() {
		return huichengList;
	}

	public void setHuichengList(List<HangCheng> huichengList) {
		this.huichengList = huichengList;
	}

	public String getTotalTaxPrice() {
		return totalTaxPrice;
	}

	public void setTotalTaxPrice(String totalTaxPrice) {
		this.totalTaxPrice = totalTaxPrice;
	}

	public String getMapKey() {
		return mapKey;
	}

	public void setMapKey(String mapKey) {
		this.mapKey = mapKey;
	}

	public String getSeatsLeft() {
		return seatsLeft;
	}

	public void setSeatsLeft(String seatsLeft) {
		this.seatsLeft = seatsLeft;
	}

	public void calculateZNumber(HangCheng qu_hangcheng) {
		String cangwei = this.getHangBan_fl().getCangwei();
		String[] qu_fan = cangwei.split("/");
		int zNum = Integer.MAX_VALUE;
		List<HangBan> hangduans_1 = null;
		for (int i = 0; i < qu_fan.length; i++) {
			switch (i) {
			case 0://去程
				hangduans_1 = qu_hangcheng.getHangbanList();
				zNum = format(qu_fan, zNum, i, hangduans_1);
				break;
			case 1://回程
				hangduans_1 = this.getHangbanList();
				zNum = format(qu_fan, zNum, i, hangduans_1);
				break;
			default:
				break;
			}
		}
		this.setzNumber(zNum);
	}

	private int format(String[] qu_fan, int zNum, int i,
			List<HangBan> hangduans_1) {
		String hangduans = qu_fan[i];
		String[] hangduan = hangduans.split(",");
		for (int j = 0; j < hangduan.length; j++) {
			String cangwei_ = hangduan[j];
			zNum = getZnum(cangwei_,hangduans_1.get(j).getCangwei(),zNum);
		}
		return zNum;
	}

	private int getZnum(String cangwei_, String cangwei,int num) {
		String[] cangweis = cangwei.split(",");
		for (int i = 0; i < cangweis.length; i++) {
			String t = cangweis[i];
			if(t.contains(cangwei_)){
				int n = Integer.parseInt(t.split(":")[1]);
				return n<num?n:num;
			}
		}
		return num;
	}

	public int getzNumber() {
		return zNumber;
	}

	public void setzNumber(int zNumber) {
		this.zNumber = zNumber;
	}
	
	public void splitJointFlightKey(HangCheng qu_hangcheng) {
		if(this.getHuichengList()==null || this.getHuichengList().size()==0){
			this.setFlightKey(qu_hangcheng.getMapKey()); 
		}else{
			for (int i = 0; i < this.getHuichengList().size(); i++) {
				this.getHuichengList().get(i).setFlightKey(this.getHuichengList().get(i).getMapKey());
			}
		}
	}
	
	/**
	 * 去程航班串儿
	 * @return
	 */
	private String getGoCangweiKey(List<HangBan> hangbanList) {
		String cangweiKey="";
		for (int i = 0; i < hangbanList.size(); i++) {
			cangweiKey+=(hangbanList.get(i).getAirline()+"_");
		}
		cangweiKey=cangweiKey.substring(0, cangweiKey.length()-1);
		return cangweiKey;
	}

	public String getFlightKey() {
		return flightKey;
	}

	public void setFlightKey(String flightKey) {
		this.flightKey = flightKey;
	}

	public String getSubmitOption() {
		return submitOption;
	}

	public void setSubmitOption(String submitOption) {
		this.submitOption = submitOption;
	}

	public List<HangCheng> getMoreHangchengList() {
		return moreHangchengList;
	}

	public void setMoreHangchengList(List<HangCheng> moreHangchengList) {
		this.moreHangchengList = moreHangchengList;
	}
	
	public String getTuiGaiSign() {
		return tuiGaiSign;
	}
	public void setTuiGaiSign(String tuiGaiSign) {
		this.tuiGaiSign = tuiGaiSign;
	}

	
}
