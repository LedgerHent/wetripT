<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="../public/tag.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
	    <base href="<%=basePath%>">
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no" />
		<title>订单填写</title>
		<link rel="stylesheet" type="text/css" href="css/allOrder.css" />
		<link rel="stylesheet" type="text/css" href="css/MyConfirm.css" />
	</head>

	<body onpagehide="$.fn.LoadingHide()">
	<div id="container">
	<div class="bounced payment">
			<div class="payment-con" id="tested">
				<div class="payment-title">
					<p>结算方式类型</p>
				</div>
				<div class="payment-choose">
				   <c:forEach items="${payMethod.getData() }" var="pay" varStatus="vs">
				        <p>
				                <input type="hidden" name="payMethod" value="${bf.payMethod}">
								<input type="hidden" name="payMethodId" value="${bf.payMethodId}">
						<span>
						    
						     <input type="hidden" name="payMethod" value="${pay.name}">
						     <input type="hidden" name="payMethodId" value="${pay.id}">
						    ${pay.name}</span>
						<img src="img/choose.png" / class="selected">
						</p>
					</c:forEach>
				</div>
			</div>
		</div>
		<div class="bounced instructe">
			<div class="payment-con" id="test">
				<div class="payment-title">
					<p>
						<p>差旅说明类型</p>
					</p>
				</div>
				<div class="payment-choose">
					<p>
						<span><input type="hidden" name="dijiaReason" value="起飞或达到时间不合适">起飞或达到时间不合适</span>
						<img src="img/choose.png" / class="selected">
					</p>
					<p>
						<span><input type="hidden" name="dijiaReason" value="航空公司喜好">航空公司喜好</span>
						<img src="img/choose.png" / class="selected">
					</p>
					<p>
						<span><input type="hidden" name="dijiaReason" value="票价限制条件">票价限制条件</span>
						<img src="img/choose.png" / class="selected">
					</p>
					<p>
						<span><input type="hidden" name="dijiaReason" value="里程选择">里程选择</span>
						<img src="img/choose.png" / class="selected">
					</p>
					<p>
						<span><input type="hidden" name="dijiaReason" value="其他">其他</span>
						<img src="img/choose.png" / class="selected">
					</p>
				</div>
			</div>
		</div>
		<div class="bounced department">
			<div class="payment-con" id="tests">
				<div class="payment-title">
					<p>
						<p>费用归属部门名称</p>
					</p>
				</div>
				<div class="payment-choose">
				<c:forEach items="${ListEmployees}" var="org" varStatus="vs">
					<p>
						<span>
						    <input type="hidden"  id="departmentid" name="" value="${org.departmentId }">
						    <input type="hidden" name="" value="${org.departmentName }">
						    ${org.departmentName }
						</span>
						<img src="img/choose.png" / class="selected">
					</p>
				</c:forEach>
				</div>
			</div>
		</div>
		
		
	    <form id="subForm" action="flight/bookAirTicket.act" method="post">
	    <input type="hidden" id="mapKey" name="mapKey" value="${bf.mapKey}" />
	    <input type="hidden" id="traveType" name="traveType" value="${bf.traveType}" />
	    <input type="hidden" id="tripexcessinfo" name="tripexcessinfo" value="${bf.tripexcessinfo}" />
	    <input type="hidden" id="retripexcessinfo" name="retripexcessinfo" value="${bf.retripexcessinfo}" />
	    
	    <input type="hidden" id="quXuanzeReason" name="quXuanzeReason" value="${bf.quXuanzeReason}" />
	    <input type="hidden" id="quTianxieReason" name="quTianxieReason" value="${bf.quTianxieReason}" />
	    <input type="hidden" id="fanXuanzeReason" name="fanXuanzeReason" value="${bf.fanXuanzeReason}" />
	    <input type="hidden" id="fanTianxieReason" name="fanTianxieReason" value="${bf.fanTianxieReason}" />
	    
	    <input type="hidden" id="source" name="source" value="3" />
	    <input type="hidden" id="childBabySuggestBook" name="childBabySuggestBook" value="${bf.childBabySuggestBook}" />
	    <input type="hidden" id="timestamp" name="timestamp" value="${bf.timestamp }" />
	    <input type="hidden" id="skipType" name="skipType" value="${bf.skipType }" />
	    <input type="hidden" id="flightType" name="flightType" value="${bf.flightType }" />
	    <input type="hidden" id="jumpCount" name="jumpCount" value="${bf.jumpCount }" />
	    
	    <input type="hidden" id="selectusers" name="selectusers" value="${selectusers }"/>
		<input type="hidden" id="excessinfo" name="excessinfo" value="${bf.excessinfo }"/>
		
		
		
		<section>
			<header>
				<div>
					<img src="img/header.gif" onclick="fanhui(-${bf.jumpCount})"/>
				</div>
				<p class="zhixiang"><span>${applicationScope.cityMap.get(quTrip.orgcity)}</span>
				    <c:if test="${bf.flightType=='1' or bf.flightType=='3'}"><img src="img/jiantou.png" /></c:if>
				    <c:if test="${bf.flightType=='2' }"><img src="img/wanfan1.png" /></c:if>
				    <span>${applicationScope.cityMap.get(quTrip.detcity)}</span>
				</p>
			</header>
			<%--去程--%>
			<div class="title" style="background: #FFFFFF;">
				<div class="title-con goimg">
					<div class="word">
						<div class="top">
							<span>${mt:fmtByStyle(quTrip.startdate,'MM月dd日')}</span>
							<span class="week">${quTripWeek}</span>
							<span>${mt:fmtByStyle(quTrip.startdate,'HH:mm')}</span>
							<span>
								<img src="img/type.png"/>
								${quTrip.airline}
							</span>
						</div>
						<div class="bottom">${applicationScope.cityMap.get(quTrip.orgcity)}${quTrip.orgterm}-${applicationScope.cityMap.get(quTrip.detcity)}${quTrip.detterm}</div>
					</div>
					<img src="img/you.png" style="width: 1.05rem; height: 1.8rem; margin-right: 1rem;" />
				</div>
				<div class="jingji">
					<div class="btns">
						<div class="pref">
						<c:set var="char2cabin" value="${fn:substring(quTrip.airline, 0, 2) }_${quTrip.cangwei}"></c:set>
							${applicationScope.cabinNameMap.get(char2cabin)}
						</div>
						<p class="official">${traveType=='1'?"因公出行":"因私出行"}</p>
					</div>
					<div class="btn">
						<div class="btn-left">
							<span>票价:<span class="money">¥${quTrip.price}</span></span>
							<span>机场税:<span class="money">¥${quTrip.taxfee}</span></span>
							<span>燃油税:<span class="money">¥${quTrip.fueltax}</span></span>
						</div>
						<div class="btn-right">
							<a href="javascript:;" class="btna gobtn">改退政策</a>
						    <input type="hidden" value="qu">
						</div>
					</div>
				</div>
			</div>
			
			<%--返程--%>
			<c:if test="${bf.flightType=='2' or bf.flightType=='3'}">
			    <div class="title" style="background: #FFFFFF;">
				<div class="title-con backimg">
					<div class="word">
						<div class="top">
							<span>${mt:fmtByStyle(fanTrip.startdate,'MM月dd日')}</span>
							<span class="week">${fanTripWeek}</span>
							<span>${mt:fmtByStyle(fanTrip.startdate,'HH:mm')}</span>
							<span>
								<img src="img/type.png"/>
								${fanTrip.airline}
							</span>
						</div>
						<div class="bottom">${applicationScope.cityMap.get(fanTrip.orgcity)}${fanTrip.orgterm}-${applicationScope.cityMap.get(fanTrip.detcity)}${fanTrip.detterm}</div>
					</div>
					<img src="img/you.png" style="width:1.05rem; height: 1.8rem; margin-right: 1rem;" />
				</div>
				<div class="jingji">
					<div class="btns">
						<div class="pref">
						<c:set var="char2cabin" value="${fn:substring(fanTrip.airline, 0, 2) }_${fanTrip.cangwei}"></c:set>
							${applicationScope.cabinNameMap.get(char2cabin)}
						</div>
						<p class="official">${traveType=='1'?"因公出行":"因私出行"}</p>
					</div>
					<div class="btn">
						<div class="btn-left">
							<span>票价:<span class="money">¥${fanTrip.price}</span></span>
							<span>机场税:<span class="money">¥${fanTrip.taxfee}</span></span>
							<span>燃油税:<span class="money">¥${fanTrip.fueltax}</span></span>
						</div>
						<div class="btn-right">
							<a href="javascript:;" class="btna backbtn">改退政策</a>
							<input type="hidden" value="fan">
						</div>
					</div>
				</div>
			</div>
			</c:if>
			
			
			
			
			
			<ul class="item-order">
				<li class="shift-order">
					<div class="list btns">
						<div class="message-order ">
							乘机人
						</div>
						<div>
							<img src="img/add.png" class="imgs" onclick='addPassenger(1)'>
						</div>
					</div>
					<ul class="detail" id="passengerUl">
					 	
					<c:forEach items="${bf.passengerInfos}" var="ps" varStatus="status">
					
						<li class="deta-message" style="border-top: 0.1rem dotted #CCCCCC;">
						        <input type="hidden" name="passengerInfos[${status.index}].id" value="${ps.id }"><%--乘机人id--%>
							    <input type="hidden" name="passengerInfos[${status.index}].userType" value="${ps.userType }"><%--乘机人类型 ：企业员工or常旅客--%>
							    <input type="hidden" name="passengerInfos[${status.index}].userName" value="${ps.userName }"><%--乘机人名字--%>
							    <input type="hidden" name="passengerInfos[${status.index}].userIdType" value="${ps.userIdType }"><%--乘机人证件类型 --%>
							    <input type="hidden" name="passengerInfos[${status.index}].userId" value="${ps.userId }"><%--乘机人证件号--%>
							    <input type="hidden" name="passengerInfos[${status.index}].userPhone" value="${ps.userPhone }"><%--乘机人电话--%>
							    <input type="hidden" name="" value=""><%--项目号--%>
							    <input type="hidden" name="passengerInfos[${status.index}].passengerType" value="${ps.passengerType }">
							<div class="left">
								<span class="oppor widths">名字</span>
								<span class="peoname">${ps.userName }</span>
								<img src="img/jian.png" class="sub" onclick='deletePassengers(this)' >
							</div>
							<div class="left">
								<span class="span widths">${ps.userIdTypeStr }</span>
								<span class="name">${ps.userId }</span>
							</div>
							<div class="left">
								<span class="span widths">手机号</span>
								<span class="name">${ps.userPhone }</span>
							</div>
							
							<!-- 费用归属控制 0不显示 -->
							<div class="left costd costbelong" style="display: flex;flex-direction: row; justify-content: space-between;" >
								<div>
									<span class="span widths">费用归属</span>
									<input type="hidden" class="costAttachId" value="passengerInfos[${status.index}].costAttachId" >
									<input type="hidden" class="costAttachName" value="passengerInfos[${status.index}].costAttachName" >
									<span class="name1">
									    <input type="hidden" class='costid'  name="passengerInfos[${status.index}].costAttachId" value="${ps.costAttachId }"><%--乘机人费用归属--%>
									    <input type="hidden" class='costname' name="passengerInfos[${status.index}].costAttachName" value="${ps.costAttachName }"><%--乘机人费用归属--%>
									    ${ps.costAttachName }
									</span>
								</div>
								<img src="img/you.png" style="width: 1.05rem; height: 1.8rem; margin-right: 1rem;margin-top: 0;vertical-align:middle ;" />
							</div>
							
							
							<!-- 管理员控制 0不显示 -->
							<!-- 管理员start -->
							<c:if test="${isShowAdmin!='0'}">
								<div class="left costd adminss" style="display: flex;flex-direction: row; justify-content: space-between;" id="cost">
								<div>
									<span class="span widths">管理员</span>
									<span class="name2 adminchoose">
									
									</span>
								</div>
								<img src="img/you.png" style="width: 1.05rem; height: 1.8rem; margin-right: 1rem;margin-top: 0;vertical-align:middle ;" />
							</div>
							</c:if>
						
							<!-- 管理员列表 -->
							<div class="bounced admin">
								<div class="payment-con"  id="testdd">
									<div class="payment-title">
										<p>
											<p>请选择管理员</p>
										</p>
									</div>
									<div class="payment-choose">
										<div class="admins">
										</div>
									</div>
								</div>
							</div>
							
							
							<!-- end -->
							<div class="left">
								<span class="span widths">项目号</span>
								<span class="name choose">
								    <input style="border: 0;outline:none;font-size:1.5rem;" name="passengerInfos[${status.index}].project" placeholder="选填" value="${ps.project}">
								</span>
							</div>
							
							<!-- 保险控制 0不显示 -->
							
							<div class="left" style="position: relative; display: flex; flex-direction: row;justify-content: space-between;">
								<div class="message span assurance" style="margin-left: 0;" name="baoxian" >
									<span class="widths">保险</span>
									<span class="insurance">¥<span class="price">${ps.insuancePrice }</span>/份</span>
									<input type="hidden" id="insuancePrice${status.index}" name="passengerInfos[${status.index}].insuancePrice" value="${ps.insuancePrice}"/>
									<input type="hidden" name="passengerInfos[${status.index}].insuraceId" value="${ps.insuraceId}"/>
									<input type="hidden" id="isSaleInsuance${status.index}" name="passengerInfos[${status.index}].isSaleInsuance" value="${ps.isSaleInsuance}"/>
									
								</div>
								<div class="circle" >
									<img src="img/circle.png" / class="circleBtn">
								</div>
							</div>
						
							
							
							
						</li>
						</c:forEach>
					</ul>
				</li>
				<c:if test="${!empty bf.excessinfo }">
					<li class="shift-order">
						<div class="list  btns">
							<div class="message-order ">
								超标理由
							</div>
							<div>
								<img src="img/add.png" class="imgs" onclick="addReason()">
							</div>
						</div>
						<ul class="detail" id="tongzhirenUl" >
							<div class="message span assurance">
								<c:if test="${!empty bf.quXuanzeReason}">
									<span class="span widths">去程:</span>   ${bf.quXuanzeReason}
								</c:if>
								<c:if test="${!empty bf.quTianxieReason}">
									<span class="span widths">去程:</span>   ${bf.quTianxieReason}
								</c:if>
								<c:if test="${!empty bf.fanXuanzeReason}">
									<br /> <span class="span widths">返程:</span>   ${bf.fanXuanzeReason}
								</c:if>
								<c:if test="${!empty bf.fanTianxieReason}">
									<br /> <span class="span widths">返程:</span>   ${bf.fanTianxieReason}
								</c:if>
							</div>
						</ul>
					</li>
				</c:if>
				
				<li class="shift-order">
					<div class="list btns">
						<div class="message-order btn">
							通知人
						</div>
						<div>
							<img src="img/add.png" class="imgs" onclick="addPassenger(2)">
						</div>
					</div>
					<ul class="detail" id="tongzhirenUl">
					
					 <c:forEach items="${bf.tongzhirens}" var="ts" varStatus="status">
					 
						<li class="deta-message" style="border-top: 0.1rem dotted #CCCCCC;">
						    <input type="hidden" name="tongzhirens[${status.index}].id" value="${ts.id }"><%--乘机人id--%>
						    <input type="hidden" name="tongzhirens[${status.index}].userType" value="${ts.userType }"><%--乘机人类型 ：企业员工or常旅客--%>
						    <input type="hidden" name="tongzhirens[${status.index}].userName" value="${ts.userName }"><%--乘机人名字--%>
						    <input type="hidden" name="tongzhirens[${status.index}].userIdType" value="${ts.userIdType }"><%--乘机人证件类型 --%>
						    <input type="hidden" name="tongzhirens[${status.index}].userId" value="${ts.userId }"><%--乘机人证件号--%>
						    <input type="hidden" name="tongzhirens[${status.index}].userPhone" value="${ts.userPhone }"><%--乘机人电话--%>
							<div class="left">
								<span class="oppor widths">通知人</span>
								<span class="peoname">${ts.userName }</span>
								<img src="img/jian.png" class="sub" onclick='deletePassengers(this)' >
							</div>
							<%--<div class="left">
								<span class="span">${ts.userIdTypeStr }</span>
								<span class="name">${ts.userId }</span>
							</div>
							--%><div class="left">
								<span class="span widths">手机号</span>
								<span class="name">${ts.userPhone }</span>
							</div>
						</li>
						</c:forEach>
					</ul>
				</li>
				<li class="shift-order">
					<div class="list module btns" style="border-bottom: 0.1rem dotted #CCCCCC;" id="lowPriceReason">
						<div class="message-order ">
							差旅说明
							<span class="type insurance-type">
								<input type="hidden" name="dijiaReason" value="${bf.dijiaReason}">
								${bf.dijiaReason}
							</span>
						</div>
						<div>

							<img src="img/you.png" style="margin-top: 1.05rem;" />

						</div>
					</div>
					<div class="list module btns">
						<div class="message-order ">
							结算方式
							<span class="type payment-type" id="paySpan">
								<input type="hidden" name="payMethod" value="${bf.payMethod}">
								<input type="hidden" name="payMethodId" value="${bf.payMethodId}">
								${bf.payMethod}
							</span>
						</div>
						<div>

							<img src="img/you.png" style="margin-top: 1.05rem;" />

						</div>
					</div>
				</li>
				<li class="shift-order" style="display: none;" id="shenheren">
					<div class="list btns">
						<div class="message-order btn">
							订单审核人
						</div>
						<div>
							<img src="img/add.png" class="imgs" onclick="addPassenger(3)">
						</div>
					</div>
					<ul class="detail" id="checks">
					
					    <c:forEach items="${bf.checkMans}" var="cs" varStatus="status">
					
						<li class="deta-message" style="border-top: 0.1rem dotted #CCCCCC;">
						    <input type="hidden" name="checkMans[${status.index}].id" value="${cs.id }"><%--乘机人id--%>
						    <input type="hidden" name="checkMans[${status.index}].userName" value="${cs.userName }"><%--乘机人名字--%>
						    <%--<input type="hidden" name="checkMans[${status.index}].userId" value="${cs.userId }">乘机人证件号--%>
						    <input type="hidden" name="checkMans[${status.index}].userPhone" value="${cs.userPhone }"><%--乘机人电话--%>
							<div class="left">
								<span class="oppor widths">审核人</span>
								<span class="name">${cs.userName }</span>
							</div>
							<%--<div class="left">
								<span class="span">${cs.userIdTypeStr }</span>
								<span class="name">${cs.userId }</span>
							</div>
							--%><div class="left">
								<span class="span widths">手机号</span>
								<span class="name">${cs.userPhone }</span>
							</div>
						</li>
						</c:forEach>
					</ul>
				</li>
			</ul>
			<footer class="footer-price">
				<p class="price">总价：<span id="totalPrice">¥0</span></p>
				<p class="true" id="bookOrder">确认</p>
			</footer>
		</section>
	</form>	
	</div>
	<%--去程退改政策--%>
		<div class="bounced bun gomask">
			<div class="tips">
				<p class="change">退改政策</p>
				<div class="contents">
					<div style="margin-bottom: 0.2rem;">
						<p>
							<span>退票条件</span>
						</p>
						<p>${quEndorse.refund==null?"暂无退票规定信息，一切以航司规定为标准！":quEndorse.refund}</p>
					</div>
					<p>
						<span>签转条件</span> 以实际航班日期为准，航前和航后的判定标准以航班起飞前2小时作为时间划分截点:
					</p>
					<p>${quEndorse.endorsement==null?"暂无改签规定信息，一切以航司规定为标准！":quEndorse.endorsement}</p>
				</div>
				<div class="closedBtn">
					<img src="img/close.png" />
				</div>
			</div>
		</div>
		
		<%--返程退改政策--%>
		<div class="bounced bun backmask">
			<div class="tips">
				<p class="change">退改政策</p>
				<div class="contents">
					<div style="margin-bottom: 0.2rem;">
						<p>
							<span>退票条件</span>
						</p>
						<p>${fanEndorse.refund}</p>
					</div>
					<p>
						<span>签转条件</span> 以实际航班日期为准，航前和航后的判定标准以航班起飞前2小时作为时间划分截点:
					</p>
					<p>${fanEndorse.endorsement}</p>
				</div>
				<div class="closedBtn">
					<img src="img/close.png" />
				</div>
			</div>
		</div>
		
		
		<%--去程行程详情--%>
		<div class="bounceds bounced gobounced">
			<div class="boun-con">
				<p class="change">行程详情</p>
				
				<div class="content-space">
					<div class="con-top color">
						<p>
							<img src="img/type.png" /> ${quTrip.airline}
						</p>
						<div class="start">
							<h3>${mt:fmtByStyle(quTrip.startdate,'HH:mm')}</h3>
							<p>${applicationScope.airportNameMap.get(quTrip.orgcity)}${quTrip.orgterm}</p>
						</div>
						<p>${applicationScope.planeTypeMap.get(quTrip.planetype)}</p>
						
						
					</div>
					<div class="con-top con-mid">
						<p style="margin-top: 0.2rem;">${mt:fmtByStyle(quTrip.startdate,'MM月dd日')}</p>
						<div class="fly">
						    <c:if test="${quTrip.ffstr!=null and quTrip.ffstr!=''}">
								<p style="color: #11b4ed;">经停</p>
								<img src="img/jingting.png"  style="width: 9rem;"/ >
							</c:if>
							<c:if test="${quTrip.ffstr==null or quTrip.ffstr==''}">
								<p style="color: #11b4ed;">直飞</p>
								<img src="img/zhifei.png"  style="width: 9rem;"/ >
							</c:if>
						</div>
						<p>${quTrip.food=='Y'?"有餐饮":"无餐饮"}</p>
						
					</div>
					<div class="con-top color con-bot">
						<p>总时长约${mt:getTimeDis(quTrip.startdate,quTrip.arrvidate)}</p>
						<div class="finish">
							<h3>${mt:fmtByStyle(quTrip.arrvidate,'HH:mm')}</h3>
							<p>${applicationScope.airportNameMap.get(quTrip.detcity)}${quTrip.detterm}</p>
						</div>
						<p>${traveType=='1'?"因公出行":"因私出行"}</p>
					</div>
			    </div>
				<div class="closedBtn">
					<img src="img/close.png" />
				</div>
			</div>
		</div>
		
		 <%--返程行程详情--%>
		<div class="bounceds bounced backbounced">
			<div class="boun-con">
				<p class="change">行程详情</p>
				
				<div class="content-space">
					<div class="con-top color" >
						<p>
							<img src="img/type.png" /> ${fanTrip.airline}
						</p>
						<div class="start">
							<h3>${mt:fmtByStyle(fanTrip.startdate,'HH:mm')}</h3>
							<p>${applicationScope.airportNameMap.get(fanTrip.orgcity)}${fanTrip.orgterm}</p>
						</div>
						<p>${applicationScope.planeTypeMap.get(fanTrip.planetype)}</p>
						
						
					</div>
					<div class="con-top con-mid">
						<p style="margin-top: 0.2rem;">${mt:fmtByStyle(fanTrip.startdate,'MM月dd日')}</p>
						<div class="fly">
						    <c:if test="${fanTrip.ffstr!=null and fanTrip.ffstr!=''}">
								<p style="color: #11b4ed;">经停</p>
								<img src="img/jingting.png"  style="width: 9rem;"/ >
							</c:if>
							<c:if test="${fanTrip.ffstr==null or fanTrip.ffstr==''}">
								<p style="color: #11b4ed;">直飞</p>
								<img src="img/zhifei.png"  style="width: 9rem;"/ >
							</c:if>
						</div>
						<p>${fanTrip.food=='Y'?"有餐饮":"无餐饮"}</p>
						
					</div>
					<div class="con-top color con-bot">
						<p>总时长约${mt:getTimeDis(fanTrip.startdate,fanTrip.arrvidate)}</p>
						<div class="finish">
							<h3>${mt:fmtByStyle(fanTrip.arrvidate,'HH:mm')}</h3>
							<p>${applicationScope.airportNameMap.get(fanTrip.detcity)}${fanTrip.detterm}</p>
						</div>
						<p>${traveType=='1'?"因公出行":"因私出行"}</p>
					</div>
			    </div>
				<div class="closedBtn">
					<img src="img/close.png" />
				</div>
			</div>
		</div>
	</body>
