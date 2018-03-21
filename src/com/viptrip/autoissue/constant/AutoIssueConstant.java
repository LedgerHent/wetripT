package com.viptrip.autoissue.constant;

public class AutoIssueConstant {
	
	 public static final int AUTOMATIC_TICKET=1;    //待自动出票
	
	 public static final int AUTOMATIC_DRAWING=2;   //自动出票中
	
	 public static final int AUTOMATIC_TICKET_SUCCESS=3;//出票成功
	
	 public static final int FAIL_PATA_PRICE_ERROR=11;   //出票失败：PATA价格不符
	
	 public static final int FAIL_PATA_PRICE_VARIOUS=12; //出票失败:PATA多价格
	
	 public static final int FAIL_BSP_TICKET_LIMIT =13;  //出票失败:BSP出票额度限制
	
	 public static final int FAIL_NO_MAINTENANCE_FEE=14; //出票失败:没维护定额代理费
	 
	 public static final int FAIL_TICKET_PRICE_CHANGE=16; //出票失败:预定票面价与出票底价不一致
	 
	 public static final int FAIL_TICKET_HAVE_TICKETNO=17; //出票失败:PNR里存在票号
	
	 public static final int FAIL_TICKET_UPDATE=18;             //出票失败:更新订单状态或入账单失败
	 
	 public static final int FAIL_OTHERS=15;             //出票失败:其他

	
	
	
	
	
	
	
	

}
