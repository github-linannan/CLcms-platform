package com.letu.healthplatform.platformmanage.goods.mapper;

import java.util.List;
import java.util.Map;
import com.letu.healthplatform.platformmanage.goods.model.Goods;
import com.letu.healthplatform.platformmanage.goods.vo.GoodsInfoVo;

public interface GoodsMapper {
    
	
    int deleteByPrimaryKey(Integer goodsId);

   
    int insert(Goods record);

   
    int insertSelective(Goods record);

   
    GoodsInfoVo  selectByPrimaryKey(Integer goodsId);

   
    int updateByPrimaryKeySelective(Goods record);
    

    int updateByPrimaryKey(Goods record);
    
  
    List<GoodsInfoVo> selectByParam(GoodsInfoVo goodsInfoVo);
    

    /***
     * 根据 商品ids加载所有产品
     * @param ids
     * @return
     */
    List<Goods>  selectGoodsByIds(String[] ids);
    
    int  updateGoodsStatus(Map<String, Object> param);

}