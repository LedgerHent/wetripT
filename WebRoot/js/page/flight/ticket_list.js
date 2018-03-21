
var is ={
				types : ["Array", "Boolean", "Date", "Number", "Object", "RegExp", "String", "Window", "HTMLDocument"]
			};
			for(var i = 0, c; c = is.types[i ++ ]; ){
				is[c] = (function(type){
					return function(obj){
							return Object.prototype.toString.call(obj) == "[object " + type + "]";
						}
				})(c);
			};


var url;
//查询
function query(){
	
}
//加载下一页
function loadPage(){
	
}
function getData(param){
	$.ajax({
		url:url,
		type:'post',
		async:false,
		data:$.param(),
		dataType:'json',
		success:function(rtn){
			
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			
		},
	});
	
}
//渲染数据
function renderData(list){
	
}
/**
 * 请求参数对象
 * @param tripType 航程类型 1=单程，2=往返，3=联程
 * @param cabinType 舱位类型 0=不限，1=经济舱，2=公务舱，3=头等舱，23=公务舱和头等舱
 * @param airline 航空公司二字码
 * @param tripInfo 航程信息 TripInfo[] 类型
 * @param passenger 乘客信息 Passenger[] 类型
 * @returns {FlightListObj}
 */
function FlightListObj(tripType,cabinType,airline,tripInfo,passenger){
	
	this.tripType = tripType;
	this.cabinType = cabinType;
	this.airline = airline;
	this.tripInfo = [];
	this.passenger = [];
	if(isType(tripInfo,'Array')){
		for(var idx in tripInfo){
			var obj = tripInfo[idx];
			if(isType(obj,'TripInfo')){
				this.tripInfo.push(obj);
			}
		}
	}
	if(isType(passenger,'Array')){
		for(var idx in passenger){
			var obj = passenger[idx];
			if(isType(obj,'Passenger')){
				this.passenger.push(obj);
			}
		}
	}
	if(typeof FlightListObj.flag == "undefined"){//初始化方法
		//addTripInfo 添加航程信息
		FlightListObj.prototype.addTripInfo = function(tripInfo){
			if(isType(tripInfo,'TripInfo')){
				this.tripInfo.push(tripInfo);
			}
		};
		//addPassenger 添加乘客信息
		FlightListObj.prototype.addPassenger = function(passenger){
			if(isType(passenger,'Passenger')){
				this.passenger.push(tripInfo);
			}
		};
		//resetArray 清空数组
		FlightListObj.prototype.ClearArray = function(){
			this.tripInfo = [];
			this.passenger = [];
		};
		//resetArray 重新设置数组
		FlightListObj.prototype.resetArray = function(tripInfo,passenger){
			this.tripInfo = [];
			this.passenger = [];
			if(isType(tripInfo,'Array')){
				for(var idx in tripInfo){
					var obj = tripInfo[idx];
					if(isType(obj,'TripInfo')){
						this.tripInfo.push(obj);
					}
				}
			}
			if(isType(passenger,'Array')){
				for(var idx in passenger){
					var obj = passenger[idx];
					if(isType(obj,'Passenger')){
						this.passenger.push(obj);
					}
				}
			}
		};
		//delTripInfo 删除航程信息 通过航程
		FlightListObj.prototype.delTripInfo = function(tripInfo){
			if(isType(tripInfo,'TripInfo')){
				for(var i=0;i<this.tripInfo.length;i++){
					var obj = this.tripInfo[i];
					if(obj.equals(tripInfo)){
						this.tripInfo.splice(i,1);//删除元素
					}
				}
			}
		};
		//delTripInfo 删除航程信息 通过航程flowId
		FlightListObj.prototype.delTripInfoByFlowId = function(flowId){
			if(isType(flowId,'Number')){
				for(var i=0;i<this.tripInfo.length;i++){
					var obj = this.tripInfo[i];
					if(obj.flowId==flowId){
						this.tripInfo.splice(i,1);//删除元素
					}
				}
			}
		};
		//delTripInfo 删除航程信息 通过航程flowId
		FlightListObj.prototype.toParamStr = function(){
			var result = "";
			for(var key in this){
				var val = this[key];
				if(is.String(val) || is.Number(val) ||is.Boolean(val)){
					result += key + "=" + val + "&"; 
				}else if((typeof val) == 'function' ){
					continue;
				}else if(is.Array(val)){
					var str = "";
					for(var k in val){
						str += key + "[" + k + "]";
						var v = val[k];
						if(is.Object(v)){
							for(var k1 in v){
								if((typeof v[k1])=='function'){
									continue;
								}else{
									var str1 = str + "." + k1 + "=" + v[k1] + "&";
								}
								result += str1;
							}
						}
					}
				}
			}
			return result.lastIndexOf("&")==result.length-1?result.substr(0,result.length-1):result;
		};
		FlightListObj.flag = true;
	}
}
/**
 * 航程信息 
 * @param flowId 航程编号
 * @param depAirport 出发机场三字码
 * @param arrAirport 到达机场三字码
 * @param date 出发日期 YYYY-MM-DD格式字符串
 * @returns
 */
function TripInfo(flowId,depAirport,arrAirport,date){
	this.flowId = flowId;
	this.depAirport = depAirport;
	this.arrAirport = arrAirport;
	this.date = date;
	if(typeof TripInfo.flag == "undefined"){//初始化方法
		TripInfo.prototype.equals=function(obj){
			if(isType(obj,'TripInfo')){
				if(this.flowId==obj.flowId && this.depAirport==obj.depAirport && this.arrAirport==obj.arrAirport && this.date==obj.date){
					return true;
				}
			}
			return false;
		};
		TripInfo.flag = true;
	};
	this.setFlowId = function(flowId){
		this.flowId = flowId;
	};
	this.setDepAirport = function(depAirport){
		this.depAirport = depAirport;
	};
	this.setArrAirport = function(arrAirport){
		this.arrAirport = arrAirport;
	};
	this.setDate = function(date){
		this.date = date;
	};
	
}

/**
 * 乘客信息
 * @param type 乘客类型 1=成人，2=儿童
 * @param count 乘客数量
 * @returns
 */
function Passenger(type,count){
	this.type = type;
	this.count = count;
}
/**
 * 是否是某一类型
 * @param obj 对象
 * @param typeStr 类型名称
 * @returns {Boolean}
 */
function isType(obj,typeStr){
	if(obj && obj.constructor.name == typeStr){
		return true;
	}
	return false;
}