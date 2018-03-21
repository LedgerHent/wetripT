package com.viptrip.wetrip.util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.ibeserver.model.DispplayTrip;
import com.viptrip.util.DateUtil;
import com.viptrip.util.StringUtil;
import com.viptrip.wetrip.dao.ext.ComDao;
import com.viptrip.wetrip.entity.TEndorse;
import com.viptrip.wetrip.entity.TNotifyPartyInformation;
import com.viptrip.wetrip.entity.TOrder;
import com.viptrip.wetrip.entity.TPnr;
import com.viptrip.wetrip.entity.TTicketRefund;
import com.viptrip.wetrip.entity.TTravelItinerary;
import com.viptrip.wetrip.entity.TUpdateDate;
import com.viptrip.wetrip.model.employees.Auditor;
import com.viptrip.wetrip.model.flight.ReqData_BookAirTicket_Contact;
import com.viptrip.wetrip.model.flight.ReqData_GetFlightList_TripInfo;
import com.viptrip.wetrip.model.flight.RespData_GetFlightDetail;
import com.viptrip.wetrip.model.flight.RespData_GetFlightDetail_Cabin;
import com.viptrip.wetrip.model.flight.RespData_GetFlightDetail_Stop;
import com.viptrip.wetrip.model.flight.RespData_GetFlightDetail_TicketRule;
import com.viptrip.wetrip.model.flight.RespData_GetFlightList;
import com.viptrip.wetrip.model.orderlist.RespData_GetOrderDetail;
import com.viptrip.wetrip.model.orderlist.RespData_GetOrderDetail_Change;
import com.viptrip.wetrip.model.orderlist.RespData_GetOrderDetail_Flight;
import com.viptrip.wetrip.model.orderlist.RespData_GetOrderDetail_Passenger;
import com.viptrip.wetrip.model.orderlist.RespData_GetOrderDetail_Refund;
import com.viptrip.wetrip.model.orderlist.RespData_GetOrderDetail_Ticket;
import com.viptrip.wetrip.model.orderlist.RespData_GetOrderList;
import com.viptrip.wetrip.model.orderlist.RespData_GetOrderList_Flight;

public class ConvertorFactory {
	private static Logger logger = LoggerFactory.getLogger(ConvertorFactory.class);
	private static ConvertorFactory instance;
	@SuppressWarnings("rawtypes")
	private Map<String,Convertor> map = new HashMap<>();
	
	private ConvertorFactory(){}
	
