package com.viptrip.hotelHtml5.common;

import java.util.HashMap;

public class ConfigConstants {

	 /**
	  * 酒店提供的接口 
	 * @ClassName: TMC_API_METHOD 
	 * @Description: TODO(这里用一句话描述这个类的作用) 
	 * @author zhaojian
	 * @date 2017年11月2日 下午5:33:42 
	 *
	 */
	public static interface TMC_API_METHOD{
		/**
		 * 热门城市
		 */
		String GETHOTCITY = "GetHotCity";
		
		/**
		 * 城市列表
		 */
		String GETCITYLIST = "GetCityList";
		
		/**
		 * 关键字联想
		 */
		String GETKEYASSOCIATEINFO = "GetKeyAssociateInfo";
		
		/**
		 * 酒店列表
		 */
		String GETHOTELLIST = "GetHotelList";
		
		/**
		 * 酒店详情
		 */
		String GETHOTELDETAIL = "GetHotelDetail";
		
		/**
		 * 产品列表
		 */
		String GETPRODUCTLIST = "GetProductList";
		
		/**
		 * 产品详情
		 */
		String GETPRODUCTDETAIL = "GetProductDetail";
	}
	/**
	 * 床型
	 */
	public static HashMap<String, String> BED_TYPE_MAP = new HashMap<String, String>() {
        {
        	put("1", "大床");         	
            put("2", "双床"); 
            put("3", "大床/双床 "); 
            put("4", "多张床 "); 
            put("5", "单人床 "); 
            put("96", "3床"); 
            put("97", "4床"); 
            put("98", "上下铺"); 
            put("99", "通铺"); 
            put("90", "榻榻米"); 
            put("91", "水床"); 
            put("92", "圆床"); 
            put("93", "拼床"); 
            put("94", "炕"); 
        }
    };
    
    public static interface TMCH5_FACILITTYITEMS{
    	/**
    	 * 免费WIFI
    	 */
		String[] FREE_WIFI={"10001"};	
		
		/**
		 * 收费WIFI
		 */
		String[] CHARGE_WIFI = {"10002"};
		
		/**
		 * 公共卫生间
		 */
		String[] PUBLIC_WASHROOM={"11"};
		
		/**
		 * 中央空调
		 */
		String[] CENTRAL_AIR_CONDITIONING={"12"};
		
		/**
		 * 专职行李员
		 */
		String[] BELLPERSON = {"20"};
		
		/**
		 * 代客泊车
		 */
		String[] VALET_PARKING= {"21"};
	}
    
    /**
     * 查询结果
     * @author guomengmeng01
     *
     */
    public static interface H5_SEARCH_RESULT_CODE{
		String SUCCESS_CODE = "1000";
		String FAIL_CODE = "1001";
		String SOCKETTIMEOUT_CODE = "1002";
		String QUERY_EXCEPTION_CODE = "1003";
	}
    public static interface H5_SEARCH_RESULT_MSG{
		String SUCCESS_MSG = "查询成功";
		String FAIL_MSG = "查询失败";
		String SOCKETTIMEOUT_MSG = "查询接口超时";
		String QUERY_EXCEPTION_MSG = "查询异常";
	}
}
