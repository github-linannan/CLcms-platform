/**
 * 乐土精准医疗有限公司
 */
package com.letu.healthplatform.platformmanage.order.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.letu.healthplatform.platformmanage.order.model.Order;
import com.letu.healthplatform.platformmanage.order.service.OrderService;

/**
 * @author dongzhibo
 * @date 2018年2月9日 下午2:38:54
 * @version 1.0.0
 * @description  发货管理
 */
@RestController
@RequestMapping(value="deliverOrder")
public class DeliverOrderController {
	
	private Logger log=LogManager.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;
	
	
	 //TODO  查询所有订单
	 @GetMapping(value="/findDeliverPage")
	 public Object findDeliverPage(@ModelAttribute Order order,
				@RequestParam(required=false,defaultValue="0") int page,
				@RequestParam(required=false,defaultValue="0") int pageSize){
		  return orderService.findDeliverPage(order,page,pageSize);
	 }
	 
	//查看详情
	 
	 //TODO  查询所有订单
	 @GetMapping(value="/findDeliverDetails/{orderNumber}")
	 public Object findDeliverDetails(@PathVariable String  orderNumber){
		  return orderService.findDeliverDetails(orderNumber);
	 }
	 

	//确认收货
	 //TODO 修改订单
	 @PostMapping(value="/update")
	 public Object update(@ModelAttribute Order order){
		  return orderService.updateOrder(order);
	}
	 

}
