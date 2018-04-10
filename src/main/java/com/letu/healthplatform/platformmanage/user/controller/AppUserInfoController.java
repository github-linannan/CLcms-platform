package com.letu.healthplatform.platformmanage.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.letu.healthplatform.platformmanage.user.model.AppUserInfo;
import com.letu.healthplatform.platformmanage.user.service.AppUserInfoService;

@RestController
@RequestMapping("/appUserInfo")
public class AppUserInfoController {
	@Autowired
	private AppUserInfoService appUserInfoServiceImpl;
	
	@GetMapping(value="/selectByParam")
	public  Object  selectByParam(AppUserInfo appUserInfo){
	
		return appUserInfoServiceImpl.selectByParam(appUserInfo);
	}
}
