package com.letu.healthplatform.platformmanage.home.service;

import com.letu.healthplatform.platformmanage.home.model.ProductPackage;

public interface ProductPackageService {
	/**
	 * 分页查询 
	 * @param proPackage
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public  Object  selectByParam(ProductPackage proPackage,int currentPage,int pageSize);
	/***
	 * 新增
	 * @param proPackage
	 * @return
	 */
	public Object  insert(ProductPackage proPackage);
	
	/***
	 * 修改
	 * @param proPackage
	 * @return
	 */
	public Object updateByPrimaryKey(ProductPackage proPackage);
	
	
}
