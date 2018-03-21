var base_path = "/wetrip/"

function objectToStr(params){
	var jsonStr = JSON.stringify(params, function(key, value){
		if(value || value === 0){
			return value;
		}else{
			return undefined;
		}
	});
	return jsonStr;
}

//保存公共的信息到cookie
function saveCommonInfoToCookie(cookieType){
	var options = { expires: 10, path: '/' };
	console.log("------------index页面取值结果start--------------")
	console.log("homeAbroadFlag:"+$("#homeAbroadFlag").val());
	console.log("enterpriseId:"+$("#enterpriseId").val());
	console.log("tripUserId:"+$("#tripUserId").val());
	console.log("tripType:"+$("#tripType").val());
	console.log("cityName:"+$("#cityName").val());
	console.log("cityId:"+$("#cityId").val());
	console.log("checkStartDate:"+$("#checkStartDate").html());
    console.log("checkOutDate:"+$("#checkOutDate").html());
    console.log("startDate:"+$("#startDate").val());
    console.log("endDate:"+$("#endDate").val());
    console.log("nightCount:"+$("#nightCount").val());
    console.log("adultCount:"+$("#adultCount").val());
	console.log("childCount:"+$("#childCount").val());
	console.log("childAgeDl:"+$("#childAgeDl").val());
	console.log("roomCount:"+$("#roomCount").val());
	console.log("starLvs:"+$("#starLvs").val());
	console.log("hotelPrice:"+$("#hotelPrice").val());
	console.log("keyWord:"+$("#keyWord").val());
	console.log("keyAssociateValue:"+$("#keyAssociateValue").val());
	console.log("------------index页面取值结果end--------------")
	
	$.cookie('common_pageType', $("#pageFlag").val(),options);	
	$.cookie('common_homeAbroadFlag', $("#homeAbroadFlag").val(),options);	
	$.cookie('common_enterpriseId', $("#enterpriseId").val(),options);	 
	$.cookie('common_tripUserId', $("#tripUserId").val(),options);	
	$.cookie('common_tripType', $("#tripType").val(),options);	
	$.cookie('common_cityName', $("#cityName").val(),options);
	$.cookie('common_cityId', $("#cityId").val(),options);	
	//alert("saveCommonInfoToCookie startDate:"+$("#startDate").val()+" checkStartDate:"+$("#checkStartDate").val());
	
	if(cookieType=="index"){
		//----index存值
		$.cookie('common_checkStartDateSpan', $("#checkStartDate").html(),options);	
		$.cookie('common_checkOutDateSpan', $("#checkOutDate").html(),options);	
	    $.cookie('common_checkStartDate', $("#startDate").val(),options);	
	    $.cookie('common_checkOutDate', $("#endDate").val(),options);	
	    $.cookie('common_nightCount', $("#nightCount").val()==""?"1":$("#nightCount").val(),options);
	    //----index存值
	}else{
		//----hotelList存值
		console.info("hotelList")
		$.cookie('common_checkStartDateSpan', $("#checkStartDateSpan").html(),options);	
		$.cookie('common_checkOutDateSpan', $("#checkOutDateSpan").html(),options);	
	    $.cookie('common_checkStartDate', $("#checkStartDate").val(),options);						
	    $.cookie('common_checkOutDate', $("#checkOutDate").val(),options);		
	    $.cookie('common_nightCount', $("#nightCount").val()==""?"1":$("#nightCount").val(),options);
	    //----hotelList存值
	}
    $.cookie('common_adultCount', $("#adultCount").val(),options);	
    $.cookie('common_childCount', $("#childCount").val(),options);	
    $.cookie('common_childAgeDl', $("#childAgeDl").val(),options);	
    $.cookie('common_roomCount', $("#roomCount").val(),options);	
    $.cookie('common_starLvs', $("#starLvs").val(),options);	
    $.cookie('common_hotelPrice', $("#hotelPrice").val(),options);
	$.cookie('common_keyWord', $("#keyWord").val(),options);	
	$.cookie('common_keyAssociateValue', $("#keyAssociateValue").val(),options);
	
	console.log("------------index页面Cookie-start--------------");
	console.log("homeAbroadFlag:"+$.cookie('common_homeAbroadFlag'));
	console.log("enterpriseId:"+$.cookie('common_enterpriseId'));
	console.log("tripUserId:"+$.cookie('common_tripUserId'));
	console.log("tripType:"+$.cookie('common_tripType'));
	console.log("cityName:"+$.cookie('common_cityName'));
	console.log("cityId:"+$.cookie('common_cityId'));
	console.log("checkStartDate:"+$.cookie('common_checkStartDate'));
    console.log("checkOutDate:"+$.cookie('common_checkOutDate'));
    console.log("checkStartDateSpan:"+$.cookie('common_checkStartDateSpan'));
    console.log("checkOutDateSpan:"+$.cookie('common_checkOutDateSpan'));
    console.log("nightCount:"+$.cookie('common_nightCount'));
    console.log("adultCount:"+$.cookie('common_adultCount'));
	console.log("cccccccccccccccccccccccchildCount:"+$.cookie('common_childCount'));
	console.log("childAgeDl:"+$.cookie('common_childAgeDl'));
	console.log("roomCount:"+$.cookie('common_roomCount'));
	console.log("starLvs:"+$.cookie('common_starLvs'));
	console.log("hotelPrice:"+$.cookie('common_hotelPrice'));
	console.log("keyWord:"+$.cookie('common_keyWord'));
	console.log("keyAssociateValue:"+$.cookie('common_keyAssociateValue'));
	console.log("------------index页面Cookie-end--------------");
	
}

