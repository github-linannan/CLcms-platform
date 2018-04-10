package com.letu.healthplatform.platformmanage.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.letu.healthplatform.platformmanage.home.model.ProductBanner;
import com.letu.healthplatform.platformmanage.home.service.ProductBannerService;

@RestController
@RequestMapping("/proBanner")
public class ProductBannerController {

	@Autowired
	private ProductBannerService productBannerService;
	
	@GetMapping(value="/selectByParam")
	public  Object  selectByParam(ProductBanner productBanner,@RequestParam(required = false,defaultValue="0")int page,@RequestParam(required = false,defaultValue="0")int pageSize){
	
		return productBannerService.selectByParam(productBanner, page, pageSize);
	}
	
	@PostMapping(value="/insertBanner")
	public  Object  insertBanner(ProductBanner productBanner){
		return productBannerService.insert(productBanner);
	}

	@PostMapping(value="/updateBanner")
    public Object  updateBanner(ProductBanner productBanner){
		return productBannerService.updateByPrimaryKey(productBanner);
    }
	
}
