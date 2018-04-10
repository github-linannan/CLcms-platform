package com.letu.healthplatform.platformmanage.report.service;

import java.util.List;
import java.util.Map;

import com.letu.healthplatform.platformmanage.report.model.SortMessage;

public interface SortMessageService {

	public boolean  insertSortMessage(SortMessage message);
	
	public Object  selectByParam(SortMessage   message, int currentPage, int pageSize);
	
	//统计查询
	public Object  queryByParam( Map<String,Object> param, int currentPage, int pageSize);
	/**
	 * 导出查询数据
	 * @param param  参数
	 * @param currentPage
	 * @param pageSize
	 * @return  
	 */
	Object queryExportParam( Map<String,Object> param, int currentPage, int pageSize);
	
	public Object sendSortMessage(String ids);
	/***
	 * 忽略发送短信
	 * @param ids
	 * @return
	 */
	public Object ignoreSortMessage(String ids);
	
	
	public Object sendEmailMessage(String ids);
	
	
	public boolean sendEmail(Map<Object,List<Map<String,Object>>> sendEmails);
}