//cookie公共的信息到页面上
function getCookieToCommonPage(cookieType){
		var date = new Date();
		var se=date.getSeconds();
		var options = { expires:se , path: '/' };
	console.log("homeAbroadFlag:"+$.cookie('common_homeAbroadFlag')+"^^^"+$.cookie('common_enterpriseId'));
	console.log("tripUserId:"+$.cookie('common_tripUserId')+"----enterpriseId:"+$.cookie('common_enterpriseId')+"----homeAbroadFlag:"+$.cookie('common_homeAbroadFlag'))
	console.log("checkStartDateSpan:"+$.cookie('common_checkStartDateSpan')+"----checkOutDateSpan:"+$.cookie('common_checkOutDateSpan'))
	console.log("checkStartDate:"+$.cookie('common_checkStartDate')+"----checkOutDate:"+$.cookie('common_checkOutDate'))
	console.log("nightCount:"+$.cookie('common_nightCount'))
	console.log("cityName:"+$.cookie('common_cityName')+"----cityId:"+$.cookie('common_cityId'))
	console.log("adultCount:"+$.cookie('common_adultCount')+"----childCount:"+$.cookie('common_childCount'))
	console.log("childAgeDl"+$.cookie('common_childAgeDl')+"----roomCount:"+$.cookie('common_roomCount'))
	console.log("starLvs："+$.cookie('common_starLvs')+"----hotelPrice："+$.cookie('common_hotelPrice'))
	
	
	$("#pageFlag").val($.cookie('common_pageFlag'));
	if(cookieType=="index"){
		//----index取值		
		$("#nightCount").val($.cookie('common_nightCount')+"晚");		
		if($.cookie('common_tripType')=='0000700001'){
			$("#cause").val("因公出行");
		}else if($.cookie('common_tripType')=='0000700002'){
			$("#cause").val("因私出行");
		}
		
		$("#cityId").val($.cookie('common_cityId'));
		$("#cityName").val($.cookie('common_cityName'));
		$("#checkStartDate").html($.cookie('common_checkStartDateSpan'));
		$("#checkOutDate").html($.cookie('common_checkOutDateSpan'));
		if(!typeof($.cookie('common_checkStartDate'))){
			$("#startDate").val($.cookie('common_checkStartDate'));
		}
		if(!typeof($.cookie('common_checkOutDate'))){
			$("#endDate").val($.cookie('common_checkOutDate'));
		}
	}else{
		//----hotelList取值
		
		$("#enterpriseId").val($.cookie('common_enterpriseId'));
		$("#tripUserId").val($.cookie('common_tripUserId'));
		$("#tripType").val($.cookie('common_tripType'));
		$("#cityId").val($.cookie('common_cityId'));
		$("#cityName").val($.cookie('common_cityName'));
		$("#checkStartDateSpan").html($.cookie('common_checkStartDateSpan'));
		$("#checkOutDateSpan").html($.cookie('common_checkOutDateSpan'));
		$("#checkStartDate").val($.cookie('common_checkStartDate'));
		$("#checkOutDate").val($.cookie('common_checkOutDate'));
		$("#nightCount").val($.cookie('common_nightCount'));
		if($.cookie('common_tripType')=='0000700001'){
			$("#feature").css("display","");
		}else if($.cookie('common_tripType')=='0000700002'){
			$("#feature").css("display","none");
		}
	}
	$("#adultCount").val($.cookie('common_adultCount')==undefined?"2":$.cookie('common_adultCount'));
	$("#childCount").val($.cookie('common_childCount')==undefined?"0":$.cookie('common_childCount'));
	$("#childAgeDl").val($.cookie('common_childAgeDl'));
	$("#roomCount").val($.cookie('common_roomCount')==undefined?"1":$.cookie('common_roomCount'));
	$("#starLvs").val($.cookie('common_starLvs'));
	$("#hotelPrice").val($.cookie('common_hotelPrice'));
	$("#keyWord").val($.cookie('common_keyWord'));
	$("#keyAssociateValue").val($.cookie('common_keyAssociateValue'));
	$("#homeAbroadFlag").val($.cookie('common_homeAbroadFlag'));
}


