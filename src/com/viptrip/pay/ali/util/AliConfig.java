package com.viptrip.pay.ali.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.viptrip.resource.Const;
import com.viptrip.util.PropertiesUtils;
import com.viptrip.util.StringUtil;

public class AliConfig {
	private static Logger log = LoggerFactory.getLogger(AliConfig.class);
	
	public static final String ALI_SERVER_URL;
	public static final String ALI_APP_ID;
	public static final String ALI_PRIVATE_KEY;
	public static final String ALI_PUBLIC_KEY;
	public static final String SIGN_TYPE;
	
	private static final String DEFAULT_ALI_SERVER_URL ="https://openapi.alipay.com/gateway.do";
	private static final String DEFAULT_ALI_APP_ID = "2016062701557655";
	private static final String DEFAULT_ALI_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDOG9VyxAbwEiIqoZs8GxXs8Pst511pEDA4P/QKgAnhfLmDteXVEJlFNBEfsGM+eLHD0F3++UF33KQkabbGxG6NuUGZdIZnOFL4OTpDGbvsttlfB6NvjAm1K/W5tHEkRssCyTM5WyKU7jMuUIuOoC/jnoJ8e+/Zjeqqoi5LUc8gJ8kPA/Sm9gX8FaEq1cmlhV5BtvB8UPEVU7LO34524pcP4SNrO//Sr9oBgzA6GkwMYN1BtIbOwqwpUdgEjoFCqA/t1JepJxA+n656YJZDDgrK7OGyxrsaiQkJVNJF12SpMyV9thZ0pTwE9umPP0568M7+TklgcQqOZFHtqXvODfWHAgMBAAECggEALBq4qj5YXbcDqKu8VDhXkMRge4ou6vST3PWSe0y60I2oBQ8/h0BuC+kAYQ+VrSCWqZhFBPLBl6yXU1Nr0Uzne1xRNU1CFYRv/L4CQamYFSdrjHJN/uiwixxFEOAT9fdQKeB0Hanq1IPEX/zMYFDW7pZUxd6fjKZZuPnLfkKbV/3I+HxdKkwSqGl7zosYeMPiwSgFw1FvQIqNxo2Pk1VMLa6n1vvKy4OTptvI4h2YsHUa+ZY8PetxyxfKhV4LspcNZ/l2i+E5tPsyrGVD9QJlMTZA1d9T/zzOASMtcFGcWs6lBQF1tOlRsDAQf5rfhvk+p/uBp1v1uQLn/73xD6KcAQKBgQD1szqTKZ5Ef++apilHEWsGurGAXa0CZWzjb0cbCzKQKg1iyXPW5gKtKqoedWKEYiIpojbvRvJd3BmN4Obdc4xOwf96yOjEd6kmfdJDWk8jU2gbZVydGBQCiy0+TZec59loVEHfE9FLYbp+XZqzMFvRhyQX57x/7KhxaFG9aSJ9QQKBgQDWv7k2ucdh5V1LvRBFLFaXG1es9JjJaXrGTIzNXLP6k465FMqcKLoKn11h1Yoqa6LRuuxTjFi1SISQScJ9rqNr54IxE8xJU8wv70SRzxLVla9hK61nyJzMHb/rPCxFtAGuyKkIF66Um5w0lS11JHPiImzBaPobjdySDTF3weCYxwKBgBoGmCfKesdKvfilrS2n/KxDzlWIlrDwSW519RQ6RWrb6XsTV7/lsVbidA6x5HOdaXz7f9Iss7OZguNydUHz4WLeTc+VxII7IqG/JiyYPlXBmf985yiEymnSkEJPOcYXEQJqGmgk00Cm43hLNf3RG4jAReR6WY87ZERPBomHv22BAoGBAKv9kVELKWx2TmpCWa2M/Sy4cVfL7jIhkHsRBBPVky+0zjHdPQgkdhvb1uZsJ+QhoJ1on62qUcGe+sy0xlfybmAVYmf7+zkfLAGYF8rPlb4JzBR+7rqYKTww92xnHO4WZOKfQwGANgo/NrPGZknTkBwkhgXBFc7Lp0Qx3NSVKlQxAoGAXC70iuxHfKGQQ4b89IPlg0jqcfIOAdBcwAzAa82qAnX4xAYxCcTTbBTkZJFtRgAURJKA3QGzfetjlfdc3GVUvaz2PelDYGGeLy2XGKz3K0nkVaTf4teYLXq+VS3JzQozdTd0MyLeBcHvoHV62ROUK8ftMVKqa+QpluzgU8oCWVI=";
	private static final String DEFAULT_ALI_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzhvVcsQG8BIiKqGbPBsV7PD7LeddaRAwOD/0CoAJ4Xy5g7Xl1RCZRTQRH7BjPnixw9Bd/vlBd9ykJGm2xsRujblBmXSGZzhS+Dk6Qxm77LbZXwejb4wJtSv1ubRxJEbLAskzOVsilO4zLlCLjqAv456CfHvv2Y3qqqIuS1HPICfJDwP0pvYF/BWhKtXJpYVeQbbwfFDxFVOyzt+OduKXD+Ejazv/0q/aAYMwOhpMDGDdQbSGzsKsKVHYBI6BQqgP7dSXqScQPp+uemCWQw4Kyuzhssa7GokJCVTSRddkqTMlfbYWdKU8BPbpjz9OevDO/k5JYHEKjmRR7al7zg31hwIDAQAB";
	private static final String DEFAULT_SIGN_TYPE = "RSA2";
	
	
	static{
		String ALI_SERVER_URL1 ;
		String ALI_APP_ID1;
		String ALI_PRIVATE_KEY1;
		String ALI_PUBLIC_KEY1;
		String SIGN_TYPE1;
		log.info("开始加载支付宝支付配置文件");
		try {
			ALI_SERVER_URL1 = PropertiesUtils.getProperty(Const.PRO_ALI_SERVER_URL, Const.FILE_PAY_CONFIG);
			ALI_APP_ID1 = PropertiesUtils.getProperty(Const.PRO_ALI_APPID, Const.FILE_PAY_CONFIG);
			ALI_PRIVATE_KEY1 = PropertiesUtils.getProperty(Const.PRO_ALI_PRIVATE_KEY, Const.FILE_PAY_CONFIG);
			ALI_PUBLIC_KEY1 = PropertiesUtils.getProperty(Const.PRO_ALI_PUBLIC_KEY, Const.FILE_PAY_CONFIG);
			SIGN_TYPE1 = PropertiesUtils.getProperty(Const.PRO_ALI_SIGNTYPE, Const.FILE_PAY_CONFIG);
			Assert.notNull(ALI_SERVER_URL1);
			Assert.notNull(ALI_APP_ID1);
			Assert.notNull(ALI_SERVER_URL1);
			Assert.notNull(ALI_PRIVATE_KEY1);
			Assert.notNull(ALI_PUBLIC_KEY1);
			Assert.notNull(SIGN_TYPE1);
			log.info("加载支付宝支付配置文件成功");
		} catch (Exception e) {
			ALI_SERVER_URL1 = DEFAULT_ALI_SERVER_URL;
			ALI_APP_ID1 = DEFAULT_ALI_APP_ID;
			ALI_PRIVATE_KEY1 = DEFAULT_ALI_PRIVATE_KEY;
			ALI_PUBLIC_KEY1 = DEFAULT_ALI_PUBLIC_KEY;
			SIGN_TYPE1 = DEFAULT_SIGN_TYPE;
			log.info("加载支付宝支付配置文件失败\r\n" + StringUtil.getExceptionStr(e));
			log.info("加载支付宝支付配置文件失败,使用默认配置");
		}
		ALI_SERVER_URL = ALI_SERVER_URL1;
		ALI_APP_ID = ALI_APP_ID1;
		ALI_PRIVATE_KEY = ALI_PRIVATE_KEY1;
		ALI_PUBLIC_KEY = ALI_PUBLIC_KEY1;
		SIGN_TYPE = SIGN_TYPE1;
	}
	
	public enum CHARSET{
		UTF8("UTF-8"),GBK("GBK");
		private String set;
		
		private CHARSET(String set) {
			this.set = set;
		}
		
		public String value() {
			return set;
		}
	}
}
