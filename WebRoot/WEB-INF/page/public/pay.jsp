<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="../public/tag.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
		<base href="<%=basePath%>">
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no" />
		<title>机票支付</title>
		<link rel="stylesheet" type="text/css" href="css/allOrder.css" />
		<link rel="stylesheet" type="text/css" href="css/MyConfirm.css">
		<script type="text/javascript" src="libs/jquery.min.js"></script>
	</head>

	<body onpagehide="$.fn.LoadingHide()">
	<div id="container">
		<section>
			<header>
				<div>
					<img src="img/header.gif" onclick="toSearch()"/>
				</div>
				<p class="zhixiang">温馨提示</p>
			</header>
			<div class="status">
				<div class="orderStatus">
					<div class="orderImg">
						<img src="img/success.png" />
					</div>
					<div class="orderContent">
						<p>订单提交成功</p>
						<p>
						    <c:if test="${result.data.data.orderStatus=='1' }">待审核</c:if>
						    <c:if test="${result.data.data.orderStatus=='2' }">待支付</c:if>
						    <c:if test="${result.data.data.orderStatus=='3' }">已审核</c:if>
						    <c:if test="${result.data.data.orderStatus=='4' }">已支付</c:if>
						    <c:if test="${result.data.data.orderStatus=='5' }">已取消</c:if>
						    <c:if test="${result.data.data.orderStatus=='7' }">已删除</c:if>
						    <c:if test="${result.data.data.orderStatus=='8' }">补录</c:if>
						    <c:if test="${result.data.data.orderStatus=='9' }">已提交</c:if>
						</p>
					</div>
				</div>
				<div class="orderMessage">
					<div class="orderNum">
						<span>订单号:${result.data.data.orderNo }</span>
						<a href="javascript:;" id="toOrderDetail">查看详情>></a>
					</div>
					<div class="orderPrice">
						<span class="payble">应付金额：</span>
						<span class="payMoney" >${result.data.data.amount }元</span>
					</div>
					<div style="text-align: center;">
						<span class="contains" >(含服务费：<span style="color:#ff7e00;">${result.data.data.serviceFee }元</span>)</span>
					</div>
					<div class="countdown">
						<span>支付倒计时：</span>
						<%--<span class="payCount">${countDownTimeStr }</span>--%>
						<mt:countDown countdown="${countDownTimeStr}" init="true" />
					</div>
				</div>

			</div>
			<c:if test="${result.data.data.orderStatus=='2'}">
			<%--<div class="content-pay">
				<ul class="pay-type">
					<c:choose>
						<c:when test="${1==isWechat}">
							&lt;%&ndash;<li class="weixin pay">
								<span class="pay">微信支付</span>
								<img src="img/wei.png" id="weixinpay" url="pay/wxpay.act"/>
							</li>&ndash;%&gt;
						</c:when>
						<c:otherwise>
							<li class="treasure pay">
								<span class="pay">支付宝支付</span>
								<img src="img/choose.png" id="alipay" url="pay/alipay.act"  />
							</li>
							<li class="weixin pay">
								<span class="pay">微信支付</span>
								<img src="img/wei.png" id="weixinpay" url="pay/wxpay.act"/>
							</li>
						</c:otherwise>
					</c:choose>

				</ul>
			</div>--%>
			<c:choose>
				<c:when test="${1==isWechat}">
					<%--<li class="weixin pay">
						<span class="pay">微信支付</span>
						<img src="img/wei.png" id="weixinpay" url="pay/wxpay.act"/>
					</li>--%>
				</c:when>
				<c:otherwise>
					<div class="content-pay">
						<ul class="pay-type">
							<li class="treasure pay">
								<span class="pay">支付宝支付</span>
								<img src="img/choose.png" id="alipay" url="pay/alipay.act"  />
							</li>
							<li class="weixin pay">
								<span class="pay">微信支付</span>
								<img src="img/wei.png" id="weixinpay" url="pay/wxpay.act"/>
							</li>
						</ul>
					</div>
				</c:otherwise>
			</c:choose>

			<footer class="footer-pay">
				<c:choose>
					<c:when test="${1==isWechat}">
						<div class="btn" id="wechat">微信支付</div>
					</c:when>
					<c:otherwise>
						<div class="btn" id="alipayid">支付</div>
					</c:otherwise>
				</c:choose>
			</footer>
			</c:if>
		</section>
		<form id="subForm" action="order/detail.act" method="post">
		    <input type="hidden"  name="orderId" value="${result.data.data.orderId}" />
		</form>
		<form id="payForm" action="pay/alipay.act" method="post">
		    <input type="hidden" id="orderId" name="orderId" value="${result.data.data.orderId}" />
		    <input type="hidden"  name="price" value="${result.data.data.amount}" />
		    <input type="hidden"  name="title" value="订单支付" />
		    <input type="hidden"  name="body" value="订单号:${result.data.data.orderNo }" />
		</form>
		</div>
	</body>

	<script type="text/javascript" src="libs/gublic.js"></script>
	<script type="text/javascript" src="js/page/DateUtil.js?_=<%=Math.random()%>"></script>
	<script type="text/javascript" src="js/MyConfirm.js"></script>
	<script type="text/javascript" src="js/page/comm.js"></script>
	<script type="text/javascript">
	/*-----页面加载头部----------*/
	var paymethod="alipay";
	var alipayURL = "pay/alipay.act";
	var weixinpayURL = "pay/wxpay.act";
	
	$("#header").load("header.html", function(data) {
		$('.homePage img').attr('src', '../img/header.gif')
		$('.myPerson').css('display', 'none');
		$('.zhixiang').html('温馨提示');
		$('header div img').on('click', function() {
			window.history.back(-1);
		});
	});
		(function($) {
			$(document).ready(function() {
				$('.pay-type img').click(function() {
					$(this).attr('src', $(this).attr('src') == 'img/choose.png' ? 'img/wei.png' : 'img/choose.png');
					$(this).parent().siblings().find('img').attr('src', $(this).attr('src') == 'img/choose.png' ?'img/wei.png':'img/choose.png' );
					 
					if($(this).attr('src') == 'img/choose.png'){
						paymethod=$(this).attr("id");
						$("#payForm").attr("action",$(this).attr("url"));
					}else{
						paymethod = $(this).parent().siblings().find('img').attr('id');
						$("#payForm").attr("action",$(this).parent().siblings().find('img').attr("url"));
					}
				});
				if('${result.data.data.orderStatus}'!='2'){
					$(".pay-type").hide();
					$(".countdown").hide();
				}
			})
		})(jQuery);