function removeCookie(){
	var options = { expires: -1, path: '/' };
	$.cookie('common_tripType', null,options);	
	$.cookie('common_cityId', null, options);
	$.cookie('common_cityName', null, options);
	$.cookie('common_checkStartDate', null, options);
	$.cookie('common_checkOutDate', null, options);
	$.cookie('common_nightCount', null, options);
	$.cookie('common_adultCount', null, options);
	$.cookie('common_childCount', null, options);
	$.cookie('common_childAgeDl', null, options);
	$.cookie('common_roomCount', null,options);
	$.cookie('common_starLvs', null,options);	
}

function removeKeyCookie(){
	var options = { expires: -1, path: '/' };
	$.cookie('common_keyAssociateValue', null,options);
}
//封装酒店列表查询条件
function packageSearchCondition(){
	var params = new Object();
	params.homeAbroadFlag = $("#homeAbroadFlag").val(); 
	params.enterpriseId = $("#enterpriseId").val();
	params.tripUserId = $("#tripUserId").val();
	params.tripType = $("#tripType").val();
	params.cityId = $("#cityId").val();
	params.roomCount = $("#roomCount").val();
	
	params.checkStartDateStr = $("#checkStartDate").val();
	params.checkOutDateStr = $("#checkOutDate").val();
	params.nightCount = $("#nightCount").val();
	params.adultCount = $("#adultCount").val();
	params.childCount  = $("#childCount").val();
	params.childAgeDl = $("#childAgeDl").val();
	
	var starLvs = "";
	var starLvsStr = $("#starLvs").val();
	if(typeof(starLvsStr)!="undefined" && starLvsStr.indexOf("|")!=-1){
		starLvs = starLvsStr.replace(/\|/g,",")
	}else{
		starLvs = starLvsStr;
	}
	params.starLvs = starLvs;
	
	params.sort = $("#sort").val();
	params.sortWay = $("#sortWay").val();
	
	var hotelPrice = [];
	if(""!=$("#hotelPrice").val()){
		hotelPrice.push($("#hotelPrice").val());
	}
	if(hotelPrice.length>0){
		var lowPrice = new Number(hotelPrice[0].substr(0, 4));
		var topPrice = new Number(hotelPrice[hotelPrice.length-1].substr(4));
		params.lowPrice = lowPrice;
		params.topPrice = topPrice;
	}
	params.tripPolicyRemindType = $("#tripPolicyRemindType").val();
	
	params.agreementPageNum = $("#agreementPageNum").val();
	params.choicePageNum = $("#choicePageNum").val();
	params.pageSize = $("#pageSize").val();
	if($("#keyWord").val()!=""){
		params.keyAssociateValue = $("#keyAssociateValue").val();
	}
	if(""==$("#keyAssociateValue").val()){
		params.ptHotelName = $("#keyWord").val();
	}else{
		if($("#keyAssociateValue").val().substring(0,1)=="2"){
			params.maparea = 5000;
			params.sort = 3;  		//初始排序字段为默认
			params.sortWay = 0;  	//初始默认升序 
		}
	}
	return params;
}

//resultType:append;html
function searchList(resultType){
	$(this).LoadingShow();
	
	var params = packageSearchCondition();
	var jsonStr = objectToStr(params);
	$.ajax({
		type:"POST",
		data:JSON.parse(jsonStr),
		url:base_path+"/hotelSearch/getHotelList.act",
		success:function(resp){
			$(this).LoadingHide();
			
			//点亮精选tab
			$('.hotel-tab li').each(function(){
				var title = $.trim($(this).text());
				if(title=="精选酒店"){
					$(this).addClass('highlight')
				}else if(title=="协议酒店"){
					$(this).removeClass('highlight')
				}
			});
			
			//刷新结果列表
			if(resultType=="append"){
				$(".list-content").append(resp);
			}else{
				$(".list-content").html(resp);
			}
			
			//弹框
			if($("#hotelSearchCode").val()!="1000"){
				$(this).MyConfirm({
		            content: $("#hotelSearchMsg").val()
		        });
			}	
			//协议酒店不为空，展示协议tab
			if($.trim($("#agreementHotelResultFlag").val())=="true"){
				$("#agreementHotelTitle").css("display","");
			}else{
				$("#agreementHotelTitle").css("display","none");
			}
		},
		error: function (xhr, status, error) {
			$(this).LoadingHide();
			$(this).MyConfirm({
	            content: error
	        });
	    }
	});
}