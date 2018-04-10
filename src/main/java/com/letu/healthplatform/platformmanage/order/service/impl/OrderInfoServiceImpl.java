/**
 * 乐土精准医疗有限公司
 */
package com.letu.healthplatform.platformmanage.order.service.impl;


import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.letu.healthplatform.platformmanage.common.code.Constants;
import com.letu.healthplatform.platformmanage.common.util.Rsp;
import com.letu.healthplatform.platformmanage.order.mapper.OrderInfoMapper;
import com.letu.healthplatform.platformmanage.order.mapper.OrderMapper;
import com.letu.healthplatform.platformmanage.order.mapper.OrderSendInfoMapper;
import com.letu.healthplatform.platformmanage.order.model.Order;
import com.letu.healthplatform.platformmanage.order.model.OrderInfo;
import com.letu.healthplatform.platformmanage.order.model.OrderSendInfo;
import com.letu.healthplatform.platformmanage.order.service.OrderInfoService;
import com.letu.healthplatform.platformmanage.user.mapper.AppUserInfoMapper;
import com.letu.healthplatform.platformmanage.user.model.AppUserInfo;

/**
 * @author dongzhibo
 * @date 2018年3月7日 上午9:58:55
 * @version 1.0.0
 * @description
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,
      isolation = Isolation.DEFAULT,
      timeout=36000,
      rollbackFor={RuntimeException.class, Exception.class})
public class OrderInfoServiceImpl implements OrderInfoService{

	private static Logger logger = LoggerFactory.getLogger(OrderInfoServiceImpl.class);
	
	
	@Autowired
	private OrderInfoMapper orderInfoMapper;
	
	@Autowired
	private OrderSendInfoMapper orderSendInfoMapper;
	
	@Autowired
	private OrderMapper orderMapper;
	
	
	@Autowired
	private  AppUserInfoMapper AppUserInfoMapper;
	
	
	/* (non-Javadoc)
	 * @see com.letu.healthplatform.platformmanage.order.service.OrderInfoService#findAccpectOrderInfoPage(com.letu.healthplatform.platformmanage.order.model.OrderInfo, int, int)
	 */
	@Override
	public Object findAccpectOrderInfoPage(OrderInfo orderInfo, int page, int pageSize) {
		List<Map<String, Object>> list=null;
		try{
			if(page>0&&pageSize>0)
		    PageHelper.startPage(page, pageSize);
		    list=orderInfoMapper.selectAcceptParam(orderInfo);
		    if(page>0&&pageSize>0){
		    	 return new PageInfo<>(list);
		    }
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(Constants.ERROR_FIND, e);
			return Rsp.fail(Constants.ERROR_FIND);
		}
		return Rsp.succ(Constants.SUCCESS_FIND, list);
	}
	
	

	/* (non-Javadoc)
	 * @see com.letu.healthplatform.platformmanage.order.service.OrderInfoService#findAccpectOrderInfoDetails(com.letu.healthplatform.platformmanage.order.model.OrderInfo)
	 */
	@Override
	public Object findAccpectOrderInfoDetails(String  orderinfoId) {
		List<Map<String, Object>> newlist=null;
		Map<String, Object> map=null;
		try{
			if(StringUtils.isBlank(orderinfoId)){
				return  Rsp.fail(Constants.ERROR_EMPTY);
			}
			OrderInfo orderInfo = new OrderInfo();
			orderInfo.setOrderinfoId(Integer.valueOf(orderinfoId));
			newlist = orderInfoMapper.selectAcceptDetails(orderInfo);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(Constants.ERROR_FIND, e);
			return Rsp.fail(Constants.ERROR_FIND);
		}
		return Rsp.succ(Constants.SUCCESS_FIND, newlist);
	}


	/* (non-Javadoc)
	 * @see com.letu.healthplatform.platformmanage.order.service.OrderInfoService#updateOrder(com.letu.healthplatform.platformmanage.order.model.OrderInfo)
	 */
	@Override
	public Object updateOrder(OrderInfo orderInfo) {
		int a =0;
		try{
			//已收到样本
			
			 a = orderInfoMapper.updateByPrimaryKeySelective(orderInfo);
			 return Rsp.succ(Constants.SUCCESS_UPDATE);
		}catch (Exception e) {
		    e.printStackTrace();
		    logger.error(Constants.ERROR_UPDATE,e);
		    return Rsp.fail(Constants.ERROR_UPDATE);
		}
	}



	/* (non-Javadoc)
	 * @see com.letu.healthplatform.platformmanage.order.service.OrderInfoService#insertOrUpdate(com.letu.healthplatform.platformmanage.order.model.OrderInfo, com.letu.healthplatform.platformmanage.user.model.AppUserInfo)
	 */
	@Override
	public Object insertOrUpdate(OrderSendInfo orderSendInfo, AppUserInfo appUserinfo) {
		try{
			//判断主键
			if(StringUtils.isEmpty(orderSendInfo.getOrderinfoId())
					||StringUtils.isEmpty(appUserinfo.getUserId().toString())){
				return   Rsp.fail(Constants.ERROR_EMPTY);
			}
			//修改收货信息信息
			orderSendInfoMapper.updateByPrimaryKeySelective(orderSendInfo);
			AppUserInfoMapper.updateByParamSelective(appUserinfo);
			//修改订单的最终状态
			OrderSendInfo  orderSendInfoParam = new OrderSendInfo();
			orderSendInfoParam.setOrderInfoStatus(orderSendInfo.getOrderInfoStatus());
			orderSendInfoParam.setOrderNumber(orderSendInfo.getOrderNumber());
			//查询订单的数量
			List<OrderSendInfo> list=orderSendInfoMapper.selectByOrderNumberAndStatus(orderSendInfoParam);

			//订单的number  查询状态
			if(list.size()==0){
				Order order =new Order();
				order.setOrderNumber(orderSendInfo.getOrderNumber());
				order.setOrderStatus(String.valueOf(orderSendInfo.getOrderInfoStatus()));
				orderMapper.updateOrderStatus(order);
			}
			return Rsp.succ(Constants.SUCCESS_UPDATE);
		}catch (Exception e) {
		    e.printStackTrace();
		    logger.error(Constants.ERROR_UPDATE,e);
		    return Rsp.fail(Constants.ERROR_UPDATE);
		}
	}
		

}
