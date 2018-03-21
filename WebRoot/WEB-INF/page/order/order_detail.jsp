<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="../public/tag.jsp" %>
<!DOCTYPE html>
<html>

	<head>
		<base href="<%=basePath%>">
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no" />
		<title>订单详情</title>
		<link rel="stylesheet" type="text/css" href="css/allOrder.css" />
	
	</head>

	<body>
	
		<div class="bounced cancleBtn">

			<div class="tips">
				<p class="change" style="font-size: 1.5rem;">取消订单</p>
				<div class="contentd" style="padding-top: 0;">
					<p>一旦取消将无法恢复</p>
					<textarea name="a" placeholder="取消订单的理由"id="cancel_reason"></textarea>
					<div class="btnA">
						<a class="true-tips1" href="javascript:;" style="color:#01AFEC;">确定</a>
						<a class="closed-tips" href="javascript:;" style="color: #333333;">取消</a>
					</div>


				</div>
			</div>
		</div>
	<div id="container">
	
	
	
		<div class="bounced resoultlBtn">

			<div class="tips">
				<p class="change" style="font-size: 1.5rem;">拒绝订单</p>
				<div class="contentd" style="padding-top: 0;">
					<p>一旦拒绝将无法恢复</p>
					<textarea name="a" placeholder="取消订单的理由" id="refuse_reason"></textarea>
					<div class="btnA">
						<a class="true-tips2" href="javascript:;" style="color:#01AFEC;">确定</a>
						<a class="closed-tips" href="javascript:;" style="color: #333333;">取消</a>
					</div>


		</div>
		</div>
		</div>
		<section>
			<c:if test="${result.resultObj!=null && result.resultObj.status==0 && result.resultObj.data!=null}">
				<c:set var="data" value="${result.resultObj.data }"/>
				<header>
					<div>
						<img id="back" src="img/header.gif" />
					</div>
					<p id="oid" oid="${data.orderId }" class="zhixiang">订单号 ${data.orderNo }</p>
				</header>
				<div class="one-way">
					<div class="way-title">
						<div class="tit-left">
							<span class="way-type">
								<c:if test="${data.tripType==1 }">单程</c:if>
								<c:if test="${data.tripType==2 }">往返</c:if>
								<c:if test="${data.tripType==3 }">联程</c:if>
							</span>
						</div>
						<h5 class="audit">
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
						</h5>
					</div>
					<div class="way-content">
						<c:if test="${data.tripType==1 }">
							<div class="flight" style="padding-bottom:0.85rem;">
								<div class="left">
									<div class="start">
										<p class="tank">${mt:fmtByStyle(data.flights[0].depDateTime,'MM-dd')}</p>
										<h5>
											${mt:fmtByStyle(data.flights[0].depDateTime,'HH:mm')}
										</h5>
									</div>
									<div class="start">
										<p class="tank">${mt:fmtByStyle(data.flights[0].arrDateTime,'MM-dd')}</p>
										<h5>
											${mt:fmtByStyle(data.flights[0].arrDateTime,'HH:mm')}
										</h5>
									</div>
								</div>
								<img src="img/luxian.png" class="line" />
								<div class="right">
									<div class="r-top">
										<h5>${applicationScope.cityMap.get(data.flights[0].depAirport) }</h5>
										<h5>${applicationScope.airportNameMap.get(data.flights[0].depAirport) }${data.flights[0].depPointAT }</h5>
									</div>
									<div class="r-middle">
										<span>
											<img src="img/type.png" />
											${applicationScope.ac2NameMap.get(data.flights[0].airline)} ${data.flights[0].flightNo}
										</span>
										<span class="tank big">${applicationScope.planeTypeMap.get(data.flights[0].planeType)}</span>
										<c:set var="cabinkey" value="${data.flights[0].airline}_${data.flights[0].cabinCode}" />
										<span class="tank">${applicationScope.cabinNameMap.get(cabinKey)}</span>
									</div>
									<div class="r-bottom">
										<h5>${applicationScope.cityMap.get(data.flights[0].arrAirport) }</h5>
										<h5>${applicationScope.airportNameMap.get(data.flights[0].arrAirport) }${data.flights[0].arrPointAT }</h5>
									</div>
								</div>
							</div>
						</c:if>
						<c:if test="${data.tripType==2 || data.tripType==3 }">
							<div class="flight">
								<div class="left">
									<div class="start">
										<p class="tank">${mt:fmtByStyle(data.flights[0].depDateTime,'MM-dd')}</p>
										<h5>
											${mt:fmtByStyle(data.flights[0].depDateTime,'HH:mm')}
										</h5>
									</div>
									<div class="start">
										<p class="tank">${mt:fmtByStyle(data.flights[0].arrDateTime,'MM-dd')}</p>
										<h5>
											${mt:fmtByStyle(data.flights[0].arrDateTime,'HH:mm')}
										</h5>
									</div>
								</div>
								<img src="img/luxian.png" class="line" />
								<div class="right">
									<div class="r-top">
										<h5>${applicationScope.cityMap.get(data.flights[0].depAirport) }</h5>
										<h5>${applicationScope.airportNameMap.get(data.flights[0].depAirport) }${data.flights[0].depPointAT }</h5>
									</div>
									<div class="r-middle">
										<span>
											<img src="img/type.png" />
											${applicationScope.ac2NameMap.get(data.flights[0].airline)} ${data.flights[0].flightNo}
										</span>
										<span class="tank big">${applicationScope.planeTypeMap.get(data.flights[0].planeType)}</span>
										<c:set var="cabinkey" value="${data.flights[0].airline}_${data.flights[0].cabinCode}" />
										<span class="tank">${applicationScope.cabinNameMap.get(cabinKey)}</span>
									</div>
									<div class="r-bottom">
										<h5>${applicationScope.cityMap.get(data.flights[0].arrAirport) }</h5>
										<h5>${applicationScope.airportNameMap.get(data.flights[0].arrAirport) }${data.flights[0].arrPointAT }</h5>
									</div>
								</div>
							</div>
							<div class="folding">
								<!-- <div class="transit">
									<img src="img/zhuan.png" />
									<div>
										<h5>杭州</h5>
										<h5>停留7h30m</h5>
									</div>
								</div> -->
								<div class="flight">
									<div class="left">
										<div class="start">
											<p class="tank">${mt:fmtByStyle(data.flights[1].depDateTime,'MM-dd')}</p>
											<h5>
												${mt:fmtByStyle(data.flights[1].depDateTime,'HH:mm')}
											</h5>
										</div>
										<div class="start">
											<p class="tank">${mt:fmtByStyle(data.flights[1].arrDateTime,'MM-dd')}</p>
											<h5>
												${mt:fmtByStyle(data.flights[1].arrDateTime,'HH:mm')}
											</h5>
										</div>
									</div>
									<img src="img/luxian.png" class="line" />
									<div class="right">
										<div class="r-top">
											<h5>${applicationScope.cityMap.get(data.flights[1].depAirport) }</h5>
											<h5>${applicationScope.airportNameMap.get(data.flights[1].depAirport) }${data.flights[1].depPointAT }</h5>
										</div>
										<div class="r-middle">
											<span>
												<img src="img/type.png" />
												${applicationScope.ac2NameMap.get(data.flights[1].airline)} ${data.flights[1].flightNo}
											</span>
											<span class="tank big">${applicationScope.planeTypeMap.get(data.flights[1].planeType)}</span>
											<c:set var="cabinkey" value="${data.flights[1].airline}_${data.flights[1].cabinCode}" />
											<span class="tank">${applicationScope.cabinNameMap.get(cabinKey)}</span>
										</div>
										<div class="r-bottom">
											<h5>${applicationScope.cityMap.get(data.flights[1].arrAirport) }</h5>
											<h5>${applicationScope.airportNameMap.get(data.flights[1].arrAirport) }${data.flights[1].arrPointAT }</h5>
										</div>
									</div>
								</div>
							</div>
							<div style="text-align: center;">
								<a href="javascript:;" class="closed">展开 </a>
								<img src="img/xiala.png"  class="img" />
							</div>
						</c:if>
					</div>
				</div>
				<div class="message">
					<c:if test="${data.passengers!=null && data.passengers.size()>0 }">
						<c:forEach items="${data.passengers }" var="pg" varStatus="vs">
							<div class="list ${vs.count lt data.passengers.size()?'bor':'' }">
								<span class="greay">乘机人${vs.count }</span>
								<span class="name">${pg.name }</span>
								<span class="phone" style="margin-left: 1rem;">${pg.mobile }</span>
							</div>
						</c:forEach>
					</c:if>
				</div>
				<div class="cost">
					<p class="greay"><span>应付金额</span><span class="money">￥${data.amount}</span></p>
					<c:set value="0" var="totalIn" />
					<c:forEach items="${data.passengers }" var="p">
						<c:set var="totalIn" value="${p.insurancePrice + totalIn }" />
					</c:forEach>
					<%-- <c:if test="${data.tripType==1 }">
						<p class="greay"><span>机票</span><span class="money">￥${data.tickets[0].price }*${data.passengers.size()}</span></p>
						<p class="greay"><span>税费</span><span class="money">￥${data.tickets[0].airportTax + data.tickets[0].fuelSurTax}*${data.passengers.size()}</span></p>
						<p class="greay"><span>保险总价</span><span class="money">￥${totalIn }</span></p>
						<p class="greay"><span>服务费</span><span class="money">￥${data.tickets[0].serviceFee }</span></p>
					</c:if>
					<c:if test="${data.tripType==2 || data.tripType==3 }">
						<p class="greay"><span>机票</span><span class="money">￥${data.tickets[0].price + data.tickets[1].price }*${data.passengers.size()}</span></p>
						<p class="greay"><span>税费</span><span class="money">￥${data.tickets[0].airportTax + data.tickets[0].fuelSurTax + data.tickets[1].airportTax + data.tickets[1].fuelSurTax}*${data.passengers.size()}</span></p>
						<p class="greay"><span>保险总价</span><span class="money">￥${totalIn }</span></p>
						<p class="greay"><span>服务费</span><span class="money">￥${data.tickets[0].serviceFee }</span></p>
					</c:if> --%>
					<c:set value="${data.passengers.size() }" var="pNum" />
					<c:set value="0" var="tPrice" />
					<c:set value="0" var="fPrice" />
					<c:forEach items="${data.tickets }" var="t">
						<c:set var="tPrice" value="${t.price + tPrice }" />
						<c:set var="fPrice" value="${(t.airportTax==null?0:t.airportTax) + (t.fuelSurTax==null?0:t.fuelSurTax) + fPrice }" />
					</c:forEach>
					<p class="greay"><span>机票</span><span class="money">￥${tPrice/pNum }*${pNum}</span></p>
					<p class="greay"><span>税费</span><span class="money">￥${fPrice/pNum }*${pNum}</span></p>
					<p class="greay"><span>保险总价</span><span class="money">￥${totalIn }</span></p>
					<p class="greay"><span>服务费</span><span class="money">￥${data.tickets[0].serviceFee }</span></p>
				</div>
				<div class="message">
					<div class="list bor">
						<span class="greay">出行方式</span>
						<span class="name1">因${data.businessType==0?'公':'私' }出行</span>
					</div>
					<div class="list">
						<span class="greay">支付方式</span>
						<span class="name1">
							<%-- <c:if test="${data.payMethod==1 }"></c:if>
							<c:if test="${data.payMethod==2 }"></c:if>
							<c:if test="${data.payMethod==3 }"></c:if>
							<c:if test="${data.payMethod==4 }"></c:if> --%>
							${applicationScope.paytypeMap.get(data.payMethod) }
						</span>
					</div>
				</div>
				<div class="message">
					<c:forEach items="${data.contacts }" var="cont" varStatus="vs">
						<div class="list">
							<span class="greay">通知人${vs.count}</span>
							<span class="name">${cont.name }</span>
							<span style="margin-left: 1rem;">${cont.mobile }</span>
						</div>
					</c:forEach>
				</div>
				<div class="message">
					<c:if test="${data.auditor != null }">
						<div class="list">
							<span class="greay">审核人</span>
							<span class="name">${data.auditor.name }</span>
							<span style="margin-left: 0.72rem;">${data.auditor.email }</span>
						</div>
					</c:if>
				</div>
				<div class="message">
					<div class="list bor">
						<span class="greay">温馨提示</span>
						<span class="name1" style="display:inline-block;line-height:2rem;margin-left:0">如需办理改期和退票，请致电凯撒商旅24小时客服服务热线<a href="tel:4006-020-365">4006-020-365</a>办理。</span>
					</div>
				</div>
			</c:if>
			<!-- <header>
				<div>
					<img src="img/header.gif" />
				</div>
				<p id="oid" oid="62373" class="zhixiang">订单号20170224A308</p>
			</header>
			<div class="one-way">
				<div class="way-title">
					<div class="tit-left">
						<span class="way-type">单程</span>
					</div>
					<h5 class="audit">待审核</h5>
				</div>
				<div class="way-content">
					<div class="flight">
						<div class="left">
							<div class="start">
								<p class="tank">02-24</p>
								<h5>
									09:20
								</h5>
							</div>
							<div class="start">
								<p class="tank">02-24</p>
								<h5>
									11:45
								</h5>
							</div>
						</div>
						<img src="img/luxian.png" class="line" />
						<div class="right">
							<div class="r-top">
								<h5>北京</h5>
								<h5>首都机场T1</h5>
							</div>
							<div class="r-middle">
								<span>
									<img src="img/type.png" />
									海航HU7619
								</span>
								<span class="tank big">波音787(大)</span>
								<span class="tank">经济舱</span>
							</div>
							<div class="r-bottom">
								<h5>杭州</h5>
								<h5>萧山机场T1</h5>
							</div>
						</div>
					</div>
					<div class="folding">
						<div class="transit">
							<img src="img/zhuan.png" />
							<div>
								<h5>杭州</h5>
								<h5>停留7h30m</h5>
							</div>
						</div>
						<div class="flight">
							<div class="left">
								<div class="start">
									<p class="tank">02-24</p>
									<h5>
									09:20
								</h5>
								</div>
								<div class="start">
									<p class="tank">02-24</p>
									<h5>
									11:45
								</h5>
								</div>
							</div>
							<img src="img/luxian.png" class="line" />
							<div class="right">
								<div class="r-top">
									<h5>北京</h5>
									<h5>首都机场T1</h5>
								</div>
								<div class="r-middle">
									<span>
									<img src="img/type.png" />
									海航HU7619
								</span>
									<span class="tank big">波音787(大)</span>
									<span class="tank">经济舱</span>
								</div>
								<div class="r-bottom">
									<h5>杭州</h5>
									<h5>萧山机场T1</h5>
								</div>
							</div>
						</div>
					</div>
					<div style="text-align: center;">
						<a href="javascript:;" class="closed">隐藏 </a>
						<img src="img/xiala.png"  class="img" />
					</div>
				</div>
			</div>
			<div class="message">
				<div class="list bor">
					<span class="greay">乘机人1</span>
					<span class="name">田晓</span>
					<span class="phone" style="margin-left: 1rem;">185123456789</span>
				</div>
				<div class="list">
					<span class="greay">乘机人2</span>
					<span class="name">田晓雨</span>
					<span class="phone" style="margin-left: 0.72rem;">185123456789</span>
				</div>
			</div>
			<div class="cost">
				<p class="greay">应付金额<span class="money">￥1458</span></p>
				<p class="greay">机票<span>￥659*2</span></p>
				<p class="greay">税费<span>￥50*2</span></p>
				<p class="greay">保险<span>￥20*2</span></p>
			</div>
			<div class="message">
				<div class="list bor">
					<span class="greay">出行方式</span>
					<span class="name1">因公出行</span>
				</div>
				<div class="list">
					<span class="greay">支付方式</span>
					<span class="name1">预付款支付</span>
				</div>
			</div>
			<div class="message">
				<div class="list">
					<span class="greay">通知人</span>
					<span class="name">田晓</span>
					<span style="margin-left: 1rem;">185123456789</span>
				</div>
			</div>
			<div class="message">
				<div class="list">
					<span class="greay">审核人</span>
					<span class="name">李秀东</span>
					<span style="margin-left: 0.72rem;">185123456789</span>
				</div>
			</div> -->
			
		<!-- 	<footer class="footer-pay">
				<div class="btn" id="alipayid">去支付</div>
			</footer> -->
			
			<footer class="footer-transit">
				
				<c:if test="${data.status==2 }">
					<div class="btn" id="alipayid" style="background:#00afec" onclick="window.location.href='flight/toPay.act?orderId=${result.resultObj.data.orderId}';">去支付</div>
				</c:if>
			
				<div class="audit-list" style="display: none;">审核通过</div>
			
				<div class="refused-list" style="display: none;">拒绝通过</div>
		
				<div class="cancel-list" style="display: none;">取消订单</div>
			</footer>
		</section>
		</div>
	</body>
	<script type="text/javascript" src="libs/jquery.min.js"></script>
	<script type="text/javascript" src="js/MyConfirm.js"></script>
	<link rel="stylesheet" type="text/css" href="css/MyConfirm.css">	
	<script type="text/javascript" src="libs/gublic.js"></script>
	<script type="text/javascript" src="js/page/order/order_detail.js"></script>
	<script type="text/javascript">

		(function() {
			var eleBtn = document.querySelector('.closed'),
				eleMore = document.querySelector('.folding');
			eleImg = document.querySelector('.img');
			var display = false;
			if(eleBtn){
				eleBtn.onclick = function() {
					display = !display;
					eleMore.style.height = display ? "12.5rem" : "0px";
					eleBtn.innerHTML = display ? "收起" : "展开";
					eleImg.src = display ? "img/dakai.png" : "img/xiala.png";
					return false;
				};
			}
		})();
		
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
				
					if ($("#cancel_reason").val() == "" || $("#cancel_reason").val() == "请填写取消理由") {		     
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
			             $("#alipayid").hide();
			             
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
		/* 	
			$(this).MyConfirm({
            content: "确定要保存内容吗",
            hasCancelBtn:true
       		 },jjcallback);
			$('.bounced').slideUp(); */
			jjcallback(true);
			
			});
			

				
			
		
			 
			 	
			 
			 function jjcallback(isConfirm){
			  orderId=$("#oid").attr("oid") ;
			
 
					if(isConfirm){
					
					if ($("#refuse_reason").val() == "" || $("#refuse_reason").val() == "请填写拒绝理由") {
						
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
</html>