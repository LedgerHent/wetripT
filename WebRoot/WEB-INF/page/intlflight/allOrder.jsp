<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/masterpage" prefix="fms" %>
<%@include file="../public/tag.jsp" %>
<fms:ContentPage materPageId="master">
<fms:Content contentPlaceHolderId="title">
	全部订单
</fms:Content>
<fms:Content contentPlaceHolderId="main">
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no" />
		<title>全部订单</title>
		<link rel="stylesheet" type="text/css" href="css/intlAllOrder.css" />
	</head>

	<body>
	<div id="container">
		<div class="headerS"></div>
		<form method="post" action="intlflight/orderDetail.act">
			<input type="hidden" id="orderId" name="orderId" value="" />
		</form>
		<section id="allAbout">

			<ul class="allList">

				<!-- <li class="all-order turn">
					<div class="one-way">
						<div class="way-title">
							<div class="tit-left">
								<span class="way-type">返程</span>
								<span class="way-num">订单号20170224A0388</span>
							</div>
							<h5 class="audit">已审核</h5>
						</div>
						<div class="way-trip">
							<div class="trip left">
								<h4>北京<img src="img/wanfan.png" / class="dancheng">上海</h4>
								<div class="adress">
									<span class="icon">
									<img src="img/go.png"/ class="img-icon">
								</span>
									<span class="ytpes">HU7619</span>
									<span class="data">02-24</span>
									<span>09:20&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;11:45</span>
								</div>
								<div class="adress">
									<span class="icon">
									<img src="img/turn1.png"  class="img-icon img-icons">
								</span>
									<span class="ytpes">HU7619</span>
									<span class="data">02-24</span>
									<span>09:20&nbsp;&nbsp;&nbsp;至 &nbsp;&nbsp;&nbsp;11:45</span>
								</div>
							</div>
							<div class="trip right">
								<h4>¥1458</h4>
								<div class="price">支付倒计时<span class="audit time">900</span></div>
							</div>
						</div>
					</div>
				</li> -->
			</ul>
		</section>
		</div>
	</body>
	<script type="text/javascript" src="libs/jquery.min.js"></script>
	<script type="text/javascript" src="js/page/DateUtil.js?_=<%=Math.random()%>"></script>
	<script type="text/javascript" src="js/MyConfirm.js?_=<%=Math.random()%>"></script>
	<script type="text/javascript" src="js/page/comm.js"></script>
	<script type="text/javascript">
		$(".headerS").load("header.html", function(data) {
			$('.zhixiang').html('全部订单');
			$('header div img').on('click', function() {
				window.history.back(-1);
			});
		});
		$('.all-order').on('click', function() {
			/*var uname = $(this).find('.way-type').html();
			var count = $(this).find('.audit').html();
			window.location.href = "oneTransit.html?name=" + uname + "&cont=" + count;*/
		})
	</script>
	<script type="text/javascript">
	
	var totalCount;
	var count = 10;
	var status = "${status}";
	var start = 0;
	var listIdx = 1;
	
	$(window).scroll(function(){
		　　var scrollTop = $(this).scrollTop();
		　　var scrollHeight = $(document).height();
		　　var windowHeight = $(this).height();
		　　if(scrollTop + windowHeight == scrollHeight){
				//console.log("totalCount=" + totalCount + ",listIdx=" + listIdx)
			　　if(totalCount > listIdx){
					getDataAndRender(status, start, count);
			   }
		　　}
		});

        function fullData(data){
         var div= $(".all-order");
          if(typeof(data.data)=="undefined" && div==0){
               showMsg("没有订单数据");
               return false;
           }
          if(typeof(data.data)=="undefined"){
              return false;
          }
          if(data && data.status==0 && data.data.list && data.data.list.length>0){
                  start += 1;
              var list=data.data.list;
              
              for(var idx in list){
                  var obj=list[idx];
                  var moduel ='<li class="all-order" onclick="goOneOrder(this);" orderId="'+obj.orderId+'"><div class="one-way"><div class="way-title"><div class="tit-left"><span class="way-type">'
                  if(obj.travelType==1){
                     moduel += '单程';
                  }else{
                     moduel +='往返';
                  }
                  moduel +='</span><span class="way-num">订单号'+obj.orderNo+'</span></div><h5 class="audit">';
                  var st="";
                 /*  国际订单状态（1-待审核，2-待支付，7-已删除，3-审核已同过，审核已拒绝，4-已支付,6-已取消） */
                 switch (obj.status) {
						case 1: st = "待审核"; break;
						case 2: st = "待支付"; break;
						case 3: st = "已审核"; break;
						case 4: st = "已支付"; break;
						case 6: st = "已取消"; break;
						case 7: st = "已删除"; break;
						
					}
				 moduel +=st+'</h5></div>';
				 if(obj.travelType ==1){
				     moduel +='<div class="way-trip"><div class="trip left"><h4>'+obj.orgCityName+'<img src="img/dancheng.png" class="dancheng"/>'+obj.detCityName+'</h4>'+
				            '<div class="adress"><span class="icon"><img src="img/go.png"/ class="img-icon"></span><span class="ytpes">'+obj.airlines[0].flightNos.replace('/,/g' , '，')+
				            '</span><span class="data">'+formatDate(Date.parse(obj.airlines[0].startDatetime),'yyyy-MM-dd')+'</span><span>'+formatDate(Date.parse(obj.airlines[0].startDatetime),'HH:mm')+'&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;'+formatDate(Date.parse(obj.airlines[0].endDatetime),'HH:mm')+'</span></div></div>';
				 }
				 
				 if(obj.travelType ==2){
				    moduel +='<div class="way-trip"><div class="trip left"><h4>'+obj.orgCityName+'<img src="img/wanfan.png" class="dancheng"/>'+obj.detCityName+'</h4>'+
				            '<div class="adress"><span class="icon"><img src="img/go.png"/ class="img-icon"></span><span class="ytpes">'+obj.airlines[0].flightNos.replace(',','，')+
				            '</span><span class="data">'+formatDate(Date.parse(obj.airlines[0].startDatetime),'yyyy-MM-dd')+'</span><span>'+formatDate(Date.parse(obj.airlines[0].startDatetime),'HH:mm')+'&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;'+formatDate(Date.parse(obj.airlines[0].endDatetime),'HH:mm')+'</span></div>'+
				            '<div class="adress"><span class="icon"><img src="img/turn1.png"  class="img-icon img-icons"></span><span class="ytpes">'+obj.airlines[1].flightNos.replace(',','，')+
				            '</span><span class="data">'+formatDate(Date.parse(obj.airlines[1].startDatetime),'yyyy-MM-dd')+'</span><span>'+formatDate(Date.parse(obj.airlines[1].startDatetime),'HH:mm')+'&nbsp;&nbsp;&nbsp;至 &nbsp;&nbsp;&nbsp;'+formatDate(Date.parse(obj.airlines[1].endDatetime),'HH:mm')+'</span></div></div>';
				    
				 }
                     
                   moduel += '<div class="trip right"><h4>¥'+obj.amount+'</h4></div></div></div></li>';
                  
              $("#allAbout").append(moduel);
              }
              
          }
        }
        
        
        function goOneOrder(obj){
           $("#orderId").val($(obj).attr('orderId'));
           $("form").submit();
        }
	
		//获取数据
		function getDataAndRender(status,start,count){
			var para = {'status':status,'start':start,'count':count};
			$.ajax({
				url:'intlflight/getIntlOrderJSON.act',
				async:false,
				data:para,
				success:function(data){
					if(!totalCount){
						if(data&&data.data){
							totalCount = data.data.totalCount;
						}
					}
					fullData(data);
				},
				dataType:'json'
			});
		}
	$(function(){

	   getDataAndRender(status, start, count);
	});
	
	</script>

</html>
</fms:Content>
</fms:ContentPage>