package com.viptrip.wetrip.action;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.viptrip.base.action.AjaxResp;
import com.viptrip.base.action.BaseAction;
import com.viptrip.util.PageParam;
import com.viptrip.util.Pager;
import com.viptrip.wetrip.controller.ListAuditor;
import com.viptrip.wetrip.model.Request_ListAuditor;
import com.viptrip.wetrip.model.Response_ListAuditor;
import com.viptrip.wetrip.model.employees.Auditor;
import com.viptrip.wetrip.util.JsonUtil;
import com.viptrip.wetrip.vo.BookOrderInfo;

import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.v2.base.Response_BaseMsg;

@Controller
@RequestMapping("/auditor")
public class ListAuditorAction extends BaseAction{
	
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(ListStaffAction.class);
	
	private static final String auditorList ="auditorList/auditor";//企业审核人列表

	@RequestMapping("/auditor.act")
	public String auditor(PageParam pp, BookOrderInfo bf) throws Exception{
		
		ListAuditor ls=new ListAuditor();
		Request_ListAuditor para = new Request_ListAuditor();
		para.setUserId(getUserId());
		OutputResult<Response_ListAuditor, Response_BaseMsg> result = ls.ClientRequest(para, 
				Response_ListAuditor.class,Response_BaseMsg.class);
		AjaxResp obj = null;
		if(result.IsSucceed()){
			Response_ListAuditor resultObj = result.getResultObj();
			List<Auditor> list = resultObj.getData();
			Pager<Auditor> pager =new Pager<>(pp.getPageNum(), pp.getPageNumShown(), list);
			obj=success(pager);
			String resultStr = new Gson().toJson(result.getResultObj());
			System.out.println(resultStr);
			addAttr("result", obj);
			bf.setJumpCount(bf.getJumpCount()+1);
			String json=JsonUtil.ObjectToJsonStr(bf);
			addAttr("bookinfo",json);
			addAttr("skipType",bf.getSkipType());
			
			return auditorList;
		}else{
			obj= fail(result.getErrorObj(Response_BaseMsg.class));
			return null;
		}
	}
	

}
