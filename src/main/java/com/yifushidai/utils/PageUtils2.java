package com.yifushidai.utils;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 */
public class PageUtils2 implements Serializable {
	private static final long serialVersionUID = 1L;
	//总记录数
	private int totalCount;
	//列表数据
	private List<?> list;

	public PageUtils2(){

	}

	public PageUtils2(List<?> list, int totalCount){
		this.list = list;
		this.totalCount = totalCount;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}


}
