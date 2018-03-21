package com.viptrip.wetrip.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.viptrip.base.action.AjaxResp;
import com.viptrip.base.action.BaseAction;
import com.viptrip.util.PageParam;
import com.viptrip.util.StringUtil;
import com.viptrip.wetrip.controller.AddPassenger;
import com.viptrip.wetrip.controller.DelPassenger;
import com.viptrip.wetrip.controller.ListPassenger;
import com.viptrip.wetrip.controller.UpdatePassenger;
import com.viptrip.wetrip.entity.TTravelPassenger;
import com.viptrip.wetrip.model.Request_AddPassenger;
import com.viptrip.wetrip.model.Request_DelPassenger;
import com.viptrip.wetrip.model.Request_ListPassenger;
import com.viptrip.wetrip.model.Request_UpdatePassenger;
import com.viptrip.wetrip.model.Response_AddPassenger;
import com.viptrip.wetrip.model.Response_DelPassenger;
import com.viptrip.wetrip.model.Response_ListPassenger;
import com.viptrip.wetrip.model.Response_UpdatePassenger;
import com.viptrip.wetrip.model.passenger.ReqData_CertificateMessage;
import com.viptrip.wetrip.model.passenger.Req_Passenger;
import com.viptrip.wetrip.service.IPassengerService;
import com.viptrip.wetrip.util.JsonUtil;
import com.viptrip.wetrip.vo.BookOrderInfo;

import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.v2.base.Response_BaseMsg;

@Controller
@RequestMapping("/ppp")
public class PassengerAction extends BaseAction{

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory
			.getLogger(PassengerAction.class);
	
	@Resource
	private IPassengerService  passengerService;
	

	@RequestMapping("/pppp.act")
	public String getListPassenger(PageParam pp,String flag,String bf) {
		Request_ListPassenger request = new Request_ListPassenger();
		request.setUserId(getUserId());
		ListPassenger lp = new ListPassenger();

		OutputResult<Response_ListPassenger, Response_BaseMsg> result = lp.ClientRequest(
				request, Response_ListPassenger.class,Response_BaseMsg.class);
		AjaxResp obj = null;
		if(result.IsSucceed()){
			Response_ListPassenger resultObj = result.getResultObj();
			List<Req_Passenger> list = resultObj.getData();
			//Pager<Req_Passenger> pager = new Pager<Req_Passenger>(1,100,list1);
			//List<Req_Passenger> list = pager.getList();
			obj= success(list);
		}else{
			obj=fail(result.getErrorObj(Response_BaseMsg.class));
		}
		addAttr("results",obj);
		addAttr("flag","pass");
		addAttr("bookinfo",bf);
		
		 return "passenger/changePassenger";
	}
	
	@RequestMapping("/addd.act")
	public String add(String flag,String bf){
		addAttr("flag", flag);
		if(StringUtil.isNotEmpty(bf)){
			BookOrderInfo as=JsonUtil.JsonToObject(BookOrderInfo.class, bf);
			as.setJumpCount(as.getJumpCount()+1);
			addAttr("skipType",as.getSkipType());
			bf=JsonUtil.ObjectToJsonStr(as);
		}
		addAttr("bookinfo",bf);
		
		/*国际下单新增常旅客标识    hzc添加 2017-12-15*/
		String intlflag = getReq().getParameter("intlflag");
		addAttr("intlflag",intlflag);
		/*----------------------------------*/
		return "passenger/add";
	}
	
