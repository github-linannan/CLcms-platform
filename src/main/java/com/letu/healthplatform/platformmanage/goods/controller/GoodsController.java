/**
 * 乐土精准医疗
 */
package com.letu.healthplatform.platformmanage.goods.controller;

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
import com.letu.healthplatform.platformmanage.goods.model.Goods;
import com.letu.healthplatform.platformmanage.goods.model.GoodsInfo;
import com.letu.healthplatform.platformmanage.goods.service.GoodsService;
import com.letu.healthplatform.platformmanage.goods.vo.GoodsInfoVo;


/**
 * @author dzb
 * @date 2017年12月4日 下午4:31:08
 * @version 1.0
 * @description   商品表
 */
@RestController
@RequestMapping("/goods")
public class GoodsController{
	
	private static final Logger log = LogManager.getLogger(GoodsController.class);
	
	@Autowired
	private GoodsService goodsService;
	
	 
	//TOD　查询商品
	/***
	 * 
	 * 查询商品
	 * @return
	 */
	 @GetMapping(value="/findGoodsPage")
	 public Object findGoodsPage(@ModelAttribute GoodsInfoVo goodsInfoVo,
				@RequestParam(required=false,defaultValue="0") int page,
				@RequestParam(required=false,defaultValue="0") int pageSize){
		  return goodsService.findGoodsPage(goodsInfoVo,page,pageSize);
	}
	 
	 
	/***
	 * 
	 * 查询商品
	 * @param goodsId 产品的ID
	 * @return
	 */
	 @GetMapping(value="/findGoods/{goodsId}")
	 public Object findGoods(@PathVariable String  goodsId){
		  return goodsService.findGoods(goodsId);
	}
	 
	 //TODO  新增商品
	/***
	 * 
	 * 新增商品
	 * @return
	 */
	 @PostMapping(value="/insert")
	 public Object insert(@ModelAttribute Goods goods,@ModelAttribute GoodsInfo goodsInfo ){
		  return goodsService.insertSelective(goods,goodsInfo);
	}
	 
	/***
	 * 
	 * 修改商品
	 * @return
	 */
	 @PostMapping(value="/update")
	 public Object update(@ModelAttribute Goods goods,@ModelAttribute GoodsInfo goodsInfo){
			 return goodsService.updateByPrimaryKeySelective(goods,goodsInfo);
	}
	 
	 //TODO 删除商品
	/***
	 * 
	 * 删除商品
	 * @param goodsId  删除商品的ID
	 * @return
	 */
	 @GetMapping(value="/delete/{goodsId}")
	 public Object delete(@PathVariable String  goodsId){
		  return goodsService.deleteByPrimaryKey(goodsId);
	}
	 
	 //TODO 商品上架和下架
	 @PostMapping(value="/updateGoodsStatus/{goodsIds}/{goodsStatus}")
	 public Object updateGoodsStatus(@PathVariable String goodsIds,
			 @PathVariable int goodsStatus){
		 return goodsService.updateGoodsStatus(goodsIds, goodsStatus);
	} 

}
