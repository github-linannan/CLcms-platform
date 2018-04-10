/**
 * 乐土精准医疗
 */
package com.letu.healthplatform.platformmanage.common.config;

import java.io.Serializable;
import java.util.Set;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;

/**
 * @author dzb
 * @date 2017年12月5日 下午2:30:41
 * @version 1.0
 * @description 
 */
@Configuration
public class RedisSetUtil {
	
    private final static Logger log = LogManager.getLogger(RedisSetUtil.class);
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;
	
	//邀请码的前缀
	public static final String INVITE_PREFIX= "invite_";
	
	  
    /**
     * 读取缓存
     * @param key
     * @return
     */
    public Object get(final String key) {
    	Set<Object>  obj =null;
         obj = redisTemplate.opsForSet().members(key);
        return obj;
    }
    
    
	
    /**
     * 是否存在value
     * @param key
     * @return true false
     */
    public boolean isMembers(final String value){
    	return redisTemplate.opsForSet().isMember(INVITE_PREFIX, value);
    }
    
    
    
	/**
     * 用户唯一的邀请码
     * @param key  invite_
     * @param value map 
     * @param loseTime 失效时间
     * @return
     */
    @SuppressWarnings("unchecked")
	public boolean set(final String key, Object value) {
    	long l  =0L;
        try {
        	SetOperations<Serializable, Object>  operations  =  redisTemplate.opsForSet();
            l = operations.add(key, value);
            if(l>0)
            	log.info(" set key is sucess");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
		return false;
    }
    
	   
}
