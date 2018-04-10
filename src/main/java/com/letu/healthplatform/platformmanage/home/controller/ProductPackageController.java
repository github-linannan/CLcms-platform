package com.letu.healthplatform.platformmanage.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.letu.healthplatform.platformmanage.home.model.ProductPackage;
import com.letu.healthplatform.platformmanage.home.service.ProductPackageService;

@RestController
@RequestMapping("/productPackage")
public class ProductPackageController {

	@Autowired
	private ProductPackageService productPackageService;
	
	@GetMapping(value="/selectByParam")
	public  Object  selectByParam(ProductPackage productPackage,@RequestParam(required = false,defaultValue="0")int page,@RequestParam(required = false,defaultValue="0")int pageSize){
	
		return productPackageService.selectByParam(productPackage, page, pageSize);
	}
	
	@PostMapping(value="/insertProductPackage")
	public  Object  insertPackage(ProductPackage productPackage){
		return productPackageService.insert(productPackage);
	}

	@PostMapping(value="/updateProductPackage")
    public Object  updatePackage(ProductPackage productPackage){
		return productPackageService.updateByPrimaryKey(productPackage);
    }
	
}
