package com.viptrip.hotel.controller;


import java.util.List;
import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.hotel.HotelClient;
import com.viptrip.hotel.model.Request_GetDepartments;
import com.viptrip.hotel.model.Response_GetDepartments;
import com.viptrip.hotel.service.impl.GetDepartmentsServiceImpl;
import com.viptrip.wetrip.controller.res.Msg;
import com.viptrip.wetrip.model.employees.CompanyInfo;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;


public class GetDepartments extends HotelClient<Request_GetDepartments,Response_GetDepartments>{

	@Override
	protected OutputSimpleResult DataValid(Request_GetDepartments req) {
		OutputSimpleResult osr=new OutputSimpleResult();
		
		
		if((req.userId==null)||(req.getOrgId()==null)){
			osr.code = 1;
			osr.result = "参数不完整";
		}else{
			osr.code = Msg.Success.getCode();
			osr.result = Msg.Success.getInfo();
		}
		
		
		
		return osr;
	}

	@Override
	protected OutputResult<Response_GetDepartments, String> DoBusiness(
			Request_GetDepartments req) {
		
		//获取service层实例
		GetDepartmentsServiceImpl service = ApplicationContextHelper.getInstance().getBean(GetDepartmentsServiceImpl.class);
		
		OutputResult<Response_GetDepartments, String> or=new OutputResult<Response_GetDepartments, String>();	
		Response_GetDepartments gef=new Response_GetDepartments();
		
		CompanyInfo topTacOrg=null;
		List<CompanyInfo> childs=null;
		CompanyInfo info=service.getAllOrg(req.getOrgId(), topTacOrg, childs);//获取
		
		
		//childs = info.child;
		//String resultStr = new Gson().toJson(childs);
				
		gef.data=info;
		System.out.println("***************coll=====" + gef.data.toString());
		if(gef.status==0){
			or.code=Msg.Success.getCode();
		}else or.result=" status Error";
		or.setResultObj(gef);
		
			
		return or;
	}

}
