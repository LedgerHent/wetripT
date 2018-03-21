<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="tmcH5tag" prefix="tmc"%>
<%@ page import="com.viptrip.hotelHtml5.common.ConfigConstants" %>
<!DOCTYPE html>
<html>
	<head>
		<title>酒店详情</title>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/hotelHtml5/index.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/hotelHtml5/swiper.min.css" />
		</head>
		<input type="hidden" id="homeAbroadFlag" value=""/>
		<input type="hidden" id="tripUserId" value=""/>
		<input type="hidden" id="enterpriseId" value=""/>
		<input type="hidden" id="tripType" value=""/>
		<input type="hidden" id="cityId" value=""/>
		<input type="hidden" id="cityName" value=""/>
		<input type="hidden" id="checkStartDate" value=""/>
		<input type="hidden" id="checkOutDate" value=""/>
		<input type="hidden" id="nightCount" value=""/>
		<input type="hidden" id="adultCount" value=""/>       
		<input type="hidden" id="childCount" value=""/>
		<input type="hidden" id="childAgeDl" value=""/>  
		<input type="hidden" id="roomCount" value=""/>
		<input type="hidden" id="starLvs" value=""/>
		<input type="hidden" id="hotelPrice" value=""/>
		<span  id="checkStartDateSpan" style="display:none;"></span>
		<span  id="checkOutDateSpan" style="display:none;"></span>
	<body>
		<!-- 差旅政策弹框 -->
		<div class="bounced cannot travelDesc" style="background: rgba(0,0,0,.6);">
			 <div id="cannot" style="position: absolute;left:50%;top: 50%;margin-left:-40%;margin-top:-10%;width: 80%;border-radius: 0.5rem;background: #FFFFFF;">
			    <div style="position: absolute;right: 1rem;top: 1rem;">
			     	<img src="../hotelimg/close.jpg"/ style="width: 1.4rem;height: 1.4rem;">
			    </div>
			    <p class="ns" id="travelDesc" style="color: #FF3C00;text-indent: 1rem;">*与您的差旅政策不匹配*</p>
			 </div>
	  	</div>
	  	
		<!-- 取消政策描述 -->
		<div class="bounced cannot orderPageChangeAndCancelDesc" style="background: rgba(0,0,0,.6);">
			 <div id="cannot" style="position: absolute;left:50%;top: 50%;margin-left:-40%;margin-top:-10%;width: 80%;border-radius: 0.5rem;background: #FFFFFF;">
			    <div style="position: absolute;right: 1rem;top: 1rem;">
			     	<img src="../hotelimg/close.jpg"/ style="width: 1.4rem;height: 1.4rem;">
			    </div>
			    <p class="ns" id="orderPageChangeAndCancelDesc" style="color: #333333;text-indent: 1rem;"></p>
			  </div>
		</div>	
		
		<!-- 人数上限描述 -->
		<div class="bounced cannot personDesc" style="background: rgba(0,0,0,.6);">
			 <div id="cannot" style="position: absolute;left:50%;top: 50%;margin-left:-40%;margin-top:-10%;width: 80%;border-radius: 0.5rem;background: #FFFFFF;">
			    <div style="position: absolute;right: 1rem;top: 1rem;">
			     	<img src="../hotelimg/close.jpg"/ style="width: 1.4rem;height: 1.4rem;">
			    </div>
			    <p class="ns" id="personDesc" style="color: #333333;text-indent: 1rem;"></p>
			  </div>
		</div>
		
		<!-- rpName描述 -->
		<div class="bounced cannot rpNameDesc" style="background: rgba(0,0,0,.6);">
			 <div id="cannot" style="position: absolute;left:50%;top: 50%;margin-left:-40%;margin-top:-10%;width: 80%;border-radius: 0.5rem;background: #FFFFFF;">
			    <div style="position: absolute;right: 1rem;top: 1rem;">
			     	<img src="../hotelimg/close.jpg"/ style="width: 1.4rem;height: 1.4rem;">
			    </div>
			    <p class="ns" id="rpNameDesc" style="color: #333333;text-indent: 1rem;"></p>
			  </div>
		</div>
		
		<section>
			<header class="header" id="header">
				<div>
					<img src="../hotelimg/header.png" / class="you">
				</div>
				<p>酒店详情</p>
			</header>
			<c:if test="${not empty hotelDetail }">
				<input type="hidden" id="ptHotelId" value="${hotelDetail.ptHotelId }">
				<input type="hidden" id="choiceAgreementHotelFlag" value="${hotelSearch.choiceAgreementHotelFlag }">
				<div id="banner" style="overflow: hidden;">
					<div class="swiper-container" style="height: 100%; position: relative;">
						<div class="swiper-wrapper">
							<c:forEach items="${hotelDetail.hotelPics }" var="item" >
								<div class="swiper-slide">
									<img src="${item.serviceUrl }${item.path }" />
								</div>
							</c:forEach>
						</div>
						<!-- Add Pagination -->
						<div class="swiper-pagination" style="position: absolute; bottom: 5rem;"></div>
					</div>
					<div class="detail">
						<div class="detail_lists">
							<div>
								<span class="ht">${hotelDetail.hotelChineseName }</span>
								<div class="scoring">
									<c:choose>
									     <c:when test="${hotelDetail.starLv == 5 || hotelDetail.starLv == 6}">
							            	<span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
						            	 </c:when>
						            	 <c:when test="${hotelDetail.starLv == 4 }">
							            	<span>★</span><span>★</span><span>★</span><span>★</span>
						            	 </c:when>
						            	 <c:when test="${hotelDetail.starLv == 3 }">
							            	<span>★</span><span>★</span><span>★</span>
						            	 </c:when>
						            	 <c:when test="${hotelDetail.starLv == 2 }">
							            	<span>★</span><span>★</span>
						            	 </c:when>
						            	 <c:when test="${hotelDetail.starLv == 1 }">
							            	<span>★</span>
						            	 </c:when>
						            	 <c:when test="${hotelDetail.starLv == 0 }">
						            	 </c:when>
					               </c:choose>
								</div>
							</div>
							<div>
								<span class="w">
									<c:choose>
										<c:when test="${hotelSearch.choiceAgreementHotelFlag == '0002400001' }">精选酒店</c:when>
										<c:when test="${hotelSearch.choiceAgreementHotelFlag == '0002400002' }">协议酒店</c:when>
									</c:choose>
								</span>
								<span  class="w">
									<c:choose>
										<c:when test="${hotelSearch.tripType == '0000700001' }">因公出行</c:when>
										<c:when test="${hotelSearch.tripType == '0000700002' }">因私出行</c:when>
									</c:choose>
								</span>
								<div class="w">
									<span id="startDate"></span> - <span id="endDate"></span>
								</div>
								<span class="w">共${hotelSearch.nightCount }晚</span>
							</div>
						</div>
						<div class="serve" style="margin:auto 0;">
							<a href="${pageContext.request.contextPath}/hotel/introduce.act?ptHotelId=${hotelDetail.ptHotelId}">详情/设施</a>
							<img src="../hotelimg/you.png"/ style="width:0.7rem;height:1.2rem;">
						</div>
					</div>
				</div>
				<div id="address" class="info">
					<div>
						${hotelDetail.particularAddress }
					</div>
					<div>
						<a href="${pageContext.request.contextPath}/hotel/map.act?longitude=${hotelDetail.lon}&latitude=${hotelDetail.lat}&hotelName=${hotelDetail.hotelChineseName }">地图/周边</a>
						<img src="../hotelimg/you.png" />
					</div>
				</div>
				<div id="room-case">
					<c:if test="${not empty productList && not empty productList.busiData }">
						<div class="room-title">房型情况</div>
						<ul class="roomList">
							<c:forEach items="${productList.busiData }" var="product" >
								<li>
									<div class="roomItem">
										<div class="room-type">
											<h4>${product.roomTypeCnName }</h4>
											<div class="room">
												<c:if test="${not empty product.roomArea && product.roomArea != 'null'}"><span>${product.roomArea }m²</span></c:if>
												<span><tmc:bedNameByType bedTypes="${product.bedType}" /></span>
												<c:if test="${not empty product.floor }"><span>${product.floor }层</span></c:if>
											</div>
										</div>
										<div class="room-price">
											<div>
												<span>¥</span>
												<span class="money">
													<fmt:formatNumber type="number" value="${product.lowestRoomPrice }" maxFractionDigits="0" groupingUsed="false"/>
												</span>
												<span class="since">起</span>
											</div>
											<div>
												<img src="../hotelimg/btm.png" class="roomTip"/>
											</div>
										</div>
									</div>
									<div class="roomType">
										<div class="bed-type">
											<p></p>
											<p>早餐</p>
											<p>人数上限</p>
											<p>取消政策</p>
											<p>价格</p>
											<p></p>
										</div>
										<ul class="bedList">
											<c:forEach items="${product.ratePlans }" var="ratePlan">
												<li>
													<p style="margin: auto 0;" >
														<a href="javascript:;" class="cx rpName" 
														   rpNameDesc='<c:choose>
																			<c:when test="${not empty ratePlan.rpName }">${ratePlan.rpName }</c:when>
																			<c:otherwise>标准价</c:otherwise>
																	   </c:choose>'>
															<c:choose>
																<c:when test="${not empty ratePlan.rpName }">${ratePlan.rpName }</c:when>
																<c:otherwise>标准价</c:otherwise>
															</c:choose>
														</a>
													</p>
													<p style="margin: auto 0;">
														<c:if test="${ratePlan.breakfastNumber>0 }">
															<c:out value="含早"></c:out>
														</c:if>
														<c:if test="${ratePlan.breakfastNumber==0||empty ratePlan.breakfastNumber }">
															<c:out value="不含早"></c:out>
														</c:if>
													</p>
													<p style="margin: auto 0;">
														<a href="javascript:;" class="personLimit aClick" 
														   <c:choose>
																<c:when test="${ratePlan.childCount==0 }">
																	personDesc="每间房可入住${ratePlan.adultCount }成人"
																</c:when>
																<c:otherwise>
																	personDesc="每间房可入住${ratePlan.adultCount }成人${ratePlan.childCount }儿童"
																</c:otherwise>
														   </c:choose>
														>
															${ratePlan.adultCount + ratePlan.childCount}
														</a>
													</p>
													<div style="position: relative; width:16%;margin: auto 0;">
														<a href="javascript:;" class="orderCancellationPolicy aClick" orderPageChangeAndCancelDesc="${ratePlan.orderPageChangeAndCancelDesc }">
															<c:choose>
												          		<c:when test="${ratePlan.freeCancel eq 0 }">
												          			收费取消
												          		</c:when>
												          		<c:when test="${ratePlan.freeCancel eq 1 }">
												          			限时免费取消
												          		</c:when>
												          		<c:otherwise>
												          			不可取消
												          		</c:otherwise>
												          	</c:choose>
														</a>
													</div>
													<p style="margin: auto 0;" class="travel">
														<span>¥<fmt:formatNumber type="number" value="${ratePlan.dayAvgRoomPriceCNY }" maxFractionDigits="0" groupingUsed="false"/></span>
														<c:choose>
															<c:when test="${productList.tripPolicyMatchResponseVo.defaultPolicy == '0003100001' }">
																<img src="../hotelimg/chaobiao.png" style="width: 1.05rem;height: 1.05rem;">
															</c:when>
															<c:otherwise>
																<c:if test="${productList.tripPolicyMatchResponseVo.starLimit lt hotelDetail.starLv || productList.tripPolicyMatchResponseVo.priceLimit lt ratePlan.dayAvgRoomPriceCNY}">
																	<img src="../hotelimg/chaobiao.png" style="width: 1.05rem;height: 1.05rem;">												
																</c:if>
															</c:otherwise>
														</c:choose>
													</p>
													<c:choose>
														<c:when test="${productList.tripPolicyMatchResponseVo.defaultPolicy=='0003100001' }">
															<c:choose>
																<c:when test="${productList.tripPolicyMatchResponseVo.remindType == '0000100002' }">
																	<p class="booking" style="background-color: #bababa;">
																		<a href="javascript:void(0);" >预订</a>
																	</p>
																</c:when>
																<c:otherwise>
																	<p class="booking"  onclick="booking('${ratePlan.phk }','${ratePlan.dayAvgRoomPriceCNY }','${hotelDetail.starLv }')">
																		<a href="javascript:void(0);">预订</a>
																	</p>
																</c:otherwise>
															</c:choose>
														</c:when>
														<c:when test="${productList.tripPolicyMatchResponseVo.defaultPolicy=='0003100002' }">
																<p class="booking" onclick="booking('${ratePlan.phk }','${ratePlan.dayAvgRoomPriceCNY }','${hotelDetail.starLv }')">
																	<a href="javascript:void(0);">预订</a>
																</p>
														</c:when>
														<c:when test="${productList.tripPolicyMatchResponseVo.starLimit lt hotelDetail.starLv || productList.tripPolicyMatchResponseVo.priceLimit lt ratePlan.dayAvgRoomPriceCNY}">
															<c:choose>
																<c:when test="${productList.tripPolicyMatchResponseVo.remindType == '0000100002' }">
																	<p class="booking" style="background-color: #bababa;">
																		<a href="javascript:void(0);" >预订</a>
																	</p>
																</c:when>
																<c:otherwise>
																	<p class="booking" onclick="booking('${ratePlan.phk }','${ratePlan.dayAvgRoomPriceCNY }','${hotelDetail.starLv }')">
																		<a href="javascript:void(0);">预订</a>
																	</p>
																</c:otherwise>
															</c:choose>
														</c:when>
														<c:otherwise>
															<p class="booking" onclick="booking('${ratePlan.phk }','${ratePlan.dayAvgRoomPriceCNY }','${hotelDetail.starLv }')">
																<a href="javascript:void(0);" >预订</a>
															</p>
														</c:otherwise>

													</c:choose>

												</li>
											</c:forEach>
										</ul>
									</div>
								</li>
							</c:forEach>
						</ul>
					</c:if>
				</div>
				<div id="survey">
					<div class="sry-title">酒店概况</div>
					<p class="omit">
						${hotelDetail.briefIntroduction }
					</p>
					<c:if test="${fn:length(hotelDetail.briefIntroduction)>=70 }">
						<div class="toggle">
							<a href="javascript:;" val="更多">更多</a>
							<img src="../hotelimg/xiala.png" />
						</div>
					</c:if>
				</div>
			</c:if>
		</section>
	</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/hotelHtml5/hotelSearch/hotelSearch.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/hotelHtml5/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/hotelHtml5/swiper.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/hotelHtml5/common.js"></script>
	<script type="text/javascript"  src="${pageContext.request.contextPath}/js/MyConfirm.js"></script>
	<script type="text/javascript">
		//酒店概况：更多|收起
		$('#survey .toggle').click(function() {
			$(this).find('a').attr('val', $(this).find('a').attr('val') == '更多' ? '收起' : '更多');
			$(this).find('a').html($(this).find('a').attr('val'));
			$(this).find('img').attr('src', $(this).find('img').attr('src') == '../hotelimg/xiala.png' ? '../hotelimg/dakai.png' : '../hotelimg/xiala.png');
			$(this).parents('#survey').find('p').attr('class', $(this).parents('#survey').find('p').attr('class') == 'omit' ? '' : 'omit');
		});

		//房型详情：展开|收起
		$('.roomList li').click(function() {
			if($(this).find('.roomTip').attr('src') == '../hotelimg/btm.png' || $(this).find('.roomTip').attr('src') == '../hotelimg/top.png'){
				$(this).find('.roomTip').attr('src', $(this).find('.roomTip').attr('src') == '../hotelimg/btm.png' ? '../hotelimg/top.png' : '../hotelimg/btm.png');
			}
			$(this).find('.roomType').slideToggle();
		});

		//返回酒店列表页
		$('.header .you').click(function() {
			window.location.href = "${pageContext.request.contextPath}/hotelSearch/init.act";
		});

		//图片滑动轮播
		var swiper = new Swiper('.swiper-container', {
			pagination: '.swiper-pagination',
			paginationClickable: true
		});

		//差旅标准弹框
		$('.travel').each(function() {
			$(this).click(function() {
				if($(this).find('img').attr('src') == '../hotelimg/chaobiao.png' ){
					$('.travelDesc').fadeIn();
				}
				return false;
			});
		});
		//取消政策弹框
		$('.orderCancellationPolicy').each(function() {
			$(this).click(function() {
				$("#orderPageChangeAndCancelDesc").html($(this).attr("orderPageChangeAndCancelDesc"));
				$('.orderPageChangeAndCancelDesc').fadeIn();
				return false;
			});
		});
		//人数上限弹框
		$('.personLimit').each(function() {
			$(this).click(function() {
				$("#personDesc").html($(this).attr("personDesc"));
				$('.personDesc').fadeIn();
				return false;
			});
		});
		//rpName弹框
		$('.rpName').each(function() {
			$(this).click(function() {
				var htmlStr = $.trim($(this).html());
				if(htmlStr.length > 4) {
					$("#rpNameDesc").html($(this).attr("rpNameDesc"));
					$('.rpNameDesc').fadeIn();
				}
				return false;
			});
		});
		//预订
		$('.booking').each(function() {
			$(this).click(function() {
				return false;
			});
		});
		

		//删除除了div之外任何区域，遮罩层隐藏
		function hideLayer(e, Obj, idAttr) {
			var e = e || window.event; //浏览器兼容性   
			var elem = e.target || e.srcElement;
			while(elem) { //循环判断至跟节点，防止点击的是div子元素   
				if(elem.id && elem.id == Obj) {
					return;
				}
				elem = elem.parentNode;
			}
			$(idAttr).parents('.bounced').css('display', 'none'); //点击的不是div或其子元素 
		}
		
		$('.travelDesc').bind('click', function(e) {
			hideLayer(e, 'travelDesc', '#travelDesc');
		});
		$('.orderPageChangeAndCancelDesc').bind('click', function(e) {
			hideLayer(e, 'orderPageChangeAndCancelDesc', '#orderPageChangeAndCancelDesc');
		});
		$('.personDesc').bind('click', function(e) {
			hideLayer(e, 'personDesc', '#personDesc');
		});
		$('.rpNameDesc').bind('click', function(e) {
			hideLayer(e, 'rpNameDesc', '#rpNameDesc');
		});
	</script>
	<script type="text/javascript">
		$(function(){
			//初始化页面数据
			getCookieToCommonPage("hotelList");
			$("#startDate").html(getDateStr($("#checkStartDateSpan").html()));
			$("#endDate").html(getDateStr($("#checkOutDateSpan").html()));
		});
		
		//入住/离店日期拼接
		function getDateStr(date){
			if(date != "" && date.indexOf("-") != -1){
				var dateArr = date.split("-");
				date = dateArr[0]+"月"+dateArr[1]+"日";
			}
			return date;
		}
		
		$(document).ready(function() {
			//限制字符个数
			$('.cx').each(function() {
				var maxwidth = 4;
				var htmlStr = $.trim($(this).html());
				if(htmlStr.length > maxwidth) {
					htmlStr = htmlStr.substring(0, maxwidth);
					$(this).html(htmlStr + '...');
				}else{
					$(this).html(htmlStr);
				}
			});
		});
	</script>
	<script type="text/javascript">
		//预订
		function booking(phk,dayAvgRoomPriceCNY,hotelStar){
			$(this).LoadingShow();
			var params = new Object();
			params.phk = phk;
			params.ptHotelId = $("#ptHotelId").val();
			params.roomCount = $("#roomCount").val();
			params.adultCount = $("#adultCount").val();
			params.nightCount = $("#nightCount").val();
			params.checkStartDateStr = $("#checkStartDate").val();
			
			params.cityId = $("#cityId").val();
			params.homeAbroadFlag = $("#homeAbroadFlag").val();
			params.tripType = $("#tripType").val();
			params.tripUserId = $("#tripUserId").val();
			params.choiceAgreementHotelFlag = $("#choiceAgreementHotelFlag").val();
			$.ajax({
				type:"POST",
				data:$.param(params),
				url:base_path+"/hotelSearch/getProductDetail.act",
				success:function(roomTypeBaseResponse){
					$(this).LoadingHide();
					if(roomTypeBaseResponse.code=="1000" || roomTypeBaseResponse.code=="1001"){
						var oldRoomPrice = parseInt(dayAvgRoomPriceCNY);
						var newRoomPrice = parseInt(roomTypeBaseResponse.ratePlans[0].dayAvgRoomPriceCNY);
						if(oldRoomPrice != newRoomPrice){
							var tripPolicy = roomTypeBaseResponse.tripPolicyMatchResponseVo;
							//弹框校验
							changePriceRemind(params, tripPolicy, newRoomPrice, hotelStar);
						}else{
							window.location.href = "${pageContext.request.contextPath}/neworder/toNewOrderWriteBefore.act?"+$.param(params);
						}
					}else{
						$(this).MyConfirm({
				            content: roomTypeBaseResponse.message
				        });
					}
				},
				error: function (xhr, status, error) {
					$(this).LoadingHide();
			    }
			});
		}
		
		
		//变价提示
		function changePriceRemind(params, tripPolicy, newRoomPrice, hotelStar){
			var defaultPolicy = tripPolicy.defaultPolicy;	//未匹配差旅政策默认值
			var remindType = tripPolicy.remindType;			//超标提醒方式
			var priceLimit = tripPolicy.priceLimit;			//金额上限
			var starLimit = tripPolicy.starLimit;			//星级上限
			
			var title = "变价提示";
			var content = "";
			var hasCancelBtn = true;
			var confirmBtnName = "确认预订";
			var cancelBtnName = "我在想想";
			
			if(defaultPolicy=="0003100001"){
				if(remindType == '0000100004'){
					content = "变动提醒", "您当前预订的酒店房价已调整为¥ " + newRoomPrice + "/间。是否继续预订？";
				}else{
					if(remindType == '0000100002'){
						content = '您当前预订的酒店房价已调整为¥ ' + newRoomPrice + '/间。与您的差旅政策不匹配，请尝试选择其它房型或酒店。';
						hasCancelBtn = false;
			            confirmBtnName = "确定";
					}else{
						content = "您当前预订的酒店房价已调整为¥ " + newRoomPrice + "/间。已超出差旅政策限制，是否继续预订？";
					}
				}
			}else{
				newRoomPrice = parseInt(newRoomPrice);
				hotelStar = parseInt(hotelStar);
				priceLimit = parseInt(priceLimit);
				starLimit = parseInt(starLimit);
				if(params.homeAbroadFlag == '-99'){	//国际
					if(newRoomPrice > priceLimit){	//超标
						if(remindType == '0000100004'){
							content = "您当前预订的酒店房价已调整为¥ " + newRoomPrice + "/间。与您的差旅政策不匹配，请尝试选择其它房型或酒店。";
						}else{
							if(remindType == '0000100002'){
					            content = "您当前预订的酒店房价已调整为¥ " + newRoomPrice + "/间。与您的差旅政策不匹配，请尝试选择其它房型或酒店。";
					            hasCancelBtn = false;
					            confirmBtnName = "确定";
							}else{
								content = "您当前预订的酒店房价已调整为¥ " + newRoomPrice + "/间。已超出差旅政策限制，是否继续预订？";
							}
						}
					}else{
						content = "变动提醒", "您当前预订的酒店房价已调整为¥ " + newRoomPrice + "/间。是否继续预订？";
					}
				}else{	//国内
					if(newRoomPrice > priceLimit || hotelStar > starLimit){	//超标
						if(remindType == '0000100004'){
							content = "变动提醒", "您当前预订的酒店房价已调整为¥ " + newRoomPrice + "/间。是否继续预订？";
						}else{
							if(remindType == '0000100002'){
								content = '您当前预订的酒店房价已调整为¥ ' + newRoomPrice + '/间。与您的差旅政策不匹配，请尝试选择其它房型或酒店。';
								hasCancelBtn = false;
					            confirmBtnName = "确定";
							}else{
								content = "您当前预订的酒店房价已调整为¥ " + newRoomPrice + "/间。已超出差旅政策限制，是否继续预订？";
							}
						}
					}else{
						content = "您当前预订的酒店房价已调整为¥ " + newRoomPrice + "/间。是否继续预订？";
					}
				}
			}
			//弹框
			showMessage(title,content,hasCancelBtn,remindType,params,confirmBtnName,cancelBtnName);
		}
		
		
		function showMessage(title,content,hasCancelBtn,remindType,params,confirmBtnName,cancelBtnName){
	  		$(this).MyConfirm({
	  			title: title, 
	            content: content,
	            hasCancelBtn: hasCancelBtn,
	            confirmBtnName: confirmBtnName,
	            cancelBtnName: cancelBtnName
	        },function callback(isConfirm){
	    		if(isConfirm && hasCancelBtn){
	    			if(remindType != '0000100002'){
		  				$(this).LoadingShow();
		  				var url = "${pageContext.request.contextPath}/neworder/toNewOrderWriteBefore.act?"+$.param(params);
		  				window.location.href = url;
		  			}
	    		}
	    	});
	  	}
	</script>
</html>