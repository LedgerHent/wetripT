package com.viptrip.ibeserver.service;

import java.util.Date;
import java.util.List;

import com.viptrip.wetrip.wsclient.IBEQuitTicketImplDelegate;
import com.viptrip.wetrip.wsclient.IBEQuitTicketImplService;
import com.viptrip.wetrip.wsclient.PnrResult;
import com.viptrip.wetrip.wsclient.TrfdResult;

/**
 * 与IBE交互相关接口 整理自VIPTRIP365项目
 * 
 * @author wjt
 * 
 */
public interface ReadIBEManagerService {
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
	public PnrResult createConnectingPNR(String[] pname, String[] airNo,
			char[] fltClass, String[] orgCity, String[] desCity,
			Date[] departureTime, String[] idType, String[] idNo,
			String[] contactinfo);
	
	
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
	public PnrResult createPNR(String[] pname, String airNo, char fltClass,
			String orgCity, String desCity, Date departureTime,
			String[] idType, String[] idNo, String[] contactinfo);
	
	/**
	 * 根据票号查看票的状态，一个票号可能有多段行程，所以返回的是数组
	 * 
	 * @author 李荣春 2015年7月1日 09:29:51
	 * @param tno
	 * @return
	 */
	public List<String> getTicketStatus(String tno);

	/**
	 * 废票(当天出票当天退票，才能使用此方法)
	 * 
	 * @param pnr
	 * @return
	 */
	public String voidETicket(String pnr, String tktno);

	/**
	 * 根据pnr提取出票组信息（出票时限）
	 * 
	 * @param pnr
	 * @return
	 */
	public List<String> getIBEPNRTkt(String pnr);

	/**
	 * 根据pnr提取备注组信息(大客户编码)
	 * 
	 * @param pnr
	 * @return
	 */
	public List<String> getIBEBigplait(String pnr);

	/**
	 * 根据Pnr提取Pnr的中的航段信息
	 * 
	 * @param pnr
	 * @return
	 */
	public List<String> getPnrFlight(String pnr);

	public List<String> autoIssue(String customerCode, String pnr, Double price);

	/**
	 * 根据Pnr提取Pnr的中的原始内容
	 * 
	 * @param pnr
	 * @return
	 */
	public List<String> getPnr(String pnr) throws Exception ;

	/**
	 * 根据Pnr提取Pnr的中的旅客信息
	 * 
	 * @param pnr
	 * @return
	 */
	public List<String> getPnrMsg(String pnr) ;

	/**
	 * 根据pnr提取票号组信息
	 * 
	 * @return
	 */
	public List<String> getTicketNo(String pnr);
	/**
	 * 为PNR 注入运价
	 * 
	 * @param pnr
	 * @return
	 */
	public List<String> pnrAddPATA(String pnr);
	/**
	 * 一个订单一个PNR验价、自动出票
	 * 
	 * @param customerCode
	 * @param pnrno
	 * @param price
	 * @return
	 */
	public List<String> autoIssueProcess(String customerCode, String pnrno,
			Double price) ;

	/**
	 * 一个订单 多个PNR验价用
	 * 
	 * @param customerCode
	 * @param pnrno
	 * @param price
	 * @return
	 */
	public List<String> checkPriceOnly(String customerCode, String pnrno,
			Double price);

	/**
	 * 做过验价的PNR出票用
	 * 
	 * @param pnrnos
	 * @return
	 */
	public List<String> rrAndIssueIBETickeForAuto(List<String> pnrnos) ;

	/**
	 * 根据传入的票号进行退票操作
	 * 
	 * @param tktno
	 * @return
	 */
	public String iBEQuitTicket(String tktno) ;
	
	/**
     * 国内自动退票，第一步：查看自动生成的退票单（不允许修改）.
     * @param tktno  票号，如999-1234567890
     * @return TRFDResult退票单对象
     */
	public TrfdResult automaticRefund(String tktno) ;
	
	/**
     * 国内自动退票，第二步：确认自动生成的退票单无误后提交该退票，生成退票单号.
     * @param result_pre 第一步自动生成的退票单对象
     * @param tktno 13位票号
     * @return 返回结果为退票单号
     */
	public String confirmAutomaticRefund(String tktno,TrfdResult trfdResult);
	
	/**
	 * 根据传入的pnr进行出票操作
	 * 
	 * @param strPNR
	 * @return
	 */
	public String sellIBETicket(String strPNR) ;
	/**
	 * 根据pnr取消座位
	 * 
	 * @param pnr
	 * @return
	 */
	public String deletePNR(String pnr);
	
	
    /**
     * 改期：这里指不需要换开票面的改升操作。
     * @param pnrno
     * @param fltnoold  "sc4651"
     * @param dateold  "21sep"
     * @param fltnonew  "sc4651"
     * @param datenew  "23sep"
     * @return
     */
    public String changeDate(String pnrno, String fltnoold, String dateold, String fltnonew, String datenew);
    
    /**
     * 升舱：换开票面的改升操作
     * @param pnr
     * @param airsegStrs  arg1=[oldorgcity_olddescity_newairno_newfletclass_newPrice_newTaxFee_newFuelTax_flightDate_oldPrice]
     * @return
     */
    public String changePnr(String pnr,List<String> airsegStrs);
    
    
    /**
     * 改期升舱 以下只针对单程和往返客票，即旅客只有一张客票的情况下。
     * 首先确认是否需要分离PNR，多旅客（成人、儿童）PNR中部分旅客改期升舱情况下使用。
     * @param pnr
     * @param name
     * @param isAdult
     * @return   null 表示 分离失败
     */
    public String splitPnrByName(String pnr,String name,boolean isAdult);
    

}