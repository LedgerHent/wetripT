$(function(){
	showFeeInfo();
});

/**
 * 订单提交
 * @returns
 */
function submitOrder(confirmBooking){
	$(this).LoadingShow();
	var params = $("#sform").serializeObject();
	if(confirmBooking){
		params.confirmBooking = true;
	}
	//数据过滤
	for(var key in params){
		if(params[key] == null || params[key] == ''){
			delete params[key];
		}
	}
	$.ajax({
		type: 'post',
		data: params,
		url: base_path + 'neworder/saveOrder.act',
		success: function(data){
			dealWithOrderResult(data);
		},
		error: function(data){
			$(this).LoadingHide();
			
		}
	});
}
/**
 * 处理订单返回结果
 */
function dealWithOrderResult(data){
	$(this).LoadingHide();
	if(data.hotelCode == '1000'){
		doSuccessOrder(data.data);
	}else if(data.hotelCode == '-1'){
		showMessage(data.hotelMsg, '变动提醒');
	}else if(data.hotelCode == '1001'){
		showMessage(data.hotelMsg, '变动提醒');
	}else if(data.hotelCode == '9999'){
		showMessage(data.hotelMsg, '变动提醒');
	}else if(data.hotelCode == '1011'){
		showMessage(data.hotelMsg, '变动提醒', true, '确认预订', '我再想想');
	}else if(data.hotelCode == '1012'){
		showMessage(data.hotelMsg, '变动提醒');
	}else if(data.hotelCode == '1013'){
		showMessage(data.hotelMsg, '友情提醒');
	}else if(data.hotelCode == '1002'){
		showMessage(data.hotelMsg, '友情提醒');
	}else if(data.hotelCode == '1003'){
		showMessage(data.hotelMsg, '友情提醒');
	}
}
/**
 * 订单提交成功后处理
 * @param orderNo
 */
function doSuccessOrder(params){
	params.orderStatueName = encodeURIComponent(params.orderStatueName);
	window.location.href = base_path + '/neworder/toSuccess.act?' + $.param(params);
}
/**----------------------------------------------------费用明细 start-----------------------------------------------------*/
/**
 * 显示费用信息
 */
function showFeeInfo(){
	bookingTotalPrice = Number(bookingTotalPrice) * (Number(newRoomCount) / Number(roomCount));
	roomFee = Number(roomFee) * (Number(newRoomCount) / Number(roomCount));
	taxFee = Number(taxFee) * (Number(newRoomCount) / Number(roomCount));
	
	var roo_fee = Number(roomFee) / Number(newRoomCount);	//房间单价
	var taxe_fee = Number(taxFee) / Number(newRoomCount);	//税费单价
	
	$('.roos_fee').text(roo_fee + ' * ' + newRoomCount);
	$('.taxes_fee').text(taxe_fee == '0' ? '0' : taxe_fee + ' * ' + newRoomCount);
	$('#rooms_fee').val(roomFee);
	$('#taxess_fee').val(taxe_fee);
	$('#tmcOrderHotel_roomFee').val(roomFee);
	$('#tmcOrderHotel_taxFee').val(taxe_fee);
	
	$.ajax({
		type: 'post',
		async: false,
		data: {"userId":bookingUserId, "choiceAgreementHotelFlag":choiceAgreementHotelFlag, "amount": bookingTotalPrice, "nightCount":nightCount, "roomCount":newRoomCount},
		url: base_path + 'neworder/hotelServiceFee.act',
		success: function(data){
			var nightFee = data.nightFee;
			var bookingFee = data.bookingFee;
			$('.night_fee').text(nightFee + '.00');
			$('.booking_fee').text(bookingFee + '.00');
			$('#tmcOrderHotel_nightFee').val(nightFee);
			$('#tmcOrderHotel_bookingFee').val(bookingFee);
		}
	});
	getAllMoney();
}

/**
 * -----明细的计算----
 */
function getAllMoney(){
	var allMoney = Number($('#rooms_fee').val()) + Number($('#taxess_fee').val()) + Number($('.booking_fee').html()) + Number($('.night_fee').html()) - Number($('.treatment').html());
	$('.f_left .money').html('¥' + allMoney + '.00');
	$("#orderTotalAmount").val(allMoney);
}
/**----------------------------------------------------费用明细 end-----------------------------------------------------*/

