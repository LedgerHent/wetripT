<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="../public/tag.jsp" %>
<!DOCTYPE html>
<html>
	<head>
	<base href="<%=basePath%>">
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no"/>
		<title>修改常旅客</title>
		<link rel="stylesheet" type="text/css" href="css/allOrder.css"/>
		<link rel="stylesheet" type="text/css" href="css/mobiscroll.css" />
		<link rel="stylesheet" type="text/css" href="css/mobiscroll_date.css" />
		<link rel="stylesheet" type="text/css" href="css/MyConfirm.css">
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
		<div class="bounced typeAdd">
			<div class="payment-con" id="test">
				<div class="payment-title">
					<p>类型</p>
				</div>
				<div class="payment-choose">
					<p>
						<span>成人</span>
						<img src="img/choose.png" / class="selected">
					</p>
					<p>
						<span>儿童</span>
						<img src="img/choose.png" / class="selected">
					</p>
					<p>
						<span>婴儿</span>
						<img src="img/choose.png" / class="selected">
					</p>
				</div>
			</div>
		</div>
		<div class="bounced genderAdd">
			<div class="payment-con" id="tested">
				<div class="payment-title">
					<p>性别</p>
				</div>
				<div class="payment-choose">
					<p>
						<span>保密</span>
						<img src="img/choose.png" / class="selected">
					</p>
					<p>
						<span>男</span>
						<img src="img/choose.png" / class="selected">
					</p>
					<p>
						<span>女</span>
						<img src="img/choose.png" / class="selected">
					</p>
				</div>
			</div>
		</div>
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
						<span>因公护照</span>
						<img src="img/choose.png" / class="selected">
					</p>
					<p id="6">
						<span>因公港澳通行证</span>
						<img src="img/choose.png" / class="selected">
					</p>
				</div>
			</div>
		</div>
		
		<form action="<%=path %>/ppp/update.act?flag=${flag}" id="save" method="post">
		<input id="intlflag" name="intlflag" type="hidden" value="${intlflag}"> 
		<input type="hidden" id="bookinfo" name="bf" value='${bookinfo}' />
		<section>
			<header>
				<div>
					<img src="img/header.gif" onclick="back()"/>
				</div>
				<p class="zhixiang">修改常旅客</p>
			</header>
			<ul class="content-flyer">
				<li class="con-list bor">
				<div>
					<span class="name">姓名</span>
					<input name="name" placeholder="必填" value="${onePass.name }" id="username">
					<input type="hidden" value="${onePass.id }" name="id" id="onepassid">
					</div>
				</li>
				<li class="con-list bor">
				<div>
					<span class="name phone">手机号</span>
					<input  name="mobile" placeholder="必填" value="${onePass.mobilephone }" id="usermobile">
					</div>
				</li>
				<li class="con-list choose bor">
					<div class="lefts">
						<span class="name">类型</span>
						<span class="people" id="type">成人</span>
						
						<input type="hidden" name="type" id="passtype">
					</div>
					<img src="img/you.png"/>
				</li>
				<li class="con-list choose bor" style="display:none">
					<div class="lefts">
						<span class="name">性别</span>
						<span class="name gender" id="sex">保密</span>
						<input type="hidden" name="sex" id="passsex">
					</div>
					<img src="img/you.png"/>
				</li>
				<li class="con-list bor" style="border-bottom:none">
				<div>
					<span class="name">邮箱</span>
					<input type="text" name="email" placeholder="选填" value="${onePass.email }" id="email">
					</div>
				</li>
				<li class="con-list" style="display:none">
				<div>
					<span class="name">生日</span>
					<input placeholder="选填" / id="date" name="birthday" class="birthdays">
					</div>
				</li>
			</ul>
			<div class="item">
				<div class="item-title">证件信息</div>
				<ul>
					<li class="item-list">
						<div class="type">
							<div>
								<span >证件类型</span>
								<span class="card card-type" id="idtype">身份证</span>
								<input type="hidden" name="idtype" id="passidtype">
							</div>
							<img src="img/you.png"/> 
						</div>
						<div class="the">
							<div>
								<span >证件号码</span>
							<input type="text" name=num placeholder="必填" class="card" value="${onePass.idnumber }" id="idnum">
							</div>
							<!-- <img src="img/add.png"/> -->
						</div>
					</li>
				</ul>
			</div>
			<footer class="footer" id="intlupdate">
				<div onclick="check(this)" >保存</div>
			</footer>
		</section>
</form>
	</body>
