package com.letu.healthplatform.platformmanage.user.service;

import java.util.List;

import com.letu.healthplatform.platformmanage.user.model.AppUser;
import com.letu.healthplatform.platformmanage.user.vo.AppUserVo;

public interface AppUserService {
	/**
	 * 分页查询 
	 * @param AppUserVo
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public Object  selectByParam(AppUser appUser,int currentPage,int pageSize);
  /***
   * 保存用户信息
   * @param appUser
   * @return
   */
	public   Object  addUser(AppUser  appUser);
	
    List<AppUserVo>  selectPatient(Integer tSiId);
	
	public   Object  updateUser(AppUser  appUser);
}
