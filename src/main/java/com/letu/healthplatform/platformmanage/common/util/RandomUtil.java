/**
 * 乐土精准医疗
 */
package com.letu.healthplatform.platformmanage.common.util;


/**
 * @author dzb
 * @date 2017年12月26日 下午2:39:48
 * @version 1.0
 * @description   6位生成工具
 */
public class RandomUtil {
	
	private static int len =6;
	
	public static String getInviteNumber() {
	    char[] letters = { '0','1','2','3','4','5','6','7','8','9'};
	    boolean[] flags = new boolean[letters.length];
	    char[] chs = new char[len];
	    for (int i = 0; i < chs.length; i++) {
	        int index;
	        do {
	            index = (int) (Math.random() * (letters.length));
	        } 
	        while (flags[index]);// 判断生成的字符是否重复
	        chs[i] = letters[index];
	        flags[index] = true;
	    }
	    return String.valueOf(chs);
	}

	
	
	public static String  getInvite(){
		String number="";
		for (int i = 0; i < len; i++) {
			int  num =  (int) Math.floor(Math.random()*10);
			if(number.indexOf(Integer.toString(num))!=-1){
				if(i!=0)i--;
			}else{
				number+=num;
			}
		}
		return number;
	}
	
	public static  String number(String number){
		//如果随机生成的存在
		if("123456".equals(number)){
			return 	number(RandomUtil.getInvite());
		}
		return number;
	}
	
	
	
	public static void main1(String[] args) {
		//String[] number ={"123456","123458","123456","123478"};
		String  a ="123456";
		   a = number(a);
		//for (int i = 0; i < number.length; i++) {
			System.out.println(a);
		//}
	}
	
}
