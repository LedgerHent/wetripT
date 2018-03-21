package com.viptrip.resource;

public class Const {
	/**
	 * 数据库配置文件加密字段配置名称
	 */
	public static final String JDBC_ENCRYPT_PROPERTY = "encryptProperties";
	public static final String DEFAULT_CACHER = "defaultCacher";//默认缓存器
	
	//
	public static final String isTest = "testPro";
	public static final String TEST_USERID = "userId";
	//航空公司icon地址
	public static final String URL_ICON_SERVER = "iconServerURL";
	//cas登录地址
	public static final String URL_CAS_LOGIN = "casServerLoginURL";
	//cas退出地址
	public static final String URL_CAS_LOGOUT = "casServerLogoutURL";
	
	//配置文件以 FILE_ 开头
	public static final String FILE_CONFIG = "/conf/config.pro";//全局置文件
	public static final String FILE_IBESERVER = "/conf/ibeserver/ibeserver.properties";//配置文件
	public static final String FILE_CAS_EXCLUDE_CONFIG = "/conf/filter/filter_exclude.pro";//CAS路径排除文件
	public static final String FILE_CAIYUN_CONFIG = "/conf/caiyun_app/app.pro";//集团彩云配置文件
	public static final String FILE_PAY_CONFIG = "/conf/pay/pay.pro";//支付配置文件
	public static final String FILE_PAY_HOTEL_CONFIG = "/conf/pay/hotel_pay.pro";//酒店支付配置文件
	public static final String FILE_WECHAT_CONFIG="/conf/wechat/config.pro";
	
	
	//配置文件属性以 PRO_ 开头
	public static final String PRO_SERVER_PREFIX = "serverPrefix";//ticketServer属性名的前缀
	public static final String PRO_SERVER_URL = "serverUrl";//ticketServer属性名
	public static final String PRO_SERVER_SEC = "serverSec";//限制访问的秒数
	public static final String PRO_SERVER_COUNT_IN_SEC = "serverCountInSec";//访问次数
	public static final String PRO_SERVER_CONTROLLER_NS = "controllerNameSpace";//controller包名
	public static final String PRO_SERVER_MODEL_NS = "ModelNameSpace";//model包名
	public static final String PRO_SERVER_DESKEY = "desKey";//加密密钥
	public static final String PRO_SERVER_DESIV = "desIV";//加密向量
	public static final String PRO_SERVER_CHARSET = "charset";//加密字符集
	
	public static final String PRO_WECHAT_DISPATCHER="dispatcher";//微信分发
	
	public static final String PRO_CACHE_LIVETIME = "cacheLivetime";//默认缓存失效时间
	
	public static final String PRO_IBESERVER_IP = "webservice.server.ip";//IBE交互服务IP
	public static final String PRO_IBESERVER_PORT = "webservice.server.port";//IBE交互服务端口
	//集团彩云配置
	public static final String PRO_CAIYUN_APP_ID = "appId";//ID
	public static final String PRO_CAIYUN_APP_SECRET = "appSecret";//密码
	public static final String PRO_CAIYUN_APP_UBANBASEURL = "ubanBaseUrl";//url根地址
	public static final String PRO_CAIYUN_APP_PLATFORMID = "platformId";//平台id
	
	public static final String PRO_CAIYUN_APP_PATH_ACCESSTOKEN = "accessTokenPath";//accessToken接口地址
	public static final String PRO_CAIYUN_APP_PATH_PREPAYCHECK = "prePayCheck";//风控接口地址
	public static final String PRO_CAIYUN_APP_PATH_ORDERCALLBACK = "orderCallback";//订单回调接口地址
	
	
	//支付全局设置
	
