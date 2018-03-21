package com.viptrip.pay.task;

public class DefaultAlg implements Algorithm {

	private int bgNum;
	

	public DefaultAlg() {
		this.bgNum = 2;
	}


	public DefaultAlg(int bgNum) {
		if(bgNum<=0){
			bgNum = 2;
		}
		this.bgNum = bgNum;
	}

	@Override
	public Integer calc(int count) {
		if(count<=1){
			return 0;
		}else{
			Double result = Math.pow(bgNum*1D, 1D*(count-2));
			return result.intValue();
		}
		
	}


	public int getBgNum() {
		return bgNum;
	}


	public void setBgNum(int bgNum) {
		this.bgNum = bgNum;
	}
	
}
