package com.letu.healthplatform.platformmanage.order.mapper;

import java.util.List;
import java.util.Map;

import com.letu.healthplatform.platformmanage.order.model.OrderInfo;


public interface OrderInfoMapper {
   
    int deleteByPrimaryKey(Integer orderinfoId);

    int insert(OrderInfo record);

    int insertSelective(OrderInfo record);
 
    OrderInfo selectByPrimaryKey(Integer orderinfoId);

    int updateByPrimaryKeySelective(OrderInfo record);

    int updateByPrimaryKey(OrderInfo record);

    int insertOrderInfoBatch(List<OrderInfo> list);
    
    List<Map<String,Object>>   selectOrderInfoDetails(OrderInfo record);
    /**
     * 收货管理列表
     * 
     * **/
    List<Map<String,Object>>   selectAcceptParam(OrderInfo record);
    /**
     * 收货管理详情
     * 
     * **/
    List<Map<String,Object>>    selectAcceptDetails(OrderInfo record);
    
    
    List<OrderInfo>  selectByOrderNumber(OrderInfo record);
    

    
}