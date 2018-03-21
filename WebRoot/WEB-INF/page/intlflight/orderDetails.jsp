<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/masterpage" prefix="fms" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="../public/tag.jsp" %>
<fms:ContentPage materPageId="master">

<fms:Content contentPlaceHolderId="title">订单详情</fms:Content>

<%-- <fms:Content contentPlaceHolderId="nav">
	<!-- <h5>子页导航</h5>  -->
</fms:Content>
 --%>

<fms:Content contentPlaceHolderId="main">
	<html>

	<head>
		<link rel="stylesheet" type="text/css" href="css/intlAllOrder.css" />
	</head>
	<body>

	<input id="userid" value="${userId}" type="hidden">
	<input id="checkid" value="${result.checkMan.id}" type="hidden">
	<%-- <input id="ostatus" value="${result.tickets[0].status}" type="hidden"> --%>
	<input id="ostatus" value="${result.status}" type="hidden"><!-- 待审核：1 -->
	<input id="oid" value="${result.orderId}" type="hidden">

		<div class="bounced cancleBtn">
			<div class="tips">
				<p class="change">取消订单</p>
				<div class="contentd">
					<p>一旦取消将无法恢复</p>
					<textarea name="a" placeholder="取消订单的理由" class="reason" id="cancel_reason" style="border-radius: 0.5rem; font-size:1.2rem; text-indent: 0.5rem;"></textarea>
					<div class="btnA">
						<a class="true-tips1 sureBtn" href="javascript:;">确定</a>
						<a class="true-tips canBtn" href="javascript:;">取消</a>
					</div>
				</div>

			</div>
		</div>
		<div class="bounced resoultlBtn">
			<div class="tips">
				<p class="change">拒绝订单</p>
				<div class="contentd">
					<p>一旦拒绝将无法恢复</p>
					<textarea name="a" placeholder="拒绝订单的理由" class="reason" id="refuse_reason" style="border-radius: 0.5rem; font-size:1.2rem; text-indent: 0.5rem;"></textarea>
					<div class="btnA">
						<a class="true-tips2 sureBtn" href="javascript:;">确定</a>
						<a class="true-tips canBtn" href="javascript:;">取消</a>
					</div>
				</div>

			</div>
		</div>
		<div class="headerS"></div>
		<section  class="section">
			<%-- <c:set var="data" value="${result.data }"/> --%>
			<div class="one-way">
				<div class="way-title">
					<div class="tit-left">
						<span class="way-type">原始订单</span>
					</div>
					<h5 class="audit">${result.status }</h5> 
				</div>
				<div class="way-content">
				
					<div class="tripWay" style="display: flex;flex-direction: row;">
					<div>
					            <c:if test="${result.travelType==1 }">单程</c:if>
								<c:if test="${result.travelType==2 }">往返</c:if>
				    </div>
				    <div style="color: #333333; margin: auto 2rem;">
							<span class="g-city">${result.orgCityName }</span>
							<span>-</span>
							<span class="g-city">${result.detCityName }</span>
					</div>
					</div>
					
					<!-- 单程 -->
					<c:if test="${result.travelType==1 }">
					<c:forEach items="${result.segments }" var="segmentss" varStatus="dd">
					<c:if test="${segmentss.type eq '1' }">   <!-- 出票 -->
					<c:forEach items="${segmentss.airlines }" var="airline" varStatus="d">
					<c:forEach items="${airline.flights }" var="flight" varStatus="ddd">
					<div class="flight" id="flight${ddd.index }">
						<div class="left">
							<div class="start">
								<p class="tank">${mt:fmtByStringStyle(flight.startDateTime,'MM-dd')}</p>
								<h5>
									${mt:fmtByStringStyle(flight.startDateTime,'HH:mm')}
								</h5>
							</div>
							<input type="hidden" id="time${ddd.index }" value="${flight.startDateTime }">
							<input type="hidden" id="endtime${ddd.index }" value="${flight.endDateTime }">
							<input type="hidden" value="${fn:length(airline.flights)}" class="timess">
							<div class="start">
								<p class="tank">${mt:fmtByStringStyle(flight.endDateTime,'MM-dd')}</p>
								<h5>
									${mt:fmtByStringStyle(flight.endDateTime,'HH:mm')}
								</h5>
							</div>
						</div>
						<img src="img/luxian.png" / class="line">
						<div class="right">
							<div class="r-top">
								<%-- <h5>${result.orgCityName }</h5> --%>
								<h5>${flight.orgAirPortStr }${flight.orgTerm }</h5>
							</div>
							<div class="r-middle">
								<span>
									<img src="${applicationScope.iconServerURL}${flight.carrier }.gif"/ >
									${flight.carrierStr }${flight.flightNo }
								</span>
								<span class="tank big">${flight.planeType }</span>
								<span class="tank">${flight.cangweiType }</span>
							</div>
							<div class="r-bottom">
								<%-- <h5>${result.detCityName }</h5> --%>
								<h5>${flight.detAirPortStr }${flight.detTerm }</h5>
							</div>
						</div>
					</div>
					<c:if test="${flight.flowid >0 and flight.flowid <fn:length(airline.flights)}">
						<div class="transit" id="waitDiv${ddd.index }">
							<img src="img/zhuan.png" />
							<div>
								<h5>${flight.detAirPortStr }</h5>
								<h5><span id="waits${ddd.index}">停留</span></h5>
							</div>
						</div>
						</c:if>
				</c:forEach>
				</c:forEach>
				</c:if>
				</c:forEach>
					<div style="text-align: center;" class="hidden">
						<a href="javascript:;" class="closed" val="隐藏">隐藏 </a>
						<img src="img/xiala.png" / class="img">
					</div>
			</c:if>
			<!-- 单程结束 -->
			
			<!-- 往返开始 -->
			<c:if test="${result.travelType==2 }">
					<c:forEach items="${result.segments }" var="segmentss" varStatus="dd">
					<c:if test="${segmentss.type eq 1 }">
						<c:forEach items="${segmentss.airlines }" var="airline" varStatus="d">
						<c:if test="${airline.type eq '1'}">
					<p style="font-size:1.3rem;color:#00afec;">去程</p>
					<c:forEach items="${airline.flights }" var="flight" varStatus="gochengs">
					<div class="flight" id="flight${gochengs.index }">
						<div class="left">
							<div class="start">
								<p class="tank">${mt:fmtByStringStyle(flight.startDateTime,'MM-dd')}</p>
								<h5>
									${mt:fmtByStringStyle(flight.startDateTime,'HH:mm')}
								</h5>
							</div>
							<div class="start">
								<p class="tank">${mt:fmtByStringStyle(flight.endDateTime,'MM-dd')}</p>
								<h5>
									${mt:fmtByStringStyle(flight.endDateTime,'HH:mm')}
								</h5>
							</div>
						</div>
						<input type="hidden" id="time${gochengs.index }" value="${flight.startDateTime }">
						<input type="hidden" id="endtime${gochengs.index }" value="${flight.endDateTime }">
						<input type="hidden" value="${fn:length(airline.flights)}" class="timess">
						<img src="img/luxian.png" / class="line">
						<div class="right">
							<div class="r-top">
								<%-- <h5>${result.orgCityName }</h5> --%>
								<h5>${flight.orgAirPortStr }${flight.orgTerm }</h5>
							</div>
							<div class="r-middle">
								<span>
									<img src="img/type.png"/ >
									${flight.carrierStr }${flight.flightNo }
								</span>
								<span class="tank big">${flight.planeType }</span>
								<span class="tank">${flight.cangweiType }</span>
							</div>
							<div class="r-bottom">
								<%-- <h5>${result.detCityName }</h5> --%>
								<h5>${flight.detAirPortStr }${flight.detTerm }</h5>
							</div>
						</div>
					</div>
					 <c:if test="${1 <fn:length(airline.flights) and !gochengs.last}"> 
						<div class="transit" id="waitDiv${gochengs.index }">
							<img src="img/zhuan.png" />
							<div>
								<h5>${flight.detAirPortStr }</h5>
								<h5><span id="waits${gochengs.index}">停留</span></h5>
							</div>
						</div>
						</c:if>
				</c:forEach>
				</c:if>
						</c:forEach>
						<c:forEach items="${segmentss.airlines }" var="airline" varStatus="d">
						<c:if test="${airline.type eq '2'}">
						<div id="backtag">				
						<p style="font-size:1.3rem;color:#00afec;">返程</p>
						</div>

				<c:forEach items="${airline.flights }" var="flight" varStatus="backchengs">
					<div class="flight" id="flightback${backchengs.index }">
						<div class="left">
							<div class="start">
								<p class="tank">${mt:fmtByStringStyle(flight.startDateTime,'MM-dd')}</p>
								<h5>
									${mt:fmtByStringStyle(flight.startDateTime,'HH:mm')}
								</h5>
							</div>
							<div class="start">
								<p class="tank">${mt:fmtByStringStyle(flight.endDateTime,'MM-dd')}</p>
								<h5>
									${mt:fmtByStringStyle(flight.endDateTime,'HH:mm')}
								</h5>
							</div>
						</div>
						<input type="hidden" id="backtime${backchengs.index }" value="${flight.startDateTime }">
						<input type="hidden" id="backendtime${backchengs.index }" value="${flight.endDateTime }">
						<input type="hidden" value="${fn:length(airline.flights)}" class="backtimess">
						<img src="img/luxian.png" / class="line">
						<div class="right">
							<div class="r-top">
								<%-- <h5>${result.orgCityName }</h5> --%>
								<h5>${flight.orgAirPortStr }${flight.orgTerm }</h5>
							</div>
							<div class="r-middle">
								<span>
									<img src="img/type.png"/ >
									${flight.carrierStr }${flight.flightNo }
								</span>
								<span class="tank big">${flight.planeType }</span>
								<span class="tank">${flight.cangweiType }</span>
							</div>
							<div class="r-bottom">
								<%-- <h5>${result.detCityName }</h5> --%>
								<h5>${flight.detAirPortStr }${flight.detTerm }</h5>
							</div>
						</div>
					</div>
					 <c:if test="${1 <fn:length(airline.flights) and !backchengs.last}"> 
						<div class="transit" id="backwaitDiv${backchengs.index }">
							<img src="img/zhuan.png" />
							<div>
								<h5>${flight.detAirPortStr }</h5>
								<h5><span id="backwaits${backchengs.index}">停留</span></h5>
							</div>
						</div>
						 </c:if> 
						
				</c:forEach>
				
				</c:if>
						</c:forEach>
						</c:if>
					</c:forEach>
					<div style="text-align: center;" class="hidden">
						<a href="javascript:;" class="closed" val="隐藏">隐藏 </a>
						<img src="img/xiala.png" / class="img">
					</div>
			</c:if>
			<!-- 往返  end -->
				</div>
			</div>

