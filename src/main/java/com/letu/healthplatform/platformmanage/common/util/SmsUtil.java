package com.letu.healthplatform.platformmanage.common.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;

/***
 * 短信提交
 * @author chenfei
 *
 */
@Component
@ConfigurationProperties(prefix="sms")
public class SmsUtil {
	
	private Logger log=Logger.getLogger(SmsUtil.class);
	
	private String url;
	
	private String account;
	
	private String password;
	
	private String code_message;
	
	public boolean sendMsg(String phone,String message){
	
	    boolean b=false;
			try{
				HttpClient client = new HttpClient();
				PostMethod method = new PostMethod(url);
				client.getParams().setContentCharset("GBK");
				method.setRequestHeader("ContentType",
						"application/x-www-form-urlencoded;charset=GBK");
				//int mobile_code = (int) ((Math.random() * 9 + 1) * 100000);
//				String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。");
				log.info(message );
				NameValuePair[] data = {// 提交短信
						new NameValuePair("account", account),
						new NameValuePair("password", password), // 查看密码请登录用户中心->验证码、通知短信->帐户及签名设置->APIKEY
						new NameValuePair("mobile", phone),
						new NameValuePair("content", message ), };
				method.setRequestBody(data);
				try {
					client.executeMethod(method);
					String SubmitResult = method.getResponseBodyAsString();
					log.info(SubmitResult);
					Document doc = DocumentHelper.parseText(SubmitResult);
					Element root = doc.getRootElement();
					String code = root.elementText("code");
					String msg = root.elementText("msg");
					String smsid = root.elementText("smsid");
					log.info(code);
					log.info(msg);
					log.info(smsid);
					if ("2".equals(code)) {
						log.info("短信提交成功");
						b=true;
					}
				} catch (HttpException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (DocumentException e) {
					e.printStackTrace();
				}
			}catch(Exception e){
				e.printStackTrace();
				b=false;
			}
       return b;
	}

	public boolean sendMsgCode(String phone,String code){
		String msg=code_message.replace("【code】", code);
		log.info(msg);
		return sendMsg(phone, msg);
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
/*	public static void main(String[] args) {
		SmsUtil sms = new SmsUtil();
		sms.setAccount("cf_lanwon");
		sms.setPassword("397ca644df1395e984d62c6dc3428de8");
		sms.setUrl("http://106.ihuyi.cn/webservice/sms.php?method=Submit");

		sms.sendMsg("13570356743", "您的验证码是：1235。请不要把验证码泄露给其他人。如非本人操作，可不用理会！120秒内有效。");

		//int mobile_code = (int) ((Math.random() * 9 + 1) * 100000);
		//System.out.println(mobile_code);
	}*/

	public String getCode_message() {
		return code_message;
	}

	public void setCode_message(String code_message) {
		this.code_message = code_message;
	}

	
}