	public static final String PRO_PAY_USE_OLD = "userOldInterface";//支付宝是否使用旧的支付接口
	public static final String PRO_PAY_IS_TEST = "isTest";//是否为测试 测试会将价格改为0.01
	public static final String PRO_PAY_RTN_URL = "return_url";//国内机票接入统一支付回跳地址
	public static final String PRO_PAY_NTF_URL = "notify_url";//国内机票接入统一支付通知地址

	
	//微信支付配置
	public static final String PRO_WX_WAP_NAME = "wx_wap_name";//应用名称
	public static final String PRO_WX_WAP_URL = "wx_wap_url";//网站地址
	public static final String PRO_WX_APPID = "wx_appid";//公众号appid
	public static final String PRO_WX_APPSECRET = "wx_appsecret";//公众号appsecret
	public static final String PRO_WX_CERTFILE = "wx_certfile";//证书路径
	public static final String PRO_WX_MACHID = "wx_mchid";//公众平台商户ID
	public static final String PRO_WX_KEY = "wx_key";//公众平台商户key
	public static final String PRO_WX_DOMAIN = "wx_domain";//公众平台商户key
	public static final String PRO_WX_URL_MICROPAY = "wx_micropay_url";//
//	public static final String PRO_WX_URL_NOTIFY_PAY = "wx_notify_pay_url";//微信支付结果通知URL
	public static final String PRO_WX_URL_UNIFIED_ORDER = "wx_unified_order_url";//统一下单URL
	public static final String PRO_WX_URL_ORDER_QUERY = "wx_order_query_url";//查询订单URL
	public static final String PRO_WX_URL_CLOSE_ORDER = "wx_close_order_url";//关闭订单URL
	public static final String PRO_WX_URL_REVERSE = "wx_reverse_url";//撤销订单URL
	public static final String PRO_WX_URL_REFUND = "wx_refund_url";//申请退款URL
	public static final String PRO_WX_URL_REFUNDQUERY = "wx_refundquery_url";//查询退款URL
	public static final String PRO_WX_URL_DOWNLOADBILLY= "wx_downloadbilly_url";//下载对账单URL
	public static final String PRO_WX_URL_REPORT= "wx_report_url";//交易保障URL
	public static final String PRO_WX_URL_SHORT= "wx_short_url";//转换短链接URL
	public static final String PRO_WX_URL_AUTHCODETOOPENID= "wx_authcodetoopenid_url";//授权码查询openId接口URL
	public static final String PRO_WX_URL_TOKEN = "wx_token_url";//获取token接口
	public static final String PRO_WX_URL_TICKET = "wx_ticket_url";//获取ticket接口
	public static final String PRO_WX_URL_OAUTH2 = "wx_oauth2_url";//获取oauth2授权接口
	
	public static final String PRO_WX_URL_NOTIFY_MWEB = "wx_notify_url_mweb";//mweb支付通知url
	public static final String PRO_WX_URL_RETURN_MWEB = "wx_return_url_mweb";//mweb支付回跳url
	
	
	//支付宝配置
	public static final String PRO_ALI_SERVER_URL = "ali_serverUrl";//接口主地址
	public static final String PRO_ALI_APPID = "ali_appId";//appid
	public static final String PRO_ALI_PRIVATE_KEY = "ali_privateKey";//私钥
	public static final String PRO_ALI_PUBLIC_KEY = "ali_pulicKey";//公钥
	public static final String PRO_ALI_SIGNTYPE = "ali_signType";//签名加密方式
	public static final String PRO_ALI_URL_NOTIFY_PAY = "ali_url_notify_pay";//支付通知回调
	public static final String PRO_ALI_URL_RETURN_PAY = "ali_url_return_pay";//支付会跳地址
	//老版支付宝配置
	public static final String PRO_ALI_OLD_URL = "ali_old_url";//
	public static final String PRO_ALI_OLD_SERVICE = "ali_old_service";//
	public static final String PRO_ALI_OLD_KEY = "ali_old_key";//KEY
	public static final String PRO_ALI_OLD_PARTNER = "ali_old_partner";//私钥
	public static final String PRO_ALI_OLD_SELLER_EMAIL = "ali_old_seller_email";//email
	public static final String PRO_ALI_OLD_PAYMENT_TYPE = "ali_old_payment_type";//签名加密方式
	public static final String PRO_ALI_OLD_CHARSET = "ali_old_charset";//签名加密方式

