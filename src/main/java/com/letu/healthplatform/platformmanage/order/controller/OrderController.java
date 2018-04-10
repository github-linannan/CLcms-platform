package com.letu.healthplatform.platformmanage.order.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.letu.healthplatform.platformmanage.order.model.Order;
import com.letu.healthplatform.platformmanage.order.service.OrderService;


@RestController
@RequestMapping("/order")
public class OrderController {

	private Logger log=LogManager.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;
	
	
	 //TODO  查询所有订单
	 @GetMapping(value="/findOrderPage")
	 public Object findOrderPage(@ModelAttribute Order order,
				@RequestParam(required=false,defaultValue="0") int page,
				@RequestParam(required=false,defaultValue="0") int pageSize){
		  return orderService.findOrderPage(order,page,pageSize);
	}
	 
	
	 //TODO  查询订单详情
	 @GetMapping(value="/findOrderDetails/{orderId}")
	 public Object findOrderDetails(@PathVariable String  orderId){
		  return orderService.findOrderDetails(orderId);
	}
	 

	 //TODO 修改订单
	 @PostMapping(value="/update")
	 public Object update(@ModelAttribute Order order){
		  return orderService.updateOrder(order);
	 }
	 

	 //TODO 修改订单状态
	 //订单状态 0：未支付，1：支付成功，2：已发货（盒子） 3：已回寄，
	 //4：已收到样品，5：检测中，6：报告待审核，7：完成报告 。-1支付失败，-2申请退款，-3已退款 -9 删除订单  
	 @PostMapping(value="/updateOrderStatus/{orderStatus}/{orderIds}")
	 public Object updateOrderStatus(@PathVariable String orderStatus,@PathVariable String  orderIds){
		  return orderService.updateOrderStatus(orderIds, orderStatus);
	}
    
	
}