<script type="text/javascript" src="libs/gublic.js"></script>
	<script type="text/javascript" src="libs/jquery.min.js"></script>
	<script type="text/javascript" src="libs/mobiscroll_date.js" charset="gb2312"></script>
	<script type="text/javascript" src="libs/mobiscroll.js"></script>
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
			sameTips('.genderAdd', '.genderAdd .payment-con', '16rem');
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
			$("#passidtype").val(this.id);
		});

		function sameTips(objParent, objSilbling, numLength) {
			var height = $("body").height();
			$('.bounced').height(height);
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

			$("#date").mobiscroll($.extend(opt['date'], opt['default']));

		});

		/*-----------input输入框的效果----------*/
		$(function() {
			$('input').on('input propertychange', function() {
				var result = $(this).val();
				if(result != "") {
					$(this).parents('.bor').find('.onfocus img').show();
				} else {
					$(this).parents('.bor').find('.onfocus img').hide();
				}
			});
			$('.onfocus img').on('click', function() {
				$(this).parents('.bor').find('input').val('');
				$(this).hide();
			});
		});
		/*-----移入和离开时关闭按钮显示或者隐藏的*/
		$(function() {
			$('.content-flyer li').mousemove(function() {
				var result = $(this).find('input').val();
				if(result != "") {
					$(this).find('.onfocus img').show();
				}
			});
			$('.content-flyer li').mouseleave(function(e) {
				$(this).find('.onfocus img').hide();
			});
		});
		
		function showMessage(obj,mess){
  		//有很多可用参数，详见jquery插件，以下是用法示例
  		$(this).MyConfirm({
            content: mess,
            hasCancelBtn:true
        });
  	}
  	
  function check(obj){
	 
	 var idcard=$("#idnum").val();
	 var passid=$("#onepassid").val();
	    $.post(
	    'ppp/idcard.act',
	    {'idcard':idcard,'passid':passid},
	    function (data){
	    if(data==''){
	    save(obj);
	    }else{
	    mess=data;
	    showMessage(obj,mess);
	    }
	    });
	   
	   }
		
		function save(obj){
		var type=$("#type").text();
		if(type=='儿童'){
		  type=2;
		}else if(type=='成人'){
		  type=1;
		}else type=3;
		$("#passtype").val(type);
		
	     var uname=$("#username").val();
         var phone=$("#usermobile").val();
         var idcard=$("#idnum").val();
         var email=$("#email").val();
         var birthdays=$(".birthdays").val();
        var idtype= $("#passidtype").val();
         if($.trim(uname)==""){
            mess="用户名为空";
            showMessage(obj,mess);
            return false;
         }
           var reg = /^[1]\d{10}$/;
         if($.trim(phone)=='' || !reg.test(phone)){
           mess="电话不符合要求";
           showMessage(obj,mess);
           return false;
         }
         var reg1 =/^[1-9][0-9]{5}(19[0-9]{2}|2[0-9]{3})(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])[0-9]{3}[0-9xX]$/i;
         if(idtype=='1'){
           if(!reg1.test(idcard)){
           mess="身份证格式不符合要求";
             showMessage(obj,mess);
             return false;
           }
         }else{
           if($.trim(idcard)==''){
           mess="证件信息不符合";
           showMessage(obj,mess);
              return false;
           }
         }
         
         var reg2 =/^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+(\.[a-zA-Z]{2,3})+$/;
         if($.trim(email)!='' && !reg2.test(email)){
          mess="邮箱格式不符合要求";
          showMessage(obj,mess);
           return false;
         }
	
		/* $("#save").submit(); */
		
		var intlflag=$('#intlflag').val();
		var passid=$("#onepassid").val();
				if(intlflag=='intl'){
				
				
					$.ajax({
				    url:"<%=path%>/ppp/intlupdate.act",     
				    dataType:"text",  					 //返回格式为json
				    async:true,						    //请求是否异步，默认为异步，这也是ajax重要特性
				    data:$("#save").serialize(), 			    //参数值
				    type:"post",   						//请求方式
				    success:function(data){
				      if('1'==data){
						//showMessage("保存成功！");
				
						parent.window.hideS(passid);
							
						}
				    },
				});
				
				
				/* $.post(function(){
					$("#save").attr("action"),//国际保存修改
					$("#save").serialize(),
					function(data){
					alert("data="+data)
						if('1'==data){
						showMessage("保存成功！");
				
						parent.window.hideS(passid);
							
						}else{
						showMessage("保存失败！");
						}
						
					},
					"json"
				}); */
				
				}else{
				$("#save").submit();//国内保存修改
				}		
		
		
		
		
		/* <c:if test="intlflag=='intl'}">
			parent.window.hideS(passid);
		</c:if> */
			
		
		
		}
	</script>
	<script type="text/javascript">
	   function back(){
	   window.history.back(-1); 
	   }
	
	   $(function (){
	   var passtype="${onePass.passengerType}";
	   if(passtype=='1'){
	   $("#type").html("成人");
	   }else if(passtype=='2'){
	   $("#type").html("儿童");
	   }else $("#type").html("婴儿");
	   
	   var idtype="${onePass.idtype}";
	   if(idtype=='1'){
	   $("#idtype").html("身份证");
	   }else if(idtype=='2'){
	   $("#idtype").html("因公护照");
	   }else if(idtype=='3'){
	   $("#idtype").html("海员证");
	   }else if(idtype=='4'){
	   $("#idtype").html("回乡证");
	   }else if(idtype=='5'){
	   $("#idtype").html("军官证");
	   }else if(idtype=='6'){
	   $("#idtype").html("因公港澳通行证");
	   }else if(idtype=='7'){
	   $("#idtype").html("台胞证");
	   }else if (idtype=='99'){
	   $("#idtype").html("其他");
	   }
	   if(idtype==""){
	   $("#passidtype").val("1");
	   }else
	   $("#passidtype").val(idtype);
	   });
	
	
	
	
	
	</script>
</html>