/*-----结算方式选择-----*/
var numLength = ($('.payment .payment-choose .rows').length + 1) * 80 / 20 + 'rem';
$('.method').click(function() {
	$('.payment').fadeIn();
	$('.payment-con').animate({
		height: numLength
	}, 1000);
});
var html;
var payType = $("#payType").val();
function chooseH(obj, ele) {
	html = $(obj).find('span').html();
	payType = $(obj).find('.payType_i').val();
	if($(obj).find('span').hasClass('color') && $(obj).find('img').hasClass('selected')) {
		$(obj).find('span').removeClass('color');
		$(obj).find('img').removeClass('selected');
		html = '';
		payType = '';
		return false;
	};
	$(obj).find('span').addClass('color').parents('.rows').siblings().find('span').removeClass('color');
	$(obj).find('img').addClass('selected').parents('.rows').siblings().find('img').removeClass('selected');
	$(ele).fadeOut();

}
$('.payment-choose .rows').click(function() {
	chooseH(this, '.payment');
	$('.method .mode').html(html);
	$("#payType").val(payType);
});

$('.clsoeB').click(function(){
	$('.bounced').fadeOut();
	$('body,html').css({'overflow':''});
});
/*--------明细点击事件-------*/
$('#detail').click(function() {
	$('.amount_').fadeIn();
	$('#amount').slideDown();
	$('footer').css('z-index', '999999991');
});

/*------------保险开关的点击事件-----*/
$("#integral_btn").click(function(){
	if(userIntegral == 0){
		showMessage("剩余积分0，不可用");
		return false;
	}
	var c = $("#interest_btn").find('img').attr('class');
	if(c == 'circleBtn'){	//金旅豆关，积分可以开
		discountBtn(this);
	}else{
		showMessage("积分金旅豆二选一");
	}
});
$("#interest_btn").click(function(){
	if(userIntegral == 0){
		showMessage("剩余金旅豆0，不可用");
		return false;
	}
	var c = $("#integral_btn").find('img').attr('class');
	if(c == 'circleBtn'){	//积分关，金旅豆可以开
		setInterest();
		discountBtn(this);
	}else{
		showMessage("积分金旅豆二选一");
	}
});
/**
 * 积分/金旅豆开关
 * @param btnSwitch	按钮开关（true:开；false:关）
 */
function discountBtn(obj){
	if(!this.flag) {
		$(obj).find('img').attr('class', 'circleBtns');
		$(obj).css('background', '#00AFEC');
		$(obj).parents('.integral').siblings().css('display','flex');
	} else {
		$(obj).find('img').attr('class', 'circleBtn');
		$(obj).css('background', '#CCCCCC');
		$(obj).parents('.integral').siblings().slideUp();
		$(obj).parent().parent().find('input').val("");
		$('.treatment').text('0.00');
		getAllMoney();
	}
	this.flag = !this.flag;
}
/**
 * 设置积分
 * @param obj
 */
function setIntegral(obj){
	var value = $(obj).val() == '' ? 0 : $(obj).val();
	var reg = /^\d*$/;
	if(reg.test(value)){
		value = parseInt(value);
		if(value % 100 == 0){
			value = value / 100;
			$("#integral_price_id").val(value);
			$('.treatment').text(value + '.00');
			getAllMoney();
		}
	}
}
/**
 * 积分校验
 * @param obj
 */
function valideIntegral(obj){
	var value = $(obj).val() == '' ? 0 : $(obj).val();
	value = parseInt(value);
	if(value % 100 != 0){
		$(obj).val("");
		$('.treatment').text('0.00');
		getAllMoney();
		showMessage('请输入100倍数的积分额', '友情提醒');
		return false;
	}else if(value > userIntegral){
		$(obj).val("");
		$('.treatment').text('0.00');
		getAllMoney();
		showMessage('积分额仅剩余' + userIntegral, '友情提醒');
		return false;
	}
	//不含优惠的价格
	var totVP = Number($('#rooms_fee').val()) + Number($('#taxess_fee').val()) + Number($('.booking_fee').html()) + Number($('.night_fee').html());
	//优惠价
	var curVP = parseInt(value) / 100;
	if(curVP > totVP){
		$(obj).val("");
		$('.treatment').text('0.00');
		getAllMoney();
		showMessage("您的优惠金额不能超过订单总额！", '友情提醒');		
		return false;
	}
}
/**
 * 设置金旅豆
 * @param obj
 */
