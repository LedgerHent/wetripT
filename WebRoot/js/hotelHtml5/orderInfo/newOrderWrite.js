var userType = '';

$(function(){
	initRoomCount();
});

/**
 * 初始化最小房间数  （默认为1间）
 * @returns
 */
function initRoomCount(){
	if(minRoom > 1){
		for(var i = 1; i <= minRoom; i++){
			$('.compute .j').html(i);
			$("#roomCount").val(i);
			roomStr(i);
		}
	}
}


function submit_one(){
	$(this).LoadingShow();
	var params = $("#sform").serializeObject();
	//数据过滤
	for(var key in params){
		if(params[key] == null || params[key] == ''){
			delete params[key];
		}
	}
	$.ajax({
		type: 'post',
		data: params,
		url: base_path + 'neworder/validateStepOne.act',
		success: function(data){
			$(this).LoadingHide();
			if(data.code != '1000'){
				showMessage(data.msg, '友情提醒');
			}else{
				window.location.href = base_path + 'neworder/toNewOrderWriteAfter.act?' + $.param(params);
//				$.StandardPost(base_path + 'neworder/toNewOrderWriteAfter.act', params);
			}
		},
		error: function(){
			$(this).LoadingHide();
		}
	});
}

/**
 * 加载企业员工和常旅客信息列表
 * @param userId
 * @param tripType
 * @param key
 * @param userType 判断是联系人/入住人
 * @returns
 */
function load_staff_passenger_info(userId, tripType, key, userType){
	var data = {"userId":userId, "tripType":tripType, "key":key, "userType":"guest", "bookingEnterpId":bookingEnterpId};
	if(userType == 'linkman'){	//联系人
		data = {"userId":userId, "tripType":tripType, "key":key, "userType":"contact", "bookingEnterpId":bookingEnterpId};
	}
	$.ajax({
		type: "POST",
		data: data,
		url: base_path + "/neworder/toCustomerInfo.act",
		success: function(resp){
			$("#customer_info_section").html(resp);
		},
		error: function (xhr, status, error) {
			
	    }
	});
}
/**
 * 查找入住人信息
 * @param obj
 * @returns
 */
function queryCustomerInfo(obj){
	var key = $(obj).val();
	load_staff_passenger_info(bookingUserId, tripType, key, userType);
	
}

/**
 * 获取入住人信息标签
 * @returns
 */
