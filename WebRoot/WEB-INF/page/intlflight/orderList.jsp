<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../public/tag.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no" />
		<title>订单填写</title>
		<style>

			*{margin:0;padding:0}
			li {list-style:none}
			img {vertical-align:middle;border:none}
			a {text-decoration:none; color: #00AFEC;}
			input{border: none; outline: none;}

			.order_{background: #FFFFFF;font-size: 1.5rem;margin-bottom: 1rem;}
			#approve .app_title{line-height: 4rem;text-indent: 1rem;border-bottom: 0.05rem dotted #CCCCCC;color: #666666;}
			.approve_list li{line-height: 4rem;display: flex;flex-direction: row;justify-content: flex-start;padding: 0 1rem;}
			.approve_list .tips{width: 1.05rem;height: 1.05rem;vertical-align: top;padding-top: 0.5rem;}
			.radioBtn{margin:auto 0;}
			.radioBtn img{width:2.2rem;height:2.2rem;}
		</style>

		<link rel="stylesheet" type="text/css" href="../css/intlAllOrder.css" />
	</head>



	<body>
		<div class="bounceds bounced">
			<div class="boun-con">
				<p class="change">航班详情</p>
				<div class="content-space">
						<div class="con-top color">
							<p>
								<img src="${applicationScope.iconServerURL}${segments.get(0).getIntlFlights().get(0).getCarrier()}.gif" /><span>${segments.get(0).getIntlFlights().get(0).getCarrierStr()}${segments.get(0).getIntlFlights().get(0).getAirline() }
							</p>
							<div class="start">
								<h3>${mt:fmtByStringStyle(startTime,'HH:mm')}</h3>
								<p>${segments.get(0).getIntlFlights().get(0).getOrgAirPortStr() }${segments.get(0).getIntlFlights().get(0).getOrgTerm() }</p>
							</div>
							<p>${segments.get(0).getIntlFlights().get(segments.get(0).getIntlFlights().get(0).getTransitCount()).getPlaneType() }</p>
	
						</div>
						<div class="con-top con-mid">
							<p style="margin-top: 0.2rem;">${startData }</p>
							<div class="fly">
								<p style="color: #11b4ed;">
									<c:if test="${segments.get(0).getIntlFlights().get(0).getTransitCount() == 0 }">
										直飞
									</c:if>
									<c:if test="${segments.get(0).getIntlFlights().get(0).getTransitCount() > 0 }">
										中转${segments.get(0).getIntlFlights().get(segments.get(0).getIntlFlights().get(0).getTransitCount()).getDetAirPortStr() }
									</c:if>
								</p>
								<img src="../img/zhifei.png" style="width: 9rem;" />
							</div>
						</div>
						<p></p>
						<p>
							${segments.get(0).getIntlFlights().get(0).getFood() }
						</p>
					<div class="con-top color con-bot">
						<p>总时长约${times }</p>
						<div class="finish">
							<h3>${mt:fmtByStringStyle(EndTimes ,'HH:mm')}</h3>
							<p>
							<c:if test="${segments.get(0).getIntlFlights().get(0).getTransitCount() == 0 }">
								${segments.get(0).getIntlFlights().get(0).getDetAirPortStr() }
							</c:if>
							<c:if test="${segments.get(0).getIntlFlights().get(0).getTransitCount() > 0 }">
								${segments.get(0).getIntlFlights().get(segments.get(0).getIntlFlights().get(0).getTransitCount()).getDetAirPortStr() }
							</c:if>
							${segments.get(0).getIntlFlights().get(0).getDetTerm() }</p>
						</div>
						<p>因公出行</p>
					</div>
				</div>
				<div class="closedBtn">
					<img src="../img/close.png" />
				</div>
			</div>
		</div>
		<div class="bounced bun">
			<div class="tips">
				<p class="change">退改政策</p>
				<div class="contents">
					<div style="margin-bottom: 0.2rem;">
						<p>
							<span>退票条件</span>
						</p>
						<p id="tuipiao">无信息</p>
					</div>
					<p>
						<span>签转条件</span> 以实际航班日期为准，航前和航后的判定标准以航班起飞前2小时作为时间划分截点:
					</p>
					<p id="gaiqian">无信息</p>
					<p>
						<span>误机条件</span>
					</p>
					<p id="wuji">无信息</p>
				</div>
				<div class="closedBtn">
					<img src="../img/close.png" />
				</div>
			</div>
		</div>
		<div class="bounced payment">
			<div class="payment-con" id="tested">
				<div class="payment-title">
					<p>结算方式类型</p>
				</div>
					
				<div class="payment-choose">
					<c:forEach items="${payMethod }" var="pm">
					
						<p>
							<span class="type payment-type" id="paySpan">
								<%-- <input type="hidden" name="payMethod" value="${pm.payMethod}"> --%>
								<input type="hidden" name="payMethodId" value="${pm.id}">
								${pm.name}
							</span>
							<img src="../img/choose.png" / class="selected">
						</p>
					</c:forEach>
					
					<!-- <p>
						<span>公司现结</span>
						<img src="../img/choose.png" / class="selected">
					</p>
					<p>
						<span>个人支付</span>
						<img src="../img/choose.png" / class="selected">
					</p> -->
				</div>
			</div>
		</div>
		<div class="bounced instructe" id="instructe">
			<div class="payment-con" id="test">
				<div class="payment-title">
					<p>
						<p>差旅说明类型</p>
					</p>
				</div>
				<div class="payment-choose">
					<p>
						<span>起飞或达到时间不合适</span>
						<img src="../img/choose.png" / class="selected">
					</p>
					<p>
						<span>航空公司喜好</span>
						<img src="../img/choose.png" / class="selected">
					</p>
					<p>
						<span>票价限制条件</span>
						<img src="../img/choose.png" / class="selected">
					</p>
					<p>
						<span>里程选择</span>
						<img src="../img/choose.png" / class="selected">
					</p>
					<p>
						<span>其他</span>
						<img src="../img/choose.png" / class="selected">
					</p>
				</div>
			</div>
		</div>
		<!-- 费用归属部门名称 -->
		
		<div class="bounced department">
			<div class="payment-con" id="tests">
				<div class="payment-title" >
					<p>
						<p>费用归属部门名称</p>
					</p>
				</div>
				<div class="payment-choose">
					<c:forEach items="${ListEmployees}" var="org" varStatus="vs">
						<p>
							<span>
							    <input type="hidden" name="" value="${org.departmentId }">
							    <input type="hidden" name="" value="${org.departmentName }">
							    
							    ${org.departmentName }
							</span>
							<img src="../img/choose.png" / class="selected">
						</p>
					</c:forEach>
					
				</div>
			</div>
		</div>
		
		<!-- 管理员列表-->
		
		<div class="bounced adminlist">
			<div class="payment-con" id="">
				<div class="payment-title" >
					<p>
						<p>管理员选择</p>
					</p>
				</div>
				<div class="payment-choose">
					
					
				</div>
			</div>
		</div>
		
		
		<!-- 保险列表 -->
		<div class="bounced insurances">
			<div class="payment-con" >
				<div class="payment-title">
					<p>
						<p>保险选择</p>
					</p>
				</div>
				<div class="payment-choose">
				<c:forEach items="${insurancelist}" var="ins" varStatus="vs">
					<p>
						<span>
						    <input type="hidden"  id="insuranceid" name="" value="${ins.id }">
						    <input type="hidden" id="insuranceidPrice" name="" value="${ins.price}">
						  <span class="price">${ins.price }</span>  /份
						</span>
						<img src="../img/choose.png" / class="selected">
					</p>
				</c:forEach>
				
							
							<c:if test="${isShowInsruance!='2'}">
							<p>
							<span  class="type payment-type" id="paySpan">
							<input type="hidden"  id="insuranceid" name="" value="-1">
						    <input type="hidden" id="insuranceidPrice" name="" value="0">不需要保险</span>
							</p>
							</c:if>
							
						
				</div>
			</div>
		</div>
		
		<!-- 常旅客修改or新增页面 -->
			<div class="updatePasengerPage" style="display:none">
				<iframe class="Iframe" name="updateIframe" id="IframeID" src="" style="display:block;width:100%;height:100%;"></iframe>
			</div>

		<div class="bounced bun shen">
			<div class="tips">
				<p class="change">审批政策详情</p>
				<div class="contents shenpi">

				</div>
				<div class="closedBtn">
					<img src="../img/close.png" />
				</div>
			</div>
		</div>


		
		<div class="pages" style="display: none;">
			<header class="addH">
				<div class="turnIcon">
					<img  src="../img/header.png" />
				</div>
				<div class="title"></div>
				<div class="login">
					<img src="../img/addBtn.png">
				</div>
			</header>
			<section>
				<div class="Tab">
					<div class="tabTitle">
						<div class="flyer highlight">企业员工</div>
						<div class="flyer">常旅客</div>
					</div>
					<div class="tabContent" >
					
						<ul class="show">
						 <c:forEach items="${listStaff}" var="staff" varStatus="status">
					<li id="certificate${status.count}" class="left-passener">
						<div class="left-passener" ins="ins" >
							<div class="middle-table">
								<div class="Ppassener-name">
								    <input type="hidden" value="${staff.id}"><%--乘机人id--%>
								    <input type="hidden" value="${staff.name}"><%--乘机人名字--%>
								    <input type="hidden" value="${staff.mobile}"><%--乘机人手机号--%>
								    <input type="hidden" value="${staff.ids[0].type}"><%--乘机人证件号类型--%>
								    <input type="hidden" value="${staff.ids[0].num}"><%--乘机人证件号--%>
								    <input type="hidden" value="${staff.type}"><%--乘机人类型--%>
								    <input type="hidden" value="1"><%-- 1-企业员工，2常旅客 --%>
								</div>
								<input type="hidden" name="passengerId" class="passengerId" value="${staff.id}"/>
								
								<p><span class="passener-name"> ${staff.name }</span></p>
								<p>
									<span class="name">手机号</span>
									<span class="passener-phone">${staff.mobile }</span>
								</p>
								<p>
								<span class="TypeName"><span class="name"><c:if test="${staff.ids[0].type==1 }"><span>身份证</span></c:if>
								<c:if test="${staff.ids[0].type==2 }"><span>护照</span></c:if>
								<c:if test="${staff.ids[0].type==3 }"><span>海员证</span></c:if>
								<c:if test="${staff.ids[0].type==4 }"><span>回乡证</span></c:if>
								<c:if test="${staff.ids[0].type==5 }"><span>军官证</span></c:if>
								<c:if test="${staff.ids[0].type==6 }"><span>港澳通行证</span></c:if>
								<c:if test="${staff.ids[0].type==7 }"><span>台胞证</span></c:if>
								<c:if test="${staff.ids[0].type==99 }"><span>其他</span></c:if></span></span>
									<span class="passener-type">${staff.ids[0].num }</span>
								</p>
								
							
								<input type="hidden" id="typeNum${status.count}" value="<%-- ${fn:length(staff.ids)} --%>"/>
								<input type="hidden"  value="${staff.type}"/>
								<%-- <input type="hidden"  id="platOrg" value="${staff.isplatFormOrg }" /> --%>
								<c:forEach items="${staff.ids }" var="ids" varStatus="statu">
									<input class="certifi${status.count}" type="hidden" 
										id="${status.count}typeNu${statu.count}" value="${ids.type}" />
									<input type="hidden" id="${status.count}userIdType${statu.count}" value="${ids.num}"/>
								</c:forEach>
							
							</div>
						</div>
					</li>
						</c:forEach> 
						
							
						
						</ul>
						<ul>
						
						
						
				
							
						<c:forEach  items="${Tlist}"  var="passenger">
					<li ids="passener" class="passengerup">
						
								<div ins="ins" >
									<div class="Ppassener-name">
									<input type="hidden" value="${passenger.id }"><%--乘机人id--%>
									<input type="hidden" value="${passenger.name }"><%--乘机人名字--%>
									<input type="hidden" value="${passenger.mobile }"><%--乘机人手机号--%>
									<input type="hidden" value="${passenger.ids[0].type}"><%--乘机人证件号类型--%>
									<input type="hidden" value="${staff.ids[0].num}"><%--乘机人证件号--%>
									<input type="hidden" value="${passenger.ids[0].num }"><%--乘机人类型--%>
									<input type="hidden" value="2"><%-- 1-企业员工，2常旅客 --%>
									</div>
									<p class="userName" class="editor"><span class="passener-name">${passenger.name }</span></p>
								
									<p>
									<span class="name">手机号</span>
									<span class="passener-phone">${passenger.mobile }</span>
								</p>
								<p>
								<input type="hidden" name="passengerId" class="passengerId" value="${passenger.id}"/>
								<span class="TypeName"><span class="name"><c:if  test="${passenger.ids[0].type==1 }"><span>身份证</span></c:if>
								<c:if test="${passenger.ids[0].type==2 }"><span>护照</span></c:if>
								<c:if test="${passenger.ids[0].type==3 }"><span>海员证</span></c:if>
								<c:if test="${passenger.ids[0].type==4 }"><span>回乡证</span></c:if>
								<c:if test="${passenger.ids[0].type==5 }"><span>军官证</span></c:if>
								<c:if test="${passenger.ids[0].type==6 }"><span>港澳通行证</span></c:if>
								<c:if test="${passenger.ids[0].type==7 }"><span>台胞证</span></c:if>
								<c:if test="${passenger.ids[0].type==99 }"><span>其他</span></c:if> </span></span>
								<span class="passener-type">${passenger.ids[0].num }</span> 
								</p>
						
									
								<input type="hidden" class="passener-uid" value="${passenger.id }">
								</div>
							
								
						
						<div class="right-table"  onclick="addPassenger('2',${passenger.id})">
							<a href="javascript:;" ><img src="../img/bianji.png" /></a>
						</div>
					</li>

							</c:forEach>
					
						
							
						</ul>
					</div>
				</div>
			</section>
		</div>


		<%--订单审核人列表--%>
		<div class="checkpages" style="display: none;">
			<header class="addH">
				<div class="turnIcon">
					<img id="closelist" src="../img/header.png" />
				</div>
				<div class="title"></div>
				<div class="login">
					<img src="../img/addBtn.png">
				</div>
			</header>
			<section>
				<div class="Tab">

					<div class="tabContent" >

						<ul class="show" id="AuditorList">




						</ul>
					</div>
				</div>
			</section>
		</div>



		<div class="wrap">
			<div class="headerS"></div>
			
	 
			
			<section>
				<div class="flightD">
					<c:if test="${hangcheng eq '1' }">
						<div class="detailM">
							<div>
								<p class="detailT">
									<span class="topDate gap">${startDataCH }</span>
									<span class="week gap">${week }</span>
									<span class="topTime gap">${mt:fmtByStringStyle(startTime ,'HH:mm')}</span>
									<span class="topType">
									<img src="${applicationScope.iconServerURL}${segments.get(0).getIntlFlights().get(0).getCarrier()}.gif"/>
										<span>${segments.get(0).getIntlFlights().get(0).getCarrierStr()}</span><span>${segments.get(0).getIntlFlights().get(0).getAirline() }</span>
									</span>
								</p>
								<p class="detailB">
									<span class="gap">${segments.get(0).getIntlFlights().get(0).getOrgAirPortStr() }${segments.get(0).getIntlFlights().get(0).getOrgTerm() }—
											<c:if test="${segments.get(0).getIntlFlights().get(0).getTransitCount() == 0 }">
												${segments.get(0).getIntlFlights().get(0).getDetAirPortStr() }
											</c:if>
											<c:if test="${segments.get(0).getIntlFlights().get(0).getTransitCount() > 0 }">
												${segments.get(0).getIntlFlights().get(segments.get(0).getIntlFlights().get(0).getTransitCount()).getDetAirPortStr() }
											</c:if>
											${segments.get(0).getIntlFlights().get(0).getDetTerm() }
									</span>
									
									<c:if test="${segments.get(0).getIntlFlights().get(0).getTransitCount() == 0 }">
										直飞
									</c:if>
									<c:if test="${segments.get(0).getIntlFlights().get(0).getTransitCount() > 0 }">
										<span>中转${segments.get(0).getIntlFlights().get(segments.get(0).getIntlFlights().get(0).getTransitCount()).getDetAirPortStr() }约${times }</span>
									</c:if>
								</p>
							</div>
							<div class="imgIcon">
								<img src="../img/you.png" / class="chooseR">
							</div>
						</div>
						
						<div class="travel">
							<p>
								<span class="priv">${cangwei }</span>
								<p>
									<c:if test="${traveltype == 0 }"><span>因公出行</span></c:if>
									<c:if test="${traveltype == 1 }"><span>因私出行</span></c:if>
								</p>
							</p>
							<div class="travelB">
								<p>
									<span>含税票价:<span class="money"><span>¥</span><span class="fare">${price }</span></span>
									</span>
									<span>总时长约${times }</span>
								</p>
								<p>
									<a href="javascript:;" class="btna">
									<input type="hidden" id="mapkey1" value="${mapkey1 }"/>
									<input type="hidden" id="cangwei1" value="${cangweidesc }"/>
									改退政策</a>
								</p>
							</div>
						</div>
					</c:if>
					<input type="hidden" value="${startTime}" id="StartTime">
					<c:if test="${hangcheng eq '2' }">
						<div class="detailM">
							<div>
								<p class="detailT">
									<span class="topDate gap">${startDataCH1 }</span>
									<span class="week gap">${week1 }</span>
									<span class="topTime gap">${mt:fmtByStringStyle(startTime1 ,'HH:mm')}</span>
									<span class="topType">
									<img src="${applicationScope.iconServerURL}${segments.get(1).getIntlFlights().get(0).getCarrier()}.gif"/>
										<span>${segments.get(1).getIntlFlights().get(0).getCarrierStr()}</span><span>${segments.get(1).getIntlFlights().get(0).getAirline() }</span>
									</span>
								</p>
								<p class="detailB">
									<span class="gap">${segments.get(1).getIntlFlights().get(0).getOrgAirPortStr() }${segments.get(1).getIntlFlights().get(0).getOrgTerm() }—
											<c:if test="${segments.get(1).getIntlFlights().get(0).getTransitCount() == 0 }">
												${segments.get(1).getIntlFlights().get(0).getDetAirPortStr() }
											</c:if>
											<c:if test="${segments.get(1).getIntlFlights().get(0).getTransitCount() > 0 }">
												${segments.get(1).getIntlFlights().get(segments.get(1).getIntlFlights().get(0).getTransitCount()).getDetAirPortStr() }
											</c:if>
											${segments.get(1).getIntlFlights().get(0).getDetTerm() }
									</span>
									
									<c:if test="${segments.get(1).getIntlFlights().get(0).getTransitCount() == 0 }">
										直飞
									</c:if>
									<c:if test="${segments.get(1).getIntlFlights().get(0).getTransitCount() > 0 }">
										<span>中转${segments.get(1).getIntlFlights().get(segments.get(1).getIntlFlights().get(0).getTransitCount()).getDetAirPortStr() }约${times1 }</span>
									</c:if>
								</p>
							</div>
							<div class="imgIcon">
								<img src="../img/you.png" / class="chooseR">
							</div>
						</div>
						
						<div class="travel">
							<p>
								<span class="priv">${cangwei }</span>
								<p>
									<c:if test="${traveltype == 0 }"><span>因公出行</span></c:if>
									<c:if test="${traveltype == 1 }"><span>因私出行</span></c:if>
								</p>
							</p>
							<div class="travelB">
								<p>
									<span>含税票价:<span class="money"><span>¥</span><span class="fare">${price }</span></span>
									</span>
									<span>总时长约${times }</span>
								</p>
								<p>
									<a href="javascript:;" class="btna">
									<input type="hidden" id="mapkey1" value="${mapkey1 }"/>
									<input type="hidden" id="cangwei1" value="${cangweidesc }"/>
									改退政策</a>
								</p>
							</div>
						</div>
						
						
						<div class="detailM">
							<div>
								<p class="detailT">
									<span class="topDate gap">${startDataCH }</span>
									<span class="week gap">${week }</span>
									<span class="topTime gap">${mt:fmtByStringStyle(startTime ,'HH:mm')}</span>
									<span class="topType">
									<img src="${applicationScope.iconServerURL}${segments.get(0).getIntlFlights().get(0).getCarrier()}.gif"/>
										<span>${segments.get(0).getIntlFlights().get(0).getCarrierStr()}</span><span>${segments.get(0).getIntlFlights().get(0).getAirline() }</span>
									</span>
								</p>
								<p class="detailB">
									<span class="gap">${segments.get(0).getIntlFlights().get(0).getOrgAirPortStr() }${segments.get(0).getIntlFlights().get(0).getOrgTerm() }—
											<c:if test="${segments.get(0).getIntlFlights().get(0).getTransitCount() == 0 }">
												${segments.get(0).getIntlFlights().get(0).getDetAirPortStr() }
											</c:if>
											<c:if test="${segments.get(0).getIntlFlights().get(0).getTransitCount() > 0 }">
												${segments.get(0).getIntlFlights().get(segments.get(0).getIntlFlights().get(0).getTransitCount()).getDetAirPortStr() }
											</c:if>
											${segments.get(0).getIntlFlights().get(0).getDetTerm() }
									</span>
									
									<c:if test="${segments.get(0).getIntlFlights().get(0).getTransitCount() == 0 }">
										直飞
									</c:if>
									<c:if test="${segments.get(0).getIntlFlights().get(0).getTransitCount() > 0 }">
										<span>中转${segments.get(0).getIntlFlights().get(segments.get(0).getIntlFlights().get(0).getTransitCount()).getDetAirPortStr() }约${times }</span>
									</c:if>
								</p>
							</div>
							<div class="imgIcon">
								<img src="../img/you.png" / class="chooseR">
							</div>
						</div>
						
						<div class="travel">
						<p>
							<span class="priv">${cangwei }</span>
							<p>
								<c:if test="${traveltype == 0 }"><span>因公出行</span></c:if>
								<c:if test="${traveltype == 1 }"><span>因私出行</span></c:if>
							</p>
						</p>
						<div class="travelB">
							<p>
								<span>含税票价:<span class="money"><span>¥</span>0</span>
								</span>
								<span>总时长约${times }</span>
							</p>
							<p>
								<a href="javascript:;" class="btna">
								<input type="hidden" id="mapkey1" value="${mapkey1 }"/>
								<input type="hidden" id="cangwei1" value="${cangweidesc }"/>
								改退政策</a>
							</p>
						</div>
					</div>
					</c:if>
					
					
				</div>
				<ul class="orderFill">
					<li class="fillList">
						<div class="airp">
							<p>乘机人</p>
							<p style="margin: auto 0;">
								<img src="../img/add.png"  id="imgEd" >
							</p>
						</div>
						<ul id="passener">

						</ul>
				
					<li class="fillList">
						<div class="airp">
							<p>通知人</p>
							<p style="margin: auto 0;">
								<img src="../img/add.png"  class="imgs">
							</p>
						</div>
						<ul id="notify">
						
						</ul>
					</li>
					<li class="fillList">
						<div class="explain">
							<p>差旅说明</p>
							<p class="module">
								<span class="insurance-type">起飞或到达时间不合适</span>
								<span>
								<img src="../img/you.png"/ class="chooseR">
							</span>
							</p>
						</div>
						<div class="explain" id="pay_Method">
							<p>
								结算方式
							</p>
							<p class="module">
								<span class="Ppayment-type">${payMethod.get(0).name}</span>
								<span>
								<img src="../img/you.png"/ class="chooseR">
							</span>
							</p>
						</div>
					</li>


					<li class="fillList">
						<div id="approve" class="order_">
							<p class="app_title">审批政策</p>
							<ul class="approve_list">

								<%--<li>
									<div class="radioBtn">
										<img src="../hotelimg/wei.png">
									</div>
									<p>产品部适用的审批政策</p>
									<img src="../hotelimg/tips.png" class="tips">
								</li>--%>
							</ul>
						</div>

					</li>

					<c:if test="${Verify=='1'}">


					<li class="fillList auditmen" >
						<div class="airp">
							<p>订单审核人</p>
							<p style="margin: auto 0;">
								<img src="../img/add.png" / class="imgs">
							</p>
						</div>
						<ul id="reviewer">
						
						</ul>
					</li>
					</c:if>
				</ul>
				
			</section>
		<input type="hidden" id="seatsLeft" value="${seatsLeft}" />
		<input type="hidden" id="InsruanceIsNull" value="${InsruanceIsNull}" />
		<input type="hidden" id="isShowInsruance" value="${isShowInsruance}" />
		<input type="hidden" id="isShowAdmin" value="${isShowAdmin}" />
		<input type="hidden" id="costbelongStatus" value="${costbelongStatus}" />
		<input type="hidden" id="projectMust" value="${projectMust}" />	
		<!-- ----确认表单提交 -------->	
		<form id="submitForm" action="<%=basePath%>intlflight/bookIntlOrder.act" method="post">
			
			<input type="hidden" name="traveltype" value="${traveltype }">
			<input type="hidden" name="cangwei" value="${cangweidesc }">
			<input type="hidden" name="mapkey" value="${mapkey1 }">
			<input type="hidden" id="paymethod" name="payMethod" value="" />
			<span></span>
		</form> 
			
		<!-- 新增常旅客常旅客,常旅客修改方法 -->
		<form action="" id="subForm">
		<input name="intlType" id="intlType"  value="" type="hidden"/>
		<input name="passengerId" id="passengerId"  value="" type="hidden"/>
		</form>
			
			<footer class="footer">
				<p class="price"><span class=" total">总价：</span><span>¥</span><span class="totalP">0</span></p>
				<p class="true">确认</p>
			</footer>
		</div>
	</body>
	<script type="text/javascript" src="../libs/jquery.min.js"></script>
	<script type="text/javascript" src="../libs/Myconfirm.js"></script>
	<script type="text/javascript">
		/*---页面加载头部---------*/

		$(".headerS").load("header.html", function(data) {

			$('header div img').eq(0).on('click', function() {

                $('.pages').fadeOut();
                $('.wrap').fadeIn();
				/*window.history.back(-1);*/
			});
            $('#closelist').on('click', function() {

                $('.checkpages').fadeOut();
                $('.wrap').fadeIn();
            });


		});


		$('.login img').on('click', function(){
		 
		 window.frames['updateIframe'].location = "<%=basePath%>ppp/addd.act?intlflag=intl";
		 $('.updatePasengerPage').fadeIn();
		 $('.wrap').fadeOut();
		 $('.pages').fadeOut();		
		     	
		return false;
		})
		
		
		
		
		//重新查询常旅客列表
		function newPassenger(){
			var htmlobj = $.ajax({url: "<%=basePath%>ppp/passAjax.act",async: false});
			var dataobj = htmlobj.responseText;
			eval("var json="+dataobj)
			var json=json.data;
			$('.tabContent ul').eq(1).find('li').remove();
			
			
		  
			for(var o in json){  
			var personcard="";
			switch(json[o].ids[0].type)
			{
			case 1:
			  personcard="身份证";
			  break;
			case 2:
			  personcard="护照";
			  break;
			case 3:
			  personcard="海员证";
			  break;
			case 4:
			  personcard="回乡证";
			  break;
			case 5:
			  personcard="军官证";
			  break;
			case 6:
			  personcard="港澳通行证";
			  break;
			case 7:
			  personcard="台胞证";
			  break;
			case 99:
			  personcard="其他";
			  break;
			default:
			 var personcard="";
			}
			
			var str=`<li ids="passener" class="passengerup">
						
								<div>
									<p class="userName" class="editor"><span class="passener-name">`+json[o].name+`</span></p>
								
									<p>
									<span class="name">手机号</span>
									<span class="passener-phone">`+json[o].mobile+`</span>
								</p>
								<p>
								<input type="hidden" name="passengerId" class="passengerId" value=`+json[o].id+`>
								<span class="TypeName"><span class="name"><span>`+personcard+`</span>
								 </span></span>
								<span class="passener-type">`+json[o].ids[0].num+`</span> 
								</p>
						
									
								<input type="hidden" class="passener-uid" value=`+json[o].id+`>
								</div>
							
								
						
						<div class="right-table"  onclick="addPassenger('2',`+json[o].id+`)">
							<a href="javascript:;" ><img src="../img/bianji.png" /></a>
						</div>
					</li>`
			$('.tabContent ul').eq(1).append(str);
			
			
			
		}
			
			$('.updatePasengerPage').fadeOut();
		    $('.pages').fadeIn();
		}
		
		var InsruanceIsNull=$('#InsruanceIsNull').val(); 
		var isShowInsruance=$('#isShowInsruance').val();
		var isShowAdmin=$('#isShowAdmin').val();
		var costbelongStatu=$('#costbelongStatu').val();				
		var projectMust=$('#projectMust').val();

		/*------------保险开关的点击事件-----*/
		var flag;
		var insurancePrice;
		
		$('body').on('click', '#circleBtn', function() {
				if(!this.flag) {
					$(this).attr('class', 'close');
					$(this).parents('.circle').css('background', '#CCCCCC')
					$(this).parents('.circle').attr('inc','open');
				} else {
					$(this).attr('class', 'circleBtn');
					$(this).parents('.circle').css('background', '#00AFEC')
					$(this).parents('.circle').attr('inc','close');
				}
				this.flag = !this.flag;
			
				$(this).parents('.detail').attr("flag", this.flag);
				
				insurancePrice=this.flag?parseFloat(-20):parseFloat(20);
				
				
				$('.footer .totalP').html(parseFloat($('.footer .totalP').html())+insurancePrice);
			
			})
			

		
		
		
			/*---------点击确认的事件------*/
		$('.true').on('click', function() {
		
		$('#paymethod').val($('.Ppayment-type').html());
	
		var insuranceNum=0;
		var str;
		var i=0;
		var arr=[];
		var pay = $('.Ppayment-type').html();
		$('.paytype').val(pay);

		$("#passener li").each(function(){
		
		/* passener-phone
			
			public int type;			//类型，1：企业员工，2：常旅客
			public long id;				//员工id
			public int idtype;			//证件类型
			public long orgid;			//费用归属
			public String project;		//项目号
			public String mileage;		//里程号
			public int insuranceNum;	//保险份数
			public long insuranceId;	//保险id
			Ppayment-type
		 */
			
			var orgid = $(this).find('#passenerorgid').html();
			var phone = $(this).find('.passenerphone').html();
			var costBelong=$(this).find('#costBelong').val();
			var insuranceid=$(this).find('#insuranceIDD').val();
			var project=$(this).find('#project').val();
			var j=0;
			$(this).find('#passenerinfo input').each(function (){
				arr[j]=$(this).val();
				j++;
			})
			/* alert("乘机人："+arr); */
			str =`<input type="hidden" name="rp[`+i+`].type" value=`+arr[0]+`>` ;
			$('#submitForm').find('span').append(str);
		
			str =`<input type="hidden" name="rp[`+i+`].id" value=`+arr[1]+`>` ;
            $('#submitForm').find('span').append(str);
			
			str =`<input type="hidden" name="rp[`+i+`].idtype" value=`+arr[2]+`>` ;
            $('#submitForm').find('span').append(str);
			
			str =`<input type="hidden" name="rp[`+i+`].orgid" value=`+costBelong+`>` ;
            $('#submitForm').find('span').append(str);
			
			//保险id
			if(isShowInsruance!='0'){
			str1=`<input type="hidden" id="pay" name="rp[`+i+`].insuranceId" value=`+insuranceid+`>`;
		    $('#submitForm').find('span').append(str1);
			}
			if(insuranceid!='-1'&&isShowInsruance!='0'){
			insuranceNum=1;
			}
			//保险数量
			str1=`<input type="hidden" id="insuranceNum" name="rp[`+i+`].insuranceNum" value=`+insuranceNum+`>`;
		    $('#submitForm').find('span').append(str1);
			
			//项目号
			str =`<input type="hidden" name="rp[`+i+`].project" value=`+project+`>` ;
			$('#submitForm').find('span').append(str);
			/* if($(this).find('.circle').css('background')=='#00AFEC'){
			insuranceNum+=1;
			} */
			
			
			
			/* var phone = $(obj).find('.passener-phone').html();
			var type = $(obj).find('.TypeName .name').html();
		    var numberType = $(obj).find('.passener-type').html(); */
		    insuranceNum=0;
		    i++;
		})
		
		/* //获取保险数量
		$('#insuranceNum').val(insuranceNum); */
		
		var str1;
		var ii=0;
		var arr1=[];
		$(".fillList .details").each(function(){
			
			var jj=0;
			$(this).find('#reviewerInfo input').each(function (){
				arr1[jj]=$(this).val();
				jj++;
			})


			/* var phone = $(this).find('.passenerphone').html(); */
			str1 =`<input type="hidden" name="ri[`+ii+`].toType" value=`+arr1[0]+`>` ;
			$('#submitForm').find('span').append(str1);
			
			str1 =`<input type="hidden" name="ri[`+ii+`].toId" value=`+arr1[1]+`>` ;
			$('#submitForm').find('span').append(str1);
			
			str1 =`<input type="hidden" name="ri[`+ii+`].toName" value=`+arr1[2]+`>` ;
			$('#submitForm').find('span').append(str1);
			
			str1 =`<input type="hidden" name="ri[`+ii+`].toTel" value=`+arr1[3]+`>` ;
			$('#submitForm').find('span').append(str1);
			
			
		    
		    ii++;
		})
		
			addPeople();
		
		   /*  $('#submitForm').submit(); */
		
		
		
		});



		

		function addPeople() {
		
			if(projectMust=='1'){
					var bgclist=[];
					var i=0;
					$('.detail').each(function(){
				
					var c=$(this).find('.isprojectmust').val();
					
					
					if(c==''||c==null){
					bgclist[i]=$(this).find('.passenername').html();
					i++;
					}
					})
					if(bgclist!=null&&bgclist.length>0){
					mess="请填写乘机人:"+bgclist[0]+" 的项目号！";
					showMessage(mess);
					$('#submitForm').find('span').find('input').remove();
					return false;
					}
				}
			if(isShowAdmin=='2'){
					var adlist=[];
					var j=0;
					$('.detail').each(function(){
					var ad=$(this).find('#adminid').val();
					
					if(ad==''||ad==null||ad=='-1'){
					adlist[j]=$(this).find('.passenername').html();
					j++;
					}
					})
					if(adlist!=null&&adlist.length>0){
					mess="请填写乘机人:"+adlist[0]+" 的管理员！";
					showMessage(mess);
					$('#submitForm').find('span').find('input').remove();
					return false;
					}
				}
			
			
			/*---判断乘机人是否添加--------*/
			if($('#passener').find('li').length == 0) {
				mess = "请添加乘机人";
				showMessage(mess);
                $('#submitForm').find('span').find('input').remove();
				return false;
			}
			/*----通知人是否有添加-------*/
			/* if($('#notify').find('li').length == 0) {
				mess = "请添加通知人";
				showMessage(mess);
				return false;
			} */



			/*----订单审核人是否有添加  预付款和公司月结必填--------*/

			var payname = $('#pay_Method').find('span:eq(0)').html();
			if(payname.indexOf('预付款支付')>-1 ||payname.indexOf('公司月结')>-1){
                if($('#reviewer').find('li').length == 0) {
                    mess = "请添加订单审核人";
                    showMessage(mess);
                    $('#submitForm').find('span').find('input').remove();
                    return false;
                }
			}else{
			$('.auditmen').find('#reviewer').remove();//清除审核人信息
			}

			
			if(isShowInsruance=='2'){
			if(InsruanceIsNull=='-1'){
			showMessage("该企业未维护保险 请先维护保险！");
			$('#submitForm').find('span').find('input').remove();
			return false;
			}
			
			}
			//获取航班号
			var  airline =$('.topType').find('span:eq(1)').html();
			//获取出发日期
			var flightDate=$("#StartTime").val();
			//获取舱位
			var cangwei=$('#cangwei1').val();
			//获取乘机人证件号
			var passengerStrIds="";
			$('#passener').find('li').find('.number-type').each(function () {
                passengerStrIds+=$(this).html()+',';
            })

			/*alert(airline+"===="+flightDate+"==="+cangwei+"==="+passengerStrIds);*/
            var flag=true;
            $.ajax({
                url:'${applicationScope.ctx}/intlflight/passengerCheck.act',
                dataType:'json',
                type:'POST',
                async:false,
                data:{'passengerStrIds':passengerStrIds,'flightDate':flightDate,'cangwei':cangwei,'airline':airline},
                success:function(data){

						if(data!=null){
                        $(this).MyConfirm({
                            content: "该证件号:"+data+"已经预定了本次航班的该舱位！"
                        });
                        $('#submitForm').find('span').find('input').remove();
                        flag=false;
                    }
                }
            });

            if(!flag){
                return false;
            }
			
			/* var bgclist=[];
			var i=0;
			$('.detail').each(function(){
			var c=$(this).find('.circle').attr('inc');
			//var bgc=RGBToHex(rgb);
			if(c=='open'){
			bgclist[i]=$(this).find('.passenername').html();
			i++;
			}
			})
			if(bgclist!=null&&bgclist.length>0){
			mess="请勾选乘机人:"+bgclist[0]+" 的保险！";
			showMessage(mess);
			return false;
			}		 */	
			
			
			
			
			
			$('#submitForm').submit();
		/* 	var dates = $('.price .totalP').html();
			window.location.href = "${applicationScope.ctx}/intlflight/pay.act?name=" + dates; */
		};
		
		function RGBToHex(rgb){ 
		   var regexp = /[0-9]{0,3}/g;  
		   var re = rgb.match(regexp);//利用正则表达式去掉多余的部分，将rgb中的数字提取
		   
		   var hexColor = "#"; var hex = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'];  
		   for (var i = 0; i < re.length; i++) {
		        var r = null, c = re[i], l = c; 
		        var hexAr = [];
		        while (c > 16){  
		              r = c % 16;  
		              c = (c / 16) >> 0; 
		              hexAr.push(hex[r]);  
		         } hexAr.push(hex[c]);
		         if(l < 16&&l != ""){        
		             hexAr.push(0)
		         }
		       hexColor += hexAr.reverse().join(''); 
		    }  
		   //alert(hexColor)  
		   return hexColor;  
		} 

		function showMessage(mess) {
			$(this).MyConfirm({
				content: mess
			});
		}

		/*该退政策提示框的显示和隐藏*/

		$('.btna').on('click', function() {
				var base = "${applicationScope.ctx}/intlflight/getIntlTicketRule.act";
				var mapKey = document.getElementById("mapkey1").value;
				var cangwei = document.getElementById("cangwei1").value;
				/* alert("mapKey="+mapKey+ "^cangwei="+cangwei); */
				param="&mapKey="+mapKey+ "&cangwei="+cangwei;
				$.ajax({
				    url:base/* +"/intlflight/getIntlTicketRule.act" */,      //请求的url地址
				    dataType:"text",  					 //返回格式为json
				    async:true,						    //请求是否异步，默认为异步，这也是ajax重要特性
				    data:"param="+param, 			    //参数值
				    type:"post",   						//请求方式
				    success:function(req){
				        /* alert(req); */
				        var tuigais = req.split("|");
				        var tuipiao;
				        var wuji;
				        var gaiqian;
				        if(tuigais[0] == 1){
					        tuipiao = tuigais[1];
					        gaiqian = tuigais[2];
					        wuji = tuigais[3];
				        }
				        $("#tuipiao").html(tuipiao);
				        $("#gaiqian").html(gaiqian);
				        $("#wuji").html(wuji);
				    },
				});
				        tipeShow('.bun');
		});
		$('.closedBtn img').on('click', function() {
			$('.bounced').fadeOut();
		});
		/*航班详情提示框的显示和隐藏*/
		$('.chooseR').eq(0).on('click', function() {
			tipeShow('.bounceds');
		});

		function tipeShow(objShow) {
			$(objShow).fadeIn();
		}

		/*----差旅说明和结算方式的默认样式-----*/
		function defalutStyles(objStyles) {
			$(objStyles).eq(0).css('color', '#00AFEC');
			$(objStyles).find('.selected').eq(0).show();
		};
		defalutStyles('.payment .payment-choose p'); //结算方式
		defalutStyles('.instructe .payment-choose p'); //差旅说明
		defalutStyles('.department .payment-choose p'); //费用归属
		defalutStyles('.insurances .payment-choose p'); //保险
		/*------结算方式种类的效果实现----------*/
		var nums = ($('.payment .payment-choose p').length + 1) * 80 / 20 + 'rem';
		$('.module').eq(1).on('click', function() {
			layerShow('.payment', '.payment .payment-con', nums)
		});
		$('.payment .payment-choose p').on('click', function() {
			var sid =$(this).find('input').val();
			if(sid=='2'||sid=='3'){
			    $('.auditmen').hide();//隐藏审核人

			}else {
                $('.auditmen').show();
			}

			chooseContent($(this), '.payment');
			
			$('.Ppayment-type').html($(this).find('span').eq(0).html());
		});

		/*----------差旅说明类型的效果实现----------*/
		var num = ($('.instructe .payment-choose p').length + 1) * 80 / 20 + 'rem';

		$('.module').eq(0).on('click', function() {
			layerShow('.instructe', '.instructe .payment-con', num);
		});
		$('.instructe .payment-choose p').on('click', function() {
			chooseContent($(this), '.instructe');
			$('.insurance-type').html($(this).find('span').eq(0).html());
		});

		/*------------结算方式、差旅说明、费用归属的遮罩层及内容展开的共同样式-------*/
		function layerShow(obj, attrObj, numLength) {
			var height = $("body").height();
			$('.bounced').height(height);
			$(obj).fadeIn();
			$(attrObj).animate({
				height: numLength
			}, 1000)
		};
		/*--------点击除了div以外所有的空白区域，div隐藏---------*/

		$('.instructe').bind('click', function(e) {
			hideLayer(e, 'test', '#test');
		});

		$('.payment').bind('click', function(e) {
			hideLayer(e, 'tested', '#tested');
		});
		$('.department').bind('click', function(e) {
			hideLayer(e, 'tests', '#tests');
		});
		/* $('.insurance').bind('click', function(e) {
			hideLayer(e, 'tests', '#tests');
		}); */
		/*-----删除除了div之外任何区域，遮罩层隐藏----------*/
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

		/*---------添加乘机人----------*/
		$('body').on('click', '#imgEd', function() {
			$('.addH .title').html('添加乘机人')
			$('.pages').fadeIn();
			$('.wrap').fadeOut();
			
			var id = "passener";
			$('.tabContent  li').attr("ids", id);
		});
		

		/*--------------添加通知人------*/
		$('.imgs').eq(0).on('click', function() {
			$('.addH .title').html('添加通知人')
			$('.pages').fadeIn();
			$('.wrap').fadeOut();
			var id = "notify";
			$('.tabContent  li').attr("ids", id);
		});
		/*----------添加订单审核人---------*/
		$('.imgs').eq(1).on('click', function() {
            if($("#passener li").length==0){
                mess = "请先选择乘机人!";
                showMessage(mess);
                return false;
            }
            $("#AuditorList").find("li").remove();
		    /*获取审核人列表*/
            $.ajax({
                url:'${applicationScope.ctx}/intlflight/getAuditorList.act',
                dataType:'json',
                type:'POST',
                async:false,
                data:{},
                success:function(json){

				var str="";
                var costbelongids=[];
                var i=0;
				$(".detailEdtior").find(".costbelong").each(function(){
				    costbelongids[i]=$(this).val();
				    i++;
				})

                    for(var o in json){
				      var flag=true;
				    for(var j=0;j<costbelongids.length;j++){
				        /*alert(json[o].auditDepartmentIds+"__"+costbelongids[j]+"____"+json[o].auditDepartmentIds.indexOf(costbelongids[j]));*/
				        if(json[o].auditDepartmentIds.indexOf(costbelongids[j])<0){

							flag=false;
							continue;
						}

					}

					if(flag){
				        str+=`<li id="" class="left-passener">
									<div class="left-passener" ins="ins" >
										<div class="middle-table">
											<input type="hidden" name="passengerId" class="passengerId" value=`+json[o].id+`>

											<p><span class="passener-name"> `+(!json[o].name?"":json[o].name)+`</span></p>
											<p>
												<span class="name">手机号</span>
												<span class="passener-phone">`+(!json[o].mobile?"":json[o].mobile) +`</span>
											</p>
											<p>
												<span class="name">邮箱</span>
												<span class="passener-email">`+(!json[o].email?"":json[o].email) +`</span>
											</p>
											<p>
												<span class="name">部门名称</span>
												<span class="passener-orgname">`+(!json[o].departmentName?"":json[o].departmentName)+`</span>
											</p>
										</div>
									</div>
								</li>`;
					}


                    }
					if(str==""){
                        var ts="";
                        var ii=0;
                        $(".detailEdtior").find(".branch").find('input:eq(1)').each(function(){
                            ts+=$(this).val()+" ";
                            ii++;
                        })

                        var mess="";
						if(i==0){
                            mess="没有审核人信息 请维护审核人!";
						}else if(i==1){
						    mess="没有能审核 "+ts+"部门的审核人,无法提交订单,请维护审核人！";
						}else {
                            mess="没有能同时审核 "+ts+"部门的审核人,无法提交订单,请分开预订！";
						}

                        showMessage(mess);

					}else {
                        $("#AuditorList").append(str);
                        $('.addH .title').html('添加审核人')
                        $('.checkpages').fadeIn();
                        $('.wrap').fadeOut();
                        var id = "reviewer";
                        $('.tabContent li').attr("ids", id);
					}


                }
            });


		});
		/*---------通知人和订单审核人的字符串拼接------*/
		function str(obj) {
			var	id= $(obj).parents('li').attr("ids");
			var peopleType = id=='reviewer'?'审核人':'通知人';
            var name = "";
            var pid="";
            var phone ="";
            var type = "";
            var numberType = "";
            var email="";
            var orgname="";
            var str="";
		    if(id=='reviewer'){

                 name = $(obj).find('.passener-name').html();
                 pid=$(obj).find('.passengerId').val();
                 phone = $(obj).find('.passener-phone').html();
                 email = $(obj).find('.passener-email').html();
                 orgname = $(obj).find('.passener-orgname').html();

                 str = `<li class="detail" >
					<div class="details">
							<div class="detailEdtior">
								<p>
										<span class="name people">`+peopleType+`</span>
									<span class="passenername">`+name+`</span>
								</p>
								<p>
									<img src="../img/jian.png" / class="deleteEle" token="token">
								</p>
							</div>
							<div>
								<span class="name">手机号</span>
								<span class="passenerphone">`+phone+`</span>
							</div>
							<div>
								<span class="name">邮箱</span>
								<span>`+email+`</span>
							</div>
							<div>
								<span class="name">部门名称</span>
								<span>`+orgname+`</span>
							</div>
							<div id="reviewerInfo">
								<input type="hidden" value=`+peopleType+`>
								<input type="hidden" class="passenerid" value=`+pid+`>
								<input type="hidden" value=`+name+`>
								<input type="hidden" value=`+phone+`>
							</div>
							</div>
						</li>`;

					$('#reviewer').find('li').remove();
					$('#reviewer').append(str);
			}else{
                 name = $(obj).find('.passener-name').html();
                 pid=$(obj).find('.passengerId').val();
                 phone = $(obj).find('.passener-phone').html();
                 type = $(obj).find('.TypeName .name').html();
                 numberType = $(obj).find('.passener-type').html();

                 str = `<li class="detail" >
					<div class="details">
							<div class="detailEdtior">
								<p>
										<span class="name people">`+peopleType+`</span>
									<span class="passenername">`+name+`</span>
								</p>
								<p>
									<img src="../img/jian.png" / class="deleteEle" token="token">
								</p>
							</div>
							<div>
								<span class="name">手机号</span>
								<span class="passenerphone">`+phone+`</span>
							</div>
							<div>
								<span class="name">`+type+`</span>
								<span>`+numberType+`</span>
							</div>
							<div id="reviewerInfo">
								<input type="hidden" value=`+peopleType+`>
								<input type="hidden" class="passenerid" value=`+pid+`>
								<input type="hidden" value=`+name+`>
								<input type="hidden" value=`+phone+`>
							</div>
							</div>
						</li>`;
                $('#notify').append(str);
			}



						
			/* $('#' + id).empty().append(str); */
		};

		var airPeople=[];
		var costIds=[];

			/*------乘机人的字符串拼接-------*/
		function str1(obj) {

            $('#reviewer').find('li').remove();/*清空审核人*/

			/* passener-phone
			
			public int type;			//类型，1：企业员工，2：常旅客
			public long id;				//员工id
			public int idtype;			//证件类型
			public long orgid;			//费用归属
			public String project;		//项目号
			public String mileage;		//里程号
			public int insuranceNum;	//保险份数
			public long insuranceId;	//保险id
			
			
			0 <input type="hidden" value="${staff.id}"  --乘机人id--
		    1 <input type="hidden" value="${staff.name}" %--乘机人名字--
		    2 <input type="hidden" value="${staff.mobile}"  %--乘机人手机号--
		    3 <input type="hidden" value="${staff.ids[0].type}"> %--乘机人证件号类型--
		    4 <input type="hidden" value="${staff.ids[0].num}"  %--乘机人证件号--
		    5 <input type="hidden" value="${staff.type}">  乘机人类型--
		
		 	*/
			var arr=[];
			var i=0;
			$(obj).find('.Ppassener-name input').each(function (){
				arr[i]=$(this).val();
				i++;
			})

			/* var htmlobj = $.ajax({url: "${applicationScope.ctx}/intlflight/getListEmployee.act",data:"userId="+arr[0],async: false});
			var dataobj = htmlobj.responseText;
		   	eval("var json=" + dataobj);
		   	var span="";
		    for(var o in json){  
			span+=`<p>
							<span>
							    <input type="hidden" name="" value=`+json[o].departmentId+`>
							    <input type="hidden" name="" value=`+json[o].departmentName+`>
							   `+json[o].departmentName+`
							</span>
							<img src="img/choose.png" / class="selected">
				</p>`
		       
		    }   */
			
			var name = $(obj).find('.passener-name').html();
			var phone = $(obj).find('.passener-phone').html();
			var type = $(obj).find('.TypeName .name').html();
		    var numberType = $(obj).find('.passener-type').html();
		 	
		 	/* var sttr=`<div class="payment-choose">
					`+span+`
					
				</div>
			`; */
			
		 	/* var bm=$('.department .payment-choose').find('span:eq(0)').html(); */
		 	var bm=$('.department .payment-choose').find('span').first().html();
		 	var costbelong=$('.department .payment-choose').find('span').first().find('input').val();
		 	var insurance=$('.insurances .payment-choose').find('span').first().find('input').next().val();
		 	var insuranceid=$('.insurances .payment-choose').find('span').first().find('input').val();
		 	if(!insurance){
			insurance=0;
			}
			var str = `<li class="detail">
							<div class="detailEdtior">
								<p>
									<span class="name people">乘机人</span>
									<span class="passenername">`+name+`</span>
								</p>
								<p>
									<img src="../img/jian.png" / class="deleteEle">
								</p>
							</div>
							<div>
								<span class="name">`+type+`</span>
								<span class="number-type">`+numberType+`</span> 
							</div>
							<div>
								<span class="name">手机号</span>
								<span class="passenerphone">`+phone+`</span>
								<input type="hidden" id="passenerorgid" value="">
							</div>
							
							<c:if test="${costbelongStatus!='0'}">
								<div class="detailEdtior">
								<p>
									<span class="name">费用归属</span>
									<span class="branch">`+bm+`</span>
									<input type="hidden" id="costBelong" class="costBelong" value=`+costbelong+`>
									
								</p>
								<p>
									<img src="../img/you.png" / class="chooseR deeeee">
								</p>
							</div>
							</c:if>
							
						
							<c:if test="${isShowAdmin!='0'}">
								<div class="detailEdtior adminstrator">
								<p>
									<span class="name">管理员</span>
									<span class="branch">请选择</span>
									<input type="hidden" id="adminid" value="-1"> 
								</p>
								<p>
									<img src="../img/you.png" / class="chooseR adminchoose">
								</p>
							</div>
							</c:if>
							
							<div>
								<span class="name">项目号</span>
								<span>
								<input type="text" name="" id="project" class="isprojectmust" value="" / placeholder="选填">
							</span>
							</div>
							<c:if test="${isShowInsruance!='0'}">
								<div class="detailEdtior">
									<p>
										<span class="name">保险费</span>
										<span class="insurance">¥<span class="price">`+insurance+`</span>/份</span>
										<input type="hidden" id="insuranceIDD" value=`+insuranceid+`>
										<input type="hidden"  value=`+insurance+`>
									</p>
									<p>
										<img src="../img/you.png" / class="chooseR binsurance">
									</p>
								</div>
							
							
							</c:if>
							<div id="passenerinfo">
								<input type="hidden" class="passenertype" value=`+arr[6]+`>
								<input type="hidden" class="passenerid" value=`+arr[0]+`>
								<input type="hidden" class="passeneridtype" value=`+arr[3]+`>
								<input type="hidden" class="passenerorgid" value=`+arr[1]+`>
							</div>
						</li>`;
		
			$('#passener').append(str);
			if(!projectMust||projectMust==null){
			$('.isprojectmust').attr('placeholder','选填');
			}else{
			$('.isprojectmust').attr('placeholder',projectMust=='1'?'必填':'选填');
			}
			totalPrice(insurance);

			//乘机人id
			$(".detail").find("#passenerinfo").each(function(){
				var ids=$(this).children(".passenerid").val();
				//airPeople.push(ids);
			});

			airPeople.push(arr[0]);
			//费用归属id
			costIds.push(costbelong);
			console.log(airPeople);
			console.log(costIds);
			showApprovalsPolicy(airPeople.join(","),costIds.join(","));

		};

		function showApprovalsPolicy(air,cost){
			if(air == "" || cost ==""){
				$("#passener").find(".passenerid").each(function(){
					if(air!=""){
						air +=",";
					}
					air += $(this).val();
				});
				$("#passener").find(".costBelong").each(function(){
					if(cost !=""){
						cost+=",";
					}
					cost += $(this).val();
				})
			}
			$(".approve_list").empty();
			//常规、超规   前面传过来的
			var approveType="0";
			if(air !=""){
				var result=$.ajax({url: "<%=basePath%>/intl/showApprovals.act",data:{peopleIds:air,costIds:cost,approveType:approveType},async: false});
				var responseText = result.responseText;
				eval("var json="+responseText);
			}
			console.log(json);
			var str="";
			if(json){
				for(var i in json){
					str += `<li class="onePolicy">
							<div class="radioBtn">
										<img src="../hotelimg/wei.png">
										<input type="hidden" class="approvePolicyId" value="`+json[i].id+`" />
									</div>
									<p>`+json[i].name+`</p>
									<img src="../hotelimg/tips.png" class="tips">
								</li>`;
				}
				$(".approve_list").append(str);
			}
		}

		$("body").on("click",".onePolicy .tips",function(){
			var appid=$(this).siblings(".radioBtn").find(".approvePolicyId").val();
			$.ajax({
				url:'${applicationScope.ctx}/intl/getDetail.act',
				dataType:'json',
				type:'POST',
				async:false,
				data:{'appId':appid},
				success:function(data){
					console.log(data);
					if(data!=null){
						$(".shenpi").empty();
						var onestep="";
						onestep=`<p>
			            <span class="aw">审批要求：</span>
			         <span> `+(data.approvals[0].type == '1'?"任一通过":"全部通过")+`</span>
		                  </p>
		                  <p>
		<span class="aw">审批类别：</span>
		<span>`+(data.type == 1?"同级审批":"逐级审批") +`</span></p>`;

						for(var i in data.approvals){
							onestep +=`<p><span class="aw">`+data.approvals[i].flowId+`级审批人：</span><span>`;
							for(var k in data.approvals[i].auditors){
								onestep +=data.approvals[i].auditors[k].name+`&nbsp`;
							}
							onestep +=`</span></p>`;
						}
					}

//					var appIdInput=`<input type="hidden" name="approvalId" value="`+appid+`" />`;
//					$('#submitForm').find('span').append(appIdInput);

                    $(".shenpi").append(onestep);
					$(".wrap").attr("overflow","hidden");
					$(".shen").fadeIn();
				}
			});

		})
		$(".approve_list").on("click",".onePolicy",function(){
			$(this).find("img").eq(0).attr("src","../hotelimg/choose.png");
			$("#submitAppid").remove();
			var appId = $(this).find(".approvePolicyId").val();
			var appIdInput=`<input type="hidden" name="approvalId" value="`+appId+`" id="submitAppid"/>`;
			$('#submitForm').find('span').append(appIdInput);
			$(this).siblings().find("img").eq(0).attr("src","../hotelimg/wei.png");
		});
		/* <div class="detailEdtior">
								<p>
									<span class="name">保险费</span>
									<span class="insurance">¥<span class="price">${baoxian.price}</span>/份</span>
								</p>
								<div class="circle">
									<img src="../img/circle.png" class="circleBtn" id="circleBtn">
								</div>
							</div> */
		/* ------总价实时计算 -------*/
	
		function totalPrice(insurance){
		/* var length=	$('#passener').find('li').length; */
		var price =$('.travelB').find('.fare').html();
		
		/* 	var totalprice=price*length; */
					
		
		/* numLength = $('.insurance .price').length; */
		
		$('.footer .totalP').html( parseFloat($('.footer .totalP').html())+parseFloat(price)+ (isShowInsruance!='0'?parseFloat(insurance):0) );		
		}
		$('').click(function(e){
			e.stopPropagation();
		})
		
		 /* ------------企业员工&常旅客-------------- */
		function addPassenger(skipType,pid){
		
		    $("#intlType").val(skipType);
		    console.log($("#intlType").val(skipType))
		    /* skipType 1:新增常旅客 2:编辑常旅客 */
		    if(skipType=='1'){
		    	 $("#subForm").attr("action","<%=basePath%>/ppp/addd.act");
		    }
		    if(skipType=='2'){
		    	<%--  $('.updatePasengerPage .Iframe').attr('src',"<%=basePath%>ppp/updatee.act?passengerId="+pid); --%>
		    	 window.frames['updateIframe'].location = "<%=basePath%>ppp/updatee.act?passengerId="+pid+"&intlflag=intl";
		    	// window.frames['updateIframe'].location.reload();
		    	//var foot= $("#intlupdate",window.frames['updateIframe'].document);
		    	 
		    	/* var test = document.getElementById('#IframeID').contentWindow.document.getElementById('#intlupdate'); */
		    	/* var foot=    $('#intlupdate').html();  */
		    	//alert(foot.html())
		    	//window.frames['updateIframe'].document.window.test();
		    	 $('.updatePasengerPage').fadeIn();
				 $('.wrap').fadeOut();
				 $('.pages').fadeOut();
		     	
		    }
		   /*  $(document).LoadingShow(); */
		    /* setTimeout(function(){
		    	$("#subForm").submit();
		    },100) */
			    
		}
		
		/* 从子页面获取根据常旅客id获取修改后的对象并放到常旅客列表 */
		function hideS(passid){
		
			var dataobj=getPassenger(passid);
			eval("var json=" + dataobj);
			
			
			$('.passengerup').each(function(){
			var passengerId=$(this).find('.passengerId').val();
			if(passengerId==passid){
			$(this).find('.passener-name').html(json.name);
			$(this).find('.passener-phone').html(json.mobilephone);
			$(this).find('.passener-type').html(json.idnumber);
			
			var personcard="";
			switch(json.idtype)
			{
			case "1":
			  personcard="身份证";
			  break;
			case "2":
			  personcard="护照";
			  break;
			case "3":
			  personcard="海员证";
			  break;
			case "4":
			  personcard="回乡证";
			  break;
			case "5":
			  personcard="军官证";
			  break;
			case "6":
			  personcard="港澳通行证";
			  break;
			case "7":
			  personcard="台胞证";
			  break;
			case "99":
			  personcard="其他";
			  break;
			default:
			 var personcard="";
			}
			$(this).find('.TypeName .name').html(personcard);
			
			
			
			
			}
			})
			
		   
		
			
		
		 $('.updatePasengerPage').fadeOut();
		 $('.pages').fadeIn();
		}

		function getPassenger(passid){
			var htmlobj = $.ajax({url: "<%=basePath%>/intlflight/getPassengerById.act",data:{pid:passid},async: false});
			var dataobj = htmlobj.responseText;
			return dataobj;
		}
		
		/*-------添加通知人、审核人、乘机人弹框的点击事件----*/

		var numLength;
		var fare = parseFloat($('.fare').html());

/*		$(".tabContent  li div[ins='ins']").on('click', function() {

			addContent(this);
			/!* numLength = $('.insurance .price').length;
			$('.footer .totalP').html(numLength * 20 + fare); *!/
		});*/
        $(document).on('click','.tabContent  li div[ins="ins"]',function(e){

            addContent(this);
        });



		function addContent(obj) {
			var flag='0';
			var id = $(obj).parents('li').attr("ids");

			var passengerId=$(obj).find('.passengerId').val();
			
			if('passener' == id) {
			var seatsLeft=$('#seatsLeft').val();
			
			if($("#passener li").length==seatsLeft){
				mess = "最多添加"+seatsLeft+"位乘机人";
				showMessage(mess);
				$('.pages').fadeOut();
				$('.wrap').fadeIn();
				return;
			}
			if($("#passener li").length==9){
				mess = "最多添加九位乘机人";
				showMessage(mess);
				$('.pages').fadeOut();
				$('.wrap').fadeIn();
				return;
			}
			
			$("#passener li").each(function(){
			var pid=$(this).find('#passenerinfo .passenerid').val();
			
			if(passengerId==pid){
				flag='1';
			}
			})
			
			
			if(flag=='0'){
				str1(obj);
			}
				
			}else{
			if($("#notify li").length==9){
				mess = "最多添加九位通知人";
				showMessage(mess);
				$('.pages').fadeOut();
				$('.wrap').fadeIn();
				return;
			}
			
			
			if("reviewer"==id){

				str(obj);
			}else{
			$("#notify li").each(function(){
			var pid=$(this).find('#reviewerInfo .passenerid').val();
			
			if(passengerId==pid){
				flag='1';
			}
			})
			if(flag=='0'){
				str(obj);
			}
			}
			
			
				
			}
             $('.checkpages').fadeOut();
			 $('.pages').fadeOut(); 
			 $('.wrap').fadeIn();
		};
		
		$(function(){
			statusControl();
		})
		
		/* -----费用归属 管理员 保险状态显示控制 ------*/
		function statusControl(){
		
			/* STATE(状态，0-不显示|1-选填|2-必填，默认值为1) */
			if(InsruanceIsNull=='-1'&&isShowInsruance=='2'){
			showMessage("该企业未维护保险 请先维护保险！");
			
			}
			
			
			
		}
		
		
		/*----------点击编辑----------*/
		$('.edit').on('click', function(event) {
			event.stopPropagation(); //阻止事件冒泡即可
			window.location.href = "flyer.html";
		});
		
		/*-------删除列表内容-----*/
		$('body').on('click', '.deleteEle', function() {
			var token=$(this).attr("token");
			$(this).parents('.detail').remove();
			if(token=='token'){
			return;
			}
			numLength = $('.insurance .price').length;
			/* $('.footer .totalP').html(numLength * 20 + fare); */
			var baoxian=0;
			var flag= $(this).parents('.detail').attr("flag");
            var inprice=$(this).parents('.detail').find('.insurance .price').html();
			
			if(!flag||flag=='false'){

			 if(!inprice){
                 baoxian=0;
			 }else {
                 baoxian=-inprice;
			 }


			}
			
			$('.footer .totalP').html( parseFloat($('.footer .totalP').html())-$('.travelB').find('.fare').html()+parseFloat(baoxian));

			var delid=$(this).parents(".detail").find(".passenerid").val();
			var costid=$(this).parents(".detail").find(".costBelong").val();
			removeValue(airPeople,delid);
			removeValue(costIds,costid);
			showApprovalsPolicy("","");
		});

		function removeValue(arr,val){
			if(arr.length>0){
				for(var i=0;i<arr.length;i++){
					if(arr[i] ==val){
						arr.splice(i,1);
					}
				}
			}
		}


		/*-------费用归属的效果样式---------*/
		var objindex;
		var numberd = ($('.department .payment-choose p').length + 1) * 80 / 20 + 'rem';
		$('body').on('click', '.deeeee', function() {
			
			
			
			layerShow('.department', '.department .payment-con', numberd);
			objindex = $(this);
			
		});
		var insurancnObj;
		/* ----保险选择的效果样式 ----*/
		var nnumberd = ($('.insurances .payment-choose p').length + 1) * 80 / 20 + 'rem';
		$('body').on('click', '.binsurance', function() {
			
			layerShow('.insurances', '.insurances .payment-con', nnumberd);

			insurancnObj = $(this);
		});
		
		var adminObj;
		/* ----管理员选择的效果样式 ----*/
			$('body').on('click', '.adminchoose', function() {
			//根据费用归属id查询管理员列表 选填和必填的情况
 			if(isShowAdmin!='0'){
			var costid=$(this).parents('.adminstrator').parent().find('#costBelong').val();
			
			if(!costid){
			return false;
			}else{
			
			var htmlobj = $.ajax({url: "<%=basePath%>/flight/admin.act",data:{orgId:costid},async: false});
			var dataobj = htmlobj.responseText;
		    var dataobjs=dataobj.split(',');//管理员id^管理员姓名
		    $('.adminlist .payment-choose').find('p').remove();
		    if(dataobjs!=''){
		    for(var i=0;i<dataobjs.length;i++){
		    	var tdata=dataobjs[i].split("^");
		    	
				var sttr=`<p>
							<span>
							    <input type="hidden" name="adminId" value=`+tdata[1]+`>
							    <input type="hidden" name="adminName" value=`+tdata[0]+`>
							   `+ tdata[0]+`
							</span>
							<img src="../img/choose.png" / class="selected">
						</p>`;
		    	
		        $('.adminlist .payment-choose').append(sttr);
		    }
			}else{
			
				mess = "该部门未维护管理员,请先维护或者选择其它部门！";
				showMessage(mess);
				return false;
			}
			
			}
			
			}
			var adnumberd = ($('.adminlist .payment-choose p').length + 1) * 80 / 20 + 'rem';
			
			layerShow('.adminlist', '.adminlist .payment-con', adnumberd);
			adminObj = $(this);
			
		});
		
		
		
		$('.adminlist').on('click' ,'.payment-choose p', function() {
			
			chooseContent($(this), '.adminlist')
			// adminObj.parents('.adminstrator').prev().find('#costBelong').val($(this).find('input').eq(0).val());
			//adminObj.parents('.adminstrator').prev().find('.branch').html($(this).find('input').eq(1).val()); 
			adminObj.parents('.adminstrator').find('.branch').html($(this).find('input').eq(1).val());
			adminObj.parents('.adminstrator').find('#adminid').val($(this).find('input').eq(0).val());
			
		});
		
		
		$('.insurances .payment-choose p').on('click', function() {

			chooseContent($(this), '.insurances')
			var oglinsurancePrice= insurancnObj.parents('.detailEdtior').find('input').next().val();
			var insurancePrice=$(this).find('span').eq(0).find('input').next().val();

			insurancnObj.parents('.detailEdtior').find(".insurance").html($(this).find('span').eq(0).html());
			insurancnObj.parents('.detailEdtior').find("#insuranceIDD").val($(this).find('span').eq(0).find('input').val());
			if(!insurancePrice){
			insurancePrice=0;
			}
			if(!oglinsurancePrice){
			oglinsurancePrice=0;
			}
			
			$('.footer .totalP').html( parseFloat($('.footer .totalP').html())+ (isShowInsruance!='0'?parseFloat(insurancePrice-oglinsurancePrice):0) );		
			
		});

		//选择   费用归属的  点击事件
		$('.department .payment-choose p').on('click', function() {
			
			chooseContent($(this), '.department')
			objindex.parents('.detailEdtior').find(".branch").html($(this).find('span').eq(0).html());
			objindex.parents('.detailEdtior').find("#costBelong").val($(this).find('span').eq(0).find('input').val());
			
			/* 加载管理员列表连动事件 */				
			//根据费用归属id查询管理员列表 选填和必填的情况
 			if(isShowAdmin!='0'){
			var costid=$(this).find('span').eq(0).find('input').val();
			if(!costid){
			return false;
			}else{
			
			var htmlobj = $.ajax({url: "<%=basePath%>/flight/admin.act",data:{orgId:costid},async: false});
			var dataobj = htmlobj.responseText;
		    var dataobjs=dataobj.split(',');//管理员id^管理员姓名
		    $('.adminlist .payment-choose').find('p').remove();
		    if(dataobjs!=''){
		    var tdata=dataobjs[0].split("^");
		    objindex.parents().next('.detailEdtior').find('span').eq(1).html(tdata[0]);
		    objindex.parents().next('.detailEdtior').find('#adminid').val(tdata[1]);
			}else{
			 objindex.parents().next('.detailEdtior').find('span').eq(1).html('');
		     objindex.parents().next('.detailEdtior').find('#adminid').val("-1");
			}
			
			}
			}
			/* ------------------------ */
			
		});
		
		
		
		/*---------选择内容的事件函数-----*/
		function chooseContent(obj, ele) {
			obj.css('color', '#00AFEC').siblings().css('color', '');
			obj.find('.selected').show().parents(obj).siblings().find('.selected').hide();
			$(ele).delay(500).fadeOut(0);
		}
		/*------选项卡的效果--------*/
		$('.flyer').on('click', function() {
			var index = $(this).index();
			console.log(index)
			if(index == 1){
				$(this).parents('body').find('.addH .login img').css('display','block');
			}else{
				$(this).parents('body').find('.addH .login img').css('display','none');
			}
			$(this).addClass('highlight').siblings().removeClass('highlight');
			$(this).parents('.tabTitle').siblings().find('ul').eq(index).addClass('show').siblings().removeClass('show');
		});

		var url = window.location.href; //获取当前页面的url
		var len = url.length; //获取url的长度值
		var a = url.indexOf("?"); //获取第一次出现？的位置下标
		var b = url.substr(a + 1, len); //截取问号之后的内容
		var c = b.split("&"); //从指定的地方将字符串分割成字符串数组	
		var arr = new Array(); //新建一个数组
		for(var i = 0; i < c.length; i++) {
			var d = c[i].split("=")[1]; //从=处将字符串分割成字符串数组,并选择第2个元素			
		}
		$('.money .fare').html(c[0].split("=")[1]);
	</script>
	<script type="text/javascript">
	/* var users = [];
	for(var i=0;i<5;i++){
		var user = {};
		user["name"] = "www" + i;
		user["age"] = 18 + i;
		users.push(user);
	}
	var str = $.param(users);
	
	alert(str); */
</script>
</html>