function setInterest(){
	var interestPrice = userInterest;
	var totP = parseInt($("#orderTotalAmount").val());
	if(interestPrice > totP){
		interestPrice = totP;
	}
	$("#interest_price_id").val(interestPrice);
	$('.treatment').text(interestPrice + '.00');
	getAllMoney();
}

/*-----金额比例的算法-------*/
$('.footer .sure').click(function(){
	
	
	
});

/*----------使用积分的提示框--------*/
$('.to_use .aw').click(function(){
	$('.prompting').fadeIn();
	$("body,html").css({"overflow":"hidden"});
});

/*-------使用金旅豆的提示框-------*/
$('.interest .aw').click(function(){
	$('.interest_').fadeIn();
	$("body,html").css({"overflow":"hidden"});
});
/*-----删除除了div之外任何区域，遮罩层隐藏----------*/
function hideLayer(e, Obj, idAttr) {
	e = e || window.event; //浏览器兼容性   
	var elem = e.target || e.srcElement;
	while(elem) { //循环判断至跟节点，防止点击的是div子元素   
		if(elem.id && elem.id == Obj) {
			return;
		}
		elem = elem.parentNode;
	}
	$(idAttr).parents('.bounced').css('display', 'none'); //点击的不是div或其子元素 

}
$('.amount_').bind('click', function(e) {
	hideLayer(e, 'matter', '#matter');
	$('#amount').css('display', 'none');
	$('footer').css('z-index', '');
});

$('.departments').bind('click', function(e) {
	hideLayer(e, 'department', '#department');
});

$('.payment').bind('click', function(e) {
	hideLayer(e, 'tested', '#tested');
});

$('.prompting').bind('click',function(e){
	hideLayer(e, 'prompting', '#prompting');
	$("body,html").css({"overflow":""});
});
$('.interest_').bind('click',function(e){
	hideLayer(e, 'interest_', '#interest_');
	$("body,html").css({"overflow":""});
});
$('.apt').bind('click', function(e) {
	hideLayer(e, 'apt', '#apt');
	$("body,html").css({"overflow":""});
});

/*---------返回上一页--------*/
$('.travel_title img').click(function() {
//	history.go(-1);
	var params = $("#sform").serializeObject();
	//数据过滤
	for(var key in params){
		if(params[key] == null || params[key] == ''){
			delete params[key];
		}
	}
//	$.StandardPost(base_path + 'neworder/backStepOne.act', params);
	window.location.href = base_path + 'neworder/backStepOne.act?' + $.param(params);
});
$('.nav .you').click(function(e) {
	e.stopPropagation();
	$('.apt').fadeOut();
	/*$('body,html').css({ "overflow": "" })*/
});
/*$('.invoice input').click(function() {
	$('.apt').fadeIn();
	$('body,html').css({ "overflow": "hidden" });
	var idFlag = 'invoice';
	reloadCompanyAptDetail(bookingUserId, bookingEnterpId, idFlag, "");
});*/
function queryCompanyApt(obj){
	var namekey = $(obj).val();
	reloadCompanyAptDetail(bookingUserId, bookingEnterpId, idFlag, namekey);
}
/**
 * 重载查找公司/部门页面
 */
function reloadCompanyAptDetail(bookingUserId, bookingEnterpId, idFlag, namekey){
	$.ajax({
		type: 'post',
		url: base_path + 'neworder/toCompanyAptDetail.act',
		data: {"userId":bookingUserId, "enterpId":bookingEnterpId, "idFlag":idFlag, "namekey":namekey, "tripType": tripType},
		success: function(resp){
			$("#myTable").html(resp);
		}
	});
}
$('body').on('click','#myTable tr',function(){
	$('.apt').fadeOut();
});
/**========================================================================================================================
/**
 *  设置开票公司信息/费用归属信息
 * @param idFlag
 * @param id
 * @param value
 */
function setDeptInfo(idFlag, id, name){
	$('#' + idFlag + '_id').val(id);
	$('#' + idFlag + '_name').val(name);
}

/**===============================================================================================================================================*/
/**
 * 提示框显示
 * @param mess
 */
function showMessage(mess, title, hasCancelBtn, confirmBName, cancelBName){
	$(this).MyConfirm({
		title: title,
		content: mess,
		hasCancelBtn: hasCancelBtn,
        confirmBtnName: confirmBName,
        cancelBtnName: cancelBName,
	}, function callback(isConfirm){
		if(isConfirm && hasCancelBtn){
			submitOrder(true);
		}
	});
}  	