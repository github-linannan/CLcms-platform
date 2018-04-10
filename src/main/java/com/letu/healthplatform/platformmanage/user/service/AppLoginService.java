package com.letu.healthplatform.platformmanage.user.service;

import com.letu.healthplatform.platformmanage.user.model.AppLogin;

public interface AppLoginService {
	/**
	 * 分页查询 
	 * @param proPackage
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public Object  selectByParam(AppLogin appLogin,int currentPage,int pageSize);

	
	/***
	 * 修改
	 * @param proPackage
	 * @return  status  2 注销，3 锁定
	 */
	public Object updateStatusByPrimaryKey(AppLogin appLogin);
}
