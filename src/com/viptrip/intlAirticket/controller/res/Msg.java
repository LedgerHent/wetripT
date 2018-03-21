package com.viptrip.intlAirticket.controller.res;

public enum Msg {
	Success(0,"成功"),IncompletePara(1,"不完整的参数"),Unknown(99,"未知错误");
	
	private Integer code;
	private String info;
	
	private Msg(int code,String info){
		this.code = code;
		this.info = info;
	}
	
	public static String getMsg(int code){
		for(Msg msg:Msg.values()){
			if(msg.getCode()==code){
				return msg.getInfo();
			}
		}
		return null;
	}
	

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	
}
