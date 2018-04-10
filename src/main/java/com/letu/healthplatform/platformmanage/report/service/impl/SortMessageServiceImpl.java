package com.letu.healthplatform.platformmanage.report.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.letu.healthplatform.platformmanage.common.util.EmailUtil;
import com.letu.healthplatform.platformmanage.common.util.Rsp;
import com.letu.healthplatform.platformmanage.common.util.SmsUtil;
import com.letu.healthplatform.platformmanage.report.mapper.SortMessageMapper;
import com.letu.healthplatform.platformmanage.report.model.SortMessage;
import com.letu.healthplatform.platformmanage.report.service.SortMessageService;
import com.letu.healthplatform.platformmanage.report.vo.SampleInfoReportVo;

@Service
public class SortMessageServiceImpl  implements  SortMessageService {

	private Logger  log=LoggerFactory.getLogger(SortMessageServiceImpl.class);
	
	@Autowired
	private SortMessageMapper  sortMessageMapper;
	
	@Autowired
	private SmsUtil        SmsUtil;
	
	public boolean  insertSortMessage(SortMessage message){
		int i=	sortMessageMapper.insertSelective(message);
			if(i>0){
				return true;
			}else{
				return false;
			}
	}
	
	public Object  selectByParam(SortMessage   message, int currentPage, int pageSize){
		if(currentPage>0&&pageSize>0)
			  PageHelper.startPage(currentPage, pageSize);
			 List<SortMessage> allMessage=sortMessageMapper.selectByParam(message);
			 if(currentPage>0&&pageSize>0){
			    return new PageInfo<>(allMessage);
			 }
	   return Rsp.succ("查询成功", allMessage);
	}
	
	
	public Object sendSortMessage(String ids){
		 List<SortMessage> allMessage=sortMessageMapper.selectbyIds(ids);
		  List list=new ArrayList();
		  for(SortMessage m:allMessage){
			 boolean b= SmsUtil.sendMsg(m.gettUserTelphone(), m.gettSendContent());
			 if(b){
				 list.add(m.gettId());
			 }
		  }
		  log.info("  message info ids: "+list.toString());
		  int i= 0;
		  if(list!=null&&list.size()>0){
				 Map<String,Object> param=new HashMap<String,Object>();
				 param.put("ids", StringUtils.join(list.toArray(), ","));
				 param.put("tStatus", 1);
		  i= sortMessageMapper.updateBatchMessage(param);
		  } if(i>0){
			  return Rsp.succ("发送短信成功");
		  }else{
			  return Rsp.fail("发送失败");
		  }
	}
	
	public Object ignoreSortMessage(String ids){
		 Map<String,Object> param=new HashMap<String,Object>();
		 param.put("ids", ids);
		 param.put("tStatus", 2);
		
			int i= sortMessageMapper.updateBatchMessage(param);
		if(i>0){
			return Rsp.succ("忽略成功");
		}else{
			return Rsp.fail("忽略失败");
		}
	}