<script type="text/javascript"  src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/jquery.cookie.js"></script>
	<script type="text/javascript" src="libs/orderList.js"></script>
	<script type="text/javascript" src="libs/gublic.js"></script>
	<script type="text/javascript"  src="js/MyConfirm.js"></script>
	
	
<script type="text/javascript">
$(document).LoadingHide();

var isShowAdmin=${isShowAdmin};//0-不显示|1-选填|2-必填，默认值为1

function addPassenger(skipType){
    $("#skipType").val(skipType);
    $("#subForm").attr("action","ppp/staff.act");
    if(skipType=='3'){
    	$("#subForm").attr("action","auditor/auditor.act");
    }
    $(document).LoadingShow();
    setTimeout(function(){
    	$("#subForm").submit();
    },100)
}

function addReason(){
	var excessinfo=$("#excessinfo").val();
	//alert(excessinfo);
	
	$("#subForm").attr("action","flight/toExcessReson.act");
    $(document).LoadingShow();
    setTimeout(function(){
    	$("#subForm").submit();
    },100)
}

$('.circleBtn').on('click',function(){
	var value=$(this).parent().prev().children().next().next().next().next().val();
	if(value=='0'){
		$(this).parent().prev().children().next().next().next().next().val("1");
	}else{
		$(this).parent().prev().children().next().next().next().next().val("0");
	}
	value=$(this).parent().prev().children().next().next().next().next().val();
	if(value==1){
		$(this).animate({
			left: '0.1rem'
		}, 500, function() {
			$(this).parent().addClass('active');
		});
	}else{
		$(this).animate({
			left: '2.52rem'
		}, 500, function() {
			$(this).parent().removeClass('active');
		});
	}
	totalPrce();
});

