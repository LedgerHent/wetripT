<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/hotelHtml5/jquery-1.8.2.min.js"></script>
	<script type="text/javascript"  src="${pageContext.request.contextPath}/js/MyConfirm.js"></script>
	<script type="text/javascript">
		var hasNextPage = '${page.hasNextPage }';
		parent.setHasNetPage(hasNextPage);
	</script>
</head>

<body>
<!----待审核流程----->
	<div class="bounced proces">
		<div id="proces" style="width: 96%;position: absolute;left: 50%;top: 50%;margin-left: -48%;margin-top: -10%;background: #FFFFFF;">
			<div style="position: absolute;right: 1rem;top: 1rem;">
				<img src="../hotelimg/close(1).png"/ style="width: 1.4rem;height: 1.4rem;">
			</div>
			<ul style="padding: 1rem;" id="showProcess">
					
				</ul>
			
			<%-- <div style="line-height: 4.5rem;background: url(${pageContext.request.contextPath}/hotelimg/icons.png) no-repeat 1rem center;background-size: 0.7rem 9.7rem;font-size: 1.2rem;text-indent: 2rem;">
				<div style="color: #333333;">
					<span>张三</span>
					<span>(审批通过)</span>
				</div>
				<div style="display: flex;flex-direction: row;justify-content: flex-start;">
					<p style="color: #333333;">
						张三芬(审批通过)
					</p>
					<p style="color: #fc5b5b;">
						杜海燕(待审批)
					</p>
				</div>
				<div style="color: #00AFEC;">
					<span>晨曦娜</span>
					<span>(未审批)</span>
				</div>
			</div> --%>
		</div>
	</div>
<c:forEach items="${page.list }" var="info">
	<li onclick="toOrderDetail('${info.orderNo }')">
		<div class="enter_date">
			<div class="enter_left">
				<p class="htl">${info.hotelName }</p>
				<div class="inter_">
					<p>
						<span><fmt:formatDate value="${info.checkInDate }" pattern="MM月dd日"/></span> 至
						<span><fmt:formatDate value="${info.checkOutnDate }" pattern="MM月dd日"/></span>
					</p>
					<p>${info.roomTypeName }</p>
					<p>${info.roomNum }间</p>
				</div>
			</div>
			<div class="enter_right">
				<p class="audit sh <c:if test="${info.orderStateName eq '待审核'}">dsh</c:if>" vlaue="${info.orderNo}" <c:if test="${info.orderStateName eq '待审核'}">onclick="outApproval('${info.orderNo}')"</c:if>><span>${info.orderStateName }</span>
					<c:if test="${info.orderStateName eq '待审核'}">
						<img src="${pageContext.request.contextPath}/hotelimg/tips.png"/ style="width:1.05rem;height:1.05rem;margin-left:0.5rem">
						<input type="hidden" vlaue="${info.orderNo}" class="fs" />
					</c:if>
				</p>
				<p class="pri_">¥<fmt:formatNumber type="number" groupingUsed="false" value="${info.orderTotalAmount }" maxFractionDigits="0"/></p>
<!-- 				<p class="countdown">
					<span>支付倒计时</span>
					<span class="time_"></span>
				</p> -->
			</div>
		</div>
		<div class="due">
			<p>
				<span>订单号：</span>
				<span>${info.orderNo }</span>
			</p>
			<p>
				<span>预订日期：</span>
				<span><fmt:formatDate value="${info.bookingDate }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
			</p>
		</div>
	</li>
</c:forEach>
</body>

<script>
	$('.dsh').click(function(e){
		e.stopPropagation();
		$('.proces').fadeIn();
	});
	
	$('#proces img').click(function(){
	    $("#showProcess").empty();
		$('.proces').fadeOut();
	});
	
	function outApproval(order){
	   $.ajax({
			    type: 'POST',
			    url:"${pageContext.request.contextPath}/hotelOrder/listProcess.act",
			    data:{"orderNo":order},
			    async:false,
			    success: function(result) {
			     if(result!=null){
					showListProcess(result);
			   		}
	   			 }
					}); 
	
	}
	
	   var str="";
	function showListProcess(order){
	   var mode="";
	   if(order.mode == 1){
	      mode="或";
	   }else if(order.mode ==2){
	      mode="和";
	   }
	   
	   str="";
	   if(order){
	     for(var idx in order.approvals){
	       str+="<li style='display: flex;flex-direction:row;justify-content: flex-start;margin-bottom:0;'><div style='margin-top: 0.2rem;'>";
	       if(order.approvals[idx].approveLevel == (idx*1+1)){
	          if(order.approvals[idx].state == 1 || order.approvals[idx].state == 2){
	            if(idx == (order.approvals.length-1)){
	               str+="<img src='${pageContext.request.contextPath}/hotelimg/dot_r.png'/>";
	            }else{
	               str+="<img src='${pageContext.request.contextPath}/hotelimg/red.png'/>";
	            }
	          }else if(order.approvals[idx].state == 11){
	             if(idx == (order.approvals.length-1)){
	               str+="<img src='${pageContext.request.contextPath}/hotelimg/dot_b.png'/>";
	            }else{
	               str+="<img src='${pageContext.request.contextPath}/hotelimg/black.png'/>";
	            }
	          
	          }
	          str+="</div>";
	          for(var ids in order.approvals[idx].auditors){
	             if(order.approvals[idx].auditors[ids].state == 11){
	               str+="<p style='color: #333333;'>"+order.approvals[idx].auditors[ids].name+"(已通过)</p>";
	               if(order.approvals[idx].auditors.length>1 && ids>=0 && ids<order.approvals[idx].auditors.length-1 && order.mode != 1){
	               str+=mode;
	               }
	             }else if(order.approvals[idx].auditors[ids].state == 1 && order.approvals[idx].state != 11){
	               str+="<p style='color: #FF0000;'>"+order.approvals[idx].auditors[ids].name;
	               if(order.approvals[idx].state == 1 || order.approvals[idx].state == 2){
	                   str+="(未审批)</p>";
				   }
	                if(order.approvals[idx].auditors.length>1 && ids>=0 && ids<order.approvals[idx].auditors.length-1){
	               str+=mode;
	               }
	             }else if(order.approvals[idx].auditors[ids].state == 2){
	               str+="<p style='color: #00AFEC;'>"+order.approvals[idx].auditors[ids].name+"(未审批)</p>";
	                if(order.approvals[idx].auditors.length>1 && ids>=0 && ids<order.approvals[idx].auditors.length-1){
	                str+=mode;
	               }
	             }
	          }
	          str+="</li>";
	       }
	     }
	   }
	   $("#showProcess").append(str);
	}
</script>
</html>