package com.letu.healthplatform.platformmanage.sys.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.letu.healthplatform.platformmanage.sys.model.Province;
import com.letu.healthplatform.platformmanage.sys.service.AreaService;
/**
 * @author dzb
 * @date 2017年12月26日 上午10:12:45
 * @version 1.0
 * @description   地区--区域
 */
@RestController
@RequestMapping("/area")
public class AreaController {
	
	private final static Logger log=LogManager.getLogger(AreaController.class);
	
	@Autowired
	private AreaService  areaServiceImpl;
	
	
	/***
	 * 根据PId获取省市区
	 * @param body
	 * @return
	 */
	@GetMapping(value="/findAreaPage")
	public  Object  findAreaPage(@ModelAttribute Province province,
			@RequestParam int page,@RequestParam int pageSize ){
	  return	areaServiceImpl.findAreaPage(province, page, pageSize);
	}
	
	
	
}