//保险控制  
$(function(){
		
	var insuraceId=${InsruanceIsNull};//企业是否有维护保险
	
	var isShowInsruance=${isShowInsruance};//保险是否显示	
		
		
	if(${isShowInsruance}=='2'&&${InsruanceIsNull}=='-1'){
	 		$(this).MyConfirm({
	  					content : "请维护企业保险!"		
	  					});
	return false;
	}
	 if(isShowInsruance=='0'){
	 	$(".assurance").hide();
	 	$(".circle").hide();//隐藏选择按钮并置灰
	 	$(".circleBtn").parent().prev().children().next().next().next().next().val("1");
	}
	/* //管理员
	var isShowAdmin=${isShowAdmin};//0-不显示|1-选填|2-必填，默认值为1
	//alert(isShowAdmin);
	//var isShowAdmin=0;
	if(isShowAdmin=='0'){
		$('.costd').eq(1).hide();
		$('.costd .name2').html("");
	}*/
	
	//费用归属控制
	var control=${control};
	if(control=='0'){//不显示
		
		/* $('.costbelong').find('input:eq(0)').each(function(){

		$(this).val('-1');
		})
		$('.costbelong').find('input:eq(3)').each(function(){

		$(this).val('-1');
		})
		$('.costd .name1').html(""); */
		$(".costbelong").hide();
		$('.costid').val('-1');
		$('.costname').val('');
	} 
})