function getRoomStr(roomIndex){
	var id = "";
	var name = "";
	var mobile = "";
	var email = "";
	var roomStr = '<li class="level">'
						+'<div class="rooms_o">房间' + roomIndex + '</div>'
						+'<div class="check_person">'
							+'<p>入住人</p>'
							+'<div>'
								+'<img src="../hotelimg/add.png" / class="add">'
							+'</div>'
						+'</div>'
					+'<ul class="check_list"></ul>';
	if(roomIndex == 1 && isShowGuest == 'true'){
		id = $("#bookingUserId").val();
		name = $("#bookingUserName").val();
		mobile = $("#bookingUser_mobile").val();
		email = $("#bookingUser_email").val();
		var bookingDeptId = $("#bookingDeptId").val();
		var bookingDeptName = $("#bookingDeptName").val();
		var birthday = $("#bookingUser_birthday").val();
		
		roomStr = '<li class="level">'
					+'<div class="rooms_o">房间' + roomIndex + '</div>'
					+'<div class="check_person">'
					+'	<p>入住人</p>'
					+'	<div>'
					+'		<img src="../hotelimg/add.png" / class="add">'
					+'	</div>'
					+'</div>'
					+'<ul class="check_list">'
					+'	<li>'
					+'		<div class="f">'
					+'			<div>'
					+'				<span class="w stay">入住人</span>'
					+'				<span>' + name + '</span>'
					+'				<input type="hidden" name="tmcOrderGuestList[0].guestId" value="' + id + '"/>'
					+'				<input type="hidden" name="tmcOrderGuestList[0].guestName" value="' + name + '"/>'
					+'				<input type="hidden" name="tmcOrderGuestList[0].roomNo" value="1"/>'
					+'				<input type="hidden" name="tmcOrderGuestList[0].guestType" value="0000800001"/>'
					+'				<input type="hidden" name="tmcOrderGuestList[0].deptId" value="' + bookingDeptId + '"/>'
					+'				<input type="hidden" name="tmcOrderGuestList[0].deptName" value="' + bookingDeptName + '"/>'
					+'				<input type="hidden" name="tmcOrderGuestList[0].ageType" value="1"/>'
					+'				<input type="hidden" name="tmcOrderGuestList[0].birthday" id="" value="' + birthday + '" / class="inputs">'
					+'			</div>'
					+'			<div>'
					+'				<img src="../hotelimg/jian.png" / class="jian">'
					+'			</div>'
					+'		</div>'
					+'		<div>'
					+'			<span class="w ash">联系电话</span>'
					+'			<input type="text" name="tmcOrderGuestList[0].tel" id="" value="' + mobile + '" / class="inputs">'
					+'		</div>'
					+'		<div>'
					+'			<span class="w ash">电子邮箱</span>'
					+'			<input type="text" name="tmcOrderGuestList[0].email" id="" value="' + email + '" / class="inputs">'
					+'		</div>';
		if(pageTempletFlag == '4'){
			roomStr = roomStr + '<div>'
							+'		<span class="w ash">身份证</span>'
							+'			<input type="hidden" name="tmcOrderGuestList[0].certType" value="0001300001" / class="inputs">'
							+'			<input type="text" name="tmcOrderGuestList[0].certNo" id="" value="" / class="inputs">'
							+'	</div>';
		}else if(pageTempletFlag == '5'){
			roomStr = roomStr + '<div>'
							+'		<span class="w ash">护照</span>'
							+'		<input type="hidden" name="tmcOrderGuestList[0].certType" value="0001300002" / class="inputs">'
							+'		<input type="text" name="tmcOrderGuestList[0].certNo" id="" value="" / class="inputs">'
							+'	</div>';
		}
		roomStr = roomStr + '</li>'
						+'</ul>';
	}
	roomStr = roomStr + '</li>';
	return roomStr;
}

/**---------------------------------------------------------------fill----------------------------------------------------------**/
/**------房间数的增加与减少--------**/
var amount = parseInt($("#roomCounSpan").text());
$('.compute .plus').click(function(){
	if(roomToSell == amount){
		showMessage('此房型最多可预订' + roomToSell + '间！', '友情提醒');
		return false;		
	}
	amount++;
	$('.compute .j').html(amount);
	$("#roomCount").val(amount);
	roomStr(amount);
});

$('.compute .minus').click(function(){
	if(minRoom == amount){
		showMessage('此房型最少需要预订' + minRoom + '间！', '友情提醒');
		return false;		
	}
	amount--;
	if(amount < 1){
		amount = 1;
		return false;					
	}
	$('.compute .j').html(amount);
	$("#roomCount").val(amount);
	$('body .i').children().eq(-1).remove();
});

/**-----默认房间数-------**/
var page_flag = $("#page_flag").val();
if(page_flag == 'false'){
	for(var j = 0; j < parseInt(amount); j++) {
		roomStr(j + 1);
	}
}
/*----------房间数的字符串拼接--------*/
function roomStr(i) {
	var roomStr = getRoomStr(i);
	$('#item .i').append(roomStr);
}

