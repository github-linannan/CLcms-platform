package com.letu.healthplatform.platformmanage.report.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.letu.healthplatform.platformmanage.common.aop.PropertiesConfig;
import com.letu.healthplatform.platformmanage.common.code.Constants;
import com.letu.healthplatform.platformmanage.common.util.Rsp;
import com.letu.healthplatform.platformmanage.report.mapper.SampleReportMapper;
import com.letu.healthplatform.platformmanage.report.mapper.SiUserMapper;
import com.letu.healthplatform.platformmanage.report.model.SampleReport;
import com.letu.healthplatform.platformmanage.report.model.SiUser;
import com.letu.healthplatform.platformmanage.report.model.SortMessage;
import com.letu.healthplatform.platformmanage.report.service.SampleReportService;
import com.letu.healthplatform.platformmanage.report.service.SortMessageService;
@Service
@Transactional
public class SampleReportServiceImpl implements SampleReportService {

	private  Logger log=LoggerFactory.getLogger(SampleReportServiceImpl.class);
	
	@Autowired
	private  SampleReportMapper  sampleReportMapper;
	@Autowired
	private PropertiesConfig  propertiesConfig;
	@Autowired
	private SortMessageService  sortMessageService;
	
	@Autowired
	private SiUserMapper  siUserMapper;
	
	public Object  selectByParam(SampleReport   report, int currentPage, int pageSize){
		if(currentPage>0&&pageSize>0)
			  PageHelper.startPage(currentPage, pageSize);
			 List<Map<String,String>> allReport=sampleReportMapper.selectByParam(report);
			 if(currentPage>0&&pageSize>0){
			    return new PageInfo<>(allReport);
			 }
	   return Rsp.succ("查询成功", allReport);
	}
	
	
	/***
	 * 添加 
	 * @param report
	 * @return
	 */
	public  boolean addSampleReport(SampleReport   report){
		report.settStatus(0);
		report.settReportStatus(0);
		report.settCreatetime(new Date());
		int i=sampleReportMapper.insertSelective(report);
		if(i>0){
			return true;
		}else{
			 return false;
		}
	}
	public  boolean deleteSampleReport(String   id){
		int i=sampleReportMapper.deleteByPrimaryKey(Integer.parseInt(id));
		if(i>0){
			return true;
		}else{
			 return false;
		}
	}
	
//增加物流确认完成
	public  boolean updateSampleReportStatus(SampleReport sampleReport){
		SampleReport  newSampleReport=new SampleReport();
		newSampleReport.settId(sampleReport.gettId());
		if(sampleReport!=null &&sampleReport.gettReportStatus()!=null){
			newSampleReport.settReportStatus(sampleReport.gettReportStatus());
			newSampleReport.settReportTime(new Date());
		}
		if(sampleReport.gettStatus()!=null){
			newSampleReport.settStatus(sampleReport.gettStatus());
		}
		if(sampleReport.gettLogistics()!=null){
			newSampleReport.settLogistics(sampleReport.gettLogistics());
		}
		if(sampleReport.gettLogisticsNumber()!=null){
			newSampleReport.settLogisticsNumber(sampleReport.gettLogisticsNumber());
		}
		int i=sampleReportMapper.updateByPrimaryKeySelective(newSampleReport);
		if(i>0){
			return true;
		}else{
			 return false;
		}
	}
	
	
	
	
	/***
	 * 报告确认完成
	 * @param sampleReport
	 * @return
	 */
	public  Object  confirmReportStatus(SampleReport sampleReport){
		
			SampleReport sr=sampleReportMapper.selectByPrimaryKey(sampleReport.gettId());
			if(sr==null){
				return Rsp.fail("Id有问题 查出数据为空");
			}
			//修改报告 状态
			sampleReport.settReportStatus(1);
			this.updateSampleReportStatus(sampleReport);
			//修改患者数据
			this.updateUserLogistics(sr);
			String sendMessage=null;
			 List<Map<String,Object>> data=findData(sampleReport.gettId());
			SortMessage sortMessage=initSortMessage(data);
				if(sr.gettBatchType()==1){//个人    拿批次患者关联 用户	
					sendMessage=propertiesConfig.getSortMessage()
								.replace("$tLogistics$", sortMessage.gettLogistics())
								.replace("$tLogisticsNumber$", sortMessage.gettLogisticsNumber());
				}else if(sr.gettBatchType()==2){//团体  拿负责人关联用户
					sendMessage=propertiesConfig.getAllMessage()
							.replace("$tLogistics$", sortMessage.gettLogistics())
							.replace("$tLogisticsNumber$", sortMessage.gettLogisticsNumber())
							.replace("$tSiSampleBatch$", sortMessage.gettBatchTopic());
				}else {
					return Rsp.fail("批次类型有问题");
				}
				log.info("短信对象为 ：==="+JSONObject.toJSONString(sortMessage));
				log.info("短信内容 ：==="+sendMessage);
				sortMessage.settSendContent(sendMessage);
				boolean b=sortMessageService.insertSortMessage(sortMessage);//保存短信通知信息。
		if(b){
			return Rsp.succ("成功");
			
		}else
			 return Rsp.fail("失败");
	}
	
