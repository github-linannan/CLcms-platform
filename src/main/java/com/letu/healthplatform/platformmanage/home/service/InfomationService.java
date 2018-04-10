package com.letu.healthplatform.platformmanage.home.service;

import com.github.pagehelper.PageInfo;
import com.letu.healthplatform.platformmanage.home.model.Information;

public interface InfomationService {

	/**
	 * 分页查询 
	 * @param proPackage
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public  Object selectByParam(Information information,int currentPage,int pageSize);
	/***
	 * 新增
	 * @param proPackage
	 * @return
	 */
	public Object  insert(Information information);
	
	/***
	 * 修改
	 * @param proPackage
	 * @return
	 */
	public Object updateByPrimaryKey(Information information);
}
