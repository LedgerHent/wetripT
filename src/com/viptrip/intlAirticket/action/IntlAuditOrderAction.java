package com.viptrip.intlAirticket.action;

import com.viptrip.base.action.BaseAction;
import com.viptrip.base.common.MyEnum;
import com.viptrip.common.controller.GetApprovalDetail;
import com.viptrip.common.controller.ListApprovalByTravller;
import com.viptrip.common.model.Request_GetApprovalDetail;
import com.viptrip.common.model.Request_ListApprovalByTravller;
import com.viptrip.common.model.Response_GetApprovalDetail;
import com.viptrip.common.model.Response_ListApprovalByTravller;
import com.viptrip.intlAirticket.controller.IntlAuditOrder;
import com.viptrip.intlAirticket.controller.UpdateApproveProcess;
import com.viptrip.intlAirticket.model.Request_IntlAuditOrder;
import com.viptrip.intlAirticket.model.Request_UpdateApproveProcess;
import com.viptrip.intlAirticket.model.Response_IntlAuditOrder;
import com.viptrip.intlAirticket.model.Response_UpdateApproveProcess;
import com.viptrip.intlAirticket.model.flightModels.Req_Data_IntlAuditOrder;
import com.viptrip.wetrip.model.policy.ApproveTravller;
import com.viptrip.wetrip.model.policy.Res_ApprovalByTravller;
import com.viptrip.wetrip.model.policy.Res_ApprovalsDetail;
import etuf.v1_0.model.base.output.OutputResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/intl")
public class IntlAuditOrderAction extends BaseAction{

	private static Logger logger = LoggerFactory.getLogger(IntlAuditOrderAction.class);
	
	@RequestMapping("/page.act")
	public String topage(){
		
		return "intlflight/orderDetails";
	}
	@RequestMapping("/audit.act")
	@ResponseBody
	public void auditor(int orderId,int type,String memo){
		Request_IntlAuditOrder req=new Request_IntlAuditOrder();
		IntlAuditOrder intlaudit=new IntlAuditOrder();
		req.userId=getUserId();
		if("".equals(memo)||memo==null){
			memo="";
		}
		req.setData(new Req_Data_IntlAuditOrder(orderId, type, memo));
		OutputResult<Response_IntlAuditOrder, String> result=intlaudit.ClientRequest(req, Response_IntlAuditOrder.class);
		int status =0;
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
			logger.debug("国际机票审核status获取失败！");
		}
	}


	/**
	 * 查询审批政策列表
	 * @param peopleIds
	 * @param costIds
	 * @param approveType
     * @return
     */
	@RequestMapping("/showApprovals")
	@ResponseBody
	public  List showApprovals(String peopleIds,String costIds,String approveType){

		Request_ListApprovalByTravller  req = new Request_ListApprovalByTravller();
		req.approvalType=Integer.valueOf(approveType);
		req.businessType=MyEnum.BussinessType.国内机票.getValue();
		String[] split = peopleIds.split(",");
		String[] split1 = costIds.split(",");
		ArrayList<ApproveTravller> objects = new ArrayList<ApproveTravller>();
		for(int i=0;i<split.length;i++){
			ApproveTravller travller = new ApproveTravller();
			Integer [] in={Integer.valueOf(split1[i])};
			travller.id=Integer.valueOf(split[i]);
			travller.costIds=in;
			objects.add(travller);
		}

		req.travellers=objects;

		ListApprovalByTravller tb = new ListApprovalByTravller();
		OutputResult<Response_ListApprovalByTravller, String> res = tb.ClientRequest(req, Response_ListApprovalByTravller.class);
		List<Res_ApprovalByTravller> data = res.getResultObj().data;
//		GetRulesByTravller grb=new GetRulesByTravller();
//		OutputResult<Response_GetRulesByTravller, String> or = grb.ClientRequest(rg, Response_GetRulesByTravller.class);

//		Response_GetRulesByTravller resultObj = or.getResultObj();
		return data;
	}

	/**
	 * 查询审批政策详情
	 * @param appId
	 * @return
     */
	@RequestMapping("/getDetail")
	@ResponseBody
	public Res_ApprovalsDetail getApprovalDetail(String appId){

		Request_GetApprovalDetail id = new Request_GetApprovalDetail();
		id.approvalId=Integer.valueOf(appId);

		GetApprovalDetail res = new GetApprovalDetail();
		OutputResult<Response_GetApprovalDetail, String> result = res.ClientRequest(id, Response_GetApprovalDetail.class);
		Response_GetApprovalDetail resultObj = result.getResultObj();
		return resultObj.data;
	}

	@RequestMapping("/updatProcess.act")
	public String updateApprove(Request_UpdateApproveProcess req){
		try {
			UpdateApproveProcess updatepro=new UpdateApproveProcess();
			req.userId=getUserId();
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
