package com.viptrip.ibeserver.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.viptrip.ibeserver.service.ReadIBEManagerService;

import com.viptrip.wetrip.wsclient.DeleteIBEPNRImplDelegate;
import com.viptrip.wetrip.wsclient.DeleteIBEPNRImplService;
import com.viptrip.wetrip.wsclient.GetIBEBigplaitImplDelegate;
import com.viptrip.wetrip.wsclient.GetIBEBigplaitImplService;
import com.viptrip.wetrip.wsclient.GetIBEPNRFlightImplDelegate;
import com.viptrip.wetrip.wsclient.GetIBEPNRFlightImplService;
import com.viptrip.wetrip.wsclient.GetIBEPNRMsgImplDelegate;
import com.viptrip.wetrip.wsclient.GetIBEPNRMsgImplService;
import com.viptrip.wetrip.wsclient.GetIBEPNRTktImplDelegate;
import com.viptrip.wetrip.wsclient.GetIBEPNRTktImplService;
import com.viptrip.wetrip.wsclient.GetIBETicketNoImplDelegate;
import com.viptrip.wetrip.wsclient.GetIBETicketNoImplService;
import com.viptrip.wetrip.wsclient.IBEPNRAddPATAImplDelegate;
import com.viptrip.wetrip.wsclient.IBEPNRAddPATAImplService;
import com.viptrip.wetrip.wsclient.IBEQuitTicketImplDelegate;
import com.viptrip.wetrip.wsclient.IBEQuitTicketImplService;
import com.viptrip.wetrip.wsclient.IBETicketImplDelegate;
import com.viptrip.wetrip.wsclient.IBETicketImplService;
import com.viptrip.wetrip.wsclient.IBEvoidETicketImplDelegate;
import com.viptrip.wetrip.wsclient.IBEvoidETicketImplService;
import com.viptrip.wetrip.wsclient.TrfdResult;
import com.viptrip.util.DateUtil;
import com.viptrip.wetrip.wsclient.CreateIBEPNR;
import com.viptrip.wetrip.wsclient.CreateIBEPNRImplService;
import com.viptrip.wetrip.wsclient.CreateIBEToAndFroPNR;
import com.viptrip.wetrip.wsclient.CreateIBEToAndFroPNRImplService;
import com.viptrip.wetrip.wsclient.PnrResult;

@Service
public class ReadIBEManagerImpl implements ReadIBEManagerService {
    /**
     * 预订往返及联程的机票，使用此方法
     * 
     * @param pname
     *            乘客姓名
     * @param airNo
     *            航班号，多个，需同一航空公司
     * @param fltClass
     *            舱位代码
     * @param orgCity
     *            出发地，多个，每一航段的出发地
     * @param desCity
     *            到达地，多个，每一航段的到达地
     * @param departureTime
     *            出发时间，多个，每一航段的出发时间
     * @param idType
     *            乘机人证件类型
     * @param idNo
     *            乘机人证件号
     * @param contactinfo
     *            乘机人电话
     * @return
     */
	@Override
    public PnrResult createConnectingPNR(String[] pname, String[] airNo,
            char[] fltClass, String[] orgCity, String[] desCity,
            Date[] departureTime, String[] idType, String[] idNo,
            String[] contactinfo) {

        CreateIBEToAndFroPNRImplService ibeService = new CreateIBEToAndFroPNRImplService();
        CreateIBEToAndFroPNR port = ibeService
                .getCreateIBEToAndFroPNRImplPort();
        java.util.List<java.lang.String> pnames = new ArrayList<String>();
        java.util.List<java.lang.String> airNos = new ArrayList<String>();
        java.util.List<java.lang.Integer> fltClasss = new ArrayList<Integer>();
        java.util.List<java.lang.String> orgCitys = new ArrayList<String>();
        java.util.List<java.lang.String> desCitys = new ArrayList<String>();
        java.util.List<java.lang.String> departureTimes = new ArrayList<String>();
        java.util.List<java.lang.String> idTypes = new ArrayList<String>();
        java.util.List<java.lang.String> idNos = new ArrayList<String>();
        java.util.List<java.lang.String> contactinfos = new ArrayList<String>();
        for (int icount = 0; icount < pname.length; icount++) {
            // system.out.println(pname[icount]);
            pnames.add(pname[icount]);
            idTypes.add(idType[icount]);
            idNos.add(idNo[icount]);
            contactinfos.add(contactinfo[icount]);
        }

        for (int i = 0; i < 2; i++) {
            airNos.add(airNo[i]);
            fltClasss.add((int) fltClass[i]);
            orgCitys.add(orgCity[i]);
            desCitys.add(desCity[i]);
            departureTimes.add(DateUtil.date2Str(departureTime[i],
                    "yyyy-MM-dd HH:mm:ss"));
        }

        PnrResult _createPNR__return = port.createManyPNR(pnames, airNos,
                fltClasss, orgCitys, desCitys, departureTimes, idTypes, idNos,
                contactinfos);

        return _createPNR__return;

    }
    
