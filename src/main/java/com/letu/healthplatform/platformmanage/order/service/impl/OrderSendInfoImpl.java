/**
 * 乐土精准医疗有限公司
 */
package com.letu.healthplatform.platformmanage.order.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.letu.healthplatform.platformmanage.order.service.OrderSendInfoService;

/**
 * @author dongzhibo
 * @date 2018年3月5日 下午2:19:19
 * @version 1.0.0
 * @description
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,
      isolation = Isolation.DEFAULT,
      timeout=36000,
      rollbackFor={RuntimeException.class, Exception.class})
public class OrderSendInfoImpl implements OrderSendInfoService {
	
	private static Logger log = LoggerFactory.getLogger(OrderSendInfoImpl.class);
	
	
	
	
}
