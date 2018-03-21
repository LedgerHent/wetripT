package com.viptrip.util;

import java.math.BigInteger;
import java.util.Random;


public class TokenGenerator {

	private static final Random RANDOM = new Random(); 
	
	
	public static String  generator(){
		return new BigInteger(165, RANDOM).toString(36).toUpperCase();
	}
	
}