	/**
	 * 调用webservice接口进行占座的方法，调用之后返回pnr及其他相关信息
	 * 
	 * @param pname
	 *            旅客姓名， 可以多个
	 * @param airNo
	 *            航班号
	 * @param fltClass
	 *            舱位等级，是字符类型
	 * @param orgCity
	 *            起飞城市
	 * @param desCity
	 *            目的城市
	 * @param departureTime
	 *            起飞日期
	 * @param idType
	 *            证件类型，与旅客姓名一一对应，且顺序一致
	 * @param idNo
	 *            证件号，与旅客姓名一一对应，且顺序一致
	 * @param contactinfo
	 *            联系信息，与旅客姓名一一对应，且顺序一致
	 * @return 返回pnrresult对象，此对象三个属性，分别是pnr号，航段数量，航段信息数组
	 */
	@Override
	public PnrResult createPNR(String[] pname, String airNo, char fltClass,
			String orgCity, String desCity, Date departureTime,
			String[] idType, String[] idNo, String[] contactinfo) {
		CreateIBEPNRImplService ibeService = new CreateIBEPNRImplService();
		CreateIBEPNR port = ibeService.getCreateIBEPNRImplPort();

		java.util.List<java.lang.String> pnames = new ArrayList<String>();
		java.util.List<java.lang.String> idtypes = new ArrayList<String>();
		java.util.List<java.lang.String> idnos = new ArrayList<String>();
		java.util.List<java.lang.String> contactinfos = new ArrayList<String>();
		for (int icount = 0; icount < pname.length; icount++) {
			pnames.add(pname[icount]);
			idtypes.add(idType[icount]);
			idnos.add(idNo[icount]);
			contactinfos.add(contactinfo[icount]);
		}
		String sdepartureTime = DateUtil.date2Str(departureTime,
				"yyyy-MM-dd HH:mm:ss");
		// system.out.println("WEB sdepartureTime : " + sdepartureTime);
		PnrResult _createPNR__return = port.createPNR(pnames, airNo, fltClass,
				orgCity, desCity, sdepartureTime, idtypes, idnos, contactinfos);
		return _createPNR__return;
	}
	
	/**
	 * 根据票号查看票的状态，一个票号可能有多段行程，所以返回的是数组
	 * 
	 * @author 李荣春 2015年7月1日 09:29:51
	 * @param tno
	 * @return
	 */
	@Override
	public List<String> getTicketStatus(String tno) {
		GetIBEPNRFlightImplService pnrService = new GetIBEPNRFlightImplService();
		GetIBEPNRFlightImplDelegate port = pnrService
				.getGetIBEPNRFlightImplPort();
		List<String> str = port.getDETR(tno);
		return str;
	}

	/**
	 * 废票(当天出票当天退票，才能使用此方法)
	 * 
	 * @param pnr
	 * @return
	 */
	@Override
	public String voidETicket(String pnr, String tktno) {
		IBEvoidETicketImplService ibEvoidETicketImplService = new IBEvoidETicketImplService();
		IBEvoidETicketImplDelegate port = ibEvoidETicketImplService
				.getIBEvoidETicketImplPort();
		String res = port.voidETicket("1", tktno, "", pnr);
		return res;

	}

	/**
	 * 根据pnr提取出票组信息（出票时限）
	 * 
	 * @param pnr
	 * @return
	 */
	@Override
	public List<String> getIBEPNRTkt(String pnr) {
		GetIBEPNRTktImplService getIBEPNRTktImplService = new GetIBEPNRTktImplService();
		GetIBEPNRTktImplDelegate port = getIBEPNRTktImplService
				.getGetIBEPNRTktImplPort();
		List<String> res = port.getPNRTkt(pnr);
		return res;
	}

	/**
	 * 根据pnr提取备注组信息(大客户编码)
	 * 
	 * @param pnr
	 * @return
	 */
	@Override
	public List<String> getIBEBigplait(String pnr) {
		GetIBEBigplaitImplService ibeBigplaitImplService = new GetIBEBigplaitImplService();
		GetIBEBigplaitImplDelegate port = ibeBigplaitImplService
				.getGetIBEBigplaitImplPort();
		List<String> res = port.getIBEBigplait(pnr);
		return res;

	}

