<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="tmcH5tag" prefix="tmc"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>新单填写</title>
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/hotelHtml5/index.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/hotelHtml5/jquery-1.8.2.min.js"></script>
	<script type="text/javascript"  src="${pageContext.request.contextPath}/js/MyConfirm.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/hotelHtml5/common.js"></script>
	<script type="text/javascript">
	var tripType = '${hotelBooking.tripType }';
	var bookingUserId = '${hotelBooking.bookingUserId }';
	var bookingEnterpId = '${hotelBooking.bookingEnterpId }';
	var choiceAgreementHotelFlag = '${hotelBooking.choiceAgreementHotelFlag }';
	var roomCount = '${hotelBooking.roomCount }';
	var newRoomCount = '${orderDetail.tmcOrderHotel.roomNum }';
	var nightCount = '${hotelBooking.nightCount }';
	var bookingTotalPrice = '${hotelBooking.bookingTotalPrice }' == '' ? 0 : '${hotelBooking.bookingTotalPrice }';
	var roomFee = '${hotelBooking.roomFee }' == '' ? 0 : '${hotelBooking.roomFee }';
	var taxFee = '${hotelBooking.taxFee }' == '' ? 0 : '${hotelBooking.taxFee }';
	var userIntegral = '${hotelBooking.userIntegral}' == '' ? parseFloat(0) : parseFloat('${hotelBooking.userIntegral }');	//折扣
	var userInterest = '${hotelBooking.userInterest}' == '' ? parseFloat(0) : parseFloat('${hotelBooking.userInterest }');	//金旅豆
	
	var hotelType = '${hotelBooking.hotelType }';
	var hotelStar = '${hotelBooking.hotelStar }';
	var hotelRoomPrice = '${hotelBooking.productPriceDays[0].finalPriceCNY }';
	var areaId = '${hotelBooking.cityId }';
	</script>
</head>

