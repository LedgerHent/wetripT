package com.viptrip.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.viptrip.hotel.service.IComService;
import com.viptrip.wetrip.model.Request_GetApprovalDetail;
import com.viptrip.wetrip.model.Request_ListApprovalByTravller;
import com.viptrip.wetrip.model.policy.ApproveTravller;
import com.viptrip.wetrip.model.policy.Res_ApprovalByTravller;
import com.viptrip.wetrip.model.policy.Res_ApprovalsDetail;



@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试  
@ContextConfiguration({"classpath:conf/spring/spring-context.xml"}) //加载配置文件
public class Test2 {

	@Autowired
	private IComService service;
	
	@org.junit.Test
	public void Test(){
		Request_ListApprovalByTravller travller = new Request_ListApprovalByTravller();
		//travller.method="ListApprovalByTravller";
		List<ApproveTravller> li = new ArrayList<ApproveTravller>();
		travller.approvalType=1;
		travller.businessType=3;
		
		//Res_ApprovalByTravller travller3 = new Res_ApprovalByTravller();
		ApproveTravller travller2 = new ApproveTravller();
		//travller2.orgId=6360;
		travller2.id=305347;
		li.add(travller2);
		travller.travellers=li;
		List<Res_ApprovalByTravller> list = service.listApprovalByTravller(travller);
		System.out.println(list.size()+list.toString());
		Request_GetApprovalDetail request = new Request_GetApprovalDetail();
		request.approvalId=7561;
		Res_ApprovalsDetail detail = service.getApprovalDetail(request);

		System.out.println(detail.toString());
	}
	
}
