package com.letu.healthplatform.platformmanage.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.letu.healthplatform.platformmanage.user.model.AppLogin;
import com.letu.healthplatform.platformmanage.user.service.AppLoginService;

@RestController
@RequestMapping("/appLogin")
public class AppLoginController {
	@Autowired
	private AppLoginService appLoginServiceImpl;
	
	@GetMapping(value="/selectByParam")
	public  Object  selectByParam(AppLogin appLogin,@RequestParam(required = false,defaultValue="0")Integer page,@RequestParam(required = false,defaultValue="0")Integer pageSize){
	
		return appLoginServiceImpl.selectByParam(appLogin, page, pageSize);
	}
	
/***
 * 修改状态 和密码
 * @param infomation
 * @return
 */
	@PostMapping(value="/updateAppLogin")
    public Object  updateInformation(AppLogin appLogin){
		return appLoginServiceImpl.updateStatusByPrimaryKey(appLogin);
    }
}
