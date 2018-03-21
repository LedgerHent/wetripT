package com.viptrip.wetrip.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.Query;

import jodd.util.StringUtil;


import org.springframework.stereotype.Service;

import com.viptrip.base.common.MyEnum.BookErrorTypeCode;
import com.viptrip.base.common.MyEnum.IdType;

import com.viptrip.ibeserver.service.ReadIBEDataService;
import com.viptrip.ibeserver.service.ReadIBEManagerService;
import com.viptrip.ibeserver.service.impl.ReadIBEManagerImpl;
import com.viptrip.wetrip.controller.GetInsuranceList;
import com.viptrip.wetrip.dao.ext.ComDao;
import com.viptrip.wetrip.entity.TAcInsurance;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.entity.TOrder;
import com.viptrip.wetrip.entity.TPnr;
import com.viptrip.wetrip.entity.TTicketRefund;
import com.viptrip.wetrip.entity.TTravelItinerary;
import com.viptrip.wetrip.entity.TTravelPassenger;
import com.viptrip.wetrip.entity.TUpdateDate;
import com.viptrip.wetrip.model.Request_BookAirTicket;
import com.viptrip.wetrip.model.Request_GetInsuranceList;
import com.viptrip.wetrip.model.Request_ToPay;
import com.viptrip.wetrip.model.Response_GetInsuranceList;
import com.viptrip.wetrip.model.Response_GetPayMethodList;
import com.viptrip.wetrip.model.base.Response_PriceAndDescription;
import com.viptrip.wetrip.model.employees.ListEmployee;
import com.viptrip.wetrip.model.flight.ReqData_BookAirTicket;
import com.viptrip.wetrip.model.flight.ReqData_BookAirTicket_Contact;
import com.viptrip.wetrip.model.flight.ReqData_BookAirTicket_OverReason;
import com.viptrip.wetrip.model.flight.ReqData_BookAirTicket_Passenger;
import com.viptrip.wetrip.model.flight.ReqData_BookAirTicket_Trip;
import com.viptrip.wetrip.model.flight.RespData_BookAirTicket;
import com.viptrip.wetrip.model.passenger.Req_Passenger;
import com.viptrip.wetrip.service.BookOrderService;
import com.viptrip.wetrip.util.JsonUtil;
import com.viptrip.wetrip.vo.BookOrderInfo;
import com.viptrip.wetrip.vo.UserInfo;
import com.viptrip.wetrip.wsclient.PnrResult;
import com.viptrip.wetrip.wsclient.base.StringArray;

import etuf.v1_0.model.base.output.OutputResult;
import sun.applet.Main;

@Service
public class BookOrderServiceImpl implements BookOrderService {
	
	@Resource
	private ComDao comDao;
	
	@Resource
	private ReadIBEManagerService readIBEManagerImpl;

	@Override
	public TAcOrg getTAcOrgByUserId(Long userId) {
		TAcUser tacuser=comDao.queryForEntity(userId, TAcUser.class);
		TAcOrg  tacorg=comDao.queryForEntity(tacuser.getOrgid(), TAcOrg.class);
		TAcOrg  comOrg=comDao.queryForEntity(Long.valueOf(tacorg.getCompany()), TAcOrg.class);
		return comOrg;
	}

	@Override
	public BookOrderInfo getBookOrderInfo(String mapKey, Integer type,Long timestamp,String bf,UserInfo userinfo,Long userId,OutputResult<Response_GetPayMethodList, String> payMethod,List<TAcInsurance> result) {
		BookOrderInfo bookinfo=null;

		System.out.println(payMethod.getResultObj().getData().toString());
		
		/*//保险，拷贝邓飞代码
		Request_GetInsuranceList gilist = new Request_GetInsuranceList();
		gilist.setUserId(userId);
		GetInsuranceList gins = new GetInsuranceList();
		OutputResult<Response_GetInsuranceList, String> or = gins.ClientRequest(gilist, Response_GetInsuranceList.class);
		Response_PriceAndDescription baoxian=new Response_PriceAndDescription();
		for (Response_PriceAndDescription rp : or.getResultObj().getData()) {
			if(240==rp.getId()){
				baoxian=rp;
			}
		}*/
		
	
		
		Response_PriceAndDescription baoxian=new Response_PriceAndDescription();
		if(result.size()>0){
			for (TAcInsurance t : result) {
				if(240==t.getId()){
					baoxian.id=t.getId();
					baoxian.price=t.getPrice();
				}
			}
		}
		
	/*----------------	end----------*/
	
		
		
		if(mapKey!=null && !"".equals(mapKey)){
			bookinfo=new BookOrderInfo();
			bookinfo.setSource(3);
			bookinfo.setChildBabySuggestBook(0);
			bookinfo.setDijiaReason("起飞或达到时间不合适");
			bookinfo.setMapKey(mapKey);
			bookinfo.setPayMethodId(payMethod.getResultObj().getData().get(0).getId());
			bookinfo.setPayMethod(payMethod.getResultObj().getData().get(0).getName());
			bookinfo.setTraveType(type);
			bookinfo.setTimestamp(timestamp);
			
			
		}else{
			bookinfo = packagingBookInfo(bf, userinfo, userId, baoxian);
		}
		return bookinfo;
	}