<!-- 单程  改期  start-->
            <c:if test="${result.travelType==1}">
			<c:forEach items="${result.segments }" var="segmentss" varStatus="dd">
				<c:if test="${segmentss.type eq 2 }">
			<div class="check">
			<div class="one">
					<div class="list">
						<div>改期第${segmentss.flowid }次</div>
					</div>
					<div class="clickIcon">
						<img src="img/xiala.png" / class="img">
					</div>
				</div>
				<div class="wayShow showList">
				
					<c:forEach items="${segmentss.airlines }" var="airline" varStatus="d">
					<c:forEach items="${airline.flights }" var="flight" varStatus="ddd">
					<div class="flight" id="flight${ddd.index }">
						<div class="left">
							<div class="start">
								<p class="tank">${mt:fmtByStringStyle(flight.startDateTime,'MM-dd')}</p>
								<h5>
									${mt:fmtByStringStyle(flight.startDateTime,'HH:mm')}
								</h5>
							</div>
						<input type="hidden" id="changebacktime${segmentss.flowid }${gochengs.index }" value="${flight.startDateTime }">
						<input type="hidden" id="changebackendtime${segmentss.flowid }${gochengs.index }" value="${flight.endDateTime }">
						<input type="hidden" value="${fn:length(airline.flights)}" class="changebacktimess">
							<div class="start">
								<p class="tank">${mt:fmtByStringStyle(flight.endDateTime,'MM-dd')}</p>
								<h5>
									${mt:fmtByStringStyle(flight.endDateTime,'HH:mm')}
								</h5>
							</div>
						</div>
						<img src="img/luxian.png" / class="line">
						<div class="right">
							<div class="r-top">
								<%-- <h5>${result.orgCityName }</h5> --%>
								<h5>${flight.orgAirPortStr }${flight.orgTerm }</h5>
							</div>
							<div class="r-middle">
								<span>
									<img src="${applicationScope.iconServerURL}${flight.carrier }.gif"/ >
									${flight.carrierStr }${flight.flightNo }
								</span>
								<span class="tank big">${flight.planeType }</span>
								<span class="tank">${flight.cangweiType }</span>
							</div>
							<div class="r-bottom">
								<%-- <h5>${result.detCityName }</h5> --%>
								<h5>${flight.detAirPortStr }${flight.detTerm }</h5>
							</div>
						</div>
					</div>
					<c:if test="${flight.flowid >0 and flight.flowid <fn:length(airline.flights)}">
						<div class="transit" id="waitDiv${ddd.index }">
							<img src="img/zhuan.png" />
							<div>
								<h5>${flight.detAirPortStr }</h5>
								<h5><span id="changebackwaits${segmentss.flowid }${backchengs.index}">停留</span></h5>
							</div>
						</div>
						</c:if>
				</c:forEach>
				</c:forEach>
				
				</div>
				</div>
				</c:if>
				</c:forEach>
				</c:if>
        <!-- 单程  改期  end-->

       <!-- 往返  改期  start-->
			<c:if test="${result.travelType==2 }">
			<c:forEach items="${result.segments }" var="segmentss" varStatus="dd">
					<c:if test="${segmentss.type eq 2 }">
					<div class="changeDIV">
			<div class="check">
			<div class="one">
					<div class="list">
						<div>改期第${segmentss.flowid }次</div>
					</div>
					<div class="clickIcon">
						<img src="img/xiala.png" / class="img">
					</div>
				</div>
				<div class="wayShow showList">
				
						<c:forEach items="${segmentss.airlines }" var="airline" varStatus="d">
						<c:if test="${airline.type eq '1'}">
					<p style="font-size:1.3rem;color:#00afec;">去程</p>
					<c:forEach items="${airline.flights }" var="flight" varStatus="gochengs">
					<div class="flight" id="flight${gochengs.index }">
						<div class="left">
							<div class="start">
								<p class="tank">${mt:fmtByStringStyle(flight.startDateTime,'MM-dd')}</p>
								<h5>
									${mt:fmtByStringStyle(flight.startDateTime,'HH:mm')}
								</h5>
							</div>
							<div class="start">
								<p class="tank">${mt:fmtByStringStyle(flight.endDateTime,'MM-dd')}</p>
								<h5>
									${mt:fmtByStringStyle(flight.endDateTime,'HH:mm')}
								</h5>
							</div>
						</div>
						<input type="hidden" id="changetime${segmentss.flowid }${gochengs.index }" value="${flight.startDateTime }">
						<input type="hidden" id="changeendtime${segmentss.flowid }${gochengs.index }" value="${flight.endDateTime }">
						<input type="hidden" value="${fn:length(airline.flights)}" class="changetimess">
						<img src="img/luxian.png"  class="line">
						<div class="right">
							<div class="r-top">
								<%-- <h5>${result.orgCityName }</h5> --%>
								<h5>${flight.orgAirPortStr }${flight.orgTerm }</h5>
							</div>
							<div class="r-middle">
								<span>
									<img src="img/type.png" >
									${flight.carrierStr }${flight.flightNo }
								</span>
								<span class="tank big">${flight.planeType }</span>
								<span class="tank">${flight.cangweiType }</span>
							</div>
							<div class="r-bottom">
								<%-- <h5>${result.detCityName }</h5> --%>
								<h5>${flight.detAirPortStr }${flight.detTerm }</h5>
							</div>
						</div>
					</div>
					 <c:if test="${1 <fn:length(airline.flights) and !gochengs.last}"> 
						<div class="transit" id="waitDiv${gochengs.index }">
							<img src="img/zhuan.png" />
							<div>
								<h5>${flight.detAirPortStr }</h5>
								<h5><span id="changewaits${segmentss.flowid }${gochengs.index }">停留</span></h5>
							</div>
						</div>
						</c:if>
				</c:forEach>
				</c:if>
						</c:forEach>
						<c:forEach items="${segmentss.airlines }" var="airline" varStatus="d">
						<c:if test="${airline.type eq '2'}">
						<div id="backtag">				
						<p style="font-size:1.3rem;color:#00afec;">返程</p>
						</div>

				<c:forEach items="${airline.flights }" var="flight" varStatus="backchengs">
					<div class="flight" id="flightback${backchengs.index }">
						<div class="left">
							<div class="start">
								<p class="tank">${mt:fmtByStringStyle(flight.startDateTime,'MM-dd')}</p>
								<h5>
									${mt:fmtByStringStyle(flight.startDateTime,'HH:mm')}
								</h5>
							</div>
						<input type="hidden" id="changebacktime${segmentss.flowid }${gochengs.index }" value="${flight.startDateTime }">
						<input type="hidden" id="changebackendtime${segmentss.flowid }${gochengs.index }" value="${flight.endDateTime }">
						<input type="hidden" value="${fn:length(airline.flights)}" class="changebacktimess">
							<div class="start">
								<p class="tank">${mt:fmtByStringStyle(flight.endDateTime,'MM-dd')}</p>
								<h5>
									${mt:fmtByStringStyle(flight.endDateTime,'HH:mm')}
								</h5>
							</div>
						</div>
						<img src="img/luxian.png" / class="line">
						<div class="right">
							<div class="r-top">
								<%-- <h5>${result.orgCityName }</h5> --%>
								<h5>${flight.orgAirPortStr }${flight.orgTerm }</h5>
							</div>
							<div class="r-middle">
								<span>
									<img src="img/type.png"/ >
									${flight.carrierStr }${flight.flightNo }
								</span>
								<span class="tank big">${flight.planeType }</span>
								<span class="tank">${flight.cangweiType }</span>
							</div>
							<div class="r-bottom">
								<%-- <h5>${result.detCityName }</h5> --%>
								<h5>${flight.detAirPortStr }${flight.detTerm }</h5>
							</div>
						</div>
					</div>
					 <c:if test="${1 <fn:length(airline.flights) and !backchengs.last}"> 
						<div class="transit" id="backwaitDiv${backchengs.index }">
							<img src="img/zhuan.png" />
							<div>
								<h5>${flight.detAirPortStr }</h5>
								<h5><span id="changebackwaits${segmentss.flowid }${backchengs.index}">停留</span></h5>
							</div>
						</div>
						 </c:if> 
						
				</c:forEach>
				
				</c:if>
						</c:forEach>

					
				</div>
			</div>
			</div>
						</c:if>
					</c:forEach>
			</c:if>
	 <!-- 往返  改期  end-->		
			<div class="cost">
			    <c:forEach items="${result.passengers }" var="pass">
			    <c:set value="${pass.ticketPrice }" var="tiprice"/>
			    <c:set value="${pass.taxPrice }" var="tprice"/>
			    <c:set value= "${pass.insuranceNum * pass.insurancePrince }" var="baoxian"/>
			      
			    </c:forEach>
			    <c:set value="${result.passengers.size() }" var="pnum"></c:set>
				<p><span class="greay">应付金额</span><span class="money">¥${result.amount }</span></p>
				<p><span class="greay">机票</span><span class="money">¥${tiprice }*${pnum }</span></p>
				<p><span class="greay">税费</span><span class="money">¥${tprice }*${pnum }</span></p>
				<p><span class="greay">保险</span><span class="money">¥${baoxian }*${pnum }</span></p>
			</div>
			<div class="message">
				<div class="list bor">
					<span class="greay goType">出行方式</span>
					<span class="name1">
					${result.type==0?'企业':'个人'}出行
					</span>
				</div>
				<div class="list">
					<span class="greay goType">支付方式</span>
					<span class="name1">
					<c:if test="${result.payMethod eq '1' }">公司月结</c:if>
					<c:if test="${result.payMethod eq '2' }">在线支付</c:if>
					<c:if test="${result.payMethod eq '3' }">线下支付</c:if>
					<c:if test="${result.payMethod eq '4' }">预付款支付</c:if>
					</span>
				</div>
			</div>
			<div class="message">
			<c:if test="${result.passengers!=null }">
				<div class="list" >乘机人</div>
				<c:forEach items="${result.passengers }" var="passenger" varStatus="num">
				<ul class="passenerItem">
					<li class="list">
						<div class="one">
							<div >
								<span class="greay goType cx">${passenger.name }</span>
								<span>${passenger.tel}</span>
							</div>
							<div class="clickIcon">
								<img src="img/xiala.png" class="img">
							</div>
						</div>
						<ul class="crewInfor showList">
							<li class="inforF">
								<div>
									<span class="infor"><c:if test="${passenger.idType==1 }">身份证</c:if>
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
									<span class="infor">费用归属</span>
									<span>${passenger.orgid }</span>
								</div>
							</li>
							<li class="inforF">
							<c:forEach items="${result.tickets }" var="ticket" varStatus="tt">
							<c:if test="${passenger.id eq ticket.psgrId }">
								<div class="original">
									<span class="infor">原始票号</span>
									<span>${ticket.ticketNo }</span>
								</div>
								<div>
									<span class="infor">票面价格</span>
									<span>¥${passenger.ticketPrice }</span>
								</div>
								<div>
									<span class="infor">保险</span>
									<span>¥${passenger.insurancePrince }</span>
								</div>
								<div>
									<span class="infor">服务费</span>
									<span>¥${passenger.servicePrice }</span>
								</div>
							</c:if>
							</c:forEach>
							</li>
							<c:forEach items="${result.changes }" var="changes">
							<c:forEach items="${changes.tickets }" var="tickets">
							<c:if test="${passenger.id eq tickets.psgrId}">
							<li class="inforF">
								<div class="original banks">
									<span class="infor">改期票号</span>
									<span>${tickets.newTicketNo }</span>
									<span class="stateT">${tickets.status }</span>
								</div>
								<c:forEach items="${changes.fees }" var="fees">
								<c:if test="${fees.psgrId eq  passenger.id}">
								<div>
									<span class="infor">升舱费</span>
									<span>¥${fees.upgrade==null?'0.0':fees.upgrade }</span>
								</div>
								<div>
									<span class="infor">改期费</span>
									<span>¥${fees.update==null?'0.0':fees.update }</span>
								</div>
								<div>
									<span class="infor">服务费</span>
									<span>¥${fees.service==null?'0.0':fees.service }</span>
								</div>
								</c:if>
								</c:forEach>
							</li>
							</c:if>
							</c:forEach>
							</c:forEach>
							
							<c:if test="${result.refunds!=null }">
							<c:forEach items="${result.refunds }" var="refunds">
							<li class="inforF">
								<div class="original banks">
									<div>
										<span class="infor">
										<c:if test="${refunds.type eq '1' }">退出票票号</c:if>
										<c:if test="${refunds.type eq '2' }">退出票票号</c:if>

                                        </span>
										<span>${refunds.ticketNo }</span>
									</div>
									<span class="stateT">
									${refunds.status}
									</span>
								</div>
								<div>
									<span class="infor">退款理由</span>
									<span>${refunds.reason==null?'未填写退款理由':refunds.reason }</span>
								</div>
								<c:forEach items="${refunds.fees}" var="fee">
								<c:if test="${fee.psgrId eq passenger.id }">
								<div>
									<span class="infor">退票费</span>
									<span>¥${fee.refund==null?'0.0':fee.refund }</span>
								</div>
								<div>
									<span class="infor">服务费</span>
									<span>¥${fee.service==null?'0.0':fee.service }</span>
								</div>
								</c:if>
								</c:forEach>
							</li>
							</c:forEach>
							</c:if>
							
						</ul>
					</li>
					
				</ul>
				</c:forEach>
				</c:if>
			</div>
			<c:forEach items="${result.informaers }" var="informer">
			<div class="message">
				<div class="list" >
					<span   class="greay goType">通知人</span>
					<span   class="name cx">${informer.name }</span>
					<span >${informer.tel }</span>
				</div>
			</div>
			</c:forEach>
			<c:if test="${result.checkMan !=null and result.checkMan!=''}">
			<div class="message">
				<div class="list">
					<span class="greay goType">审核人</span>
					<span class="name cx">${result.checkMan.name }</span>
					<span>${result.checkMan.tel }</span>
				</div>
			</div>
			</c:if>
			<footer class="footer-transit">
			<%-- <c:if test="${data.status==2 }">
					<div class="btn" id="alipayid" style="background:#00afec" onclick="window.location.href='flight/toPay.act?orderId=${result.resultObj.data.orderId}';">去支付</div>
				</c:if> --%>
				<%--审批流  start--%>

				<c:set value="01" var="auu"></c:set>
				<c:forEach items="${result1.approvals}" var="approver"  varStatus="a" >
					<c:forEach items="${approver.auditors}" var="result1s" varStatus="b" >
						<c:if test="${approver.state=='1' or approver.state=='2' }">
							<c:if test="${result1s.state=='1' or result1s.state=='2' }">
								<c:if test="${userId==result1s.id }">
									<%--<c:set value="${result1}" var="au"></c:set>--%>
									<c:if test="${auu=='01' }">
											<div class="audit-list"  onclick="updateStatus('1')">审核通过</div>
										    <div class="refused-list">审批拒绝</div>
										<c:set value="02" var="auu"></c:set>
									</c:if>
								</c:if>
							</c:if>
						</c:if>
					</c:forEach>
				</c:forEach>

				<%--审批流   end--%>


				<%--<div class="audit-list" style="display: none;">审核通过</div>
				<div class="refused-list" style="display: none;">拒绝通过</div>--%>
				<div class="cancel-list" style="display: none;">取消订单</div>
			</footer>
		</section>
		
	</body>
	<script type="text/javascript" src="libs/jquery.min.js"></script>
	<script type="text/javascript" src="js/page/DateUtil.js?_=<%=Math.random()%>"></script>
	<script type="text/javascript" src="js/page/comm.js"></script>
	<script type="text/javascript" src="<%=basePath %>/libs/jquery.min.js"></script>
	<script type="text/javascript" src="js/MyConfirm.js?_=<%=Math.random()%>"></script>
	
	<script type="text/javascript">
	$(document).ready(function() {
			//限制字符个数
			$('.cx').each(function() {
				var maxwidth = 4;
				if($(this).html().length > maxwidth) {
					$(this).html($(this).html().substring(0, maxwidth));
					$(this).html($(this).html() + '...');
				}
			});
		});
	
	   $(function(){
		    var leng=$(".timess").val();
		    var backLeng=$(".backtimess").val();
		    var change=$(".changetimess").val();
		    var changeback=$(".changebacktimess").val();
		    if(backLeng>0 && typeof(backLeng)!="undefined"){   //往返回程停留时间计算
		    var cishu=0;
		      var backdsa=backLeng[0];
		      for(var i=0;i<backdsa;i++){
		         if(backdsa>1){
		           var endTime=$("#backendtime"+cishu).val();
		           cishu++;
		           var startTime=$("#backtime"+cishu).val();
		           var waitTime=Date.parse(startTime)-Date.parse(endTime);
		           var cu=cishu-1;
				   $("#backwaits"+cu).text("停留"+formatDuring(waitTime));
		         }
		      }
		    }
		    if(leng.length>0 && typeof(leng)!="undefined"){  //往返去程和单程停留时间确定
		        var cishu=0;
			    var dsa=leng[0];
			    for(var i=0;i<dsa;i++){
				    if(dsa>1){
				      var endTime=$("#endtime"+cishu).val();
				      cishu++;
				      var startTime=$("#time"+cishu).val();
				      var waitTime=Date.parse(startTime)-Date.parse(endTime);
				      //alert(formatDuring(waitTime));
				      var cu=cishu-1;
				      $("#waits"+cu).text("停留"+formatDuring(waitTime));
				    }
			    }
	    	}
	    	
	    	   var flowid=1;
	    	$(".changeDIV").each(function(i){
	    	if(typeof(change)!="undefined" && change.length>0 ){  //改期往返 去程 停留时间计算
	    	   var cishus=0;
	    	   var dsa=change[0];
	    	      if(dsa>1){
	    	         var endTime=$("#changeendtime"+flowid+""+cishus).val();
	    	         cishus++;
	    	         var startTime=$("#changetime"+flowid+""+cishus).val();
	    	         var waitTime=Date.parse(startTime)-Date.parse(endTime);
	    	         var cu=cishus-1;
	    	         $("#changewaits"+flowid+""+cu).text("停留"+formatDuring(waitTime));
	    	         flowid++;
	    	      }
	    	}
	    	
	    	if(typeof(changeback)!="undefined" && changeback.length>0 ){  //改期往返  回程  停留时间计算
	    	   var cishus=0;
	    	   var dsa=change[0];
	    	      if(dsa>1){
	    	         var endTime=$("#changebackendtime"+flowid+""+cishus).val();
	    	         cishus++;
	    	         var startTime=$("#changebacktime"+flowid+""+cishus).val();
	    	         var waitTime=Date.parse(startTime)-Date.parse(endTime);
	    	         var cu=cishus-1;
	    	         $("#changebackwaits"+flowid+""+cu).text("停留"+formatDuring(waitTime));
	    	         flowid++;
	    	      }
	    	}
	    	
	    	});
	   });
	   
		
		function formatDuring(mss) {
			var days = parseInt(mss / (1000 * 60 * 60 * 24));
			var hours = parseInt((mss % (1000 * 60 * 60 * 24))
					/ (1000 * 60 * 60));
			var minutes = parseInt((mss % (1000 * 60 * 60)) / (1000 * 60));
			var str="";
			//var seconds = (mss % (1000 * 60)) / 1000;
			if(days>0){
			  str=days + "d" + hours + "h"+ minutes + "m";
			}else{
			  str=hours + "h" + minutes + "m";
			}
			return str;
		}
		
		$(function(){
		$('.closed').parents('.way-content').find('.flight').each(function(i){
		 var type="${result.travelType}";
		  var ii=i+1;
		if(type ==2){
		  $("#flightback"+i).hide();
		  $("#backwaitDiv"+i).hide();
		  $("#backtag").hide();
		}
		  
		  $("#flight"+ii).hide();
		  $("#waitDiv"+i).hide();
		});
		
		})
	</script>
	<script type="text/javascript">
		/*-----页面加载头部---*/
		/* $(".headerS").load("header.html", function(data) {
			$('.zhixiang').html('订单号20170224A308');
			$('header div img').on('click', function() {
				window.history.back(-1);
			});
		}); */
		/*-------点击收起和隐藏的效果----*/
		$('.closed').on('click',function(){
			$(this).attr('val', $(this).attr('val') == '隐藏' ? '收起' : '隐藏');
			$(this).html($(this).attr('val'));
			$(this).siblings().attr('src', $(this).siblings().attr('src') == '<%=basePath %>/img/xiala.png' ? '<%=basePath %>/img/dakai.png' : '<%=basePath %>/img/xiala.png');
			
			$(this).parents('.way-content').find('.flight').each(function(i){
			if(i>0){
			$("#flight"+i).slideToggle();
			}
			if(i>-1){
			$("#waitDiv"+i).slideToggle();
			}
			$("#flightback"+i).slideToggle();
			$("#backwaitDiv"+i).slideToggle();
			});
			$("#backtag").slideToggle();
			//$(this).parents('.way-content').find('.folding').slideToggle()
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
		/*-----------改期第一次和第二次的点击事件*/
		$('.clickIcon').on('click', function() {
			$(this).children().attr('src', $(this).children().attr('src') == '<%=basePath %>/img/xiala.png' ? '<%=basePath %>/img/dakai.png' : '<%=basePath %>/img/xiala.png');
			$(this).parents('.one').siblings().slideToggle()
		});
		
		/*var url = window.location.href; //获取当前页面的url
		var len = url.length; //获取url的长度值
		var a = url.indexOf("?"); //获取第一次出现？的位置下标
		var b = url.substr(a + 1, len); //截取问号之后的内容
		var c = b.split("&"); //从指定的地方将字符串分割成字符串数组
		var arr = new Array();
		$('.tit-left .way-type').html(decodeURI(c[0].split("=")[1]));
		$('.audit').html(decodeURI(c[1].split("=")[1]));
		if($('.audit').html() == "undefined"){
			$('.audit').html('已取消');
			$('.audit').css('color','#c0c0c0');
		}
		if($('.tit-left .way-type').html() == "单程") {
			$('.folding').css('display', 'none');
			$('.hidden').css('display', 'none');
			$('.flight').css('padding-bottom', '0.85rem');
		}
		if($('.audit').html() == "已审核" || $('.audit').html() == "已取消" ) {
			$('.footer-transit').css('display', 'none');
		}*/
	</script>
	<script type="text/javascript">
		//审核通过、审核拒绝、取消订单按钮权限
		
		$(function(){
			var uid=$("#userid").val();
			var cid=$("#checkid").val();
			var status=$("#ostatus").val();
		
			//alert(uid+"  "+cid)
			//var flag=1;
		 	//审核订单按钮权限
		 	/* alert(uid+" _ "+cid+" _ "+status) */
		 /*	if(uid==cid&&status=='待审核'){//待审核(待审核订单入口)
		    	$(".refused-list").show();	
				$(".audit-list").show();
		 	}else{
		 		$(".refused-list").hide();
				$(".audit-list").hide();
		 	}*/
		 	//取消订单按钮权限
		 	if(status=='待审核'||status=='待支付'){//待审核和待支付（我的订单入口）
				$(".cancel-list").show();
		 	}else{
		 	$(".cancel-list").hide();
		 	}
		 	});
		 	var base = "${applicationScope.ctx}";
	//审核通过
	/*$('.audit-list').on('click', function() {
			var orderId=$("#oid").val();
			parm = "&orderId="+orderId + "&type=1";
		 	$.ajax({
			    type: 'POST',
			    url:base+"/intl/audit.act",
			    data:"parm="+parm, 
			    success: function(status) {
			        if(status== '0'){//成功
			        	$(this).MyConfirm({
							content : "已审核通过！"
						});	
							$(".audit").html('<h5 class="audit">审核通过</h5>');  
							$(".audit-list").hide();	
							$(".refused-list").hide();
							$(".cancel-list").hide();
			        }else{
			        	$(this).MyConfirm({
							content : "审核通过失败！"
						});	
			        }
	   			 }
					});
		});*/


		//加入审批流之后
		function updateStatus(ff){
			var yes;
			if(ff=="1"){
				yes=11;
			}else{
				yes=12;
			}
			$.ajax({
				type: 'POST',
				url:base+"/intl/updatProcess.act",
				data:{"businessType":1,"orderno":"${result.orderNo}","audit.type":5,"audit.state":yes,"audit.memo":"","audit.id":"${user.userid}",
					"audit.name":"${user.username}","audit.mobile":"${user.phone}","audit.email":"${user.email}","audit.operator.id":"${user.userid}","audit.operator.name":"${user.username}",
					"audit.operator.mobile":"${user.phone}","audit.operator.email":"${user.email}"},
				async:false,
				success: function(result) {
					location.reload();
					if(result=='0'){
						showMessage("操作成功！");
					}else{
						showMessage("操作失败！");
					}
				}
			});
		}

		/*--------提示框显示---------*/
		function showMessage(mess) {
			$(this).MyConfirm({
				content: mess
			});
		}

	//审核拒绝 true-tips sureBtn
	$('.true-tips2 ').on('click',function(){
		refuseCheck(true);	
	});
	function refuseCheck(isConfirm){
		var orderId=$("#oid").val();
			if(isConfirm){
				if ($("#refuse_reason").val() == "" || $("#refuse_reason").val() == "拒绝订单的理由") {
				    $(this).MyConfirm({
							content : "请填写审核不通过原因！"
					},function(){
			    		$(document).layerShow();
  					});	
				    } else {
					updateStatus("12");
				    	/*parm = "&orderId="+orderId + "&type=2&memo=" + $("#refuse_reason").val();
			    		$.ajax({
						    type:'POST',
						    url:base+"/intl/audit.act",
						    dataType:"text",
						    data:"parm="+parm, 
						    async: false,
						    success:function(status){
						   		if (status == "0") {  //成功拒绝审核
							      $(this).MyConfirm({
											content : "成功拒绝审核！"
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
										content : "拒绝审核失败！"
									});	
								  $('.bounced').slideUp();
								  $(document).layerHide();
								}
						    }
					    });*/
						}
					}else{
						$('.bounced').slideUp();						
						$(document).layerHide();
					}
			 }	
	//取消订单
	$('.true-tips1 ').on('click',function(){
		cancelReason(true);	
	});
	function cancelReason(isConfirm){
				var orderId=$("#oid").val();
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
			         $.ajax({
					         type:'POST',
					         url:base+"/intl/cancelOrder.act",
					         data:"parm="+parm, 
					         async: false,
					        success:function(status){
					        	if (status == "0") {
						          $(this).MyConfirm({
			  						content : "取消订单成功！"		
			  						})
						             $(".audit").html('<h5 class="audit">已取消</h5>');	               
						             $(".cancel-list").hide();
						             $(".refused-list").hide();	
						   			 $(".audit-list").hide();		
						             $('.bounced').slideUp();
		            				 return;		
						         } else{
						          $(this).MyConfirm({
			  						content : "取消订单失败！"		
			  						})
							         $('.bounced').slideUp();
							         $(document).layerHide();
						         }
					        }
				         });
						}
						}else{
						$('.bounced').slideUp();
						$(document).layerHide();
						}
	}
	</script>
</html>

</fms:Content>

</fms:ContentPage>
