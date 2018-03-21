package com.viptrip.util;

import java.util.List;


public class Pager<T> {
	private static int DEFAULT_PAGE_NUM = 1;
	private static int DEFAULT_PAGE_SIZE = 20;
	//结果集
	private List<T> list;
	
	//页码
	private int pageNum;
	
	//页大小
	private int pageSize;
	
	//总记录数
	private int totalCount;
	
	private int totalPage;
	
	public Pager() {
		this.pageNum = DEFAULT_PAGE_NUM;
		this.pageSize = DEFAULT_PAGE_SIZE;
	}
	
	public Pager(int pageNum){
		this.pageNum = pageNum;
		this.pageSize = DEFAULT_PAGE_SIZE;
	}
	
	public Pager(int pageNum,int pageSize){
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}
	
	public Pager(PageParam pp){
		if(pp==null){
			pp = new PageParam();
		}
		this.pageNum = pp.getPageNum()==null?DEFAULT_PAGE_NUM:pp.getPageNum();
		this.pageSize = pp.getNumPerPage()==null?DEFAULT_PAGE_SIZE:pp.getNumPerPage();
	}
	
	public Pager(PageParam pp,int totalCount){
		this(pp);
		this.totalCount = totalCount;
		init();
	}
	
	public Pager(int pageNum,int pageSize,int totalCount){
		this.pageNum = Math.max(pageNum,DEFAULT_PAGE_NUM);
		this.pageSize = pageSize<=0?DEFAULT_PAGE_SIZE:pageSize;
		this.totalCount = totalCount;
		init();
	}
	
	public Pager(int pageNum,int pageSize,long totalCount){
		this.pageNum = Math.max(pageNum,DEFAULT_PAGE_NUM);
		this.pageSize = pageSize<=0?DEFAULT_PAGE_SIZE:pageSize;
		this.totalCount = (int)totalCount;
		init();
	}
	
	/**
	 * 此处可以传入未进行分页的list结果集，然后自动进行分页设置
	 * @param pageNum
	 * @param pageSize
	 * @param list
	 */
	public Pager(int pageNum,int pageSize,List<T> list){
		this.pageNum = pageNum<=0?DEFAULT_PAGE_NUM:pageNum;
		this.pageSize = pageSize<=0?DEFAULT_PAGE_SIZE:pageSize;
		if(null!=list&&list.size()>=0){
			totalCount = list.size();
			init();
			this.list = list.subList(pageSize*(pageNum-1), Math.min(list.size(), pageSize*pageNum));
		}
	}
	
	private void init(){
		if(this.totalCount<=0){
			this.pageNum = 0;
			this.pageSize=20;
			this.totalCount = 0;
		}else{
			this.totalPage = (int)Math.ceil((double)this.totalCount/this.pageSize);//总页数
			if(this.pageNum>totalPage){
				pageNum = totalPage;
			}
		}
	}
	
	
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		init();
	}
	public int getTotalPage() {
		return totalPage;
	}

	public static void main(String[] args) {
		double d = (double)1/10;
		System.out.println(d);
		System.out.println(Math.ceil(d));
	}
	
}
