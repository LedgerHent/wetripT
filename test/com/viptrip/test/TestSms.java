package com.viptrip.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class TestSms {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String encode = URLEncoder.encode("楌", "GB2312");
			System.out.println(encode);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
