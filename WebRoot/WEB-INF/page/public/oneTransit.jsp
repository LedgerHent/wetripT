<!DOCTYPE html>

<html>

	<head>
	<%String path=request.getContextPath(); %>
	<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
	<%@ taglib  uri="http://java.sun.com/jstl/core" prefix="c" %> 

		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no" />
		<title>单程中转订单详情</title>
		<link rel="stylesheet" type="text/css" href="../css/allOrder.css" />
	</head>

	<body>
		<div class="bounced cancleBtn">
			<div class="tips-order">
				<div class="cancal-title">
				<div class="icon-tips">
					<img src="../img/tips.png"/>
				</div>
				<div class="tips-content">
					<p class="tips-top">取消订单</p>
					<p class="tips-bot">一旦取消将无法恢复</p>
				</div>
			</div>
				<div class="reason">
				<div class="reaon-can">
					取消理由：
				</div>
				<textarea rows="10" cols="30" style="max-width: 3.5rem; max-height: 1.8rem;" name="cancel_reason" id="cancel_reason">请填写取消理由</textarea>
				<div class="reason-true">
					<div class="true-tips1">确定</div>
				</div>
			</div>
				<div class="closed-tips">
					<a href="javascript:;">关闭</a>
				</div>
			</div>
		</div>
		<div class="bounced resoultlBtn">
			<div class="tips-order">
				<div class="cancal-title">
				<div class="icon-tips">
					<img src="../img/tips.png"/>
				</div>
				<div class="tips-content">
					<p class="tips-top">拒绝订单</p>
					<p class="tips-bot">一旦取消将无法恢复</p>
				</div>
			</div>
				<div class="reason">
				<div class="reaon-can">
					拒绝理由：
				</div>
				<textarea rows="10" cols="30" style="max-width: 3.5rem; max-height: 1.8rem;" name="refuse_reason" id="refuse_reason">请填写拒绝理由</textarea>
				<div class="reason-true">
					<div class="true-tips2">确定</div>
				</div>
			</div>
				<div class="closed-tips">
					<a href="javascript:;">关闭</a>
				</div>
			</div>
		</div>
		<section>
			<header>
				<div>
					<img src="../img/header.gif" />
				</div>
				<p class="zhixiang">订单号20170224A308</p>
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
						<img src="../img/luxian.png" / class="line">
						<div class="right">
							<div class="r-top">
								<h5>北京</h5>
								<h5>首都机场T1</h5>
							</div>
							<div class="r-middle">
								<span>
									<img src="../img/type.png"/ >
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
							<img src="../img/zhuan.png" />
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
							<img src="../img/luxian.png" / class="line">
							<div class="right">
								<div class="r-top">
									<h5>北京</h5>
									<h5>首都机场T1</h5>
								</div>
								<div class="r-middle">
									<span>
									<img src="../img/type.png"/ >
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
						<img src="../img/xiala.png" / class="img">
					</div>
				</div>
			</div>
			<div class="message">
				<div class="list bor">
					<span class="gay">乘机人1</span>
					<span class="name">田晓</span>
					<span class="phone" style="margin-left: 1rem;">185123456789</span>
				</div>
				<div class="list">
					<span class="gay">乘机人2</span>
					<span class="name">田晓雨</span>
					<span class="phone" style="margin-left: 0.72rem;">185123456789</span>
				</div>
			</div>
			<div class="cost">
				<p class="gay">应付金额<span class="money">¥1458</span></p>
				<p class="gay">机票<span>¥659*2</span></p>
				<p class="gay">税费<span>¥50*2</span></p>
				<p class="gay">保险<span>¥20*2</span></p>
			</div>
			<div class="message">
				<div class="list bor">
					<span class="gay">出行方式</span>
					<span class="name1">因公出行</span>
				</div>
				<div class="list">
					<span class="gay">支付方式</span>
					<span class="name1">预付款支付</span>
				</div>
			</div>
			<div class="message">
				<div class="list">
					<span class="gay">通知人</span>
					<span class="name">田晓</span>
					<span style="margin-left: 1rem;">185123456789</span>
				</div>
			</div>
			<div class="message">
				<div class="list">
					<span class="gay">审核人</span>
					<span class="name">李秀东</span>
					<span style="margin-left: 0.72rem;">185123456789</span>
				</div>
			</div>
			<footer class="footer-transit">
	
			
				<div class="audit-list" style="display: none;">审核通过</div>
			
				<div class="refused-list" style="display: none;">拒绝通过</div>
		
				<div class="cancel-list" style="display: none;">取消订单</div>
			</footer>
		</section>
	</body>
	<script type="text/javascript" src="../libs/jquery.min.js"></script>
	<script type="text/javascript" src="../libs/gublic.js"></script>
	<script type="text/javascript">
		(function() {
			var eleBtn = document.querySelector('.closed'),
				eleMore = document.querySelector('.folding');
			eleImg = document.querySelector('.img');
			var display = false;

			eleBtn.onclick = function() {
				display = !display;
				eleMore.style.height = display ? "3rem" : "0px";
				eleBtn.innerHTML = display ? "收起" : "隐藏";
				eleImg.src = display ? "../img/dakai.png" : "../img/xiala.png";
				return false;
			};
		})();
		
	$(function(){
	
	orderId=62373;
	parm = "&orderId="+orderId + "&type=3";
 	htmlobj =$.ajax({type:'POST',url: "../auditorder/auditorder.act",dataType:"text",data:"parm="+parm, async: false});
 	var noconfirm = htmlobj.responseText;
 	
 	//审核订单按钮权限
 	if(noconfirm.substring(0, 1)  =="1"){
 	
 		
             $(".refused-list").show();	
   			 $(".audit-list").show();
 	}
 	//取消订单按钮权限
 	if(noconfirm.substring(1, 2)  =="1"){
 			 $(".cancel-list").show();		
 	}
})


	</script>

</html>