<body>
<div id="main_div">
	<div class="bounced apt">
		<div id="apt" style="overflow-y: scroll;position: absolute;left: 0;top: 0;width: 100%;height: 100%;">
			<header class="nav" id="header">
			<div>
				<img src="../hotelimg/header.png" class="you">
			</div>
			<div class="inbox" id="out">
				<div class="search" id="ser_box" style="display:flex;flex-direction:row;">
					<div style="margin:auto 0;"><img src="../hotelimg/search.png"></div>
					<div>
						<input type="text" name="" id="myInput" value="" placeholder="查找公司" oninput="queryCompanyApt(this)" style="vertical-align:middle;" >
					</div>
				</div>
			</div>
			</header>
			<section id="myTable">
			</section>
		</div>
	</div>
	<!------比例分摊/金额分摊------->
	<div class="bounced share_">
		<div class="payment-con" id="share_" >
			<div class="payment-title">
				<p>分摊方式</p>
			</div>
			<div class="share-choose">
				<div class="rows">
					<span class="color">比例分摊(%)</span>
					<input type="hidden" value="0001500002" name="c_shareType" />
					<div style="margin: auto 0;">
						<img src="../hotelimg/choose.png" class="selected">
					</div>
				</div>
				<div class="rows">
					<span>金额分摊(元)</span>
					<input type="hidden" value="0001500001" name="c_shareType"/>
					<div style="margin: auto 0;">
						<img src="../hotelimg/choose.png">
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="bounced examine" >
		<div id="policy_">
			<div style="display:flex;flex-direction:row;justify-content:space-around;">
				<p class="instre">审批政策说明</p>
				<div style="position:absolute;top:1rem;right:1rem;" class="clsoeB">
					<img src="../hotelimg/close.jpg" style="width:1.4rem;height:1.4rem;">
				</div>
			</div>
			<div class="steps" id="policy_approvalDetail">
			</div>
		</div>
	</div>
	<!----明细----->
	<div class="bounced amount_">
		<div id="matter"></div>
	</div>
	<!------支付方式------->
	<div class="bounced payment">
		<div class="payment-con" id="tested">
			<div class="payment-title">
				<p>结算方式类型</p>
			</div>
			<div class="payment-choose">
				<c:forEach items="${hotelBooking.commonPayMethodList }" var="payType" varStatus="payType_i">
					<div class="rows">
						<span <c:if test='${payType_i.index eq 0 }'>class="color"</c:if>>${payType.n }</span>
						<input type="hidden" class="payType_i" value="${payType.v }"/>
						<div style="margin: auto 0;">
							<img src="../hotelimg/choose.png" <c:if test='${payType_i.index eq 0 }'>class="selected"</c:if> >
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	
	<!-------使用积分的弹框样式------->
	<div class="bounced prompting">
		<div id="prompting">
			<div style="position:absolute;top:1rem;right:1rem;" class="clsoeB">
				<img src="../hotelimg/close.jpg" style="width:1.4rem;height:1.4rem;">
			</div>
			<div>
				<p class="p_title">1.积分有什么用处？</p>
				<p>积分是凯撒商旅给予会员的回馈，可直接消费抵现。某些特例产品不能使用积分进行抵扣。</p>
			</div>
			<div>
				<p class="p_title">2.积分如何抵现？</p>
				<p>积分可用于购买产品时抵扣订单金额。预订时可填写使用积分的数值，相应从较早产生的开始扣除。</p>
			</div>
		</div>
	</div>
	
	<!-----------使用金旅豆的弹框样式---------->
	<div class="bounced interest_">
		<div id="interest_">
			<div style="position:absolute;top:1rem;right:1rem;" class="clsoeB">
				<img src="../hotelimg/close.jpg" style="width:1.4rem;height:1.4rem;">
			</div>
			<div>
				<p class="p_title">1.如何获得“金旅豆”？</p>
				<p>企业通过预存可获得金旅豆，金旅豆可购买凯撒商旅机票、酒店、机加酒等产品，也可选择直接冲抵账单</p>
			</div>
			<div>
				<p class="p_title">2.如何使用金旅豆？</p>
				<p>企业员工在差旅出行下单时，可在支付环节使用金旅豆冲抵订单，并获得会员积分；</p>
			</div>
		</div>
	</div>
	<form action="" method="post" id="sform">
		<input type="hidden" name="payType" id="payType" value="${hotelBooking.privatePayMethodList[0].v }"/>
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
			<div id="numbering" class="order_" <c:if test='${hotelBooking.enterpProjectNo eq 0 }'>style="display: none;"</c:if> >
				<div class="aw">
					<p>项目名称/编号</p>
				</div>
				<input type="text" name="projectName" id="" value="" placeholder="请输入项目名称/编号" />
			</div>
			<div id="proportion" class="order_">
				<div class="pro_title">费用归属与比例分摊</div>
				<div class="choose_share">
					<div style="margin: auto 0;flex: 1;">
						<input type="text" name="" id="" value="比例分摊(%)" placeholder="比例分摊/金额分摊" readonly unselectable="on" onfocus="this.blur()" />
					</div>
					<div>
						<img src="../hotelimg/you.png"/ class="you">
					</div>
				</div>
					<input type="hidden" id="cost_dept_length" value="${fn:length(costDepts)}" />
				<ul>
					<c:forEach items="${costDepts }" var="costD" varStatus="cost_i">
						<li class="department" id="costDept_${cost_i.index }">
							<input type="hidden" class="costId" id="costDept_${cost_i.index }_id" name="tmcOrderCostShareList[${cost_i.index }].shareDeptId" value="${costD.v }" />
							<input type="hidden" id="costDept_${cost_i.index }_name" name="tmcOrderCostShareList[${cost_i.index }].shareDeptName" value="${costD.n }" />
							<div style="display: flex;flex-direction: row;" class="aw">
								<p class="dptm cx">${costD.n }</p>
								<div style="margin: auto 0;">
									<img src="../hotelimg/you.png" / class="you">
								</div>
							</div>
							<input type="text" name="tmcOrderCostShareList[${cost_i.index }].shareValue" id="" value="" placeholder="请输入分摊百分比或金额" onblur="validateShareValue(this)"/>
							<input type="hidden" name="tmcOrderCostShareList[${cost_i.index }].shareType" class="shareType" value="0001500002" />
							<div style="margin: auto 0;">
								<c:choose>
									<c:when test="${cost_i.index eq 0 }"><img src="../hotelimg/add.png" / class="add"></c:when>
									<c:otherwise><img src="../hotelimg/jian.png" / class="jian"></c:otherwise>
								</c:choose>
							</div>
						</li>
					</c:forEach>
					<c:if test="${empty costDepts }">
						<li class="department" id="costDept_0">
							<input type="hidden" class="costId" id="costDept_0_id" name="tmcOrderCostShareList[0].shareDeptId" value="" />
							<input type="hidden" id="costDept_0_name" name="tmcOrderCostShareList[0].shareDeptName" value="" />
							<div style="display: flex;flex-direction: row;" class="aw">
								<p class="dptm">请选择</p>
								<div style="margin: auto 0;">
									<img src="../hotelimg/you.png"  class="you">
								</div>
							</div>
							<input type="text" name="tmcOrderCostShareList[0].shareValue" id="" value="" placeholder="请输入分摊百分比或金额" onblur="validateShareValue(this)" />
							<input type="hidden" name="tmcOrderCostShareList[0].shareType" class="shareType" value="0001500002" />
							<div style="margin: auto 0;">
								<img src="../hotelimg/add.png"  class="add">
							</div>
						</li>
					</c:if>
				</ul>
			</div>
		
			<div id="pay" class="order_">
				<div class="method small">
					<p>结算方式</p>
					<div>
						<span class="mode">${hotelBooking.commonPayMethodList[0].n }</span>
						<img src="../hotelimg/you.png" / class="you">
					</div>
				</div>
				<div class="invoice small">
					<p class="aw">
						发票公司
					</p>
					<input type="text" name="invoiceDeptName" id="invoice_name" value="${hotelBooking.bookingEnterpName }" placeholder="公司/部门名称" readonly unselectable="on" onfocus="this.blur()" />
					<input type="hidden" name="invoiceDeptCode" id="invoice_id" value="${hotelBooking.bookingEnterpId }" />
				</div>
			</div>
			<!-- 积分/金旅豆暂不上线 -->
