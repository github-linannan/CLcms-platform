/**
 * 乐土精准医疗
 */
package com.letu.healthplatform.platformmanage.goods.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.letu.healthplatform.platformmanage.goods.model.GoodsType;
import com.letu.healthplatform.platformmanage.goods.service.GoodsTypeService;

/**
 * @author dzb
 * @date 2017年12月4日 下午4:31:08
 * @version 1.0
 * @description 商品类型表
 */
@RestController
@RequestMapping("/goodsType")
public class GoodsTypeController {
	
	private static final Logger log = LogManager.getLogger(GoodsTypeController.class);
	
	
	@Autowired
	private GoodsTypeService goodsTypeService;
		
	/***
	 * 
	 * 查询商品类型
	 * @return
	 */
	 @GetMapping(value="/findGoodsTypePage")
	 public Object findGoodsTypePage(@ModelAttribute GoodsType goodsType,
				@RequestParam(required=false,defaultValue="0") int page,
				@RequestParam(required=false,defaultValue="0") int pageSize){
		  return goodsTypeService.findGoodsTypePage(goodsType,page,pageSize);
	}
	 
	 
	/***
	 * 
	 * 查询商品类型
	 * @param goodstypeId 产品类型的ID
	 * @return
	 */
	 @GetMapping(value="/findGoodsType/{goodstypeId}")
	 public Object findGoodsType(@PathVariable String  goodstypeId){
		  return goodsTypeService.findGoodsType(goodstypeId);
	}
	 
	 
	/***
	 * 
	 * 新增商品类型
	 * @return
	 */
	 @PostMapping(value="/insert")
	 public Object insert(@ModelAttribute GoodsType goodsType){
		  return goodsTypeService.insertSelective(goodsType);
	}
	 
	/***
	 * 
	 * 修改商品类型
	 * @return
	 */
	 @PostMapping(value="/update")
	 public Object update(@ModelAttribute GoodsType goodsType){
		  return goodsTypeService.updateByPrimaryKeySelective(goodsType);
	}
	 
	 
	/***
	 * 
	 * 删除商品类型
	 * @param goodstypeIds 删除商品类型的ID
	 * @return
	 */
	 @GetMapping(value="/delete/{goodstypeIds}")
	 public Object delete(@PathVariable String  goodstypeIds){
		  return goodsTypeService.deleteByPrimaryKey(goodstypeIds);
	}
	 
	 
	 /***
	 * 
	 * 状态修改
	 * @param goodstypeIds 商品类型的ID
	 * @return
	 */
	 @GetMapping(value="/updateByStatus/{goodstypeIds}")
	 public Object updateByStatus(@PathVariable String  goodstypeIds){
		  return goodsTypeService.updateByStatus(goodstypeIds);
	}
	

}
