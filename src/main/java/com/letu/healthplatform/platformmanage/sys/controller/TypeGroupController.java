package com.letu.healthplatform.platformmanage.sys.controller;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.letu.healthplatform.platformmanage.sys.model.TypeGroup;
import com.letu.healthplatform.platformmanage.sys.service.TypeGroupService;

@RestController
@RequestMapping("/typeGroup")
public class TypeGroupController {

     private static final Logger logger = LogManager.getLogger(TypeGroupController.class);
	
	@Autowired
	private TypeGroupService TypeGroupService;
	
	@GetMapping(value="/findTypeGroupPage")
	public Object findDepartmentPage(@ModelAttribute TypeGroup record,	
			@RequestParam(required=false,defaultValue="0") int page,
			@RequestParam(required=false,defaultValue="0") int pageSize){
	 return TypeGroupService.findTypeGroupPage(record,page,pageSize);
	}
	
	
	@GetMapping(value="/findTypeGroup/{id}")
	public Object findTypeGroup(@PathVariable String  id){
	 return TypeGroupService.findTypeGroup(id);
	}
	
	
	@GetMapping(value="/findAllTypeGroup")
	public Object findAllTypeGroup(@ModelAttribute TypeGroup record){
	 return TypeGroupService.findAllTypeGroup(record);
	}
	
	@PostMapping(value="/insert") public Object insert(@ModelAttribute TypeGroup record){
	 return TypeGroupService.insertSelective(record);
	}
	
	 @PostMapping(value="/update")
	 public Object update(@ModelAttribute TypeGroup record){
	  return TypeGroupService.updateByPrimaryKeySelective(record);
	}
	
	@GetMapping(value="/delete/{id}")
	public Object delete(@PathVariable String  id){
	  return TypeGroupService.deleteByPrimaryKey(id);
	}
	
	
}
