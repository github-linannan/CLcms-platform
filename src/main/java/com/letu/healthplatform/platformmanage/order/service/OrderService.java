package com.letu.healthplatform.platformmanage.order.service;

import java.util.List;
import java.util.Map;

import com.letu.healthplatform.platformmanage.order.model.Order;

public interface OrderService {
	
	
	
	/***
	 * 查询所有订单
	 * @param body
	 * @return
	 */
	public Object  findOrderPage(Order order,int page,int pageSize);
	
	
	
	/***
	 * 查询订单详情
	 * @param  订单编号
	 * @return
	 */
	public Object findOrderDetails(String orderId);
	/***
	 * 修改订单
	 * @param  
	 * @return
	 */
	public Object updateOrder(Order order);
	/***
	 * 订单状态修改
	 * @param  
	 * @return
	 */
	public Object updateOrderStatus(String orderIds,String  orderStatus);
	
	
   /***
    *发货管理列表
    * @return
    */
	public Object  findDeliverPage(Order order,int page,int pageSize);
	
   /***
    * 发货列表详情
    * @param  orderNumber  订单编号
    * @return
    */
	public Object  findDeliverDetails(String orderNumber);
	
}
