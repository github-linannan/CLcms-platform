package com.letu.healthplatform.platformmanage.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 字符串工具类
 * 
 * @author Meng
 * 
 */
public class StringUtil {
	
	

	private static final Pattern IP_PATTERN = Pattern.compile("\\d+\\.\\d+\\.\\d+\\.\\d+");

	private static Pattern chinesePattern;

	static {
		chinesePattern = Pattern.compile("[\u4e00-\u9fa5]");
	}

	public static String unicode10ToWord(String unicode) {
		try {
			if (unicode.contains("&#")) {
				String[] ss = unicode.split("&#");
				String newString = "";
				for (String s : ss) {
					if (containsNumber(s) && s.contains(";")) {
						int index = s.indexOf(";");
						String number = s.substring(0, index);
						String s1 = "";
						int a = Integer.parseInt(number, 10);
						s1 = s1 + (char) a;

						newString = newString + s1 + s.substring(index + 1);
					} else {
						newString = newString + s;
					}
				}

				return newString;
			} else {
				return unicode;
			}
		} catch (Exception e) {
			return unicode;
		}
	}

	public static boolean containsNumber(String source) {
		for (char i = '0'; i < '9' + 1; i++) {
			if (source.contains(String.valueOf(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 返回Pascal化的字符串，即将字符串的首字母大写
	 * 
	 * @param string
	 *            字符串
	 * @return 首字母大写的字符串
	 */
	public static String getPascalString(String string) {
		return string.substring(0, 1).toUpperCase() + string.substring(1);
	}

	/**
	 * 保留分隔符的划分方法
	 * 
	 * @param string
	 * @param rexs
	 * @return
	 */
	public static String[] splitRemainRex(String string, String[] rexs) {
		List<String> list = new ArrayList<String>();
		HashSet<String> set = new HashSet<String>();
		for (String rex : rexs) {
			set.add(rex);
		}
		list.add(string);
		for (int i = 0; i < rexs.length; i++) {
			List<String> newList = new ArrayList<String>();
			for (String s : list) {
				if (set.contains(s)) {
					newList.add(s);
				} else {
					newList.addAll(Arrays.asList(splitRemainRex(s, rexs[i])));
				}
			}

			list = newList;
		}

		return list.toArray(new String[] {});
	}

	/**
	 * 
	 * 保留分隔符的划分方法
	 * 
	 * @param source
	 * @param rex
	 * @return
	 */
	public static String[] splitRemainRex(String source, String rex) {
		String[] ss = split(source, rex);

		List<String> list = new ArrayList<String>();
		for (int i = 0; i < ss.length; i++) {
			if (ss[i].length() > 0) {
				list.add(ss[i].trim());
			}
			if (i < ss.length - 1) {
				list.add(rex);
			}
		}

		return list.toArray(new String[] {});
	}

	/**
	 * 
	 * String.split的行为：以字符어，分割어어时，返回的值为String[0]
	 * 
	 * 返回标识符的个数+1的String数组
	 * 
	 * @param string
	 * @param rex
	 * @return
	 */
	public static String[] split(String string, String rex) {
		int[] indexs = getAllIndex(string, rex);

		List<String> list = new ArrayList<String>();
		for (int i = 0; i < indexs.length; i++) {
			if (i == 0) {
				list.add(string.substring(0, indexs[i]));
			}

			if (i < indexs.length - 1) {
				list.add(string.substring(indexs[i] + rex.length(), indexs[i + 1]));
			} else if (i == indexs.length - 1) {
				list.add(string.substring(indexs[i] + rex.length()));
			}
		}

		if (indexs.length == 0) {
			list.add(string);
		}

		return list.toArray(new String[] {});
	}

	public static String[] splitWithoutBlank(String string, String rex) {
		int[] indexs = getAllIndex(string, rex);

		List<String> list = new ArrayList<String>();
		for (int i = 0; i < indexs.length; i++) {
			if (i == 0) {
				if (!StringUtil.isNullOrEmpty(string.substring(0, indexs[i]))) {
					list.add(string.substring(0, indexs[i]));
				}
			}

			if (i < indexs.length - 1) {
				if (!StringUtil.isNullOrEmpty(string.substring(indexs[i] + rex.length(), indexs[i + 1]))) {
					list.add(string.substring(indexs[i] + rex.length(), indexs[i + 1]));
				}
			} else if (i == indexs.length - 1) {
				if (!StringUtil.isNullOrEmpty(string.substring(indexs[i] + rex.length()))) {
					list.add(string.substring(indexs[i] + rex.length()));
				}
			}
		}

		if (indexs.length == 0) {
			if (!StringUtil.isNullOrEmpty(string)) {
				list.add(string);
			}
		}

		return list.toArray(new String[] {});
	}

	public static String[] split(String string, String[] rexs) {
		List<String> list = new ArrayList<String>();

		list.add(string);
		for (int i = 0; i < rexs.length; i++) {
			List<String> newList = new ArrayList<String>();
			for (String s : list) {
				newList.addAll(Arrays.asList(split(s, rexs[i])));
			}

			list = newList;
		}

		return list.toArray(new String[] {});
	}

	/**
	 * 判断字符是否为中文字符
	 * 
	 * @param ch
	 *            字符
	 * @return true则是中文字符，false不是中文字符
	 */
	public static boolean isChineseCharacter(char ch) {
		String string = String.valueOf(ch);
		return chinesePattern.matcher(string).find();
	}

	/**
	 * 把pascal化的字符的首字母变小写
	 * 
	 * @param string
	 *            字符串
	 * @return 去pascal化的字符串
	 */
	public static String getStringFromPascal(String string) {
		return string.substring(0, 1).toLowerCase() + string.substring(1);
	}

	/**
	 * 把字符串列用空格连接成一个字符串
	 * 
	 * @param strings
	 *            字符串列
	 * @return 字符串
	 */
	public static String getStringFromStrings(String[] strings) {
		StringBuffer buf = new StringBuffer();
		for (String string : strings) {
			buf.append(string);
			buf.append(" ");
		}

		return buf.toString().trim();
	}

	
	public static String getStringFromStrings(List<String> list, String spliter) {
		return getStringFromStrings(list.toArray(new String[] {}), spliter);
	}

	/**
	 * 把字符串列用指定的方式连接成一个字符串
	 * 
	 * @param strings
	 *            字符串列
	 * @return 字符串
	 */
	public static String getStringFromStrings(String[] strings, String spliter) {
		if (strings == null || strings.length == 0) {
			return "";
		} else {
			if (spliter == null) {
				spliter = "";
			}
			StringBuffer buf = new StringBuffer();
			for (String string : strings) {
				buf.append(string);
				buf.append(spliter);
			}

			return buf.toString().substring(0, buf.toString().length() - spliter.length());
		}
	}

	public static String[] getStringsFromString(String stirng, String spliter) {
		return stirng.split(spliter);
	}

	public static boolean isNullOrEmpty(String string) {
		return !(string != null && string.trim().length() != 0);
	}

	/**
	 * 去掉字符串中的空格和tab
	 * 
	 * @param string
	 *            字符串
	 * @return 去掉后的值
	 */
	public static String removeWhiteSpace(String string) {
		if (isNullOrEmpty(string)) {
			return "";
		} else {
			string = string.replace(" ", "");
			string = string.replace("\t", "");

			return string;
		}
	}

	/**
	 * 判断是否为英文或者数字字符串
	 * 
	 * @param string
	 *            字符串
	 * @return true则是，false则否
	 */
	public static boolean isCharOrNumberString(String string) {
		char[] cs = string.toCharArray();
		for (char c : cs) {
			if (!Character.isDigit(c) && !isEnglishCharacter(c)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 判断是否为数字字符串
	 * 
	 * @param string
	 *            字符串
	 * @return true则是，false则否
	 */
	public static boolean isNumberString(String string) {
		char[] cs = string.toCharArray();
		for (char c : cs) {
			if (!Character.isDigit(c) && c != '.') {
				return false;
			}
		}

		return true;
	}

	/**
	 * 判断字符串是否为数字
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		return isNum.matches();
	}

	public static boolean isIpAddress(String str) {

		Matcher matcher = IP_PATTERN.matcher(str);

		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否为英文字符
	 * 
	 * @param ch
	 *            字符
	 * @return true为英文字符，false则不是
	 */
	public static boolean isEnglishCharacter(char ch) {
		String a = String.valueOf(ch).toLowerCase();
		return a.charAt(0) >= 'a' && a.charAt(0) <= 'z';
	}

	public static boolean isEnglishOrNumberCharacter(char ch) {
		return isEnglishCharacter(ch) || Character.isDigit(ch);
	}

	public static boolean isNumberCharacter(char ch) {
		return Character.isDigit(ch);
	}

	public static boolean containsChinese(String word) {
		if (!isNullOrEmpty(word)) {
			for (int i = 0; i < word.length(); i++) {
				if (isChineseCharacter(word.charAt(i))) {
					return true;
				}
			}

			return false;
		}

		return false;
	}

	public static String removeParenthesis(String source) {
		return removeParenthesis(source, new String[] { "(" }, new String[] { ")" });
	}

	public static String removeParenthesis(String source, String[] starts, String[] ends) {
		for (int i = 0; i < starts.length; i++) {
			source = source.replace(starts[i], "(");
			source = source.replace(ends[i], ")");
		}
		int[] si = getAllIndex(source, "(");
		int[] ei = getAllIndex(source, ")");
		String ns = "";
		if (si.length > 0) {
			int es = 0;
			boolean find = false;
			for (int e : ei) {
				if (e > si[0]) {
					find = true;
					break;
				} else {
					es++;
				}
			}
			int lastposition = 0;

			if (find) {
				for (int i = 0; i < si.length && es < ei.length; i++) {
					if (si[i] >= lastposition) {
						ns = ns + " " + source.substring(lastposition, si[i]);
						lastposition = ei[es] + 1;
						es++;
					}
				}
			}

			ns = ns + " " + source.substring(lastposition);
			return ns.trim();
		}

		return source;
	}

	public static boolean containsEnglishOrNumber(String word) {
		if (!isNullOrEmpty(word)) {
			for (int i = 0; i < word.length(); i++) {
				if (isEnglishOrNumberCharacter(word.charAt(i))) {
					return true;
				}
			}

			return false;
		}

		return false;
	}

	public static boolean containsEnglish(String word) {
		if (!isNullOrEmpty(word)) {
			for (int i = 0; i < word.length(); i++) {
				if (isEnglishCharacter(word.charAt(i))) {
					return true;
				}
			}

			return false;
		}

		return false;
	}

	public static boolean isAllChineseCharacter(String word) {
		if (!isNullOrEmpty(word)) {
			for (int i = 0; i < word.length(); i++) {
				if (!isChineseCharacter(word.charAt(i))) {
					return false;
				}
			}

			return true;
		}

		return false;
	}

	/**
	 * 返回所有的rex在souce串中独立出现的位置 此处独立的意思为英文和数字的两边不能为英文和数字
	 * 
	 * @param source
	 *            源字符串
	 * @param rex
	 *            表达式
	 * @return 所有的位置信息
	 */
	public static int[] getAllInDependentIndex(String source, String rex) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		if (isCharOrNumberString(rex)) {
			int position = 0;
			while (position < source.length()) {
				int index = source.indexOf(rex, position);
				if (index > -1) {
					if (index > 0 && index + rex.length() < source.length()) {
						if (!isEnglishOrNumberCharacter(source.charAt(index - 1))
								&& !isEnglishOrNumberCharacter(source.charAt(index + rex.length()))) {
							list.add(source.indexOf(rex, position));
						}
					} else if (index > 0) {
						if (!isEnglishOrNumberCharacter(source.charAt(index - 1))) {
							list.add(source.indexOf(rex, position));
						}
					} else if (index + rex.length() < source.length()) {
						// if (!isEnglishOrNumberCharacter(source.charAt(index
						// + rex.length()))) {
						list.add(source.indexOf(rex, position));
						// }
					} else if (index + rex.length() == source.length()) {
						list.add(source.indexOf(rex, position));
					}
					position = index + 1;
				} else {
					break;
				}
			}
		} else {
			return getAllIndex(source, rex);
		}

		int[] ins = new int[list.size()];
		for (int i = 0; i < ins.length; i++) {
			ins[i] = list.get(i);
		}

		return ins;
	}

	/**
	 * 返回所有的rex在souce串中出现的位置
	 * 
	 * @param source
	 *            源字符串
	 * @param rex
	 *            表达式
	 * @return 所有的位置信息
	 */
	public static int[] getAllIndex(String source, String rex) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int position = 0;
		while (position < source.length()) {
			int index = source.indexOf(rex, position);
			if (index > -1) {
				list.add(source.indexOf(rex, position));
				position = index + 1;
			} else {
				break;
			}
		}

		int[] ins = new int[list.size()];
		for (int i = 0; i < ins.length; i++) {
			ins[i] = list.get(i);
		}

		return ins;
	}

	/**
	 * 返回所有的rex在souce串中出现的位置
	 * 
	 * @param source
	 *            源字符串
	 * @param rex
	 *            表达式
	 * @return 所有的位置信息
	 */
	public static int[] getAllIndex(String source, String[] rexs) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (String s : rexs) {
			int[] indexs = getAllIndex(source, s);
			for (int index : indexs) {
				list.add(index);
			}
		}

		int[] ins = new int[list.size()];
		for (int i = 0; i < ins.length; i++) {
			ins[i] = list.get(i);
		}

		return ins;
	}

	/**
	 * 倒转字符串，输入abc，返回cba
	 * 
	 * @param string
	 *            字符串
	 * @return 倒转后的值
	 */
	public static String reverseString(String string) {
		if (isNullOrEmpty(string)) {
			return "";
		} else {
			StringBuffer sb = new StringBuffer();
			for (int i = 1; i <= string.length(); i++) {
				sb.append(string.charAt(string.length() - i));
			}

			return sb.toString();
		}
	}

	public static String getNotNullValue(String string) {
		if (string == null) {
			string = "";
		}

		return string;
	}

	/**
	 * 全角转半角
	 * 
	 * @param QJstr
	 *            全角字符
	 * @return
	 */
	public static String qBchange(String QJstr) {
		if (isNullOrEmpty(QJstr)) {
			return "";
		}

		char[] c = QJstr.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375)
				c[i] = (char) (c[i] - 65248);
		}
		return new String(c);
	}

	/**
	 * 去除字符串中的特殊字符
	 * 
	 * @param str
	 *            原始字符串
	 * @return 去除特殊字符后的字符串
	 */
	public static String removeSpecialChars(String str) {
		// 清除掉所有特殊字符
		String regEx = "[`~!@#$%^&* ()_+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Matcher m = null;
		try {
			Pattern p = Pattern.compile(regEx);
			m = p.matcher(str);
		} catch (PatternSyntaxException p) {
			p.printStackTrace();
		}
		return m.replaceAll("").trim();
	}

	public static String removeSpecialCharsForCompanyfullname(String str) {
		// 清除掉所有特殊字符
		String regEx = "[`~!@#$%^&* _+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Matcher m = null;
		try {
			Pattern p = Pattern.compile(regEx);
			m = p.matcher(str);
		} catch (PatternSyntaxException p) {
			p.printStackTrace();
		}
		return m.replaceAll("").trim();
	}

	public static String getTimeString(String time, int length) {
		if (time.contains(".")) {
			int dl = time.length() - time.indexOf(".") - 1;
			if (dl > length) {
				time = time.substring(0, time.length() - dl + length);
			}
		}

		return time;
	}

	public static boolean parseBoolean(String bool) {
		try {
			return Boolean.parseBoolean(bool);
		} catch (Exception e) {
			return false;
		}
	}

	public static int getChineseLength(String string) {
		if (string == null) {
			return 0;
		} else {
			int length = 0;
			for (char c : string.toCharArray()) {
				if (isChineseCharacter(c)) {
					length++;
				}
			}

			return length;
		}
	}

	public static int getEnglishLength(String string) {
		if (string == null) {
			return 0;
		} else {
			int length = 0;
			for (char c : string.toCharArray()) {
				if (isEnglishCharacter(c)) {
					length++;
				}
			}

			return length;
		}
	}

	public static String getBlankString(int len) {
		String s = "";
		for (int i = 0; i < len; i++) {
			s = s + " ";
		}

		return s;
	}

	public static boolean sameTypeCharacter(String a, String b) {
		if (a == null || b == null) {
			return false;
		} else {
			if (containsChinese(a)) {
				if (containsChinese(b)) {
					return true;
				}
			}

			if (containsEnglish(a)) {
				if (containsEnglish(b)) {
					return true;
				}
			}

			if (containsNumber(a)) {
				if (containsNumber(b)) {
					return true;
				}
			}

			return false;
		}
	}

	public static boolean stringEquals(String a, String b) {
		if (a == null && b == null) {
			return true;
		} else if (a != null && b != null) {
			return a.equals(b);
		} else {
			return false;
		}
	}

	public static String replaceFirst(String string, String source, String target) {
		int[] ids = getAllIndex(string, source);
		if (ids.length > 0) {
			return string.substring(0, ids[0]) + target + string.substring(ids[0] + source.length());
		}

		return string;
	}

	private static boolean isOneSideAbsoluteWord(String source, char rex, int i) {
		if (i >= 0 && i < source.length()) {
			if (isEnglishCharacter(rex) || isNumberCharacter(rex)) {
				if (isEnglishCharacter(source.charAt(i)) || isNumberCharacter(source.charAt(i))) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * 约定table field和object field之间的关系
	 * 
	 * ccDdEfg = > cc_dd_efg
	 * 
	 * @param tableField
	 * @return Object field name
	 */
	public static String getTableField(String objectField) {

		if (StringUtil.isNullOrEmpty(objectField)) {
			throw new RuntimeException("empty string is not allowed");
		}

		try {
			char[] chars = objectField.toCharArray();

			List<Integer> ids = new ArrayList<Integer>();
			for (int i = 0; i < chars.length; i++) {
				if (chars[i] >= 'A' && chars[i] <= 'Z') {
					ids.add(i);
				}
			}

			if (ids.size() == 0) {
				return objectField;
			} else {
				StringBuffer sb = new StringBuffer();
				sb.append(objectField.substring(0, ids.get(0)));
				for (int i = 0; i < ids.size() - 1; i++) {
					sb.append("_" + objectField.substring(ids.get(i), ids.get(i + 1)).toLowerCase());
				}
				sb.append("_" + objectField.substring(ids.get(ids.size() - 1), objectField.length()).toLowerCase());
				return sb.toString();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 
	 * 把字符串分割为字母，数字，汉字，符号，依次输出
	 * 
	 * @param s
	 * @param keepMark
	 *            是否保留符号
	 * @return
	 */
	public static String[] getSeparatedString(String s, boolean keepMark) {

		List<String> list = new ArrayList<String>();

		char[] cs = s.toCharArray();
		int lastType = -1;
		String currentString = "";
		for (int i = 0; i < cs.length; i++) {
			if (i == 0) {
				currentString = "" + cs[i];
				lastType = getType(cs[i]);
			} else {
				int type = getType(cs[i]);
				if (type == lastType) {
					currentString = currentString + cs[i];
				} else {
					if ((lastType > 0 || keepMark) && !StringUtil.isNullOrEmpty(currentString)) {
						list.add(currentString);
					}

					currentString = "" + cs[i];
					lastType = type;
				}
			}

			if (i == cs.length - 1) {
				if ((lastType > 0 || keepMark) && !StringUtil.isNullOrEmpty(currentString)) {
					list.add(currentString);
				}
			}
		}

		return list.toArray(new String[] {});

	}

	private static int getType(char c) {
		if (StringUtil.isEnglishCharacter(c)) {
			return 1;
		} else if (StringUtil.isNumberCharacter(c) || c == '.') {
			return 2;
		} else if (StringUtil.isChineseCharacter(c)) {
			return 3;
		}

		return -1;
	}

	public static int getDependency(String source, String word, int start) {
		int dependency = 0;
		if (word.length() > 0) {
			if (start == 0) {
				dependency = dependency + 1;
			} else {
				String left = String.valueOf(source.charAt(start - 1));
				String left2 = source.substring(Math.min(0, start - 2), start);
				if (!isSameType(word.substring(0, 1), left)) {

					// aa-word
					if (StringUtil.containsEnglishOrNumber(word) && left.equals("-")
							&& StringUtil.containsEnglishOrNumber(left2)) {
						dependency = dependency + 0;
					} else {
						dependency = dependency + 1;
					}
				}
			}

			if (start + word.length() == source.length()) {
				dependency = dependency + 1;
			} else {
				String right = source.substring(start + word.length(), start + word.length() + 1);
				String right2 = source.substring(start + word.length(),
						Math.min(source.length(), start + word.length() + 2));
				if (!isSameType(word.substring(word.length() - 1), right)) {
					// word-aa
					if (StringUtil.containsEnglishOrNumber(word) && right.equals("-")
							&& StringUtil.containsEnglishOrNumber(right2)) {
						dependency = dependency + 0;
					} else {
						dependency = dependency + 1;
					}
				}
			}

			return dependency;
		}

		return -1;
	}

	private static boolean isSameType(String s1, String s2) {
		return (StringUtil.isCharOrNumberString(s1) && StringUtil.isCharOrNumberString(s2))
				|| (StringUtil.isAllChineseCharacter(s1) && StringUtil.isAllChineseCharacter(s2));
	}

	public static String getMeanfullString(String s) {
		if (StringUtil.isNullOrEmpty(s)) {
			return "";
		}
		List<String> list = new ArrayList<String>();
		for (char c : s.toCharArray()) {
			if (isChineseCharacter(c) || isEnglishOrNumberCharacter(c)) {
				list.add(String.valueOf(c));
			}
		}

		return StringUtil.getStringFromStrings(list, "");
	}

	public static boolean isMeanfullString(String s) {
		for (char c : s.toCharArray()) {
			if (isChineseCharacter(c) || isEnglishOrNumberCharacter(c)) {
				return true;
			}
		}

		return false;
	}

	public static String replace(String temple, HashMap<String, String> map) {
		HashMap<Integer, String> indexMap = new HashMap<Integer, String>();
		HashSet<Integer> set = new HashSet<Integer>();
		for (String key : map.keySet()) {
			int index = temple.indexOf(key);
			if (index >= 0) {
				indexMap.put(index, key);
				for (int i = index; i < index + key.length(); i++) {
					set.add(i);
				}
			}
		}
		String s = "";
		for (int i = 0; i < temple.length(); i++) {
			if (!set.contains(i)) {
				s = s + temple.charAt(i);
			} else {
				if (indexMap.containsKey(i)) {
					s = s + map.get(indexMap.get(i));
				}
			}
		}

		return s;
	}

	public static String replaceAll(String temple, HashMap<String, String> map) {
		String t = temple;
		for (String key : map.keySet()) {
			t = t.replace(key, map.get(key));
		}

		return t;
	}

	public static boolean contains(String big, String small, String spliter) {
		String[] ss = big.split(spliter);

		for (String s : ss) {
			if (s.trim().toLowerCase().equals(small.trim().toLowerCase())) {
				return true;
			}
		}

		return false;
	}

	public static String[] split(String source, String front, String back) {
		int[] fs = StringUtil.getAllIndex(source, front);
		int[] bs = StringUtil.getAllIndex(source, back);

		List<String> list = new ArrayList<String>();
		for (int i = 0; i < fs.length; i++) {

			int f1 = fs[i];
			for (int j = i + 1; j <= fs.length; j++) {
				int end = tryNext(source, f1, i, fs, bs, list, j);
				if (end >= 0) {
					if (i == 0) {
						list.add(0, source.substring(0, fs[i]));
					} else if (end == bs.length - 1) {
						list.add(source.substring(bs[end] + 1, source.length()));
					} else if (end == bs.length) {
						list.add(source.substring(bs[end - 1] + 1, source.length()));
					}

					i = j - 1;

					break;
				}
			}
		}

		return list.toArray(new String[] {});
	}

	private static int tryNext(String source, int f1, int i, int[] fs, int[] bs, List<String> list, int end) {
		int f2 = source.length();
		if (end < fs.length) {
			f2 = fs[end];
		}
		for (int j = bs.length - 1; j >= 0; j--) {
			int b = bs[j];
			if (f1 < b && f2 > b) {
				list.add(source.substring(f1 + 1, b));

				return j;
			}
		}

		return -1;
	}

	public static String[] splitStrict(String source, String front, String back) {
		int[] fs = StringUtil.getAllIndex(source, front);
		int[] bs = StringUtil.getAllIndex(source, back);

		if (fs.length == bs.length) {
			for (int i = 0; i < bs.length; i++) {
				if (fs[i] > bs[i]) {
					return new String[] {};
				}
			}

			List<String> list = new ArrayList<String>();
			for (int i = 0; i < fs.length; i++) {
				String head = "";
				if (i == 0) {
					head = source.substring(0, fs[i]);
				} else {
					head = source.substring(bs[i - 1] + 1, fs[i]);
				}
				if (StringUtil.isMeanfullString(head)) {
					list.add(head);
				}
				list.add(source.substring(fs[i] + 1, bs[i]));
			}

			if (bs[bs.length - 1] < source.length()) {
				list.add(source.substring(bs[bs.length - 1] + 1));
			}

			return list.toArray(new String[] {});
		}

		return new String[] {};
	}

	// 获取字符串前几位
	public static String getStringBefore(String oldcontext, int length) {
		String newcontext = "";
		if (oldcontext != null) {
			if (oldcontext.length() <= 10) {
				newcontext = oldcontext;
			} else {
				newcontext = oldcontext.substring(0, 10);
			}
		}
		return newcontext;
	}



}
