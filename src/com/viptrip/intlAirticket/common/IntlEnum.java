package com.viptrip.intlAirticket.common;

import java.util.Arrays;
import java.util.List;


public class IntlEnum {
    
/*  说明：<100自己预定的订单，100-200作为管理员，审核员所能看到的订单
     0=全部订单，
     1=待审核订单（含审核过程中），
     2=审核通过的订单，
     3=审核驳回的订单，
     4=待支付订单，
     5=已完成订单（已审核，已支付，已取消），
     100=全部订单，
     101=待审核的订单（待我审核），
     102=审核通过的订单（只是我审核通过，审核流程未必完结），
     103=审核驳回的订单，
     105=已完成订单（已审核，已支付，已取消）*/
    public enum IntlOrderStatus{
      全部订单(0,"全部订单"),
      待审核订单(1,"待审核订单"),//1=待审核订单（含审核过程中），
      审核通过的订单(2,"审核通过的订单"),  // 2=审核通过的订单，
      审核驳回的订单(3,"审核驳回的订单"),  // 3=审核驳回的订单，
      待支付订单(4,"待支付订单"), // 4=待支付订单，
      已完成订单(5,"已完成订单"),  // 5=已完成订单（已审核，已支付，已取消），
      全部订单_管理员_审核员(100,"全部订单_管理员_审核员"), // 100=全部订单，
      待审核的订单_管理员_审核员(101,"待审核的订单_管理员_审核员"),  // 101=待审核的订单（待我审核），
      审核通过的订单_管理员_审核员(102,"审核通过的订单_管理员_审核员"),  // 102=审核通过的订单（只是我审核通过，审核流程未必完结），
      审核驳回的订单_管理员_审核员(103,"审核驳回的订单_管理员_审核员"),  // 103=审核驳回的订单，
      已完成订单_管理员_审核员(105,"已完成订单_管理员_审核员"); // 105=已完成订单（已审核，已支付，已取消）
        
        private int code;
        private String desc;
        
        private IntlOrderStatus(int code,String desc){
            this.code = code;
            this.desc = desc;
        }
        
        public static List<IntlOrderStatus> list(){
            return Arrays.asList(IntlOrderStatus.values());
        }
        
        public String desc(){
            return this.desc;
        }
        
        public int code(){
            return this.code;
        }
        
        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

        /**
         * 根据code获取对应的权限码
         * @param code
         * @return
         */
        public static IntlOrderStatus asIntlOrderStatus(int code){
            IntlOrderStatus result = null;
            for(IntlOrderStatus pc:IntlOrderStatus.values()){
                if(pc.code() == code){
                    result = pc;
                    break;
                }
            }
            return result;
        }
    }
}
