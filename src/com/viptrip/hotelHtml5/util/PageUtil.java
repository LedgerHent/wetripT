package com.viptrip.hotelHtml5.util;

import java.util.ArrayList;
import java.util.List;

public class PageUtil<T> {
		private Integer pageNum = 1;
		private Integer pageSize;// 每页5条数据
	    public Integer totalPages = 0; // 总页数
	    private Integer totalRows = 0; // 总数据数
	    
	    private Integer pageStartRow = 0;// 每页的起始数
	    private Integer pageEndRow = 0; // 每页显示数据的终止数
	    private boolean hasNextPage = false; // 是否有下一页
	    private boolean hasPreviousPage = false; // 是否有前一页
	    private List<T> list;

	    /**
	     * 
	     * @param list
	     * @param pageSize
	     */
	    public PageUtil(Integer pageSize,Integer total,Integer pageNum) {
	    	init(pageNum, pageSize,total);// 通过对象集，记录总数划分
	    }

	    /** */
	    /**
	     * 初始化list，并告之该list每页的记录数
	     * 
	     * @param list
	     * @param pageSize
	     */
	    public void init(Integer pageNum,Integer pageSize,Integer total) {
	    	this.pageNum = pageNum;
			this.pageSize = pageSize;
			totalRows = total;
			//设置页数
			if (pageNum == 0) {
			    this.setPageNum(1);
			    pageNum = 1;
			} else {
			    this.setPageNum(pageNum);
			}
			//获取总页数
			if ((totalRows % pageSize) == 0) {
			    totalPages = totalRows / pageSize;
			} else {
			    totalPages = totalRows / pageSize + 1;
			}
			//是否有下一页
			if (pageNum >= totalPages) {
			    hasNextPage = false;
			} else {
			    hasNextPage = true;
			}
			//是否有上一页
			hasPreviousPage = false;
			if ((pageNum - 1) > 0) {
			    hasPreviousPage = true;
			} else {
			    hasPreviousPage = false;
			}
	    }


	    public void setHasPreviousPage(boolean hasPreviousPage) {
	    	this.hasPreviousPage = hasPreviousPage;
	    }

	    public Integer getPageNum() {
			return pageNum;
		}

		public void setPageNum(Integer pageNum) {
			this.pageNum = pageNum;
		}

	    public Integer getTotalPages() {
		return totalPages;
	    }

	    public void setTotalPages(Integer totalPages) {
	    	this.totalPages = totalPages;
	    }
	    
	    public Integer getPageSize() {
			return pageSize;
		}

		public void setPageSize(Integer pageSize) {
			this.pageSize = pageSize;
		}

	    public Integer getTotalRows() {
	    	return totalRows;
	    }

	    public void setTotalRows(Integer totalRows) {
	    	this.totalRows = totalRows;
	    }

	    public Integer getPageStartRow() {
	    	return pageStartRow;
	    }

	    public void setPageStartRow(Integer pageStartRow) {
	    	this.pageStartRow = pageStartRow;
	    }

	    public void setPageEndRow(Integer pageEndRow) {
	    	this.pageEndRow = pageEndRow;
	    }

	    public boolean isHasNextPage() {
	    	return hasNextPage;
	    }

	    public void setHasNextPage(boolean hasNextPage) {
	    	this.hasNextPage = hasNextPage;
	    }

	    public List<T> getList() {
	    	return list;
	    }

	    public void setList(List<T> list) {
	    	this.list = list;
	    }

	    public boolean isHasPreviousPage() {
	    	return hasPreviousPage;
	    }

		public PageUtil() {
			super();
			// TODO Auto-generated constructor stub
		}
	    

}
