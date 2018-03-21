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
import com.viptrip.wetrip.controller.GetEnterpriseFramework;
import com.viptrip.wetrip.controller.ListStaff;
import com.viptrip.wetrip.model.Request_GetEnterpriseFramework;
import com.viptrip.wetrip.model.Request_ListStaff;
import com.viptrip.wetrip.model.Response_GetEnterpriseFramework;
import com.viptrip.wetrip.model.Response_ListStaff;
import com.viptrip.wetrip.model.employees.CompanyInfo;
import com.viptrip.wetrip.model.employees.ListEmployee;

import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.v2.base.Response_BaseMsg;

@Controller
@RequestMapping("/org")
public class GetEnterpriseFrameworkAction extends BaseAction{
	
	
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(ListStaffAction.class);

	@RequestMapping("org")
	@ResponseBody
	public AjaxResp staff(PageParam pp) throws Exception{
		GetEnterpriseFramework gf=new GetEnterpriseFramework();
		Request_GetEnterpriseFramework para = new Request_GetEnterpriseFramework();
		para.setUserId(getUserId());
		OutputResult<Response_GetEnterpriseFramework, Response_BaseMsg> result = gf.ClientRequest(para, 
				Response_GetEnterpriseFramework.class,Response_BaseMsg.class);
		 if(result.IsSucceed()){
			Response_GetEnterpriseFramework resultObj = result.getResultObj();
			//List<CompanyInfo> list = resultObj.getData();
			List<CompanyInfo> list=resultObj.getData();
			Pager<CompanyInfo> pager =new Pager<>(pp.getPageNum(), pp.getPageNumShown(), list);
			String resultStr = new Gson().toJson(result.getResultObj());
			System.out.println(resultStr);
			return new AjaxResp(result.code, pager);
		}else{
			return new AjaxResp(result.code, result.getErrorObj(Response_BaseMsg.class));
		}
	 
	}

}
