package com.viptrip.wetrip.controller;

import java.util.List;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.hotel.service.IComService;
import com.viptrip.wetrip.TicketClient;
import com.viptrip.wetrip.model.Request_ListApprovalByTravller;
import com.viptrip.wetrip.model.Response_ListApprovalByTravller;
import com.viptrip.wetrip.model.policy.Res_ApprovalByTravller;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class ListApprovalByTravller extends TicketClient<Request_ListApprovalByTravller, Response_ListApprovalByTravller> {

	@Override
	protected OutputSimpleResult DataValid(Request_ListApprovalByTravller arg0) {
		OutputSimpleResult osr = new OutputSimpleResult();
		if(!"".equals(arg0.businessType) && arg0.businessType!=null){
			if(!"".equals(arg0.approvalType) && arg0.approvalType!=null){
				if(arg0.travellers.size()>0 && arg0.travellers!=null){
					osr.code=0;
				}else{
					osr.code=1;
					osr.result="出行人信息为空";
				}
			}else{
				osr.code=1;
				osr.result="审批类型为空";
			}
		}else{
			osr.code=1;
			osr.result="业务类型为空";
		}
		return osr;
	}

	@Override
	protected OutputResult<Response_ListApprovalByTravller, String> DoBusiness(Request_ListApprovalByTravller arg0) {
		OutputResult<Response_ListApprovalByTravller, String> result = new OutputResult<Response_ListApprovalByTravller, String>();
		Response_ListApprovalByTravller res = new Response_ListApprovalByTravller();
		IComService service = ApplicationContextHelper.getInstance().getBean(IComService.class);
		List<Res_ApprovalByTravller> approveList = service.listApprovalByTravller(arg0);
		res.data=approveList;
		result.code=Constant.Code_Succeed;
		result.setResultObj(res);
		
		return result;
	}

}
