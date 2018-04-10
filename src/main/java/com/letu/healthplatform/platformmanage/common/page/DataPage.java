/**
 * 乐土精准医疗
 */
package com.letu.healthplatform.platformmanage.common.page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dzb
 * @date 2017年11月30日 上午9:19:56
 * @version 1.0
 * @description 
 */
public class DataPage {

	private  int total;//总数
	/**
	 * 当前页
	 */
	private  int page=0;//当前页
	
	private  int pageSize=6;//每页显示多少条
	
	private  int startRow=0;
		
	private  List<?> entitys=new ArrayList<Object>();//数据结果集
	
	private  int totalPage=0;
	
	/**
	 * @return the total
	 */
	public  int getTotal() {
		return total;
	}
	
	//计算总页数的方式
	public  int getTotalPage(){
			if(pageSize==0){
				pageSize=6;
			}
		return  (total+pageSize-1) /pageSize >0 ?(total+pageSize-1) /pageSize :0;
	}
	
	/**
	 * @param total the total to set
	 */
	public  void setTotal(int total) {
		this.total = total;
	}
	
	
	/**
	 * @return the page
	 */
	public  int getPage() {
		return page;
	}
	
	
	/**
	 * @param page the page to set
	 */
	public  void setPage(int page) {
		this.page = page;
	}
	
	
	/**
	 * @return the rows
	 */
	public int getPageSize() {
		if(pageSize<=0){
			pageSize=10;
		}
		return pageSize;
	}
	
	
	/**
	 * @param rows the rows to set
	 */
	public  void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * @return the firstIndex
	 */
	public  int getStartRow() {
		startRow=pageSize*(page-1);//如果是oralce+1  mysql 不+1
		return startRow>0?startRow:0;
	}
	public  List<?> getEntitys() {
		return entitys;
	}
	public  void setEntitys(List<?> entitys) {
		this.entitys = entitys;
	}

}
