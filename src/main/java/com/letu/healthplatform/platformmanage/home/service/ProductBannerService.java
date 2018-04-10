package com.letu.healthplatform.platformmanage.home.service;

import com.letu.healthplatform.platformmanage.home.model.ProductBanner;

public interface ProductBannerService {
	/**
	 * 分页查询 
	 * @param ProductBanner proBanner
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public  Object  selectByParam(ProductBanner proBanner,int currentPage,int pageSize);
	/***
	 * 新增
	 * @param ProductBanner proBanner
	 * @return
	 */
	public Object  insert(ProductBanner proBanner);
	
	/***
	 * 修改
	 * @param ProductBanner proBanner
	 * @return
	 */
	public Object updateByPrimaryKey(ProductBanner proBanner);
	
	
}