	/**
	 * 根据Pnr提取Pnr的中的航段信息
	 * 
	 * @param pnr
	 * @return
	 */
	@Override
	public List<String> getPnrFlight(String pnr) {
		GetIBEPNRFlightImplService getIBEPNRFlightImplService = new GetIBEPNRFlightImplService();
		GetIBEPNRFlightImplDelegate port = getIBEPNRFlightImplService
				.getGetIBEPNRFlightImplPort();
		List<String> res = port.getPNRFlight(pnr);
		return res;
	}
	/**
	 * 自动出票调用出大客户协议价
	 */
	@Override
	public List<String> autoIssue(String customerCode, String pnr, Double price) {
		// IBEPNRAddPATA
		GetIBEPNRFlightImplService getIBEPNRFlightImplService = new GetIBEPNRFlightImplService();
		GetIBEPNRFlightImplDelegate port = getIBEPNRFlightImplService
				.getGetIBEPNRFlightImplPort();

		List<String> res = port.getPNRFlight(pnr);
		return res;
	}

	/**
	 * 根据Pnr提取Pnr的中的原始内容
	 * 
	 * @param pnr
	 * @return
	 */
	@Override
	public List<String> getPnr(String pnr) throws Exception {
		try {
			GetIBEPNRFlightImplService getIBEPNRFlightImplService = new GetIBEPNRFlightImplService();
			GetIBEPNRFlightImplDelegate port = getIBEPNRFlightImplService
					.getGetIBEPNRFlightImplPort();
			List<String> res = port.getOringinalRT(pnr);
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<String>();
		}

	}

	/**
	 * 根据Pnr提取Pnr的中的旅客信息
	 * 
	 * @param pnr
	 * @return
	 */
	@Override
	public List<String> getPnrMsg(String pnr) {
		GetIBEPNRMsgImplService getIBEPNRMsgImplService = new GetIBEPNRMsgImplService();
		GetIBEPNRMsgImplDelegate port = getIBEPNRMsgImplService
				.getGetIBEPNRMsgImplPort();
		List<String> res = port.getPNRMsg(pnr);
		return res;
	}

	/**
	 * 根据pnr提取票号组信息
	 * 
	 * @return
	 */
	@Override
	public List<String> getTicketNo(String pnr) {
		GetIBETicketNoImplService getIBETicketNoImplService = new GetIBETicketNoImplService();
		GetIBETicketNoImplDelegate port = getIBETicketNoImplService
				.getGetIBETicketNoImplPort();
		List<String> _GetTicketNo__return = port.getTicketNo(pnr);
		return _GetTicketNo__return;

	}

	/**
	 * 为PNR 注入运价
	 * 
	 * @param pnr
	 * @return
	 */
	@Override
	public List<String> pnrAddPATA(String pnr) {
		IBEPNRAddPATAImplService ibepnrAddPATAImplService = new IBEPNRAddPATAImplService();
		IBEPNRAddPATAImplDelegate port = ibepnrAddPATAImplService
				.getIBEPNRAddPATAImplPort();
		List<String> _IBEPNRAddPATA__return = port.pnrAddPata(pnr);
		return _IBEPNRAddPATA__return;
	}

	/**
	 * 一个订单一个PNR验价、自动出票
	 * 
	 * @param customerCode
	 * @param pnrno
	 * @param price
	 * @return
	 */
	@Override
	public List<String> autoIssueProcess(String customerCode, String pnrno,
			Double price) {
		IBEPNRAddPATAImplService ibepnrAddPATAImplService = new IBEPNRAddPATAImplService();
		IBEPNRAddPATAImplDelegate port = ibepnrAddPATAImplService
				.getIBEPNRAddPATAImplPort();
		List<String> _IBEPNRAddPATA__return = port.autoIssueProcess(customerCode,
				pnrno, price);
		return _IBEPNRAddPATA__return;
	}

	/**
	 * 一个订单 多个PNR验价用
	 * 
	 * @param customerCode
	 * @param pnrno
	 * @param price
	 * @return
	 */
	@Override
	public List<String> checkPriceOnly(String customerCode, String pnrno,
			Double price) {
		IBEPNRAddPATAImplService ibepnrAddPATAImplService = new IBEPNRAddPATAImplService();
		IBEPNRAddPATAImplDelegate port = ibepnrAddPATAImplService
				.getIBEPNRAddPATAImplPort();
		List<String> _IBEPNRAddPATA__return = port.checkPriceOnly(customerCode,
				pnrno, price);
		return _IBEPNRAddPATA__return;
	}

