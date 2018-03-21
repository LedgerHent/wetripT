<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="../public/tag.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
	    <base href="<%=basePath%>">
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no" />
		<title><c:if test="${skipType=='1'}">添加乘机人</c:if>
			   <c:if test="${skipType=='2'}">添加通知人</c:if></title>
		<link rel="stylesheet" type="text/css" href="css/allOrder.css" />
	</head>
        <form id="subForm" action="flight/bookAirTicket.act" method="post">
	        <input type="hidden" id="bookinfo" name="bf" value='${bookinfo}' />
	        <input type="hidden" id="userType" name="userType" value='1' /><%--乘机人类型 ：0:企业员工   1:常旅客--%>
	        <input type="hidden" id="id" name="id" value='' />
	        <input type="hidden" id="userName" name="userName" value='' />
	        <input type="hidden" id="userIdType" name="userIdType" value='' />
	        <input type="hidden" id="userPhone" name="userPhone" value='' />
	        <input type="hidden" id="userId" name="userId" value='' />
            <input type="hidden" id="passengerType" name="passengerType" value='' /><%--乘机人类型 ：1:成人   2:儿童  3：婴儿--%>
            <input type="hidden" id="passengerId" name="passengerId" value='' />
            <input type="hidden" id="excessinfoStr" name="excessinfoStr" value="${excessinfoStr }" >
	    </form>
	<body onpagehide="$.fn.LoadingHide()">
		<div class="bounced doc-type">
			<div class="document-content" >
				<p class="document-title" >请选择一下证件类型：</p>
				<div id="type" class='certype'></div>	
				<div class="doc-btn">确认</div>
			</div>
		</div>
		<section>
			<header>
				<div>
					<img src="img/header.gif" onclick="history.go(-1);"/>
				</div>
				<p class="zhixiang"><c:if test="${skipType=='1'}">添加乘机人</c:if>
				                    <c:if test="${skipType=='2'}">添加通知人</c:if>
				</p>
				<div class="addBtn">
					<%--<a href="<%=path %>/ppp/addd.act"><img src="img/addBtn.png" /></a>--%>
					<img src="img/addBtn.png" id="addd"/>
				</div>
			</header>
			<div class="table-content">
				
				<c:if test="${skipType=='2' || (skipType=='1' && excessinfoStr=='')}">
					<div class="table-title">
						<p id="1" class="employees activeTable tableBtn" onclick="search(this)">企业员工</p>
						<p id="2" class="passener tableBtn" onclick="search(this)">常旅客</p>
					</div>
				</c:if>
				<c:if test="${skipType=='1' && excessinfoStr!=''}">
					<div class="table-title"> 
					<!-- 	<p id="1" class="employees  tableBtn"></p> -->
						<p id="2" class="passener activeTable tableBtn" onclick="search(this)">常旅客</p>
					</div>
				</c:if>
				 <ul class="passener-content emp-table" >
				 <div id="fuu">
				<%-- 			<c:forEach var="passenger" items="${results.data }">
					<li>
						<div class="left-passener" >
							<div class="middle-table">
								<div class="passener-name">${passenger.name }</div>
								<div class="passener-num" id="ceshi">
									<span>手机号</span>
									<span class="number-type">${passenger.mobile }</span>
								</div>
								<div class="passener-type">
								<c:if test="${passenger.ids[0].type==1 }"><span>身份证</span></c:if>
								<c:if test="${passenger.ids[0].type==2 }"><span>护照</span></c:if>
								<c:if test="${passenger.ids[0].type==3 }"><span>海员证</span></c:if>
								<c:if test="${passenger.ids[0].type==4 }"><span>回乡证</span></c:if>
								<c:if test="${passenger.ids[0].type==5 }"><span>军官证</span></c:if>
								<c:if test="${passenger.ids[0].type==6 }"><span>港澳通行证</span></c:if>
								<c:if test="${passenger.ids[0].type==7 }"><span>台胞证</span></c:if>
								<c:if test="${passenger.ids[0].type==99 }"><span>其他</span></c:if> 
								<span class="number-type">${passenger.ids[0].num }</span> 
								</div>
							</div>
							
							
						</div>
						<div class="right-table">
							<a href="<%=path %>/ppp/updatee.act"><img src="img/bianji.png" /></a>
						</div>
					</li>

							</c:forEach> --%>
							</div>
				</ul> 


				<ul class="passener-content pas-table">
					 <c:forEach items="${result.data.list }" var="staff" varStatus="status">
					<li id="certificate${status.count}" class="left-passener">
						<div class="left-passener" >
							<div class="middle-table">
								<div class="passener-name">
								    <input type="hidden" value="${staff.id}"><%--乘机人id--%>
								    <input type="hidden" value="${staff.name}"><%--乘机人名字--%>
								    <input type="hidden" value="${staff.mobile}"><%--乘机人手机号--%>
								    <input type="hidden" value="${staff.ids[0].type}"><%--乘机人证件号类型--%>
								    <input type="hidden" value="${staff.ids[0].num}"><%--乘机人证件号--%>
								    <input type="hidden" value="${staff.type}"><%--乘机人类型--%>
								    ${staff.name }
								</div>
								<input type="hidden" name="passengerId" value=""/>
								<div class="passener-num">
									<span>手机号</span>
									<span class="number-type">${staff.mobile }</span>
								</div>
								<input type="hidden" id="typeNum${status.count}" value="${fn:length(staff.ids)}"/>
								<input type="hidden"  value="${staff.type}"/>
								<input type="hidden"  id="platOrg" value="${staff.isplatFormOrg }" />
								<c:forEach items="${staff.ids }" var="ids" varStatus="statu">
									<input class="certifi${status.count}" type="hidden" 
										id="${status.count}typeNu${statu.count}" value="${ids.type}" />
									<input type="hidden" id="${status.count}userIdType${statu.count}" value="${ids.num}"/>
								</c:forEach>
								<div class="passener-type">
								<c:if test="${staff.ids[0].type==1 }"><span>身份证</span></c:if>
								<c:if test="${staff.ids[0].type==2 }"><span>护照</span></c:if>
								<c:if test="${staff.ids[0].type==3 }"><span>海员证</span></c:if>
								<c:if test="${staff.ids[0].type==4 }"><span>回乡证</span></c:if>
								<c:if test="${staff.ids[0].type==5 }"><span>军官证</span></c:if>
								<c:if test="${staff.ids[0].type==6 }"><span>港澳通行证</span></c:if>
								<c:if test="${staff.ids[0].type==7 }"><span>台胞证</span></c:if>
								<c:if test="${staff.ids[0].type==99 }"><span>其他</span></c:if> 
								 <span class="number-type">${staff.ids[0].num }</span> 
								</div>
							</div>
						</div>
					</li>
						</c:forEach> 
				</ul>
			</div>
		</section>

	</body>
	<script type="text/javascript"  src="js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="libs/gublic.js"></script>
	<script type="text/javascript"  src="js/MyConfirm.js"></script>
	<script type="text/javascript">
		/*判断是否为平台企业，如果是平台企业只显示常旅客*/
		$(function(){
		 	//  && excessinfoStr eq ''
		 	var skipTypevalue='${skipType}';
		 	var excessinfoStr_='${excessinfoStr}';
		 	//alert(skipTypevalue+"___"+excessinfoStr_)
		  if(skipTypevalue==2 || (skipTypevalue==1 && excessinfoStr_=='')){
			$('.table-title').css("display","none");
			var isplat=$("#platOrg").val();
			 if(isplat=='true'){
				/* $('.show').css("display","none"); */
				$.post(
				'ppp/passAjax.act',
				{'flag':'PEK'},
				function(data){
					if(data&&data.status==0){
					$("#fuu").html("");
					var text="";
					var tys="";
						for(var idx in data.data){
						 var ty=data.data[idx].ids[0].type;
						 if(ty==1){
						    tys="身份证";
						 }else if(ty==2){
						    tys="护照";
						 }else if(ty==3){
						    tys="海员证";
						 }else if(ty==4){
						    tys="回乡证";
						 }else if(ty==5){
						    tys="军官证";
						 }else if(ty==6){
						    tys="港澳通行证";
						 }else if(ty==7){
						    tys="台胞证";
						 }else tys="其他";
						 
						 text+="<li class='left-passener'>"
						         +"<div class='left-passener' onclick='xuanze(this)'>"
						             +"<div class='middle-table'>"
						                 +"<div  class='passener-name'>"
							                 +"<input type='hidden' value="+data.data[idx].id+">"
							                 +"<input type='hidden' value="+data.data[idx].name+">"
							                 +"<input type='hidden' value="+data.data[idx].mobile+">"
							                 +"<input type='hidden' value="+data.data[idx].ids[0].type+">"
							                 +"<input type='hidden' value="+data.data[idx].ids[0].num+">"
							                 +"<input type='hidden' value="+data.data[idx].type+">"
							                 +data.data[idx].name
						                 +"</div>"
					                     +"<div class='passener-num'>"
					                         +"<span>手机号</span>"
					                         +"<span class='number-type'>"+data.data[idx].mobile+"</span>"
					                     +"</div>"
					                     +"<div class='passener-type'>"
					                         +"<span>"+tys+"</span>"
					                         +"<span class='number-type'>"+data.data[idx].ids[0].num+"</span>"
					                     +"</div>"
						             +"</div>"
						         +"</div>"
					             +"<div class='right-table' >"
					                 +"<img src='img/bianji.png' onclick='update("+data.data[idx].id+")'/>"
					             +"</div>"
					             
						      +"</li>";

						}
						$("#fuu").html(text);
					}
				},
				'json'
			);
			$('.tableBtn').parents('section').find('.addBtn img').addClass('imgShow');
			$('.tableBtn').parent().siblings('.emp-table').addClass('show').siblings().removeClass('show');
			}else{
				$('.table-title').show();
				$('.pas-table').css('display','block');
			} 
		 }
		  if(skipTypevalue==1 && excessinfoStr_!=''){
			search_pass();
			$('.tableBtn').parents('section').find('.addBtn img').addClass('imgShow');
			$('.tableBtn').addClass('activeTable').siblings().removeClass('activeTable');
			$('.tableBtn').parent().siblings('.emp-table').addClass('show').siblings().removeClass('show');
		  }
		}); 
		
		/*------选项卡的效果--------*/
		$('.tableBtn').eq(1).on('click', function() {//常旅客
			$(this).parents('section').find('.addBtn img').addClass('imgShow');
			$(this).addClass('activeTable').siblings().removeClass('activeTable');
			$(this).parent().siblings('.emp-table').addClass('show').siblings().removeClass('show');
			<%-- window.location.href='<%=path%>/ppp/pppp.act'; --%>
		});
		$('.tableBtn').eq(0).on('click', function() {//企业员工
			$(this).parents('section').find('.addBtn img').removeClass('imgShow');
			$(this).addClass('activeTable').siblings().removeClass('activeTable');
			$(this).parent().siblings('.pas-table').addClass('show').siblings().removeClass('show');
		});
		/*---------证件类型的显示和隐藏---------*/
		$('.left-passener').each(function(i){
			
			$("#certificate"+(i+1)).on('click',function(){
				var id=$(this).children().children().children().children().val();
			    var name=$(this).children().children().children().children().next().val();
			    var mobile=$(this).children().children().children().children().next().next().val();
			    var userIdType=$(this).children().children().children().children().next().next().next().val();
			    var userId=$(this).children().children().children().children().next().next().next().next().val();
			    var passengerType=$(this).children().children().children().children().next().next().next().next().next().val();
			    $("#id").val(id);
			    $("#userType").val(1);
			    $("#userName").val(name);
			    $("#userPhone").val(mobile);
			    
			    $("#userIdType").val(userIdType);
			    $("#userId").val(userId);
			    $("#passengerType").val(passengerType);
			$("#type").empty();
			if($("#typeNum"+i).val()>1&&'${skipType}'=='1'){
				//每个人的证件类型及证件号 
							var s = [];
							var ss;
							var userIdTypes=[];
							var userIdType;
						$('.certifi'+i).each(function(r){
							 ss=$("#"+i+"typeNu"+(r+1)).val();
							 userIdType=$("#"+i+"userIdType"+(r+1)).val();
							 if(ss != null){
								 s.push(ss);
							 }
							 userIdTypes.push(userIdType);
						}); 
				
				console.log(s)
				for (var j = 0; j < s.length; j++) {
					
					 if(s[j]==1){
					$("#type").append("<div class=\"type-document\"><span><input type='hidden' value="+s[j]+"_"+userIdTypes[j]+">身份证</span><img src=\"img/wei.png\" class='noselect'/></div>");
					}
					 if(s[j]==2){
					$("#type").append("<div class=\"type-document\"><span><input type='hidden' value="+s[j]+"_"+userIdTypes[j]+">护照</span><img src=\"img/wei.png\" class='noselect'/></div>");
					}
					if(s[j]==3){
					$("#type").append("<div class=\"type-document\" ><span><input type='hidden' value="+s[j]+"_"+userIdTypes[j]+">海员证</span><img src=\"img/wei.png\" class='noselect'/></div>");
					}	
					if(s[j]==4){
					$("#type").append("<div class=\"type-document\" ><span><input type='hidden' value="+s[j]+"_"+userIdTypes[j]+">回乡证</span><img src=\"img/wei.png\" class='noselect'/></div>");
					}	
					if(s[j]==5){
					$("#type").append("<div class=\"type-document\"><span><input type='hidden' value="+s[j]+"_"+userIdTypes[j]+">军官证</span><img src=\"img/wei.png\" class='noselect'/></div>");
					}	
					if(s[j]==6){
					$("#type").append("<div class=\"type-document\" ><span><input type='hidden' value="+s[j]+"_"+userIdTypes[j]+">港澳通行证</span><img src=\"img/wei.png\" class='noselect'/></div>");
					}	
					if(s[j]==7){
					$("#type").append("<div class=\"type-document\"><span><input type='hidden' value="+s[j]+"_"+userIdTypes[j]+">台胞证</span><img src=\"img/wei.png\" class='noselect'/></div>");
					}	
					if(s[j]==99){
					$("#type").append("<div class=\"type-document\" ><span><input type='hidden' value="+s[j]+"_"+userIdTypes[j]+">其他</span><img src=\"img/wei.png\" class='noselect'/></div>");
					}	  
						
		}   
		console.log($(".type-document").html())
		$(".certype .type-document:first-child").find("span").after('<img src=\"img/choose.png\"/>')
		$(".certype .type-document:first-child").find("img").remove(".noselect")			
					var height = $("body").height();
					$('.bounced').height(height);
					$('.doc-type').animate({
						height:'show'
					},1500);
				}else{
					$("#subForm").attr("action","flight/toBookTicket.act");
					
					$(document).LoadingShow();
				    setTimeout(function(){
				    	$("#subForm").submit();
				    },100)
				}
			});
		});
		
		/*------请选择证件类型------------*/
		
		(function($) {
			$(document).ready(function() {
				$('#type').on('click','img',function(){
					
					$(this).parent().siblings().find('img').attr("src","img/wei.png")
					$(this).attr("src","img/choose.png")
					console.log($(this))
					//$(this).siblings().find('img').attr('src', $(this).attr('src') == 'img/choose.png' ? 'img/wei.png' : 'img/choose.png');
					//$(this).attr('src', $(this).attr('src') == 'img/choose.png' ? 'img/wei.png' : 'img/choose.png');
				});
			})
		})(jQuery);
	
		
		
		$('.doc-btn').on('click',function(){
			var height = $("body").height();
			$('.bounced').height(height);
			$('.doc-type').animate({
				height:'hide'
			},1500);
			
			var noselect = getClass('img','noselect');
			var userIdType="";
			  for(var i=0;i < noselect.length; i++){
				  if($(noselect[i]).attr('src')=='img/choose.png'){
					  userIdType=$(noselect[i]).prev().children().val();   
				  }
			  }
			  if(userIdType!=null&&userIdType!=''){
				  $("#userIdType").val(userIdType.split("_")[0]);
				  $("#userId").val(userIdType.split("_")[1]);
			  }
			  $("#subForm").attr("action","flight/toBookTicket.act");
			  $(document).LoadingShow();
			  setTimeout(function(){
			  	$("#subForm").submit();
			  },100)
			
		});
		
		
		function search(obj){
		  var o=$(obj);
		  var oid=o.attr("id");
		  if(1==oid){       //企业员工
		    search_com();
		  }else if (2==oid){    //常旅客 
		    // alert($(".passener-content emp-table"));
		   // $(".passener-content emp-table").style.display='block';
		    //document.getElementById("lpassenger").style.display="";
		    search_pass();
		    
		  }
		}
		
		function search_pass(){
		    $.post(
				'ppp/passAjax.act',
				{'flag':'PEK'},
				function(data){
					if(data&&data.status==0){
					$("#fuu").html("");
					var text="";
					var tys="";
						for(var idx in data.data){
						 var ty=data.data[idx].ids[0].type;
						 if(ty==1){
						    tys="身份证";
						 }else if(ty==2){
						    tys="护照";
						 }else if(ty==3){
						    tys="海员证";
						 }else if(ty==4){
						    tys="回乡证";
						 }else if(ty==5){
						    tys="军官证";
						 }else if(ty==6){
						    tys="港澳通行证";
						 }else if(ty==7){
						    tys="台胞证";
						 }else tys="其他";
						 
						 text+="<li class='left-passener'>"
						         +"<div class='left-passener' onclick='xuanze(this)'>"
						             +"<div class='middle-table'>"
						                 +"<div  class='passener-name'>"
							                 +"<input type='hidden' value="+data.data[idx].id+">"
							                 +"<input type='hidden' value="+data.data[idx].name+">"
							                 +"<input type='hidden' value="+data.data[idx].mobile+">"
							                 +"<input type='hidden' value="+data.data[idx].ids[0].type+">"
							                 +"<input type='hidden' value="+data.data[idx].ids[0].num+">"
							                 +"<input type='hidden' value="+data.data[idx].type+">"
							                 +data.data[idx].name
						                 +"</div>"
					                     +"<div class='passener-num'>"
					                         +"<span>手机号</span>"
					                         +"<span class='number-type'>"+data.data[idx].mobile+"</span>"
					                     +"</div>"
					                     +"<div class='passener-type'>"
					                         +"<span>"+tys+"</span>"
					                         +"<span class='number-type'>"+data.data[idx].ids[0].num+"</span>"
					                     +"</div>"
						             +"</div>"
						         +"</div>"
					             +"<div class='right-table' >"
					                 //+"<a href='<%=path %>/ppp/updatee.act'><img src='img/bianji.png' /></a>"
					                 +"<img src='img/bianji.png' onclick='update("+data.data[idx].id+")'/>"
					             +"</div>"
					             
						      +"</li>";

						
						//alert(data.data[idx].name);
						}
						$("#fuu").html(text);
					}
				},
				'json'
			);
		}
		
		
		function xuanze(obj){
			var id=$(obj).children().children().children().val();
		    var name=$(obj).children().children().children().next().val();
		    var mobile=$(obj).children().children().children().next().next().val();
		    var userIdType=$(obj).children().children().children().next().next().next().val();
		    var userId=$(obj).children().children().children().next().next().next().next().val();
		    var passengerType=$(obj).children().children().children().next().next().next().next().next().val();
		    $("#id").val(id);
		    $("#userType").val(2);
		    $("#userName").val(name);
		    $("#userPhone").val(mobile);
		    $("#userIdType").val(userIdType);
		    $("#userId").val(userId);
		    $("#passengerType").val(passengerType);
		    $("#subForm").attr("action","flight/toBookTicket.act");
		    $(document).LoadingShow();
			setTimeout(function(){
				 $("#subForm").submit();
			},100)
		}
		
		function getClass(tagName,className){ //获得标签名为tagName,类名className的元素
		    if(document.getElementsByClassName){  //支持这个函数
		        return document.getElementsByClassName(className);
		    }else{       
		    	var tags=document.getElementsByTagName(tagName);//获取标签
		        var tagArr=[];//用于返回类名为className的元素
		        for(var i=0;i < tags.length; i++){
		            if(tags[i].class == className){
		                tagArr[tagArr.length] = tags[i];//保存满足条件的元素
		            }
		        }
		        return tagArr;
		    }
		}
		
		$('#addd').on('click',function(){
			 $("#subForm").attr("action","ppp/addd.act");
			 $("#subForm").submit();
		})
		//$('#update').on('click',function(){ 
			// $("#subForm").attr("action","<%-- <%=path %>/ppp/addd.act"); --%>
			// $("#subForm").submit();
		//})
		function update(passengerId){
			$("#passengerId").val(passengerId);
			$("#subForm").attr("action","ppp/updatee.act");
			 $("#subForm").submit();
		}
		function search_com(){
		}
	</script>

</html>