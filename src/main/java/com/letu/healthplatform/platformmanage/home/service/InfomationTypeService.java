package com.letu.healthplatform.platformmanage.home.service;

import com.github.pagehelper.PageInfo;
import com.letu.healthplatform.platformmanage.home.model.InformationType;

public interface InfomationTypeService {

	/**
	 * 分页查询 
	 * @param proPackage
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public Object  selectByParam(InformationType information,int currentPage,int pageSize);
	/***
	 * 新增
	 * @param proPackage
	 * @return
	 */
	public Object  insert(InformationType information);
	
	/***
	 * 修改
	 * @param proPackage
	 * @return
	 */
	public Object updateByPrimaryKey(InformationType information);
}
