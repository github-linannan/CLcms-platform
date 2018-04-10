/**
 * 乐土精准医疗有限公司
 */
package com.letu.healthplatform.platformmanage.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @author dzb
 * @date 2018年1月23日 下午4:37:23
 * @version 1.0.0
 * @see  集合工具互转包
 */
public class ListSetArrayUtil {
	
	
	/**
	 * 对比两个数组,找出不同的元素
	 *@param firstArray  副集合
	 *@param twoArray  主集合
	 *@return  T
	 ***/
    public static <T> List<T> compare(T[] firstArray, T[] twoArray) {    
        List<T> list1 = Arrays.asList(firstArray);    
        List<T> list2 = new ArrayList<T>();    
        for (T t : twoArray) {    
            if (!list1.contains(t)) {    
                list2.add(t);    
            }    
        }    
        return list2;
    }  
    
	/**
	 * 对比两个数组,找相同的元素
	 *@param t1
	 *@param t2
	 *@return  T
	 ***/
    public static Set<Integer> getIds(Integer[] a, Integer[] b){  
        Set<Integer> same = new HashSet<Integer>();  //用来存放两个数组中相同的元素  
        Set<Integer> temp = new HashSet<Integer>();  //用来存放数组a中的元素  
        for (int i = 0; i < a.length; i++) {  
            temp.add(a[i]);   //把数组a中的元素放到Set中，可以去除重复的元素  
        }  
        for (int j = 0; j < b.length; j++) {  
          //把数组b中的元素添加到temp中  
          //如果temp中已存在相同的元素，则temp.add（b[j]）返回false  
          if(!temp.add(b[j]))  
              same.add(b[j]);  
        }  
        return same;   
    } 
    
    
	/**
	 * @param value
	 *            数组
	 * @return list 集合
	 **/
	public static List<String> SringToList(String[] value) {
		if (value.length > 0) {
			return Arrays.asList(value);
		}
		return new ArrayList<String>();
	}
	
	
	public static List<Integer> arrayToList(String[] array){
		List<String> list = Arrays.asList(array);
		List<Integer> ilist =new LinkedList<Integer>();
		for (int i = 0; i < list.size(); i++) {
			ilist.add(Integer.parseInt(list.get(i)));
		}
		return ilist;
	}
	
	
	
	/** 
	 * 将字符串转化为int数组 
	*  
	 * @param str 带解析的字符串 
	* @return 转化而成的int数组 
	*/  
	public static int[] parse(String str) {  
		 int length = str.length();  
		 int[] result = new int[length];  
		 // 依次取得字符串中的每一个字符，并将其转化为数字，放进int数组中  
		 for (int i = 0; i < length; i++) {  
			 char c = str.charAt(i);  
			 result[i] = Character.getNumericValue(c);  
		 }  
		return result;  
	}   
	
	
	/** 
	 * 将String[]转化为Integer[] 
	*  
	 * @param array String数组 
	* @return 转化而成的Integer数组 
	*/ 
	public static Integer[] StringToInteger(String[] array) {
		Integer[] intTemp = new Integer[array.length];
		for (int i = 0; i <array.length; i++)
		 {
			 intTemp[i] = Integer.parseInt(array[i]);//int.TryParse函数返回Bool型。不会抛出异常
	     }
		return intTemp;
	}
	
	
	/** 
	 * 将list转化为Integer[] 
	*  
	 * @param list 集合
	* @return 转化而成的Integer数组 
	*/ 
	public static Integer[] ListToArry(List<Integer> list) {
		return   (Integer[])list.toArray(new Integer[list.size()]);
	}
	
	
	public static void main1(String[] args) {
		List<Integer> oldlist =new LinkedList<Integer>();
		oldlist.add(1);
		oldlist.add(2);
		oldlist.add(3);
		oldlist.add(4);
		oldlist.toArray();
		Integer[] oldArry = (Integer[])oldlist.toArray(new Integer[oldlist.size()]);
		System.out.println(oldArry.length);
	}

}
