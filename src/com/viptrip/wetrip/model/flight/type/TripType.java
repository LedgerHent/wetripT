package com.viptrip.wetrip.model.flight.type;

/**
 * 航程类型
 * @author selfwhisper
 *
 */
public enum TripType {
	
	OneWay(1,"单程"),ComeAndGo(2,"往返"),Connecting(3,"联程");
	
	private Integer code;
	private String desc;
	
	private TripType(int code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public static String getMsg(int code){
		for(TripType type:TripType.values()){
			if(type.getCode()==code){
				return type.getDesc();
			}
		}
		return null;
	}
	
	public static TripType getTripType(Integer code){
		for(TripType type:TripType.values()){
			if(type.getCode()==code){
				return type;
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	

}
