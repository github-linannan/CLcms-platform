package com.letu.healthplatform.platformmanage.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Configuration
public class RedisUntil {

    private final static Logger logger = LoggerFactory.getLogger(RedisUntil.class);

	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
    /**
     * 前缀
     */
    public static final String KEY_PREFIX_VALUE = "platform-manager:value:";
    public static final String KEY_PREFIX_SET = "platform-manager:set:";
    public static final String KEY_PREFIX_LIST = "platform-manager:list:";
    
    public static final String CODE_PREFIX = "code:";
    public static final String USER_PREFIX = "user:";
    
	/**
	 *  检测是否存在ke值
	 *  param key
	 *  return false true
	 * **/
	public boolean checkYzm(String key){
		ValueOperations<String, String> ops = this.stringRedisTemplate.opsForValue();
		if(this.stringRedisTemplate.hasKey(key)){
			 return true;
		}
		return false;
		
	}
	
	
	
	/**
	 *保存值
	 * param: key
	 * param： value
	 * param：loseTime  失效时间
	 * **/
	public void set(String key, String value,long lose_time) {
		ValueOperations<String, String> ops = this.stringRedisTemplate.opsForValue();
		if (!this.stringRedisTemplate.hasKey(key)) {
			 ops.set(key, value);
			 if(lose_time>0)this.stringRedisTemplate.expire(key, lose_time, TimeUnit.SECONDS);
			logger.info("set key success");
		} else {
			// 存在则打印之前的value值
			logger.info("this key = " + ops.get(key));
		}
	}
	
	
	/**
	 * 获取值
	 * param: key
	 * return： value
	 * **/
	public  String  get(String key){
	   return	stringRedisTemplate.opsForValue().get(key);
	}
	
	
	/**
	 * 删除
	 * param: key
	 *
	 * **/
	public  void del(String key) {
		this.stringRedisTemplate.delete(key);
	}
	
	 /**
     * 缓存value操作
     * @param k
     * @param v
     * @param time
     * @return
     */
    public boolean cacheValue(String k, String v, long time) {
        String key = KEY_PREFIX_VALUE + k;
        try {
            ValueOperations<String, String> valueOps =  stringRedisTemplate.opsForValue();
            valueOps.set(key, v);
            if (time > 0) stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
            else {
            	//过期时间设置为300天 300*24*60*60
            	stringRedisTemplate.expire(key, 25920000, TimeUnit.SECONDS);}
            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }
    
    /**
    *
    * @param key
    * @param liveTime
    * @return
    */
   public Long incr(String key, long liveTime) {
       RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, this.stringRedisTemplate.getConnectionFactory());
       Long increment = entityIdCounter.getAndIncrement();

       if ((null == increment || increment.longValue() == 0) && liveTime > 0) {//初始设置过期时间
           entityIdCounter.expire(liveTime, TimeUnit.SECONDS);
       }

       return increment;
   }
   /**
   *
   * @param key
   * @param liveTime
   * @return
   */
  public Long incr(String key) {
      return incr(key,-1);
  }
    /**
     * 缓存value操作
     * @param k
     * @param v
     * @return
     */
    public boolean cacheValue(String k, String v) {
        return cacheValue(k, v, -1);
    }

    /**
     * 判断缓存是否存在
     * @param k
     * @return
     */
    public boolean containsValueKey(String k) {
        return containsKey(KEY_PREFIX_VALUE + k);
    }

    /**
     * 判断缓存是否存在
     * @param k
     * @return
     */
    public boolean containsSetKey(String k) {
        return containsKey(KEY_PREFIX_SET + k);
    }

    /**
     * 判断缓存是否存在
     * @param k
     * @return
     */
    public boolean containsListKey(String k) {
        return containsKey(KEY_PREFIX_LIST + k);
    }

    public boolean containsKey(String key) {
        try {
            return stringRedisTemplate.hasKey(key);
        } catch (Throwable t) {
            logger.error("判断缓存存在失败key[" + key + ", error[" + t + "]");
        }
        return false;
    }

    /**
     * 获取缓存
     * @param k
     * @return
     */
    public String getValue(String k) {
        try {
            ValueOperations<String, String> valueOps =  stringRedisTemplate.opsForValue();
            return valueOps.get(KEY_PREFIX_VALUE + k);
        } catch (Throwable t) {
            logger.error("获取缓存失败key[" + KEY_PREFIX_VALUE + k + ", error[" + t + "]");
        }
        return null;
    }

    /**
     * 移除缓存
     * @param k
     * @return
     */
    public boolean removeValue(String k) {
        return remove(KEY_PREFIX_VALUE + k);
    }

    public boolean removeSet(String k) {
        return remove(KEY_PREFIX_SET + k);
    }

    public boolean removeList(String k) {
        return remove(KEY_PREFIX_LIST + k);
    }

