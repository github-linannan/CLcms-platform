/**
 * 乐土基因
 */
package com.letu.healthplatform.platformmanage.sys.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.letu.healthplatform.platformmanage.sys.model.LoginRole;
import com.letu.healthplatform.platformmanage.sys.model.Menu;
import com.letu.healthplatform.platformmanage.sys.service.LoginRoleService;

/**
 * @author dzb
 * @date 2017年12月29日 下午3:04:27
 * @version 1.0
 * @description 
 */
@RestController
@RequestMapping("/loginRole")
public class LoginRoleController {
	
	  private static final Logger log = LogManager.getLogger(LoginRoleController.class);
	  
	  @Autowired
	  private LoginRoleService loginRoleService;
	  
	/***
	 * 
	 * 查询用户角色关联关系
	 * @param tId 科室id
	 * @return
	 */
	 @GetMapping(value="/findLoginRole/{tLoginId}")
	 public Object findLoginRole(@PathVariable String  tLoginId){
		  return loginRoleService.findLoginRole(tLoginId);
	}
	  
	  
	/***
	 * 
	 * 新增菜单
	 * @return
	 */
	 @PostMapping(value="/insert")
	 public Object insert(@ModelAttribute LoginRole record){
		  return loginRoleService.insertLoginRoleBatch(record);
	}
	  

}
