
function checkValidMobile(mobile) {
    var regex = /^((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8}$/
    return regex.test(mobile)
}



function formatDate(date, format) {
    if (!date) return;
    if (!format) format = "yyyy-MM-dd HH:mm:ss";
    switch (typeof date) {
        case "string":
            date = new Date(date.replace(/-/, "/"));
            break;
        case "number":
            date = new Date(date);
            break;
    }
    if (!date instanceof Date) return;
    var dict = {
        "yyyy": date.getFullYear(),
        "M": date.getMonth() + 1,
        "d": date.getDate(),
        "H": date.getHours(),
        "m": date.getMinutes(),
        "s": date.getSeconds(),
        "MM": ("" + (date.getMonth() + 101)).substr(1),
        "dd": ("" + (date.getDate() + 100)).substr(1),
        "HH": ("" + (date.getHours() + 100)).substr(1),
        "mm": ("" + (date.getMinutes() + 100)).substr(1),
        "ss": ("" + (date.getSeconds() + 100)).substr(1)
    };
    return format.replace(/(yyyy|MM?|dd?|HH?|ss?|mm?)/g, function () {
        return dict[arguments[0]];
    });
}

//| 日期时间有效性检查 
//| 格式为：YYYY-MM-DD HH:MM:SS 
//| callback(dt) 
function isDateTime(str, isTimeNeeded,callback) {
    if (isTimeNeeded == undefined) isTimeNeeded = false;
    //var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;
    var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})( (\d{1,2}):(\d{1,2}))?$/;//精确到分钟
    var r = str.match(reg);
    if (r == null) return false;
    //r数组length为8，分别是日期时间字符串、年、-、月、日、时间字符串、时、分
    //r[2]值为 - 
    var d;
    if (isTimeNeeded == true) {
        if (r[5] == undefined) return false;
        d = new Date(r[1], r[3], r[4], r[6], r[7]);
    }
    else
        d = new Date(r[1], r[3], r[4]);
    if (d.getFullYear() != r[1]) return false;
    if (d.getMonth() != r[3]) return false;
    if (d.getDate() != r[4]) return false;
    if (isTimeNeeded == true) {
        if (r[6] != undefined && d.getHours() != r[6]) return false;
        if (r[7] != undefined && d.getMinutes() != r[7]) return false;
        //if (d.getSeconds() != r[7]) return false;
    }
    if (callback) callback(d);
    return true;
}


//判断是否为空字符串，空格，制表符，换页符也不算
function isEmptyString(str) {
    if (str.replace(/(^s*)|(s*$)/g, "").length == 0)
        return true;
    return false;
}

//判断是否为数字
function isNumber(num) {
    return !isNaN(num);
}

//判断是否为经度，123.456789这种格式[-180,180]
function isLongitude(num) {
    if (!isNumber(num)){
        return false;
    }
    return num >= -180.0 && num <= 180.0;
}

//判断是否为经度，12.345678这种格式[-90,90]
function isLatitude(num) {
    if (!isNumber(num)) {
        return false;
    }
    return num >= -90.0 && num <= 90.0;
}

//获得时间，该方法默认不判断时间的有效性
function getDatetime(dtStr, isCheckValid,isTimeNeeded) {
    if (isCheckValid == true) {
        if (!isDateTime(dtStr, isTimeNeeded))
            return undefined;
    }
    return new Date(dtStr.replace(/-/g, "/"));
}