package com.viptrip.hotel.controller;

import com.viptrip.hotel.HotelClient;
import com.viptrip.hotel.model.Request_Demo;
import com.viptrip.hotel.model.Response_Demo;

import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class Demo extends HotelClient<Request_Demo,Response_Demo> {

	@Override
	protected OutputSimpleResult DataValid(Request_Demo arg0) {
		OutputSimpleResult osr=new OutputSimpleResult();
		if(arg0.data!=null && !etuf.v1_0.common.Common.IsNullOrEmpty(arg0.data.demoStr)
				&& arg0.data.demoInt>=0 && arg0.data.demoList!=null && arg0.data.demoList.size()>0)
		{
			osr.code=etuf.v1_0.common.Constant.Code_Succeed;
		}else{
			osr.result="无效的请求参数。示例接口中字符串不能为null或者empty，整数不小于0，数组元素个数大于0。";
			if(!etuf.v1_0.common.Common.IsNullOrEmpty(arg0.data.demoStr)){
				osr.result+="demoStr="+arg0.data.demoStr;
			}
		}
		
		Demo d=new Demo();
		d.GetConfig();
		OutputSimpleResult encryptParams = d.EncryptParams(d.GetConfig(), "");
		OutputResult<Response_Demo, String> clientRequest = d.ClientRequest(null, null);
		
		return osr;
	}

	@Override
	protected OutputResult<Response_Demo, String> DoBusiness(Request_Demo arg0) {
		OutputResult<Response_Demo, String> or=new OutputResult<>();
		Response_Demo data=new Response_Demo();
		or.setResultObj(data);
		or.code=etuf.v1_0.common.Constant.Code_Succeed;
		return or;
	}

}
