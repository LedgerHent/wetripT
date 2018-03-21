package com.viptrip.hotel.task;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.hotel.entity.HotelNotify;
import com.viptrip.hotel.entity.HotelPayInfo;
import com.viptrip.hotel.service.IHotelNotifyService;
import com.viptrip.hotel.service.IHotelPayInfoService;
import com.viptrip.hotel.util.PayConfig;
import com.viptrip.util.JSON;
/**
 * 自动检查因意外终止（比如服务器重启）发送任务的NotifyTask
 * @author selfwhisper
 *
 */
@Service
public class AutoCheckNotifyTask {
	
	private static Logger logger = LoggerFactory.getLogger(AutoCheckNotifyTask.class);
	
	@Resource
	private IHotelPayInfoService hpis;
	@Resource
	private IHotelNotifyService hns;
	
	/**
	 * 检查因意外终止的自动通知
	 */
	public void checkAndAutoStart(){
		logger.info("开始检查酒店支付通知自动发送信息");
		String hql = "from HotelNotify where status = 0";
		List<HotelNotify> list = hns.query(hql);
		if(null!=list && list.size()>0){
			for(HotelNotify hn:list){
				//if(hn.getCount()>=PayConfig.maxCount && 0 == hn.getSending()){
//					hn.setStatus(9);
//					hn.setSending(0);
//					hns.update(hn);
//					if(1==hn.getStatus() || 9 == hn.getStatus()){
//						continue;
//					}
//				}
				
				Date sendTime = hn.getSendTime();
				Integer sending = hn.getSending();
				if(null==sendTime || null==sending || 0==sending || (new Date().getTime()-sendTime.getTime()) > (Math.pow(2D, PayConfig.maxCount*1D-2) + 10)*60*1000){//以 最大重试时间+10 分钟为界限 最后
					HotelPayInfo hpi = hpis.getHotelPayInfoByOrderno(hn.getOrderno());
					if(null!=hpi){
						Map<String, Object> paramMap = PayUtil.getNotifyParamMap(hpi , 2, 1, hpi.getStatus(), "成功");
						Map<String,Object> map = new HashMap<>();
						map.put("data", PayUtil.getEncryptParamStr(paramMap));
						new NotifyTask(hn, ApplicationContextHelper.getInstance().getBean(IHotelNotifyService.class), map).sendNow();
					}
				}
			}
		}
		logger.info("结束检查酒店支付通知自动发送信息");
	}


	

	public static void main(String[] args) {
		String str = "{\"name\":\"John\",\"age\":18}";
		
		System.out.println("str=" + str);
		System.out.println("str to json=" + JSON.get().notEscapeHTML(true).toJson(str));
	}
	
}
