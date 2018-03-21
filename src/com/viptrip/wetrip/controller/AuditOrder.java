package com.viptrip.wetrip.controller;



import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.wetrip.TicketClient;
import com.viptrip.wetrip.controller.res.Msg;
import com.viptrip.wetrip.model.Request_AuditOrder;
import com.viptrip.wetrip.model.Response_AuditOrder;
import com.viptrip.wetrip.service.AuditOrderService;
import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class AuditOrder extends TicketClient<Request_AuditOrder, Response_AuditOrder>{
					
					
	@Override
	protected OutputSimpleResult DataValid(Request_AuditOrder req) {
		OutputSimpleResult osr = new OutputSimpleResult();
		if(null==req || null == req.data){
			osr.result = Msg.IncompletePara.getInfo();
		}else{
			osr.code = Msg.Success.getCode();
			osr.result = Msg.Success.getInfo();
		}
		return osr;
		
		
	}

	@Override
	protected OutputResult<Response_AuditOrder, String> DoBusiness(Request_AuditOrder req) {
		OutputResult<Response_AuditOrder, String> or = new OutputResult<Response_AuditOrder, String>();
		or.code = Constant.Code_Succeed;//成功标志
		AuditOrderService aos = ApplicationContextHelper.getInstance().getBean(AuditOrderService.class);
		Integer status = aos.AuditOrder(req);
		Response_AuditOrder resultObj = new Response_AuditOrder();
		
		String type = (String) req.data.get("type");
		if("3".equals(type)){
			resultObj.setMy_status(status);
		}else{
			resultObj.status=status;
		}
		
		
		
		or.setResultObj(resultObj);
		return or;
	}

}
