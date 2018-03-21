package com.viptrip.common.model;

import java.util.List;

import com.viptrip.wetrip.model.base.Response_Base;

@SuppressWarnings("serial")
public class Response_GetCabinCodeByType extends Response_Base {
	public List<ClassCodeInfoModel> data;
}
