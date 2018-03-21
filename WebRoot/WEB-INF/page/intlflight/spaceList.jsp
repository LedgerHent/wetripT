<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../public/tag.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no" />
		<title>直飞舱位列表</title>
		<link rel="stylesheet" type="text/css" href="../css/intlAllOrder.css" />
		<%-- <link rel="stylesheet" type="text/css" href="<%=basePath%>/css/date/mobiscroll.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/date/mobiscroll_date.css" /> --%>
	</head>

	<body>
		<div class="bounced">
			<div class="tips">
				<p class="change">退改政策</p>
				<div class="contents">
					<div style="margin-bottom: 1rem;">
						<p>
							<span>退票条件</span>
						</p>
						<p id="tuipiao">无信息</p>
					</div>
					<p>
						<span>签转条件</span>
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
		<div class="headerS">
		<header>
			<div class="turnIcon">
				<img src="../img/header.png"/>
			</div>
			<p class="zhixiang"><span>${segments.get(0).getIntlFlights().get(0).getOrgAirPortStr() }</span><img src="../img/jiantou.png" />
			<span>
				<c:if test="${segments.get(0).getIntlFlights().get(0).getTransitCount() == 0 }">
					${segments.get(0).getIntlFlights().get(0).getDetAirPortStr() }
				</c:if>
				<c:if test="${segments.get(0).getIntlFlights().get(0).getTransitCount() > 0 }">
					${segments.get(0).getIntlFlights().get(segments.get(0).getIntlFlights().get(0).getTransitCount()).getDetAirPortStr() }
				</c:if>
			</span></p>
		</header>
		</div>
		<section>			
			
			<c:if test="${hangcheng eq '2'}">
				<!-- 往返-返程信息 -->
				
				<div class="content-space">
					<div class="con-top color">
	
						<p class="typeH">
							<img src="../img/type.png" /> <span>${segments.get(1).getIntlFlights().get(0).getCarrierStr()}${segments.get(1).getIntlFlights().get(0).getAirline() }</span>
						</p>
						<div class="start">
						
							<h3>${mt:fmtByStringStyle(startTime1,'HH:mm')}</h3>
							<p>${segments.get(1).getIntlFlights().get(0).getOrgAirPortStr() }${segments.get(1).getIntlFlights().get(0).getOrgTerm() }</p>
						</div>
						<p>${segments.get(1).getIntlFlights().get(segments.get(1).getIntlFlights().get(0).getTransitCount()).getPlaneType() }</p>
	
					</div>
					<div class="con-top con-mid">
						<p style="margin-top: 0.2rem;" class="dateD">${startData1}</p>
						<div class="fly">
							<p style="color: #11b4ed;">
								<c:if test="${segments.get(1).getIntlFlights().get(0).getTransitCount() == 0 }">
									直飞
								</c:if>
								<c:if test="${segments.get(1).getIntlFlights().get(0).getTransitCount() > 0 }">
									中转${segments.get(1).getIntlFlights().get(segments.get(1).getIntlFlights().get(0).getTransitCount()).getDetAirPortStr() }
								</c:if>
							</p>
							<img src="../img/zhifei.png" style="width: 9rem;" />
						</div>
						<p></p>
						<p>
							${segments.get(1).getIntlFlights().get(0).getFood()}
						</p>
					</div>
					<div class="con-top color con-bot">
						<p>总时长约${times1 }</p>
						<div class="finish">
						
							<h3>${mt:fmtByStringStyle(EndTimes1,'HH:mm')}</h3>
							<p>
							<c:if test="${segments.get(1).getIntlFlights().get(0).getTransitCount() == 0 }">
								${segments.get(1).getIntlFlights().get(0).getDetAirPortStr() }
							</c:if>
							<c:if test="${segments.get(1).getIntlFlights().get(0).getTransitCount() > 0 }">
								${segments.get(1).getIntlFlights().get(segments.get(1).getIntlFlights().get(0).getTransitCount()).getDetAirPortStr() }
							</c:if>
							${segments.get(1).getIntlFlights().get(0).getDetTerm() }</p>
						</div>
						<p>
							<c:if test="${traveltype == 0 }">因公出行</c:if>
							<c:if test="${traveltype == 1 }">因私出行</c:if>
						</p>
					</div>
				</div>
				
				<!-- 往返-返程信息 -->
				<div class="content-space">
					<div class="con-top color">
	
						<p class="typeH">
							<img src="../img/type.png" /> <span>${segments.get(0).getIntlFlights().get(0).getCarrierStr()}${segments.get(0).getIntlFlights().get(0).getAirline() }1</span>
						</p>
						<div class="start">
						
							<h3>${mt:fmtByStringStyle(startTime,'HH:mm')}</h3>
							<p>${segments.get(0).getIntlFlights().get(0).getOrgAirPortStr() }${segments.get(0).getIntlFlights().get(0).getOrgTerm() }</p>
						</div>
						<p>${segments.get(0).getIntlFlights().get(segments.get(0).getIntlFlights().get(0).getTransitCount()).getPlaneType() }</p>
	
					</div>
					<div class="con-top con-mid">
						<p style="margin-top: 0.2rem;" class="dateD">${startData}</p>
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
						<p></p>
						<p>
							${segments.get(0).getIntlFlights().get(0).getFood()}
						</p>
					</div>
					<div class="con-top color con-bot">
						<p>总时长约${times }</p>
						<div class="finish">
						
							<h3>${mt:fmtByStringStyle(EndTimes,'HH:mm')}</h3>
							<p>
							<c:if test="${segments.get(0).getIntlFlights().get(0).getTransitCount() == 0 }">
								${segments.get(0).getIntlFlights().get(0).getDetAirPortStr() }
							</c:if>
							<c:if test="${segments.get(0).getIntlFlights().get(0).getTransitCount() > 0 }">
								${segments.get(0).getIntlFlights().get(segments.get(0).getIntlFlights().get(0).getTransitCount()).getDetAirPortStr() }
							</c:if>
							${segments.get(0).getIntlFlights().get(0).getDetTerm() }</p>
						</div>
						<p>
							<c:if test="${traveltype == 0 }">因公出行</c:if>
							<c:if test="${traveltype == 1 }">因私出行</c:if>
						</p>
					</div>
				</div>
			</c:if>
			
			<c:if test="${hangcheng eq '1'}">
				<div class="content-space">
					<div class="con-top color">
	
						<p class="typeH">
							<img src="../img/type.png" /> <span>${segments.get(0).getIntlFlights().get(0).getCarrierStr()}${segments.get(0).getIntlFlights().get(0).getAirline() }1</span>
						</p>
						<div class="start">
						
							<h3>${mt:fmtByStringStyle(startTime,'HH:mm')}</h3>
							<p>${segments.get(0).getIntlFlights().get(0).getOrgAirPortStr() }${segments.get(0).getIntlFlights().get(0).getOrgTerm() }</p>
						</div>
						<p>${segments.get(0).getIntlFlights().get(segments.get(0).getIntlFlights().get(0).getTransitCount()).getPlaneType() }</p>
	
					</div>
					<div class="con-top con-mid">
						<p style="margin-top: 0.2rem;" class="dateD">${startData}</p>
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
						<p></p>
						<p>
							${segments.get(0).getIntlFlights().get(0).getFood()}
						</p>
					</div>
					<div class="con-top color con-bot">
						<p>总时长约${times }</p>
						<div class="finish">
						
							<h3>${mt:fmtByStringStyle(EndTimes,'HH:mm')}</h3>
							<p>
							<c:if test="${segments.get(0).getIntlFlights().get(0).getTransitCount() == 0 }">
								${segments.get(0).getIntlFlights().get(0).getDetAirPortStr() }
							</c:if>
							<c:if test="${segments.get(0).getIntlFlights().get(0).getTransitCount() > 0 }">
								${segments.get(0).getIntlFlights().get(segments.get(0).getIntlFlights().get(0).getTransitCount()).getDetAirPortStr() }
							</c:if>
							${segments.get(0).getIntlFlights().get(0).getDetTerm() }</p>
						</div>
						<p>
							<c:if test="${traveltype == 0 }">因公出行</c:if>
							<c:if test="${traveltype == 1 }">因私出行</c:if>
						</p>
					</div>
				</div>
			</c:if>
			
			
			<ul class="item-space">
				<c:forEach items="${cabinList }" var="cabin">
					<li class="shift-space">
						<div class="list-space">
							<div class="message-space">
								<div class="h3"><span>¥</span><h3>${cabin.getTotalTaxPrice()+cabin.getTotalPrice() }</h3><span class="contain">含税价</span></div>
								<div class="discount">${cabin.getCangweiDesc() }${cabin.getDiscount() }
									<input type="hidden" value="${cabin.getCangwei() }">
									<input type="hidden" value="${mapKey }">
									<a href="javascript:;">改退政策</a>
								</div>
							</div>
							<div class="ticket">
								<input type="hidden"  value="${cabin.getCangweiDesc() }"/>
								<input type="hidden" value="${cabin.getTotalTaxPrice()+cabin.getTotalPrice() }"/>
								<input type="hidden" value="${mapKey }"/>
								<input type="hidden" value="${traveltype }"/>
								<input type="hidden" value="${cabin.getCangwei() }">
								<input type="hidden" value="${cabin.getSeatsLeft() }"/>
								<p class="make">订</p>
								<c:if test="${cabin.getSeatsLeft() < 10 }"> 
									<p class="remaining">剩${cabin.getSeatsLeft() }张</p>
								</c:if>
							</div>
						</div>
					</li>
				</c:forEach>
			</ul>

		</section>

	</body>
 	<script type="text/javascript" src="../libs/jquery.min.js"></script>
	<script type="text/javascript">
		
		$(".headerS").load("header.html", function(data) {
			$('header div img').eq(0).on('click', function() {
				window.history.back(-1);
			});
		});
		
		/*该退政策提示框的显示和隐藏*/
		
		var arr1=[];
		$('.discount a').on('click', function() {
		
		var i=0;
		$(this).parents('.discount').find('input').each(function (){
	
		arr1[i]=$(this).val();
		i++;
		})
		/* alert(arr1); */	
		var cangwei = arr1[0];
		var mapkey = arr1[1];	
		var base = "${applicationScope.ctx}";
		
		/* var cangwei = document.getElementById("cangwei1").value;
		var mapKey = document.getElementById("mapkey1").value; */
		/* alert("cangwei:" + cangwei);
		alert("mapKey:" + mapkey); */
		param="&mapKey="+mapkey+ "&cangwei="+cangwei;
			$.ajax({
				    url:base+"/intlflight/getIntlTicketRule.act",      //请求的url地址
				    dataType:"text",  					//返回格式为json
				    async:true,						    //请求是否异步，默认为异步，这也是ajax重要特性
				    data:"param="+param, 			    //参数值
				    type:"post",   						//请求方式
				    success:function(req){
				       /*  alert(req); */
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
			$('.bounced').fadeIn();
		});
		$('.closedBtn img').on('click', function() {
			$('.bounced').fadeOut();
		});
		
		var arr=[];
		$('.ticket').on('click', function() {
		
			var i=0;
			$(this).find('input').each(function (){
			arr[i]=$(this).val();
			i++;
			})
			
			var base = "${applicationScope.ctx}";
			var traveltype = arr[3];
			var cangwei = arr[0];
			var mapkey = arr[2];
			var price = arr[1];
			var cangweidesc = arr[4];
			var seatsLeft = arr[5]
			var dates = $(this).parents('.shift-space').find('.h3 h3').html();
			/* alert("cangwei="+cangwei);
			alert("price="+price);
			alert("mapkey="+mapkey); */
			window.location.href = base + "/intlflight/getFlightInfo.act?name=" + dates +"&flag=1&cangwei=" + cangwei +"&mapkey=" + mapkey + "&cangweidesc=" + cangweidesc + "&price=" +price + "&traveltype=" + traveltype + "&seatsLeft=" + seatsLeft;
		})
		
		var url = window.location.href; //获取当前页面的url
		var len = url.length; //获取url的长度值
		var a = url.indexOf("?"); //获取第一次出现？的位置下标
		var b = url.substr(a + 1, len); //截取问号之后的内容
		var c = b.split("&"); //从指定的地方将字符串分割成字符串数组	
		var arr = new Array(); //新建一个数组
		for(var i = 0; i < c.length; i++) {
			var d = c[i].split("=")[1]; //从=处将字符串分割成字符串数组,并选择第2个元素
			arr.push(d); //将获取的元素存入到数组中			
		}
		/* $('.dateD').html(c[0].split("=")[1]);
		$('.start h3').html(c[1].split("=")[1]);
		$('.finish h3').html(c[2].split("=")[1]); 
		$('.typeH span').html(decodeURI(c[3].split("=")[1]));*/
		console.log($('.typeH span').html().substr(0,2));
		if($('.typeH span').html().substr(0,2) == "吉祥"){
			$('.typeH').find('img').attr('src','../img/MU.png');
		}
		if($('.typeH span').html().substr(0,2) == "国际"){
			$('.typeH').find('img').attr('src','../img/CZ.png');
		}
		
		
		
	/* 	function ss(){
		$(".list-space").click(function(){
		var s=$(this).find('input').val();
		})
		} */
	</script>

</html>