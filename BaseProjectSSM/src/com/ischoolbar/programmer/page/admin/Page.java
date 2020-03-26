package com.ischoolbar.programmer.page.admin;

import org.springframework.stereotype.Component;

/** 分页基本信息
* @author  作者: lubingliang: 
* @version 创建时间：2020年3月14日 上午10:48:04 
*/
@Component
public class Page {
	
	private int page = 1;// 当前页面
	
	private int rows ;//每页显示数量
	
	private int offset ; // 偏移量	对应数据库

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getOffset() {
		this.offset = (page - 1)*rows;
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	

}
