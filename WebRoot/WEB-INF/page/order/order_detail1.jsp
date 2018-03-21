<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="../public/tag.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	    <base href="<%=basePath%>">
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<title>国内机票订单详情</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/MyConfirm.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/index.css" />
</head>

	<body>
		<div class="bounced cancleBtn">
			<div class="tips">
				<p class="changes">取消订单</p>
				<div class="contentd">
					<p>一旦取消将无法恢复</p>
					<textarea name="a" placeholder="取消订单的理由" class="reason" id="cancel_reason" style="border-radius: 0.5rem; font-size:1.2rem; text-indent: 0.5rem;"></textarea>
					<div class="btnA">
						<a class="true-tips1 sureBtn" href="javascript:;">确定</a>
						<a class="true-tips1 canBtn" href="javascript:;">取消</a>
					</div>
				</div>

			</div>
		</div>
		<div class="bounced resoultlBtn">
			<div class="tips">
				<p class="changes">拒绝订单</p>
				<div class="contentd">
					<p>一旦拒绝将无法恢复</p>
					<textarea name="a" placeholder="拒绝订单的理由" class="reason" id="cancel_reason" style="border-radius: 0.5rem; font-size:1.2rem; text-indent: 0.5rem;"></textarea>
					<div class="btnA">
						<a class="true-tips2 sureBtn" href="javascript:;">确定</a>
						<a class="true-tips2 canBtn" href="javascript:;">取消</a>
					</div>
				</div>

			</div>
		</div>
		<c:if test="${result.resultObj!=null && result.resultObj.status==0 && result.resultObj.data!=null}">
		<c:set var="data" value="${result.resultObj.data }"/>
		<header>
			<div class="arrow">
				<img src="<%=basePath%>/img/header.png" />
			</div>
			<p id="oid" oid="${data.orderId }" class="number">订单号${data.orderNo }</p>
		</header>
		<section>
			<div class="wrap">
				<div class="original-order">
					<div class="order-title">
						<p>原始订单</p>
						<p class="stay">
							<c:choose>
								<c:when test="${data.status==1}">待审核</c:when>
								<c:when test="${data.status==2}">待支付</c:when>
								<c:when test="${data.status==3}">已审核</c:when>
								<c:when test="${data.status==4}">已支付</c:when>
								<c:when test="${data.status==5}">已取消</c:when>
								<c:when test="${data.status==6}"></c:when>
								<c:when test="${data.status==7}">已删除</c:when>
								<c:when test="${data.status==8}">补录</c:when>
								<c:when test="${data.status==9}">已提交</c:when>
							</c:choose>
						</p>
					</div>
					<div class="odd">
						<div class="odd-title">
							    <c:if test="${data.tripType==1 }">单程</c:if>
								<c:if test="${data.tripType==2 }">往返</c:if>
								<c:if test="${data.tripType==3 }">联程</c:if>
						</div>
						<!-- 单程 -->
						<c:if test="${data.tripType==1 }">
						<div class="odd-list">
							<div class="t">
								<div class="st">
									<p class="data">${mt:fmtByStyle(data.flights[0].depDateTime,'MM-dd')}</p>
									<p class="time">${mt:fmtByStyle(data.flights[0].depDateTime,'HH:mm')}</p>
								</div>
								<!-- 经停 -->
								<c:if test="${data.flights[0].stops!=null}">
								<div class="stop">
									 <p>经停</p> 
									 <p>${data.flights[0].stops[0].airportCode }</p>
									<p id="time">${mt:getTimeDis(data.flights[0].stops[0].depDateTime,data.flights[0].stops[0].arrDateTime) }</p>
								</div>
								</c:if>
								<div class="js">
									<p class="data">${mt:fmtByStyle(data.flights[0].arrDateTime,'MM-dd')}</p>
									<p class="time">${mt:fmtByStyle(data.flights[0].arrDateTime,'HH:mm')}</p>
								</div>
							</div>
							<div class="line">
								<img src="img/luxian.png" />
							</div>
							<div class="place">
								<div class="site">
									<span class="city">${applicationScope.cityMap.get(data.flights[0].depAirport) }</span>
									<span class="air">${applicationScope.airportNameMap.get(data.flights[0].depAirport) }${data.flights[0].depPointAT }</span>
								</div>
								<div class="message">
									<span>
										<img src="img/MU.png"/>
										<span>${applicationScope.ac2NameMap.get(data.flights[0].airline)} ${data.flights[0].flightNo}</span>
									</span>
									<span class="boeing">${applicationScope.planeTypeMap.get(data.flights[0].planeType)}</span>
									<c:set var="cabinkey" value="${data.flights[0].airline}_${data.flights[0].cabinCode}" />
									<span>${applicationScope.cabinNameMap.get(cabinKey)}</span>
								</div>
								<div class="site">
									<span class="city">${applicationScope.cityMap.get(data.flights[0].arrAirport) }</span>
									<span class="air">${applicationScope.airportNameMap.get(data.flights[0].arrAirport) }${data.flights[0].arrPointAT }</span>
								</div>
							</div>
						</div>
						</c:if>
						<!-- 单程end -->
						<c:if test="${data.tripType==2 || data.tripType==3 }">
						<c:if test="${data.tripType==2}">
						<p class="go-trip">去程</p>
						</c:if>
						<div class="odd-list">
							<div class="t">								
								<div class="st">
									<p class="data">${mt:fmtByStyle(data.flights[0].depDateTime,'MM-dd')}</p>
									<p class="time">${mt:fmtByStyle(data.flights[0].depDateTime,'HH:mm')}</p>
								</div>
								  <c:if test="${data.flights[0].stops.size()>0 }">
									<div class="stop">
										<p>经停</p>
										<p>${data.flights[0].stops[0].airportCode }</p>
										<p id='timeX'>${mt:getTimeDis(data.flights[0].stops[0].depDateTime,data.flights[0].stops[0].arrDateTime) }</p>
									</div>
								</c:if>  
								<div class="js">
									<p class="data">${mt:fmtByStyle(data.flights[0].arrDateTime,'MM-dd')}</p>
									<p class="time">${mt:fmtByStyle(data.flights[0].arrDateTime,'HH:mm')}</p>
								</div>
							</div>
							<div class="line">
								<img src="img/luxian.png" />
							</div>
							<div class="place">
								<div class="site">
									<span class="city">${applicationScope.cityMap.get(data.flights[0].depAirport) }</span>
									<span class="air">${applicationScope.airportNameMap.get(data.flights[0].depAirport) }${data.flights[0].depPointAT }</span>
								</div>
								<div class="message">
									<span>
										<img src="img/MU.png"/>
										<span>${applicationScope.ac2NameMap.get(data.flights[0].airline)} ${data.flights[0].flightNo}</span>
									</span>
									<span class="boeing">${applicationScope.planeTypeMap.get(data.flights[0].planeType)}</span>
									<c:set var="cabinkey" value="${data.flights[0].airline}_${data.flights[0].cabinCode}" />
									<span>${applicationScope.cabinNameMap.get(cabinKey)}</span>
								</div>
								<div class="site">
									<span class="city">${applicationScope.cityMap.get(data.flights[0].arrAirport) }</span>
									<span class="air">${applicationScope.airportNameMap.get(data.flights[0].arrAirport) }${data.flights[0].arrPointAT }</span>
								</div>
							</div>
						</div>
						<div class="hidden" style="margin-top: 1.5rem;">
						<c:if test="${data.tripType==2}">
						<p class="go-trip">返程</p>
						</c:if>
							<div class="odd-list">
								<div class="t">									
									<div class="st">
										<p class="data">${mt:fmtByStyle(data.flights[1].depDateTime,'MM-dd')}</p>
										<p class="time">${mt:fmtByStyle(data.flights[1].depDateTime,'HH:mm')}</p>
									</div>
								<c:if test="${data.flights[1].stops.size()>0 }">
									<div class="stop">
										<p>经停 </p>
										<p>${data.flights[1].stops[0].airportCode }</p>
										<p id='timeX'>${mt:getTimeDis(data.flights[1].stops[0].depDateTime,data.flights[1].stops[0].arrDateTime) }</p>
									</div>
								</c:if> 
									<div class="js">
										<p class="data">${mt:fmtByStyle(data.flights[1].arrDateTime,'MM-dd')}</p>
										<p class="time">${mt:fmtByStyle(data.flights[1].arrDateTime,'HH:mm')}</p>
									</div>
								</div>
								<div class="line">
									<img src="img/luxian.png" />
								</div>
								<div class="place">
									<div class="site">
										<span class="city">${applicationScope.cityMap.get(data.flights[1].depAirport) }</span>
										<span class="air">${applicationScope.airportNameMap.get(data.flights[1].depAirport) }${data.flights[1].depPointAT }</span>
									</div>
									<div class="message">
										<span>
										<img src="img/MU.png"/>
										<span>${applicationScope.ac2NameMap.get(data.flights[1].airline)} ${data.flights[1].flightNo}</span>
										</span>
										<span class="boeing">${applicationScope.planeTypeMap.get(data.flights[1].planeType)}</span>
										<c:set var="cabinkey" value="${data.flights[1].airline}_${data.flights[1].cabinCode}" />
										<span>${applicationScope.cabinNameMap.get(cabinKey)}</span>
									</div>
									<div class="site">
										<span class="city">${applicationScope.cityMap.get(data.flights[1].arrAirport) }</span>
										<span class="air">${applicationScope.airportNameMap.get(data.flights[1].arrAirport) }${data.flights[1].arrPointAT }</span>
									</div>
								</div>
							</div>
							
						</div>
						
						<div class="up-click">
							<p>
								<a href="javascript:;">隐藏</a>
								<img src="img/dakai.png" />
							</p>
						</div>
						</c:if>
					</div>
				</div>
			<c:if test="${data.changes.size()!='0'}">
				<c:forEach items="${data.changes }" var="change" varStatus="dd">
				 <c:forEach items="${change.flights }" var="fly">
				<div class="period">
					<div class="period-title">
						<p>改期第${change.flowId }次</p>
						<p>
							<img src="img/dakai.png" />
						</p>
					</div>
					<div class="odd">
						<div class="odd-title">
							    <c:if test="${data.tripType==1 }">单程</c:if>
								<c:if test="${data.tripType==2 }">往返</c:if>
								<c:if test="${data.tripType==3 }">联程</c:if>
						</div>
						<!--改期 单程 start  -->
					<c:if test="${data.tripType==1}">
						<div class="odd-list">
							<div class="t">
								<div class="st">
									<p class="data">${mt:fmtByStyle(fly.depDateTime,'MM-dd')}</p>
									<p class="time">${mt:fmtByStyle(fly.depDateTime,'HH:mm')}</p>
								</div>
								<c:if test="${fly.stops!=null}">
								<c:forEach items="${fly.stops }" var="stop">
								<div class="stop">
									 <p>经停</p> 
									 <p>${stop.airportCode }</p>
									<p id="timeX">${mt:getTimeDis(stop.depDateTime,stop.arrDateTime) }</p>
								</div>
								</c:forEach>
								</c:if>
								<div class="js">
									<p class="data">${mt:fmtByStyle(fly.arrDateTime,'MM-dd')}</p>
									<p class="time">${mt:fmtByStyle(fly.arrDateTime,'HH:mm')}</p>
								</div>
							</div>
							<div class="line">
								<img src="img/luxian.png" />
							</div>
							<div class="place">
								<div class="site">
									<span class="city">${applicationScope.cityMap.get(fly.depAirport) }</span>
									<span class="air">${applicationScope.airportNameMap.get(fly.depAirport) }${fly.depPointAT }</span>
								</div>
								<div class="message">
									<span>
										<img src="img/MU.png"/>
										<span>${applicationScope.ac2NameMap.get(fly.airline)} ${fly.flightNo}</span>
									</span>
									<span class="boeing">${applicationScope.planeTypeMap.get(fly.planeType)}</span>
									<c:set var="cabinkey" value="${fly.airline}_${fly.cabinCode}" />
									<span>${applicationScope.cabinNameMap.get(cabinKey)}</span>
								</div>
								<div class="site">
									<span class="city">${applicationScope.cityMap.get(fly.arrAirport) }</span>
									<span class="air">${applicationScope.airportNameMap.get(fly.arrAirport) }${fly.arrPointAT }</span>
								</div>
							</div>
						</div>
					</c:if>
						<!-- 单程 end-->
						<c:if test="${data.tripType==2 || data.tripType==3 }">
							<c:if test="${fly.flowId ==1}"><!-- 去程 -->
						<c:if test="${data.tripType==2}">
						<p class="go-trip">去程</p>
						</c:if>
						<div class="odd-list">
							<div class="t">								
								<div class="st">
									<p class="data">${mt:fmtByStyle(fly.depDateTime,'MM-dd')}</p>
									<p class="time">${mt:fmtByStyle(fly.depDateTime,'HH:mm')}</p>
								</div>
								<c:if test="${fly.stops!=null}">
								<c:forEach items="${fly.stops }" var="stop">
								<div class="stop">
									 <p>经停</p> 
									 <p>${stop.airportCode }</p>
									<p id="">${mt:getTimeDis(stop.depDateTime,stop.arrDateTime) }</p>
								</div>
								</c:forEach>
								</c:if>
								<div class="js">
									<p class="data">${mt:fmtByStyle(fly.arrDateTime,'MM-dd')}</p>
									<p class="time">${mt:fmtByStyle(fly.arrDateTime,'HH:mm')}</p>
								</div>
							</div>
							<div class="line">
								<img src="img/luxian.png" />
							</div>
							<div class="place">
								<div class="site">
									<span class="city">${applicationScope.cityMap.get(fly.depAirport) }</span>
									<span class="air">${applicationScope.airportNameMap.get(fly.depAirport) }${fly.depPointAT }</span>
								</div>
								<div class="message">
									<span>
										<img src="img/MU.png"/>
										<span>${applicationScope.ac2NameMap.get(fly.airline)} ${fly.flightNo}</span>
									</span>
									<span class="boeing">${applicationScope.planeTypeMap.get(fly.planeType)}</span>
									<c:set var="cabinkey" value="${fly.airline}_${fly.cabinCode}" />
									<span>${applicationScope.cabinNameMap.get(cabinKey)}</span>
								</div>
								<div class="site">
									<span class="city">${applicationScope.cityMap.get(fly.arrAirport) }</span>
									<span class="air">${applicationScope.airportNameMap.get(fly.arrAirport) }${fly.arrPointAT }</span>
								</div>
							</div>
						</div>
						</c:if>
						
						<div style="margin-top: 1.5rem;">
						<c:if test="${fly.flowId == -1}"><!-- 返程 -->
						<c:if test="${data.tripType==2}">
						<p class="go-trip">返程</p>
						</c:if>
							<div class="odd-list">
								<div class="t">									
									<div class="st">
										<p class="data">${mt:fmtByStyle(fly.depDateTime,'MM-dd')}</p>
										<p class="time">${mt:fmtByStyle(fly.depDateTime,'HH:mm')}</p>
									</div>
								<c:if test="${fly.stops!=null}">
								<c:forEach items="${fly.stops }" var="stop">
								<div class="stop">
									 <p>经停</p>
									 <p>${stop.airportCode }</p> 
									<p id="">${mt:getTimeDis(stop.depDateTime,stop.arrDateTime) }</p>
								</div>
								</c:forEach>
								</c:if>
									<div class="js">
										<p class="data">${mt:fmtByStyle(fly.arrDateTime,'MM-dd')}</p>
										<p class="time">${mt:fmtByStyle(fly.arrDateTime,'HH:mm')}</p>
									</div>
								</div>
								<div class="line">
									<img src="img/luxian.png" />
								</div>
								<div class="place">
									<div class="site">
										<span class="city">${applicationScope.cityMap.get(fly.depAirport) }</span>
										<span class="air">${applicationScope.airportNameMap.get(fly.depAirport) }${fly.depPointAT }</span>
									</div>
									<div class="message">
										<span>
										<img src="img/MU.png"/>
										<span>${applicationScope.ac2NameMap.get(fly.airline)} ${fly.flightNo}</span>
										</span>
										<span class="boeing">${applicationScope.planeTypeMap.get(fly.planeType)}</span>
										<c:set var="cabinkey" value="${fly.airline}_${fly.cabinCode}" />
										<span>${applicationScope.cabinNameMap.get(cabinKey)}</span>
									</div>
									<div class="site">
										<span class="city">${applicationScope.cityMap.get(fly.arrAirport) }</span>
										<span class="air">${applicationScope.airportNameMap.get(fly.arrAirport) }${fly.arrPointAT }</span>
									</div>
								</div>
							</div>
							
						</c:if>
						</div>
						</c:if>
					</div>
				</div>
					</c:forEach>
				   </c:forEach>
				</c:if>
				<div class="amount-detail">
					<div>
						<span class="w">应付金额</span>
						<span class="m">￥${data.amount}</span>
					</div>
					<c:set value="0" var="totalIn" />
					<c:forEach items="${data.passengers }" var="p">
						<c:set var="totalIn" value="${p.insurancePrice + totalIn }" />
					</c:forEach>
					<c:set value="${data.passengers.size() }" var="pNum" />
					<c:set value="0" var="tPrice" />
					<c:set value="0" var="fPrice" />
					<c:forEach items="${data.tickets }" var="t">
						<c:set var="tPrice" value="${t.price + tPrice }" />
						<c:set var="fPrice" value="${(t.airportTax==null?0:t.airportTax) + (t.fuelSurTax==null?0:t.fuelSurTax) + fPrice }" />
					</c:forEach>
					<div>
						<span class="w">机票</span>
						<span class="m">￥${tPrice/pNum }*${pNum}</span>
					</div>
					<div>
						<span class="w">税费</span>
						<span class="m">￥${fPrice/pNum }*${pNum}</span>
					</div>
					<div>
						<span class="w">保险</span>
						<span class="m">￥${totalIn }</span>
					</div>
					<div>
						<span class="w">服务费</span>
						<span class="m">￥${data.tickets[0].serviceFee==null?'0.0':data.tickets[0].serviceFee }</span>
					</div>
				</div>
				<div class="mode">
					<div>
						<span class="w">出行方式</span>
						<span>因${data.businessType==0?'公':'私' }出行</span>
					</div>
					<div>
						<span class="w">支付方式</span>
						<span>${applicationScope.paytypeMap.get(data.payMethod) }</span>
					</div>
				</div>
				<div class="air">
				<c:if test="${data.passengers!=null }">
					<div class="air-title">乘机人</div>
					<c:forEach items="${data.passengers }" var="passenger" varStatus="pp">
					<ul class="air-item">
						<li>
							<div class="name">
								<div>
									<span class="w">${passenger.name }</span>
									<span>${passenger.mobile }</span>
								</div>
								<div>
									<img src="img/dakai.png" />
								</div>
							</div>
							<div class="an">
								<div class="line">
									<div>
										<span class="w">
											<c:if test="${passenger.idType==1 }">身份证</c:if>
											<c:if test="${passenger.idType==2 }">护照</c:if>
											<c:if test="${passenger.idType==3 }">海员证</c:if>
											<c:if test="${passenger.idType==4 }">回乡证</c:if>
											<c:if test="${passenger.idType==5 }">军官证</c:if>
											<c:if test="${passenger.idType==6 }">港澳通行证</c:if>
											<c:if test="${passenger.idType==7 }">台胞证</c:if>
											<c:if test="${passenger.idType==99 }">其他</c:if>
										</span>
										<span>${passenger.idNum }</span>
									</div>
									<div>
										<span class="w">费用归属</span>
										<span>${passenger.costCenter }</span>
									</div>
								</div>
								<div class="line">
									<c:forEach items="${data.tickets }" var="ticket" varStatus="tt">
										<c:if test="${passenger.id eq ticket.passengerId}">
										<div class="num">
											<span class="w">原始票号</span>
											<span>${ticket.ticketNo }</span>
										</div>
										<div>
											<span class="w">票面价格</span>
											<span>¥${ticket.price }</span>
										</div>
										<div>
											<span class="w">保险</span>
											<span>¥${passenger.insurancePrice}</span>
										</div>
										<div>
											<span class="w">服务费</span>
											<span>¥${ticket.serviceFee==null?'0.0':ticket.serviceFee}</span>
										</div>
										</c:if>
									</c:forEach>
								</div>
						<c:if test="${data.changes.size()!='0' }">
								<div class="line">
							 <c:forEach items="${data.changes}" var="cupdate" varStatus="cc"> 
							 	<c:forEach items="${cupdate.tickets }" var="cticket">
									<div class="change">
										<span>改期票号</span>
										<span class="state">
											<c:if test="${cticket.status=='5'}">改期通过</c:if>
											<c:if test="${cticket.status=='6'}">改期拒绝</c:if>
										</span>
									</div>
									<div>
										<span class="w">升舱费</span>
										<span>¥${cticket.classFee }</span>
									</div>
									<div>
										<span class="w">改期费</span>
										<span>¥${cticket.changeFee }</span>
									</div>
									<div>
										<span class="w">服务费</span>
										<span>¥${cticket.serviceFee==null?'0.0':cticket.serviceFee }</span>
									</div>
									</c:forEach>
							</c:forEach>
								</div>
						</c:if>
					<c:if test="${data.refunds.size()!='0' }">
						<c:forEach items="${data.refunds }" var="refun" varStatus="rr">
						<div class="line">
							<c:forEach items="${refun.tickets }" var="ticket" >
									<div class="change">
										<div>
											<span class="w">
												<c:if test="${refun.type==1 }">退出票票号</c:if>
												<c:if test="${refun.type==2 }">退改期票号</c:if>
											</span>
											<span>${ticket.ticketNo }</span>
										</div>
										<span class="state">
											<c:if test="${ticket.status==1 }">已申请退票</c:if>
											<c:if test="${ticket.status==2 }">退票同意</c:if>
											<c:if test="${ticket.status==3 }">退票拒绝</c:if>
											<c:if test="${ticket.status==4 }">待航空公司退票</c:if>
										</span>
									</div>
									<div>
										<span class="w">退票理由</span>
										<span>${ticket.refundReason }</span>
									</div>
									<div>
										<span class="w">退票费</span>
										<span>¥${ticket.refundFee }</span>
									</div>
									<div>
										<span class="w">服务费</span>
										<span>¥${ticket.serviceFee==null?'0.0':ticket.serviceFee }</span>
									</div>
									</c:forEach>
							</div>
							</c:forEach>
						</c:if>
							</div>
						</li>
					</ul>
					</c:forEach>
					</c:if>
				</div>
				<div class="people">
				<c:forEach items="${data.contacts }" var="cont" varStatus="vs">
					<span class="w surname">通知人${vs.count}</span>
					<span class="w">${cont.name }</span>
					<span>${cont.mobile }</span>
					</c:forEach>
				</div>
				<div class="people">
					<c:if test="${data.auditor != null }">
					<span class="w surname">审核人</span>
					<span class="w">${data.auditor.name }</span>
					<span>${data.auditor.email }</span>
					</c:if>
				</div>
				<footer class="footer-transit">
				<c:if test="${data.status==2 }">
					<div class="btn" id="alipayid" style="background:#00afec" onclick="window.location.href='flight/toPay.act?orderId=${result.resultObj.data.orderId}';">去支付</div>
				</c:if>
					<div class="audit-list">审核通过</div>
					<div class="refused-list">拒绝通过</div>
					<div class="cancel-list">取消订单</div>
				</footer>
			</div>
		</section>
		</c:if>
		<script type="text/javascript" src="<%=basePath%>/js/jquery.min.js"></script>
	    <script type="text/javascript" src="<%=basePath%>/js/MyConfirm.js"></script>
	    <script type="text/javascript" src="<%=basePath%>/libs/gublic.js"></script>
	    <script type="text/javascript" src="<%=basePath%>/js/page/comm.js"></script>
	
		<script type="text/javascript">
			$('.up-click p').on('click', function() {
				$('.hidden').slideToggle();
				if($(this).find('a').html() == "收起") {
					$(this).find('a').html('隐藏');
					$(this).find('img').attr('src', 'img/dakai.png');
				} else {
					$(this).find('a').html('收起');
					$(this).find('img').attr('src', 'img/xiala.png');
				}
			});

			$('.period-title img').on('click', function() {
				$(this).attr('src', $(this).attr('src') == 'img/dakai.png' ? 'img/xiala.png' : 'img/dakai.png');
				$(this).parents('.period-title').siblings('.odd').slideToggle();
			});

			$('.name img').on('click', function() {
				$(this).attr('src', $(this).attr('src') == 'img/dakai.png' ? 'img/xiala.png' : 'img/dakai.png');
				$(this).parents('.name').siblings('.an').slideToggle();
			});
			
			/*-------取消订单的弹框-------*/
			$('.cancel-list').on('click', function() {
				$('.cancleBtn').fadeIn()
			});
			$('.canBtn').on('click', function() {
				$('.bounced').fadeOut();
			});
			/*----------拒绝通过的弹框----*/
			$('.refused-list').on('click', function() {
				$('.resoultlBtn').fadeIn();
			});
			/*---------是否要保存取消理由------*/
		$('.sureBtn').eq(0).on('click', function() {
			$('.bounced').fadeOut();
			$(this).MyConfirm({
				content: '确定要保存取消的理由吗？',
				hasCancelBtn: true
			});
		});
		/*----是否要保存确定的理由---------*/
		$('.sureBtn').eq(1).on('click', function() {
			$('.bounced').fadeOut();
			$(this).MyConfirm({
				content: '确定要保存拒绝的理由吗？',
				hasCancelBtn: true
			});
		});
		</script>
		
		<script type="text/javascript">
		var base = "${applicationScope.ctx}";
		function button(){
		orderId=$("#oid").attr("oid");
			parm = "&orderId="+orderId + "&type=3";
		 	htmlobj =$.ajax({
			 	type:'POST',
			 	url: base + "/auditorder/auditorder.act",
			 	dataType:"text",
			 	data:"parm="+parm, 
			 	async: false
		 	});
		 	var noconfirm = htmlobj.responseText;
		 	
		 	//审核订单按钮权限
		 	if(noconfirm.substring(0, 1)  =="1"){
		    	$(".refused-list").show();	
				$(".audit-list").show();
		 	}else{
		 		
		 		$(".refused-list").hide();
				$(".audit-list").hide();
		 	}
		 	//取消订单按钮权限
		 	if(noconfirm.substring(1, 2)  =="1"){
				$(".cancel-list").show();		
		 	}else{
		 	$(".cancel-list").hide();
		 	}
		 	
		 	$("#back").on('click',function(){
		 		window.location.href = "order/list.act";
		 	});
		
		}
		
		$(function(){
			button();
		 	});
			//取消订单
			$('.true-tips1').on('click',function(){
			qxcallback(true);
			});
			
			
			function qxcallback(isConfirm){
		
				orderId=$("#oid").attr("oid");
			
				if(isConfirm){
				
					if ($("#cancel_reason").val() == "" || $("#cancel_reason").val() == "取消订单的理由") {		     
			     	$(this).MyConfirm({
  						content : "请填写取消订单原因！"		
  					},function(){
			    		$(document).layerShow();
  					})
				}else if($("#cancel_reason").val().length < 3) {
			        $(this).MyConfirm({
  						content : "取消订单原因太少！"		
  					},function(){
			    		$(document).layerShow();
  					});
			    } else if ($("#cancel_reason").val().length > 100) {
			       $(this).MyConfirm({
  					content : "取消订单原因太长！"		
  					},function(){
			    		$(document).layerShow();
  					});
			     
			    } else {
			         parm = "&orderId="+orderId + "&memo=" + $("#cancel_reason").val();
			         htmlobj = $.ajax({
						         type:'POST',
						         url: base + "/auditorder/cancelOrder.act",
						         dataType:"text",
						         data:"parm="+parm, 
						         async: false
					         });
			         var cancelresult = htmlobj.responseText;
			         
			         if (cancelresult == "0") {
			         
			          $(this).MyConfirm({
  						content : "取消订单成功！"		
  						})
			        	
			             $(".audit").html('<h5 class="audit">已取消</h5>');	               
			             $(".cancel-list").hide();
			             $(".refused-list").hide();	
			   			 $(".audit-list").hide();		
			             $('.bounced').slideUp();
			             //隐藏 去支付 按钮
			             /* $("#alipayid").hide(); */
			             
			             return;		
			         } else{
			         
			          $(this).MyConfirm({
  						content : "取消失败"		
  						})
				         $('.bounced').slideUp();
				         $(document).layerHide();
			         }
			}
				}else{
				$('.bounced').slideUp();
				$(document).layerHide();
				
				}
			}
			
			//订单审核拒绝
			$('.true-tips2').on('click',function(){
			jjcallback(true);
			});
			
			 function jjcallback(isConfirm){
			  orderId=$("#oid").attr("oid") ;
			
 
					if(isConfirm){
					
					if ($("#refuse_reason").val() == "" || $("#refuse_reason").val() == "拒绝订单的理由") {
						
				    $(this).MyConfirm({
							content : "请填写审核不通过原因！"
					},function(){
			    		$(document).layerShow();
  					});	
				    } else {
				    	parm = "&orderId="+orderId + "&type=2&memo=" + $("#refuse_reason").val();
				    	
			    		htmlobj = $.ajax({
						    type:'POST',
						    url: base + "/auditorder/auditorder.act",
						    dataType:"text",
						    data:"parm="+parm, 
						    async: false,
						    success:function(data){
						    	
						    }
					    });
			    var noconfirm = htmlobj.responseText;
			    if (noconfirm == "0") {  //成功拒绝审核
			    
			      $(this).MyConfirm({
							content : "成功拒绝审核"
					});	
			   
			   	$(".audit").html('<h5 class="audit">已取消</h5>');
			   		
			   		$(".refused-list").hide();	
			   		$(".audit-list").hide();	
					$(".cancel-list").hide();	
			   		$('.bounced').slideUp();
			   		$(document).layerHide();
			   		return;			
				}else{
				$(this).MyConfirm({
							content : "拒绝审核失败"
					});	
					
					$('.bounced').slideUp();
					$(document).layerHide();
					
					
				}
				
			}
					
					}else{
						
						$('.bounced').slideUp();						
						$(document).layerHide();
						
					}
			 	
			 }	
			 	
			$('.closed-tips').on('click',function(){
				$('.bounced').slideUp();
				$(document).layerHide();
			});
			
			$('.cancel-list').on('click',function(){
				var height = $("body").height();
				$(document).layerShow();
				$('.bounced').height(height);
				//$('.bounced').css("z-index",10002);
				$('.cancleBtn').slideDown()
			});
			
			//定单审核拒绝
			$('.refused-list').on('click',function(){
				$(document).layerShow();
				var height = $("body").height();
				$('.bounced').height(height);
				$('.resoultlBtn').slideDown()
			});
			
			//订单审核通过
			$('.audit-list').on('click',function(){
				orderId=$("#oid").attr("oid") ;
				parm = "&orderId="+orderId + "&type=1";
				$.ajax({
					type:"post",
					url:"auditorder/auditorder.act",
					dataType:"text",
					data:"parm="+parm,
					success:function(status){
						if(status == 0){
						
						$(this).MyConfirm({
							content : "已审核"
					});	
							
							$(".audit").html('<h5 class="audit">审核通过</h5>');  
							$(".audit-list").hide();	
							$(".refused-list").hide();
							button();
							
						}else{
								$(this).MyConfirm({
							content : "审核失败"
					});	
							
						
						}
					},
					error:function(){
					$(this).MyConfirm({
							content : "请求失败"
					});	
					
					}
				});
			});
		
		
		</script>
	</body>

</html>