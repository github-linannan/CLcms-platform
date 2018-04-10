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
import com.letu.healthplatform.platformmanage.order.model.OrderInfo;
import com.letu.healthplatform.platformmanage.order.model.OrderSendInfo;
import com.letu.healthplatform.platformmanage.order.service.OrderInfoService;
import com.letu.healthplatform.platformmanage.user.model.AppUserInfo;


/**
 * @author dongzhibo
 * @date 2018年2月9日 下午2:39:37
 * @version 1.0.0
 * @description  接收管理
 */
@RestController
@RequestMapping(value="acceptOrder")
public class AcceptOrderController {
	
	
	private Logger logger=LogManager.getLogger(AcceptOrderController.class);
	
	@Autowired
	private OrderInfoService orderInfoService;
	
	

	 //TODO  查询接收列表信息
	 @GetMapping(value="/findAccpectPage")
	 public Object findAccpectOrderInfoPage(@ModelAttribute OrderInfo orderInfo,
				@RequestParam(required=false,defaultValue="0") int page,
				@RequestParam(required=false,defaultValue="0") int pageSize){
		 orderInfo.setOrderInfoStatus("4");
		  return orderInfoService.findAccpectOrderInfoPage(orderInfo,page,pageSize);
	}
	 //TODO  查询结果录入列表信息
	 @GetMapping(value="/findSendGoodsList")
	 public Object findSendGoodsList(@ModelAttribute OrderInfo orderInfo,
			 @RequestParam(required=false,defaultValue="0") int page,
			 @RequestParam(required=false,defaultValue="0") int pageSize){
		 orderInfo.setOrderInfoStatus("5");
		 return orderInfoService.findAccpectOrderInfoPage(orderInfo,page,pageSize);
	 }
	 
	
	 //TODO  查询接收详情
	 @GetMapping(value="/findAccpectDetails/{orderinfoId}")
	 public Object findOrderDetails(@PathVariable String  orderinfoId){
		  return orderInfoService.findAccpectOrderInfoDetails(orderinfoId);
	 }
	 
	 
	 //TODO 
	 @PostMapping(value="/update")
	 public Object update(@ModelAttribute OrderInfo orderInfo){
		  return orderInfoService.updateOrder(orderInfo);
	 }
	 
	 
	//TODO 确认收货
	 @PostMapping(value="/updateOrderAndUserInfo")
	 public Object insert(@ModelAttribute OrderSendInfo orderSendInfo,
			 @ModelAttribute AppUserInfo appUserInfo){
		  return orderInfoService.insertOrUpdate(orderSendInfo, appUserInfo);
	 }
	 
	 
	
}
