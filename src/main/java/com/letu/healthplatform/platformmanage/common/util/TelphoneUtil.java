/**
 * 乐土精准医疗
 */
package com.letu.healthplatform.platformmanage.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author dzb
 * @date 2017年12月8日 下午3:50:58
 * @version 1.0
 * @description  手机号和电话号码验证
 */
public class TelphoneUtil {
	
	   
	 /** 
	  * 手机号验证 
	  * @author dzb
	  * @param  str 
	  * @return 验证通过返回true 
	  */  
	 public static boolean isMobile(final String str) {  
	     Pattern p = null;  
	     Matcher m = null;  
	     boolean b = false;  
	     p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号  
	     m = p.matcher(str);  
	     b = m.matches();  
	     return b;  
	 }  
	 
	 
	 
	 /** 
	  * 电话号码验证 
	  * @author ：dzb
	  * @param  str 
	  * @return 验证通过返回true 
	  */  
	 public static boolean isPhone(final String str) {  
	     Pattern p1 = null, p2 = null;  
	     Matcher m = null;  
	     boolean b = false;  
	     p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");  // 验证带区号的  
	     p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");         // 验证没有区号的  
	     if (str.length() > 9) {  
	        m = p1.matcher(str);  
	        b = m.matches();  
	     } else {  
	         m = p2.matcher(str);  
	        b = m.matches();  
	     }  
	     return b;  
	 }  
	   
	 
	 
	/**
	 * 验证手机号码是否正确
	 * @param telephone
	 * @return
	 */
	public static boolean checkTelephone(String telephone) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9])|(17[0-9]))\\d{8}$"); 
		Matcher m = p.matcher(telephone); 
		return m.matches();
	}
	 
	 
	 public static void main(String[] args) {  
	   String phone = "17688706037";  
	   String phone2 = "021-88889999";  
	   String phone3 = "88889999";  
	   String phone4 = "1111111111";  
	   //测试1  
	   if(isPhone(phone) || isMobile(phone)){  
	     System.out.println("1这是符合的");  
	   }  
	   //测试2  
	   if(isPhone(phone2) || isMobile(phone2)){  
	     System.out.println("2这是符合的");  
	   }  
	   //测试3  
	   if(isPhone(phone3) || isMobile(phone3)){  
	     System.out.println("3这是符合的");  
	   }  
	   //测试4  
	   if(isPhone(phone4) || isMobile(phone4)){  
	     System.out.println("4这是符合的");  
	   }else{  
	     System.out.println("不符合");  
	   }  
	 }  
}