	@Override
    public BookOrderInfo getBookOrderInfo(Integer type,Long timestamp,String bf,UserInfo userinfo,Long userId,OutputResult<Response_GetPayMethodList, String> payMethod,List<TAcInsurance> result) {
            BookOrderInfo bookinfo=null;
            System.out.println(payMethod.getResultObj().getData().toString());
            Response_PriceAndDescription baoxian=new Response_PriceAndDescription();
            if(result.size()>0){
                for (TAcInsurance t : result) {
                    if(240==t.getId()){
                        baoxian.id=t.getId();
                        baoxian.price=t.getPrice();
                    }
                }
            }
            
        /*----------------  end----------*/
      bookinfo = packagingBookInfo(bf, userinfo, userId, baoxian);
      return bookinfo;
    }
	
    public BookOrderInfo packagingBookInfo(String bf, UserInfo userinfo, Long userId,
            Response_PriceAndDescription baoxian) {
//        String mapKey;
//        Integer type;
        BookOrderInfo bookinfo=new BookOrderInfo();
        if(StringUtil.isNotEmpty(bf)){
            bookinfo=JsonUtil.JsonToObject(BookOrderInfo.class, bf);
        }
        
        
        /*for (UserInfo u : bookinfo.getCheckMans()) {
        	u.setUserIdTypeStr(IdType.getIdTypeStr(Integer.valueOf(u.getUserIdType())).toString());
        }*/
        List<UserInfo> newUserInfos=new ArrayList<UserInfo>();
        for (UserInfo u : bookinfo.getPassengerInfos()) {
        	if(u.getId()==null||"".equals(u.getId().toString())){
            	continue;
            }
        	newUserInfos.add(u);
        }
        bookinfo.setPassengerInfos(newUserInfos);
        for (UserInfo u : bookinfo.getPassengerInfos()) {
            
        	if(u.getUserIdType()!=null&&!"".equals(u.getUserIdType())){
        		u.setUserIdTypeStr(IdType.getIdTypeStr(Integer.valueOf(u.getUserIdType())).toString());
        	}else{
        		u.setUserIdTypeStr("证件号");
        	}
        }
        Boolean flag=true;
        if(1==bookinfo.getSkipType()){
        	if(userinfo!=null){
        	    for (UserInfo u : bookinfo.getPassengerInfos()) {
                	if(u.getId().equals(userinfo.getId())&&u.getUserType().equals(userinfo.getUserType())){
        				flag=false;
        			}
                }
        		
        	}
        	if(flag&&userinfo!=null){
        		if(userinfo.getUserIdType()!=null&&!"".equals(userinfo.getUserIdType())){
        			userinfo.setUserIdTypeStr(IdType.getIdTypeStr(Integer.valueOf(userinfo.getUserIdType())).toString());
        		}else{
        			userinfo.setUserIdTypeStr("证件号");
        		}
        		userinfo.setIsSaleInsuance("0");
        		userinfo.setInsuraceId(baoxian.getId()==null?"-1":baoxian.getId().toString());
        		//userinfo.setInsuraceId(baoxian.getId().toString());
        		userinfo.setInsuancePrice(String.valueOf(baoxian.getPrice()));
        		TAcUser user=comDao.queryForEntity(Long.valueOf(userId), TAcUser.class);
        		TAcOrg org=comDao.queryForEntity(user.getOrgid(), TAcOrg.class);
        		if(userinfo.getCostAttachId()==null||"".equals(userinfo.getCostAttachId())){
        			if("1".equals(userinfo.getUserType())){//企业员工
        				TAcUser tacuser=comDao.queryForEntity(Long.valueOf(userinfo.getId()), TAcUser.class);
        				if(tacuser!=null){
        					TAcOrg tacorg=comDao.queryForEntity(tacuser.getOrgid(), TAcOrg.class);
        					userinfo.setCostAttachId(tacuser.getOrgid().toString());
        					userinfo.setCostAttachName(tacorg.getOrgname());
        				}
        			}else{
        				userinfo.setCostAttachId(user.getOrgid().toString());
        				userinfo.setCostAttachName(org.getOrgname());
        			}
        		}
        		bookinfo.getPassengerInfos().add(userinfo);
        	}
        	
        }else if(2==bookinfo.getSkipType()){
            
        	List<UserInfo> newTongzhirens=new ArrayList<UserInfo>();
        	for (UserInfo u : bookinfo.getTongzhirens()) {
        		if(u.getId()==null||"".equals(u.getId().toString())){
                	continue;
                }
        		newTongzhirens.add(u);
        		if(userinfo!=null){
        			if(u.getId().equals(userinfo.getId())&&u.getUserType().equals(userinfo.getUserType())){
        				flag=false;
        			}
        		}
        		
        	}
        	bookinfo.setTongzhirens(newTongzhirens);
        	
        	
        	/*if(userinfo.getUserIdType()!=null&&!"".equals(userinfo.getUserIdType())){
        		userinfo.setUserIdTypeStr(IdType.getIdTypeStr(Integer.valueOf(userinfo.getUserIdType())).toString());
        	}*/
        	if(flag&&userinfo!=null){
        		bookinfo.getTongzhirens().add(userinfo);
        	}
        	
        }else{
        	List<UserInfo> userInfos=new ArrayList<UserInfo>();
        	if(userinfo!=null){
        		userInfos.add(userinfo);
        		bookinfo.setCheckMans(userInfos);
        	}
        	
        }
//        mapKey=bookinfo.getMapKey();
//        type=bookinfo.getTraveType();
        return bookinfo;
    }

