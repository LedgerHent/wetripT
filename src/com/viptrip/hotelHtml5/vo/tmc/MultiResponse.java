package com.viptrip.hotelHtml5.vo.tmc;

import java.util.List;

@SuppressWarnings("serial")
public class MultiResponse<T extends JhData> extends BaseResponse {
	
	private List<T> data;

	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}

}
