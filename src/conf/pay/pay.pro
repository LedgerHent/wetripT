####支付设置####
##是否测试##
isTest=true
##酒店接口是否为测试##
hotelPayIsTest=true
##支付宝使用旧接口##
userOldInterface=false
##微信配置##
#附加信息#
wx_wap_name=凯撒商旅
wx_wap_url=
#公众号appid
#wx_appid=wxb0a059e1665238e6
wx_appid=wx1a2d9214cda67345
#公众号appsecret
wx_appsecret=
#证书路径
wx_certfile=
#公众平台商户号
wx_mchid=1355150102
#wx_mchid=1363014402
#公众平台商户key
wx_key=3232KFDKSFKS23FSkdfkdlgldlflsfls
#微信H5支付绑定域名
wx_domain=viptrip365.com
#微信支付结果通知URL
wx_notify_url_mweb=pay/wxntf_old.act
#微信支付回跳URL
wx_return_url_mweb=pay/wxrtn_old.act
#刷卡支付URL MICROPAY_URL
wx_micropay_url=https://api.mch.weixin.qq.com/pay/micropay
#统一下单URL
wx_unified_order_url=https://api.mch.weixin.qq.com/pay/unifiedorder
#查询订单URL
wx_order_query_url=https://api.mch.weixin.qq.com/pay/orderquery
#关闭订单URL
wx_close_order_url =https://api.mch.weixin.qq.com/pay/closeorder
#撤销订单
wx_reverse_url=https://api.mch.weixin.qq.com/secapi/pay/reverse
#申请退款
wx_refund_url=https://api.mch.weixin.qq.com/secapi/pay/refund
#查询退款
wx_refundquery_url=https://api.mch.weixin.qq.com/pay/refundquery
#下载对账单
wx_downloadbilly_url=https://api.mch.weixin.qq.com/pay/downloadbill
#交易保障
wx_report_url=https://api.mch.weixin.qq.com/payitil/report
#转换短链接
wx_short_url=https://api.mch.weixin.qq.com/tools/shorturl
#授权码查询openId接口
wx_authcodetoopenid_url=https://api.mch.weixin.qq.com/tools/authcodetoopenid
#获取token接口
wx_token_url=https://api.weixin.qq.com/cgi-bin/token
#获取ticket接口
wx_ticket_url=https://api.weixin.qq.com/cgi-bin/ticket/getticket
#获取oauth2授权接口
wx_oauth2_url=https://api.weixin.qq.com/sns/oauth2/access_token



