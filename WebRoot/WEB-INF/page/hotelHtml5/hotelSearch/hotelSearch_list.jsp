<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="com.viptrip.hotelHtml5.common.ConfigConstants" %>

<!DOCTYPE html>
<html>
	<head>
		<title>酒店查询列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/hotelHtml5/index.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/hotelHtml5/calendar.css" />
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/hotelHtml5/jquery-1.8.2.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/hotelHtml5/date.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/MyConfirm.js"></script>
		<style type="text/css">
			img{
				border-radius:10px;
			}
			.tripPolicyRemindDesc{
				color: #ff0000;
			    font-size: 1rem;
			    position:absolute;
			}
		</style>
	</head>
	<body>
		<input type="hidden" id="agreementHotelResultFlag" value=${agreementHotelResultFlag }>
		<input type="hidden" id="hotelSearchCode" value="${hotelInfo.code }">
		<input type="hidden" id="hotelSearchMsg" value="${hotelInfo.msg }">
		
		<!-- 精选酒店 -->
		<ul class="item choiceHotel_list" >
			<c:choose>
				<c:when test="${hotelInfo.code eq '1000' }">
					<c:choose>
						<c:when test="${not empty choicePageInfo }">
							<input type="hidden" class="choiceHasNextPage" value="${choicePageInfo.hasNextPage }">
							<c:choose>
								<c:when test="${not empty choicePageInfo.list }">
						  			<c:forEach items="${choicePageInfo.list }" var="choiceHotel">
										<li id="${choiceHotel.ptHotelId }">
											<div class="hotel-img">
												<img alt="${choiceHotel.hotelPics[0].name }" src="${choiceHotel.hotelPics[0].serviceUrl }${choiceHotel.hotelPics[0].path }" >
											</div>
											<div class="h-news">
												<div class="h-right">
													<h2>${choiceHotel.hotelChineseName }</h2>
													<div class="scoring" value="五星/豪华">
														<c:choose>
											            	 <c:when test="${choiceHotel.starLv == 5 || choiceHotel.starLv == 6}">
												            	<span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
											            	 </c:when>
											            	 <c:when test="${choiceHotel.starLv == 4 }">
												            	<span>★</span><span>★</span><span>★</span><span>★</span>
											            	 </c:when>
											            	 <c:when test="${choiceHotel.starLv == 3 }">
												            	<span>★</span><span>★</span><span>★</span>
											            	 </c:when>
											            	 <c:when test="${choiceHotel.starLv == 2 }">
												            	<span>★</span><span>★</span>
											            	 </c:when>
											            	 <c:when test="${choiceHotel.starLv == 1 }">
												            	<span>★</span>
											            	 </c:when>
											            	 <c:when test="${choiceHotel.starLv == 0 }">
											            	 </c:when>
										               </c:choose>
													</div>
													<p class="adr">
														${choiceHotel.particularAddress }
													</p>
												</div>
				
												<div>
													<c:if test="${choiceHotel.tripPolicyRemindType !='0000100004' }"><p class="tripPolicyRemindDesc">*与您的差旅政策不匹配*</p></c:if>
													<div class="float">
														<span>¥</span>
														<span class="money" value="1001-1300">
															<fmt:formatNumber type="number" value="${choiceHotel.lowestPrice }" maxFractionDigits="0" groupingUsed="false"/>
														</span>
														<span class="since">起</span>
													</div>
												</div>
											</div>
										</li>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<c:if test="${hotelRequest.choicePageNum == 1 }"><li>没有查询到任何结果，请修改查询条件后重试。</li></c:if>
								</c:otherwise>
							</c:choose>
						</c:when>
					</c:choose>
				</c:when>
				<c:otherwise>
					<c:if test="${hotelRequest.choicePageNum == 1 }"><li>没有查询到任何结果，请修改查询条件后重试。</li></c:if>
				</c:otherwise>
			</c:choose>
		</ul>
	
		<!-- 协议酒店 -->
		<ul class="item agreementHotel_list"  style="display:none;">
			<c:choose>
				<c:when test="${hotelInfo.code eq '1000' }">
					<c:choose>
						<c:when test="${not empty agreementPageInfo }">
							<input type="hidden" class="agreementHasNextPage" value="${agreementPageInfo.hasNextPage }">
							<c:choose>
								<c:when test="${not empty agreementPageInfo.list  }">
						  			<c:forEach items="${agreementPageInfo.list }" var="agreementHotel">
										<li id="${agreementHotel.ptHotelId }">
											<div class="hotel-img">
												<img alt="${agreementHotel.hotelPics[0].name }" src="${agreementHotel.hotelPics[0].serviceUrl }${agreementHotel.hotelPics[0].path }" >
											</div>
											<div class="h-news">
												<div class="h-right">
													<h2>${agreementHotel.hotelChineseName }</h2>
													<div class="scoring" value="五星/豪华">
														<c:choose>
											            	 <c:when test="${agreementHotel.starLv == 5 || agreementHotel.starLv == 6}">
												            	<span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
											            	 </c:when>
											            	 <c:when test="${agreementHotel.starLv == 4 }">
												            	<span>★</span><span>★</span><span>★</span><span>★</span>
											            	 </c:when>
											            	 <c:when test="${agreementHotel.starLv == 3 }">
												            	<span>★</span><span>★</span><span>★</span>
											            	 </c:when>
											            	 <c:when test="${agreementHotel.starLv == 2 }">
												            	<span>★</span><span>★</span>
											            	 </c:when>
											            	 <c:when test="${agreementHotel.starLv == 1 }">
												            	<span>★</span>
											            	 </c:when>
											            	 <c:when test="${agreementHotel.starLv == 0 }">
											            	 </c:when>
										               </c:choose>
													</div>
													<p class="adr">
														${agreementHotel.particularAddress }
													</p>
												</div>
												<div>
													<c:if test="${agreementHotel.tripPolicyRemindType !='0000100004' }"><p class="tripPolicyRemindDesc">*与您的差旅政策不匹配*</p></c:if>
													<div class="float">
														<span>¥</span>
														<span class="money" value="1001-1300">
															<fmt:formatNumber type="number" value="${agreementHotel.lowestPrice }" maxFractionDigits="0" groupingUsed="false"/>
														</span>
														<span class="since">起</span>
													</div>
												</div>
											</div>
										</li>
									</c:forEach>
								</c:when>
							</c:choose>
						</c:when>
						<c:otherwise>
							<c:if test="${hotelRequest.agreementPageNum == 1 }"><li>没有查询到任何结果，请修改查询条件后重试。</li></c:if>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<c:if test="${hotelRequest.agreementPageNum == 1 }"><li>没有查询到任何结果，请修改查询条件后重试。</li></c:if>
				</c:otherwise>
			</c:choose>
		</ul>
	</body>
	<script type="text/javascript">
		//跳转酒店详情页
		$('.item li').click(function() {
			$(this).LoadingShow();
			var ptHotelId = $(this).attr("id");
			var params = new Object();
			params.ptHotelId = ptHotelId;
			params.nightCount = '${param.nightCount }';
			params.adultCount = '${param.adultCount }';
			params.childAgeDl = '${param.childAgeDl }';
			params.roomCount = '${param.roomCount }';
			params.checkStartDateStr = '${param.checkStartDateStr }';
			
			params.cityId  = '${param.cityId }';
			params.tripType = '${param.tripType }';
			params.tripUserId = '${param.tripUserId }';
			params.enterpriseId = '${param.enterpriseId }';
			params.enterpriseName = '${param.enterpriseName }';
			params.homeAbroadFlag = '${param.homeAbroadFlag }';
			
			$(".hotel-tab li").each(function(){
			   if($(this).attr("class")=='highlight' && $.trim($(this).text())=="精选酒店"){
				   params.choiceAgreementHotelFlag = '0002400001';
		       }else if($(this).attr("class")=='highlight' && $.trim($(this).text())=="协议酒店"){
		    	   params.choiceAgreementHotelFlag = '0002400002';
		       }
			});
			var data=$.param(params);
			var url = "${pageContext.request.contextPath}/hotelSearch/getHotelDetail.act?"+data;
			saveCommonInfoToCookie("hotelList");
			window.location.href=url;	
			$(this).LoadingHide();
		});
	</script>
</html>