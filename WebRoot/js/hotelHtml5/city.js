$(function(){
	/*****首页*****/
	
	
	//城市列表
	$('.citySelect').click(function(event) {
		$('.zi_mu').find('.xiala').removeClass('img_top').addClass('img_down');
		$('.zi_mu_fen').find('span').hide();
		$('.zi_mu_fen0').show();
		var type = $('.parentId').val()=='-99'?'2':'1';
		getSearchHistory('city',false,type);
		var inVal = $('.parentId').val()=='-99'?'搜索国际城市名称':'搜索国内城市名称';
		$('.zi_mu_fen').find('span').hide();
		$('.zi_mu_fen0').show();
		$('.sousuo_inp').val(inVal);
		$('.ss_cs_in').val(inVal);
		$('.ss_cs_in').attr('type_code',1);
		$('.ban_top').removeClass('displayNONE');
		$('.fahui').addClass('displayNONE');
		$('.index_section').addClass('displayNONE');
		$('.city_section').removeClass('displayNONE');
		$('.ui-icon-return').attr('type_code', '1');
		$('html, body').animate({scrollTop:0});
	});	
	//城市搜索
	$('.sousuo_sr').click(function(event) {
		console.log(1)
		/*$('.search_history').addClass('displayNONE');
		$('.search_history').find('ul').empty();
		$('.qingchu_jl').attr('type_code','city');
		var type = $('.parentId').val()=='-99'?'2':'1';
		getSearchHistory('city',true,type);
		$('.city_section').addClass('displayNONE');
		$('.search_section').removeClass('displayNONE');
		$('.ui-icon-return').attr('type_code', '2');
		$('.ss_cs_in').attr('type_code',1);
		$('.liandong').html('<ul></ul>');*/
	});
	//城市联想
	$('.ss_cs_in').focus(function(event) {
		if ($(this).val()=='搜索国内城市/区域/位置' || $(this).val()=='搜索酒店名称/关键词' || $(this).val()=='搜索国际城市名称' || $(this).val()=='搜索国内城市名称') {
			$(this).val('');
		}
	});
	$('.ss_cs_in').blur(function(event) {
		if ($(this).val()=='') {
			if ($(this).attr('type_code')==1) {
				var inVal = $('.parentId').val()=='-99'?'搜索国际城市名称':'搜索国内城市名称';
				$('.sousuo_inp').val(inVal);
				$('.ss_cs_in').val(inVal);
			}else if ($(this).attr('type_code')==2) {
				$(this).val('搜索酒店名称/关键词');
			}
		}
	});
	//城市选择
	$('.quan_bu_sp').on('click',function(event) {
		console.log(1)
		var cityId = $(this).attr('id');
		var cityCnName = $(this).text();
		var type = $('.parentId').val()=='-99'?2:1;
		var temp_city ={};
		temp_city['id'] = cityId;
		temp_city['name'] = cityCnName;
		temp_city['type'] = type;
		setSearchHistory('city',temp_city);
		$('.ban_top').addClass('displayNONE');
		$('.fahui').removeClass('displayNONE');
		$('.cityId').val(cityId);
		$('#city').val(cityCnName);
		$('.city_section').addClass('displayNONE');
		$('.index_section').removeClass('displayNONE');
		resetCity();// 重置品牌信息
		var sTop = $('.haiwai').offset().top;
		$('html, body').animate({scrollTop:sTop});
	});
	// 全部城市列表
	$('.zi_mu').click(function(event) {
		var lettObj = $(this);
		var span = lettObj.find('.xiala');
		var next = lettObj.next('.zi_mu_fen');
		if (span.hasClass('img_top')) {
			span.removeClass('img_top');
			span.addClass('img_down');
			// lettObj.find('.letterV').removeClass('zi_mu_sa');
		}else{
			span.removeClass('img_down');
			span.addClass('img_top');
			// lettObj.find('.letterV').addClass('zi_mu_sa');
		}
		// lettObj.find('.letterV').toggleClass('zi_mu_sa');
		if (next.find('span').length<=1) {
			//请求数据
			var key = lettObj.text();
			var is_inter = $('.parentId').val();
			$.post('/hotel/index/getCity',{key: key,is_inter:is_inter}, function(data) {
				if (data.code==200) {
					var html = '';
					var len = data.data.length;
					$.each(data.data,function(i, el) {
						if ('-99'==is_inter) {
							html+='<span id="'+el.city_id+'" onclick=\'selectCity(this,'+el.city_id+',"'+el.city+'")\' type_code="'+el.city+'">'+el.city+','+el.country+'</span>';
						}else{
							html+='<span id="'+el.city_id+'" onclick=\'selectCity(this,'+el.city_id+',"'+el.city+'")\' type_code="'+el.city+'">'+el.city+'</span>';
						}
					});
					lettObj.next('.zi_mu_fen').append(html);
				}
			});
		}else{
			// lettObj.next('.zi_mu_fen').toggle();
			lettObj.next('.zi_mu_fen').find('span').toggle();
			lettObj.next('.zi_mu_fen').find('span').eq(0).show();
		}
	});

})
//获取cookie 从cookie中获取搜索历史并展示，type为city或者hotel showpos 是否默认位置展示
function getSearchHistory(type,showpos,is_inter){
	var searchKey = 'hotel'==type?'searchListHistory':'wapHotelCityHistory'+is_inter;
	var len = 'hotel'==type?10:8;
	var values = $.cookie(searchKey);
	var html = '';
	$('.searchHistory').addClass('displayNONE');
	if (eval(values)) {
		values = jQuery.parseJSON(values);
		var hisLen = values.length>len?len:values.length;
		if(type == 'city'){
			$('.searchHistory').find('ul').html('');
			$('.search_cs').find('ul').html('');
			var tmp_i = 0;
			for(var i = 0; i < hisLen; i++) {
				var objVal = jQuery.parseJSON(eval(values[i]));
				var id = objVal.id;
				var name = objVal.name;
				var city_type = objVal.type;
				if (city_type==is_inter && tmp_i<=len) {
					if (showpos) {
						var style = '';
						if (tmp_i == len-1) {
							style = ' style="border:0px;"';
						}
						html += '<li onclick=\'selectCity(this,'+id+',"'+name+'")\''+style+'><span class="search_span txtEllipsis" style="height:20px" type_code="'+id+'">'+name+'</span></li>';
					}else{
						var className = '';
						if (0==tmp_i%3) {
							className = ' class="li_first"';
						}
						// id = '"'+id+'"';
						html += '<li onclick=\'selectCity(this,'+id+',"'+name+'")\''+className+'"><span class="quan_bu_sp txtEllipsis style="height:20px" type_code='+id+'>'+name+'</span></li>';
					}
					tmp_i++;
				}
				var parentId = is_inter==2?'-99':'36916';
				$('.parentId').val(parentId);
			}
			if (showpos) {
				$('.search_cs').find('ul').html(html);
				if($('.search_cs').find('li').length>0){
					$('.search_cs').parents('section').removeClass('displayNONE');
				}
			}else{
				$('.searchHistory').find('ul').html(html);
				if ($('.searchHistory').find('ul').find('li').length>0) {
					$('.searchHistory').removeClass('displayNONE');
				}
			}
		}else if (type == 'hotel') {
			var tmp_i=0;
			for(var i = 0; i < hisLen; i++) {
				var hotelVal = jQuery.parseJSON(eval(values[i]));
				var id = hotelVal['id'];
				var name = hotelVal['name'];
				var hotel_type = hotelVal['type'];
				var style = '';
				if (hotel_type==is_inter && tmp_i<=len) {
					if (tmp_i == len-1) {
						style = 'style="border:0px;"';
					}
					id = '"'+id+'"';
					html += '<li '+style+' onclick=\'selectHotel(this,'+id+',"'+name+'")\'><span class="search_span txtEllipsis" style="height:20px" type_code='+id+'>'+name+'</span></li>';
					tmp_i++;
				}
			}
			$('.parentId').val(is_inter);
			$('.search_cs').find('ul').html(html);
			if($('.search_cs').find('li').length>0){
				$('.search_cs').parents('.search_history').removeClass('displayNONE');
			}
		}
	}
}
// 设置cookie type为city或者hotel.      temp_value是要操作的数据
function setSearchHistory(type,temp_value){
	var parentId = $('.parentId').val()=='-99'?'2':'1';
	var searchKey = 'hotel'==type?'searchListHistory':'wapHotelCityHistory'+parentId;
	var len = 'hotel'==type?10:8;
	var values = $.cookie(searchKey);
	// json字符串转json对象：jQuery.parseJSON(jsonStr);
	// json对象转json字符串：JSON.stringify(jsonObj);
	if (eval(values)) {
		values = jQuery.parseJSON(values);
		// 判断是否存在
		var hisLen = values.length;
		for(var i = 0; i < hisLen; i++) {
			var id = jQuery.parseJSON(eval(values[i]))['id'];
			var name = jQuery.parseJSON(eval(values[i]))['name'];
			var hisType = jQuery.parseJSON(eval(values[i]))['type'];
			if(type == 'city'){
				if (id==temp_value.id) {
					values.splice(i, 1);
					break;
				}
			}else{
				if (name==temp_value.name) {
					if (temp_value.type==hisType) {
						values.splice(i, 1);
						break;
					}
				}
			}
		}
		// remove(values,"'"+JSON.stringify(temp_value)+"'");//存在则删除重复元素
		//存入最新数据
		values.unshift("'"+JSON.stringify(temp_value)+"'");
		if (hisLen>len) {//判断是否需要删除最早的数据
			values.pop();
		}
		console.log(values);
		//重新设置cookie
		// $.cookie(searchKey, values, { expires: 7*30, path: '/' });//存一个月
		$.cookie(searchKey, JSON.stringify(values),{ expires: 7*30, path: '/' });//存一个月
	}else{
		var val = new Array();
		val.push("'"+JSON.stringify(temp_value)+"'");
		$.cookie(searchKey, JSON.stringify(val),{ expires: 7*30, path: '/' });//存一个月
	}
}
//清除某类型的cookie，type为city或者hotel
function clearHistory(obj){
	var type = $(obj).attr('type_code');
	var searchKey = 'hotel'==type?'searchListHistory':'wapHotelCityHistory';
	// $.cookie(searchKey, null,{ path: '/' });
	if ('hotel'==type) {
		$.cookie('searchListHistory', null,{ path: '/' });
	}else{
		var parentId = $('.parentId').val()=='-99'?2:1;
		$.cookie('wapHotelCityHistory'+parentId, null,{ path: '/' });
	}
	$('.History').addClass('displayNONE');
	$(obj).parents('section').find('.clearHistory').empty();
}
//选择城市
function selectCity(obj,cityId,cityCnName){
	resetCity();// 重置品牌信息
	$('.ban_top').addClass('displayNONE');
	$('.fahui').removeClass('displayNONE');
	var type = $('.parentId').val()=='-99'?2:1;
	$('.cityList').removeClass('cur').removeClass('le_bg');
	$.each($('.cityList'),function(index) {
		var cityList = $('.cityList').eq(index);
		if (type==cityList.attr('type_code')) {
			if (cityList.hasClass('countryList')) {
				cityList.addClass('le_bg');
			}else{
				cityList.addClass('cur');
			}
		}
	});
	$('.cityId').val(cityId);
	$('#city').val(cityCnName);
	$('.city_section').addClass('displayNONE');
	$('.index_section').removeClass('displayNONE');
	$('.search_section').addClass('displayNONE');
	var sTop = $('.haiwai').offset().top;
	$('html, body').animate({scrollTop:sTop});
	$('.liandong').addClass('displayNONE');
	$('.History').addClass('displayNONE');
	$('.liandong').find('ul').html('');
	var temp_city ={};
	temp_city['id'] = cityId;
	temp_city['name'] = cityCnName;
	temp_city['type'] = type;
	setSearchHistory('city',temp_city);
}
//选择酒店
function selectHotel(obj,hotelId,hotelCnName){
	$('.ban_top').addClass('displayNONE');
	$('.fahui').removeClass('displayNONE');
	var type = $('.parentId').val();
	$('.hotelId').val(hotelId);
	$('.hotelName').val(hotelCnName);
	$('.gjz').val(hotelCnName);
	$('.gjz').css('color', '#000');
	$('.search_hotel').parents('li').find('.guanbi .close').removeClass('displayNONE');
	$('.search_hotel').parents('li').find('.guanbi .open').addClass('displayNONE');
	$('.city_section').addClass('displayNONE');
	$('.index_section').removeClass('displayNONE');
	$('.search_section').addClass('displayNONE');
	var sTop = $('.haiwai').offset().top;
	$('html, body').animate({scrollTop:sTop});
	$('.liandong').addClass('displayNONE');
	$('.History').addClass('displayNONE');
	$('.liandong').find('ul').html('');
	var temp_hotel ={};
	temp_hotel['id'] = hotelId;
	temp_hotel['name'] = hotelCnName;
	temp_hotel['type'] = type;
	setSearchHistory('hotel',temp_hotel);
}
//字符串长度  jmz.GetLength(keyword);
function resetCity(){
	$('.search_hotel').find('.gjz').val('关键词/酒店名称').css('color', '#ccc');
	$('.gjz_xz').val('价格/星级').css('color', '#ccc');
	$('.close').addClass('displayNONE');
	$('.open').removeClass('displayNONE');
	$('.hotelId').val();
	$('.hotelName').val();
	$('.lowPrice').val('');
	$('.topPrice').val('');
	$('.starLvs').val('');
	$('.priceLv').val('');
	$('.touming').addClass('displayNONE');

	$('.price_radio').removeClass('li_bk');
	$('.price_radio').find('span').addClass('displayNONE');
	$('.starLvUl').find('li').removeClass('li_bk');
	$('.starLvUl').find('span').addClass('displayNONE');
}