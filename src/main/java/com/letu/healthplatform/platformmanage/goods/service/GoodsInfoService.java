package com.letu.healthplatform.platformmanage.goods.service;

import com.letu.healthplatform.platformmanage.goods.model.GoodsInfo;

/**
 * @author dongzhibo
 * @date 2017年11月29日 上午11:08:49
 * @version 1.0
 * @description
 */
public interface GoodsInfoService {
	
	Object deleteByPrimaryKey(String goodsId);

	Object insertSelective(GoodsInfo record);
   
    Object findGoodsInfo(String  goodsId);

    Object updateByPrimaryKeySelective(GoodsInfo record);

    Object findGoodsInfoPage(GoodsInfo goodsInfo,int page,int pageSize);
    
	
}
