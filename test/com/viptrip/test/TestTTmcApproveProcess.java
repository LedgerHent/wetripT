package com.viptrip.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.viptrip.common.entity.TTmcApproveProcess;
import com.viptrip.common.entity.TTmcApproveTraveller;
import com.viptrip.common.model.ApproveAuditModel;
import com.viptrip.common.model.ApproveAuditorModel;
import com.viptrip.common.model.ApproveProcessTravellerMode;
import com.viptrip.common.model.BookerInfo;
import com.viptrip.common.model.Request_CreateTTmcApproveProcess;
import com.viptrip.common.model.Request_DeleteApprovalProcess;
import com.viptrip.common.model.Request_GetApprovalProcess;
import com.viptrip.common.model.Request_ListApprovalPending;
import com.viptrip.common.model.Request_UpdateApproveProcess;
import com.viptrip.common.model.Response_GetApprovalProcess;
import com.viptrip.common.model.Response_ListApprovalPending;
import com.viptrip.common.model.TTmcApproveProcessInfo;
import com.viptrip.common.service.TTmcApproveProcessService;
import com.viptrip.common.vo.TTmcApproveProcessVO;
import com.viptrip.hotel.service.IComService;


@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试  
@ContextConfiguration({"classpath:conf/spring/spring-context.xml"}) //加载配置文件
public class TestTTmcApproveProcess {
	@Autowired
	private TTmcApproveProcessService tTmcApproveProcessService;
	@Autowired
	private IComService iComService;
	
	//@Test
	public void testTTmcApproveProcessService() {
		Request_CreateTTmcApproveProcess request_CreateTTmcApproveProcess=new Request_CreateTTmcApproveProcess();
		request_CreateTTmcApproveProcess.approvalId=7561l;
		TTmcApproveProcessInfo tTmcApproveProcess=new TTmcApproveProcessInfo();
		BookerInfo bookerInfo=new BookerInfo();
		tTmcApproveProcess.booker=bookerInfo;
		
		tTmcApproveProcess.booker.email="yuxingfei@caissa.com.cn";
		tTmcApproveProcess.booker.id=3l;
		tTmcApproveProcess.booker.name="yuxingfei";
		tTmcApproveProcess.orderid=-1;
		tTmcApproveProcess.orderno="DO171109HT005292";
		tTmcApproveProcess.approvalType=1;
		
		
		List<ApproveProcessTravellerMode> traveller=new ArrayList<ApproveProcessTravellerMode>();
		ApproveProcessTravellerMode tTmcApproveTraveller1=new ApproveProcessTravellerMode();
		tTmcApproveTraveller1.setId(20l);
		tTmcApproveTraveller1.setName("zhangsan");
		tTmcApproveTraveller1.setEmail("zhangsan@caissa.com.cn");
		tTmcApproveTraveller1.setMobile("13621260227");
		
		ApproveProcessTravellerMode tTmcApproveTraveller2=new ApproveProcessTravellerMode();
		tTmcApproveTraveller2.setId(20l);
		tTmcApproveTraveller2.setName("lisi");
		tTmcApproveTraveller2.setEmail("lisi@caissa.com.cn");
		tTmcApproveTraveller2.setMobile("13621260228");
		
		ApproveProcessTravellerMode tTmcApproveTraveller3=new ApproveProcessTravellerMode();
		tTmcApproveTraveller3.setId(20l);
		tTmcApproveTraveller3.setName("wangwu");
		tTmcApproveTraveller3.setEmail("wangwu@caissa.com.cn");
		tTmcApproveTraveller3.setMobile("13621260229");
		traveller.add(tTmcApproveTraveller1);
		traveller.add(tTmcApproveTraveller2);
		traveller.add(tTmcApproveTraveller3);
		tTmcApproveProcess.traveller=traveller;
		request_CreateTTmcApproveProcess.data=tTmcApproveProcess;
		String result=null;
		try {			
			result=tTmcApproveProcessService.saveTTmcApproveProcess(request_CreateTTmcApproveProcess);
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}		
		System.out.println("result:"+result);		
	}
	
