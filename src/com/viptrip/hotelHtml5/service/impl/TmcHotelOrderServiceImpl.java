package com.viptrip.hotelHtml5.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.viptrip.hotelHtml5.service.TmcHotelOrderService;
import com.viptrip.hotelHtml5.util.DateUtil;
import com.viptrip.hotelHtml5.util.IdcardValidatorUtil;
import com.viptrip.hotelHtml5.util.JhUtil;
import com.viptrip.hotelHtml5.util.ValidatorUtil;
import com.viptrip.hotelHtml5.vo.request.Request_SaveTmcOrder;
import com.viptrip.hotelHtml5.vo.tmc.OrderDetailRequestVo;
import com.viptrip.hotelHtml5.vo.tmc.ParameterResponseVO;
import com.viptrip.hotelHtml5.vo.tmc.TmcOrderContacts;
import com.viptrip.hotelHtml5.vo.tmc.TmcOrderGuest;
import com.viptrip.hotelHtml5.vo.tmc.request.Request_GetHotelBooking;
import com.viptrip.hotelHtml5.vo.tmc.request.Request_GetTmcOrderDetail;
import com.viptrip.hotelHtml5.vo.tmc.request.Request_ListApprovePolicyInfo;
import com.viptrip.hotelHtml5.vo.tmc.request.Request_ListTmcOrder;
import com.viptrip.hotelHtml5.vo.tmc.response.Response_GetHotelBooking;
import com.viptrip.hotelHtml5.vo.tmc.response.Response_GetTmcOrderDetail;
import com.viptrip.hotelHtml5.vo.tmc.response.Response_ListApprovePolicyInfo;
import com.viptrip.hotelHtml5.vo.tmc.response.Response_ListTmcOrder;
import com.viptrip.hotelHtml5.vo.tmc.response.Response_SaveTmcOrder;
import com.viptrip.wetrip.model.employees.CompanyInfo;

import etuf.v1_0.common.Constant;

@Service
public class TmcHotelOrderServiceImpl implements TmcHotelOrderService {