$('.header .you').click(function() {
	$('.bounced').fadeOut();
});
$('.top_m .you').click(function() {
	var params = new Object();
	params.ptHotelId = ptHotelId;
	params.nightCount = nightCount;
	params.adultCount = adultCount;
	params.childAgeDl = childAgeDl;
	params.roomCount = roomCount;
	params.checkStartDateStr = checkStartDateStr;
	params.cityId  = cityId;
	params.tripType = tripType;
	params.tripUserId = bookingUserId;
	params.enterpriseId = bookingEnterpId;
	params.choiceAgreementHotelFlag = choiceAgreementHotelFlag;
	params.homeAbroadFlag = homeAbroadFlag;
	var data = $.param(params);
	window.location.href = base_path + 'hotelSearch/getHotelDetail.act?' + data;
});

var guestEnter = $('.guest_li').length;	//入住人索引
if(guestEnter == undefined || guestEnter == 0){
	guestEnter = 1;
}
var contactEnter = $('.contact_li').length;	//联系人索引
if(contactEnter == undefined || contactEnter == 0){
	contactEnter = 1;
}
var id;
/*---------增加入住人-----*/
$('body').on('click', '.level .check_person', function() {
	var currMan = $(this).siblings('.check_list').children().length;	//当前每间房人数
	if(currMan >= checkInMan) {
		showMessage('最大入住人数不能超过' + checkInMan + '人', '友情提醒');
		return false;
	}
	index = $(this).parents('.level').index();
	id = "item";
	userType = id;
	$('.bounced').fadeIn();
	$('#bounced_p').text("添加客人信息");
	$('#ipt').val('');
	load_staff_passenger_info(bookingUserId, tripType, "", id);
});
$('.level .check_person').click(function(){
	var currMan = $(this).siblings('.check_list').children().length;	//当前每间房人数
	if(currMan >= checkInMan) {
		showMessage('最大入住人数不能超过' + checkInMan + '人', '友情提醒');
		return false;
	}
	index = $(this).parents('.level').index();
	id = "item";
	$('.bounced').fadeIn();
	$('#bounced_p').text("添加客人信息");
	$('#ipt').val('');
	userType = id;
	load_staff_passenger_info(bookingUserId, tripType, "", id);
});

/*--------联系人的增加事件-------*/
$('.linkman .check_person').click(function() {
	$('.bounced').fadeIn();
	index = 0;
	id = "linkman";
	$('#bounced_p').text("添加联系人信息");
	$('#ipt').val('');
	userType = id;
	load_staff_passenger_info(bookingUserId, tripType, "", id);
});

$('body').on('click', '.show li', function() {
	var manObj = new Object();
	manObj.id = $(this).find('.passener-guestId').val();
	manObj.name = $(this).find('.passener-name').html();
	manObj.phone = $(this).find('.passener-phone').html();
	manObj.email = $(this).find('.passener-email').html();
	manObj.birthday = $(this).find('.passener-birthday').val();
	manObj.deptId = $(this).find('.passener-deptId').val();
	manObj.deptName = $(this).find('.passener-deptName').val();
	manObj.ageType = $(this).find('.passener-ageType').val();
	manObj.idCard = $(this).find('.passener-idCard').val();
	manObj.passPort = $(this).find('.passener-passPort').val();
	$('.bounced').fadeOut();
	hotelStaff(manObj, index, id);
});

