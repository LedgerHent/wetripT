package com.viptrip.wetrip.action;

import java.util.List;

import javax.servlet.http.HttpServletResponse;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.viptrip.base.action.AjaxResp;
import com.viptrip.base.action.BaseAction;
import com.viptrip.util.PageParam;
import com.viptrip.util.Pager;
import com.viptrip.wetrip.controller.ListStaff;
import com.viptrip.wetrip.model.Request_ListStaff;
import com.viptrip.wetrip.model.Response_ListStaff;
import com.viptrip.wetrip.model.employees.ListEmployee;
import com.viptrip.wetrip.util.JsonUtil;
import com.viptrip.wetrip.vo.BookOrderInfo;
import com.viptrip.wetrip.vo.FlightListFilterParam;

import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.v2.base.Response_BaseMsg;



@Controller
@RequestMapping("/ppp")
public class ListStaffAction extends BaseAction{
	
	
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(ListStaffAction.class);

	private static final String staffList ="public/addPassengers";//企业员工列表页
	private static final String passengersList ="public/selectPassengers";//企业员工列表页
	private static final String toExcessReason2 = "public/excessReason";//跳转页面
	@RequestMapping("/staff.act")
	public String staff(PageParam pp, BookOrderInfo bf) throws Exception{
	    long time1=System.currentTimeMillis();
		ListStaff ls=new ListStaff();
		Request_ListStaff para = new Request_ListStaff();
		para.setUserId(getUserId());
		OutputResult<Response_ListStaff, Response_BaseMsg> result = ls.ClientRequest(para,
				Response_ListStaff.class,Response_BaseMsg.class);
		AjaxResp obj = null;
		long time3=System.currentTimeMillis();
		System.out.println(time3-time1);
		if(result.IsSucceed()){
			Response_ListStaff resultObj = result.getResultObj();
			List<ListEmployee> list = resultObj.getData();
			Pager<ListEmployee> pager =new Pager<>(pp.getPageNum(), pp.getPageNumShown(), list);
			obj = success(pager);
			String resultStr = new Gson().toJson(result.getResultObj());
			System.out.println(resultStr);
			addAttr("result", obj);
			
			if(bf!=null&&bf.getMapKey()!=null&&!"".equals(bf.getMapKey())){
				bf.setJumpCount((bf.getJumpCount()==null?1:bf.getJumpCount())+1);
				String json=JsonUtil.ObjectToJsonStr(bf);
				addAttr("bookinfo",json);
				addAttr("skipType",bf.getSkipType());
				addAttr("excessinfoStr",bf.getExcessinfo());
//				addAttr("excessinfoStr",null);
			}else{
			    addAttr("excessinfoStr",null);
			}
			
			 long time2=System.currentTimeMillis();
			 System.out.println(time2-time1);
			return staffList;
		}else{
			obj= fail(result.getErrorObj(Response_BaseMsg.class));
			return null;
		}

	}
	
	
	    @RequestMapping("/selectPassengerList.act")
	    public String selectPassengerList(Long timestamp,Integer flag,Integer tripType,Integer cabinType,String airline,String[] flowId,String[] depAirport,String[] arrAirport,String[] date,Integer type,FlightListFilterParam fp,String order,String direction,PageParam pp, BookOrderInfo bf) throws Exception{
	        ListStaff ls=new ListStaff();
	        Request_ListStaff para = new Request_ListStaff();
	        para.setUserId(getUserId());
	        OutputResult<Response_ListStaff, Response_BaseMsg> result = ls.ClientRequest(para,
	                Response_ListStaff.class,Response_BaseMsg.class);
	        AjaxResp obj = null;
	        
	        if(result.IsSucceed()){
	            Response_ListStaff resultObj = result.getResultObj();
	            List<ListEmployee> list = resultObj.getData();
	            Pager<ListEmployee> pager =new Pager<>(pp.getPageNum(), pp.getPageNumShown(), list);
	            obj = success(pager);
	            String resultStr = new Gson().toJson(result.getResultObj());
	            System.out.println(resultStr);
	            addAttr("result", obj);
	            
	            if(bf!=null&&bf.getMapKey()!=null&&!"".equals(bf.getMapKey())){
	                bf.setJumpCount((bf.getJumpCount()==null?1:bf.getJumpCount())+1);
	                String json=JsonUtil.ObjectToJsonStr(bf);
	                addAttr("bookinfo",json);
	                addAttr("skipType",bf.getSkipType());
	            }
	            
	            addAttr("flag",flag);
	            addAttr("tripType",tripType);
	            addAttr("cabinType",cabinType);
	            addAttr("airline",airline);
	            Integer[] flowId_=new Integer[2];
	            for (int i = 0; i < flowId.length; i++) {
	                if(StringUtils.isNotEmpty(flowId[i])){
	                    flowId_[i]=Integer.valueOf(flowId[i]);
	                }
                }
	            addAttr("flowId",flowId_);
	            addAttr("depAirport",depAirport);
	            addAttr("arrAirport",arrAirport);
	            addAttr("date",date);
	            addAttr("type",type);
	            
	            return passengersList;
	        }else{
	            obj= fail(result.getErrorObj(Response_BaseMsg.class));
	            return null;
	        }

	    }
	    
	    @RequestMapping("/rrrrrrrrrrrrrrr.act")
	    public String toReson2(){
	        System.out.println("0000000000000000");
	        return staffList;
	    }

}
