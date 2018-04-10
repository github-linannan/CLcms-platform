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
import com.letu.healthplatform.platformmanage.sys.model.RoleMenu;
import com.letu.healthplatform.platformmanage.sys.service.LoginRoleService;
import com.letu.healthplatform.platformmanage.sys.service.RoleMenuService;

/**
 * @author dzb
 * @date 2017年12月29日 下午3:56:05
 * @version 1.0
 * @description 
 */
@RestController
@RequestMapping("/roleMenu")
public class RoleMenuController {

	
	private static final Logger log = LogManager.getLogger(LoginRoleController.class);
	  
	  @Autowired
	  private RoleMenuService roleMenuService;
	  
	  
	  
		/***
		 * 
		 * 查询角色资源关联关系
		 * @param tId 科室id
		 * @return
		 */
		 @GetMapping(value="/findRoleMenu/{tRoleId}")
		 public Object findRoleMenu(@PathVariable String  tRoleId){
			  return roleMenuService.findRoleMenu(tRoleId);
		}
	 
		/***
		 * 
		 * 新增菜单
		 * @return
		 */
		 @PostMapping(value="/insert")
		 public Object insert(@ModelAttribute RoleMenu record){
			  return roleMenuService.insertRoleMenuBatch(record);
		}
		
}
