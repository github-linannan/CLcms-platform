package com.letu.healthplatform.platformmanage.goods.mapper;

import java.util.List;

import com.letu.healthplatform.platformmanage.goods.model.GoodsType;

public interface GoodsTypeMapper {
	
   
	/**
	 * @param goodsType
	 * @return
	 */
	List<GoodsType> selectGoodsTypeAll(GoodsType goodsType);
	
    GoodsType selectByPrimaryKey(Integer goodstypeId);
   
    int insert(GoodsType record);

    int insertSelective(GoodsType record);

    int updateByPrimaryKeySelective(GoodsType record);
   
    int updateByPrimaryKey(GoodsType record);
    
    int updateByStatus(String[] goodstypeIds);
    
    int deleteByPrimaryKey(String[] goodstypeIds);

    
}