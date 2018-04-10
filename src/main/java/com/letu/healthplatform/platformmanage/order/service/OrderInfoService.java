/**
 * 乐土精准医疗有限公司
 */
package com.letu.healthplatform.platformmanage.order.service;

import com.letu.healthplatform.platformmanage.order.model.Order;
import com.letu.healthplatform.platformmanage.order.model.OrderInfo;
import com.letu.healthplatform.platformmanage.order.model.OrderSendInfo;
import com.letu.healthplatform.platformmanage.user.model.AppUserInfo;

/**
 * @author dongzhibo
 * @date 2018年3月7日 上午9:58:44
 * @version 1.0.0
 * @description
 */
public interface  OrderInfoService {
	
	
   /***
    *发接收列表
    * @return
    */
	public Object  findAccpectOrderInfoPage(OrderInfo orderInfo,int page,int pageSize);
	
	
   /***
    *发接收详情
    * @return
    */
	public Object  findAccpectOrderInfoDetails(String orderinfoId);
	
	
	/***
	 * 修改订单
	 * @param  
	 * @return
	 */
	public Object updateOrder(OrderInfo orderInfo);
	
	
	public  Object   insertOrUpdate(OrderSendInfo orderSendInfo,AppUserInfo appUserinfo);

}