	@Test
	public void testTTmcApproveProcessServiceJson() {
		String jsonStr="{ \"approvalId\": 7561, \"data\": {  \"amount\": 467.0,  \"bookTime\": \"2017-11-27\",  \"booker\": {   \"email\": \"cesh01@viptrip365.com\","
				+ "\"id\": 294274,   \"mobile\":\"13621260225\",   \"name\": \"管理审核员（测试）\"  },  \"businessType\": 3,  \"orgId\": 5660,  \"orgName\": \"测试企业\",  \"payType\": 1,"
				+ "  \"productDetail\": \"3人标准房\",  \"productName\": \"北京友谊酒店\",  \"status\": \"待审核\",  \"travelType\": 1,  \"traveller\": [{"
				+ "   \"email\": \"15235177981@qq.com\",   \"id\": 305347,  \"mobile\":\"13621260225\",   \"name\": \"关羽\"  }] }, "
				+ "\"method\": \"createTTmcApproveProcess\", \"versionId\": 1.0}";
		Request_CreateTTmcApproveProcess request_CreateTTmcApproveProcess=new Request_CreateTTmcApproveProcess();
		ObjectMapper mapper = new ObjectMapper();
		try {
			
			request_CreateTTmcApproveProcess = mapper.readValue(jsonStr, request_CreateTTmcApproveProcess.getClass());
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String result=null;
		try {			
			result=tTmcApproveProcessService.saveTTmcApproveProcess(request_CreateTTmcApproveProcess);
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}		
		System.out.println("result:"+result);	
		
	}
	
	@Test
	public void testListApprovalPending() {
		Request_ListApprovalPending request_ListApprovalPending=new Request_ListApprovalPending();
		request_ListApprovalPending.businessType=3;
		request_ListApprovalPending.page.index=1;
		request_ListApprovalPending.page.size=2;
		request_ListApprovalPending.auditorId=305347l;
		Response_ListApprovalPending responseApproval=tTmcApproveProcessService.listApprovalPending(request_ListApprovalPending);
		System.out.println("result:"+com.alibaba.fastjson.JSONObject.toJSONString(responseApproval));
	}
	
	@Test
	public void testGetApprovalProcess() {
		Request_GetApprovalProcess request_GetApprovalProcess=new Request_GetApprovalProcess();
		request_GetApprovalProcess.orderno="DO171109HT005298";
		Response_GetApprovalProcess responseApproval=tTmcApproveProcessService.getApprovalProcess(request_GetApprovalProcess);
		System.out.println("result:"+com.alibaba.fastjson.JSONObject.toJSONString(responseApproval));		
	}
	
	@Test
	public void testUpdateApprovalProcess() {
		Request_UpdateApproveProcess updateApproveProcess=new Request_UpdateApproveProcess();
		updateApproveProcess.orderno="DO171109HT005298";
		updateApproveProcess.businessType=3;
		ApproveAuditModel approveAuditModel=new ApproveAuditModel();
		approveAuditModel.id=305357l;
		approveAuditModel.name="林冲";
		approveAuditModel.email="15235177981@qq.com";
		approveAuditModel.mobile="13633237834";
		approveAuditModel.state=11;
		approveAuditModel.type=1;
		approveAuditModel.memo="审核通过";
		ApproveAuditorModel operator=new ApproveAuditorModel();
		operator.id=305357l;
		operator.name="林冲";
		operator.email="15235177981@qq.com";
		operator.mobile="13633237834";
		approveAuditModel.operator=operator;
		updateApproveProcess.audit=approveAuditModel;
		String result=tTmcApproveProcessService.updateApproveProcess(updateApproveProcess);
		System.out.println("result:"+result);		
	}
	
	@Test
	public void testDeleteApprovalProcess() {
		Request_DeleteApprovalProcess updateApproveProcess=new Request_DeleteApprovalProcess();
		updateApproveProcess.orderno="HT171204001357";
		updateApproveProcess.businessType=3;
		updateApproveProcess.memo="";
		;
		String result=tTmcApproveProcessService.deleteApprovalProcess(updateApproveProcess);
		System.out.println("result:"+result);		
	}
	
	/*@Test
	public void testTTmcApproveProcessService() {
		TTmcApproveProcess tTmcApproveProcess=new TTmcApproveProcess();
		Request_GetApprovalDetail request_GetApprovalDetail=new Request_GetApprovalDetail();
		request_GetApprovalDetail.approvalId=7561;
		Res_ApprovalsDetail res_ApprovalsDetail=iComService.getApprovalDetail(request_GetApprovalDetail);
		System.out.println("result:"+com.alibaba.fastjson.JSONObject.toJSONString(res_ApprovalsDetail));
		
	}*/

}