	@RequestMapping("/updatee.act")
	public String update( String passengerId,String flag,String bf){
		Long passenger=Long.parseLong(passengerId);
		TTravelPassenger oneById = passengerService.getOneById(passenger);
		addAttr("onePass",oneById);
		if(StringUtil.isNotEmpty(bf)){
			BookOrderInfo as=JsonUtil.JsonToObject(BookOrderInfo.class, bf);
			as.setJumpCount(as.getJumpCount()+1);
			addAttr("skipType",as.getSkipType());
			bf=JsonUtil.ObjectToJsonStr(as);
		}
		addAttr("bookinfo",bf);
		addAttr("flag",flag);
		
		String intlflag = req.getParameter("intlflag");
		if("intl".equals(intlflag)){
			addAttr("intlflag",intlflag);
		}
		
		return "passenger/update";
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/update.act")
	public String updatePassenger(Req_Passenger addP,
			Integer idtype, String num,String flag,String bf,RedirectAttributes attr) {

		Request_UpdatePassenger passenger = new Request_UpdatePassenger();
		ReqData_CertificateMessage personData = new ReqData_CertificateMessage();
		passenger.setUserId(getUserId());
		List list = new ArrayList();
		personData.setType(idtype);
		personData.setNum(num);
		
		list.add(personData);
		addP.setIds(list);
		passenger.setAddP(addP);

		UpdatePassenger updatePassenger = new UpdatePassenger();

		@SuppressWarnings("unused")
		OutputResult<Response_UpdatePassenger, String> result = updatePassenger
				.ClientRequest(passenger, Response_UpdatePassenger.class);

		if(StringUtil.isNotEmpty(bf)){
			BookOrderInfo as=JsonUtil.JsonToObject(BookOrderInfo.class, bf);
			as.setJumpCount(as.getJumpCount()+1);
			attr.addFlashAttribute("skipType",as.getSkipType());
			bf=JsonUtil.ObjectToJsonStr(as);
		}
		if("my".equals(flag)){
			return this.getListPassenger(null,null,bf);
		}
		attr.addFlashAttribute("bookinfo",bf);
		
		
			return "redirect:/ppp/staff.act";
	
		
        
	}

	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/intlupdate.act")
	@ResponseBody
	public String intlupdatePassenger(Req_Passenger addP,
			Integer idtype, String num,String flag,String bf,RedirectAttributes attr) {

		Request_UpdatePassenger passenger = new Request_UpdatePassenger();
		ReqData_CertificateMessage personData = new ReqData_CertificateMessage();
		passenger.setUserId(getUserId());
		List list = new ArrayList();
		personData.setType(idtype);
		personData.setNum(num);
		
		list.add(personData);
		addP.setIds(list);
		passenger.setAddP(addP);

		UpdatePassenger updatePassenger = new UpdatePassenger();

		@SuppressWarnings("unused")
		OutputResult<Response_UpdatePassenger, String> result = updatePassenger
				.ClientRequest(passenger, Response_UpdatePassenger.class);

		if(StringUtil.isNotEmpty(bf)){
			BookOrderInfo as=JsonUtil.JsonToObject(BookOrderInfo.class, bf);
			as.setJumpCount(as.getJumpCount()+1);
			attr.addFlashAttribute("skipType",as.getSkipType());
			bf=JsonUtil.ObjectToJsonStr(as);
		}
		if("my".equals(flag)){
			return this.getListPassenger(null,null,bf);
		}
		attr.addFlashAttribute("bookinfo",bf);
		
		
			return "1";
	
		
        
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/add.act")
	public String addPassanger( Req_Passenger addP, Integer idType,
			String num,String flag,String bf,RedirectAttributes attr) throws Exception {
		Request_AddPassenger addPassenger = new Request_AddPassenger();
		List list = new ArrayList();
		ReqData_CertificateMessage person= new ReqData_CertificateMessage();
		person.setType(idType);
		person.setNum(num);
		list.add(person);
		addP.setIds(list);

		addPassenger.setAddP(addP);

		addPassenger.setUserId(getUserId());

		AddPassenger ap = new AddPassenger();
		@SuppressWarnings("unused")
		OutputResult<Response_AddPassenger, String> result = ap.ClientRequest(
				addPassenger, Response_AddPassenger.class);
		
		if(StringUtil.isNotEmpty(bf)){
			BookOrderInfo as=JsonUtil.JsonToObject(BookOrderInfo.class, bf);
			as.setJumpCount(as.getJumpCount()+1);
			attr.addFlashAttribute("skipType",as.getSkipType());
			bf=JsonUtil.ObjectToJsonStr(as);
		}
		if("my".equals(flag)){
			
			return this.getListPassenger(null,null,bf);
			
		}
		attr.addFlashAttribute("bookinfo",bf);
		
		String intlType = req.getParameter("intlType");//国际机票下单新增常旅客标识 用于区分重定向的页面 hzc添加 2017-12-15
		
		if(intlType!=null&&"1".equals(intlType)){
			return "redirect:/ppp/staff.act";
		}else{
			return "redirect:/ppp/staff.act";
		}
		
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/intladd.act")
	@ResponseBody
	public String intladdPassanger( Req_Passenger addP, Integer idType,
			String num,String flag,String bf,RedirectAttributes attr) throws Exception {
		Request_AddPassenger addPassenger = new Request_AddPassenger();
		List list = new ArrayList();
		ReqData_CertificateMessage person= new ReqData_CertificateMessage();
		person.setType(idType);
		person.setNum(num);
		list.add(person);
		addP.setIds(list);

		addPassenger.setAddP(addP);

		addPassenger.setUserId(getUserId());

		AddPassenger ap = new AddPassenger();
		@SuppressWarnings("unused")
		OutputResult<Response_AddPassenger, String> result = ap.ClientRequest(
				addPassenger, Response_AddPassenger.class);
		
		if(StringUtil.isNotEmpty(bf)){
			BookOrderInfo as=JsonUtil.JsonToObject(BookOrderInfo.class, bf);
			as.setJumpCount(as.getJumpCount()+1);
			attr.addFlashAttribute("skipType",as.getSkipType());
			bf=JsonUtil.ObjectToJsonStr(as);
		}
		if("my".equals(flag)){
			
			return this.getListPassenger(null,null,bf);
			
		}
		attr.addFlashAttribute("bookinfo",bf);
		
		String intlType = req.getParameter("intlType");//国际机票下单新增常旅客标识 用于区分重定向的页面 hzc添加 2017-12-15
		
		
		return "1";
		
		
	}
	
	
	@RequestMapping("/del.act")
	public String delPassenger(Integer passengerId) {
		Request_DelPassenger delPassenger = new Request_DelPassenger();
		delPassenger.setPassengerId(passengerId);
		delPassenger.setUserId(getUserId());

		DelPassenger dp = new DelPassenger();
		@SuppressWarnings("unused")
		OutputResult<Response_DelPassenger, String> result = dp.ClientRequest(
				delPassenger, Response_DelPassenger.class);
		return this.getListPassenger(null,null,null);
	}
	
	@RequestMapping("/passAjax.act")
	@ResponseBody
	public AjaxResp getListPassengerAjax(String flag) {
		Request_ListPassenger request = new Request_ListPassenger();
		request.setUserId(getUserId());
		ListPassenger lp = new ListPassenger();

		OutputResult<Response_ListPassenger, Response_BaseMsg> result = lp.ClientRequest(
				request, Response_ListPassenger.class,Response_BaseMsg.class);
		AjaxResp obj = null;
		if(result.IsSucceed()){
			Response_ListPassenger resultObj = result.getResultObj();
			List<Req_Passenger> list = resultObj.getData();
			//Pager<Req_Passenger> pager = new Pager<Req_Passenger>(1,10,list1);
			//List<Req_Passenger> list = pager.getList();
			obj= success(list);
		}else{
			obj=fail(result.getErrorObj(Response_BaseMsg.class));
		}
		
	
	    return obj;
	}
	
	@RequestMapping("/idcardAjax.act")
	@ResponseBody
	public String getPassByIdcard(String idcards){
		Long userId = getUserId();
		List<TTravelPassenger> list = passengerService.getPassByIdcard(idcards,userId);
		if(list.size()>0){
			return "该常旅客已存在"; 
		}
		return "";
	}
	
	@RequestMapping("/idcard.act")
	@ResponseBody
	public String getPassangerByIdcard(String idcard,String passid){
		TTravelPassenger oneById = passengerService.getOneById(new Long(passid));
		Long userId = getUserId();
		if(!oneById.getIdnumber().equals(idcard)){
			List<TTravelPassenger> list = passengerService.getPassByIdcard(idcard,userId);
			if(list.size()>0){
				return "请更换为未使用的证件号"; 
			}
		}
		return "";
	}
}