	@Override
	public Object sendEmailMessage(String batchIds) {
		
		 List<Map<String,Object>> allEmailMessage=sortMessageMapper.selectbyBatchIds(batchIds);
		Map<Object,List<Map<String,Object>>> saleMessage=new HashMap<Object,List<Map<String,Object>>>();
		  for(Map m:allEmailMessage){
			if(saleMessage.containsKey(m.get("tSaleId"))){
				List<Map<String,Object>> alist=saleMessage.get(m.get("tSaleId"));
				alist.add(m);
			}else{
				List<Map<String,Object>> alist=new ArrayList();
				alist.add(m);
				saleMessage.put(m.get("tSaleId"), alist);
			}
		  }
		boolean b=  sendEmail(saleMessage);
		 int i= 0;
			if(b)
		   i= sortMessageMapper.updateEmailStatus(batchIds);
		  if(1>0){
			  return Rsp.succ("邮件发送成功");
		  }else{
			  return Rsp.fail("邮件发送失败");
		  }
	}
	
	
	public boolean sendEmail(Map<Object,List<Map<String,Object>>> sendEmails){
	   if(sendEmails==null||sendEmails.size()==0){
		   return true;
	   }
	   for(List<Map<String,Object>> lists:sendEmails.values()){
		   String saleEmail="",superEmail="";
			StringBuffer contents=new StringBuffer("尊敬的销售同事：您辛苦了，客户的报告我们已经邮寄出去了，详情如下，如有问题请及时与客服部联系，谢谢！");
			contents.append("<table class='table table-border table-bordered table-hover table-bg table-sort' style='width: 100%;empty-cells: show;background-color: transparent;border-collapse: collapse;border-spacing: 0;border: 1px solid #ddd;border-bottom: 0;font-size: 14px;color: #333;'>		<thead>"
					+ "<tr>"
					//+ "<th width='100'  style='background-color: #f5fafe;border-left: 1px solid #ddd;border-bottom: 1px solid #ddd;text-align: center;padding: 8px;line-height: 20px;word-break: break-all;font-weight: bold;'>采样时间</th>"
					+ "<th width='100'  style='background-color: #f5fafe;border-left: 1px solid #ddd;border-bottom: 1px solid #ddd;text-align: center;padding: 8px;line-height: 20px;word-break: break-all;font-weight: bold;'>省份</th>"
					+ "<th width='100'  style='background-color: #f5fafe;border-left: 1px solid #ddd;border-bottom: 1px solid #ddd;text-align: center;padding: 8px;line-height: 20px;word-break: break-all;font-weight: bold;'>样本来源</th>"
					+ "<th width='100'  style='background-color: #f5fafe;border-left: 1px solid #ddd;border-bottom: 1px solid #ddd;text-align: center;padding: 8px;line-height: 20px;word-break: break-all;font-weight: bold;'>商务订单号</th>"
					+ "<th width='100'  style='background-color: #f5fafe;border-left: 1px solid #ddd;border-bottom: 1px solid #ddd;text-align: center;padding: 8px;line-height: 20px;word-break: break-all;font-weight: bold;'>采样编号</th>"
					+ "<th width='100'  style='background-color: #f5fafe;border-left: 1px solid #ddd;border-bottom: 1px solid #ddd;text-align: center;padding: 8px;line-height: 20px;word-break: break-all;font-weight: bold;'>姓名</th>"
//					+ "<th width='100'  style='background-color: #f5fafe;border-left: 1px solid #ddd;border-bottom: 1px solid #ddd;text-align: center;padding: 8px;line-height: 20px;word-break: break-all;font-weight: bold;'>电话</th>"
					+ "<th width='100'  style='background-color: #f5fafe;border-left: 1px solid #ddd;border-bottom: 1px solid #ddd;text-align: center;padding: 8px;line-height: 20px;word-break: break-all;font-weight: bold;'>样本接收时间</th>"
					+ "<th width='100'  style='background-color: #f5fafe;border-left: 1px solid #ddd;border-bottom: 1px solid #ddd;text-align: center;padding: 8px;line-height: 20px;word-break: break-all;font-weight: bold;'>检测项目</th>"
					+ "<th width='100'  style='background-color: #f5fafe;border-left: 1px solid #ddd;border-bottom: 1px solid #ddd;text-align: center;padding: 8px;line-height: 20px;word-break: break-all;font-weight: bold;'>销售对接人</th>"
					+ "<th width='100'  style='background-color: #f5fafe;border-left: 1px solid #ddd;border-bottom: 1px solid #ddd;text-align: center;padding: 8px;line-height: 20px;word-break: break-all;font-weight: bold;'>发报告时间</th>"
					+ "<th width='100'  style='background-color: #f5fafe;border-left: 1px solid #ddd;border-bottom: 1px solid #ddd;text-align: center;padding: 8px;line-height: 20px;word-break: break-all;font-weight: bold;'>物流公司</th>"
					+ "<th width='100'  style='background-color: #f5fafe;border-left: 1px solid #ddd;border-bottom: 1px solid #ddd;text-align: center;padding: 8px;line-height: 20px;word-break: break-all;font-weight: bold;'>物流单号</th>"
					+ "</tr></thead><tbody>");
			int i=0;
			for(Map m:lists){
				log.info(i+"");
		    	if(i==0){
		    		saleEmail=m.get("tSaleEmail")+"";
		    		if(m.get("tSaleSuperiorEamil")!=null&&!"".equals(m.get("tSaleSuperiorEamil")))
		    		  superEmail=m.get("tSaleSuperiorEamil")+"";
		    	}
		       contents.append("<tr>"
		       				//+ "<td style='border-left: 1px solid #ddd; border-bottom: 1px solid #ddd;text-align: center;padding: 8px;line-height: 20px;word-break: break-all;font-size: 12px;color: #333;'>"+m.get("tTestingTime")+"</td>"
		       				+ "<td style='border-left: 1px solid #ddd; border-bottom: 1px solid #ddd;text-align: center;padding: 8px;line-height: 20px;word-break: break-all;font-size: 12px;color: #333;'>"+m.get("tSiArea")+"</td>"
		       				+ "<td style='border-left: 1px solid #ddd; border-bottom: 1px solid #ddd;text-align: center;padding: 8px;line-height: 20px;word-break: break-all;font-size: 12px;color: #333;'>"+m.get("tSiSource")+"</td>"
		       				+ "<td style='border-left: 1px solid #ddd; border-bottom: 1px solid #ddd;text-align: center;padding: 8px;line-height: 20px;word-break: break-all;font-size: 12px;color: #333;'>"+m.get("tNumber")+"</td>"
		       				+ "<td style='border-left: 1px solid #ddd; border-bottom: 1px solid #ddd;text-align: center;padding: 8px;line-height: 20px;word-break: break-all;font-size: 12px;color: #333;'>"+m.get("tSampleNumber")+"</td>"
		       				+ "<td style='border-left: 1px solid #ddd; border-bottom: 1px solid #ddd;text-align: center;padding: 8px;line-height: 20px;word-break: break-all;font-size: 12px;color: #333;'>"+m.get("tUserName")+"</td>"
		       				//+ "<td style='border-left: 1px solid #ddd; border-bottom: 1px solid #ddd;text-align: center;padding: 8px;line-height: 20px;word-break: break-all;font-size: 12px;color: #333;'>"+m.get("tUserTelphone")+"</td>"
		       				+ "<td style='border-left: 1px solid #ddd; border-bottom: 1px solid #ddd;text-align: center;padding: 8px;line-height: 20px;word-break: break-all;font-size: 12px;color: #333;'>"+m.get("sampleTime")+"</td>"
		       				+ "<td style='border-left: 1px solid #ddd; border-bottom: 1px solid #ddd;text-align: center;padding: 8px;line-height: 20px;word-break: break-all;font-size: 12px;color: #333;'>"+m.get("tSampleItem")+"</td>"
		       				+ "<td style='border-left: 1px solid #ddd; border-bottom: 1px solid #ddd;text-align: center;padding: 8px;line-height: 20px;word-break: break-all;font-size: 12px;color: #333;'>"+m.get("tSaleName")+"</td>"
		       				+ "<td style='border-left: 1px solid #ddd; border-bottom: 1px solid #ddd;text-align: center;padding: 8px;line-height: 20px;word-break: break-all;font-size: 12px;color: #333;'>"+m.get("tReportTime")+"</td>"
		       				+ "<td style='border-left: 1px solid #ddd; border-bottom: 1px solid #ddd;text-align: center;padding: 8px;line-height: 20px;word-break: break-all;font-size: 12px;color: #333;'>"+m.get("tLogistics")+"</td>"
		       				+ "<td style='border-left: 1px solid #ddd; border-bottom: 1px solid #ddd;text-align: center;padding: 8px;line-height: 20px;word-break: break-all;font-size: 12px;color: #333;'>"+m.get("tLogisticsNumber")+"</td></tr>");
			  i++;
			}
			contents.append("</tbody></table>");
			contents.append(" <br/>深圳市乐土精准医疗科技有限公司（客服中心）");
			contents.append(" <br/>Tel：400-6698-988");
			contents.append(" <br/>E-mall：cl-healthcare@cheerlandgroup.com");
			contents.append(" <br/>Add:深圳市南山区桃源街道南方科技大学创园8栋");
			if(!"".equals(superEmail)){
				saleEmail=saleEmail+","+superEmail;
			}
			log.info("收件人为{},抄送人为:{},邮件内容为：{}",saleEmail,superEmail,contents);
			EmailUtil.sendEmail(saleEmail, "样本报告已寄出", contents.toString());
		    
	   }
	   return true;
	   
	   
	}

	@Override
	public Object queryByParam( Map<String,Object> param, int currentPage, int pageSize) {
		if(currentPage>0&&pageSize>0)
			  PageHelper.startPage(currentPage, pageSize);
			  
			 List<SortMessage> allMessage=sortMessageMapper.queryByParam(param);
			 if(currentPage>0&&pageSize>0){
			    return new PageInfo<>(allMessage);
			 }
	   return Rsp.succ("查询成功", allMessage);
	}
	
	
	
	
	@Override
	public Object queryExportParam( Map<String,Object> param, int currentPage, int pageSize) {
		if(currentPage>0&&pageSize>0)
			  PageHelper.startPage(currentPage, pageSize);
			 List<SampleInfoReportVo> allMessage=sortMessageMapper.queryExportParam(param);
			 if(currentPage>0&&pageSize>0){
			    return new PageInfo<>(allMessage);
			 }
	   return  allMessage;
	}
	
	
}
