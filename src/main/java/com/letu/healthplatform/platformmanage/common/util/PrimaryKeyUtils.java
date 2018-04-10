package com.letu.healthplatform.platformmanage.common.util;

import com.letu.healthplatform.platformmanage.common.config.RedisUntil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;


@Component
public class PrimaryKeyUtils {

	private static Logger logger = LoggerFactory.getLogger(PrimaryKeyUtils.class);
    @Autowired
	private RedisUntil redisUtil;
	
	private  String createPrimaryKey(Date date){
		Calendar cd=Calendar.getInstance();
		cd.setTime(date);
		int year=cd.get(Calendar.YEAR);
		int day=cd.get(Calendar.DAY_OF_YEAR);
		int hourse=cd.get(Calendar.HOUR_OF_DAY);
		int minute=cd.get(Calendar.MINUTE);
		String dayFmt=String.format("%1$03d", day);
		String hourseFmt=String.format("%1$02d", hourse);
		String minuteFmt=String.format("%1$02d", minute);
		return (year-2000)+dayFmt+hourseFmt;
	}
	/***
	 * 获取主键ID,每小时99999多的订单量，目前可以范满足我们的需求
	 * @param prefix
	 * @return
	 */
	public  Long getPrimaryKeyId(){
		String prefix=createPrimaryKey(new Date());
		String key="primarykey_"+prefix;
		String orderId=null;
		
		try {
			long index=redisUtil.incr(key,1*60*60);
			orderId=prefix+String.format("%1$05d", index);
			return Long.valueOf(orderId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("从reids 中获取自增值 失败");
		}finally {
		}
		
		System.out.println("eroor");
		return null;
	}
}
