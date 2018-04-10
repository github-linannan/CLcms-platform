package com.letu.healthplatform.platformmanage.order.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
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
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.letu.healthplatform.platformmanage.common.code.Constants;
import com.letu.healthplatform.platformmanage.common.util.MD5;
import com.letu.healthplatform.platformmanage.common.util.MapToBean;
import com.letu.healthplatform.platformmanage.common.util.PrimaryKeyUtils;
import com.letu.healthplatform.platformmanage.common.util.Rsp;
import com.letu.healthplatform.platformmanage.goods.model.Goods;
import com.letu.healthplatform.platformmanage.goods.service.GoodsService;
import com.letu.healthplatform.platformmanage.order.mapper.OrderInfoMapper;
import com.letu.healthplatform.platformmanage.order.mapper.OrderMapper;
import com.letu.healthplatform.platformmanage.order.model.Order;
import com.letu.healthplatform.platformmanage.order.model.OrderInfo;
import com.letu.healthplatform.platformmanage.order.service.OrderService;
import com.letu.healthplatform.platformmanage.order.vo.GoodsVo;
import com.letu.healthplatform.platformmanage.order.vo.OrderDetailsVo;
import com.letu.healthplatform.platformmanage.order.vo.OrderInfoVo;


@Service
@Transactional(propagation = Propagation.REQUIRED,
      isolation = Isolation.DEFAULT,
      timeout=36000,
      rollbackFor={RuntimeException.class, Exception.class})
public class OrderServiceImpl implements OrderService{

	private static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired
	private  GoodsService   goodsServiceImpl;
	
	@Autowired
	private  PrimaryKeyUtils  primaryKeyUtils;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private OrderInfoMapper orderInfoMapper;
	

	/***
	 * 创建订单
	 * 商品id
	 * 购买数量
	 * 检测人
	 * 推荐人邀请码
	 * 收货地址
	 * token
	 * 1、判断用户是否有效
	 */
	@Transactional
	public  Object  addOrder(String body){
		JSONObject data=JSONObject.parseObject(body);
		//Login  login=authoreService.authore(body);
			String goodsIds=data.getString("goodsIds");
			String orderDegree = data.getString("orderDegree");
			//如果是空值 默认给值 0  购物车下单支付的
			if(StringUtils.isBlank(orderDegree)){
				  orderDegree="0";
			}
			//List<Goods> goodsList=goodsServiceImpl.findGoodsByIds(goodsIds);
			String orderNumber=primaryKeyUtils.getPrimaryKeyId().toString();
			String orderPayType=data.getString("orderPayType");
			  int orderShipAddress=  data.getInteger("orderShipAddress");
			  String goodsNum=data.getString("goodsNum");
				Order  order=new Order();
			    order.setOrderNumber(orderNumber);//订单编号
			    //order.setLoginId(login.getLoginId());
			    order.setOrderPayType(orderPayType);
			    order.setOrderStatus("0");
			    order.setOrderCreateTime(new Date());
			    order.setOrderShipAddress(orderShipAddress);
			    order.setOrderDegree(Integer.valueOf(orderDegree));
			    List<OrderInfo>  orderInfos=new ArrayList<OrderInfo>();
			    double  totalMoney=0;
			    int nums=0;
			 /* for(Goods goods:goodsList){
				  OrderInfo  orderInfo=new OrderInfo();
				  orderInfo.setGoodsId(goods.getGoodsId());
				  orderInfo.setOrderGoodsPrice(goods.getGoodsPrice());
				  orderInfo.setOrderGoodsDiscount(goods.getGoodsDiscount());
				  orderInfo.setOrderGoodsDiscountPrice(goods.getGoodsDiscountPrice());
				  nums=this.getNum(goods.getGoodsId(), goodsIds, goodsNum);
				  totalMoney=totalMoney+goods.getGoodsDiscountPrice()*nums;
				  orderInfo.setOrderGoodsNum(nums);
				  orderInfo.setOrderNumber(orderNumber);
				  orderInfo.setOrderCreateTime(new Date());
				  orderInfos.add(orderInfo);
			  }        */
			  order.setOrderPayTotalPrice(totalMoney);
			  //先保存订单，再保存订单详情
			  int i=  orderMapper.insert(order);
			  int j=  orderInfoMapper.insertOrderInfoBatch(orderInfos);
			 if(i>0&&j>0){
				return Rsp.succ("订单创建成功.....");
			 }
	    	return Rsp.fail("订单创建 失败........");
	}
	
