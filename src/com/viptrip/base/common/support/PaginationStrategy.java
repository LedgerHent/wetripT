package com.viptrip.base.common.support;

public interface PaginationStrategy {
	/**
	 * 获取数据结束点
	 * @return
	 */
	public int getStartPoint();
	/**
	 * 获取数据起始点
	 * @return
	 */
	public int getEndPoint();
	/**
	 * 设置起分页始点和结束点
	 * @param currentPage
	 * @param pageSize
	 */
	public void setDataPoint(int currentPage,int pageSize);
}