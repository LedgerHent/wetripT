package com.viptrip.intlAirticket.model.flightModels;

public class Resp_Data_QuitUpdateTicket {

	private int flightType;			//1-去程，2-回程
	
	private String tuipiao;			//退票规定
	
	private String gaiqian;			//改签规定
	
	private String wuji;			//误机规定

	public int getFlightType() {
		return flightType;
	}

	public void setFlightType(int flightType) {
		this.flightType = flightType;
	}

	public String getTuipiao() {
		return tuipiao;
	}

	public void setTuipiao(String tuipiao) {
		this.tuipiao = tuipiao;
	}

	public String getGaiqian() {
		return gaiqian;
	}

	public void setGaiqian(String gaiqian) {
		this.gaiqian = gaiqian;
	}

	public String getWuji() {
		return wuji;
	}

	public void setWuji(String wuji) {
		this.wuji = wuji;
	}
	
	
	
}
