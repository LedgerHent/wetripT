package com.viptrip.intlAirticket.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.viptrip.intlAirticket.model.Request_BookIntlOrder;
import com.viptrip.intlAirticket.model.Request_GetIntlOrderDetail;
import com.viptrip.intlAirticket.model.Request_GetIntlOrderList;
import com.viptrip.intlAirticket.model.Response_BookIntlOrder;
import com.viptrip.intlAirticket.model.Response_GetIntlOrderDetail;
import com.viptrip.intlAirticket.model.Response_GetIntlOrderList;

@Service
@Transactional
public interface BookIntlOrderService {
    /**
     * H5国际机票预定下单service
     * @param para
     * @return
     */
    public Response_BookIntlOrder goBookIntlOrder(Request_BookIntlOrder para);
    /**
     * H5国际机票订单列表查询接口
     * @param para
     * @return
     */
    public Response_GetIntlOrderList getIntlOrderList(Request_GetIntlOrderList para);
    
        
}
