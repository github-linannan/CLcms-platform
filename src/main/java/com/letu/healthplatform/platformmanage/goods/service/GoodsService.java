package com.letu.healthplatform.platformmanage.goods.service;



import com.letu.healthplatform.platformmanage.goods.model.Goods;
import com.letu.healthplatform.platformmanage.goods.model.GoodsInfo;
import com.letu.healthplatform.platformmanage.goods.vo.GoodsInfoVo;

public interface GoodsService {
	
	Object deleteByPrimaryKey(String goodsId);

	Object insertSelective(Goods record,GoodsInfo goodsInfo);
   
    Object findGoods(String  goodsId);

    Object updateByPrimaryKeySelective(Goods record,GoodsInfo goodsInfo);

    Object findGoodsPage(GoodsInfoVo goodsInfoVo,int page,int pageSize);
    
    Object  updateGoodsStatus(String goodsIds,int goodsStatus);
	
}
