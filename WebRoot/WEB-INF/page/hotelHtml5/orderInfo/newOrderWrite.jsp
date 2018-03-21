<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="tmcH5tag" prefix="tmc"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>订单填写</title>
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/hotelHtml5/index.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/hotelHtml5/jquery-1.8.2.min.js"></script>
	<script type="text/javascript"  src="${pageContext.request.contextPath}/js/MyConfirm.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/hotelHtml5/common.js"></script>
</head>

<body>
<script type="text/javascript">
	var bookingUserId = '${hotelBooking.bookingUserId }';
	var tripType = '${hotelBooking.tripType }';
	var pageTempletFlag = '${hotelBooking.pageTempletFlag }';
	var roomCount = '${hotelBooking.roomCount }';
	var roomToSell = '${hotelBooking.roomToSell }';	//可售房量
	var minRoom = '${hotelBooking.minRoom }';	//最小预订房间量
	
	//返回酒店详情所需参数
	var ptHotelId = '${hotelBooking.ptHotelId }';
	var nightCount = '${hotelBooking.nightCount }';
	var adultCount = '${hotelBooking.adultCount }';
	var childCount = '${fn:length(hotelBooking.childAgeDlList) }';
	var childAgeDl = '${hotelBooking.childAgeDl }';
	var checkStartDateStr = '${hotelBooking.checkInDateStr }';
	var cityId  = '${hotelBooking.cityId }';
	var bookingEnterpId = '${hotelBooking.bookingEnterpId }';
	var choiceAgreementHotelFlag = '${hotelBooking.choiceAgreementHotelFlag }';
	var homeAbroadFlag = '${hotelBooking.hotelType eq "0001000001" ? "36916" : "-99" }';
	
	var isShowGuest = '${isShowGuest }';

	//每间房入住总人数
	var checkInMan = parseInt(adultCount) + parseInt(childCount);
</script>
<div class="bounced">
	<div class="pages">
		<header class="header" style="background: #00AFEC;padding: 0 1rem 1rem 1rem; ">
			<div class="h_t" style="display: flex;flex-direction: row;">
				<div>
					<img src="../hotelimg/header.png" / class="you">
				</div>
				<p id="bounced_p">添加客人信息</p>
				<div>
					<!-- <img src="../hotelimg/addBtn.png" / class="addBtn"> -->
				</div>
			</div>
			<div class="inbox" id="out">
				<div class="searchd" id="ser_box" style="display:flex;flex-direction:row;">
					<div style="margin: auto 0;">
						<img src="../hotelimg/search.png">
					</div>
					<div>
						<input type="text" name="" id="ipt" value="" placeholder="查找其他人员信息" onblur="" oninput="queryCustomerInfo(this)" style="vertical-align:middle;">
					</div>
				</div>
			</div>
		</header>
		<section id="customer_info_section"></section>
	</div>