	/**
	 * 获取工厂
	 * @return
	 */
	public static ConvertorFactory instance(){
		if(null==instance){
			synchronized (ConvertorFactory.class) {
				if(null==instance){
					instance = new ConvertorFactory();
				}
			}
		}
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	public <F,T> Convertor<F, T> getConvertor(Class<F> fclz,Class<T> tclz){
		Convertor<F, T> result = null;
		if(null!=fclz && null!=tclz){
			String key = fclz.getName() + "." + tclz.getName();
			Convertor<?, ?> convertor = map.get(key);
			if(null == convertor){
				if(DispplayTrip.class==fclz && RespData_GetFlightList.class==tclz){//DispplayTrip→RespData_GetFlightList转换器
					convertor = new Convertor<DispplayTrip, RespData_GetFlightList>() {
						
						@Override
						public RespData_GetFlightList convert(DispplayTrip from) {
							if(null!=from){
								RespData_GetFlightList result = new RespData_GetFlightList();
								result.setMapKey(from.getMapKey());//设置mapkey
								result.setFlightNo(from.getAirline());//航班号
								result.setAirline(from.getAirline().substring(0,2));//航空公司二字码
								if(!StringUtil.isEmpty(from.getAirlineshare())){
									result.setShareFlightNo(from.getAirlineshare());//共享航班号
									result.setShareAirline(from.getAirlineshare().substring(0, 2));//共享航空公司二字码
								}
								result.setDepDateTime(from.getStartdate());//起飞日期时间
								result.setArrDateTime(from.getArrvidate());//到达日期时间
								result.setDepAirport(from.getOrgcity());//起飞城市
								result.setArrAirport(from.getDetcity());//到达城市
								result.setDepPointAT(from.getOrgterm());//起飞航站楼
								result.setArrPointAT(from.getDetterm());//到达航站楼
								result.setPrice(from.getPrice());//价格
								result.setRebatePrice(from.getRebatePrice());//优惠价格
								result.setDiscount(from.getDiscountrate());//折扣
								result.setCabin(from.getCangwei());//仓位代码
								result.setCabinName(from.getCangweiDesc());//仓位名称
								String seatleft = from.getSeatsLeft();//剩余票量
								if("大于9".equalsIgnoreCase(seatleft)){
									result.setRemain(10);
								}else{
									try {
										Integer r = Integer.parseInt(seatleft);
										result.setRemain(r);
									} catch (NumberFormatException e) {
										logger.error(StringUtil.getExceptionStr(e));
									}
								}
								result.setPlaneType(from.getPlanetype());//机型
								result.setStops(FlightUtil.countStops(from.getFfstr()));//经停次数
								result.setExcessinfo(from.getExcessinfo());//超标类型
								result.setShowRule(from.getShowRule());//
								return result;
							}
							return null;
						}
					};
					map.put(key, convertor);
					result = (Convertor<F, T>) convertor;
				}
				if(List.class==fclz && RespData_GetFlightDetail.class==tclz){//List<DispplayTrip>→RespData_GetFlightDetail转换器
					convertor = new Convertor<List<DispplayTrip>, RespData_GetFlightDetail>() {
						
						@Override
						public RespData_GetFlightDetail convert(
								List<DispplayTrip> from) {
							// 转换数据
							if(null!=from && from.size()>0){
								RespData_GetFlightDetail result = new RespData_GetFlightDetail();
								DispplayTrip dt = from.get(0);
								result.setFlightNo(dt.getAirline());//航班号
								result.setAirlineCode(dt.getAirline().substring(0,2));//航空公司二字码
								if(!StringUtil.isEmpty(dt.getAirlineshare())){
									result.setShareFlightNo(dt.getAirlineshare());//共享航班号
									result.setShareAirlineCode(dt.getAirlineshare().substring(0, 2));//共享航空公司二字码
								}
								result.setDepDateTime(dt.getStartdate());//起飞日期时间
								result.setArrDateTime(dt.getArrvidate());//到达日期时间
								result.setDepAirport(dt.getOrgcity());//起飞城市
								result.setArrAirport(dt.getDetcity());//到达城市
								result.setDepPointAT(dt.getOrgterm());//起飞航站楼
								result.setArrPointAT(dt.getDetterm());//到达航站楼
								result.setAirportTax(dt.getTaxfee());//机场建设费
								result.setFuelSurTax(dt.getFueltax());//燃油费
								result.setPlaneType(dt.getPlanetype());//机型
								List<RespData_GetFlightDetail_Cabin> cabins = new ArrayList<RespData_GetFlightDetail_Cabin>();
								List<RespData_GetFlightDetail_Stop> stops = new ArrayList<>();
								List<TEndorse> endorse = CommUtil.getEndorse(dt.getAirline().substring(0,2), DateUtil.SDF_yyyyMMdd.format(dt.getStartdate()), DateUtil.P_yyyyMMdd);//获取航司票规
								for(DispplayTrip dtrip:from){
									RespData_GetFlightDetail_Cabin cabin = new RespData_GetFlightDetail_Cabin();
									RespData_GetFlightDetail_Stop stop = null;
									cabin.setExcessinfo(dtrip.getExcessinfo());//超标类型
                                    cabin.setShowRule(dtrip.getShowRule());//
									cabin.setMapKey(dtrip.getMapKey());//设置mapkey
									cabin.setPrice(dtrip.getPrice());//价格
									cabin.setRebatePrice(dtrip.getRebatePrice());//优惠价格
									cabin.setPriceSource(dtrip.getAgreementTypeCode());//价格来源
									cabin.setDiscount(dtrip.getDiscountrate());//折扣
//									cabin.setCabinType();//
									cabin.setCabin(dtrip.getCangwei());//舱位代码
//									cabin.setCabinName();
									String seatleft = dtrip.getSeatsLeft();//剩余票量
									
									if(null!=endorse && endorse.size()>0){//设置票规
										B:for(TEndorse eds:endorse){
											if(dtrip.getCangwei().equals(eds.getCangwei())){
												RespData_GetFlightDetail_TicketRule ticketRule = new RespData_GetFlightDetail_TicketRule();
//												ticketRule.setChange(eds.getEndorsement());
												ticketRule.setEndorsement(eds.getEndorsement());
												ticketRule.setRefund(eds.getRefund());
												cabin.setTicketRule(ticketRule);
												break B;
											}
										}
									}
									
									if("大于9".equalsIgnoreCase(seatleft)){
										cabin.setRemain(10);
									}else{
										try {
											Integer r = Integer.parseInt(seatleft);
											cabin.setRemain(r);
										} catch (NumberFormatException e) {
											logger.error(StringUtil.getExceptionStr(e));
										}
									}
									//以下为经停信息
									String ffstr = dtrip.getFfstr();// 格式为：抵达城市三字码_抵达时间yyyyMMdd HH:mm_出发时间yyyyMMdd HH:mm
									if(!StringUtil.isEmpty(ffstr)){
										stop =new RespData_GetFlightDetail_Stop();
										String[] strs = ffstr.split("_");
										if(null!=strs && strs.length==3){
											stop.setAirportCode(strs[0]);
											String arrtimeStr = strs[1];
											String deptimeStr = strs[2];
											try {
												Date arrtime = DateUtil.parse(arrtimeStr, "yyyyMMdd HH:mm");
												Date deptime = DateUtil.parse(deptimeStr, "yyyyMMdd HH:mm");
												stop.setArrDateTime(arrtime);
												stop.setDepDateTime(deptime);
											} catch (ParseException e) {
												logger.error(StringUtil.getExceptionStr(e));
											}
										}
									}
									
									cabins.add(cabin);
									stops.add(stop);
								}
								result.setCabins(cabins);
								result.setStops(stops);
								
								return result;
							}
							return null;
						}
					};
					map.put(key, convertor);
					result = (Convertor<F, T>) convertor;
				}
				if(TOrder.class==fclz && RespData_GetOrderList.class==tclz){//TOrder→RespData_GetOrderList转换器
					convertor = new Convertor<TOrder, RespData_GetOrderList>() {

						@Override
						public RespData_GetOrderList convert(TOrder from) {
							if(null!=from){
								RespData_GetOrderList result = new RespData_GetOrderList();
								result.setOrderId(from.getOId());//订单id
								result.setOrderNo(from.getOrderno());//订单号
								result.setAmount(from.getTotalPrice());//总价格
								result.setStatus(from.getOrderStatus());//状态
//								result.setSurplusPayTime(null);//剩余支付时间
								result.setSurplusPayTime(DateUtil.getCountDownTimeStr(from.getSubscribeDate(), 15*60));//剩余支付时间  15分钟内
								
								String processstate = from.getProcessstate();//1-单程  2-往返  3-联程
								result.setTripType(Integer.parseInt(processstate));//行程类型
								
								List<ReqData_GetFlightList_TripInfo> tripInfo = null;
								List<RespData_GetOrderList_Flight> flights = null;
								Set<TPnr> pnrs = from.getTPnrs();
								if(null!=pnrs && pnrs.size()>0){
									tripInfo = new ArrayList<ReqData_GetFlightList_TripInfo>();
									flights = new ArrayList<RespData_GetOrderList_Flight>();
									for(TPnr pnr:pnrs){
										ReqData_GetFlightList_TripInfo trip = new ReqData_GetFlightList_TripInfo(Integer.parseInt(pnr.getFlighttype()), pnr.getOriginCity(), pnr.getDestinationCity(), DateUtil.SDF_yyyyOMMOdd.format(pnr.getFlightDate()));
										Integer flowId = null;
										if("2".equals(processstate) || "3".equals(processstate)){//如果是往返或者联程
											if(Integer.parseInt(pnr.getFlighttype())==2){// 说明是返程
												flowId = -1;
											}else{
												flowId = Integer.parseInt(pnr.getFlighttype());
											}
										}else{
											flowId = 0;
//											flowId = Integer.parseInt(pnr.getFlighttype());
										}
										RespData_GetOrderList_Flight flight = new RespData_GetOrderList_Flight(flowId, pnr.getAirline(), pnr.getAirlineCompany(), pnr.getAirlineshare(), StringUtil.isEmpty(pnr.getAirlineshare())?"":pnr.getAirlineshare().substring(0, 2), pnr.getOriginTime(), pnr.getDestinationTime(), pnr.getOriginCity(), pnr.getDestinationCity());
										tripInfo.add(trip);
										flights.add(flight);
									}
								}
								
								result.setTripInfo(tripInfo);//设置行程信息
								result.setFlights(flights);//设置航班信息
								return result;
							}
							return null;
						}
					};
					map.put(key, convertor);
					result = (Convertor<F, T>) convertor;
				}
				if(TOrder.class==fclz && RespData_GetOrderDetail.class==tclz){ //TOrder→RespData_GetOrderDetail转换器
					convertor = new Convertor<TOrder, RespData_GetOrderDetail>() {

						@Override
						public RespData_GetOrderDetail convert(TOrder from) {
							if(null!=from){
								RespData_GetOrderDetail result = new RespData_GetOrderDetail();
								result.setOrderId(from.getOId());
								result.setOrderNo(from.getOrderno());
								result.setAmount(from.getTotalPrice());
								String processstate = from.getProcessstate();//1-单程  2-往返  3-联程
								result.setTripType(Integer.parseInt(processstate));//行程类型
								result.setPayMethod(from.getPayMethod());
								result.setStatus(from.getOrderStatus());
								result.setOrderDateTime(DateUtil.format(from.getSubscribeDate(), "yyyy-MM-dd HH:mm"));
								result.setBusinessType(Integer.parseInt(from.getTravelType()==null?"0":from.getTravelType()));//设置出行类型
								
								if(null!=from.getCheckordermenId()){//设置审核人信息
									Auditor auditor = new Auditor();
									auditor.setId(from.getCheckmenId().intValue());
									auditor.setName(from.getCheckordermen());
									auditor.setMobile(from.getChecktel());
									auditor.setEmail(from.getCheckemail());
									result.setAuditor(auditor);
								}
								
								List<ReqData_GetFlightList_TripInfo> tripInfo = null;
								List<RespData_GetOrderDetail_Flight> flights = null;
								List<RespData_GetOrderDetail_Passenger> passengers = new ArrayList<RespData_GetOrderDetail_Passenger>();
								List<ReqData_BookAirTicket_Contact> contacts = null;
								List<RespData_GetOrderDetail_Ticket> tickets = new ArrayList<RespData_GetOrderDetail_Ticket>();
								Set<TPnr> pnrs = from.getTPnrs();
								List<Long> ticketId = null; //存放机票id
								
								Double fee = 0D;
								if(null!=pnrs && pnrs.size()>0){
									tripInfo = new ArrayList<ReqData_GetFlightList_TripInfo>();
									flights = new ArrayList<RespData_GetOrderDetail_Flight>();
									ticketId = new ArrayList<Long>();
									boolean passengerSetFlag = false;//passenger是否已经设置
									for(TPnr pnr:pnrs){
										ReqData_GetFlightList_TripInfo trip = new ReqData_GetFlightList_TripInfo(Integer.parseInt(pnr.getFlighttype()), pnr.getOriginCity(), pnr.getDestinationCity(), DateUtil.SDF_yyyyOMMOdd.format(pnr.getFlightDate()));
										tripInfo.add(trip);
										RespData_GetOrderDetail_Flight flight = buildFlight(processstate, pnr, null);
										/*List<TEndorse> endorse = CommUtil.getEndorse(pnr.getAirlineCompany(), DateUtil.SDF_yyyyMMdd.format(pnr.getOriginTime()), DateUtil.P_yyyyMMdd);//获取航司票规
										Integer flowId = null;
										if("2".equals(processstate)){//如果是联程
											if(Integer.parseInt(pnr.getFlighttype())==2){// 说明是返程
												flowId = -1;
											}else{
												flowId = Integer.parseInt(pnr.getFlighttype());
											}
										}else{
											flowId = Integer.parseInt(pnr.getFlighttype());
										}
										
										if("2".equals(processstate) || "3".equals(processstate)){//如果是往返或者联程
											if(Integer.parseInt(pnr.getFlighttype())==2){// 说明是返程
												flowId = -1;
											}else{
												flowId = Integer.parseInt(pnr.getFlighttype());
											}
										}else{
											flowId = 0;
//											flowId = Integer.parseInt(pnr.getFlighttype());
										}
										
										RespData_GetOrderDetail_Flight flight = new RespData_GetOrderDetail_Flight();
										flight.setFlowId(flowId);//航班顺序号
										//new RespData_GetOrderList_Flight(flowId, pnr.getAirline(), pnr.getAirlineCompany(), pnr.getAirlineshare(), StringUtil.isEmpty(pnr.getAirlineshare())?"":pnr.getAirlineshare().substring(0, 2), pnr.getOriginTime(), pnr.getDestinationTime(), pnr.getOriginCity(), pnr.getDestinationCity());
										flight.setFlightNo(pnr.getAirline());//航班号
										flight.setAirline(pnr.getAirlineCompany());//航司二字码
										flight.setShareAirline(StringUtil.isEmpty(pnr.getAirlineshare())?"":pnr.getAirlineshare().substring(0, 2));//共享航司二字码
										flight.setShareflightNo(pnr.getAirlineshare());//共享航班号
										flight.setPlaneType(pnr.getPlanetype());//机型
										flight.setCabinCode(pnr.getCangwei());//舱位代码
//										flight.setCabinName();//舱位名称
										flight.setPrice(pnr.getDiscountPrice());//原价
										flight.setRebatePrice(pnr.getDiscountPrice());//票价优惠价
//										flight.setDiscount();//折扣
										flight.setAirportTax(pnr.getTaxPrice());//机场建设费
										flight.setFuelSurTax(pnr.getFueltax());//燃油费
										flight.setDepAirport(pnr.getOriginCity());//起飞机场
										flight.setArrAirport(pnr.getDestinationCity());//到达机场
										flight.setDepPointAT(pnr.getOrgterm());//出发航站楼
										flight.setArrPointAT(pnr.getDetterm());//到达航站楼
										flight.setDepDateTime(pnr.getOriginTime());//出发日期时间
										flight.setArrDateTime(pnr.getDestinationTime());//到达日期时间
										//以下设置票规
										RespData_GetFlightDetail_TicketRule ticketRule = null;
										if(null!=endorse && endorse.size()>0){
											B:for(TEndorse eds:endorse){
												if(pnr.getCangwei().equals(eds.getCangwei())){
													ticketRule = new RespData_GetFlightDetail_TicketRule();
//													ticketRule.setChange(eds.getEndorsement());
													ticketRule.setEndorsement(eds.getEndorsement());
													ticketRule.setRefund(eds.getRefund());
													flight.setTicketRule(ticketRule);//设置票规
													break B;
												}
											}
										}
										//以下设置经停信息
										String ffstr = pnr.getFfstr();// 格式为：抵达城市三字码_抵达时间yyyyMMdd HH:mm_出发时间yyyyMMdd HH:mm
										if(!StringUtil.isEmpty(ffstr)){
											List<RespData_GetFlightDetail_Stop> stops = new ArrayList<RespData_GetFlightDetail_Stop>();
											RespData_GetFlightDetail_Stop stop =new RespData_GetFlightDetail_Stop();
											String[] strs = ffstr.split("_");
											if(null!=strs && strs.length==3){
												stop.setAirportCode(strs[0]);
												String arrtimeStr = strs[1];
												String deptimeStr = strs[2];
												try {
													Date arrtime = DateUtil.parse(arrtimeStr, "yyyyMMdd HH:mm");
													Date deptime = DateUtil.parse(deptimeStr, "yyyyMMdd HH:mm");
													stop.setArrDateTime(arrtime);
													stop.setDepDateTime(deptime);
												} catch (ParseException e) {
													logger.error(StringUtil.getExceptionStr(e));
												}
											}
											flight.setStops(stops);
										}*/
										flights.add(flight);
										
										Set<TTravelItinerary> tTravelItineraries = pnr.getTTravelItineraries();
										//以下为设置乘客信息
										if("1".equals(pnr.getFlighttype())){//取第一程的信息即可 其他程的信息与第一程一致
											if(null!=tTravelItineraries && tTravelItineraries.size()>0){
												boolean flag = true; 
												for(TTravelItinerary tt:tTravelItineraries){
													
													if(!passengerSetFlag){
														RespData_GetOrderDetail_Passenger pa = buildPassenger(tt);
														/*RespData_GetOrderDetail_Passenger pa = new RespData_GetOrderDetail_Passenger();
//														pa.setId(id)//顺序号
														pa.setName(tt.getPassengerName());//姓名
														pa.setMobile(tt.getPassengerTel());//手机号
														pa.setEmail(tt.getPassengerEmail());//邮箱
														pa.setIdType(Integer.parseInt(tt.getPassengerIdtype()));//证件类型
														pa.setIdNum(tt.getParentorgid());//证件号码
//														pa.setCostCenter(tt.getProjectid());//成本中心
														pa.setProjectNo(tt.getProjectid());//项目号
														pa.setInsuranceId(tt.getInsuranceId());//保险id
														pa.setInsuranceNum(tt.getAssuranceNum().intValue());//保险数量
														pa.setInsurancePrice(tt.getAssurancePrice());//保险单价
*/														passengers.add(pa);//
													}else{
														for(RespData_GetOrderDetail_Passenger p:passengers){
															if(p.getName().equals(tt.getPassengerName())){
																Double price1 = p.getInsuranceNum() * p.getInsurancePrice();
																Double price2 = tt.getAssuranceNum().intValue() * tt.getAssurancePrice();
																p.setInsurancePrice(price1 + price2);
															}
														}
													}
													if(flag){
														RespData_GetOrderDetail_Ticket ticket = buildTicket(tt, null,null);
														/*RespData_GetOrderDetail_Ticket ticket = new RespData_GetOrderDetail_Ticket();
														ticket.setAirportTax(tt.getTaxPrice());
//														ticket.setChildBabyBookCabin(null);
														ticket.setFlightNo(tt.getFlightNumber());
														ticket.setFuelSurTax(tt.getFueltax());
//														ticket.setPassengerId(null);
														ticket.setPnr(tt.getTPnr().getPnr());
														ticket.setPrice(tt.getTicketPrice());
														ticket.setRebatePrice(tt.getTicketPrice());
														ticket.setStatus(tt.getFlightStatus());
//														ticket.setTicketNo(null);
*/														
														tickets.add(ticket);
														//flag = false;
													}
													
												}
											}
											passengerSetFlag = true;
										}
										if("2".equals(pnr.getFlighttype())){//第二程
											if(null!=tTravelItineraries && tTravelItineraries.size()>0){
												boolean flag = true; 
												for(TTravelItinerary tt:tTravelItineraries){
													if(!passengerSetFlag){
														RespData_GetOrderDetail_Passenger pa = buildPassenger(tt);
														/*RespData_GetOrderDetail_Passenger pa = new RespData_GetOrderDetail_Passenger();
//														pa.setId(id)//顺序号
														pa.setName(tt.getPassengerName());//姓名
														pa.setMobile(tt.getPassengerTel());//手机号
														pa.setEmail(tt.getPassengerEmail());//邮箱
														pa.setIdType(Integer.parseInt(tt.getPassengerIdtype()));//证件类型
														pa.setIdNum(tt.getParentorgid());//证件号码
//														pa.setCostCenter(tt.getProjectid());//成本中心
														pa.setProjectNo(tt.getProjectid());//项目号
														pa.setInsuranceId(tt.getInsuranceId());//保险id
														pa.setInsuranceNum(tt.getAssuranceNum().intValue());//保险数量
														pa.setInsurancePrice(tt.getAssurancePrice());//保险单价
*/														passengers.add(pa);//
													}else{
														for(RespData_GetOrderDetail_Passenger p:passengers){
															if(p.getName().equals(tt.getPassengerName())){
																Double price1 = p.getInsuranceNum() * p.getInsurancePrice();
																Double price2 = tt.getAssuranceNum().intValue() * tt.getAssurancePrice();
																p.setInsurancePrice(price1 + price2);
															}
														}
													}
													if(flag){
														RespData_GetOrderDetail_Ticket ticket = buildTicket(tt, null,null);
														/*RespData_GetOrderDetail_Ticket ticket = new RespData_GetOrderDetail_Ticket();
														ticket.setAirportTax(tt.getTaxPrice());
//														ticket.setChildBabyBookCabin(null);
														ticket.setFlightNo(tt.getFlightNumber());
														ticket.setFuelSurTax(tt.getFueltax());
//														ticket.setPassengerId(null);
														ticket.setPnr(tt.getTPnr().getPnr());
														ticket.setPrice(tt.getTicketPrice());
														ticket.setRebatePrice(tt.getTicketPrice());
														ticket.setStatus(tt.getFlightStatus());
//														ticket.setTicketNo(null);
*/														tickets.add(ticket);
														//flag = false;
													}
													
												}
											}
											passengerSetFlag = true;
										}
										
										Set<TTravelItinerary> set = pnr.getTTravelItineraries();
										for (Iterator<TTravelItinerary> iterator = set.iterator(); iterator
												.hasNext();) {
											TTravelItinerary t = (TTravelItinerary) iterator
													.next();
											
											ticketId.add(t.getTId());//添加机票id
											
										}
										
									}
									
								}
								
								//以下设置联系方式
								Long oId = from.getOId();
								if(oId != null){
									List<TNotifyPartyInformation> list = CommUtil.getContactList(oId,0);
									if(null!=list && list.size()>0){
										contacts = new ArrayList<ReqData_BookAirTicket_Contact>();
										for(TNotifyPartyInformation ti:list){
											ReqData_BookAirTicket_Contact contact = new ReqData_BookAirTicket_Contact();
											contact.setId(ti.getNotifyId());
											contact.setEmail(ti.getMail());
											contact.setMobile(ti.getPassengerTel());
											contact.setName(ti.getNotifyName());
											contacts.add(contact);
										}
									}
								}
								
								
								
								result.setTripInfo(tripInfo);//设置行程信息
								result.setFlights(flights);//设置航班信息
								result.setPassengers(passengers);//乘客信息
								result.setContacts(contacts);//联系人
//								tickets.get(0).setServiceFee(fee);
								result.setTickets(tickets);//机票信息
								
								//以下为设置改期以及退票信息
								List<RespData_GetOrderDetail_Change> changes = new ArrayList<>();
								List<RespData_GetOrderDetail_Refund> refunds = new ArrayList<>();
								if(ticketId!=null&&ticketId.size()>0){
									Map<String, Object> conditions = new HashMap<>();
									conditions.put("tids", ticketId);
									
									ComDao comDao =  ApplicationContextHelper.getInstance().getBean(ComDao.class);
									
									String hql = " from TUpdateDate where TId in (:tids) order by UId asc";
									List<TUpdateDate> tuds = comDao.queryWithInCondition(hql, conditions);
									if(null!=tuds && tuds.size()>0){
										//设置改期信息
										int count = 0;
										for (Iterator<TUpdateDate> iterator2 = tuds
												.iterator(); iterator2
												.hasNext();) {
											TUpdateDate tUpdateDate = (TUpdateDate) iterator2
													.next();
											List<RespData_GetOrderDetail_Flight> fList = new ArrayList<RespData_GetOrderDetail_Flight>();
											List<RespData_GetOrderDetail_Ticket> ticketList = new ArrayList<RespData_GetOrderDetail_Ticket>();
											RespData_GetOrderDetail_Flight f = null;
											String hql2 = "select pnr from TPnr pnr,TUpdateDate tud,TTravelItinerary tti where tud.UId=? and tud.TId=tti.TId and pnr.pnrId=tti.TPnr.pnrId";
											List<TPnr> pnrList = comDao.queryForList(hql2, new Object[]{tUpdateDate.getUId()});
											if(null!=pnrList && pnrList.size()>0){
												TPnr tPnr = pnrList.get(0);
												f = buildFlight(processstate, tPnr, tUpdateDate);
												Set<TTravelItinerary> tTravelItineraries = tPnr.getTTravelItineraries();
												if(null!=tTravelItineraries&&tTravelItineraries.size()>0){
													for (Iterator<TTravelItinerary> iterator = tTravelItineraries
															.iterator(); iterator
															.hasNext();) {
														TTravelItinerary tTravelItinerary = (TTravelItinerary) iterator
																.next();
														RespData_GetOrderDetail_Ticket t =buildTicket(tTravelItinerary, tUpdateDate,null);
														ticketList.add(t);
													}
												}
											}
											if(f!=null){
												fList.add(f);
											}
											RespData_GetOrderDetail_Change change = new RespData_GetOrderDetail_Change(++count,fList,ticketList);
											changes.add(change);
										}
									}
									
									hql = "from TTicketRefund where tid in (:tids)";
									
									List<TTicketRefund> ttrs = comDao.queryWithInCondition(hql, conditions);
									if(null!=ttrs && ttrs.size()>0){
										//设置退票信息
										int count = 0;
										for (Iterator<TTicketRefund> iterator = ttrs
												.iterator(); iterator.hasNext();) {
											TTicketRefund tTicketRefund = (TTicketRefund) iterator
													.next();
											List<RespData_GetOrderDetail_Ticket> ticketList = new ArrayList<RespData_GetOrderDetail_Ticket>();
											TTravelItinerary tt = null;
											TUpdateDate tud = null;
											String hql2 = "from TTravelItinerary where TId = ?";
											List<TTravelItinerary> tList = comDao.queryForList(hql2, new Object[]{tTicketRefund.getTid()});
											if(null!=tList && tList.size()>0){
												tt = tList.get(0);
											}
											if(1==tTicketRefund.getIsChangedate()&&tTicketRefund.getUid()!=null){
												hql2 = "from TUpdateDate where UId = ? ";
												List<TUpdateDate> tudList = comDao.queryForList(hql2,new Object[]{tTicketRefund.getUid()});
												if(null!=tudList && tudList.size()>0){
													tud = tudList.get(0);
												}
											}
											RespData_GetOrderDetail_Ticket ticket = buildTicket(tt,tud,tTicketRefund);
											if(ticket!=null){
												ticketList.add(ticket);
											}
											
											RespData_GetOrderDetail_Refund refund = new RespData_GetOrderDetail_Refund(tTicketRefund.getIsChangedate()+1, ++count, ticketList );
											refunds.add(refund);
										}
									}
								}
								result.setChanges(changes);
								result.setRefunds(refunds);
								
								
								return result;
							}
							return null;
						}
						
					};
					map.put(key, convertor);
					result = (Convertor<F, T>) convertor;
				}
			}
			result = (Convertor<F, T>) convertor;
		}
		return result;
	}
	
	
	/**
	 * 构建航班信息
	 * @param processstate //1-单程  2-往返  3-联程
	 * @param pnr pnr
	 * @param tud 改期信息 不存在传null
	 */
	private RespData_GetOrderDetail_Flight buildFlight(String processstate,TPnr pnr,TUpdateDate tud){
		RespData_GetOrderDetail_Flight flight = null;
		if(null!=pnr && tud == null){
			flight = new RespData_GetOrderDetail_Flight();
			
			List<TEndorse> endorse = CommUtil.getEndorse(pnr.getAirlineCompany(), DateUtil.SDF_yyyyMMdd.format(pnr.getOriginTime()), DateUtil.P_yyyyMMdd);//获取航司票规
			Integer flowId = null;
			
			if("2".equals(processstate) || "3".equals(processstate)){//如果是往返或者联程
				if(Integer.parseInt(pnr.getFlighttype())==2){// 说明是返程
					flowId = -1;
				}else{
					flowId = Integer.parseInt(pnr.getFlighttype());
				}
			}else{
				flowId = 0;
//				flowId = Integer.parseInt(pnr.getFlighttype());
			}
			
			flight.setFlowId(flowId);//航班顺序号
			//new RespData_GetOrderList_Flight(flowId, pnr.getAirline(), pnr.getAirlineCompany(), pnr.getAirlineshare(), StringUtil.isEmpty(pnr.getAirlineshare())?"":pnr.getAirlineshare().substring(0, 2), pnr.getOriginTime(), pnr.getDestinationTime(), pnr.getOriginCity(), pnr.getDestinationCity());
			flight.setFlightNo(pnr.getAirline());//航班号
			flight.setAirline(pnr.getAirlineCompany());//航司二字码
			flight.setShareAirline(StringUtil.isEmpty(pnr.getAirlineshare())?"":pnr.getAirlineshare().substring(0, 2));//共享航司二字码
			flight.setShareflightNo(pnr.getAirlineshare());//共享航班号
			flight.setPlaneType(pnr.getPlanetype());//机型
			flight.setCabinCode(pnr.getCangwei());//舱位代码
//			flight.setCabinName();//舱位名称
			flight.setPrice(pnr.getDiscountPrice());//原价
			flight.setRebatePrice(pnr.getDiscountPrice());//票价优惠价
//			flight.setDiscount();//折扣
			flight.setAirportTax(pnr.getTaxPrice());//机场建设费
			flight.setFuelSurTax(pnr.getFueltax());//燃油费
			flight.setDepAirport(pnr.getOriginCity());//起飞机场
			flight.setArrAirport(pnr.getDestinationCity());//到达机场
			flight.setDepPointAT(pnr.getOrgterm());//出发航站楼
			flight.setArrPointAT(pnr.getDetterm());//到达航站楼
			flight.setDepDateTime(pnr.getOriginTime());//出发日期时间
			flight.setArrDateTime(pnr.getDestinationTime());//到达日期时间
			//以下设置票规
			RespData_GetFlightDetail_TicketRule ticketRule = null;
			if(null!=endorse && endorse.size()>0){
				B:for(TEndorse eds:endorse){
					if(pnr.getCangwei().equals(eds.getCangwei())){
						ticketRule = new RespData_GetFlightDetail_TicketRule();
//						ticketRule.setChange(eds.getEndorsement());
						ticketRule.setEndorsement(eds.getEndorsement());
						ticketRule.setRefund(eds.getRefund());
						flight.setTicketRule(ticketRule);//设置票规
						break B;
					}
				}
			}
			//以下设置经停信息
			String ffstr = pnr.getFfstr();// 格式为：抵达城市三字码_抵达时间yyyyMMdd HH:mm_出发时间yyyyMMdd HH:mm
			if(!StringUtil.isEmpty(ffstr)){
				List<RespData_GetFlightDetail_Stop> stops = new ArrayList<RespData_GetFlightDetail_Stop>();
				RespData_GetFlightDetail_Stop stop =new RespData_GetFlightDetail_Stop();
				String[] strs = ffstr.split("_");
				if(null!=strs && strs.length==3){
					stop.setAirportCode(strs[0]);
					String arrtimeStr = strs[1];
					String deptimeStr = strs[2];
					try {
						Date arrtime = DateUtil.parse(arrtimeStr, "yyyyMMdd HH:mm");
						Date deptime = DateUtil.parse(deptimeStr, "yyyyMMdd HH:mm");
						stop.setArrDateTime(arrtime);
						stop.setDepDateTime(deptime);
						stops.add(stop);
					} catch (ParseException e) {
						logger.error(StringUtil.getExceptionStr(e));
					}
				}
				flight.setStops(stops);
			}
		}else if(tud!=null){
			flight = new RespData_GetOrderDetail_Flight();
			
			List<TEndorse> endorse = CommUtil.getEndorse(StringUtil.isEmpty(tud.getAirlineCompany())?tud.getFlightNumber().substring(0, 2):tud.getAirlineCompany(), DateUtil.SDF_yyyyMMdd.format(tud.getFlightTime()), DateUtil.P_yyyyMMdd);//获取航司票规
			Integer flowId = null;
			
			if("2".equals(processstate) || "3".equals(processstate)){//如果是往返或者联程
				if(Integer.parseInt(pnr.getFlighttype())==2){// 说明是返程
					flowId = -1;
				}else{
					flowId = Integer.parseInt(pnr.getFlighttype());
				}
			}else{
				flowId = 0;
//				flowId = Integer.parseInt(pnr.getFlighttype());
			}
			
			flight.setFlowId(flowId);//航班顺序号
			//new RespData_GetOrderList_Flight(flowId, pnr.getAirline(), pnr.getAirlineCompany(), pnr.getAirlineshare(), StringUtil.isEmpty(pnr.getAirlineshare())?"":pnr.getAirlineshare().substring(0, 2), pnr.getOriginTime(), pnr.getDestinationTime(), pnr.getOriginCity(), pnr.getDestinationCity());
			flight.setFlightNo(tud.getFlightNumber());//航班号
			flight.setAirline(StringUtil.isEmpty(tud.getAirlineCompany())?tud.getFlightNumber().substring(0, 2):tud.getAirlineCompany());//航司二字码
//			flight.setShareAirline(StringUtil.isEmpty(pnr.getAirlineshare())?"":pnr.getAirlineshare().substring(0, 2));//共享航司二字码
//			flight.setShareflightNo(pnr.getAirlineshare());//共享航班号
			flight.setPlaneType(tud.getPlanetype());//机型
			flight.setCabinCode(tud.getCangwei());//舱位代码
//			flight.setCabinName();//舱位名称
			flight.setPrice(tud.getTicketPrice());//原价
			flight.setRebatePrice(tud.getTicketPrice());//票价优惠价
//			flight.setDiscount();//折扣
			flight.setAirportTax(tud.getTaxPrice());//机场建设费
			flight.setFuelSurTax(tud.getFueltax());//燃油费
			flight.setDepAirport(tud.getFlightStart());//起飞机场
			flight.setArrAirport(tud.getFlightEnd());//到达机场
			flight.setDepPointAT(tud.getOrgterm());//出发航站楼
			flight.setArrPointAT(tud.getDetterm());//到达航站楼
			flight.setDepDateTime(tud.getFlightTime());//出发日期时间
			flight.setArrDateTime(tud.getDestinationTime());//到达日期时间
			//以下设置票规
			RespData_GetFlightDetail_TicketRule ticketRule = null;
			if(null!=endorse && endorse.size()>0){
				B:for(TEndorse eds:endorse){
					if(tud.getCangwei().equals(eds.getCangwei())){
						ticketRule = new RespData_GetFlightDetail_TicketRule();
//						ticketRule.setChange(eds.getEndorsement());
						ticketRule.setEndorsement(eds.getEndorsement());
						ticketRule.setRefund(eds.getRefund());
						flight.setTicketRule(ticketRule);//设置票规
						break B;
					}
				}
			}
			//以下设置经停信息
			/*String ffstr = pnr.getFfstr();// 格式为：抵达城市三字码_抵达时间yyyyMMdd HH:mm_出发时间yyyyMMdd HH:mm
			if(!StringUtil.isEmpty(ffstr)){
				List<RespData_GetFlightDetail_Stop> stops = new ArrayList<RespData_GetFlightDetail_Stop>();
				RespData_GetFlightDetail_Stop stop =new RespData_GetFlightDetail_Stop();
				String[] strs = ffstr.split("_");
				if(null!=strs && strs.length==3){
					stop.setAirportCode(strs[0]);
					String arrtimeStr = strs[1];
					String deptimeStr = strs[2];
					try {
						Date arrtime = DateUtil.parse(arrtimeStr, "yyyyMMdd HH:mm");
						Date deptime = DateUtil.parse(deptimeStr, "yyyyMMdd HH:mm");
						stop.setArrDateTime(arrtime);
						stop.setDepDateTime(deptime);
					} catch (ParseException e) {
						logger.error(StringUtil.getExceptionStr(e));
					}
				}
				flight.setStops(stops);
			}*/
		}
		return flight;
	}
	
	/**
	 * 组装乘客信息
	 * @param tt
	 * @return
	 */
	private RespData_GetOrderDetail_Passenger buildPassenger(TTravelItinerary tt){
		RespData_GetOrderDetail_Passenger result = null;
		if(null!=tt){
			
			result = new RespData_GetOrderDetail_Passenger();
			result.setId(tt.getTId().intValue());//顺序号
			result.setName(tt.getPassengerName());//姓名
			result.setMobile(tt.getPassengerTel());//手机号
			result.setEmail(tt.getPassengerEmail());//邮箱
			result.setIdType(Integer.parseInt(tt.getPassengerIdtype()));//证件类型
			result.setIdNum(tt.getPassengerId());//证件号码
			result.setCostCenter(tt.getOrgid());//成本中心
			result.setProjectNo(tt.getProjectid());//项目号
			result.setInsuranceId(tt.getInsuranceId());//保险id
			result.setInsuranceNum(tt.getAssuranceNum().intValue());//保险数量
			result.setInsurancePrice(tt.getAssurancePrice());//保险单价
		}
		
		return result;
	}
	
	/**
	 * 组装机票信息
	 * @param tt TTravelItinerary机票信息
	 * @param tud TUpdateDate改期信息
	 * @return
	 */
	private RespData_GetOrderDetail_Ticket buildTicket(TTravelItinerary tt,TUpdateDate tud,TTicketRefund trf){
		RespData_GetOrderDetail_Ticket result = null;
		
		Double fee = 0d;
		
		if(null!=tt && tud==null){
			result = new RespData_GetOrderDetail_Ticket();
			result.setAirportTax(tt.getTaxPrice()==null?0:tt.getTaxPrice());
//			ticket.setChildBabyBookCabin(null);
			result.setFlightNo(tt.getFlightNumber());
			result.setFuelSurTax(tt.getFueltax()==null?0:tt.getFueltax());
			result.setPassengerId(tt.getTId().intValue());
			result.setPnr(tt.getTPnr().getPnr());
			result.setPrice(tt.getTicketPrice());
			result.setRebatePrice(tt.getTicketPrice());
			result.setStatus(tt.getFlightStatus());
			result.setTicketNo(tt.getTravelItineraryNo());
			
			Double serviceFee = tt.getServiceFee()==null?0:tt.getServiceFee();
			Double nightFee = tt.getNightFee()==null?0:tt.getNightFee();
			Double customerServiceFee = tt.getCustomerServiceFee()==null?0:tt.getCustomerServiceFee();
			fee += serviceFee+nightFee+customerServiceFee;
			
		}else if(tud!=null){
			result = new RespData_GetOrderDetail_Ticket();
			result.setAirportTax(tud.getTaxPrice()==null?0:tud.getTaxPrice());
//			ticket.setChildBabyBookCabin(null);
			result.setFlightNo(tud.getFlightNumber());
			result.setFuelSurTax(tud.getFueltax()==null?0:tud.getFueltax());
//			ticket.setPassengerId(null);
			result.setPnr(StringUtil.isEmpty(tud.getNewPnr())?tt.getTPnr().getPnr():tud.getNewPnr());
			result.setPrice(tud.getTicketPrice());
			result.setRebatePrice(tud.getTicketPrice());
			result.setStatus(tud.getFlightStatus());
			result.setTicketNo(StringUtil.isEmpty(tud.getChangeBanks())?tt.getTravelItineraryNo():tud.getChangeBanks());
			result.setChangeFee(tud.getChangeTheFreight());
			result.setClassFee(tud.getClassFee());
			
			
			Double serviceFee = tud.getChangeServiceFee()==null?0:tud.getChangeServiceFee();
			Double nightFee = tud.getNightFee()==null?0:tud.getNightFee();
//			Double customerServiceFee = tud.getCustomerServiceFee()==null?0:tud.getCustomerServiceFee();
			fee += serviceFee+nightFee;
		}
		if(null!=trf){
			result.setStatus(trf.getRefundStatus().toString());
			result.setRefundFee(trf.getRefundServiceFee());
			result.setRefundReason(trf.getApplyRefuseReson());
		}
		return result;
	}
	
}