	/**
	 * l暂时不用
	 * 
	 * 	 * 2、判断签名是否有效   
	 * 根据Id从大到小进行排序
	 * {"token":"xxx","goodsIds":"1,2,3","goodsNum":"1,1,1","orderPayType":"订单支付方式","orderShipAddress":"xxxx","sign":"xxx"}
	 * order_pay_type
	 * MD5(token=xx&MD5(goodsId=xxx&goodsNum=xx&goodsPrice=xx+......+totalMoney=xxx)
	 * MD5(token+MD5(商品id+商品数量+商品单价+....+商品总价)+支付方式+收货地址)
	 * @param body
	 * @param goodLists
	 * @return
	 */
	DecimalFormat    df   = new DecimalFormat("######0.00");   
	private boolean checkSign(String body,List<Goods> goodLists,String sign){
		JSONObject data=JSONObject.parseObject(body);
	    String token=	data.getString("token");
		String orderPayType=data.getString("orderPayType");
		  int orderShipAddress=  data.getInteger("orderShipAddress");
		  String goodsIds=data.getString("goodsIds");
		  String goodsNum=data.getString("goodsNum");
		    double  totalMoney=0;
		    int nums=0;
		    StringBuffer signbf=new StringBuffer();
		  for(Goods goods:goodLists){
			  nums=this.getNum(goods.getGoodsId(), goodsIds, goodsNum);
			  totalMoney=totalMoney+goods.getGoodsDiscountPrice()*nums;
			  signbf.append("goodsId=").append(goods.getGoodsId()).append("&")
			  		.append("goodsNum=").append(nums).append("&")
			  		.append("goodsPrice=").append(goods.getGoodsPrice()).append("&");
		  }   
		   signbf.append("totalMoney=").append(df.format(totalMoney));
		  String newSign=MD5.encryptSign("token="+token+"&"+MD5.encryptSign(signbf.toString())+"&orderPayType="+orderPayType+"&orderShipAddress="+orderShipAddress);
		  log.info("签名字符串："+newSign);
		  if(newSign.equals(sign)){
			  return true;
		  }
		return false;
	}
	
	
	private  int  getNum(int id,String ids,String nums){
		String[]  nid=ids.split(",");
		List listIds= Arrays.asList(nid);
		String[]  num=nums.split(",");
		List listnums= Arrays.asList(num);
		int index=listIds.indexOf(String.valueOf(id));
		return Integer.parseInt(listnums.get(index)+"");
	}



	@Override
	public Object findOrderPage(Order order, int page, int pageSize) {
		List<Map<String, Object>> list=null;
			try{
				if(page>0&&pageSize>0)
			    PageHelper.startPage(page, pageSize);
			    list=orderMapper.selectByParam(order);
			    if(page>0&&pageSize>0){
			    	 return new PageInfo<>(list);
			    }
			}catch (Exception e) {
				e.printStackTrace();
				log.error(Constants.ERROR_FIND, e);
				return Rsp.fail(Constants.ERROR_FIND);
			}
			return Rsp.succ(Constants.SUCCESS_FIND, list);
	}



