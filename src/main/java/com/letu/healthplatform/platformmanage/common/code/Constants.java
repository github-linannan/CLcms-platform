package com.letu.healthplatform.platformmanage.common.code;


/**
 * @author dzb
 * @date 2017/09/23
 * @see 基础类
 * 
 * **/
public class Constants
{
	
	public final static String SUCCESS_FIND="查询成功";
	
	public final static String SUCCESS_INSERT="新增成功";
	
	public final static String SUCCESS_UPDATE="修改成功";
	
	public final static String SUCCESS_DELETE="删除成功";
	
	public final static String  SUCCESS_UPLOAD="上传成功";
	
	public final static String  FAIL_UPLOAD="上传失败";
	
	public final static String ERROR_FIND="查询失败";
	
	public final static String ERROR_INSERT="新增失败";
	
	public final static String ERROR_UPDATE="修改失败";
	
	public final static String ERROR_DELETE="删除失败";
	
	public final static String USERNAME_NOT_NULL="用户名不为空";
	
	public final static String PASSWORD_NOT_NULL="密码不能为空";
	
	public final static String FORBIDDEN="此账号已注销，请联系管理员。";
	
	public final static String USER_FAILURE = "账户或密码错误";
	
	public final static String ERROR = "错误";
	
	public final static String ERROR_LINK = "通讯出现错误";
	
	public final static String ERROR_ILLEGAL="非法请求链接";
	
	public final static String ERROR_PARAMS="参数错误";
	
	public final static String ERROR_EMPTY="参数错误";
	
	public final static String DEFAULT_KEY_ISNULL="参数ID值为空";
	
	public final static String PASSEWORD_ISNULL="密码为空";
	
	public final static String ERROR_PASSEWORD="旧密码错误";
	
	public final static String ERROR_MSG="人员信息有错误";
	
	
	public  final  static  String  TELPHONE_ERROR="电话号码错误";
	
	//报告状态
	public interface reportCode{
		
		public static final String  PERSION="1";
		
		public static final String  ORGANIZATION="2";
	}
	
    //短信类型
	public interface mesType{
		
		public static final int  RECEIVE=0;//接收
		
		public static final int  DETECTION=1;//检测
		
		public static final int  SEND_FISH=3;//发送报告
		
	}
	
	
	//操作类型
	public interface operationCode{
		
		public static final String   INSERT="insert";
		
		public static final String   UPDATE="update";
		
		public static final String   FIND="find";
	}
	
	
	
	
}
