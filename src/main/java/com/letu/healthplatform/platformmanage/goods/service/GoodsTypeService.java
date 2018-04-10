/**
 * 乐土精准医疗
 */
package com.letu.healthplatform.platformmanage.goods.service;

import com.letu.healthplatform.platformmanage.goods.model.GoodsType;

/**
 * @author dzb
 * @date 2017年12月4日 下午4:18:32
 * @version 1.0
 * @description 
 */
public interface GoodsTypeService {
	
   public Object  findGoodsTypePage(GoodsType goodsType,int currentPage,int pageSize);
   
   Object findGoodsType(String goodstypeId);
   
   Object insertSelective(GoodsType record);
   
   Object updateByPrimaryKeySelective(GoodsType record);
   
   Object deleteByPrimaryKey(String goodstypeIds);
   
   Object updateByStatus(String goodstypeIds);
}
