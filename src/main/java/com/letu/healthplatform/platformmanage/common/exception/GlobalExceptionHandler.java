package com.letu.healthplatform.platformmanage.common.exception;

import com.letu.healthplatform.platformmanage.common.util.Rsp;
import com.letu.healthplatform.platformmanage.home.exception.HomeException;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private final static Logger log=org.slf4j.LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(value=Exception.class)
	public Object Hadler(Exception e){
		if(e instanceof MyException){
			MyException myException=(MyException)e;
			return Rsp.fail(myException.getMsg(), myException.getCode());
		}if(e instanceof ValidateAuthoreException){
			ValidateAuthoreException authoreException=(ValidateAuthoreException)e;
			return Rsp.fail(authoreException.getMsg(), authoreException.getCode());
		}if(e instanceof HomeException){
			HomeException homeException=(HomeException)e;
			return Rsp.fail(homeException.getKey());
		}else{
			log.error("【系统异常】{}",e);
			return Rsp.fail("未知异常",-1);
		}
	}
}
