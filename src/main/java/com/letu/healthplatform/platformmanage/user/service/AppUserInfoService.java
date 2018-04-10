package com.letu.healthplatform.platformmanage.user.service;

import com.letu.healthplatform.platformmanage.user.model.AppUserInfo;

public interface AppUserInfoService {
	/**
	 * 分页查询 
	 * @param AppUser
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public Object  selectByParam(AppUserInfo appUserInfo);

}
