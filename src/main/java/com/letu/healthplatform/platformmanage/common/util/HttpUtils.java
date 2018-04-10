package com.letu.healthplatform.platformmanage.common.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtils {

	private static HttpUtils httpRequest; // 单例实例

	private static CloseableHttpClient client = HttpClients.createDefault(); // HttpClient对象

	/**
     * 非公开构造方法
	 */
	protected HttpUtils() {
		super();
	}

	/**
	 * 单例方式公共构造方法
	 * 
	 * @return HttpRequestUtilsImpl单个实例
	 */
	public synchronized static HttpUtils getInstance() {

		synchronized (HttpUtils.class) {
			if (httpRequest == null) {
				httpRequest = new HttpUtils();
			}
			return httpRequest;
		}
	}

	/**
	 * 使用GET方式通过参数发送HTTP请求
	 * 
	 * @param url
	 *            请求的URL地址
	 * @param names
	 *            请求参数名称列表
	 * @param values
	 *            请求参数值列表
	 * @return 请求返回的内容体

	 */
	public String getByParams(String url, String[] names, String[] values) throws Exception {

		if (StringUtils.isEmpty(url)) {
			throw new Exception();
		}
		if (names != null && values != null && names.length != values.length) {
			throw new Exception();
		}

		StringBuilder sb = new StringBuilder(url);
		if (names != null && values != null) {
			for (int i = 0; i < names.length; i++) {
				if (i > 0) {
					sb.append("&");
				}
				sb.append(names[i]).append("=").append(values[i]);
			}
		}
		url = sb.toString();
		HttpGet get = new HttpGet(url);
		HttpResponse response = client.execute(get);
		int code = response.getStatusLine().getStatusCode();
		if (code != 200) {
			throw new Exception(String.valueOf(code));
		}

		HttpEntity entity = response.getEntity();
		return EntityUtils.toString(entity, Consts.UTF_8);

	}

	/**
	 * 使用POST方式通过参数发送HTTP请求
	 * 
	 * @param url
	 *            请求的URL地址
	 * @param names
	 *            请求参数名称列表
	 * @param values
	 *            请求参数值列表
	 * @return 请求返回的内容体
	 */
	public String postByParams(String url, String[] names, String[] values) throws Exception {

		if (StringUtils.isEmpty(url)) {
			throw new Exception();
		}
		if (names != null && values != null && names.length != values.length) {
			throw new Exception();
		}

		List<NameValuePair> params = null;
		if (names != null && values != null) {
			params = new ArrayList<NameValuePair>(names.length);
			for (int i = 0; i < names.length; i++) {
				params.add(new BasicNameValuePair(names[i], values[i]));
			}
		}

		HttpPost post = new HttpPost(url);
		post.setEntity(new UrlEncodedFormEntity(params, Consts.UTF_8));
		HttpResponse response = client.execute(post);
		int code = response.getStatusLine().getStatusCode();
		if (code != 200) {
			throw new Exception(String.valueOf(code));
		}

		HttpEntity entity = response.getEntity();
		return EntityUtils.toString(entity, Consts.UTF_8);

	}

	/**
	 * 使用POST方式通过参数发送HTTP请求，参数为Map形式
	 * 
	 * @param url
	 *            请求的URL地址
	 * @param params
	 *            请求参数Map
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String postByParams(String url, Map<String, String> params) throws Exception {
		String[] keys = new String[params.size()];
		String[] values = new String[params.size()];
		int i = 0;
		for (String key : params.keySet()) {
			keys[i] = key;
			values[i++] = params.get(key);
		}
		return postByParams(url, keys, values);
	}

	/**
	 * 使用POSt方式通过json发送HTTP请求
	 * 
	 * @param url
	 *            请求的URL地址
	 *            请求的json内容
	 * @return 请求返回的内容体
	 */
	public static String postJson(String url, String jsonBody) throws Exception {

		if (StringUtils.isEmpty(url)) {
			throw new Exception();
		}
		if (StringUtils.isEmpty(jsonBody)) {
			throw new Exception();
		}
		HttpPost post = new HttpPost(url);
		post.addHeader("content-type", "application/vnd.kafka.json.v2+json");
		post.addHeader("Accept", "application/vnd.kafka.v2+json");
		post.setEntity(new StringEntity(jsonBody, Consts.UTF_8));
		HttpResponse response = client.execute(post);
		int code = response.getStatusLine().getStatusCode();
		if (code != 200) {
			throw new Exception(String.valueOf(code));
		}

		HttpEntity entity = response.getEntity();
		return EntityUtils.toString(entity, Consts.UTF_8);

	}

	public String postXml(String url, String xmlBody) throws ClientProtocolException, IOException,
			Exception {

		if (StringUtils.isEmpty(url)) {
			throw new Exception();
		}
		if (StringUtils.isEmpty(xmlBody)) {
			throw new Exception();
		}
		HttpPost post = new HttpPost(url);
		post.addHeader("content-type", "application/xml");
		post.setEntity(new StringEntity(xmlBody, Consts.UTF_8));
		HttpResponse response = client.execute(post);
		int code = response.getStatusLine().getStatusCode();
		if (code != 200) {
			throw new Exception(String.valueOf(code));
		}

		HttpEntity entity = response.getEntity();
		return EntityUtils.toString(entity, Consts.UTF_8);

	}

	/**
	 * 使用POST方式将对象转换为json发送HTTP请求
	 * 
	 * @param url
	 *            请求的URL地址
	 * @param object
	 *            请求的对象
	 * @return 请求返回的内容体
	 */
	public String postByObject(String url, Object object) throws ClientProtocolException, IOException,
			Exception {

		return postJson(url, JSON.toJSONString(object));
	}
}