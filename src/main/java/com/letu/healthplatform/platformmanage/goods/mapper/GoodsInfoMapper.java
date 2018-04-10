package com.letu.healthplatform.platformmanage.goods.mapper;

import java.util.List;

import com.letu.healthplatform.platformmanage.goods.model.GoodsInfo;

public interface GoodsInfoMapper {
	
    int deleteByPrimaryKey(Integer goodsinfoId);

    int insert(GoodsInfo record);

    int insertSelective(GoodsInfo record);

    GoodsInfo selectByPrimaryKey(Integer goodsinfoId);
    
    List<GoodsInfo> selectByParam(GoodsInfo record);

    int updateByPrimaryKeySelective(GoodsInfo record);

    int updateByPrimaryKey(GoodsInfo record);
    
    int updateByGoodsId(GoodsInfo record);
    
}