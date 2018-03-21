package com.viptrip.test;


import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.viptrip.util.JSON;
import com.viptrip.wetrip.model.Request_GetOrderDetail;
import com.viptrip.wetrip.model.Request_GetOrderList;
import com.viptrip.wetrip.model.Response_GetOrderDetail;
import com.viptrip.wetrip.model.Response_GetOrderList;
import com.viptrip.wetrip.model.orderlist.ReqData_GetOrderList;
import com.viptrip.wetrip.service.ICaiyunPayService;
import com.viptrip.wetrip.service.IGetOrderService;
import com.viptrip.wetrip.vo.CaiyunResp;

@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试  
@ContextConfiguration({"classpath:conf/spring/spring-context.xml"}) //加载配置文件
public class TestOrderList {
	@Resource
	private IGetOrderService service;
	
	@Resource
	private ICaiyunPayService pService;
	
	@Test
	public void test(){
//		Request_GetOrderList para = new Request_GetOrderList();
//		para.setData(new ReqData_GetOrderList(1,10,0));
//		Response_GetOrderList orderList1 = service.getOrderList(para);
//		System.out.println("TestOrderList.test()==orderList==" + new Gson().toJson(orderList1));
//		System.out.println(orderList1.list.size());
//		para.setData(new ReqData_GetOrderList(11,10,0));
//		Response_GetOrderList orderList2 = service.getOrderList(para);
//		System.out.println(orderList2.list.size());
//		System.out.println(list.size());
		
		Request_GetOrderDetail para = new Request_GetOrderDetail();
		Long orderID = 64351L;
//		Long orderID = 64355L;
		para.setOrderID(orderID);
		Response_GetOrderDetail detail = service.getOrderDetail(para);
		System.out.println("TestOrderList.test()==detail==" + JSON.get().notEscapeHTML(true).nullSerialize(true).toJson(detail));
		String extend = JSON.get().notEscapeHTML(true).nullSerialize(true).toJson(detail);
		
		
	/*	String extend = "{\"orderId\":\"AT170816084496\",\"price\":156000,\"extend\":\"{\"orderId\":84496,\"orderNo\":\"AT170816084496\",\"amount\":1560.0,\"tripType\":1,\"payMethod\":\"4\",\"status\":\"3\",\"orderDateTime\":\"2017-08-16 15:31\",\"businessType\":0,\"tripInfo\":[{\"flowId\":1,\"depAirport\":\"KWE\",\"arrAirport\":\"LPF\",\"date\":\"2017-09-01\"}],\"flights\":[{\"flowId\":0,\"flightNo\":\"GS6465\",\"airline\":\"GS\",\"shareAirline\":\"\",\"planeType\":\"190\",\"cabinCode\":\"U\",\"price\":1560.0,\"rebatePrice\":1560.0,\"discount\":0,\"airportTax\":200.0,\"fuelSurTax\":0.0,\"depAirport\":\"KWE\",\"arrAirport\":\"LPF\",\"depPointAT\":\"T2\",\"depDateTime\":\"Sep 1, 2017 10:20:00 AM\",\"arrDateTime\":\"Sep 1, 2017 11:10:00 AM\",\"ticketRule\":{\"refund\":\"航班离站时间前4小时（含）之前，收取各舱位对应票价50％退票费，航班离站时间前4小时（不含）之后，收取各舱位对应票价100％退票费(即不得退票)。\",\"endorsement\":\"不允许签转，航班离站时间前4小时（含）之前,每次收取各舱位对应票价30％变更费; 航班离站时间前4小时（不含）之后,每次收取各舱位对应票价50％变更费。\"}}],\"passengers\":[{\"mobile\":\"18969937873\",\"idType\":1,\"projectNo\":\"\",\"insuranceId\":240,\"insuranceNum\":1,\"insurancePrice\":20.0,\"name\":\"测贰\",\"status\":0},{\"mobile\":\"15088603364\",\"idType\":1,\"projectNo\":\"\",\"insuranceId\":240,\"insuranceNum\":1,\"insurancePrice\":20.0,\"name\":\"讯盟测\",\"status\":0},{\"mobile\":\"15068746748\",\"idType\":1,\"projectNo\":\"\",\"insuranceId\":240,\"insuranceNum\":1,\"insurancePrice\":20.0,\"name\":\"测四\",\"status\":0},{\"mobile\":\"18817350871\",\"idType\":1,\"projectNo\":\"\",\"insuranceId\":240,\"insuranceNum\":1,\"insurancePrice\":20.0,\"name\":\"测仨\",\"status\":0}],\"tickets\":[{\"flightNo\":\"GS6465\",\"pnr\":\"ASDFGH\",\"price\":320.0,\"rebatePrice\":320.0,\"airportTax\":50.0,\"fuelSurTax\":0.0,\"serviceFee\":0.0,\"status\":\"11\"},{\"flightNo\":\"GS6465\",\"pnr\":\"ASDFGH\",\"price\":320.0,\"rebatePrice\":320.0,\"airportTax\":50.0,\"fuelSurTax\":0.0,\"status\":\"11\"},{\"flightNo\":\"GS6465\",\"pnr\":\"ASDFGH\",\"price\":320.0,\"rebatePrice\":320.0,\"airportTax\":50.0,\"fuelSurTax\":0.0,\"status\":\"11\"},{\"flightNo\":\"GS6465\",\"pnr\":\"ASDFGH\",\"price\":320.0,\"rebatePrice\":320.0,\"airportTax\":50.0,\"fuelSurTax\":0.0,\"status\":\"11\"}],\"changes\":[],\"refunds\":[]}\"}";
		
		extend = "{\"orderId\":84496,\"orderNo\":\"AT170816084496\",\"amount\":1560.0,\"tripType\":1,\"payMethod\":\"4\",\"status\":\"3\",\"orderDateTime\":\"2017-08-16 15:31\",\"businessType\":0,\"tripInfo\":[{\"flowId\":1,\"depAirport\":\"KWE\",\"arrAirport\":\"LPF\",\"date\":\"2017-09-01\"}],\"flights\":[{\"flowId\":0,\"flightNo\":\"GS6465\",\"airline\":\"GS\",\"shareAirline\":\"\",\"planeType\":\"190\",\"cabinCode\":\"U\",\"price\":1560.0,\"rebatePrice\":1560.0,\"discount\":0,\"airportTax\":200.0,\"fuelSurTax\":0.0,\"depAirport\":\"KWE\",\"arrAirport\":\"LPF\",\"depPointAT\":\"T2\",\"depDateTime\":\"Sep 1, 2017 10:20:00 AM\",\"arrDateTime\":\"Sep 1, 2017 11:10:00 AM\",\"ticketRule\":{\"refund\":\"航班离站时间前4小时（含）之前，收取各舱位对应票价50％退票费，航班离站时间前4小时（不含）之后，收取各舱位对应票价100％退票费(即不得退票)。\",\"endorsement\":\"不允许签转，航班离站时间前4小时（含）之前,每次收取各舱位对应票价30％变更费; 航班离站时间前4小时（不含）之后,每次收取各舱位对应票价50％变更费。\"}}],\"passengers\":[{\"mobile\":\"18969937873\",\"idType\":1,\"projectNo\":\"\",\"insuranceId\":240,\"insuranceNum\":1,\"insurancePrice\":20.0,\"name\":\"测贰\",\"status\":0},{\"mobile\":\"15088603364\",\"idType\":1,\"projectNo\":\"\",\"insuranceId\":240,\"insuranceNum\":1,\"insurancePrice\":20.0,\"name\":\"讯盟测\",\"status\":0},{\"mobile\":\"15068746748\",\"idType\":1,\"projectNo\":\"\",\"insuranceId\":240,\"insuranceNum\":1,\"insurancePrice\":20.0,\"name\":\"测四\",\"status\":0},{\"mobile\":\"18817350871\",\"idType\":1,\"projectNo\":\"\",\"insuranceId\":240,\"insuranceNum\":1,\"insurancePrice\":20.0,\"name\":\"测仨\",\"status\":0}],\"tickets\":[{\"flightNo\":\"GS6465\",\"pnr\":\"ASDFGH\",\"price\":320.0,\"rebatePrice\":320.0,\"airportTax\":50.0,\"fuelSurTax\":0.0,\"serviceFee\":0.0,\"status\":\"11\"},{\"flightNo\":\"GS6465\",\"pnr\":\"ASDFGH\",\"price\":320.0,\"rebatePrice\":320.0,\"airportTax\":50.0,\"fuelSurTax\":0.0,\"status\":\"11\"},{\"flightNo\":\"GS6465\",\"pnr\":\"ASDFGH\",\"price\":320.0,\"rebatePrice\":320.0,\"airportTax\":50.0,\"fuelSurTax\":0.0,\"status\":\"11\"},{\"flightNo\":\"GS6465\",\"pnr\":\"ASDFGH\",\"price\":320.0,\"rebatePrice\":320.0,\"airportTax\":50.0,\"fuelSurTax\":0.0,\"status\":\"11\"}],\"changes\":[],\"refunds\":[]}";
		
		CaiyunResp<?> result = pService.prePay(300276L, "84496", 1560D, extend);
		
		System.out.println("TestOrderList.test()==result==" + JSON.get().notEscapeHTML(true).nullSerialize(true).toJson(result));*/
		
		
		
		/*CaiyunResp<?> result = pService.payRefund(300276L, "84496", 0D, null);
		System.out.println("TestOrderList.test()==result==" + JSON.get().notEscapeHTML(true).nullSerialize(true).toJson(result));*/
	}
	
	public static void main(String[] args) {
		
	}
	
}
