package com.viptrip.wetrip.model.userlogin;


public class AvoidLoginH5Model {
	public int pid;//平台id
	public String authcode;//
	public String uid  ;//    用户平台的id，唯一主键
	public String name;
	public String mobile;
	public String email;
	public int idType  ; //证件类型枚举（选填）数字，1=二代身份证，2=护照
	public String idNum  ; //证件号码（选填）
}
