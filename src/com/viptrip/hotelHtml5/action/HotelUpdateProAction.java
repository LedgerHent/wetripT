package com.viptrip.hotelHtml5.action;

import java.io.PrintWriter;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.viptrip.base.action.BaseAction;
import com.viptrip.common.controller.UpdateApproveProcess;
import com.viptrip.common.model.ApproveAuditModel;
import com.viptrip.common.model.Request_UpdateApproveProcess;
import com.viptrip.common.model.Response_GetApprovalProcess;
import com.viptrip.common.model.Response_UpdateApproveProcess;
import com.viptrip.hotelHtml5.service.TmcHotelOrderService;
import com.viptrip.hotelHtml5.util.PageUtil;
import com.viptrip.hotelHtml5.vo.tmc.TmcOrderInfoH5;
import com.viptrip.hotelHtml5.vo.tmc.request.Request_ListTmcOrder;
import com.viptrip.hotelHtml5.vo.tmc.response.Response_ListTmcOrder;

import etuf.v1_0.model.base.output.OutputResult;

@Controller
@RequestMapping("/hotelOrder")
public class HotelUpdateProAction extends BaseAction{
	
	@Autowired
	private TmcHotelOrderService tmcHotelOrderService;
	
	@RequestMapping("/updatpro.act")
	public String updateApprove(Request_UpdateApproveProcess req){
		try {
			UpdateApproveProcess updatepro=new UpdateApproveProcess();
			OutputResult<Response_UpdateApproveProcess, String> result = updatepro.ClientRequest(req, Response_UpdateApproveProcess.class);
			System.out.println("result@@@@=======updatpro"+result.getResultObj());
			PrintWriter pw;
			int status;
			status=result.getResultObj().status;
				pw = resp.getWriter();
				pw.write(status+"");
				pw.flush();
				pw.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null; 
	}
	
}