	//易宝支付配置
	public static final String PRO_YEE_MERID = "yeepay_MerId";//
	public static final String PRO_YEE_KEY = "yeepay_key";//
	public static final String PRO_YEE_URL_PAY = "yeepay_url_pay";//
	public static final String PRO_YEE_URL_QUERY = "yeepay_url_query";//
	public static final String PRO_YEE_URL_REFUND = "yeepay_url_refund";//
	public static final String PRO_YEE_URL_REFUNDQUERY = "yeepay_url_refundquery";//
	public static final String PRO_YEE_URL_ORDERCANCLE = "yeepay_url_ordercancle";//

	
	//酒店支付配置
	public static final String PRO_PAY_HOTEL_IS_TEST = "isTest";//酒店支付接口是否为测试 测试价格会改成0.01
	public static final String PRO_PAY_HOTEL_MAXCOUNT = "maxCount";//最大重试次数
	public static final String PRO_PAY_HOTEL_RETURN_URL = "returnURL";//回跳地址
	public static final String PRO_PAY_HOTEL_NOTIFY_URL = "notifyURL";//通知地址
	public static final String PRO_PAY_HOTEL_RETURN_URL_R = "hotel.returnURL";//回跳地址
	public static final String PRO_PAY_HOTEL_NOTIFY_URL_R = "hotel.notifyURL";//通知地址
	
	public static final String PRO_PAY_HOTELH5_NOTIFY_URL_R= "hotelH5.notifyURL"; //酒店h5项目  通知地址
	public static final String PRO_PAY_HOTELH5_RETURN_URL_R= "hotelH5.returnURL";  //酒店h5项目  回跳地址

	//以下为统一支付
	public static final String PRO_PAY_UNI_MAXCOUNT = "maxCount";
	public static final String PRO_PAY_UNI_DESKEY = "desKey";
	public static final String PRO_PAY_UNI_DESIV = "desIV";
	public static final String PRO_PAY_UNI_CHARSET = "charset";
	public static final String PRO_PAY_UNI_URL = "url";

	
	//application内容以 APP_ 开头
	public static final String APP_CTX = "ctx";//存储项目名  wetrip
	public static final String APP_MAP_3CHAR_CITY = "cityMap";//三字码-城市名map
	public static final String APP_MAP_3CHAR_AIRPORTNAME = "airportNameMap";//三字码-机场名 map
	public static final String APP_MAP_PLANETYPE = "planeTypeMap";//机型-机型名称 map
	public static final String APP_MAP_CABINNAME = "cabinNameMap";//航空公司二字码_舱位码-舱位名称  map
	public static final String APP_MAP_CABINCODE = "cabinCodeMap";//航空公司二字码_舱位码-舱位名称  map
	public static final String APP_MAP_AC2NAME = "ac2NameMap";//航空公司二字码_舱位码-舱位名称  map
	public static final String APP_MAP_PAYTYPE = "paytypeMap";//订单支付方式 code_name   map
	
    public static final String APP_MAP_IDCARDTYPE="IDCARDTYPE_MAP";//乘机人证件数据字典
    public static final String APP_MAP_PERSONNELTYPE="PERSONNELTYPE_MAP";//乘机人证件数据字典
	
	//request内容以REQ_ 开头
	public static final String REQ_BASEPATH = "basepath";//存储http://localhost:8080
	public static final String REQ_PATH = "path";//存储完整路径 basepath + ctx 
	
	
	//session内容以 SESSION_ 开头
	public static final String SESSION_CONFIG_TOKEN = "token";
	public static final String SESSION_LOGIN_USER = "userinfo";
	public static final String SESSION_LOGIN_USERGROUP = "groupinfo";
	public static final String SESSION_LOGIN_ROLE = "role";
	public static final String SESSION_LOGIN_MENUS = "menus";
	public static final String SESSION_LOGIN_PERMISSIONS = "permissions";
	
	//缓存key以CACHE_开头
	public static final String CACHE_ENDORSE = "endorse";
	
	public static final String CACHE_CAIYUN_CACHE_KEY = "caiyun_accessToken_key";//彩云集团access_token缓存key
	
	/**
     * 三字码-城市名List<String(PEK_北京)> 
     */
    public static final String CACHE_3CHAR_CITY_LIST="3CHAR_CITY_LIST";
    /**
     * 大客户协议规则
     */
    public static final String CACHE_PREFERENTIAL_RULES="T_PREFERENTIAL_RULES";
    
    public static final String CACHE_PAYTYPE_MAP="PAYTYPE_MAP";
    
    public static final int INTEGRATION_OVERDUE=2;
	
	public static final int SIGN_COUNT=5;//连续签到5天送积分
	public static final int SIGN_INTEGRAL=6;//连续签到5天送6积分
	public static final int[] SIGN_INTEGRAL_ARRAYS={1,1,1,1,6};//连续签到5天送6积分
}
