<%@page import="com.google.gson.Gson"%>
<%@page import="java.text.MessageFormat"%>
<%@page import="com.viptrip.wetrip.model.Response_Test"%>
<%@page import="etuf.v1_0.model.base.output.OutputResult"%>
<%@page import="com.viptrip.wetrip.controller.Test"%>
<%@page import="com.viptrip.wetrip.model.Request_Test"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>支付测试</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		.l1{
			width: 600px;
		}
		.l2{
			width: 500px;
		}
	</style>
	<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript">
		function toPay(){
			alert("1111");
			var para = $("#wxpay").serialize();
			$.ajax({
				url:"pay/wxpay.act",
				type:"post",
				data:para,
				async: false,
				success:function(data){
					if(data && data.mweb_url){
						window.location = data.mweb_url;
					}
				}
			});
		}
		
	</script>
</head>

  <body>
  	<fieldset class="l1">
  		<legend>国内机票支付</legend>
  		<fieldset class="l2">
	  		<legend>测试支付宝支付</legend>
		  	<form action="unipay/pay.act">
				<input type="hidden" name="payType" VALUE="11">
				<input type="hidden" name="notifyURL" VALUE="http://61.51.80.138:58899/wetrip/unipay/test_ntf.act">
				<input type="hidden" name="returnURL" VALUE="http://61.51.80.138:58899/wetrip/unipay/test_rtn.act">
		  	订单号:<input name="orderId" value="<%=System.currentTimeMillis() %>"  /><br>
		  	价格:<input name="price" value="0.01"   /><br>
		  	标题:<input name="title" value="支付宝下单测试1"   /><br>
		  	主体:<input name="body" value="支付宝下单测试主体1"   /><br>
		  	<input type="submit" value="测试支付"/>
		  	</form>
	  	</fieldset>
	  	<fieldset class="l2">
	  		<legend>测试微信支付</legend>
			<form action="unipay/pay.act">
				<input type="hidden" name="payType" VALUE="21">
				<input type="hidden" name="notifyURL" VALUE="http://61.51.80.138:58899/wetrip/unipay/test_ntf.act">
				<input type="hidden" name="returnURL" VALUE="http://61.51.80.138:58899/wetrip/unipay/test_rtn.act">
				订单号:<input name="orderId" value="<%=System.currentTimeMillis() %>"  /><br>
				价格:<input name="price" value="0.01"   /><br>
				标题:<input name="title" value="微信下单测试1"   /><br>
				主体:<input name="body" value="微信下单测试主体1"   /><br>
				<input type="submit" value="测试支付"/>
			</form>
	  	</fieldset>
  	</fieldset>
  	
  	<hr>
  	<fieldset class="l1">
  		<legend>国际机票支付</legend>
	  	<fieldset class="l2">
	  		<legend>测试国际支付宝支付</legend>
		  	<form action="intlflight/intlPay.act">
		  	<input type="hidden" name="payType" value="11" />
		  	订单号:<input name="orderId" value="<%=System.currentTimeMillis() + 2%>"  /><br>
		  	价格:<input name="price" value="0.01"   /><br>
		  	标题:<input name="title" value="国际下单测试1"   /><br>
		  	主体:<input name="body" value="国际下单测试主体1"   /><br>
		  	<input type="submit" value="测试支付"/>
		  	</form>
	  	</fieldset>
	  	<fieldset class="l2">
	  		<legend>测试国际微信支付</legend>
		  	<form id="wxpay" action="intlflight/intlPay.act">
		  	<input type="hidden" name="payType" value="21" />
		  	订单号:<input name="orderId" value="<%=System.currentTimeMillis() + 3%>"  /><br>
		  	价格:<input name="price" value="0.01"   /><br>
		  	标题:<input name="title" value="国际下单测试1"   /><br>
		  	主体:<input name="body" value="国际下单测试主体1"   /><br>
		  	<input type="submit" value="测试支付" />
		  	</form>
	  	</fieldset>
  	</fieldset>
  </body>
</html>