    /**
     * 移除缓存
     * @param key
     * @return
     */
    public boolean remove(String key) {
        try {
        	stringRedisTemplate.delete(key);
            return true;
        } catch (Throwable t) {
            logger.error("获取缓存失败key[" + key + ", error[" + t + "]");
        }
        return false;
    }
    /**
     * 缓存set操作
     * @param k
     * @param v
     * @param time
     * @return
     */
    public boolean cacheSet(String k, String v, long time) {
        String key = KEY_PREFIX_SET + k;
        try {
            SetOperations<String, String> valueOps =  stringRedisTemplate.opsForSet();
            valueOps.add(key, v);
            if (time > 0) stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 缓存set
     * @param k
     * @param v
     * @return
     */
    public boolean cacheSet(String k, String v) {
        return cacheSet(k, v, -1);
    }

    /**
     * 缓存set
     * @param k
     * @param v
     * @param time
     * @return
     */
    public boolean cacheSet(String k, Set<String> v, long time) {
        String key = KEY_PREFIX_SET + k;
        try {
            SetOperations<String, String> setOps =  stringRedisTemplate.opsForSet();
            setOps.add(key, v.toArray(new String[v.size()]));
            if (time > 0) stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 缓存set
     * @param k
     * @param v
     * @return
     */
    public boolean cacheSet(String k, Set<String> v) {
    	if(v==null||v.size()==0){
    		return false;
    	}
        return cacheSet(k, v, -1);
    }

    /**
     * 获取缓存set数据
     * @param k
     * @return
     */
    public Set<String> getSet(String k) {
        try {
            SetOperations<String, String> setOps = stringRedisTemplate.opsForSet();
            return setOps.members(KEY_PREFIX_SET + k);
        } catch (Throwable t) {
            logger.error("获取set缓存失败key[" + KEY_PREFIX_SET + k + ", error[" + t + "]");
        }
        return null;
    }

    /**
     * list缓存
     * @param k
     * @param v
     * @param time
     * @return
     */
    public boolean cacheList(String k, String v, long time) {
        String key = KEY_PREFIX_LIST + k;
        try {
            ListOperations<String, String> listOps =  stringRedisTemplate.opsForList();
            listOps.rightPush(key, v);
            if (time > 0) stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 缓存list
     * @param k
     * @param v
     * @return
     */
    public boolean cacheList(String k, String v) {
        return cacheList(k, v, -1);
    }

    /**
     * 缓存list
     * @param k
     * @param v
     * @param time
     * @return
     */
    public boolean cacheList(String k, List<String> v, long time) {
        String key = KEY_PREFIX_LIST + k;
        try {
            ListOperations<String, String> listOps =  stringRedisTemplate.opsForList();
            long l = listOps.rightPushAll(key, v);
            if (time > 0) stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 缓存list
     * @param k
     * @param v
     * @return
     */
    public boolean cacheList(String k, List<String> v) {
       return cacheList(k, v, -1);
    }

    /**
     * 获取list缓存
     * @param k
     * @param start
     * @param end
     * @return
     */
    public List<String> getList(String k, long start, long end) {
        try {
            ListOperations<String, String> listOps =  stringRedisTemplate.opsForList();
            return listOps.range(KEY_PREFIX_LIST + k, start, end);
        } catch (Throwable t) {
            logger.error("获取list缓存失败key[" + KEY_PREFIX_LIST + k + ", error[" + t + "]");
        }
        return null;
    }

    /**
     * 获取总条数, 可用于分页
     * @param k
     * @return
     */
    public long getListSize(String k) {
        try {
            ListOperations<String, String> listOps =  stringRedisTemplate.opsForList();
            return listOps.size(KEY_PREFIX_LIST + k);
        } catch (Throwable t) {
            logger.error("获取list长度失败key[" + KEY_PREFIX_LIST + k + "], error[" + t + "]");
        }
        return 0;
    }

    /**
     * 获取总条数, 可用于分页
     * @param listOps
     * @param k
     * @return
     */
    public long getListSize(ListOperations<String, String> listOps, String k) {
        try {
            return listOps.size(KEY_PREFIX_LIST + k);
        } catch (Throwable t) {
            logger.error("获取list长度失败key[" + KEY_PREFIX_LIST + k + "], error[" + t + "]");
        }
        return 0;
    }

    /**
     * 移除list缓存
     * @param k
     * @return
     */
    public boolean removeOneOfList(String k) {
        String key = KEY_PREFIX_LIST + k;
        try {
            ListOperations<String, String> listOps =  stringRedisTemplate.opsForList();
            listOps.rightPop(KEY_PREFIX_LIST + k);
            return true;
        } catch (Throwable t) {
            logger.error("移除list缓存失败key[" + KEY_PREFIX_LIST + k + ", error[" + t + "]");
        }
        return false;
    }



	public StringRedisTemplate getStringRedisTemplate() {
		
		return stringRedisTemplate;
	}
    
    

}
