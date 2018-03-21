<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="../public/tag.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>">
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no"/>
		<title>我的</title>
		<link rel="stylesheet" type="text/css" href="css/allOrder.css"/>
		<link rel="stylesheet" type="text/css" href="css/MyConfirm.css">
	</head>
	<body>
	<div id="container">
		<section>
			<header>
				<div>
					<img src="img/header.gif"  <c:if test="${iflag == 'intl'}" >onclick="window.location.href='<%=basePath%>/intlflight/search.act';"</c:if> <c:if test="${iflag != 'intl'}" >onclick="window.location.href='flight/search.act';"</c:if>/>
				</div>
				<p class="zhixiang">我的</p>
			</header>
			<div class="content-my" onclick="jump()">
				<form action="wetrip/updateInfo.act" id="jumpp">
				  <input type="hidden" name="id" value="${user.userid }">
				  <input type="hidden" name="name" value="${user.username }">
				  <input type="hidden" name="email" value="${user.email }">
				  <input type="hidden" name="phone" value="${user.phone }">
				  <input type="hidden" name="usertype" value="${user}">
				  <input type="hidden" name="idcard" value="${user.idcard }">
				</form>
				
				<div>
					<p class="name">${user.username }</p>
				</div>
				<div class="new">
					<p>
						<span >手机号</span>
						<span class="phone">${user.phone }</span>
					</p>
					<p>
						<span>身份证</span>
						<span class="phone">${user.idcard }</span>
					</p>
					<p>
						<span>邮箱</span>
						<span class="email">${user.email }</span>
					</p>
				</div>
			</div>
			<ul class="order">
				<!-- <li class="order-list" style="border-bottom: 0.02rem dotted #CCCCCC;">
					<p>我的</p>
					<div>
						<img src="img/you.png"/>
					</div>
				</li> -->
				<li class="order-list" style="border-bottom: 0.1rem dotted #CCCCCC;"  url="order/list.act?status=0">
				 
					<p>国内机票订单</p>
					<div>
						<img src="img/you.png"/>
					</div> 
				</li>

				<li class="order-list" id="judge" url="order/list.act?status=101">
				 
				
				<input type="hidden" value="${rolename }" id="rolename" >
					<p>国内机票待审核订单</p>
					<div>
						<img src="img/you.png"/>
					</div>
				</li>

			</ul>
			<ul class="order">
				<!-- <li class="order-list" style="border-bottom: 0.02rem dotted #CCCCCC;">
					<p>我的</p>
					<div>
						<img src="img/you.png"/>
					</div>
				</li> -->

				<li class="order-list" style="border-bottom: 0.1rem dotted #CCCCCC;"  url="<%=basePath%>/intlflight/getIntlOrderList.act?orderStatus=0"  >

					<p>国际机票订单</p>
					<div>
						<img src="img/you.png"/>
					</div>
				</li>




				<li class="order-list" id="judge"  url="<%=basePath%>/intlflight/getIntlOrderList.act?orderStatus=1">


					<input type="hidden" value="${rolename }" id="rolename" >
					<p>国际机票待审核订单</p>
					<div>
						<img src="img/you.png"/>
					</div>
				</li>
			</ul>
			<ul class="order">
				<li class="order-list myOrder" style="border-bottom: 0.1rem dotted #CCCCCC;" url="orderInfo/listAllOrderInit.act">
					<p>酒店订单</p>
					<div>
						<img src="img/you.png"/>
					</div>
				</li>
				<li class="order-list myOrder" id="waitHotelCheck" url="orderInfo/listApprovalPendingOrderInit.act">
					<p>酒店待审核订单</p>
					<div>
						<img src="img/you.png"/>
					</div>
				</li>
			</ul>
			<ul class="order">
				<li class="order-list"  url="ppp/pppp.act" >
					<p>常旅客</p>
					<div>
						<img src="img/you.png"/>
					</div>
				</li>
				<li class="order-list">
					<p>来源</p>
					<div>
						<c:if test="${isWhere=='100' }">
						    H5
						</c:if>
						<c:if test="${isWhere=='200' }">
						          微信
						</c:if>
						<c:if test="${isWhere=='201' }">
						          杭州讯盟
						</c:if>
					</div>	
				</li>
				<c:if test="${isWechat=='1' }">
				<%--<li class="order-list" style="display:none;">--%>
				<li class="order-list" onclick="wechatUnbind()">
					<p>解除微信绑定</p>
					<div>
						<img src="img/you.png"/>
					</div>	
				</li>
				</c:if>
			</ul>
		</section>
	</div>
	</body>

	<script type="text/javascript" src="libs/gublic.js"></script>
	<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript"  src="js/MyConfirm.js"></script>
    <script type="text/javascript">
      /* $("li.order-list").on('click',function(){
      	var url = $(this).attr('url');
      	if(url){
	      	window.location.href = url
      	}
      	return ;
      }); */
      
      function wechatUnbind()
      {
    	  $(this).MyConfirm({
              content: "是否取消微信与商旅账户的绑定？",
              hasCancelBtn:true
          },unBindCallback);
      }
      
      function unBindCallback(isConfirm){
    	  if(isConfirm){
    		  window.location.href="bindSmsCheckCode/unbindUser.act";
    	  }
    	}
      
      $("li.order-list").each(function(idx,ele){
      	var url = $(ele).attr('url');
      	$(ele).on("click",function(){
      		if(url){
      			window.location.href = url;
      		}
      	})
      });
    
      $(function (){
    	//alert(${isWechat});
        var rolename=$("#rolename").val();
        if(rolename.indexOf('客服') >0 || rolename.indexOf('审核')>0){
          $("#judge").show();
          $("#waitHotelCheck").show();
          $("#judge").prev().css("border-bottom","0.02rem dotted #CCCCCC");
          $("#waitHotelCheck").prev().css("border-bottom","0.02rem dotted #CCCCCC");
        }else{
         $("#judge").hide();
         $("#waitHotelCheck").hide();
         $("#judge").prev().css("border-bottom","");
         $("#waitHotelCheck").prev().css("border-bottom","");
        }
      
      });
      
      function jump(){
      $("#jumpp").submit();
      }
      
    </script>
</html>