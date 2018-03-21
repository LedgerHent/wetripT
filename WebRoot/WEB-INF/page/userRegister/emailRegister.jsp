<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html class="ui-page-login">
  <head>
    <base href="<%=basePath%>">
    
    <title>用户邮箱注册</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript"  src="js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript"  src="js/MyConfirm.js"></script>
	<link rel="stylesheet" type="text/css" href="css/MyConfirm.css">
	<link rel="stylesheet" type="text/css" href="css/reset.css" />
	<link rel="stylesheet" type="text/css" href="css/login.css"/>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	.email_form {
    width: auto;
    margin-top: 1rem;
    background-color: #fff;
    border-radius: 10px;
    margin-left: 1.5rem;
    margin-right: 1.5rem;
}
</style>
  </head>
  <script type="text/javascript">
  		function sendEmail() {
  		var email = $("#login-tel-input").val();
  		if (email != "") {
     		var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
     	if(reg.test(email)){
　　　　var index=email.indexOf("@");
		var parm=email.substr(index); 
		var url = "<%=basePath%>send/email.act";
		var htmlobj;
		if (parm != "") {
			htmlobj= $.ajax({
				url : url,
				type : "POST",
				data : {'emailsuffix':parm,'email':email},
				async : true,
				dataType : "html",
				success:function(data){
				//alert(data);
				if(data=='true'){
				$(this).MyConfirm({
							content : "邮件发送成功。"
					});	
				}
				if(data=='false'){
				
				$(this).MyConfirm({
							content : "非合作企业邮箱，请重新输入或联系客服。"
					});
				}
				if(data=='exist'){
				
				$(this).MyConfirm({
							content : "该邮箱已被注册，无需重复注册，请直接登录。"
					});
				}
				},
				error:function(){
				}
			});
			
		}
　　	}else{
			$(this).MyConfirm({
							content : "请输入正确的邮箱格式。"
					});
}
    }else{
			$(this).MyConfirm({
							content : "邮箱不能为空，请输入。"
					});
		};
		
	}
  
  </script>
  <body>
<div id="container">
	<div class="">
			<ul class="login-bar">
				<li style="" class="login-bar-main">请输入您的邮箱</li>
			</ul>
		</div>
<div class="email_form" id="">
    <form id="form" name="form" action="" method="post">
        <div class="login-tel">
					<label style="" class="login-tel-label">邮　箱:</label>
					<input id="login-tel-input" name="mobile" value="" type="text" placeholder="请输入邮箱" class="login-tel-inp"/>
					<img src="<%=basePath%>/icon/x.png" class="login-tel-delete" id="login-tel-delete" style=""/>
		</div>
    </form>
    
</div>
<div class="btn">
			<button type="button" id="login" class="login-btn" onclick="sendEmail()">注&nbsp;&nbsp;&nbsp;&nbsp;册</button>
		</div>
		</div>
</body>

</html>
<script type="text/javascript">
	function setSize() {
			var iHtml = document.querySelector('html');
			var w = iHtml.getBoundingClientRect().width;
			w = w > 750 ? 750 : w;
			iHtml.style.fontSize = w / 37.5 + 'px';
		}
		setSize();
		window.addEventListener('resize', setSize, false);
		window.addEventListener('orientantionchange', setSize, false);

</script>
