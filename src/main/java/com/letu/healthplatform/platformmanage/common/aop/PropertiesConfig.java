package com.letu.healthplatform.platformmanage.common.aop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties( prefix = "com.sendMessag")
@PropertySource("classpath:message.properties")
public class PropertiesConfig {

	//团体信息
	@Value("${com.sendMessage.allMessage}")
	private  String allMessage;
	//个人信息
	@Value("${com.sendMessage.sortMessage}")
	private String sortMessage;
	
	//抄送人邮箱
	@Value("${com.sendMessage.ccEmail}")
	private String ccEmail;
	
	@Value("${com.sendMessage.receive}")
	private String  receive;
	
	@Value("${com.sendMessage.detection}")
	private String detection;
	
	
	
	
	
	
	/**
	 * @return the receive
	 */
	public String getReceive() {
		return receive;
	}
	/**
	 * @param receive the receive to set
	 */
	public void setReceive(String receive) {
		this.receive = receive;
	}
	/**
	 * @return the detection
	 */
	public String getDetection() {
		return detection;
	}
	/**
	 * @param detection the detection to set
	 */
	public void setDetection(String detection) {
		this.detection = detection;
	}
	public String getAllMessage() {
		return allMessage;
	}
	public void setAllMessage(String allMessage) {
		this.allMessage = allMessage;
	}
	public String getSortMessage() {
		return sortMessage;
	}
	public void setSortMessage(String sortMessage) {
		this.sortMessage = sortMessage;
	}
	public String getCcEmail() {
		return ccEmail;
	}
	public void setCcEmail(String ccEmail) {
		this.ccEmail = ccEmail;
	}
	
	
	
}
