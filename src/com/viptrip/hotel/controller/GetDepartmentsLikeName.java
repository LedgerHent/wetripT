package com.viptrip.hotel.controller;


import java.util.List;
import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.hotel.HotelClient;
import com.viptrip.hotel.model.Request_GetDepartmentsLikeName;
import com.viptrip.hotel.model.Response_GetDepartmentsLikeName;
import com.viptrip.hotel.model.getDepartmentsLikeName.CompanyInfoGDLN;
import com.viptrip.hotel.service.impl.GetDepartmentsLikeNameServiceImpl;
import com.viptrip.wetrip.controller.res.Msg;
import com.viptrip.wetrip.model.employees.CompanyInfo;
import etuf.v1_0.common.Common;
import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class GetDepartmentsLikeName extends HotelClient<Request_GetDepartmentsLikeName,Response_GetDepartmentsLikeName> {

	@Override
	protected OutputSimpleResult DataValid(Request_GetDepartmentsLikeName arg0) {
		OutputSimpleResult osr=new OutputSimpleResult();
		
		if (!Common.IsNullOrEmpty(arg0.userId+"")){
			if(arg0!=null){
				if(!Common.IsNullOrEmpty(arg0.orgId+"")){
					if(!Common.IsNullOrEmpty(arg0.nameKey)){
						if(!Common.IsNullOrEmpty(arg0.page.index+"")&&arg0.page.index>=1){
							if(!Common.IsNullOrEmpty(arg0.page.size+"")&&arg0.page.size>=0){						
								osr.code=Constant.Code_Succeed;	
						    }else osr.result="页码尺寸不正确";
					    }else osr.result="页码索引不正确";
					}else osr.result="部门名称为空";
				}else osr.result="企业id为空";
			}else osr.result="请求数据为空";
		}else osr.result="用户id为空";
		
		return osr;
	}

	@Override
	protected OutputResult<Response_GetDepartmentsLikeName, String> DoBusiness(
			Request_GetDepartmentsLikeName arg0) {
			//获取service层实例
				GetDepartmentsLikeNameServiceImpl service = ApplicationContextHelper.getInstance().getBean(GetDepartmentsLikeNameServiceImpl.class);
				
				OutputResult<Response_GetDepartmentsLikeName, String> or=new OutputResult<Response_GetDepartmentsLikeName, String>();	
				Response_GetDepartmentsLikeName gef=new Response_GetDepartmentsLikeName();
				
				CompanyInfoGDLN topTacOrg=null;
				List<CompanyInfoGDLN> childs=null;
				CompanyInfoGDLN info=service.getAllOrg(arg0.orgId,arg0,topTacOrg, childs);//获取
				//childs = info.child;							
				gef.data=info;
				
				if(gef.status==0){
					or.code=Msg.Success.getCode();
				}else or.result=" status Error";
				or.setResultObj(gef);
				
					
				return or;
	}

}