$('#toOrderDetail').on('click',function(){
	$("#subForm").submit();
});
function toSearch(){
	$("#subForm").attr("action","flight/search.act");
	$("#subForm").submit();
}
/*----倒计时的效果--------*/
var SysSecond;
var InterValObj;


function p(s) {
	return s < 10 ? '0' + s : s;
};
var url = window.location.href; //获取当前页面的url
var len = url.length; //获取url的长度值
var a = url.indexOf("?"); //获取第一次出现？的位置下标
var b = url.substr(a + 1, len); //截取问号之后的内容
var c = b.split("&"); //从指定的地方将字符串分割成字符串数组	
var arr = new Array(); //新建一个数组
for(var i = 0; i < c.length; i++) {
	var d = c[i].split("=")[1]; //从=处将字符串分割成字符串数组,并选择第2个元素			
}
//$('.payMoney').html(c[0].split("=")[1]+'元');
$('.cn-pay .number1').html('¥' +c[0].split("=")[1] )



$("#alipayid").click(function(){
	if(paymethod=='alipay'){
	   // window.location.href="pay/alipay.act?orderId=${result.data.data.orderId}&price=${result.data.data.amount}&title=订单支付&body=订单号:${result.data.data.orderNo }"
	   $("#payForm").submit();
	}else if(paymethod=='weixinpay'){
		//showMsg("微信支付接口维护中！");
		$("#payForm").submit();
	}
})

	</script>
<c:if test="${1==isWechat}">
	<script>
        $("#wechat").click(function(){//微信公众号支付
            $(document).LoadingShow();
            $.post(
                "${ctx}/pay/JSAPI.act",
                $("#payForm").serialize(),
                function (data) {
                    $(document).LoadingHide();
                    if(data && data.code==1){
                        WeixinJSBridge.invoke(
                            'getBrandWCPayRequest', {
                                "appId":data.data.appId,     //公众号名称，由商户传入
                                "timeStamp":data.data.timeStamp,         //时间戳，自1970年以来的秒数
                                "nonceStr":data.data.nonceStr, //随机串
                                "package":data.data.package,
                                "signType":data.data.signType,         //微信签名方式：
                                "paySign":data.data.paySign //微信签名
                            },
                            function(res){
                                if(res.err_msg == "get_brand_wcpay_request:ok" ) { // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
									window.location.href = "pay/jsapi_rtn.act?orderId=" + $("#orderId").val();
								}
                            }
                        );
                    }else{
                        showMsg(data.msg);
                    }
                },
				"json"
            );
        })
	</script>
</c:if>

</html>