	@Override
	public Object findOrderDetails(String orderId) {
		List<Map<String, Object>> listMap = null;
		List<GoodsVo> gList =null;
		OrderDetailsVo detailsVo=null;
		if(StringUtils.isBlank(orderId)){
			return Rsp.fail(Constants.DEFAULT_KEY_ISNULL);
		}
		Order o = new Order();
		o.setOrderId(Integer.valueOf(orderId));
		try{
			listMap =orderMapper.selectOrderDetails(o);
			if(!listMap.isEmpty()){
				gList =new  LinkedList<GoodsVo>();
				OrderInfoVo  orderInfoVo=	(OrderInfoVo) MapToBean.mapToBean(listMap.get(0), OrderInfoVo.class);
				  detailsVo =new OrderDetailsVo();
				  detailsVo.setJcName("");
				  detailsVo.setLoginTelephone(orderInfoVo.getLoginTelephone());
				  detailsVo.setLoginNickname(orderInfoVo.getLoginNickname());
				  detailsVo.setAddressInfo(orderInfoVo.getAddressInfo());
				  detailsVo.setLoginInvite(orderInfoVo.getLoginInvite());
				  detailsVo.setOrderCreateTime(orderInfoVo.getOrderCreateTime());
				  detailsVo.setOrderNumber(orderInfoVo.getOrderNumber());
				  detailsVo.setOrderParticulars(orderInfoVo.getOrderParticulars());
				  detailsVo.setOrderPayTotalPrice(orderInfoVo.getOrderPayTotalPrice());
				  detailsVo.setOrderStatus(orderInfoVo.getOrderStatus());
				  for (int i = 0; i < listMap.size(); i++) {
					  OrderInfoVo  orderInfoVo1 =	(OrderInfoVo) MapToBean.mapToBean(listMap.get(0), OrderInfoVo.class);
					  GoodsVo goodsVo =new GoodsVo();
					  goodsVo.setGoodsId(orderInfoVo1.getGoodsId());
					  goodsVo.setGoodsName(orderInfoVo1.getGoodsName());
					  goodsVo.setGoodsDiscount(orderInfoVo1.getOrderGoodsDiscount());
					  goodsVo.setGoodsPrice(orderInfoVo1.getOrderGoodsPrice());
					  goodsVo.setGoodsDiscountPrice(orderInfoVo1.getOrderGoodsDiscountPrice());
					  goodsVo.setOrderGoodsNum(orderInfoVo1.getOrderGoodsNum());
					  gList.add(goodsVo);
				  }
				  detailsVo.setList(gList);
				 
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error(Constants.ERROR_FIND, e);
			 return Rsp.fail(Constants.ERROR_FIND);
		}
		 return Rsp.succ(Constants.SUCCESS_FIND,detailsVo);
	}



	@Override
	public Object updateOrder(Order order) {
		int a =0;
		try{
			 order.setOrderStatus("2");//已发货状态
			 order.setOrderShipTime(new Date());//发货时间
			 a = orderMapper.updateByPrimaryKeySelective(order);
			 return Rsp.succ(Constants.SUCCESS_UPDATE);
		}catch (Exception e) {
		    e.printStackTrace();
		    log.error(Constants.ERROR_UPDATE,e);
		    return Rsp.fail(Constants.ERROR_UPDATE);
		}
	}



	@Override
	public Object updateOrderStatus(String orderId, String orderStatus) {
		int a =0;
		Map<String, Object> params =new HashMap<String, Object>();
		try {
			params.put("orderStatus", orderStatus);
			List<String> list = Arrays.asList(orderId.split(","));
			params.put("list", list);
			 a = orderMapper.udapteOrderStatus(params);
			 if(a>0) return Rsp.succ(Constants.SUCCESS_UPDATE);
		}catch (Exception e) {
		    e.printStackTrace();
		    log.error(Constants.ERROR_UPDATE,e);
		    return Rsp.fail(Constants.ERROR_UPDATE);
		}
		return Rsp.fail(Constants.ERROR_UPDATE);
	}



	@Override
	public Object findDeliverPage(Order order,int page,int pageSize) {
		List<Map<String, Object>> list=null;
		try{
			if(page>0&&pageSize>0)
		    PageHelper.startPage(page, pageSize);
		    list=orderMapper.selectDeliverParam(order);
		    if(page>0&&pageSize>0){
		    	 return new PageInfo<>(list);
		    }
		}catch (Exception e) {
			e.printStackTrace();
			log.error(Constants.ERROR_FIND, e);
			return Rsp.fail(Constants.ERROR_FIND);
		}
		return Rsp.succ(Constants.SUCCESS_FIND, list);
	}


	/* (non-Javadoc)
	 * @see com.letu.healthplatform.platformmanage.order.service.OrderService#findDeliverDetails(java.lang.String)
	 */
	@Override
	public Object findDeliverDetails(String orderNumber) {
		List<Map<String, Object>> newlist=null;
		Map<String, Object> map=null;
		try{
			if(StringUtils.isEmpty(orderNumber)){
				  return Rsp.fail(Constants.ERROR_EMPTY);
			}
			List<Map<String, Object>>  list=orderMapper.selectDeliverDetails(new Order(orderNumber));
		    if(!list.isEmpty()){
		    	newlist =new LinkedList<Map<String, Object>>();
		    	map = new  HashMap<String, Object>();
		    	List<Map<String, Object>> oList =	
		    			orderInfoMapper.selectOrderInfoDetails(new OrderInfo(orderNumber));
		    	for (int i = 0; i < list.size(); i++) {
		    		Map<String, Object> map2 = list.get(i);
		    		if(map2.containsKey("list")){
		    			map2.put("list", oList);
		    		}
		    		newlist.add(map2);
				}
		    }
		}catch (Exception e) {
			e.printStackTrace();
			log.error(Constants.ERROR_FIND, e);
			return Rsp.fail(Constants.ERROR_FIND);
		}
		return Rsp.succ(Constants.SUCCESS_FIND, newlist);
	}


	
}
