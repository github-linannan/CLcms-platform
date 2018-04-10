package com.letu.healthplatform.platformmanage.common.util;

import com.letu.healthplatform.platformmanage.common.vo.MailSenderInfo;

/***
 * 发送email
 * @author chenfei
 *
 */
public class EmailUtil {


	
	/**
	 * 
	 * @param tomail 接收邮箱 
	 * @param subject 主题
	 * @param content  内容
	 * @return
	 */
	private static boolean sendEmail(MailSenderInfo mailInfo ){
       boolean b=false;
		try{
			// 这个类主要来发送邮件
			SimpleMailSender sms = new SimpleMailSender();
			sms.sendTextMail(mailInfo);// 发送文体格式
			b=true;
		}catch(Exception e){
			e.printStackTrace();
			b=false;
		}
		return b;
	}
	
	
	public static boolean sendEmail(String toAddress,String topic,String content){
		MailSenderInfo   ms=new MailSenderInfo();
		ms.setMailServerHost("smtp.exmail.qq.com");
		ms.setMailServerPort("25");
		ms.setValidate(true);
		ms.setUserName("cl-healthcare@cheerlandgroup.com");//帐号 
		ms.setPassword("Clsz00");//密码
		ms.setFromAddress("cl-healthcare@cheerlandgroup.com");
		ms.setToAddress(toAddress);
		ms.setSubject(topic);//主题
		ms.setContent(content);//发送内容
		//ms.setcAddress("dongzhibo@cheerlandgroup.com"); 测试
		ms.setcAddress("dongzhibo@cheerlandgroup.com,"
				+ "business_service@cheerlandgroup.com,zhaolingzhi@cheerlandgroup.com,"
				+ "zhangxiaoxia@cheerlandgroup.com");//抄送人邮箱
		boolean b=EmailUtil.sendEmail(ms);
		return b;
	}
	
	
	
	

	public static void main(String[] args) {
	/*	EmailUtil e=new EmailUtil();
		e.mailServerHost="smtp.exmail.qq.com";
		e.mailServerPort="25";
		e.userName="chenfei@cheerlandgroup.com";
		e.password="Cf123456";
		e.fromAddress="chenfei@cheerlandgroup.com";*/
		//"919352524@qq.com", "测试2222222", "说了。。。"
		MailSenderInfo   ms=new MailSenderInfo();
		ms.setMailServerHost("smtp.exmail.qq.com");
		ms.setMailServerPort("25");
		ms.setValidate(true);
		ms.setUserName("chenfei@cheerlandgroup.com");
		ms.setPassword("Cf123456");
		ms.setFromAddress("chenfei@cheerlandgroup.com");
		ms.setToAddress("919352524@qq.com,zhengyan@cheerlandgroup.com");
		ms.setSubject("样本报告");
		ms.setContent("我是测试数据");
		ms.setcAddress("linannan@cheerlandgroup.com,dongzhibo@cheerlandgroup.com");
	
		
		boolean b=EmailUtil.sendEmail(ms);
		System.out.println(b);
		
	}

	
}
