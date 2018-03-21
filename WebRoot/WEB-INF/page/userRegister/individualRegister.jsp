<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/WEB-INF/page/public/tag.jsp" %>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=no" />
<title>在线散客注册</title>
<link rel="stylesheet" type="text/css" href="css/allOrder.css" />
<link rel="stylesheet" type="text/css" href="css/mobiscroll.css" />
<link rel="stylesheet" type="text/css" href="css/MyConfirm.css">
<link rel="stylesheet" type="text/css" href="css/mobiscroll_date.css" />
<style type="text/css">
input {
	font-size: 1.5rem;
	border: 0;
	outline: none;
	background: #FFFFFF;
}
</style>
</head>
<body>
<div id="container">
		
		<div class="bounced cardAdd">
			<div class="payment-con" id="tests">
				<div class="payment-title">
					<p>证件类型</p>
				</div>
				<div class="payment-choose">
					<p id="1">
						<span>身份证</span>
						<img src="img/choose.png" / class="selected">
					</p>
					<p id="2">
						<span>护照</span>
						<img src="img/choose.png" / class="selected">
					</p>
					
				</div>
			</div>
		</div>
	<form action="<%=path %>/register/registeruser.act" id="save" method="post">
		<section>
			<header>
			<p class="zhixiang">散客注册</p>
			</header>
			
			<ul class="content-flyer">
				<li class="con-list bor">
					<div style="width:100%;">
						<span class="name">姓名</span> <input name="username" placeholder="必填" id="username" style="width:60%;">
					</div></li>
				<li class="con-list bor">
					<div style="width:100%;">
						<span class="name phone">手机号</span> <input name="phone" placeholder="必填" id="usermobile" style="width:60%;">
					</div></li>
				
				<li class="con-list bor" style="border-bottom:none;">
					<div style="width:100%;">
						<span class="name">邮箱</span> <input type="text" name="email" placeholder="必填" id="email" style="width:60%;">
					</div></li>
				
			</ul>
			<div class="item">
				<div class="item-title">证件信息</div>
				<ul>
					<li class="item-list">
						<div class="type">
							<div>
								<span>证件类型</span> <span class="card card-type" id="cardtype">身份证</span>
								<input type="hidden" id="idtype" name="idType">
							</div>
							<img src="img/you.png" />
						</div>
						<div class="the">
							<div>
								<span>证件号码</span> <input type="text" name="num" placeholder="必填"
									class="card" id="idnum" style="width:60%;">
									<input type="hidden" id="shenfen" name="idcard">
									<input type="hidden" id="huzhao" name="passport">
							</div>
							
						</div>
						
						</li>
					
				</ul>
			</div>
			
			<ul class="content-flyer" id="passportMess" style="position: relative;">
				<li class="con-list bor">
				<div style="width:100%;">
						<span class="name">国籍</span> 
						<input name="nationalitys" placeholder="必填" id="nationality" style="width:60%;" 
						class="nationality-suggest-input" >
						<input type="hidden" class="counum" value="" name="nationalityTwo">
						<div class="nationality-suggest-list-container" style="display:block;" id="tests3">
							<p class="nationality-suggest-hint">支持中文、英文搜索</p>
							<ul class="nationality-suggest-list"></ul>
						</div>
					</div>
				</li>
				<li class="con-list bor">
					<div style="width:100%;">
						<span class="name">护照有效期</span> 
						<div style="width:60%;">
							<input name="passportEnd" placeholder="必填" class="time1"  id="date1" style="width:40%;">
							<span style="width:15%;display:inline-block;text-align:center;font-size:3rem;"></span>
							
						</div>
					</div></li>
				
				<li class="con-list bor" style="border-bottom:none;">
					<div style="width:100%;">
						<span class="name">护照英文名</span> <input type="text" name="passportEn" placeholder="必填" id="engname" style="width:60%;">
					</div></li>
				
			</ul>
			<footer class="footer">
				<div onclick="save(this)">注册</div>
			</footer>
		</section>
	</form>
	</div>
</body>
<script type="text/javascript" src="libs/gublic.js"></script>
<script type="text/javascript" src="libs/jquery.min.js"></script>
<script type="text/javascript" src="libs/mobiscroll_date.js"
	charset="gb2312"></script>
<script type="text/javascript" src="libs/mobiscroll.js"></script>
<script type="text/javascript" src="libs/nationality.js"></script>
<script type="text/javascript" src="libs/main.js"></script>
<script type="text/javascript"  src="js/MyConfirm.js"></script>
	