##支付宝配置##
ali_serverUrl=https://openapi.alipay.com/gateway.do
ali_appId=2017083008462339
#私钥
ali_privateKey=MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDOG9VyxAbwEiIqoZs8GxXs8Pst511pEDA4P/QKgAnhfLmDteXVEJlFNBEfsGM+eLHD0F3++UF33KQkabbGxG6NuUGZdIZnOFL4OTpDGbvsttlfB6NvjAm1K/W5tHEkRssCyTM5WyKU7jMuUIuOoC/jnoJ8e+/Zjeqqoi5LUc8gJ8kPA/Sm9gX8FaEq1cmlhV5BtvB8UPEVU7LO34524pcP4SNrO//Sr9oBgzA6GkwMYN1BtIbOwqwpUdgEjoFCqA/t1JepJxA+n656YJZDDgrK7OGyxrsaiQkJVNJF12SpMyV9thZ0pTwE9umPP0568M7+TklgcQqOZFHtqXvODfWHAgMBAAECggEALBq4qj5YXbcDqKu8VDhXkMRge4ou6vST3PWSe0y60I2oBQ8/h0BuC+kAYQ+VrSCWqZhFBPLBl6yXU1Nr0Uzne1xRNU1CFYRv/L4CQamYFSdrjHJN/uiwixxFEOAT9fdQKeB0Hanq1IPEX/zMYFDW7pZUxd6fjKZZuPnLfkKbV/3I+HxdKkwSqGl7zosYeMPiwSgFw1FvQIqNxo2Pk1VMLa6n1vvKy4OTptvI4h2YsHUa+ZY8PetxyxfKhV4LspcNZ/l2i+E5tPsyrGVD9QJlMTZA1d9T/zzOASMtcFGcWs6lBQF1tOlRsDAQf5rfhvk+p/uBp1v1uQLn/73xD6KcAQKBgQD1szqTKZ5Ef++apilHEWsGurGAXa0CZWzjb0cbCzKQKg1iyXPW5gKtKqoedWKEYiIpojbvRvJd3BmN4Obdc4xOwf96yOjEd6kmfdJDWk8jU2gbZVydGBQCiy0+TZec59loVEHfE9FLYbp+XZqzMFvRhyQX57x/7KhxaFG9aSJ9QQKBgQDWv7k2ucdh5V1LvRBFLFaXG1es9JjJaXrGTIzNXLP6k465FMqcKLoKn11h1Yoqa6LRuuxTjFi1SISQScJ9rqNr54IxE8xJU8wv70SRzxLVla9hK61nyJzMHb/rPCxFtAGuyKkIF66Um5w0lS11JHPiImzBaPobjdySDTF3weCYxwKBgBoGmCfKesdKvfilrS2n/KxDzlWIlrDwSW519RQ6RWrb6XsTV7/lsVbidA6x5HOdaXz7f9Iss7OZguNydUHz4WLeTc+VxII7IqG/JiyYPlXBmf985yiEymnSkEJPOcYXEQJqGmgk00Cm43hLNf3RG4jAReR6WY87ZERPBomHv22BAoGBAKv9kVELKWx2TmpCWa2M/Sy4cVfL7jIhkHsRBBPVky+0zjHdPQgkdhvb1uZsJ+QhoJ1on62qUcGe+sy0xlfybmAVYmf7+zkfLAGYF8rPlb4JzBR+7rqYKTww92xnHO4WZOKfQwGANgo/NrPGZknTkBwkhgXBFc7Lp0Qx3NSVKlQxAoGAXC70iuxHfKGQQ4b89IPlg0jqcfIOAdBcwAzAa82qAnX4xAYxCcTTbBTkZJFtRgAURJKA3QGzfetjlfdc3GVUvaz2PelDYGGeLy2XGKz3K0nkVaTf4teYLXq+VS3JzQozdTd0MyLeBcHvoHV62ROUK8ftMVKqa+QpluzgU8oCWVI=
#公钥
#ali_pulicKey=MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzhvVcsQG8BIiKqGbPBsV7PD7LeddaRAwOD/0CoAJ4Xy5g7Xl1RCZRTQRH7BjPnixw9Bd/vlBd9ykJGm2xsRujblBmXSGZzhS+Dk6Qxm77LbZXwejb4wJtSv1ubRxJEbLAskzOVsilO4zLlCLjqAv456CfHvv2Y3qqqIuS1HPICfJDwP0pvYF/BWhKtXJpYVeQbbwfFDxFVOyzt+OduKXD+Ejazv/0q/aAYMwOhpMDGDdQbSGzsKsKVHYBI6BQqgP7dSXqScQPp+uemCWQw4Kyuzhssa7GokJCVTSRddkqTMlfbYWdKU8BPbpjz9OevDO/k5JYHEKjmRR7al7zg31hwIDAQAB
#支付宝公钥
ali_pulicKey=MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkiuFxCTLXT20DaNrsT1kXfTbe+kdlrqOCHkkb9RFN9WbyV58v7iG5NZQNDtEKbWpJEW/B4noQTaVWLVpjEV+sEKuUsnv9pDJTImCeAiPYOoPHOFCm10zHWZ/Uh+7BrVMN2TdrXbGZ1x2WEzYVCwI4LLN5qfhdC+EzxjrJwnauuozWUkAfzzlMw8AKDXaeJzqWKpFxCNGGctbiLbgl05JdytdVZfFvyoV9TYIVbRtXEqAuBIZeMKfHzWMM45zF1qWUXfIUXF6OYu08MXlJHDPbpwwi3nIyFSVhL38ScONLzj3eK5UP6uSUTdHFT2Vw7KNNCSIbv9nM3g/oEcIeTU2KQIDAQAB
#原支付宝publicKey##
#ali_pulicKey=MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAptDYphYe4ulfrIz7bgtMlRy/jhCEtiykxKjAsog9yyryecOi8ExSSrLqpxQcZCWn/D2+ocHkI2FfLVatCxzeznWHdVJ+eaE65Z36TFnCZUGvwhQUJmggUL99f+jb9xjuqaNx49eTdrM2k1NOwm7VAaz3rZVWA04Ac7Voz+PId87E8ZTb4UT9col53ZbxVLDUvxkzKwVTZ3Q2bbpPk2kM/JdBkZPSWFApPdhLsZknSz2blppaB7t+hch4NqO3dI/6PaA/RKqaGzAdaa2izAaOzNsP4Z1YArCTF5AQ0FNVzTm1hTvJhwtfzSQlH3tiImh3RgYrZoT9u4sfWoBXgGaBmwIDAQAB
ali_signType=RSA2
ali_url_notify_pay=pay/alintf_old.act
ali_url_return_pay=pay/alirtn_old.act

##老版支付宝配置##
ali_old_url=https://mapi.alipay.com/gateway.do
ali_old_service=create_direct_pay_by_user
#新key
ali_old_key=yh6xsgf8hih4sf86vonblwvle99leid2
#新pid
ali_old_partner=2088721803428399
#新email
ali_old_seller_email=fengmeixin@viptrip365.com
#原key
#ali_old_key=scv3fdhd7i4eqysa5vemilensobl3tx8
#原pid
#ali_old_partner=2088421258192381
#原email
#ali_old_seller_email=lvbingying@viptrip365.com
ali_old_payment_type=1
ali_old_charset=utf-8

##易宝支付##
#商户编号
yeepay_MerId=10012414518
#密钥
yeepay_key=12TiI13X6v214JA87hlY5G4m18I5t42078FTjnx58DMGAd33E5v60682e199
#下单地址
yeepay_url_pay=https://www.yeepay.com/app-merchant-proxy/node
#查询地址
yeepay_url_query=https://cha.yeepay.com/app-merchant-proxy/command
#退款地址
yeepay_url_refund=https://cha.yeepay.com/app-merchant-proxy/command
#退款查询地址
yeepay_url_refundquery=https://www.yeepay.com/app-merchant-proxy/node
#订单取消地址
yeepay_url_ordercancle=https://cha.yeepay.com/app-merchant-proxy/command



####国内机票支付接入统一支付参数####
return_url=pay/rtn.act
notify_url=pay/ntf.act


##通知发送最大次数##
maxCount=6
##支付通知加密信息##
desKey=景鸿H5
desIV=TmcHtml5
charset=utf-8
url=http://61.51.80.138:58899/wetrip/