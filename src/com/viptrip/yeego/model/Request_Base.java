package com.viptrip.yeego.model;

import java.io.Serializable;


public class Request_Base implements Serializable{

	/*
	 * 接口版本号，现在固定为1.0，请勿修改
	 * */
	public String Version="1.0";
	
	/*
	 * 返回结果数据格式指定
	 * 易购接口返回结果部分只能是xml，部分只能是json，部分都支持
	 * 请求的时候，务必把该参数传对了
	 * 0 -- 默认值，无效
	 * 1 -- xml
	 * 2 -- json
	 * */
	public int Dataformat=0;
	
}