<script type="text/javascript">
	/*-------------默认属性-----------*/
		function defalutStyles(objStyles) {
			$(objStyles).eq(0).css('color', '#00AFEC');
			$(objStyles).find('.selected').eq(0).show();
		};
		defalutStyles('.typeAdd .payment-choose p'); //类型
		defalutStyles('.genderAdd .payment-choose p'); //性别
		defalutStyles('.cardAdd .payment-choose p'); //证件类型
		/*-----成人，儿童，婴儿类型的选择框------*/
		$('.choose').eq(0).on('click', function() {
			sameTips('.typeAdd', '.typeAdd .payment-con', '16rem');
		});
		$('.typeAdd .payment-choose p').on('click', function() {
			$(this).css('color', '#00AFEC').siblings().css('color', '');
			$(this).find('.selected').show().parents('.typeAdd .payment-choose p').siblings().find('.selected').hide();
			$('.typeAdd').delay(500).fadeOut(0);
			$('.con-list .people').html($(this).find('span').eq(0).html());
		});
		/*------性别类型的选择--------*/
		$('.choose').eq(1).on('click', function() {
			sameTips('.genderAdd', '.genderAdd .payment-con', '12rem');
		});
		$('.genderAdd .payment-choose p').on('click', function() {
			$(this).css('color', '#00AFEC').siblings().css('color', '');
			$(this).find('.selected').show().parents('.genderAdd .payment-choose p').siblings().find('.selected').hide();
			$('.genderAdd').delay(500).fadeOut(0);
			$('.con-list .gender').html($(this).find('span').eq(0).html());
		});
		/*----------证件类型选择的效果是实现------*/
		var num = ($('.cardAdd .payment-choose p').length + 1) * 80 / 20 + 'rem';
		$('.item-list .type').on('click', function() {
			sameTips('.cardAdd', '.cardAdd .payment-con', num);
		});
		$('.cardAdd .payment-choose p').on('click', function() {
			$(this).css('color', '#00AFEC').siblings().css('color', '');
			$(this).find('.selected').show().parents('.cardAdd .payment-choose p').siblings().find('.selected').hide();
			$('.cardAdd').delay(500).fadeOut(0);
			$('.type .card-type').html($(this).find('span').eq(0).html());
			if(this.id==2){
			   $("#passportMess").delay(500).fadeIn(0);
			}else{
			   $("#passportMess").delay(500).fadeOut(0);
			}
		});

/*-------------日期插件-------------*/
		$(function() {
			var currYear = (new Date()).getFullYear();
			var opt = {};
			opt.date = {
				preset: 'date'
			};
			opt.datetime = {
				preset: 'datetime'
			};
			opt.time = {
				preset: 'time'
			};
			opt.default = {
				theme: 'android-ics light', //皮肤样式
				display: 'modal', //显示方式 
				mode: 'scroller', //日期选择模式
				dateFormat: 'yyyy-mm-dd',
				lang: 'zh',
				showNow: true,
				nowText: "今天",
				startYear: currYear - 50, //开始年份
				endYear: currYear + 10 //结束年份
			};

			$("#date1").mobiscroll($.extend(opt['date'], opt['default']));
		});



		function sameTips(objParent, objSilbling, numLength) {
			$(objParent).fadeIn();
			$(objSilbling).animate({
				height: numLength
			}, 1000)
		};
		$('.typeAdd').bind('click', function(e) {
			hideLayer(e, 'test', '#test');
		});

		$('.genderAdd').bind('click', function(e) {
			hideLayer(e, 'tested', '#tested');
		});
		$('.cardAdd').bind('click', function(e) {
			hideLayer(e, 'tests', '#tests');
		});
		$('.cardAdd').bind('click', function(e) {
			hideLayer(e, 'tests', '#tests');
		});
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
	</script>
<script type="text/javascript">
	 function showMessage(obj,mess){
  		//有很多可用参数，详见jquery插件，以下是用法示例
  		$(this).MyConfirm({
            content: mess,
            hasCancelBtn:true
        });
  	}
	
	$(function(){
	$("#passportMess").hide();
	});
	
	
	  function save(obj){
		
		var uname=$("#username").val();
        var phone=$("#usermobile").val();
        var idcard=$("#idnum").val();
        var email=$("#email").val();
		var idtype=$("#cardtype").text();
        var country=$("#nationality").val();
        var dateStr=$("#date1").val();
        var dateEnd=$("#date2").val();
        var engName=$("#engname").val();
		var idtypes="";
		
		if(idtype=='身份证'){
		    $("#shenfen").val(idcard);
		    $("#huzhao").val(null);
	   }else if(idtype=='护照'){
	        $("#huzhao").val(idcard);
	        $("#shenfen").val(null);
	   }
        var mess="";
         if($.trim(uname)==""){
            mess="请填写用户名";
            showMessage(obj,mess);
            return false;
         }
         
          var reg = /^((13[0-9])|(15[^4,\\D])|(17[0-9])|(18[0-9]))+\d{8}$/;
         if($.trim(phone)=='' || !reg.test($.trim(phone))){
           mess="请填写合法的手机号码";
           showMessage(obj,mess);
           return false;
         }
         
        var reg2 =/^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+(\.[a-zA-Z]{2,3})+$/;
         if($.trim(email)=='' || !reg2.test($.trim(email))){
          mess="请填写合法的邮箱";
          showMessage(obj,mess);
           return false;
         }
         
         if($.trim(idcard)==''){
          mess="请填写证件信息";
          showMessage(obj,mess);
          return false;
         }
         
        var reg1 = /^[1-9][0-9]{5}(19[0-9]{2}|2[0-9]{3})(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])[0-9]{3}[0-9xX]$/i;
         if(idtype=='身份证'){
           if(!reg1.test($.trim(idcard))){
           mess="请填写合法的身份证号码";
             showMessage(obj,mess);
             return false;
           }
         }else if(idtype=='护照'){
           if($.trim(idcard).length<5){
             mess="请填写有效的护照号码";
             showMessage(obj,mess);
             return false;
           }
           
           if($.trim(country)=='' || $(".counum").val==''){
             mess="请选择正确的国籍";
             showMessage(obj,mess);
             return false;
           }
           
        /*  if(country!=""){
         var ss=$(".counum").val();
             alert(ss);
         } */
          var dB = new Date(dateStr.replace(/-/g, "/"));
           if(dateStr=='' ||  Date.parse(dB)<new Date()){
             mess="请填写合法的护照有效期";
             showMessage(obj,mess);
             return false;
           }

           if($.trim(engName)==''){
             mess="请填写合法的护照英文名";
             showMessage(obj,mess);
             return false;
           }
         }
         
		$("#save").submit();
		}
	
	</script>
</html>