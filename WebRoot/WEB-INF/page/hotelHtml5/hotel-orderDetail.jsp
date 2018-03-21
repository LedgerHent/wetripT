<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="../public/tag.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
		<base href="<%=basePath%>">
		<meta charset="UTF-8">
		<title>订单详情</title>
		<style>
			#charges{background:#ffffff;border-radius:0.5rem;line-height:2rem;font-size:1.2rem;width:60%;position:absolute;left:50%;top:50%;margin-left:-30%;padding:1rem 0;text-indent:1rem}
		</style>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/hotelHtml5/index.css" />
	
	</head>

	<body>
	
	<!----待审核流程----->
	<c:if test="${result.orderDetail.orderStatusName eq '待审核'}">
	<div class="bounced proces">
		<div id="proces" style="width: 96%;position: absolute;left: 50%;top: 50%;margin-left: -48%;margin-top: -10%;background: #FFFFFF;">
			<div style="position: absolute;right: 1rem;top: 1rem;">
				<img src="<%=basePath%>/hotelimg/close(1).png"/ style="width: 1.4rem;height: 1.4rem;">
			</div>
			<ul style="padding: 1rem;">
			<c:choose>
			  <c:when test="${result1.mode eq 1 }">
			    <c:set var="zi" value="或"></c:set>
			  </c:when>
			  <c:when test="${result1.mode eq 2 }">
			  <c:set var="zi" value="和"></c:set>
			  </c:when>
			</c:choose>
			<c:forEach items="${result1.approvals}" var="approves" varStatus="star">
			<c:if test="${approves.approveLevel eq (star.index+1) }">
			        <li style="display: flex;flex-direction:row;justify-content: flex-start;">
						<div style="margin-top: 0.2rem;">
						  <c:choose>
						    <c:when test="${approves.state eq 1 or approves.state eq 2}">
						    <c:if test="${star.last }">
						    <img src="<%=basePath%>/hotelimg/dot_r.png"/>
							</c:if>
							<c:if test="${!star.last}">
							<img src="<%=basePath%>/hotelimg/red.png"/>
							</c:if>
							</c:when>
							<c:when test="${approves.state eq 11}">
							<c:if test="${star.last }">
							 <img src="<%=basePath%>/hotelimg/dot_b.png"/>
							</c:if>
							<c:if test="${!star.last }">
							<img src="<%=basePath%>/hotelimg/black.png"/>
							</c:if>
							</c:when>
						  </c:choose>
						</div>
						<c:forEach items="${approves.auditors }" var="people" varStatus="num">
						  <c:choose>
			              <c:when test="${people.state eq 11}">
						<p style="color: #333333;">${people.name }(已通过)</p>
						<c:if test="${approves.auditors.size() gt 1 && num.index ge 0 && num.index lt approves.auditors.size()-1 && result1.mode ne 1}">
						  ${zi }
						</c:if>
						</c:when>
						 <c:when test="${people.state eq 1 and approves.state ne 11}">
						<p style="color: #FF0000;">${people.name }<c:if test="${people.state eq 1 and approves.state eq 1 or approves.state eq 2}">(未审批)</c:if>
						</p>
						<c:if test="${approves.auditors.size() gt 1 && num.index ge 0 && num.index lt approves.auditors.size()-1}">
						  ${zi }
						</c:if>
						
						</c:when>
						 <c:when test="${people.state eq 2}">
						<p style="color: #00AFEC;">${people.name }(未审批)</p>
						
						<c:if test="${approves.auditors.size() gt 1 && num.index ge 0 && num.index lt approves.auditors.size()-1}">
						  ${zi }
						</c:if>
						</c:when>
						</c:choose>
						</c:forEach>
					</li>
			</c:if>
					</c:forEach>
					
				</ul>
		</div>
	</div>
