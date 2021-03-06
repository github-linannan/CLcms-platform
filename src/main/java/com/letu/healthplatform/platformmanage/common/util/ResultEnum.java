package com.letu.healthplatform.platformmanage.common.util;

public enum ResultEnum {

	REP_CODE_SUCC(200,"成功"),
	REP_CODE_FAIL(300,"失败");
	
	private int code;
	private String msg;
	 ResultEnum(int code,String msg){
		this.code=code;
		this.msg=msg;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