/*-----------保险开与关的效果实现----------*/
$('.circle .circleBtn').each(function(i){
	var value=$(this).parent().prev().children().next().next().next().next().val();
	if(1==value) {
		$(this).animate({
			left: '0.1rem'
		}, 500, function() {
			$(this).parent().addClass('active');
		});

	} else {
		$(this).animate({
			left: '2.52rem'
		}, 500, function() {
			$(this).parent().removeClass('active');
		});

	}
});


function totalPrce(){
	var totalTicketPrice='${totalTicketPrice}';//单人往返价格（机票+基建+燃油）
    var passengers = document.getElementById('passengerUl').getElementsByTagName('li');
    var passengerSize=passengers.length;
    var flightType='${bf.flightType}';
    var  baoxianPrices=0;
    for ( var i = 0; i < passengers.length; i++) {
    	var baoxiannum=$("#isSaleInsuance"+i).val();
    	var insuancePrice=$("#insuancePrice"+i).val();
    	if(baoxiannum=='0'){
    		baoxianPrices=baoxianPrices+parseFloat(insuancePrice);
    	}
	}
    if(flightType=='2' || flightType=='3'){
    	baoxianPrices=parseFloat(baoxianPrices)*parseFloat(2)
    }
    //alert(baoxianPrices)
	var totalPrice=parseFloat(totalTicketPrice)*parseFloat(passengerSize)+parseFloat(baoxianPrices)
	$("#totalPrice").text("￥"+totalPrice);
}


		
$(function(){
	//totalPrice
	//totalTicketPrice
	
    totalPrce();
	paySpanHide();
	
    
})
function paySpanHide(){
	var paymathod=$("#paySpan").find("input:eq(1)").val();//付款方式：1-公司月结，2-个人支付，3-公司现结,4:预付款
	var verify='${verify}' //1 需要 0不需要
	if((paymathod=='1'||paymathod=='4')&&verify!='0'){//都需要审核 
		$("#shenheren").show();
	}else{
		$("#shenheren").hide();
	}
	
	var nolowpriceReason='${nolowpriceReason}';//未选择低价航班原因是否需选择（1为需选择，0为不需选择）
	var isLowPrice='0';//该航班是否是最低价；0是最低价(微信暂时不要最低价理由，所以暂时写死不显示)
	if(nolowpriceReason=='0'&&isLowPrice!='0'){
		$("#lowPriceReason").show();
	}else{
		$("#lowPriceReason").hide();
	}
	
}		
//$('#paySpan').each(function(i){
	//paySpanHide();
