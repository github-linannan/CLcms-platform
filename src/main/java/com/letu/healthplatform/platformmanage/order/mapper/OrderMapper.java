package com.letu.healthplatform.platformmanage.order.mapper;

import java.util.List;
import java.util.Map;

import com.letu.healthplatform.platformmanage.order.model.Order;

public interface OrderMapper {
   
    int deleteByPrimaryKey(Integer orderId);

    int insert(Order record);
   
    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
    
    /***
     * 根据订单相关条件 查询所有订单
     * @return
     */
    List<Map<String, Object>> selectByParam(Order order);
   
   /***
    * 查询用户的详细订单
    * @return
    */
    List<Map<String, Object>> selectOrderDetails(Order order);
   
   /***
    *  删除订单   状态修改为-9
    * @param orderNumber 订单
    * **/
   int udapteOrderStatus(Map<String, Object> params);
   
   /***
    *发货管理列表
    * @return
    */
   List<Map<String, Object>>  selectDeliverParam(Order order);
   /***
    *发货管理详情
    * @return
    */
   List<Map<String, Object>>  selectDeliverDetails(Order order);
   /***
    * 修改状态
    * @return
    */
   int  updateOrderStatus(Order order);
   
}