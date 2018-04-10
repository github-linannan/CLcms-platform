package com.letu.healthplatform.platformmanage.user.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.letu.healthplatform.platformmanage.common.util.MD5;
import com.letu.healthplatform.platformmanage.common.util.Rsp;
import com.letu.healthplatform.platformmanage.user.mapper.AppLoginMapper;
import com.letu.healthplatform.platformmanage.user.mapper.AppUserMapper;
import com.letu.healthplatform.platformmanage.user.model.AppLogin;
import com.letu.healthplatform.platformmanage.user.model.AppUser;
import com.letu.healthplatform.platformmanage.user.service.AppUserService;
import com.letu.healthplatform.platformmanage.user.vo.AppUserVo;


@Service
@Transactional
public class AppUserServiceImpl  implements  AppUserService {

	private static final  Logger  log=LoggerFactory.getLogger(AppUserServiceImpl.class);
	
	@Autowired
	private AppUserMapper  appUserMapper;
	@Autowired
	private AppLoginMapper appLoginMapper;
	
	@Override
	public Object selectByParam(AppUser appUser, int currentPage, int pageSize) {
		 if(currentPage>0&&pageSize>0)
			  PageHelper.startPage(currentPage, pageSize);
			 List<AppUser> allAppUser=appUserMapper.selectByParam(appUser);
			 if(currentPage>0&&pageSize>0){
			    return new PageInfo<>(allAppUser);
			 }
			 return Rsp.succ("查询成功", allAppUser);
	}


	@Override
	public List<AppUserVo> selectPatient(Integer tSiId) {
		List<AppUserVo> list =null;
		try {
			list= appUserMapper.selectPatient(tSiId);
		} catch (Exception e) {
			log.error("selectPatient", e);
		}
		return list;
	}

	public   Object  addUser(AppUser  appUser){
		AppLogin  appLogin=new AppLogin();
		appLogin.setLoginTelephone(appUser.getUserTelephone());
		appLogin.setLoginPassword(MD5.encryptPassword("123456"));
		appLogin.setUserName(appUser.getUserTelephone());
		appLogin.setLoginRegisterTime(new Date());
		int id=appLoginMapper.insertSelective(appLogin);
		appUser.setLoginId(appLogin.getLoginId());
		appUser.setUserRelationship(appUser.getUserRelationship()==null?28:appUser.getUserRelationship());
		int i=   appUserMapper.insertSelective(appUser);
		if(i>0){
			return Rsp.succ("成功");
		}else{
			return Rsp.fail("失败");
		}
	}
	
	
	public   Object  updateUser(AppUser  appUser){
		int i=   appUserMapper.updateByPrimaryKeySelective(appUser);
		if(i>0){
			return Rsp.succ("成功");
		}else{
			return Rsp.fail("失败");
		}
	}
	

}
