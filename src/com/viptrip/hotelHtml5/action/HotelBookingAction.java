package com.viptrip.hotelHtml5.action;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.viptrip.hotelHtml5.service.HotelInfoService;
import com.viptrip.hotelHtml5.util.ClassUtil;
import com.viptrip.hotelHtml5.util.PageUtil;
import com.viptrip.hotelHtml5.vo.request.Request_GetHotelDetail;
import com.viptrip.hotelHtml5.vo.request.Request_GetHotelList;
import com.viptrip.hotelHtml5.vo.request.Request_GetProductDetail;
import com.viptrip.hotelHtml5.vo.request.Request_GetProductList;
import com.viptrip.hotelHtml5.vo.request.vo.IndexPoiQueryRequest;
import com.viptrip.hotelHtml5.vo.response.Response_GetHotelDetail;
import com.viptrip.hotelHtml5.vo.response.Response_GetHotelList;
import com.viptrip.hotelHtml5.vo.response.Response_GetKeyAssociateInfo;
import com.viptrip.hotelHtml5.vo.response.Response_GetProductDetail;
import com.viptrip.hotelHtml5.vo.response.Response_GetProductList;
import com.viptrip.hotelHtml5.vo.response.ro.HotelQueryResponse;


@Controller
@RequestMapping("hotelSearch")
public class HotelBookingAction {
	private static Logger logger = LoggerFactory.getLogger(HotelBookingAction.class);
	/**关键字搜索页*/
	private static final String KEYWORD_SEARCH = "hotelHtml5/hotelSearch/keyWord_search";	
	
	/**酒店列表查询页**/
	private static final String HOTEL_SEARCH_INIT = "hotelHtml5/hotelSearch/hotelSearch_init"; 
	
	/**酒店列表结果页**/
	private static final String HOTEL_SEARCH_LIST = "hotelHtml5/hotelSearch/hotelSearch_list"; 
	
	/**酒店详情页**/
	private static final String HOTEL_DETAIL = "hotelHtml5/hotelSearch/hotelDetail";	
	
	@Autowired
	private HotelInfoService hotelInfoService;
	
	/**
	 * 跳转酒店列表查询页
	 * @param request
	 * @return
	 */
	@RequestMapping("init")
	public String init(HttpServletRequest request){
		return HOTEL_SEARCH_INIT;
	}
	
	/**
	 * 获取酒店列表查询结果
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("getHotelList")
	public String getHotelList(Request_GetHotelList request,Model model){
		logger.info("getHotelList request:"+com.alibaba.fastjson.JSONObject.toJSONString(request));
		Response_GetHotelList response_GetHotelList =null;
		response_GetHotelList = hotelInfoService.getH5HotelList(request);
		if(response_GetHotelList!=null){
			//是否有协议酒店
			if(response_GetHotelList.getAgreementPageInfo()!=null 
					&& response_GetHotelList.getAgreementPageInfo().getList()!=null
					&& response_GetHotelList.getAgreementPageInfo().getList().size() >0){
				model.addAttribute("agreementHotelResultFlag", true);
			}else{
				model.addAttribute("agreementHotelResultFlag", false);
			}
			PageUtil<HotelQueryResponse> choicePageInfo = response_GetHotelList.getChoicePageInfo();
			PageUtil<HotelQueryResponse> agreementPageInfo = response_GetHotelList.getAgreementPageInfo();
			if(response_GetHotelList.getChoicePageInfo()==null){
				choicePageInfo = new PageUtil<HotelQueryResponse>();
				choicePageInfo.setHasNextPage(false);
			}
			if(response_GetHotelList.getAgreementPageInfo()==null){
				agreementPageInfo = new PageUtil<HotelQueryResponse>();
				agreementPageInfo.setHasNextPage(false);
			}
			model.addAttribute("hotelRequest", request);
			model.addAttribute("hotelInfo", response_GetHotelList);
			model.addAttribute("choicePageInfo", choicePageInfo);		//精选酒店
			model.addAttribute("agreementPageInfo", agreementPageInfo);	//协议酒店
		}
		return HOTEL_SEARCH_LIST;
	}
	
	/**
	 * 酒店详情页（酒店基础信息|酒店房型列表|酒店房型详情|酒店查询条件）
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("getHotelDetail")
	public String getHotelDetail(Request_GetHotelDetail request,Model model){
		try {
			//酒店基础信息
			Response_GetHotelDetail response_GetHotelDetail = hotelInfoService.getH5HotelDetail(request);
			if(response_GetHotelDetail !=null && response_GetHotelDetail.getBusiData()!=null){
//				List<MongoHotelFacilittyItem> facilittyItems = hotelInfoService.getMongoHotelFacilittyItem(response_GetHotelDetail.getBusiData().getFacilittyItems());
//				model.addAttribute("facilittyItems", facilittyItems);
				model.addAttribute("hotelDetail", response_GetHotelDetail.getBusiData());

			}
			
			//酒店房型列表|酒店房型详情
			Request_GetProductList request_GetProductList =new Request_GetProductList();
			request.setCheckStartDate(new Date());	
			request.setTimeIntervalEndDate(new Date());
			ClassUtil.setField(request, request_GetProductList);
			request_GetProductList.setCheckStartDate(null);
			request_GetProductList.setTimeIntervalEndDate(null);
			Response_GetProductList response_GetProductList = hotelInfoService.getH5ProductList(request_GetProductList);
			logger.info(JSONObject.toJSONString(response_GetProductList));
			model.addAttribute("productList", response_GetProductList);
			
			//酒店查询条件
			model.addAttribute("hotelSearch", request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("asdadsasd");
		return HOTEL_DETAIL;
	}
	
	/**
	 * 房型详情（验价）
	 * 1000 | 1001   验价成功 
	 * 9999		         无房
	 * -1		         失败
	 * @param productQueryRequest
	 * @return
	 */
	@RequestMapping("getProductDetail")
	@ResponseBody
	public Response_GetProductDetail getProductDetail(Request_GetProductDetail request){
		Response_GetProductDetail result = hotelInfoService.getH5ProductDetail(request);
		return result;
	}
	
	/**
	 * 跳转关键字搜索页
	 * @param request
	 * @return
	 */
	@RequestMapping("keyWordSearchInit")
	public String keyWordSearch(String hisPage,Model model,HttpServletRequest request){
		model.addAttribute("hisPage", hisPage);
		return KEYWORD_SEARCH;
	}
	
	/**
	 * 获取关键字联想结果
	 * @param indexPoiQueryRequest
	 * @return
	 */
	@RequestMapping("keyWordSearchList")
	@ResponseBody
	public Response_GetKeyAssociateInfo keyWordSearch(IndexPoiQueryRequest indexPoiQueryRequest){
		Response_GetKeyAssociateInfo result = hotelInfoService.getH5KeyAssociateInfo(indexPoiQueryRequest);
		return result;
	}
}
