/**
 * 乐土精准医疗
 */
package com.letu.healthplatform.platformmanage.goods.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.letu.healthplatform.platformmanage.goods.service.GoodsInfoService;


/**
 * @author dzb
 * @date 2017年11月29日 上午10:52:29
 * @version 1.0
 */
@RestController
@RequestMapping("/goodsInfo")
public class GoodsInfoController{
	
	private static final Logger log = LogManager.getLogger(GoodsInfoController.class);
	
	@Autowired
	private GoodsInfoService goodsInfoService;
	
	/***
	 * 
	 * @param goodsId 商品ID
	 * @return
	 */
	 @PostMapping(value="/findGoodsInfoList")
	 public Object findGoodsInfoList(@RequestBody String body){
		  return goodsInfoService.findGoodsInfo(body);
	}

}
