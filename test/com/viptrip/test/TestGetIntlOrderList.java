package com.viptrip.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.intlAirticket.model.Request_GetIntlOrderDetail;
import com.viptrip.intlAirticket.model.Request_GetIntlOrderList;
import com.viptrip.intlAirticket.model.Response_GetIntlOrderDetail;
import com.viptrip.intlAirticket.model.Response_GetIntlOrderList;
import com.viptrip.intlAirticket.model.flightModels.Req_Data_IntlOrderListInfo;
import com.viptrip.intlAirticket.model.flightModels.Resp_Data_IntlOrderDetail;
import com.viptrip.intlAirticket.service.BookIntlOrderService;
import com.viptrip.intlAirticket.service.IntlOrderDetailService;

@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试  
@ContextConfiguration({"classpath:conf/spring/spring-context.xml"}) //加载配置文件
public class TestGetIntlOrderList {
    
    /*@Resource
    private BookIntlOrderService bookIntlOrderService;
    
    @Test
    public void testBookIntlOrderService(){
        Request_GetIntlOrderList para=new Request_GetIntlOrderList();
        Req_Data_IntlOrderListInfo info=new Req_Data_IntlOrderListInfo();
        info.setCount(20);
        info.setStart(1);
        info.setStatus(0);
        para.setData(info);
//        BookIntlOrderService service = ApplicationContextHelper.getInstance().getBean(BookIntlOrderService.class);
        Response_GetIntlOrderList data = bookIntlOrderService.getIntlOrderList(para);
    }*/
    
    @Resource
    private IntlOrderDetailService intlOrderDetailService;
    
    @Test
    public void testGetIntlOrderDetail(){
        Request_GetIntlOrderDetail para =new Request_GetIntlOrderDetail();
        para.setOrderID(4749);
        Response_GetIntlOrderDetail detail=intlOrderDetailService.getIntlOrderDetail(para);
        Resp_Data_IntlOrderDetail data=detail.data;
        System.out.println(data.orderNo);
        System.out.println(data.orgCityName);
    }
    
    
}
