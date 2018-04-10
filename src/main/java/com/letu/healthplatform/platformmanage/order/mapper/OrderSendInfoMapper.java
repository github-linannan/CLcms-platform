package com.letu.healthplatform.platformmanage.order.mapper;

import java.util.List;

import com.letu.healthplatform.platformmanage.order.model.OrderInfo;
import com.letu.healthplatform.platformmanage.order.model.OrderSendInfo;

public interface OrderSendInfoMapper {
	
//    List<OrderSendInfo>	selectByParam(OrderSendInfo record);
	
    int deleteByPrimaryKey(Integer orderSendId);

    int insert(OrderSendInfo record);

    int insertSelective(OrderSendInfo record);

    OrderSendInfo selectByPrimaryKey(Integer orderSendId);

    int updateByPrimaryKeySelective(OrderSendInfo record);

    int updateByPrimaryKey(OrderSendInfo record);
    
    
    List<OrderSendInfo>   selectByOrderNumber(OrderSendInfo record);
    
    /***
     * 根据订单状态 来判断主状态 是否修改
     * @param record
     * @return
     */
    List<OrderSendInfo>   selectByOrderNumberAndStatus(OrderSendInfo record);
}