	public  List<Map<String,Object>>  findData(Integer id){
		SampleReport param=new SampleReport();
		param.settId(id);
	   List<Map<String,Object>> list=sampleReportMapper.selectReportById(param);
	   List<Map<String,Object>> alllist=sampleReportMapper.selectReportByAllId(param);
	   alllist.addAll(list);
	   if(alllist==null||alllist.size()==0){
		   return null;
	   }
	   return alllist;
	}
	//生成短信
	public  SortMessage  initSortMessage(List<Map<String,Object>> list){
		if(list==null){
			return null;
		}
		SortMessage  message=new SortMessage();
	   for(Map<String,Object> data :list){
		   System.out.println(data.get("t_batch_id"));
		   		if(data.get("t_batch_id")!=null)
		      message.settBatchId(Integer.parseInt(data.get("t_batch_id")+""));
		      if(data.get("t_si_sample_batch")!=null)
		      message.settBatchTopic(data.get("t_si_sample_batch")+"");
		      if(data.get("t_batch_type")!=null)
		      message.settBatchType(Integer.parseInt(data.get("t_batch_type")+""));
		      if(data.get("t_logistics")!=null)
		      message.settLogistics(data.get("t_logistics")+"");
		      if(data.get("t_logistics_number")!=null)
		      message.settLogisticsNumber(data.get("t_logistics_number")+"");
		      message.settStatus(0);
		      if(data.get("user_id")!=null)
		      message.settUserId(Integer.parseInt(data.get("user_id")+""));
		      if(data.get("user_name")!=null)
		      message.settUserName(data.get("user_name")+"");
		      if(data.get("user_sex")!=null)
		      message.settUserSex(data.get("user_sex")+"");
		      if(data.get("user_telephone")!=null)
		      message.settUserTelphone(data.get("user_telephone")+"");	
		      if(data.get("saleId")!=null)
		      message.settSaleId(Integer.parseInt(data.get("saleId")+""));
		      message.settSendEmailTatus(0);
		      message.settType(2);
	   }
	  return message;
	}


	/* (non-Javadoc)
	 * @see com.letu.healthplatform.platformmanage.report.service.SampleReportService#insetBatch(com.letu.healthplatform.platformmanage.report.model.SampleReport)
	 */
	@Override
	public Object insertBatch(List<SampleReport> list) {
		try {
			int a = sampleReportMapper.insertBatch(list);
			return Rsp.succ(Constants.SUCCESS_INSERT);
		} catch (Exception e) {
			return Rsp.fail(Constants.ERROR_INSERT);
		}
	}


	@Override
	public Object deleteByBatchId(SampleReport record) {
		try {
			int a = sampleReportMapper.deleteByBatchId(record);
			return Rsp.succ(Constants.SUCCESS_DELETE);
		} catch (Exception e) {
			return Rsp.fail(Constants.ERROR_DELETE);
		}
	}
	
	//设置用户中间表的物流及单号 
	public   int  updateUserLogistics(SampleReport record){
		SiUser  siUser=new SiUser();
		siUser.settReportStatus(1);
		siUser.settReportTime(new Date());
		siUser.settLogistics(record.gettLogistics());
		siUser.settLogisticsNumber(record.gettLogisticsNumber());
		siUser.settSiId(record.gettBatchId());
		if(record.gettBatchType()==1){
			siUser.settUserId(record.gettUserId());
		}
		int i=siUserMapper.updateUserLogistics(siUser);
		return i;
	}
	
	
}
