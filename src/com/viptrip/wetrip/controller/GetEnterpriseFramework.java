package com.viptrip.wetrip.controller;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.viptrip.base.cache.manager.RedisCacheManager;
import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.wetrip.TicketClient;
import com.viptrip.wetrip.controller.res.Msg;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.model.Request_GetEnterpriseFramework;
import com.viptrip.wetrip.model.Response_GetEnterpriseFramework;
import com.viptrip.wetrip.model.Response_ListAuditor;
import com.viptrip.wetrip.model.base.Response_IdAndName;
import com.viptrip.wetrip.model.employees.CompanyInfo;
import com.viptrip.wetrip.service.impl.GetEnterpriseFrameworkServiceImpl;
import com.viptrip.wetrip.service.impl.ListAuditorServiceImpl;
import com.viptrip.wetrip.service.impl.ListStaffServiceImpl;

import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class GetEnterpriseFramework extends TicketClient<Request_GetEnterpriseFramework,Response_GetEnterpriseFramework>{

	@Override
	protected OutputSimpleResult DataValid(Request_GetEnterpriseFramework rgf) {
		// TODO 验证
		OutputSimpleResult osr = new OutputSimpleResult();
		if(rgf!=null){
			osr.code = Msg.Success.getCode();
			osr.result = Msg.Success.getInfo();
		}else{
			osr.result = Msg.IncompletePara.getInfo();
		}
		return osr;
	}

	@Override
	protected OutputResult<Response_GetEnterpriseFramework, String> DoBusiness(
			Request_GetEnterpriseFramework regf) {
		// TODO 业务逻辑
		
		/*List<CompanyInfo> cl = new ArrayList<CompanyInfo>();
		
		String key = new Gson().toJson(regf);
		GetEnterpriseFrameworkServiceImpl service = ApplicationContextHelper.getInstance().getBean(GetEnterpriseFrameworkServiceImpl.class);
		CompanyInfo topTacOrg=null;
		List<CompanyInfo> childs=null;
		CompanyInfo info=null;
		if(RedisCacheManager.exists(key)){//读取缓存
			TypeToken<List<CompanyInfo>> typeToken = new TypeToken<List<CompanyInfo>>(){};
			cl = (List<CompanyInfo>) RedisCacheManager.get(key, typeToken.getClass());
		}else{//读库
			//获取service层实例
			Long id=regf.getUserId();
			info=service.getAllOrg(id.intValue(), topTacOrg, childs);//获取
			cl.add(info);
			if(null!=cl){
				RedisCacheManager.save(key, cl);//写入缓存
			}
		}*/
		
	//获取service层实例
	GetEnterpriseFrameworkServiceImpl service = ApplicationContextHelper.getInstance().getBean(GetEnterpriseFrameworkServiceImpl.class);
	
	OutputResult<Response_GetEnterpriseFramework, String> or=new OutputResult<Response_GetEnterpriseFramework, String>();	
	Response_GetEnterpriseFramework gef=new Response_GetEnterpriseFramework();
	Long orgid=regf.getUserId();
	CompanyInfo topTacOrg=null;
	List<CompanyInfo> childs=null;
	CompanyInfo info=service.getAllOrg(orgid.intValue(), topTacOrg, childs);//获取
	childs=new ArrayList<CompanyInfo>();
	childs.add(info);
	gef.setData(childs);
	System.out.println("***************coll=====" + gef.getData().toString());
	if(gef.status==0){
		or.code=Msg.Success.getCode();
	}else or.result=" status Error";
	or.setResultObj(gef);
	return or;
	}

	

}