//})		
$('.payment .payment-choose p').on('click',function(){
	paySpanHide();
})
$('#bookOrder').on('click',function(){
	/*保险*/
	//判断保险 1-选填  2-必填 没有维护保险情况下
	if(${isShowInsruance}=='2'&&${InsruanceIsNull}=='-1'){
	 		$(this).MyConfirm({
	  					content : "请维护企业保险!"		
	  					});
	return false;
	}
	//必填情况下 
	var value=$(".circleBtn").parent().prev().children().next().next().next().next();
	var  iflag='0';
	$(value).each(function(){
	if($(this).val()=='1'){
	iflag='1';
	}
	});
	
	if(${isShowInsruance}=='2'&&iflag=='1'){
		$(this).MyConfirm({
            content: "保险为必填！"
        });
        return false;
	}
	
	//必填情况下 
	var adminName=$('.adminchoose');
	var aflag='0';
	$(adminName).each(function(){
	if($(this).html()==''){
	aflag='1';
	}
	});
	if(${isShowAdmin}=='2'&&aflag=='1'){
		$(this).MyConfirm({
            content: "管理员为必填！"
        });
        return false;
	}
	
	var passengers = document.getElementById('passengerUl').getElementsByTagName('li');
	var passengeridStr='';
	if(passengers.length<1){
		$(this).MyConfirm({
            content: "请选择乘机人！"
        });
		//alert("请选择乘机人！")
		return false;
		
	}else{
		  for ( var i = 0; i < passengers.length; i++) {
			 var userid=$(passengers[i]).find("input:eq(0)").val();//乘机人id
			 var usertype=$(passengers[i]).find("input:eq(1)").val();//乘机人类型
			 var passengerid=$(passengers[i]).find("input:eq(4)").val();//证件号
			 if(passengerid==null||passengerid==''){
					$(this).MyConfirm({
			            content: "请选择证件号！"
			        });
					//alert("请选择费用归属！")
					return false;
				}
			 for(var j = i+1; j < passengers.length; j++){
				 var userid1=$(passengers[j]).find("input:eq(0)").val();//乘机人id
				 var usertype1=$(passengers[j]).find("input:eq(1)").val();//乘机人类型
				 if(userid==userid1 && usertype==usertype1){
					 $(this).MyConfirm({
				            content: "乘机人不能重复！"
				        });
						//alert("请选择费用归属！")
						return false;
				 }
			 }
			 passengeridStr = passengeridStr+passengerid+ ",";
		  }
		  
		  
		  
		    var verify='${verify}' //1 需要 0不需要
		    //alert("verify"+verify);
			var paymathod=$("#paySpan").find("input:eq(1)").val();//付款方式：1-公司月结，2-个人支付，3-公司现结,4:预付款
			if(paymathod=='1'||paymathod=='4'){
				for ( var j = 0; j < passengers.length; j++) {//月结合预付款是需要费用归属
					var cost=$(passengers[j]).find('.name1').find("input:eq(0)").val();
					if(${control}=='2'&&cost==null||cost==''){
						$(this).MyConfirm({
				            content: "请选择费用归属！"
				        });
						//alert("请选择费用归属！")
						return false;
					}
				}
				if(verify!='0'){//都需要审核 
					var checks = document.getElementById('checks').getElementsByTagName('li');
					if(checks.length<1){
						$(this).MyConfirm({
				            content: "请选择审核人！"
				        });
						//alert("请选择审核人！")
						return false;
					}
					
				}
			}
	}
	  passengeridStr = passengeridStr.substring(0,passengeridStr.length-1);
	  var flag=true;
	  $.ajax({
			url:'flight/passengercheck.act',
			dataType:'json',
			type:'POST',
			async:false,
			data:{'passengeridStr':passengeridStr,'mapKeys':'${bf.mapKey}'},
			success:function(data){
				if(data!=null){
					 $(this).MyConfirm({
				            content: "该证件号:"+data+"已经预定了本次航班的该舱位！"
				        });
					 flag=false;
			    }
			}
	 });
		
	if(!flag){
		return false;
	}
   
	$(document).LoadingShow();
    setTimeout(function(){
    	$("#subForm").attr("action","flight/bookAirTicket.act");
    	
    	$("#subForm").submit();
    },100)

	
	
});
function deletePassengers(obj){
	$(obj).parent().parent().remove();
    
	//totalPrce();
	$("#subForm").attr("action","flight/deleteFlush.act");
	$(document).LoadingShow();
    $("#subForm").submit();
}


