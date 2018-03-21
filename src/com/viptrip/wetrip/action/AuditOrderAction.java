package com.viptrip.wetrip.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.viptrip.base.action.BaseAction;
import com.viptrip.wetrip.controller.AuditOrder;
import com.viptrip.wetrip.controller.CancelOrder;
import com.viptrip.wetrip.model.Request_AuditOrder;
import com.viptrip.wetrip.model.Request_CancelOrder;
import com.viptrip.wetrip.model.Response_AuditOrder;
import com.viptrip.wetrip.model.Response_CancelOrder;
import etuf.v1_0.model.base.output.OutputResult;

@Controller
@RequestMapping("/auditorder")
public class AuditOrderAction  extends BaseAction{

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(AuditOrderAction.class);

	private AuditOrder ac = new AuditOrder();
	
	private CancelOrder cc = new CancelOrder();
	
	
	
	@RequestMapping("/auditorder")
	@ResponseBody
	public void auditOrder(Request_AuditOrder para,String  orderId,String type,String memo) throws UnsupportedEncodingException{
		
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("orderId",Long.valueOf(orderId));
		m.put("type", type);
		m.put("memo", memo);
		
		para.userId=getUserId();
		
		 //管理审核员(测试)294274
		//审核员(测试)294864
		//para.userId=294864;
		para.data=	JSONObject.fromObject(m);
		
		OutputResult<Response_AuditOrder, String> or = ac.ClientRequest(para, Response_AuditOrder.class);
		
		int status =0;
		if("3".equals(type)){
			status=or.getResultObj().getMy_status();
		}else{
			status=or.getResultObj().status;
		}
		//addAttr("status", status);
		PrintWriter pw;
		try {
			pw = resp.getWriter();
			pw.write(status+"");
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	
	@RequestMapping("/cancelOrder")
	@ResponseBody
	public void cancelOrder(Request_CancelOrder para,String orderId,String memo) throws UnsupportedEncodingException{
		
		para.userId=getUserId();
		//para.userId=userId;
		System.out.println("++++++++++++="+getUserId());
		para.orderId=Long.valueOf(orderId);
		para.memo=memo;
		//para.userId=294864;
		OutputResult<Response_CancelOrder, String> or = cc.ClientRequest(para,  Response_CancelOrder.class);
		
		int status = or.getResultObj().status;
		addAttr("status", status);
		ServletOutputStream pw;
		try {
			pw = resp.getOutputStream();
			pw.write((status+"").getBytes());
			pw.flush();
			pw.close();
		} catch (IOException e) {		
			e.printStackTrace();
		}
	
	}
	
	
	
}
