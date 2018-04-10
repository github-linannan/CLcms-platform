package com.letu.healthplatform.platformmanage.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 微信相关工具类
 * @author yangcheng.ai
 *
 */
public class WeChatUtil {

    private static Logger log = LoggerFactory.getLogger(WeChatUtil.class);
	
	/**
	 * 获取微信AccessToken
	 * @param appId
	 * @param secret
	 * @return
	 */
	public static String getAccessToken(String appId, String secret){
		String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&";
		String accessToken = "";
		try {
			String result = HttpUtils.getInstance().getByParams(accessTokenUrl, 
					new String[]{"appid","secret"}, new String[]{appId,secret});
			JSONObject resultJson = JSON.parseObject(result);
			accessToken = (String)resultJson.get("access_token");
			log.info("appid:" + appId + "|secret:" + secret + "|result:" + resultJson + "accessToken:" + accessToken);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accessToken;
	}
	
	/**
	 * 获取openId
	 * @param appId
	 * @param secret
	 * @param code
	 * @return
	 */
	public static String getOpenId(String appId, String secret, String code){
		String getOpenIdUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?grant_type=authorization_code&";
		String openId = "";
		String accessToken = "";
		try {
			String result = HttpUtils.getInstance().getByParams(getOpenIdUrl, 
					new String[]{"appid","secret","code"}, new String[]{appId,secret,code});
			JSONObject resultJson = JSON.parseObject(result);
			openId = (String)resultJson.get("openid");
			accessToken = (String)resultJson.get("access_token");
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("code:" + code + "|openId:" + openId + "|accessToken:" + accessToken);
		return openId;
	}

	
	/**
	 * 创建菜单
	 * @param accessToken
	 * @return
	 */
	public static String createMenu(String appId, String accessToken){
		String createUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken;
		String jsonBody = "{\"button\": "
							+ "[{\"type\": \"view\","
								+ "\"name\": \"订单购买\","
								+ "\"url\": \"https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId + ""
						+ "&redirect_uri=http://platfrom.letu.com/html/report.html"
						+ "&scope=snsapi_base&response_type=code&state=STATE#wechat_redirect\""
							+ "}]}";
		String result = "";
		try {
			log.info(jsonBody);
			result = HttpUtils.postJson(createUrl, jsonBody);
			log.info("创建菜单结果:" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 删除菜单
	 * @param accessToken
	 * @return
	 */
	public static String deleteMenu(String accessToken){
		String deleteUrl = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=" + accessToken;
		String result = "";
		try {
			result = HttpUtils.getInstance().getByParams(deleteUrl, new String[]{}, new String[]{});
			log.info("删除菜单结果:" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
//	
	public static void main(String[] args) {
		String appId = "wxc1584ab070b2fa97";
		String secret = "d4624c36b6795d1d99dcf0547af5443d";
		String accessToken = getAccessToken(appId, secret);
		deleteMenu(accessToken);
		createMenu(appId, accessToken);
//		String appId = "wx359ff6474ede1a21";
//		String secret = "a6904dad693a43cef205a234c7c8b89e";
//		String openId = "oVh_z0Tqgg5hhv--UncF8m6z_TGw";
//		String templateId = "K526-XRQd_emgy6z5h9D7gChvzK0oYiXlVtI6x79iP4";
//		String redirectUrl = "http://192.168.121.137:8070/html/report.html";
//		rt.setStudyTime(DateTimeUtil.getCurrentTime());
//		System.out.println("accessToken:" + accessToken);
//		String result = sendTemplateMsg(appId, accessToken, rt);
//		System.out.println("发送结果:" + result);
//		String code = "001kFP6H1U8Qc606DF9H16L47H1kFP6s";
//		String openId = getOpenId(appId, secret, code);
//		System.out.println("openId:" + openId);
	}
}