	@Override
	public Response_GetHotelBooking getHotelBooking(Request_GetHotelBooking request) {
		Response_GetHotelBooking result = new Response_GetHotelBooking();
		try {
			request.method = "getHotelBooking";
			result = JhUtil.sendReqJh(request, Response_GetHotelBooking.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.status = Constant.Code_Failed;
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Response_SaveTmcOrder saveTmcOrder(Request_SaveTmcOrder request) {
		Response_SaveTmcOrder result = new Response_SaveTmcOrder();
		try {
			request.method = "saveTmcOrder";
			result = JhUtil.sendReqJh(request, Response_SaveTmcOrder.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.status = Constant.Code_Failed;
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Response_ListTmcOrder listTmcOrder(Request_ListTmcOrder request) {
		Response_ListTmcOrder result = new Response_ListTmcOrder();
		try {
			request.method = "listTmcOrder";
			result = JhUtil.sendReqJh(request, Response_ListTmcOrder.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.status = Constant.Code_Failed;
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Response_GetTmcOrderDetail getTmcOrderDetail(Request_GetTmcOrderDetail request) {
		Response_GetTmcOrderDetail result = new Response_GetTmcOrderDetail();
		try {
			request.method = "getTmcOrderDetail";
			result = JhUtil.sendReqJh(request, Response_GetTmcOrderDetail.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.status = Constant.Code_Failed;
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<ParameterResponseVO> getOrgList(CompanyInfo companyInfo, List<ParameterResponseVO> list, String temp) {
		if(companyInfo != null){
			ParameterResponseVO vo = new ParameterResponseVO();
			vo.setV(companyInfo.id.toString());
			vo.setN(companyInfo.name);
			if(StringUtils.isEmpty(temp)){
				vo.setAttribute(companyInfo.name);
			}else{
				vo.setAttribute(temp + "-" + companyInfo.name);
			}
			temp = vo.getAttribute();
			list.add(vo);
			List<CompanyInfo> childs = companyInfo.child;
			if(!CollectionUtils.isEmpty(childs)){
				for(CompanyInfo child : childs){
					getOrgList(child, list, temp);
				}
			}
		}
		return list;
	}

	@Override
	public Map<String, String> validateStepOne(OrderDetailRequestVo orderDetail) throws Exception {
		Map<String, String> result = new HashMap<String, String>();
		result.put("code", "1000");

		//联系人List元素去空对象
		List<TmcOrderContacts> tmcOrderContactsList = orderDetail.getTmcOrderContactsList();
		if(!CollectionUtils.isEmpty(tmcOrderContactsList)){
			Iterator<TmcOrderContacts> contactsIt = tmcOrderContactsList.iterator();
			while(contactsIt.hasNext()){
				TmcOrderContacts contact = contactsIt.next();
				if(StringUtils.isEmpty(contact.getContact()) && StringUtils.isEmpty(contact.getTel())){
					contactsIt.remove();
				}
			}
			List<String> names = new ArrayList<String>();
			for(int i = 0; i < tmcOrderContactsList.size(); i++){
				TmcOrderContacts contacts = tmcOrderContactsList.get(i);
				if(names.contains(contacts.getContact())){
					throw new Exception("您填写的客人【" + contacts.getContact() + "】姓名有重复");
				}else{
					names.add(contacts.getContact());
				}
			}
		}
		//入住人List元素去空对象
		List<TmcOrderGuest> tmcOrderGuestList = orderDetail.getTmcOrderGuestList();
		if(!CollectionUtils.isEmpty(tmcOrderGuestList)){
		Iterator<TmcOrderGuest> guestIt = tmcOrderGuestList.iterator();
			while(guestIt.hasNext()){
				TmcOrderGuest guest = guestIt.next();
				if(StringUtils.isEmpty(guest.getGuestName())){	//入住人为空时，表示为填写次入住人信息， 移除空信息
					guestIt.remove();
					continue;
				}
			}
		}
		if(CollectionUtils.isEmpty(orderDetail.getTmcOrderGuestList())){
			throw new Exception("订单提交参数缺少[订单客人信息]");
		}else{
			/*基本信息判断*/
			List<TmcOrderGuest> guestList = orderDetail.getTmcOrderGuestList();
			List<TmcOrderGuest> childGuestList = new ArrayList<TmcOrderGuest>(); 	//儿童客人集合
			Set<String> roomNos = new HashSet<String>();
			Set<String> guestNames = new HashSet<String>();
			for(TmcOrderGuest g : guestList){
				if(StringUtils.isBlank(g.getGuestName())){
					throw new Exception("您填写的客人的姓名信息！");
				}
				if(!guestNames.add(g.getGuestName())){	//判断客人姓名是否重复
					throw new Exception("您填写的客人【" + g.getGuestName() + "】姓名有重复");
				}
				if(StringUtils.isBlank(g.getTel())){
					throw new Exception("请填写客人【" + g.getGuestName() + "】的电话号码！");
				}else if(!ValidatorUtil.isMobile(g.getTel())){
					throw new Exception("请正确输入客人【" + g.getGuestName() + "】的电话号码！");
				}
				if(StringUtils.isNotBlank(g.getEmail()) && !ValidatorUtil.isEmail(g.getEmail())){
					throw new Exception("请正确输入客人【" + g.getGuestName() + "】的邮箱地址！");
				}
				if("1".equals(g.getAgeType())){	//1、成人		判断每间房至少入住一成人（获取房间号集合，判断size 和 房间数量是否匹配）
					roomNos.add(g.getRoomNo());
				}else{
					childGuestList.add(g);
					if(g.getGuestAge() == 0){
						g.setGuestAge(DateUtil.getAgeByBirth(g.getBirthday()));
					}
				}
			}
			/*每间房至少住一个人，判断是否满足此条件*/
			Integer roomCount = orderDetail.getTmcOrderHotel().getRoomNum();
			Integer childCount = orderDetail.getChildCount();
			if(roomCount > roomNos.size()){
				throw new Exception("每间房请至少填写一位成人！");
			}
			/*根据模板校验*/
			String templetFlag = orderDetail.getPageTempletFlag();
			if(StringUtils.isBlank(templetFlag)){
				throw new Exception("获取酒店模板信息异常");
			}else{
				for(TmcOrderGuest guest : guestList){
					switch (templetFlag) {
					case "1":
						//入住人姓名支持中文+英文+拼音
						
						break;
					case "2":
						//入住人姓名支持英文+拼音
						
						break;
					case "2A":
						//入住人姓名支持英文+拼音　订单填写页面不能更换房间数量
						
						break;
					case "3":
						//入住人姓名支持英文+拼音	（增加每间房其余儿童住客姓名及年龄必填）
						if(childGuestList.size() % roomCount == 0 && childCount != childGuestList.size() / roomCount){
							throw new Exception("每间房请填写" + childCount + "位儿童信息");
						}
						if("2".equals(guest.getAgeType()) && guest.getGuestAge() > 0){
							//不做处理
						}else{
							throw new Exception("客人" + guest.getGuestName() + "缺少年龄信息，请补充完善！");
						}
						break;
					case "4":
						//入住人姓名支持英文+拼音（增加住客证件类型及证件号+生日）	--身份证必填，生日非必填
						if(StringUtils.isBlank(guest.getCertType())){
							throw new Exception("订单提交参数缺少[客人信息信息-证件类型-身份证]");
						}
						if(StringUtils.isBlank(guest.getCertNo())){
							throw new Exception("请填写客人【" + guest.getGuestName() + "】的身份证号！");
						}else if(!IdcardValidatorUtil.isValidatedAllIdcard(guest.getCertNo())){
							throw new Exception("您填写的客人【" + guest.getGuestName() + "】身份证号不合法！");
						}
						break;
					case "5":
						//入住人姓名支持英文+拼音（增加住客护照+生日+护照签发日期+护照到期日期）	护照签发日期+护照到期日期暂时不填（酒店系统没有这两个字段）
						if(StringUtils.isBlank(guest.getCertType())){
							throw new Exception("订单提交参数缺少[客人信息信息-证件类型-护照]");
						}
						if(StringUtils.isBlank(guest.getCertNo())){
							throw new Exception("请填写客人【" + guest.getGuestName() + "】的护照信息！！");
						}
						if(guest.getBirthday() == null){
							throw new Exception("请填写客人【" + guest.getGuestName() + "】的出生日期！");
						}
						break;
					case "6":
						//入住人姓名支持中文+英文+拼音
						
						break;
					default:
						break;
					}
					
				}
			}
		}
		
		if(CollectionUtils.isEmpty(orderDetail.getTmcOrderContactsList())){
			throw new Exception("订单提交参数缺少[订单联系人信息]");
		}else{
			List<TmcOrderContacts> contactsList = orderDetail.getTmcOrderContactsList();
			for(TmcOrderContacts contact : contactsList){
				if(StringUtils.isBlank(contact.getContact())){
					throw new Exception("订单提交参数缺少[订单联系人信息-姓名]");
				}
				if(StringUtils.isBlank(contact.getTel())){
					throw new Exception("订单提交参数缺少[订单联系人信息-电话]");
				}else if(!ValidatorUtil.isMobile(contact.getTel())){
					throw new Exception("请正确输入联系人【" + contact.getContact() + "】的电话号码！");
				}
				break;
			}
		}
		return result;
	}

	@Override
	public Response_ListApprovePolicyInfo listApprovePolicyInfo(Request_ListApprovePolicyInfo request) {
		Response_ListApprovePolicyInfo result = new Response_ListApprovePolicyInfo();
		try {
			request.method = "listApprovePolicyInfo";
			result = JhUtil.sendReqJh(request, Response_ListApprovePolicyInfo.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.status = Constant.Code_Failed;
			e.printStackTrace();
		}
		return result;
	}

}
