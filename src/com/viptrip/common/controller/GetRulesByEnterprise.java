package com.viptrip.common.controller;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.common.CommonClient;
import com.viptrip.common.model.Request_GetRulesByEnterprise;
import com.viptrip.common.model.Response_GetRulesByEnterprise;
import com.viptrip.hotel.service.IComService;
import com.viptrip.wetrip.model.policy.PolicyByenterprise;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class GetRulesByEnterprise extends CommonClient<Request_GetRulesByEnterprise, Response_GetRulesByEnterprise>{

	@Override
	protected OutputSimpleResult DataValid(Request_GetRulesByEnterprise arg) {
		OutputSimpleResult osr = new OutputSimpleResult();
		//--------------------测试----------------------------
		if(arg.businessTypes != null&&arg.businessTypes.size() > 0 ){
			if(arg.enterpriseId != null ){
				osr.code=etuf.v1_0.common.Constant.Code_Succeed;
			}else{
				osr.code = 1;
				osr.result = "企业id为空";
			}
		}else{
			osr.code = 1;
			osr.result = "业务类型为空";
		}
		System.out.print(osr.IsSucceed());
		return osr;
	}

	@Override
	protected OutputResult<Response_GetRulesByEnterprise, String> DoBusiness(
			Request_GetRulesByEnterprise arg) {
		OutputResult<Response_GetRulesByEnterprise, String> or=new OutputResult<Response_GetRulesByEnterprise, String>();
		Response_GetRulesByEnterprise obj = new Response_GetRulesByEnterprise();
		IComService service = ApplicationContextHelper.getInstance().getBean(IComService.class);
		PolicyByenterprise data = service.getPolicyByEnterprise(arg);
		obj.data = data;
		obj.status=etuf.v1_0.common.Constant.Code_Succeed;
		or.setResultObj(obj);
		or.code = Constant.Code_Succeed;
		return or;
	}

}
