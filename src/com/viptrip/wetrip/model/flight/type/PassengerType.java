package com.viptrip.wetrip.model.flight.type;
/**
 * 乘客类型枚举
 * @author selfwhisper
 *
 */
public enum PassengerType {
	
	adult(1,"成人"),child(2,"儿童");
	
	private Integer code;
	private String desc;
	
	private PassengerType(int code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public static String getMsg(int code){
		for(PassengerType type:PassengerType.values()){
			if(type.getCode()==code){
				return type.getDesc();
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
