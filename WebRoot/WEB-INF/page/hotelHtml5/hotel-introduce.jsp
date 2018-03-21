<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="../public/tag.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>">
		<meta charset="UTF-8">
		<title>酒店介绍页</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/hotelHtml5/index.css" />
	</head>
	<body>
		<section>
			<header class="header" id="header">
				<div>
					<img src="<%=basePath%>/hotelimg/header.png" / class="you" onclick="history.back();">
				</div>
				<p>酒店信息</p>
			</header>
			<div id="hotel_g">
				<div class="state_title">
					酒店概况
				</div>
				<p>
					${result.briefIntroduction }
				</p>
			</div>
			<div id="facility">
				<div class="policy_t state_title">酒店设施与服务</div>
				<ul class="facility_list">
				<li>
					<p class="facility">网络连接</p>
					<div class="kind">
						<c:forEach items="${result.facilittyItems }" var="facilitty">
							<c:if test="${facilitty.itemType=='1' }">
								<p><img src="<%=basePath%>/hotelimg/dot.png"/></p><p class="caissa_wuxian">${facilitty.itemName }</p>
							</c:if>
						</c:forEach>
					</div></li>

				<li>
					<p class="facility">停车场</p>
					<div class="kind">
						<c:forEach items="${result.facilittyItems }" var="facilitty">
							<c:if test="${facilitty.itemType=='2' }">
								<img src="<%=basePath%>/hotelimg/dot.png"/><p class="caissa_shoufeitingche">${facilitty.itemName }</p>
							</c:if>
						</c:forEach>
					</div></li>
				<li>
					<p class="facility">综合设施</p>
					<div class="kind">
						<c:forEach items="${result.facilittyItems }" var="facilitty">
							<c:if test="${facilitty.itemType=='3' }">
								<c:choose>
									<c:when
										test="${fn:containsIgnoreCase(facilitty.itemName, 'wifi') }">
										<div><img src="<%=basePath%>/hotelimg/wifi.png"/><p class="caissa_wuxian">${facilitty.itemName }</p></div>
									</c:when>
									<c:when
										test="${fn:containsIgnoreCase(facilitty.itemName, '传真/复印') }">
										<div><img src="<%=basePath%>/hotelimg/dot.png"/><p class="icon02xxxx">${facilitty.itemName }</p></div>
									</c:when>
									<c:when
										test="${fn:containsIgnoreCase(facilitty.itemName, '中餐') }">
										<div><img src="<%=basePath%>/hotelimg/xc.png"/><p class="caissa_zhongcan">${facilitty.itemName }</p></div>
									</c:when>
									<c:when
										test="${fn:containsIgnoreCase(facilitty.itemName, '西餐') }">
										<div><img src="<%=basePath%>/hotelimg/xc1.png"/><p class="caissa_xican">${facilitty.itemName }</p></div>
									</c:when>
									<c:when
										test="${fn:containsIgnoreCase(facilitty.itemName, '停车场') }">
										<div><img src="<%=basePath%>/hotelimg/tcc.png"/><p class="caissa_shoufeitingche">${facilitty.itemName }</p></div>
									</c:when>
									<c:when
										test="${fn:containsIgnoreCase(facilitty.itemName, '无烟楼层') }">
										<div><img src="<%=basePath%>/hotelimg/jz.png"/><p class="caissa_wuyan">${facilitty.itemName }</p></div>
									</c:when>
									<c:when
										test="${fn:containsIgnoreCase(facilitty.itemName, '游泳池') }">
										<div><img src="<%=basePath%>/hotelimg/hb.png"/><p class="caissa_yongchi">${facilitty.itemName }</p></div>
									</c:when>
									<c:when
										test="${fn:containsIgnoreCase(facilitty.itemName, '健身') }">
										<div><img src="<%=basePath%>/hotelimg/sport.png"/><p class="caissa_jianshen">${facilitty.itemName }</p></div>
									</c:when>
									<c:when
										test="${fn:containsIgnoreCase(facilitty.itemName, '洗衣服务') }">
										<div><img src="<%=basePath%>/hotelimg/gx.png"/><p class="caissa_xiyi">${facilitty.itemName }</p></div>
									</c:when>
									<c:when
										test="${fn:containsIgnoreCase(facilitty.itemName, '行李寄存') }">
										<div><img src="<%=basePath%>/hotelimg/ys.png"/><p class="caissa_jicun">${facilitty.itemName }</p></div>
									</c:when>
									<c:when
										test="${fn:containsIgnoreCase(facilitty.itemName, '24小时前台服务') }">
										<div><img src="<%=basePath%>/hotelimg/service.png"/><p class="caissa_qiantai">${facilitty.itemName }</p></div>
									</c:when>
									<c:when
										test="${fn:containsIgnoreCase(facilitty.itemName, '叫醒服务') }">
										<div><img src="<%=basePath%>/hotelimg/nz.png"/><p class="caissa_jiaoxing">${facilitty.itemName }</p></div>
									</c:when>
									<c:when
										test="${fn:containsIgnoreCase(facilitty.itemName, '接机服务') }">
										<div><img src="<%=basePath%>/hotelimg/car.png"/><p class="caissa_jieji">${facilitty.itemName }</p></div>
									</c:when>
									<c:when
										test="${fn:containsIgnoreCase(facilitty.itemName, '接站服务') }">
										<div><img src="<%=basePath%>/hotelimg/tcq.png"/><p class="caissa_jiezhan">${facilitty.itemName }</p></div>
									</c:when>
									<c:when
										test="${fn:containsIgnoreCase(facilitty.itemName, '会议厅') }">
										<div><img src="<%=basePath%>/hotelimg/sw.png"/><p class="caissa_huiyiting">${facilitty.itemName }</p></div>
									</c:when>
									<c:when
										test="${fn:containsIgnoreCase(facilitty.itemName, 'SPA') }">
										<div><img src="<%=basePath%>/hotelimg/yd.png"/><p class="caissa_spa">${facilitty.itemName }</p></div>
									</c:when>
									<c:otherwise>
										<div><img src="<%=basePath%>/hotelimg/dot.png"/><p class="caissa_tongyong">${facilitty.itemName }</p></div>
									</c:otherwise>
								</c:choose>
							</c:if>
						</c:forEach>
					</div></li>
				<li>
					<p class="facility">活动设施</p>
					<div class="kind">
						<c:forEach items="${result.facilittyItems }" var="facilitty">
							<c:if test="${facilitty.itemType=='4' }">
								<c:choose>
									<c:when
										test="${fn:containsIgnoreCase(facilitty.itemName, 'wifi') }">
										<div><img src="<%=basePath%>/hotelimg/wifi.png"/><p class="caissa_wuxian">${facilitty.itemName }</p></div>
									</c:when>
									<c:when
										test="${fn:containsIgnoreCase(facilitty.itemName, '传真/复印') }">
										<div><img src="<%=basePath%>/hotelimg/dot.png"/><p class="icon02xxxx">${facilitty.itemName }</p></div>
									</c:when>
									<c:when
										test="${fn:containsIgnoreCase(facilitty.itemName, '中餐') }">
										<div><img src="<%=basePath%>/hotelimg/xc.png"/><p class="caissa_zhongcan">${facilitty.itemName }</p></div>
									</c:when>
									<c:when
										test="${fn:containsIgnoreCase(facilitty.itemName, '西餐') }">
										<div><img src="<%=basePath%>/hotelimg/xc1.png"/><p class="caissa_xican">${facilitty.itemName }</p></div>
									</c:when>
									<c:when
										test="${fn:containsIgnoreCase(facilitty.itemName, '停车场') }">
										<div><img src="<%=basePath%>/hotelimg/tcc.png"/><p class="caissa_shoufeitingche">${facilitty.itemName }</p></div>
									</c:when>
									<c:when
										test="${fn:containsIgnoreCase(facilitty.itemName, '无烟楼层') }">
										<div><img src="<%=basePath%>/hotelimg/jz.png"/><p class="caissa_wuyan">${facilitty.itemName }</p></div>
									</c:when>
									<c:when
										test="${fn:containsIgnoreCase(facilitty.itemName, '游泳池') }">
										<div><img src="<%=basePath%>/hotelimg/hb.png"/><p class="caissa_yongchi">${facilitty.itemName }</p></div>
									</c:when>
									<c:when
										test="${fn:containsIgnoreCase(facilitty.itemName, '健身') }">
										<div><img src="<%=basePath%>/hotelimg/sport.png"/><p class="caissa_jianshen">${facilitty.itemName }</p></div>
									</c:when>
									<c:when
										test="${fn:containsIgnoreCase(facilitty.itemName, '洗衣服务') }">
										<div><img src="<%=basePath%>/hotelimg/gx.png"/><p class="caissa_xiyi">${facilitty.itemName }</p></div>
									</c:when>
									<c:when
										test="${fn:containsIgnoreCase(facilitty.itemName, '行李寄存') }">
										<div><img src="<%=basePath%>/hotelimg/ys.png"/><p class="caissa_jicun">${facilitty.itemName }</p></div>
									</c:when>
									<c:when
										test="${fn:containsIgnoreCase(facilitty.itemName, '24小时前台服务') }">
										<div><img src="<%=basePath%>/hotelimg/service.png"/><p class="caissa_qiantai">${facilitty.itemName }</p></div>
									</c:when>
									<c:when
										test="${fn:containsIgnoreCase(facilitty.itemName, '叫醒服务') }">
										<div><img src="<%=basePath%>/hotelimg/nz.png"/><p class="caissa_jiaoxing">${facilitty.itemName }</p></div>
									</c:when>
									<c:when
										test="${fn:containsIgnoreCase(facilitty.itemName, '接机服务') }">
										<div><img src="<%=basePath%>/hotelimg/car.png"/><p class="caissa_jieji">${facilitty.itemName }</p></div>
									</c:when>
									<c:when
										test="${fn:containsIgnoreCase(facilitty.itemName, '接站服务') }">
										<div><img src="<%=basePath%>/hotelimg/tcq.png"/><p class="caissa_jiezhan">${facilitty.itemName }</p></div>
									</c:when>
									<c:when
										test="${fn:containsIgnoreCase(facilitty.itemName, '会议厅') }">
										<div><img src="<%=basePath%>/hotelimg/sw.png"/><p class="caissa_huiyiting">${facilitty.itemName }</p></div>
									</c:when>
									<c:when
										test="${fn:containsIgnoreCase(facilitty.itemName, 'SPA') }">
										<div><img src="<%=basePath%>/hotelimg/yd.png"/><p class="caissa_spa">${facilitty.itemName }</p></div>
									</c:when>
									<c:otherwise>
										<div><img src="<%=basePath%>/hotelimg/dot.png"/><p class="caissa_tongyong">${facilitty.itemName }</p></div>
									</c:otherwise>
								</c:choose>
							</c:if>
						</c:forEach>

					</div></li>
					<li>
					<p class="facility">服务项目</p>
					<div class="kind">
						<c:forEach items="${result.facilittyItems }" var="facilitty">
							<c:if test="${facilitty.itemType=='5' }">
								<div><img src="<%=basePath%>/hotelimg/dot.png"/><p class="caissa_tongyong">${facilitty.itemName }</p></div>
							</c:if>
						</c:forEach>

					</div>
					
					</li>


			</ul>
			</div>
			<div id="policy">
				<div class="policy_t state_title">酒店政策</div>
				<ul class="policy_list">
					<li>
						<p class="issue">入离时间</p>
						<p class="reply">入住时间：${result.pCheckInTime }以后&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;离店时间：${result.pCheckOutTime }以前</p>
					</li>
					<li>
						<p class="issue">儿童政策</p>
						<p class="reply">${result.childAddBed }
						</p>
					</li>
					<li>
						<p class="issue">膳食安排</p>
						<p class="reply">${result.diet }</p>
					</li>
					<li>
						<p class="issue">宠物</p>
						<p class="reply">
							<c:if test="${result.withPet== '1'}">可以</c:if>
							<c:if test="${result.withPet== '2'}">不可以</c:if>
							<c:if test="${result.withPet== ''}">无</c:if>
						</p>
					</li>
					<li>
						<p class="issue">接受信用卡</p>
						<p class="reply">
							<c:if test="${fn:substring(result.cardType,0,1) == '1'}">万事达（Master）</c:if>
							<c:if test="${fn:substring(result.cardType,1,2) == '1'}">威士（VISA）</c:if>
							<c:if test="${fn:substring(result.cardType,2,3) == '1'}">运通（AMEX）</c:if>
							<c:if test="${fn:substring(result.cardType,3,4) == '1'}">大来（Diners Club）</c:if>
							<c:if test="${fn:substring(result.cardType,4,5) == '1'}">JCB</c:if>
							<c:if test="${fn:substring(result.cardType,5,6) == '1'}">国内发行银联卡</c:if>
							</p>
					</li>
				</ul>
			</div>
		</section>
	</body>
	
	<script type="text/javascript" src="js/hotelHtml5/jquery-1.8.2.min.js"></script>
</html>