$('.gobtn').on('click', function() {
	$(this).layerShow('gomask');
});
$('.backbtn').on('click', function() {
	$(this).layerShow('backmask');
});
$('.closedBtn img').on('click', function() {
	$(this).layerHide('bounced');
});
		
		
/*航班详情提示框的显示和隐藏*/
$('.goimg').on('click', function() {
	$(this).layerShow('gobounced');
});
/*航班详情提示框的显示和隐藏*/
$('.backimg').on('click', function() {
	$(this).layerShow('backbounced');
});
var jumpcount=0;
function fanhui(count){
	
	var passengers = document.getElementById('passengerUl').getElementsByTagName('li');
	var tongzhirens = document.getElementById('tongzhirenUl').getElementsByTagName('li');
	jumpcount=count;
	var verify='${verify}' //1 需要 0不需要
	var paymathod=$("#paySpan").find("input:eq(1)").val();//付款方式：1-公司月结，2-个人支付，3-公司现结,4:预付款
	if(paymathod=='1'||paymathod=='4'){
		if(verify!='0'){//都需要审核 
			var checks = document.getElementById('checks').getElementsByTagName('li');
			if(passengers.length>0 || tongzhirens.length>0 || checks.length>0){
				$(this).MyConfirm({
		            content: "该订单尚未完成，确定要放弃该订单？",
		            hasCancelBtn:true
		        }, callback);
			}else{
				history.go(count); 
			}
			
		}else{
			if(passengers.length>0 || tongzhirens.length>0){
				$(this).MyConfirm({
					content: "该订单尚未完成，确定要放弃该订单？",
					hasCancelBtn:true
		        }, callback);
			}else{
				history.go(count); 
			}
		}
	}else{
		if(passengers.length>0 || tongzhirens.length>0){
			$(this).MyConfirm({
				content: "该订单尚未完成，确定要放弃该订单？",
				hasCancelBtn:true
	        }, callback);
		}else{
			history.go(count); 
		}
	}
}
function callback(isConfirm) {
	if(isConfirm==true){
	    history.go(jumpcount); 
	}
}
		</script>
</html>