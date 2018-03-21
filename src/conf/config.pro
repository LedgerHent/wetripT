##  全局配置文件    ##
##测试配置属性##
testPro=0
userId=299530
##航空公司图标地址##
iconServerURL=http://e.viptrip365.com/images/airline/
##cas 登录登出地址##
##casServerLoginURL=http://61.51.80.138:18080/cas/login
##casServerLogoutURL=http://61.51.80.138:18080/cas/logout
casServerLoginURL=http://we.viptrip365.com/cas/login
casServerLogoutURL=http://we.viptrip365.com/cas/logout

####server配置####
##配置前缀，多个用逗号,隔开##
serverPrefix=ticket,hotel,intlTicket,intlTicketPC,common,hotelHtml5,pay
##ticket server配置##
ticket.serverUrl=http://127.0.0.1:8080/wetrip/server/ticketServer
ticket.serverSec=2
ticket.serverCountInSec=20
ticket.controllerNameSpace=com.viptrip.wetrip.controller
ticket.ModelNameSpace=com.viptrip.wetrip.model
ticket.desKey=随意取值
ticket.desIV=ticketiv
ticket.charset=utf-8
##hotel server配置## 
hotel.serverUrl=http://127.0.0.1:8080/wetrip/server/hotelServer
hotel.serverSec=2
hotel.serverCountInSec=50
hotel.controllerNameSpace=com.viptrip.hotel.controller
hotel.ModelNameSpace=com.viptrip.hotel.model
hotel.desKey=凯撒酒店
hotel.desIV=iv4Hotel
hotel.charset=utf-8

##intlTicket server配置##
intlTicket.serverUrl=http://127.0.0.1:8080/wetrip/server/intlTicketServer
intlTicket.serverSec=2
intlTicket.serverCountInSec=20
intlTicket.controllerNameSpace=com.viptrip.intlAirticket.controller
intlTicket.ModelNameSpace=com.viptrip.intlAirticket.model
intlTicket.desKey=国际机票
intlTicket.desIV=inticket
intlTicket.charset=utf-8

##intlTicketPC server配置##
intlTicketPC.serverUrl=http://127.0.0.1:8080/wetrip/server/intlTicketPCServer
intlTicketPC.serverSec=2
intlTicketPC.serverCountInSec=20
intlTicketPC.controllerNameSpace=com.viptrip.intlAirticketPC.controller
intlTicketPC.ModelNameSpace=com.viptrip.intlAirticketPC.model
intlTicketPC.desKey=国际机票
intlTicketPC.desIV=intkt4pc
intlTicketPC.charset=utf-8

##common server配置##
common.serverUrl=http://127.0.0.1:8080/wetrip/server/commonServer
common.serverSec=2
common.serverCountInSec=20
common.controllerNameSpace=com.viptrip.common.controller
common.ModelNameSpace=com.viptrip.common.model
common.desKey=公共接口
common.desIV=comtiv
common.charset=utf-8
##cache manager 缓存时间  秒为单位##
cacheLivetime=600

##hotelHtml5 server配置## 
hotelHtml5.serverUrl=http://10.0.8.53:8081/caissa-tmc/server/hotelServer
hotelHtml5.desKey=景鸿H5
hotelHtml5.desIV=TmcHtml5
hotelHtml5.charset=utf-8
##
hotelHtml5.serverSec=1
hotelHtml5.serverCountInSec=10
hotelHtml5.controllerNameSpace=com.viptrip.hotelHtml5.controller
hotelHtml5.ModelNameSpace=com.viptrip.hotelHtml5.model


##支付##
pay.serverUrl=http://127.0.0.1:8080/wetrip/server/payServer
pay.serverSec=2
pay.serverCountInSec=20
pay.controllerNameSpace=com.viptrip.pay.controller
pay.ModelNameSpace=com.viptrip.pay.vo
pay.desKey=国际机票
pay.desIV=unipay2i
pay.charset=utf-8