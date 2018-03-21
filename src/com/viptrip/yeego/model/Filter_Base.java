package com.viptrip.yeego.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name = "filter")
public class Filter_Base implements Serializable {
	/*
	 * 响应数据格式，业务请求时无需关心该参数，由接口统一处理即可
	 * X为返回数据为xml(默认)
	 * J为返回数据为json
	 * */
	public String DataFormat="X";
}