</div>
<form id="sform" action="" method="post">
	<input type="hidden" id="tripType" name="tripType" value="${hotelBooking.tripType }" />
	
	<input type="hidden" id="childCount" name="childCount" value="${fn:length(hotelBooking.childAgeDlList) }" />
	<!-- 预订人信息 -->
	<input type="hidden" id="bookingUserId" name="bookingUserId" value="${hotelBooking.bookingUserId }" />
	<input type="hidden" id="bookingUserName" name="bookingUserName" value="${hotelBooking.bookingUserName }" />
	<input type="hidden" id="bookingDeptId" name="bookingDeptId" value="${hotelBooking.bookingDeptId }" />
	<input type="hidden" id="bookingDeptName" name="bookingDeptName" value="${hotelBooking.bookingDeptName }" />
	<input type="hidden" id="bookingEnterpId" name="bookingEnterpId" value="${hotelBooking.bookingEnterpId }" />
	<input type="hidden" id="bookingEnterpName" name="bookingEnterpName" value="${hotelBooking.bookingEnterpName }" />
	<input type="hidden" id="bookingUser_mobile" name="bookingUser_mobile" value="${hotelBooking.staffGroup.mobile }" />
	<input type="hidden" id="bookingUser_email" name="bookingUser_email" value="${hotelBooking.staffGroup.email }" />
	<input type="hidden" id="bookingUser_birthday" name="bookingUser_birthday" value="${hotelBooking.staffGroup.birthday }" />
	
	<input type="hidden" id="pageTempletFlag" name="pageTempletFlag" value="${hotelBooking.pageTempletFlag }" />
	<input type="hidden" name="phk" value="${hotelBooking.phk }" />
	<input type="hidden" name="tmcOrderHotel.bookingCheckinDate" value='<fmt:formatDate value="${hotelBooking.checkInDate }" pattern="yyyy-MM-dd"/>' />
	<input type="hidden" name="tmcOrderHotel.bookingCheckoutDate" value='<fmt:formatDate value="${hotelBooking.checkOutDate }" pattern="yyyy-MM-dd"/>' />
	<input type="hidden" id="roomCount" name="tmcOrderHotel.roomNum" value='${hotelBooking.roomCount }' />
	
	<input type="hidden" id="bookingTotalPrice" name="tmcOrderHotel.bookingTotalPrice" value='${hotelBooking.bookingTotalPrice }' />
	
	<!-- 页面标志，根据此标志判断当前是由酒店详情预订页面进来还是由预订后半部分页面返回而来  true是返回，false是第一次进入-->
	<input type="hidden" id="page_flag" value="${not empty orderDetail }"/>
	
	
	<section>
		<header class="travel_title">
			<div class="top_m">
				<div>
					<img src="../hotelimg/header.png" / class="you">
				</div>
				<p>${hotelBooking.hotelName }</p>
			</div>
			<div id="write">
				<p class="model">${hotelBooking.roomTypeName }(${hotelBooking.rateplanName })(<tmc:bedNameByType bedTypes="${hotelBooking.bedType }" />)</p>
				<div class="dateT">
					<div class="enter">
						<span>入住：</span>
						<span class="enterDate"><fmt:formatDate value="${hotelBooking.checkInDate }" pattern="MM-dd"/></span>
						<span class="enterWeek">(<fmt:formatDate value="${hotelBooking.checkInDate }" pattern="EE"/>)</span>
					</div>
					<div class="leave">
						<span>离店：</span>
						<span class="leaveDate"><fmt:formatDate value="${hotelBooking.checkOutDate }" pattern="MM-dd"/></span>
						<span class="leaveWeek">(<fmt:formatDate value="${hotelBooking.checkOutDate }" pattern="EE"/>)</span>
					</div>
					<div class="night">共${hotelBooking.nightCount }晚</div>
				</div>
				<div class="dateT">
					<span>
						<c:if test="${hotelBooking.tripType eq '0000700001' }">因公出行</c:if>
						<c:if test="${hotelBooking.tripType eq '0000700002' }">因私出行</c:if>
					</span>
					<p>
						<span>单价</span>
						<span class="money_">¥<fmt:formatNumber type="number" groupingUsed="false" pattern="0.00" value="${hotelBooking.productPriceDays[0].finalPriceCNY }" maxFractionDigits="2"/></span>
					</p>
				</div>
			</div>
		</header>
		<div id="rooms_num">
			<div class="room_all">
				<div>
					<span>房间数</span>
				</div>
				<div class="compute">
					<div class="minus operation">－</div>
					<div class="amount">
						<span class="j" id="roomCounSpan">
							${not empty orderDetail ? orderDetail.tmcOrderHotel.roomNum : hotelBooking.roomCount }
						</span>
						<span>间</span></div>
					<div class="plus operation">＋</div>
				</div>
			</div>
		</div>
		<div id="item">
			<c:set var="guestIndex" value="0"></c:set>
			<ul class="i">
				<c:if test="${not empty orderDetail }">
					<c:forEach begin="1" end="${not empty orderDetail ? orderDetail.tmcOrderHotel.roomNum : hotelBooking.roomCount }" var="roomIndex" >
						<li class="level">
							<div class="rooms_o">房间${roomIndex }</div>
							<div class="check_person">
								<p>入住人</p>
								<div>
									<img src="../hotelimg/add.png" / class="add">
								</div>
							</div>
							<ul class="check_list">
								<c:forEach items="${orderDetail.tmcOrderGuestList }" var="guest" varStatus="g_i">
									<c:if test="${guest.roomNo eq roomIndex }">
										
										<li class="guest_li">
											<div class="f">
												<div>
													<span class="w stay">入住人</span>
													<span>${guest.guestName }</span>
													<input type="hidden" name="tmcOrderGuestList[${guestIndex }].guestId" value="${guest.guestId }"/>
													<input type="hidden" name="tmcOrderGuestList[${guestIndex }].guestName" value="${guest.guestName }"/>
													<input type="hidden" name="tmcOrderGuestList[${guestIndex }].roomNo" value="${guest.roomNo }"/>
													<input type="hidden" name="tmcOrderGuestList[${guestIndex }].guestType" value="${guest.guestType }"/>
													<input type="hidden" name="tmcOrderGuestList[${guestIndex }].deptId" value="${guest.deptId }"/>
													<input type="hidden" name="tmcOrderGuestList[${guestIndex}].deptName" value="${guest.deptName }"/>
													<input type="hidden" name="tmcOrderGuestList[${guestIndex}].ageType" value="${guest.ageType }"/>
												</div>
												<div>
													<img src="../hotelimg/jian.png" / class="jian">
												</div>
											</div>
											<div>
												<span class="w ash">联系电话</span>
												<input type="text" name="tmcOrderGuestList[${guestIndex }].tel" id="" value="${guest.tel }" / class="inputs">
											</div>
											<div>
												<span class="w ash">电子邮箱</span>
												<input type="text" name="tmcOrderGuestList[${guestIndex }].email" id="" value="${guest.email }" / class="inputs">
											</div>
										</li>
										<c:set var="guestIndex" value="${guestIndex+1 }"></c:set>
									</c:if>
								</c:forEach>
							</ul>
						</li>
					</c:forEach>
				</c:if>
			</ul>
		</div>
		<div class="linkman" id="linkman">
			<div class="check_person">
				<p>联系人</p>
				<div>
					<img src="../hotelimg/add.png" class="add">
				</div>
			</div>
			<ul class="check_list">
				<c:if test="${not empty orderDetail }">
					<c:forEach items="${orderDetail.tmcOrderContactsList }" var="contact" varStatus="c_i">
						<li class="contact_li">
							<div class="f">
								<div>
									<span class="w stay">联系人</span>
									<span>${contact.contact }</span>
									<input type="hidden" name="tmcOrderContactsList[${c_i.index }].contact" value="${contact.contact }">
								</div>
								<div>
									<img src="../hotelimg/jian.png" class="jian">
								</div>
							</div>
							<div>
								<span class="w ash">联系电话</span>
								<input type="text" name="tmcOrderContactsList[${c_i.index }].tel" id="" value="${contact.tel }" class="inputs">
							</div>
							<div>
								<span class="w ash">电子邮箱</span>
								<input type="text" name="tmcOrderContactsList[${c_i.index }].email" id="" value="${contact.email }" class="inputs">
							</div>
						</li>
					</c:forEach>
				</c:if>
				<c:if test="${empty orderDetail }">
					<li class="contact_li">
							<div class="f">
								<div>
									<span class="w stay">联系人</span>
									<span>${hotelBooking.bookingUserName }</span>
									<input type="hidden" name="tmcOrderContactsList[0].contact" value="${hotelBooking.bookingUserName }">
								</div>
								<div>
									<img src="../hotelimg/jian.png" class="jian">
								</div>
							</div>
							<div>
								<span class="w ash">联系电话</span>
								<input type="text" name="tmcOrderContactsList[0].tel" id="" value="${hotelBooking.staffGroup.mobile }" class="inputs">
							</div>
							<div>
								<span class="w ash">电子邮箱</span>
								<input type="text" name="tmcOrderContactsList[0].email" id="" value="${hotelBooking.staffGroup.email }" class="inputs">
							</div>
						</li>
				</c:if>
			</ul>
		</div>
		<div id="next_step" onclick="submit_one()">
			<a href="javascript:void(0)">下一步</a>
		</div>
	</section>
<input type="hidden" id="hotelBookingRequest" name="hotelBookingRequest" value='${hotelBookingRequest }'/>
</form>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/hotelHtml5/orderInfo/newOrderWrite.js"></script>
</body>
</html>