<%-- 			<div id="integral" class="order_">
				<p class="pick">二选一</p>
				<div class="parents">
					<div class="to_use integral">
						<div class="aw">
							<p>使用积分</p>
							<img src="../hotelimg/tips.png" / class="tips">
						</div>
						<div class="circle" id="integral_btn">
							<img src="../hotelimg/circle.png" class="circleBtn" id="circleBtn">
						</div>
					</div>
					<div class="pv integral">
						<p class="aw">积分额</p>
						<input type="text" onblur="valideIntegral(this)" oninput="setIntegral(this)" name="" id="" value=""
						 placeholder='共<fmt:formatNumber type="number" groupingUsed="false"
							value="${empty hotelBooking.userIntegral ? 0 : hotelBooking.userIntegral }" maxFractionDigits="1"/>，满100可用' />
						<input type="hidden" id="integral_price_id" name="integralDiscount" value="0" />
					</div>
				</div>
				<div class="parents">
					<div class="interest integral">
						<div class="aw">
							<p>使用金旅豆</p>
							<img src="../hotelimg/tips.png" / class="tips">
						</div>
						<div class="circle" id="interest_btn">
							<img src="../hotelimg/circle.png" class="circleBtn" id="circleBtn">
						</div>
					</div>
					<div class="rates integral">
						<p class="aw">金旅豆</p>
						<input type="text" readonly="readonly" name="" id="" value="" 
							placeholder='共<fmt:formatNumber type="number" groupingUsed="false" pattern="0.00" 
							value="${empty hotelBooking.userInterest ? 0 : hotelBooking.userInterest }" maxFractionDigits="2"/>' />
						<input type="hidden" id="interest_price_id" name="interestDiscount" value="${empty hotelBooking.userInterest ? 0 : hotelBooking.userInterest }" />
					</div>
				</div>
			</div> --%>
			<div id="approve" class="order_" <c:if test='${hotelBooking.enterpverify eq 0 }'>style="display: none;"</c:if> > 
				<p class="app_title">审批政策</p>
				<input type="hidden" name="approvePolicyId" id="approvePolicyId" value=""/>
				<input type="hidden" name="plicyRuleListLength" id="plicyRuleListLength" value=""/>
				<ul class="approve_list" id="approve_list">
				</ul>
			</div>
			<div id="remark" class="order_">
				<div class="aw">
					<p>备注</p>
				</div>
				<!-- <textarea placeholder="选填：对本次交易的说明" name="orderMemo" rows="2" style="width: 15rem;max-width: 15rem;overflow-y:auto ;border: none;font-size: 1.5rem;padding-top: 1.3rem;"></textarea> -->
				<input placeholder="选填：对本次交易的说明" name="orderMemo" type="text" name="" id="" value="" class="text">
			</div>
			<footer>
				<div class="footer">
					<div class="f_left">
						<div>
							<span>总价：</span>
							<span class="money"></span>
						</div>
						<a href="javascript:;" id="detail">明细</a>
					</div>
					<div class="sure" onclick="submitOrder()">
						<a href="javascript:void(0);">确&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;定</a>
					</div>
				</div>
				<div class="amount_list" id="amount">
					<input type="hidden" name="orderTotalAmount" id="orderTotalAmount" value="0" />
					<input type="hidden" name="tmcOrderHotel.roomFee" id="tmcOrderHotel_roomFee" value="${hotelBooking.roomFee }" />
					<input type="hidden" name="tmcOrderHotel.taxFee" id="tmcOrderHotel_taxFee" value="${hotelBooking.taxFee }" />
					<input type="hidden" name="tmcOrderHotel.bookingFee" id="tmcOrderHotel_bookingFee" value="0" />
					<input type="hidden" name="tmcOrderHotel.nightFee" id="tmcOrderHotel_nightFee" value="0" />
					<div class="detail_title">明细</div>
					<ul class="amount_item">
						<!-- 不以总价形式展示房间费和税费，页面隐藏 -->
						<li style="display: none;">
							<p>房间费</p>
							<p>¥<span class="roos_fee">0.00</span>
								<input type="hidden" id="rooms_fee" value="${hotelBooking.roomFee }"/>
							</p>
						</li>
						<li style="display: none;">
							<p>税费</p>
							<p>¥<span class="taxes_fee">0.00</span>
								<input type="hidden" id="taxess_fee" value="${hotelBooking.taxFee }"/>
							</p>
						</li>
						<!-- 不以总价形式展示房间费和税费，页面隐藏 -->
						<c:forEach var="dayPrice" items="${hotelBooking.productPriceDays }">
							<li>
								<p><fmt:formatDate value="${dayPrice.priceDay }" pattern="yyyy-MM-dd"/>${dayPrice.priceDayStr }</p>
								<p>¥<fmt:formatNumber type="number" groupingUsed="false" pattern="0.00" value="${dayPrice.finalPriceCNY }" maxFractionDigits="2"/>*${orderDetail.tmcOrderHotel.roomNum }间</p>
							</li>
						</c:forEach>
						<li>
							<p>预订费</p>
							<p>¥<span class="booking_fee">0.00</span></p>
						</li>
						<li>
							<p>夜间费</p>
							<p>¥<span class="night_fee">0.00</span></p>
						</li>
						<!-- 积分优惠本期不上线 -->
						<li style="display: none;">
							<p>积分优惠</p>
							<p>-¥<span class="treatment">0.00</span></p>
						</li>
					</ul>
				</div>
			</footer>
		</section>	
		<div style="display: none;">
			<input type="hidden" name="phk" value="${hotelBooking.phk }" />
			<input type="hidden" id="tripType" name="tripType" value="${hotelBooking.tripType }" />
			<input type="hidden" id="bookingUserId" name="bookingUserId" value="${hotelBooking.bookingUserId }" />
			<input type="hidden" id="bookingUserName" name="bookingUserName" value="${hotelBooking.bookingUserName }" />
			<input type="hidden" id="bookingDeptId" name="bookingDeptId" value="${hotelBooking.bookingDeptId }" />
			<input type="hidden" id="bookingDeptName" name="bookingDeptName" value="${hotelBooking.bookingDeptName }" />
			<input type="hidden" id="bookingEnterpId" name="bookingEnterpId" value="${hotelBooking.bookingEnterpId }" />
			<input type="hidden" id="bookingEnterpName" name="bookingEnterpName" value="${hotelBooking.bookingEnterpName }" />
			<input type="hidden" id="pageTempletFlag" name="pageTempletFlag" value="${hotelBooking.pageTempletFlag }" />
			<input type="hidden" name="tmcOrderHotel.bookingCheckinDateStr" value='<fmt:formatDate value="${hotelBooking.checkInDate }" pattern="yyyy-MM-dd"/>' />
			<input type="hidden" name="tmcOrderHotel.bookingCheckoutDateStr" value='<fmt:formatDate value="${hotelBooking.checkOutDate }" pattern="yyyy-MM-dd"/>' />
			<input type="hidden" id="roomCount" name="tmcOrderHotel.roomNum" value='${orderDetail.tmcOrderHotel.roomNum }' />
			<c:forEach items="${orderDetail.tmcOrderGuestList }" var="guest" varStatus="g_i">
				<c:if test="${not empty guest.guestName }">
					<input type="hidden" name="tmcOrderGuestList[${g_i.index }].guestId" value="${guest.guestId }" class="guestId">
					<input type="hidden" name="tmcOrderGuestList[${g_i.index }].guestName" value="${guest.guestName }">
					<input type="hidden" name="tmcOrderGuestList[${g_i.index }].guestType" value="${guest.guestType}">
					<input type="hidden" name="tmcOrderGuestList[${g_i.index }].roomNo" value="${guest.roomNo }">
					<input type="hidden" name="tmcOrderGuestList[${g_i.index }].tel" value="${guest.tel }">
					<input type="hidden" name="tmcOrderGuestList[${g_i.index }].email" value="${guest.email }">
					<input type="hidden" name="tmcOrderGuestList[${g_i.index }].birthdayStr" value='<fmt:formatDate value="${guest.birthday }" pattern="yyyy-MM-dd"/>'>
					<input type="hidden" name="tmcOrderGuestList[${g_i.index }].certType" value="${guest.certType }">
					<input type="hidden" name="tmcOrderGuestList[${g_i.index }].certNo" value="${guest.certNo }">
					<input type="hidden" name="tmcOrderGuestList[${g_i.index }].deptId" value="${guest.deptId }">
					<input type="hidden" name="tmcOrderGuestList[${g_i.index }].deptName" value="${guest.deptName }">
					<input type="hidden" name="tmcOrderGuestList[${g_i.index }].ageType" value="${guest.ageType }">
				</c:if>
			</c:forEach>
			<c:forEach items="${orderDetail.tmcOrderContactsList }" var="contact" varStatus="c_i">
				<c:if test="${not empty contact.contact }">
					<input type="hidden" name="tmcOrderContactsList[${c_i.index }].contact" value="${contact.contact }">
					<input type="hidden" name="tmcOrderContactsList[${c_i.index }].tel" value="${contact.tel }">
					<input type="hidden" name="tmcOrderContactsList[${c_i.index }].email" value="${contact.email }">
					<input type="hidden" name="tmcOrderContactsList[${c_i.index }].seq" value="${contact.seq }">
				</c:if>
			</c:forEach>
			<input type="hidden" id="hotelBookingRequest" name="hotelBookingRequest" value='${hotelBookingRequest }'/>
			<input type="hidden" name="superRuleReason" value="" id="superRuleReason"/>
		</div>
	</form>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/hotelHtml5/orderInfo/newOrderPublic.js"></script>
</div>
<div id="orderExceededRemind_div" style="display: none;"></div>
</body>
</html>