</c:if>
	
	
	
	
	
	<!-- 不可取消布局----->
	<div class="bounced charges">
			<div id="charges">
				<p>${result.orderDetail.tmcOrderHotel.hotelPolicyDesc }</p>
			</div>
	</div>
	

	<div class="bounced because">
		<div id="because">
			<p>驳回原因驳回原因驳回原因驳回原因驳回原因</p >
		</div>
	</div>
	<!-- 驳回原因的布局--- -->
		<div class="bounced dismiss">
			<div id="reject">
				<div class="reject_reason">驳回原因</div>
				<div style="padding: 1rem;">
					<textarea name="a" placeholder="驳回订单的理由" class="reason" id="cancel_reason" style="border-radius: 0.5rem; font-size:1.2rem; text-indent: 0.5rem;width: 100%;height: 7rem;resize: none;"></textarea>
				</div>
				<div class="action_btn">
					<div class="cancel_btn">
						<a href="javascript:;">取消</a>
					</div>
					<div>
						<a href="javascript:;" class="qd" onclick="refuse()">确定</a>
					</div>
				</div>
			</div>
		</div>
		<section>
			<header class="travel_title">
				<div class="top_m">
					<div>
						<img src="<%=basePath%>/hotelimg/header.png" / class="you" onclick="history.back();">
					</div>
					<p>${result.orderDetail.tmcOrderHotel.hotelName }</p>
				</div> 
				<div id="orderNs">
					<div class="orderN">
						<div class="order_l">
							<div>
								<span>订单号</span>
								<span>${result.orderDetail.orderNo }</span>
							</div>
							<div>
								<span>预定日期:</span>
								<span>${mt:fmtByStringStyle(result.orderDetail.bookingTimeStr,'yyyy-MM-dd')}</span>
							</div>
						</div>
						<div class="audit_">${result.orderDetail.orderStatusName }
						<c:if test="${result.orderDetail.orderStatusName eq '待审核'}">
					<img src="<%=basePath%>/hotelimg/tips.png"/ style="width:1.05rem;height:1.05rem;margin-left:0.5rem">
					</c:if>
						
						</div>
					</div>
					<div class="order_l">
						<span>预订人:</span>
						<span>${result.orderDetail.bookingUserName}</span>
					</div>
				</div>
			</header>
			<div class="hotel_news smallType">
				<div class="news_t">
					<div style="flex: 1;margin-right: 2rem;">
						<div class="grade">
							<p class="hotel_">${result.orderDetail.tmcOrderHotel.hotelName }</p>
							<p class="protocol">
							<c:choose>
								<c:when test="${result.orderDetail.choiceAgreementHotelFlag =='0002400001'}">(精选酒店)</c:when>
								<c:when test="${result.orderDetail.choiceAgreementHotelFlag =='0002400002'}">(协议酒店)</c:when>
								<c:otherwise></c:otherwise>
							</c:choose>
							</p>
							<div class="scoring">
							<c:if test="${result.orderDetail.tmcOrderHotel.star =='1'}"><span>★</span></c:if>
							<c:if test="${result.orderDetail.tmcOrderHotel.star =='2'}"><span>★</span><span>★</span></c:if>
							<c:if test="${result.orderDetail.tmcOrderHotel.star =='3'}"><span>★</span><span>★</span><span>★</span></c:if>
							<c:if test="${result.orderDetail.tmcOrderHotel.star =='4'}"><span>★</span><span>★</span><span>★</span><span>★</span></c:if>
							<c:if test="${result.orderDetail.tmcOrderHotel.star =='5'}"><span>★</span><span>★</span><span>★</span><span>★</span><span>★</span></c:if>
							</div>
						</div>
						<div style="display:flex;flex-direction:row;" onclick="tomap()">
							<div style="margin:auto 0;"><img src="<%=basePath%>/hotelimg/dingwei.png" / class="dingwei"></div>
						<p class="adress_ht" style="margin-bottom:0;">${result.orderDetail.tmcOrderHotel.hotelAddress }</p>
						</div>
						<input type="hidden" id="hotelid" value="${result.orderDetail.tmcOrderHotel.ptHotelId }">
					</div>
					<div class="click_d">
					<c:set value="${result.orderDetail.tmcOrderHotel.ptHotelId }" var="hotelid"></c:set><!-- 酒店ID -->
						<a href="<c:url value="hotel/introduce.act?ptHotelId=${hotelid}"/>">详情/设施</a>
						<img src="<%=basePath%>/hotelimg/you.png" / class="you">
					</div>
				</div>
				<div class="news_t">
					<div>
						<p class="h_hotel">${result.orderDetail.tmcOrderHotel.roomTypeName }    ${result.orderDetail.tmcOrderHotel.rpName }</p>
						<div class="date_">
							<div>
								<span>${mt:fmtByStringStyle(result.orderDetail.tmcOrderHotel.actualCheckinDateStr,'MM月dd日')}</span> -
								<span>${mt:fmtByStringStyle(result.orderDetail.tmcOrderHotel.actualCheckoutDateStr,'MM月dd日')}</span>
							</div>
							<div style="margin: 0 0.5rem;">
								<span>共${result.orderDetail.nithtCount }晚*${result.orderDetail.tmcOrderHotel.roomNum }间</span>
							</div>
							<div>
							<c:if test="${result.orderDetail.tmcOrderHotel.isBreakfast=='1' }">含早</c:if>
							<c:if test="${result.orderDetail.tmcOrderHotel.isBreakfast=='0' }">无早</c:if>
							</div>
						</div>
						<div class="date_">
							<p style="color:#333333;line-height:2rem">
							<c:if test="${result.orderDetail.tmcOrderHotel.hotelPolicy == '0'}">收费取消</c:if>
							<c:if test="${result.orderDetail.tmcOrderHotel.hotelPolicy == '1'}">限时免费取消</c:if>
							<c:if test="${result.orderDetail.tmcOrderHotel.hotelPolicy == '2'}">不可取消</c:if>
							</p>
							<img src="<%=basePath%>/hotelimg/tips.png" / class="tips" style="padding-top:0;">							
						</div>
					</div>
					<div class="r">
						<span>单价:</span>
						<span class="price_">¥${mt:formatPrice(result.orderDetail.tmcOrderHotel.bookingPrice) }</span>
					</div>
				</div>
			</div>
			<div id="" class="smallType">
				<c:forEach begin="1" end="${result.orderDetail.tmcOrderHotel.roomNum }" var="s"> 
					<div class="enter_click">
						<div class="enter_r">
						<p>房间${s}</p>
						<div>
							<span class="name">${guest.guestName}</span>
							<img src="<%=basePath%>/hotelimg/btm.png"/  style="width: 1.8rem;height: 1.05rem;">
						</div>
						</div>
					<ul class="check_list">
					<c:forEach items="${result.orderDetail.tmcOrderGuestList }" var="guest" varStatus="member">
					<c:if test="${guest.roomNo ==s}">
						<li>
							<div class="f">
								<div>
									<span class="w stay">姓名</span>
									<span class="enter_name">${guest.guestName}</span>
								</div>
							</div>
							<div>
								<span class="w ash">联系电话</span>
								<input type="text" name="" id="" value="${guest.tel }" class="inputs" readonly="readonly">
							</div>
							<div>
								<span class="w ash">电子邮箱</span>
								<input type="text" name="" id="" value="${guest.email }" class="inputs" readonly="readonly">
							</div>
						</li>
					</c:if>
					</c:forEach>
					</ul>
					</div>
				</c:forEach>
			</div>
			<div id="" class="smallType">
			<c:forEach items="${result.orderDetail.tmcOrderContactsList }" var="contact">
				<div class="contacts">
					<p>联系人
					<c:choose>
								<c:when test="${contact.seq =='0'}"></c:when>
								<c:otherwise>${contact.seq }</c:otherwise>
					</c:choose>
					</p>
					<div>
						<span class="name">${contact.contact }</span>
						<img src="<%=basePath%>/hotelimg/btm.png"/  style="width: 1.8rem;height: 1.05rem;">
					</div>
				</div>
				<ul class="check_list">
					<li>
						<div class="f">
							<div>
								<span class="w stay">姓名</span>
								<span class="contact_name">${contact.contact }</span>
							</div>
						</div>
						<div>
							<span class="w ash">联系电话</span>
							<input type="text" name="" id="" value="${contact.tel }" class="inputs" readonly="readonly">
						</div>
						<div>
							<span class="w ash">电子邮箱</span>
							<input type="text" name="" id="" value="${contact.email }" class="inputs" readonly="readonly">
						</div>
					</li>
				</ul>
				</c:forEach>
			</div>
			<div id="traApay" class="smallType">
				<div>
					<p class="aw">出行方式</p>
					<p>${result.orderDetail.tripTypeName }</p>
				</div>
				<div>
					<p class="aw">支付方式</p>
					<p>${result.orderDetail.payTypeName }</p>
				</div>
			</div>
			<div class="smallType price_l">
				<div class="allPrice">
					<p class="aw">总金额</p>
					<p>¥${mt:formatPrice(result.orderDetail.orderTotalAmount) }</p>
				</div>
				<div class="price_details">
					<%-- <div>
						<p class="aw">房间费</p>
						<p>¥${result.orderDetail.tmcOrderHotel.bookingPrice }*${result.orderDetail.tmcOrderHotel.roomNum }</p>
					</div>
					<div>
						<p class="aw">税费</p>
						<p>
						<c:choose>
						<c:when test="${result.orderDetail.tmcOrderHotel.taxFee =='0.0'}">
						¥${result.orderDetail.tmcOrderHotel.taxFee }
						</c:when>
						<c:otherwise>¥${result.orderDetail.tmcOrderHotel.taxFee }*${result.orderDetail.tmcOrderHotel.roomNum }</c:otherwise>
						</c:choose>
						</p>
					</div> --%>
					<div style="display:block;padding:0;">
					<c:forEach items="${result.orderDetail.tmcOrderDayPriceList }" var="dayprice">
					<c:set var="totalIn" value="${dayprice.price  + dayprice.tax }" />
					<div>
					<p class="aw">${mt:fmtByStringStyle(dayprice.priceDateStr ,'yyyy-MM-dd')}
					<c:choose>
						<c:when test="${dayprice.tax =='0.0'}">
						</c:when>
						<c:otherwise>含税</c:otherwise>
					</c:choose>
					</p>
					<p>¥${mt:formatPrice(totalIn) }*${result.orderDetail.tmcOrderHotel.roomNum }间</p>
					</div>
					</c:forEach>
					</div>
					<div>
						<p class="aw">预订费</p>
						<p>¥${mt:formatPrice(result.orderDetail.tmcOrderHotel.bookingFee) }</p>
					</div>
					<div>
						<p class="aw">夜间费</p>
						<p>¥${mt:formatPrice(result.orderDetail.tmcOrderHotel.nightFee) }</p>
					</div>
					<%--<div>
						<p class="aw">积分/利息优惠</p>
						<p>
						<c:choose>
						<c:when test="${result.orderDetail.interestDiscount=='0.0' && result.orderDetail.integralDiscount=='0.0'}">¥0.00</c:when>
						<c:otherwise>-¥${result.orderDetail.interestDiscount=='0.0'?'':mt:formatPrice(result.orderDetail.interestDiscount)}${result.orderDetail.integralDiscount=='0.0'?'':mt:formatPrice(result.orderDetail.integralDiscount)}</c:otherwise>
						</c:choose>
						</p>
					</div>
				--%></div>
			</div>
			<c:if test="${result.orderDetail.tripTypeName=='因公出行'}">
			<div id="projects" class="smallType">
				<div class="belonging">
					<div class="aw">
						<p>项目名称/编号</p>
					</div>
					<p style="margin: auto 0;">${result.orderDetail.projectName }</p>
				</div>
				<div class="belonging">
					<p class="aw">费用归属</p>
					<div>
					<c:forEach items="${result.orderDetail.tmcOrderCostShareList }" var="fee">
						<p>
							<span>${fee.shareDeptName }</span>
							<c:choose>
							<c:when test="${fee.shareValue >100}">
							
							<span class="share">分摊<fmt:formatNumber type="number" value="${fee.shareValue }" pattern="#"/>元</span>
							</c:when>
							<c:otherwise>
							<span class="share">分摊<fmt:formatNumber type="number" value="${fee.shareValue }" pattern="#"/>%</span>
							</c:otherwise>
							</c:choose>
						</p>
					</c:forEach>	
					</div>
				</div>
			</div>
			<div class="smallType travel_">
				<div class="tra">
					<p class="aw">差旅政策</p>
					<p>${result.orderDetail.tmcOrderTripPolicy.policyName }</p>
				</div>
				<div class="tra">
					<p class="aw">差旅标准</p> 
					<p>${result.orderDetail.tripPolicyText }</p>
				</div>
				<c:if test="${not empty result.orderDetail.limitMemo && result.orderDetail.limitMemo!=''&&result.orderDetail.limitMemo!=null}">
				<div class="tra">
					<p class="aw">超标原因</p>
					<p>${result.orderDetail.limitMemo }</p>
				</div>
				</c:if>
			</div>
			<div class="smallType">
				<c:if test="${not empty result1.approvals}">
				<div class="approve">审批列表</div>
				</c:if>
				<ul class="approve_item">
				<c:forEach items="${result1.approvals}" var="approve" varStatus="appr">
					<c:forEach items="${approve.auditors}" var="auditor" varStatus="coun">
					<c:if test="${auditor.state=='11'||auditor.state=='12'}">
					<c:if test="${auditor.operator.id!=null}">
					<li class="app_list">
						<p style="text-align: center;">${approve.approveLevel }</p>
						<p>${auditor.operator.name }</p>
						<p>
							<a href="javascript:;" id="appro">
							<c:if test="${auditor.state =='11'}">已通过</c:if>
							<c:if test="${auditor.state =='12'}">已拒绝</c:if></a>
						</p>
						<p>${mt:fmtBySStyle(auditor.datetime ,'yyyy-MM-dd HH:mm:ss')}</p>
					</li>
					</c:if>
					</c:if>
					</c:forEach>
				</c:forEach>
				</ul>
			</div>
			</c:if>
				<c:set value="01" var="auu"></c:set>
			<c:forEach items="${result1.approvals}" var="approver"  varStatus="a" >
					<c:forEach items="${approver.auditors}" var="result1" varStatus="b" >
					<c:if test="${approver.state=='1' or approver.state=='2' }">
						<c:if test="${result1.state=='1' or result1.state=='2' }">
							<c:if test="${user.userid==result1.id }">
					<c:set value="${result1}" var="au"></c:set>
					<c:if test="${auu=='01' }">
							
			<footer id="footer">
				<div class="pay_for">
					<a href="javascript:;" onclick="updateStatus()" flag='0'>审批通过</a>
				</div>
				<div class="cancel_order">
					<a href="javascript:;"  flag='1'>审批驳回</a>
				</div>
				
			</footer>
			<c:set value="02" var="auu"></c:set>
			</c:if>	
							</c:if>
						</c:if>
					</c:if>
					</c:forEach>
			</c:forEach>

		</section>
		
		<form id="form" action="<%=basePath%>hotelOrder/updatpro.act"  method="post">
			<input type="hidden" name="businessType" value="3" />
			<input type="hidden" name="orderno" id="orderno" value="${result.orderDetail.orderNo }" />
			<input type="hidden" name="audit.type" value="5" />
			<input type="hidden" name="audit.state" value="" id="state"/>
			<input type="hidden" name="audit.memo" value=""  id="memo"/>
			<input type="hidden" name="audit.id" id="id" value="${au.id }" />
			<input type="hidden" name="audit.name" id="name" value="${au.name }" />
			<input type="hidden" name="audit.mobile" id="mobile" value="${au.mobile }" />
			<input type="hidden" name="audit.email" id="email" value="${au.email }" />
			<input type="hidden" name="audit.operator.id" id="oid" value="${au.operator.id }" />
			<input type="hidden" name="audit.operator.name" id="oname" value="${au.operator.name }" />
			<input type="hidden" name="audit.operator.mobile" id="omobile" value="${au.operator.mobile }" />
			<input type="hidden" name="audit.operator.email" id="oemail" value="${au.operator.email }" />
		</form>
		<form id="lform" action="<%=basePath%>hotel/map.act"  method="post">
			<input id="lat" type="hidden" value="" name="latitude">
			<input id="lon" type="hidden" value="" name="longitude">
		</form>
	</body>
	<script type="text/javascript" src="js/hotelHtml5/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.cookie.js"></script>
	<script type="text/javascript" src="js/hotelHtml5/hotelSearch/hotelSearch.js"></script>
	<script type="text/javascript"  src="${pageContext.request.contextPath}/js/MyConfirm.js"></script>
	<script type="text/javascript">
	
		/*审批驳回数据*/
		$(function(){
			$('.app_list').find('#appro').each(function(){
				var appro=$(this).html().replace(/\s+/g,"");;
				if(appro == '待审批'){
					$(this).addClass('reject');
				}
			})
		});
		var base = "${applicationScope.ctx}";
		/*提示信息*/
		function showMessage(meg){
			$(this).MyConfirm({
				content:meg
			},function callback(isConfirm){
	    		if(isConfirm){
	    			window.location.href = "${pageContext.request.contextPath}/orderInfo/listApprovalPendingOrderInit.act";
		  			}
	    	});
		}
		/*审核通过
		*/
			var orderno=$("#orderno").val();
			var id=$("#id").val();
			var name=$("#name").val();
			var mobile=$("#mobile").val();
			var email=$("#email").val();
			var oid=$("#oid").val();
			var oname=$("#oname").val();
			var omobile=$("#omobile").val();
			var oemail=$("#oemail").val();
		function updateStatus(){
			$.ajax({
			    type: 'POST',
			    url:base+"/hotelOrder/updatpro.act",
			    data:{"businessType":3,"orderno":orderno,"audit.type":5,"audit.state":11,"audit.memo":"","audit.id":id,"audit.name":name,"audit.mobile":mobile,"audit.email":email,"audit.operator.id":oid,"audit.operator.name":oname,"audit.operator.mobile":omobile,"audit.operator.email":oemail},
			    async:false,
			    success: function(result) {
			    	if(result=='0'){
			    		showMessage("审核通过操作成功！");
			    	}else{
			    		showMessage("审核通过操作失败！");
			    	}
	   			 }
					});
			//$("#form").submit();
			//showMessage("操作成功！");
		}
		/*审核拒绝*/
		function refuse(){
			var reason=$('#cancel_reason').val();
			$.ajax({
			    type: 'POST',
			    url:base+"/hotelOrder/updatpro.act",
			    data:{"businessType":3,"orderno":orderno,"audit.type":5,"audit.state":12,"audit.memo":reason,"audit.id":id,"audit.name":name,"audit.mobile":mobile,"audit.email":email,"audit.operator.id":oid,"audit.operator.name":oname,"audit.operator.mobile":omobile,"audit.operator.email":oemail},
			    async:false,
			    success: function(result) {
			    	if(result=='0'){
			    		showMessage("审核拒绝操作成功！");
			    		window.location.href = "${pageContext.request.contextPath}/orderInfo/listApprovalPendingOrderInit.act";
			    	}else{
			    		showMessage("审核拒绝操作失败！");
			    	}
	   			 }
					});
			//$("#form").submit();
		}
		/*跳转地图*/
		function tomap(){
			var hotelid=$("#hotelid").val();
			$.ajax({
			    type: 'POST',
			    url:base+"/hotelOrder/hotelid.act",
			    data:{"hotelId":hotelid},
			    async:false,
			    success: function(result) {
			    	var json = eval("("+result+")"); 
			     	var lat=json.lat;
			     	var lon=json.lon;
			     	$("#lat").val(lat);
			     	$("#lon").val(lon);
			     	$("#lform").submit();
	   			 }
					});
			
		}
		
		/*------入住人的效果-----*/
		$('.enter_r').click(function() {
			var arrs = [];
			$(this).siblings().find('.enter_name').each(function() {
				var html = $(this).html();
				arrs.push(html)
			})
			var en = $(this).siblings().find('.enter_name').html();

			if($(this).siblings().css('display') == "none") {
				$(this).find('img').attr('src', "<%=basePath%>/hotelimg/top.png");
				$(this).siblings().css('display', 'block');
				//$(this).find('.name').html('');
			} else {
				$(this).find('img').attr('src', "<%=basePath%>/hotelimg/btm.png");
				$(this).siblings().css('display', 'none');
				//$(this).find('.name').html(arrs[0]);
			}

			var name=$(this).find('.name').html();
			if(name==""){
				person();
			}else{
				$(this).find('.name').html('');
			}
		});
		
		function person(){
			var person="";
			$('.enter_click').each(function(){
				$(this).find('.enter_name').each(function(){
					person+=$(this).html()+" ";
				})
				$(this).find('.name').html(person);
				person="";
			})
		}
		
		$(function () {
			person();
		});
		/*----------联系人的样式------*/
		$('.contacts').click(function() {
			var en = $('.contact_name').html();

			if($(this).siblings().css('display') == "none") {
				$(this).find('img').attr('src', "<%=basePath%>/hotelimg/top.png");
				$(this).siblings().css('display', 'block');
				$(this).find('.name').html('');
			} else {
				$(this).find('img').attr('src', "<%=basePath%>/hotelimg/btm.png");
				$(this).siblings().css('display', 'none');
				$(this).find('.name').html(en);
			}

		});
		
		/*-----------审批驳回的效果---------*/
		  
		$('.cancel_order').click(function(){
			$('.dismiss').fadeIn();
			$("body,html").css({"overflow":"hidden"});
		});
		$('body').on('click','.reject',function(){
			$('.because').fadeIn();
			$("body,html").css({"overflow":"hidden"});
		});
		
		$('.cancel_btn').click(function(){
			$('.bounced').fadeOut();
			$("body,html").css({"overflow":""});
		});
		
		/* 不可取消的效果 */
		$('.date_ .tips').click(function(){
			$('.charges').fadeIn();
			$("body,html").css({"overflow":"hidden"});
		});
		$('.charges').click(function(){
			$('.bounced').fadeOut();
			$("body,html").css({"overflow":""});
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
			$("body,html").css({"overflow":""});
		}
		$('.dismiss').bind('click', function(e) {
			hideLayer(e, 'reject', '#reject');
		});
		$('.because').bind('click', function(e) {
			hideLayer(e, 'because', '#because');
		});
		
		$('.audit_').click(function(){
			$('.proces').fadeIn();
		});
		
		$('#proces img').click(function(){
			$('.bounced').fadeOut();
		})
		
	</script>

</html>