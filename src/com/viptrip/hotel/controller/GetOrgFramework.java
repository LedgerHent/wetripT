package com.viptrip.hotel.controller;


import java.util.List;
import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.hotel.HotelClient;
import com.viptrip.hotel.model.Request_GetOrgFramework;
import com.viptrip.hotel.model.Response_GetOrgFramework;
import com.viptrip.hotel.service.impl.GetOrgFrameworkServiceImpl;
import com.viptrip.wetrip.controller.res.Msg;
import com.viptrip.wetrip.model.employees.CompanyInfo;
import etuf.v1_0.common.Common;
import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;



public class GetOrgFramework extends HotelClient<Request_GetOrgFramework,Response_GetOrgFramework>{

	@Override
	protected OutputSimpleResult DataValid(Request_GetOrgFramework req) {
		OutputSimpleResult osr=new OutputSimpleResult();
		if (!Common.IsNullOrEmpty(req.userId+"")){
			if(req!=null){
				if(!Common.IsNullOrEmpty(req.type+"")){
					if(!Common.IsNullOrEmpty(req.orgId+"")){	
						osr.code=Constant.Code_Succeed;											
					}else osr.result="企业id为空";
				}else osr.result="查询类别为空";
			}else osr.result="请求数据为空";
		}else osr.result="用户id为空";
		
	
		
		return osr;
	}

	@Override
	protected OutputResult<Response_GetOrgFramework, String> DoBusiness(
			Request_GetOrgFramework req) {
	

	
	
		//获取service层实例
		GetOrgFrameworkServiceImpl service = ApplicationContextHelper.getInstance().getBean(GetOrgFrameworkServiceImpl.class);
		
		OutputResult<Response_GetOrgFramework, String> or=new OutputResult<Response_GetOrgFramework, String>();	
		Response_GetOrgFramework gef=new Response_GetOrgFramework();
		
		CompanyInfo topTacOrg=null;
		List<CompanyInfo> child=null;
		CompanyInfo info=service.getAllOrg(req, topTacOrg, child);//获取
		//child = info.child;
		
		gef.data=info;
		
		if(gef.status==0){
			or.code=Msg.Success.getCode();
		}else or.result=" status Error";
		or.setResultObj(gef);
		
			
		return or;
		
	}

}
