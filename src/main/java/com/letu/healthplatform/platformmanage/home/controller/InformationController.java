package com.letu.healthplatform.platformmanage.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.letu.healthplatform.platformmanage.home.model.Information;
import com.letu.healthplatform.platformmanage.home.service.InfomationService;

@RestController
@RequestMapping("/infomation")
public class InformationController {
	@Autowired
	private InfomationService infomationService;
	
	@GetMapping(value="/selectByParam")
	public  Object  selectByParam(Information infomation,@RequestParam(required = false,defaultValue="0")Integer page,@RequestParam(required = false,defaultValue="0")Integer pageSize){
	
		return infomationService.selectByParam(infomation, page, pageSize);
	}
	
	@PostMapping(value="/insertInfomation")
	public  Object  insertInformation(Information infomation){
		return infomationService.insert(infomation);
	}

	@PostMapping(value="/updateInfomation")
    public Object  updateInformation(Information infomation){
		return infomationService.updateByPrimaryKey(infomation);
    }
}
