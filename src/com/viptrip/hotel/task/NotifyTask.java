package com.viptrip.hotel.task;

import java.util.Date;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.kevinsawicki.http.HttpRequest;
import com.viptrip.hotel.entity.HotelNotify;
import com.viptrip.hotel.service.IHotelNotifyService;
import com.viptrip.hotel.util.PayConfig;
import com.viptrip.util.JSON;
/**
 * 通知任务类
 * @author selfwhisper
 *
 */
public class NotifyTask extends TimerTask{
	
	private static Logger logger = LoggerFactory.getLogger(NotifyTask.class);
	
	private HotelNotify notify;
	
	private Map<String,Object> map;//通知发送的参数
	private Algorithm alg;//重试算法
	private Timer timer = new Timer();
	private IHotelNotifyService service;
	
	public NotifyTask(HotelNotify notify,IHotelNotifyService service,Map<String,Object> param){
		this.notify = notify;
		this.service = service;
		this.map = param;
		this.alg = new DefaultAlg();
	}
	
	public NotifyTask(HotelNotify notify,IHotelNotifyService service,Map<String,Object> param,Algorithm alg){
		this.notify = notify;
		this.service = service;
		this.map = param;
		this.alg = alg;
	}
	
	private NotifyTask(NotifyTask nt){
		this.notify = nt.getNotify();
		this.map = nt.getMap();
		this.alg = nt.getAlg();
		this.timer = nt.getTimer();
		this.service = nt.getService();
	}
	
	private IHotelNotifyService getService() {
		return service;
	}

	private Algorithm getAlg() {
		return alg;
	}

	private HotelNotify getNotify() {
		return notify;
	}

	private Map<String, Object> getMap() {
		return map;
	}

	private Timer getTimer() {
		return timer;
	}

	public void beginSend(){
		timer.schedule(this, 60*1000*alg.calc(notify.getCount()==null?1:notify.getCount()));
	}
	
	public void sendNow(){
		timer.schedule(this, 0);
	}

	@Override
	public void run() {
		notify.setSending(1);//更改发送状态
		service.update(notify);
		String url = notify.getUrl();
		String result = "false";
		try {
			result = HttpRequest.post(url).form(map).body();
			logger.info("NotifyTask发送通知：url→" + url + "，参数为" + JSON.get().notEscapeHTML(true).toJson(map) + ",返回结果为：" + result);
			if("SUCCESS".equalsIgnoreCase(result)){
				logger.error("订单号：" + notify.getOrderno() + ",发送通知成功,当前为第" + notify.getCount() + "次发送");
				notify.setSending(0);
				notify.setStatus(1);
				notify.setSendTime(new Date());
				service.update(notify);//更新通知状态
				this.cancel();
				timer.cancel();
				alg = null;
				map = null;
				notify = null;
				timer = null;
				service = null;
			}else{
				doRetryOrNot();
			}
		} catch (Exception e) {
			doRetryOrNot();
		}
	}
	
	private void doRetryOrNot(){
		notify.setSendTime(new Date());
		notify.setStatus(0);
		if(notify.getCount()<PayConfig.maxCount){//是否达到最大重试次数
			logger.error("订单号：" + notify.getOrderno() + ",发送通知失败,当前为第" + notify.getCount() + "次发送，下次将在" + 60*alg.calc(notify.nextCount()) + "秒之后重试");
			service.update(notify);//更新通知次数
			timer.schedule(new NotifyTask(this), 60*1000*alg.calc(notify.getCount()));
			this.cancel();
		}else{
			logger.error("订单号：" + notify.getOrderno() + ",发送通知失败,当前为第" + notify.getCount() + "次发送，达到最大重试次数，不再重试");
			notify.setSending(0);
			notify.setStatus(9);//更新通知状态为失效
			service.update(notify);
			this.cancel();
			timer.cancel();
			alg = null;
			map = null;
			notify = null;
			timer = null;
			service = null;
		}
	}
	
	public static void main(String[] args) {
		
	}
	
	
	
}