	/**
	 * 做过验价的PNR出票用
	 * 
	 * @param pnrnos
	 * @return
	 */
	@Override
	public List<String> rrAndIssueIBETickeForAuto(List<String> pnrnos) {
		IBEPNRAddPATAImplService ibepnrAddPATAImplService = new IBEPNRAddPATAImplService();
		IBEPNRAddPATAImplDelegate port = ibepnrAddPATAImplService
				.getIBEPNRAddPATAImplPort();
		List<String> _IBEPNRAddPATA__return = port.rrAndIssueIBETickeForAuto(pnrnos);
		return _IBEPNRAddPATA__return;
	}
	

	/**
	 * 根据传入的票号进行退票操作
	 * 
	 * @param tktno
	 * @return
	 */
	@Override
	public String iBEQuitTicket(String tktno) {
		IBEQuitTicketImplService quitTicketservice = new IBEQuitTicketImplService();
		IBEQuitTicketImplDelegate port = quitTicketservice
				.getIBEQuitTicketImplPort();
		int printno = 1;
		TrfdResult trfdResult = port.automaticRefund(tktno, printno, "D");
		String _IBEQuitTicket__return = port.confirmAutomaticRefund(trfdResult,
				tktno, printno, "D");
		return _IBEQuitTicket__return;
	}
	
	@Override
    public TrfdResult automaticRefund(String tktno) {
        IBEQuitTicketImplService quitTicketservice = new IBEQuitTicketImplService();
        IBEQuitTicketImplDelegate port = quitTicketservice
                .getIBEQuitTicketImplPort();
        int printno = 1;
        TrfdResult trfdResult = port.automaticRefund(tktno, printno, "D");
        return trfdResult;
    }

	@Override
    public String confirmAutomaticRefund(String tktno,TrfdResult trfdResult ) {
        IBEQuitTicketImplService quitTicketservice = new IBEQuitTicketImplService();
        IBEQuitTicketImplDelegate port = quitTicketservice.getIBEQuitTicketImplPort();
        int printno = 1;
        String _IBEQuitTicket__return = port.confirmAutomaticRefund(trfdResult,
                tktno, printno, "D");
        return _IBEQuitTicket__return;
    }
	/**
	 * 根据传入的pnr进行出票操作
	 * 
	 * @param strPNR
	 * @return
	 */
	@Override
	public String sellIBETicket(String strPNR) {
		IBETicketImplService ticketservice = new IBETicketImplService();
		IBETicketImplDelegate port = ticketservice.getIBETicketImplPort();
		int printno = 1;
		String _sellIBETicket__return = port.sellIBETicket(strPNR, printno);
		return _sellIBETicket__return;
	}

	/**
	 * 根据pnr取消座位
	 * 
	 * @param pnr
	 * @return
	 */
	public String deletePNR(String pnr) {
		DeleteIBEPNRImplService ibeService = new DeleteIBEPNRImplService();
		DeleteIBEPNRImplDelegate port = ibeService.getDeleteIBEPNRImplPort();
		String _deletePNR__return = port.deletePNR(pnr);
		return _deletePNR__return;

	}
	
	/**
     * 改期：这里指不需要换开票面的改升操作。
     * @param pnrno
     * @param fltnoold  "sc4651"
     * @param dateold  "21sep"
     * @param fltnonew  "sc4651"
     * @param datenew  "23sep"
     * @return
     */
    public String changeDate(String pnrno, String fltnoold, String dateold, String fltnonew, String datenew){
        IBETicketImplService ticketservice = new IBETicketImplService();
        IBETicketImplDelegate port = ticketservice.getIBETicketImplPort();
        String _sellIBETicket__return = port.changeDate(pnrno, fltnoold, dateold, fltnonew, datenew);
        return _sellIBETicket__return;
    }
    
    /**
     * 升舱：换开票面的改升操作
     * @param pnr
     * @param airsegStrs
     * @return
     */
    public String changePnr(String pnr,List<String> airsegStrs){
        IBETicketImplService ticketservice = new IBETicketImplService();
        IBETicketImplDelegate port = ticketservice.getIBETicketImplPort();
        String _sellIBETicket__return = port.changePnr(pnr, airsegStrs);
        return _sellIBETicket__return;
    }
    
    
    /**
     * 改期升舱 以下只针对单程和往返客票，即旅客只有一张客票的情况下。
     * 首先确认是否需要分离PNR，多旅客（成人、儿童）PNR中部分旅客改期升舱情况下使用。
     * @param pnr
     * @param name
     * @param isAdult
     * @return   null 表示 分离失败
     */
    public String splitPnrByName(String pnr,String name,boolean isAdult){
        IBETicketImplService ticketservice = new IBETicketImplService();
        IBETicketImplDelegate port = ticketservice.getIBETicketImplPort();
        String _sellIBETicket__return = port.splitPnrByName(pnr, name, isAdult);
        return _sellIBETicket__return;
    }
    
}