/*-------入住人员的字符串拼接-------*/
function hotelStaff(manObj, index, id) {
	if(id == "item") {
		var roomNo = index + 1;
		var guest = '<li>'
					+'	<div class="f">'
					+'		<div>'
					+'			<span class="w stay">入住人</span>'
					+'			<span>' + manObj.name + '</span>'
					+'			<input type="hidden" name="tmcOrderGuestList[' + guestEnter + '].guestId" value="' + manObj.id + '"/>'
					+'			<input type="hidden" name="tmcOrderGuestList[' + guestEnter + '].guestName" value="' + manObj.name + '"/>'
					+'			<input type="hidden" name="tmcOrderGuestList[' + guestEnter + '].roomNo" value="' + roomNo + '"/>'
					+'			<input type="hidden" name="tmcOrderGuestList[' + guestEnter + '].guestType" value="0000800001"/>'
					+'			<input type="hidden" name="tmcOrderGuestList[' + guestEnter + '].deptId" value="' + manObj.deptId + '"/>'
					+'			<input type="hidden" name="tmcOrderGuestList[' + guestEnter + '].deptName" value="' + manObj.deptName + '"/>'
					+'			<input type="hidden" name="tmcOrderGuestList[' + guestEnter + '].ageType" value="' + manObj.ageType + '"/>'
					+'			<input type="hidden" name="tmcOrderGuestList[' + guestEnter + '].birthday" id="" value="' + manObj.birthday + '" / class="inputs">'
					+'		</div>'
					+'		<div>'
					+'			<img src="../hotelimg/jian.png" / class="jian">'
					+'		</div>'
					+'	</div>'
					+'	<div>'
					+'		<span class="w ash">联系电话</span>'
					+'		<input type="text" name="tmcOrderGuestList[' + guestEnter + '].tel" id="" value="' + manObj.phone + '" / class="inputs">'
					+'	</div>'
					+'	<div>'
					+'		<span class="w ash">电子邮箱</span>'
					+'		<input type="text" name="tmcOrderGuestList[' + guestEnter + '].email" id="" value="' + manObj.email + '" / class="inputs">'
					+'	</div>';
		if(pageTempletFlag == '4'){
			guest = guest + '<div>'
							+'	<span class="w ash">身份证</span>'
							+'	<input type="hidden" name="tmcOrderGuestList[' + guestEnter + '].certType" value="0001300001" / class="inputs">'
							+'	<input type="text" name="tmcOrderGuestList[' + guestEnter + '].certNo" id="" value="' + manObj.idCard + '" / class="inputs">'
							+'</div>';
		}else if(pageTempletFlag == '5'){
			guest = guest + '<div>'
							+'	<span class="w ash">护照</span>'
							+'	<input type="hidden" name="tmcOrderGuestList[' + guestEnter + '].certType" value="0001300002" / class="inputs">'
							+'	<input type="text" name="tmcOrderGuestList[' + guestEnter + '].certNo" id="" value="' + manObj.passPort + '" / class="inputs">'
							+'</div>';
		}
		guest = guest + '</li>';
		guestEnter++; 
		$('#' + id).find('.check_list').eq(index).append(guest);
	} else {
		var contact = '<li>'
					+'		<div class="f">'
					+'			<div>'
					+'				<span class="w stay">联系人</span>'
					+'				<span>' + manObj.name + '</span>'
					+'				<input type="hidden" name="tmcOrderContactsList[' + contactEnter + '].contact" value="' + manObj.name + '"/>'
					+'			</div>'
					+'			<div>'
					+'				<img src="../hotelimg/jian.png" / class="jian">'
					+'			</div>'
					+'		</div>'
					+'		<div>'
					+'			<span class="w ash">联系电话</span>'
					+'			<input type="text" name="tmcOrderContactsList[' + contactEnter + '].tel" id="" value="' + manObj.phone + '" / class="inputs">'
					+'		</div>'
					+'		<div>'
					+'			<span class="w ash">电子邮箱</span>'
					+'			<input type="text" name="tmcOrderContactsList[' + contactEnter + '].email" id="" value="' + manObj.email + '" / class="inputs">'
					+'		</div>'
					+'	</li>';
		contactEnter++;
		$('#' + id).find('.check_list').eq(index).append(contact);
	}
}

/**----------删除入住人------**/
$('body').on('click', '.f', function() {
	if($(this).parents('.check_list').children().length <= 1) {
		if($(this).find('input[name$="roomNo"]').val() == 1){
			return false;
		}
	}
	$(this).parent().remove();
});

/**===============================================================================================================================================**/
/**
 * 提示框显示
 * @param mess
 */
function showMessage(mess, title){
	$(this).MyConfirm({
		title: title,
		content: mess
	});
}
