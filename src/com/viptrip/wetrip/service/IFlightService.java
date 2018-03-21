package com.viptrip.wetrip.service;



import java.util.HashMap;
import java.util.List;

import com.viptrip.wetrip.model.Request_BookAirTicket;
import com.viptrip.wetrip.model.Request_GetChildBabyPrice;
import com.viptrip.wetrip.model.Request_GetFlightDetail;
import com.viptrip.wetrip.model.Request_GetFlightList;
import com.viptrip.wetrip.model.Request_GetReschedueFlightList;
import com.viptrip.wetrip.model.flight.ReqData_GetFlightList;
import com.viptrip.wetrip.model.flight.RespData_BookAirTicket;
import com.viptrip.wetrip.model.flight.RespData_GetChildBabyPrice;
import com.viptrip.wetrip.model.flight.RespData_GetFlightDetail;
import com.viptrip.wetrip.model.flight.RespData_GetFlightList;
import com.viptrip.wetrip.model.flight.RespData_GetReschedueFlightList;
import com.viptrip.wetrip.vo.PassengerIdTypeAndIdNum;
/**
 * 航班查询
 * @author selfwhisper
 *
 */
public interface IFlightService {
	/**
	 * 机票列表查询
	 * @param para 查询参数
	 * @param pp 分页参数
	 * @return
	 */
	public List<RespData_GetFlightList> getFlightList(
			Request_GetFlightList para);
	
	/**
	 * 改期机票数据列表查询
	 * @param para
	 * @return
	 */
	public List<RespData_GetReschedueFlightList> getReschedueFlight(
	        Request_GetReschedueFlightList para);
	
	
	/**
	 * 机票详情查询
	 * @param para
	 * @return
	 */
	public RespData_GetFlightDetail getFlightDetail(Request_GetFlightDetail para);
	
	/**
	 * 儿童婴儿票价格查询
	 * @param para
	 * @return
	 */
	public RespData_GetChildBabyPrice getChildBabyPrice(Request_GetChildBabyPrice para);
	
	/**
	 * 机票预定
	 * @param para
	 * @return
	 */
	public RespData_BookAirTicket bookAirTicket(Request_BookAirTicket para);
	
	/**
	 * 根据乘客id以及类型获取订单乘客的idType、idNum
	 * @param id 乘客id
	 * @param type 乘客类型  1-企业员工  2-常旅客
	 * @return
	 */
	public PassengerIdTypeAndIdNum getBookTicketPassengerIdInfoByIdAndType(Integer id,Integer type);

}
