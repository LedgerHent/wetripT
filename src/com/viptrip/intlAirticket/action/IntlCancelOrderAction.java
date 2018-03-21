package com.viptrip.intlAirticket.action;

import java.io.IOException;
import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.viptrip.base.action.BaseAction;
import com.viptrip.intlAirticket.controller.CancelIntlOrder;
import com.viptrip.intlAirticket.model.Request_CancelIntlOrder;
import com.viptrip.intlAirticket.model.Response_CancelIntlOrder;

import etuf.v1_0.model.base.output.OutputResult;

@Controller
@RequestMapping("/intl")
public class IntlCancelOrderAction extends BaseAction{
	
	private static Logger logger = LoggerFactory.getLogger(IntlCancelOrderAction.class);
	
	@RequestMapping("/cancelOrder.act")
	@ResponseBody
	public void cancleOrder(int orderId,String memo){
		CancelIntlOrder cancel=new CancelIntlOrder();
		Request_CancelIntlOrder req=new Request_CancelIntlOrder();
		req.userId=getUserId();
		req.setOrderId(orderId);
		req.setMemo(memo);
		OutputResult<Response_CancelIntlOrder, String> result=cancel.ClientRequest(req, Response_CancelIntlOrder.class);
		int status = 0;
		if(null!=result){
			status=result.getResultObj().status;
		}
		PrintWriter pw;
		try {
			pw = resp.getWriter();
			pw.write(""+status);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
			logger.debug("国际机票取消订单status获取失败！");
		}
	}
	
}