	@Override
	public Request_BookAirTicket getRequestBookOrderInfo(BookOrderInfo bf,Long userId) {
		Request_BookAirTicket para=new Request_BookAirTicket();
		try {
			para.source=bf.getSource();
			para.orderType=1;
			para.businessType=bf.getTraveType();
			para.userId=userId;
			ReqData_BookAirTicket data=new ReqData_BookAirTicket();
			data.setTripType(bf.getFlightType());
			data.setTimestamp(bf.getTimestamp());
			data.setPayMethod(bf.getPayMethodId());
			if(bf.getCheckMans()!=null&&bf.getCheckMans().size()>0){
				data.setAuditorId(Integer.valueOf(bf.getCheckMans().get(0).getId()));
			}
			List<ReqData_BookAirTicket_Trip> trips=new ArrayList<ReqData_BookAirTicket_Trip>();
			ReqData_BookAirTicket_Trip trip=null;
			String[] mapKeys=bf.getMapKey().split(",");
			for (int i = 0; i < mapKeys.length; i++) {
				trip=new ReqData_BookAirTicket_Trip();
				trip.setFlowId(i+1);
				trip.setMapKey(mapKeys[i]);
				trip.setChildBabySuggestBook(0);
				trips.add(trip);
			}
			data.setTrips(trips);
		
			List<ReqData_BookAirTicket_Passenger> passengers=new ArrayList<ReqData_BookAirTicket_Passenger>();
			ReqData_BookAirTicket_Passenger passenger=null;
			for (UserInfo userinfo : bf.getPassengerInfos()) {
				passenger=new ReqData_BookAirTicket_Passenger();
				passenger.setType(Integer.valueOf(userinfo.getUserType()));
				passenger.setId(Integer.valueOf(userinfo.getId()));
				passenger.setIdType(Integer.valueOf(userinfo.getUserIdType()));
				passenger.setIdNum(userinfo.getUserId());
				if(userinfo.getCostAttachId()!=null&&!"".equals(userinfo.getCostAttachId())){
					passenger.setCostCenter(Integer.valueOf(userinfo.getCostAttachId()));
				}
				
				passenger.setProjectNo(userinfo.getProject());
				passenger.setInsuranceId(Integer.valueOf(userinfo.getInsuraceId()));
				if("0".equals(userinfo.getIsSaleInsuance())){
					passenger.setInsuranceNum(1);
				}else{
					passenger.setInsuranceNum(0);
				}
				passengers.add(passenger);
			}
			data.setPassengers(passengers);
			
			List<ReqData_BookAirTicket_Contact> contacts =new ArrayList<ReqData_BookAirTicket_Contact>();
			ReqData_BookAirTicket_Contact  contact=null;
			if(bf.getTongzhirens()!=null&&bf.getTongzhirens().size()>0){
				for (UserInfo userinfo : bf.getTongzhirens()) {
					contact=new ReqData_BookAirTicket_Contact();
					contact.setId(Integer.valueOf(userinfo.getId()));
					contact.setName(userinfo.getUserName());
					contact.setMobile(userinfo.getUserPhone());
					contact.setEmail("");
					contacts.add(contact);
				}
				data.setContacts(contacts);
			}
			//超标信息
			List<ReqData_BookAirTicket_OverReason> overReason=new ArrayList<ReqData_BookAirTicket_OverReason>();
			if(StringUtil.isNotEmpty(bf.getQuTianxieReason())||StringUtil.isNotEmpty(bf.getQuXuanzeReason())){
			    ReqData_BookAirTicket_OverReason overResonObj=new ReqData_BookAirTicket_OverReason();
			    if(StringUtil.isNotEmpty(bf.getQuTianxieReason())){
			        overResonObj.setReason(bf.getQuTianxieReason());
			    }
			    if(StringUtil.isNotEmpty(bf.getQuXuanzeReason())){
                    overResonObj.setReason(bf.getQuXuanzeReason());
                }
			    String type=bf.getExcessinfo().split(",")[0].split("_")[0];
			    overResonObj.setType(Integer.valueOf(type));//非金额的
			    String flightCity=bf.getMapKey().split(",")[0].split("\\|")[0];
			    String flightcitystr=flightCity.split("_")[3]+flightCity.split("_")[4];
			    overResonObj.setFlightCity(flightcitystr);
			    overReason.add(overResonObj);
			}
			System.out.println();
			if(StringUtil.isNotEmpty(bf.getFanTianxieReason())||StringUtil.isNotEmpty(bf.getFanXuanzeReason())){
                ReqData_BookAirTicket_OverReason overResonObj=new ReqData_BookAirTicket_OverReason();
                if(StringUtil.isNotEmpty(bf.getFanTianxieReason())){
                    overResonObj.setReason(bf.getFanTianxieReason());
                }
                if(StringUtil.isNotEmpty(bf.getFanXuanzeReason())){
                    overResonObj.setReason(bf.getFanXuanzeReason());
                }
                String type=bf.getExcessinfo().split(",")[1].split("_")[0];
                overResonObj.setType(Integer.valueOf(type));//非金额的
                String flightCity=bf.getMapKey().split(",")[1].split("\\|")[0];
                String flightcitystr=flightCity.split("_")[3]+flightCity.split("_")[4];
                overResonObj.setFlightCity(flightcitystr);
                overReason.add(overResonObj);
            }
			data.setOverReason(overReason);
			para.data=data;
			return para;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ListEmployee> getListEmployees(Long orgid) {
		String sql="select o.orgid,o.orgname,o.parentid from  t_ac_org o where o.orgid in ("
                  +" select tt.orgid from t_ac_org tt  where tt.orgtype not in ('0', '1') "
                  +" start with orgid = ? connect by prior tt.orgid = tt.parentid)";
		Long[] parm=new Long[1];  
		parm[0]=orgid;
		List<Object[]> orgs=(List<Object[]>) comDao.queryBySQL(sql,parm);
		List<ListEmployee> ListEmployees=new ArrayList<ListEmployee>();
		if(orgs!=null&&orgs.size()>0){
			ListEmployee listEmployee=null;
			for (int i = 0; i < orgs.size(); i++) {
				listEmployee=new ListEmployee();
                listEmployee.setDepartmentId(Integer.valueOf(orgs.get(i)[0].toString()));
				listEmployee.setDepartmentName(orgs.get(i)[1].toString());
				ListEmployees.add(listEmployee);
			}
		}
		
		return ListEmployees;
	}
	
	
	
	
	public String createPNR(TOrder order){
		List<TPnr> tpnrList = new ArrayList<TPnr>(order.getTPnrs()); // 获取PNR表信息集合
        String pnr = new String();
        String[] pname = null;
        String[] idType = null;
        String[] idNo = null;
        String[] contactinfo = null;
        List<TTravelItinerary> ttList = null;
        String[] airNo = new String[tpnrList.size()];
        char[] fltClass = new char[tpnrList.size()];
        String[] orgCity = new String[tpnrList.size()];
        String[] desCity = new String[tpnrList.size()];
        Date[] departureTime = new Date[tpnrList.size()];
        for (int i = 0; i < tpnrList.size(); i++) {
            TPnr tPnr = tpnrList.get(i);
            if (i == 0) {
                // 乘机人只需要获取一次
                ttList = new ArrayList<TTravelItinerary>(tPnr.getTTravelItineraries());// 获取乘机人集合信息
                pname = new String[ttList.size()];
                idType = new String[ttList.size()];
                idNo = new String[ttList.size()];
                contactinfo = new String[ttList.size()];
                /* 添加旅客身份证信息 */
                TTravelItinerary tt = null;
                for (int j = 0; j < ttList.size(); j++) {
                    tt = ttList.get(j);
                    if (tt.getPassengerType().equals("1")) {
                        pname[j] = tt.getPassengerName() + "1";
                    } else if (tt.getPassengerType().equals("2")) {
                        pname[j] = tt.getPassengerName() + "2";
                    } else if (tt.getPassengerType().equals("3")) {
                        pname[j] = tt.getPassengerName() + "3";
                    }
                    idType[j] = "NI";
                    idNo[j] = tt.getPassengerId();
                    contactinfo[j] = tt.getPassengerTel();
                }
            }
            airNo[i] = tPnr.getAirline();
            fltClass[i] = tPnr.getCangwei().charAt(0);
            orgCity[i] = tPnr.getOriginCity();
            desCity[i] = tPnr.getDestinationCity();
            departureTime[i] = tPnr.getFlightDate();
	   }
        PnrResult pr=readIBEManagerImpl.createConnectingPNR(pname, airNo, fltClass, orgCity, desCity, departureTime, idType, idNo, contactinfo);
        String actioncode = "";
        if (pr == null) {
            return null;
        } else {
            pnr = pr.getStrpnrno();  
            List<StringArray> pnrsegment = pr.getSegments();
            List<String> o = new ArrayList<String>();
            for (StringArray ps : pnrsegment) {
            	o = ps.getItem(); 
            	if (o.size() > 4)
            		actioncode = o.get(4);
            }
        }
        System.out.println("PNR的状态：" + actioncode);
        return pnr;
	}
    public String[] getPNR(TOrder order) {

        List<TPnr> tpnrList =  new ArrayList<TPnr>(order.getTPnrs()); // 获取PNR表信息集合;// 获取PNR表信息集合
        String[] pnr = new String[tpnrList.size()];
        // ------------------------------------------------------------------------------单程预订PNR
        if (tpnrList.size() == 1) {

            TPnr tPnr = tpnrList.get(0);
            List<TTravelItinerary> ttList = new ArrayList<TTravelItinerary>(tPnr.getTTravelItineraries());// 获取乘机人集合信息
            String[] pname = new String[ttList.size()];
            String airNo = tPnr.getAirline();
            char fltClass = tPnr.getCangwei().charAt(0);
            String orgCity = tPnr.getOriginCity();
            String desCity = tPnr.getDestinationCity();
            Date departureTime = tPnr.getFlightDate();
            String[] idType = new String[ttList.size()];
            String[] idNo = new String[ttList.size()];
            String[] contactinfo = new String[ttList.size()];

            /* 添加旅客身份证信息 */
            TTravelItinerary tt = null;
            for (int i = 0; i < ttList.size(); i++) {
                tt = ttList.get(i);
                if (tt.getPassengerType().equals("1")) {
                    pname[i] = tt.getPassengerName() + "1";
                } else if (tt.getPassengerType().equals("2")) {
                    pname[i] = tt.getPassengerName() + "2";
                } else if (tt.getPassengerType().equals("3")) {
                    pname[i] = tt.getPassengerName() + "3";
                }

                idType[i] = "NI";
                idNo[i] = tt.getPassengerId();
                contactinfo[i] = tt.getPassengerTel();// 通知人手机号（联系人）
            }
            PnrResult pr = readIBEManagerImpl.createPNR(pname, airNo, fltClass, orgCity, desCity, departureTime, idType,
                    idNo, contactinfo);
            if (pr == null) {
                return null;
            } else {
                pnr[0] = pr.getStrpnrno();
                List<StringArray> pnrsegment = pr.getSegments();
                List<String> o = new ArrayList<String>();
                for (StringArray ps : pnrsegment) {
                	o = ps.getItem(); 
                	if (o.size() > 4) {
                		System.out.println("单程pnr的行动代码：" + o.get(4));
                	}
                }
            }

            // system.out.println("此订单生成的PNR：" + pr.getStrpnrno());
        } else {
            TPnr qutpnr = new TPnr();
            TPnr fantpnr = new TPnr();
            TPnr tPnr = null;
            for (int i = 0; i < tpnrList.size(); i++) {
                tPnr = tpnrList.get(i);
                if (tPnr.getFlighttype().equals("1")) {
                    qutpnr = tPnr;
                } else {
                    fantpnr = tPnr;
                }
            }
         
            // 往返(航空公司不同)相当与预订两个单程的PNR
            // -------------------------------------------------------------去程PNR预订(航空公司不同)
            if (qutpnr.getFlighttype().equals("1")) {
                List<TTravelItinerary> ttList = new ArrayList<TTravelItinerary>(qutpnr.getTTravelItineraries());// 获取乘机人集合信息
                String[] pname = new String[ttList.size()];
                String airNo = qutpnr.getAirline();
                char fltClass = qutpnr.getCangwei().charAt(0);
                String orgCity = qutpnr.getOriginCity();
                String desCity = qutpnr.getDestinationCity();
                Date departureTime = qutpnr.getFlightDate();
                String[] idType = new String[ttList.size()];
                String[] idNo = new String[ttList.size()];
                String[] contactinfo = new String[ttList.size()];

                /* 添加旅客身份证信息 */
                TTravelItinerary tt = null;
                for (int i = 0; i < ttList.size(); i++) {
                    tt = ttList.get(i);
                    if (tt.getPassengerType().equals("1")) {
                        pname[i] = tt.getPassengerName() + "1";
                    } else if (tt.getPassengerType().equals("2")) {
                        pname[i] = tt.getPassengerName() + "2";
                    } else if (tt.getPassengerType().equals("3")) {
                        pname[i] = tt.getPassengerName() + "3";
                    }

                    idType[i] = "NI";
                    idNo[i] = tt.getPassengerId();
                    contactinfo[i] = tt.getPassengerTel();
                }
                PnrResult pr = readIBEManagerImpl.createPNR(pname, airNo, fltClass, orgCity, desCity, departureTime,
                        idType, idNo, contactinfo);
                if (pr == null) {
                    return null;
                } else {
                    pnr[0] = pr.getStrpnrno();
                    List<StringArray> pnrsegment = pr.getSegments();
                    List<String> o = new ArrayList<String>();
                    for (StringArray ps : pnrsegment) {
                    	o = ps.getItem(); 
                    	if (o.size() > 4) {
                    		System.out.println("去程pnr的行动代码：" + o.get(4));
                    	}
                    }
                }
                // system.out.println("此订单生成的PNR：" + pr.getStrpnrno());
            }
            // ------------------------------------------------------------------返程PNR预订(航空公司不同)
            if (fantpnr.getFlighttype().equals("2")) {
                List<TTravelItinerary> ttList =  new ArrayList<TTravelItinerary>(fantpnr.getTTravelItineraries());// 获取乘机人集合信息
                String[] pname = new String[ttList.size()];
                String airNo = fantpnr.getAirline();
                char fltClass = fantpnr.getCangwei().charAt(0);
                String orgCity = fantpnr.getOriginCity();
                String desCity = fantpnr.getDestinationCity();
                Date departureTime = fantpnr.getFlightDate();
                String[] idType = new String[ttList.size()];
                String[] idNo = new String[ttList.size()];
                String[] contactinfo = new String[ttList.size()];

                /* 添加旅客身份证信息 */
                TTravelItinerary tt = null;
                for (int i = 0; i < ttList.size(); i++) {
                    tt = ttList.get(i);
                    if (tt.getPassengerType().equals("1")) {
                        pname[i] = tt.getPassengerName() + "1";
                    } else if (tt.getPassengerType().equals("2")) {
                        pname[i] = tt.getPassengerName() + "2";
                    } else if (tt.getPassengerType().equals("3")) {
                        pname[i] = tt.getPassengerName() + "3";
                    }

                    idType[i] = "NI";
                    idNo[i] = tt.getPassengerId();
                    contactinfo[i] = tt.getPassengerTel();
                }
                PnrResult pr = readIBEManagerImpl.createPNR(pname, airNo, fltClass, orgCity, desCity, departureTime,
                        idType, idNo, contactinfo);
                if (pr == null) {
                    return null;
                } else {
                    pnr[1] = pr.getStrpnrno();
                    List<StringArray> pnrsegment = pr.getSegments();
                    List<String> o = new ArrayList<String>();
                    for (StringArray ps : pnrsegment) {
                    	o = ps.getItem(); 
                    	if (o.size() > 4) {
                    		System.out.println("回程pnr的行动代码：" + o.get(4));
                    	}
                    }
                }
            }

        }
        return pnr;

    }
    /**
     * 根据pnr提取备注组信息(大客户编码)
     * 
     * @param pnr
     */
    public String[] getBigplait(long oid, String[] pnr) {
        // 返回大客户编码
        String[] bigplait = new String[pnr.length];
        if (pnr.length == 2) {
            List<String> resList = null;
            for (int j = 0; j < pnr.length; j++) {
                resList = readIBEManagerImpl.getIBEBigplait(pnr[j]);
                String res = resList.get(0);
                if (res.substring(3, res.length()) != null && !"".equals(res.substring(3, res.length()))) {
                    bigplait[j] = res.substring(3, res.length());
                }
            }
        } else {// 单程
            List<String> resList = readIBEManagerImpl.getIBEBigplait(pnr[0]);
            if (resList.size() > 0) {
                String res = resList.get(0);
                if (res.substring(3, res.length()) != null && !"".equals(res.substring(3, res.length()))) {
                    bigplait[0] = res.substring(3, res.length());
                }
            }
        }

        return bigplait;
    }
    /**
     * @功能：保存pnr信息
     */
    @SuppressWarnings("unchecked")
    public void updateOrder(TOrder t, String[] pnr, String[] bigplait) {// Double
  

            List<TPnr> tpnrList = new ArrayList<TPnr>(t.getTPnrs());
            
            TPnr tPnr=new TPnr();
            for (int i = 0; i < tpnrList.size(); i++) {
                tPnr = tpnrList.get(i);
                // 添加pnr
                if (tPnr.getFlighttype().equals("1")) {
                    tPnr.setPnr(pnr[0]);
                } else {
                    if (pnr.length == 2) // 如果pnr数组不是两个元素，则设置pnr为同一个
                        tPnr.setPnr(pnr[1]);
                    else
                        tPnr.setPnr(pnr[0]);
                }
                // 添加大编
                if (bigplait.length > 0) {
                    if (tPnr.getFlighttype().equals("1")) {
                        tPnr.setBigplait(bigplait[0].substring(0, bigplait[0].indexOf("_")));
                        tPnr.setPnrstatus(bigplait[0].substring(bigplait[0].indexOf("_") + 1));  //行动代码
                    } else {
                        if (bigplait.length == 2) {// 大编同理
                            tPnr.setBigplait(bigplait[1].substring(0, bigplait[1].indexOf("_")));
                            tPnr.setPnrstatus(bigplait[1].substring(bigplait[1].indexOf("_") + 1));  //行动代码
                        } else {
                            tPnr.setBigplait(bigplait[0].substring(0, bigplait[0].indexOf("_")));
                            tPnr.setPnrstatus(bigplait[0].substring(bigplait[0].indexOf("_") + 1));  //行动代码
                        }
                    }
                }
            }
            comDao.executeUpdate(t);
    }
  
    
    public RespData_BookAirTicket toPay(Request_ToPay para){
    	RespData_BookAirTicket rb=new RespData_BookAirTicket();
    	TOrder torder=comDao.queryForEntity(para.orderID, TOrder.class);
    	if(torder==null){
    		rb.setOrderId(BookErrorTypeCode.订单id不对.getValue());
    		return rb;
    	}else{
    		   rb.setOrderId(Integer.valueOf(torder.getOId().toString()));
	           rb.setOrderNo(torder.getOrderno());
	           rb.setAmount(torder.getTotalPrice());
	           rb.setServiceFee(torder.getTotalServiceFee());
	           rb.setOrderStatus(torder.getOrderStatus());
    	}
    	return rb;
    }
    public List getBookFlights(String passengerId) {
		
        return comDao.getBookFlights(passengerId);
	}
    
    public String getTotalPath(Long orgid) {
		String sql=" select replace( wm_concat(t.orgname), ',', ' -> ') orgnames from " +
				  " ( select * from t_ac_org tt where tt.orgtype not in ('0', '1') start with " +
				  " orgid="+orgid+" connect by prior tt.parentid = tt.orgid order by orgid ) t";
		List list1=comDao.queryBySQL(sql.toString(), null);
		if(list1!=null && list1.size()>0){
			return list1.get(0)==null?"":list1.get(0).toString();
		}
		return null;
	}
    
    
    public List<TUpdateDate> getTUpdateDates(List<Long> tids){
        String hql2 = "SELECT t from TUpdateDate t where TId in(:tids) and t.flightStatus='5' order by t.auditDate desc";
        Query query = comDao.getEntityManager().createQuery(hql2).setParameter("tids", tids);
        List<TUpdateDate> tts2=query.getResultList();
        /*List<TUpdateDate> tts2=comDao.queryForList(hql2,new Object[]{tids});*/
        return tts2;
    }
    
    public List<TTravelItinerary> getTTIts(List<Long> tids){
        String hql = "SELECT t from TTravelItinerary t where TId in(:tids)";
        Query query = comDao.getEntityManager().createQuery(hql).setParameter("tids", tids);
        return query.getResultList();
        /*List<TTravelItinerary> tts=comDao.queryForList(hql,new Object[]{tids});
        return tts;*/
    }
    
    public List<TTicketRefund> getTTicketRefundList(List<Long> tids ){
        String hql = "SELECT t from TTicketRefund t where tid in(:tids)";
        Query query = comDao.getEntityManager().createQuery(hql).setParameter("tids", tids);
        return query.getResultList();
       /* List<TTicketRefund> tts=comDao.queryForList(hql,new Object[]{tids});
        return tts;*/
    }
    
    
    @Override
    public Double getRescheduledUpgradeFee(String orderno, List<String> ticketNumbers,
            HashMap<String, Double> changeTrips) {
        double upgradeFee=-100000d;//初始化负一百万,如果返回此数值，表示数据有误，票号与订单号不匹配
        String hql2 = "SELECT t from TUpdateDate t,TOrder o where  t.orderno=o.orderno and o.orderno='"+orderno+"' and t.flightStatus='5' order by t.auditDate desc";
        HashMap<String,Boolean> maps=new HashMap<String,Boolean>();
        List<TUpdateDate> tts2=comDao.queryForList(hql2,null);
        if(tts2!=null && tts2.size()>0){
            for (String ticketNumber : ticketNumbers) {
                for (TUpdateDate tupdateDate : tts2) {
                    if(ticketNumber.equals(tupdateDate.getTravelItineraryNo())){
                        if(upgradeFee<0){
                            upgradeFee=0;
                        }
                        String key=tupdateDate.getFlightStart()+tupdateDate.getFlightEnd();
                        Double changetripsPrice=changeTrips.get(key);
                        upgradeFee+=(changetripsPrice.doubleValue()-tupdateDate.getTicketPrice().doubleValue());
                        maps.put(ticketNumber, true);
                    }
                }
            }
        }
        //已经改期过的票号不再处理，一般情况下改期过的票号不会在两个表中同时出现，但是以防万一。
        if(ticketNumbers.size() > maps.keySet().size()){
            String hql = "SELECT t from TTravelItinerary t,TPnr p,TOrder o where t.TPnr.pnrId=p.pnrId and p.TOrder.OId=o.OId and o.orderno='"+orderno+"'";
            List<TTravelItinerary> tts=comDao.queryForList(hql,null);
            for (String ticketNumber : ticketNumbers) {
                for (TTravelItinerary tTravelItinerary : tts) {
                    if(!maps.get(ticketNumber) && ticketNumber.equals(tTravelItinerary.getTravelItineraryNo())){
                        if(upgradeFee<0){
                            upgradeFee=0;
                        }
                        String key=tTravelItinerary.getFlightStart()+tTravelItinerary.getFlightEnd();
                        Double changetripsPrice=changeTrips.get(key);
                        upgradeFee+=(changetripsPrice.doubleValue()-tTravelItinerary.getTicketPrice().doubleValue());
                    }
                }
            }
        }
        
        //如果改期的升舱费用小于0,则返回0.
        if(upgradeFee>-100000 && upgradeFee<0){
            upgradeFee=0d;
        }
        return new Double(upgradeFee);
    }
    
    
    @Override
    public HashMap<String,Double> getRescheduledUpgradeFeeMap(String orderno, List<String> ticketNumbers,
            HashMap<String, Double> changeTrips) {
        HashMap<String,Double> feemap=new HashMap<String, Double>();
        double upgradeFee=-100000d;//初始化负一百万,如果返回此数值，表示数据有误，票号与订单号不匹配
        String hql2 = "SELECT t from TUpdateDate t,TOrder o where  t.orderno=o.orderno and o.orderno='"+orderno+"' and t.flightStatus='5' order by t.auditDate desc";
        HashMap<String,Boolean> maps=new HashMap<String,Boolean>();
        List<TUpdateDate> tts2=comDao.queryForList(hql2,null);
        if(tts2!=null && tts2.size()>0){
            for (String ticketNumber : ticketNumbers) {
                for (TUpdateDate tupdateDate : tts2) {
                    if(ticketNumber.equals(tupdateDate.getTravelItineraryNo())){
                        if(upgradeFee<0){
                            upgradeFee=0;
                        }
                        String key=tupdateDate.getFlightStart()+tupdateDate.getFlightEnd();
                        Double changetripsPrice=changeTrips.get(key);
                        upgradeFee+=(changetripsPrice.doubleValue()-tupdateDate.getTicketPrice().doubleValue());
                        maps.put(ticketNumber, true);
                    }
                }
                feemap.put(ticketNumber, upgradeFee);
                upgradeFee=-100000d;
            }
        }
        //已经改期过的票号不再处理，一般情况下改期过的票号不会在两个表中同时出现，但是以防万一。
        if(ticketNumbers.size() > maps.keySet().size()){
            String hql = "SELECT t from TTravelItinerary t,TPnr p,TOrder o where t.TPnr.pnrId=p.pnrId and p.TOrder.OId=o.OId and o.orderno='"+orderno+"'";
            List<TTravelItinerary> tts=comDao.queryForList(hql,null);
            for (String ticketNumber : ticketNumbers) {
                for (TTravelItinerary tTravelItinerary : tts) {
                    if(((maps.get(ticketNumber)!=null && !maps.get(ticketNumber)) || maps.get(ticketNumber)==null ) && ticketNumber.equals(tTravelItinerary.getTravelItineraryNo())){
                        if(upgradeFee<0){
                            upgradeFee=0;
                        }
                        String key=tTravelItinerary.getFlightStart()+tTravelItinerary.getFlightEnd();
                        Double changetripsPrice=changeTrips.get(key);
                        upgradeFee+=(changetripsPrice.doubleValue()-tTravelItinerary.getTicketPrice().doubleValue());
                    }
                }
                feemap.put(ticketNumber, upgradeFee);
                upgradeFee=-100000d;
            }
        }
        return feemap;
    }
    /**
     *   根据id获取常旅客
     */
    public TTravelPassenger getPassenger(String pid){
    	TTravelPassenger passenger=null;
    	if(pid!=null&&!"".equals(pid)){
    		//String sql="select ID,NAME,MOBILEPHONE,EMAIL,PASSENGER_TYPE,IDTYPE,IDNUMBER  from t_travel_passenger where ID= "+pid;
    		
    		 passenger = comDao.queryForEntity(Long.valueOf(pid) , TTravelPassenger.class);
    	}
    	return passenger;
    }


    public List getBookIntlFlights(String passengerStr,String airline,String flightDate){
        List ttList=null;
        if(passengerStr!=null&&!"".equals(passengerStr)){
            String sql="select ti.intl_departure_time,ti.intl_flight_number,ti.intl_cangwei, tt.intl_passenger_id " +
                    "from t_intl_air_ticket_itinerary ti,t_intl_travel_itinerary tt where ti.orderid=tt.orderid " +
                    " and  tt.intl_passenger_id='" +passengerStr.trim()+"'" +
                    " and to_char(ti.intl_departure_time,'yyyy-mm-dd')='"+flightDate+"'" +
                    " and ti.intl_flight_number ='"+airline+"'";



            ttList = comDao.queryBySQL(sql,null);

        }



        return ttList;
    }



}
