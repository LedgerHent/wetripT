package com.viptrip.hotel.model;

import com.viptrip.hotel.model.base.Response_Base;

public class Response_AddPassenger extends Response_Base{
	
		
	 
	/**
	 * 2017-7-4 hanzhicheng
	 */
	private static final long serialVersionUID = 1L;
		public long number;

		public long getNumber() {
			return number;
		}

		public void setNumber(long number) {
			this.number = number;
		} 
}
