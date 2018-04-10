package com.letu.healthplatform.platformmanage.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.letu.healthplatform.platformmanage.home.model.InformationType;
import com.letu.healthplatform.platformmanage.home.service.InfomationTypeService;
@RestController
@RequestMapping("/infomationType")
public class InformationTypeController {

	@Autowired
	private InfomationTypeService infomationTypeService;
	
	@GetMapping(value="/selectByParam")
	public  Object  selectByParam(InformationType infomationType,@RequestParam(required = false,defaultValue="0")int page,@RequestParam(required = false,defaultValue="0")int pageSize){
	
		return infomationTypeService.selectByParam(infomationType, page, pageSize);
	}
	
	@PostMapping(value="/insertInfomationType")
	public  Object  insertInformationType(InformationType infomationType){
		return infomationTypeService.insert(infomationType);
	}

	@PostMapping(value="/updateInfomationType")
    public Object  updateInformationType(InformationType infomationType){
		return infomationTypeService.updateByPrimaryKey(infomationType);
    }
}
