<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="../public/tag.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="ui-page-login">

	<head>
		<meta charset="utf-8">
		<base href="<%=basePath%>">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>用户绑定</title>
		<link rel="stylesheet" type="text/css" href="css/login.css"/>
		<link rel="stylesheet" type="text/css" href="css/MyConfirm.css">
		<style type="text/css">
			
		</style>
	</head>

	<body>
		<header>
			<div class="arrow">
				<img src="img/header.gif" />
			</div>
			<p class="number">手机动态密码登录</p>
		</header>
		<section>
			<div class="wrap">
				<form id="checkForm" action="bindSmsCheckCode/smsCode.act" method="post" >
					<ul class="verify">
						<li>
							<div class="click">
								<span>手机号</span>
								<input type="text" id="txtMobile" name="mobile" value="${data.mobile}" placeholder="请输入手机号码" />
							</div>
							<div class="obtain">
								<img src="icon/x.png" / class="iconx">
							</div>
						</li>
						<li>
							<div class="click">
								<span>验证码</span>
								<input id='txtCode' name="checkCode" type="text" value="${data.checkCode}" placeholder="请输入验证码" />
							</div>
							<div class="obtain">
								<p id="btnGetCode" onclick="getCheckCode()">获取验证码</p>
							</div>
						</li>
					</ul>
				</form>
				<div id="login" class="binding">
					<a href="javascript:;">绑&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;定</a>
				</div>
			</div>
		</section>
	</body>
	<script type="text/javascript"  src="js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/MyConfirm.js"></script>
	<script type="text/javascript">
	
	function setSize() {
		var iHtml = document.querySelector('html');
		var w = iHtml.getBoundingClientRect().width;
		w = w > 750 ? 750 : w;
		iHtml.style.fontSize = w / 37.5 + 'px';
	}
	setSize();
	document.querySelector('body').style.display = "block";
	window.addEventListener('resize', setSize, false);
	window.addEventListener('orientantionchange', setSize, false);
	
	$('.verify input').eq(0).focus(function() {
		$(this).parents('li').find('img').css('display', 'block')
	});
	$('.iconx').click(function() {
		$(this).parents('li').find('input').val('');
		$(this).css('display', 'none');
	});
	
	$('.click').click(function(){
		$(this).find('input').focus();
	});


	var InterValObj; //timer变量，控制时间
    var count = 60; //间隔函数，1秒执行
    var curCount;//当前剩余秒数

    function getCheckCode() {
        var mobile = document.getElementById("txtMobile").value;
        if (!checkValidMobile(mobile)) {
            //alert('无效的手机号码，请输入11位合法的大陆手机号码。');
            $(this).MyConfirm({
                content: "无效的手机号码，请输入11位合法的大陆手机号码。"
            });
            return;
        }
        
        $.ajax({
        	url:'bindSmsCheckCode/smsCode.act',
			dataType:'json',
			type:'POST',
			async:true,
			data:{'mobile':mobile},
            success: function (result) {
                if (result.status == 0) {//调用成功，但不一定是发送成功。修改按钮状态，1分钟后重试
                	if(result.data.status == 0){
	                    curCount = count;
	                    //设置button效果，开始计时
	                    $("#btnGetCode").attr("disabled", "true");//关闭按钮
	                    $("#btnGetCode").html(curCount + "秒后重新获取");
	                    InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
	                }else {//调用失败，提示。
	                    //alert(result);
	                    $(this).MyConfirm({
	                        content: result.data.msg
	                    });
	                }
                }
                else {//调用失败，提示。
                    //alert(result);
                    $(this).MyConfirm({
                        content: result.data.msg
                    });
                }
            },
            error: function (result) {
                //alert(data);
                $(this).MyConfirm({
                    content: result
                });
            }
        });
    }

    //timer处理函数
    function SetRemainTime() {
        if (curCount == 0) {
            window.clearInterval(InterValObj);//停止计时器
            $("#btnGetCode").removeAttr("disabled");//启用按钮
            $("#btnGetCode").html("获取验证码");
        }
        else {
            $("#btnGetCode").html(curCount + "秒后重新获取");
            curCount--;
        }
    }

    function checkMsgCode(code) {
        var regex = /^\d{6}$/
        return regex.test(code)
    }

    function ValidInput(id) {
        var ctl = $('#' + id);
        var num = ctl.val();
        if (id == "txtMobile") {
            if(num==""){
            	$(this).MyConfirm({
                    content: "请输入手机号码。"
                });
                ctl.focus();
                return false;
            }else if (!checkValidMobile(num)) {
                $(this).MyConfirm({
                    content: "无效的手机号码，请输入11位合法的大陆手机号码。"
                });
                ctl.focus();
                return false;
            }
        }
        else if (id == "txtCode") {
        	if(num==""){
            	$(this).MyConfirm({
                    content: "请输入短信验证码。"
                });
                ctl.focus();
                return false;
            }else if (!checkMsgCode(num)) {
                $(this).MyConfirm({
                    content: "验证码无效，验证码是6位数字。"
                });
                ctl.focus();
                return false;
            }
        }
        else if (id == "check_Protocol") {
            if (!document.getElementById(id).checked) {
                $(this).MyConfirm({
                    content: "请先选择服务协议。"
                });
                return false;
            }
        }
        return true;
    }

	</script>
	<script>
		$("#login").on("click", function() {
			if(ValidInput("txtMobile") && ValidInput("txtCode")){
				//绑定接口调用
				$("#checkForm").attr("action","<%=path %>/bindSmsCheckCode/bindingUser.act");
				$("#checkForm").submit();
			}
		});

	</script>

</html>