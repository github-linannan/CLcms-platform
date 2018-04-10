package com.letu.healthplatform.platformmanage.common.util;



 
/**
 * 操作信息响应类
 */
public  class Rsp {
	
	
	public static String REP_CODE = "code";
	public static String REP_MSG = "message";
	public static String REP_DATA = "data";

/*	public static String REP_CODE_SUCC = "200";
	public static String REP_CODE_FAIL = "300";*/
	public static boolean REP_CODE_STATUS=true;
	
	public static String REP_MSG_FAIL = "失败";
	public static String REP_MSG_SUCC = "成功";
	
	public static Result succ(String msg ){
		return succ(msg,"");
	}
	
	public static Result succ(String msg , Object obj){
		Result  result = new Result();
		result.setCode(ResultEnum.REP_CODE_SUCC.getCode());
		msg = msg==null|| "".equals(msg)?ResultEnum.REP_CODE_SUCC.getMsg():msg;
		result.setMessage("请求成功!");
		result.setDetailMessage(msg);
		result.setData(obj);
		return result;
	}
	
	
	public static Result fail(String msg ){
		return fail(msg,"");
	}
	
	
	public static Result fail(String msg ,Object obj){
		Result  result = new Result();
		result.setCode(ResultEnum.REP_CODE_FAIL.getCode());
		msg = msg==null|| "".equals(msg)?ResultEnum.REP_CODE_FAIL.getMsg():msg;
		result.setMessage("请求成功!");
		result.setDetailMessage(msg);
		result.setData(obj);
		return result;
	}
	public static Result fail(String msg ,int code){
		Result  result = new Result();
		result.setCode(code);
		msg = msg==null|| "".equals(msg)?ResultEnum.REP_CODE_FAIL.getMsg():msg;
		result.setMessage("请求成功!");
		result.setDetailMessage(msg);
		return result;
	}
	

}
