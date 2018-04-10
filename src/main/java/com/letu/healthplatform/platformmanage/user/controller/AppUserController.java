package com.letu.healthplatform.platformmanage.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.letu.healthplatform.platformmanage.user.model.AppUser;
import com.letu.healthplatform.platformmanage.user.service.AppUserService;

@RestController
@RequestMapping("/appUser")
public class AppUserController {
	@Autowired
	private AppUserService appUserServiceImpl;
	
	@GetMapping(value="/selectByParam")
	public  Object  selectByParam(AppUser appUser,@RequestParam(required = false,defaultValue="0")Integer page,@RequestParam(required = false,defaultValue="0")Integer pageSize){
	
		return appUserServiceImpl.selectByParam(appUser, page, pageSize);
	}
	@PostMapping(value="/addUser")
	public  Object  addUser(AppUser appUser){
		
		return appUserServiceImpl.addUser(appUser);
	}
	@PostMapping(value="/updateUser")
	public  Object  updateUser(AppUser appUser){
		
		return appUserServiceImpl.updateUser(appUser);
	}
	

}
