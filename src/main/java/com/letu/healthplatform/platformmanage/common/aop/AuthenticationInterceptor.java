package com.letu.healthplatform.platformmanage.common.aop;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.letu.healthplatform.platformmanage.common.config.RedisUntil;
import com.letu.healthplatform.platformmanage.common.util.Result;

@Configuration
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
	
	private final static  Logger log=LogManager.getLogger(AuthenticationInterceptor.class);
	
	  @Autowired
	    private RedisUntil  redisUntil;
	
	 // 在调用方法之前执行拦截
	  @Override
	  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		/* HttpSession session=request.getSession(true);
		 	String token=  request.getParameter("token");
		    if(token==null){
		 	     log.info("请求无ToKen，无权限 ");
		 	   return handleInvalidToken(request, response, handler);
		    }
	 		boolean b=redisUntil.containsValueKey(token);
		 	if(b){
		 		log.info("Token验证通过");;
		 		return true;
		 	}
	 	
		 	return handleInvalidToken(request, response, handler);*/
	         return true;
	  }
	  
	  /** 
	     * 当出现一个非法令牌时调用 
	     */  
	    protected boolean handleInvalidToken(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception  
	    {  
	      
	        writeMessageUtf8(response, new Result(HttpStatus.SC_UNAUTHORIZED,"请重新登录后，再操作"));  
	        return false;  
	    } 

		 private void writeMessageUtf8(HttpServletResponse response, Result json) throws IOException  
		    {  
		        try  
		        {  
		            response.setCharacterEncoding("UTF-8");  
		            response.setContentType("text/xml;charset=UTF-8");
		            response.getWriter().print(JSONObject.toJSON(json));  
		        }  
		        finally  
		        {  
		            response.getWriter().close();  
		        }  
		    } 

		
}
