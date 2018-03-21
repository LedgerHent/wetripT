package com.viptrip.util;

/**
 * 页面查询分页参数
 * @author selfwhisper
 *
 */
public class PageParam {
	private static int DEFAULT_PAGE_NUM = 1;
	private static int DEFAULT_PAGE_SIZE = 20;
	private static int DEFAULT_PAGE_NUM_SHOWN = 10;
	private Integer numPerPage;//一页显示多少条
	private Integer pageNumShown;//导航显示多少数字pageNumShown
	private Integer pageNum;
	private Integer totalPage;
	
	
	public PageParam() {
		this(DEFAULT_PAGE_NUM, DEFAULT_PAGE_SIZE, null);
	}
	
	public PageParam(Integer pageNum,Integer numPerPage){
		this(pageNum, numPerPage, null);
	}
	
	public PageParam(Integer pageNum,Integer numPerPage,Integer pageNumShown){
		this.pageNum = (pageNum==null||pageNum<=0)?DEFAULT_PAGE_NUM:pageNum;
		this.numPerPage = (numPerPage==null||numPerPage<=0)?DEFAULT_PAGE_SIZE:numPerPage;
		this.pageNumShown = (pageNumShown==null||pageNumShown<=0)?DEFAULT_PAGE_NUM_SHOWN:pageNumShown;
	}
	
	public PageParam fill(){
		if(this.getPageNum()==null||this.getPageNum()<=0)
			this.pageNum = DEFAULT_PAGE_NUM;
		if(this.getNumPerPage()==null||this.getNumPerPage()<=0)
			this.numPerPage = DEFAULT_PAGE_SIZE;
		if(this.getPageNumShown()==null||this.getPageNumShown()<=0)
			this.pageNumShown = DEFAULT_PAGE_NUM_SHOWN;
		return this;
	}

	public Integer getNumPerPage() {
		return numPerPage;
	}
	public void setNumPerPage(Integer numPerPage) {
		this.numPerPage = numPerPage;
	}
	public Integer getPageNumShown() {
		return pageNumShown;
	}
	public void setPageNumShown(Integer pageNumShown) {
		this.pageNumShown = pageNumShown;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		if (pageNum == null || pageNum <= 0) {
			pageNum = DEFAULT_PAGE_NUM;
		}
		this.pageNum = pageNum;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	
	
}
