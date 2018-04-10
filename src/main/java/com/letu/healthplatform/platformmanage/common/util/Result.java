package com.letu.healthplatform.platformmanage.common.util;


import java.io.Serializable;


/**
 * 操作信息响应类
 */
public class Result implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer code;// 结果 200:成功；300：失败,400 失败     可以自己定义任何数字 标识各种状态

	/**
	 * 信息
	 */
	private String message;
	
	/**
	 * 详细信息
	 */
	private String detailMessage;
	
	/**
	 * 响应其他数据
	 */
	private Object data;

    public Result() {
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }



	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetailMessage() {
		return detailMessage;
	}

	public void setDetailMessage(String detailMessage) {
		this.detailMessage = detailMessage;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	
	public String toString() {
		return "code:"+this.code+",message:"+this.message+",datailMessage:"+this.detailMessage+",data:"+this.data.toString();
	}
	
	
	
}
