package com.letu.healthplatform.platformmanage.report.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.letu.healthplatform.platformmanage.common.aop.PropertiesConfig;
import com.letu.healthplatform.platformmanage.common.code.Constants;
import com.letu.healthplatform.platformmanage.common.util.DateUtil;
import com.letu.healthplatform.platformmanage.common.util.ListSetArrayUtil;
import com.letu.healthplatform.platformmanage.common.util.Rsp;
import com.letu.healthplatform.platformmanage.report.mapper.SampleInformationMapper;
import com.letu.healthplatform.platformmanage.report.mapper.SampleReportMapper;
import com.letu.healthplatform.platformmanage.report.mapper.SiLoginMapper;
import com.letu.healthplatform.platformmanage.report.mapper.SiUserMapper;
import com.letu.healthplatform.platformmanage.report.mapper.SortMessageMapper;
import com.letu.healthplatform.platformmanage.report.model.SampleInformation;
import com.letu.healthplatform.platformmanage.report.model.SampleReport;
import com.letu.healthplatform.platformmanage.report.model.SiLogin;
import com.letu.healthplatform.platformmanage.report.model.SiUser;
import com.letu.healthplatform.platformmanage.report.model.SortMessage;
import com.letu.healthplatform.platformmanage.report.service.SampleInformationService;
import com.letu.healthplatform.platformmanage.report.vo.Patient;
import com.letu.healthplatform.platformmanage.report.vo.SampleInformationVo;
import com.letu.healthplatform.platformmanage.sys.mapper.LoginMapper;
import com.letu.healthplatform.platformmanage.sys.model.Login;
import com.letu.healthplatform.platformmanage.user.mapper.AppUserMapper;
import com.letu.healthplatform.platformmanage.user.model.AppUser;
import com.letu.healthplatform.platformmanage.user.vo.AppUserVo;


@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = {
		RuntimeException.class, Exception.class })
public class SampleInformationServiceImpl implements SampleInformationService {
	
	
	private static final Logger log = LogManager.getLogger(SampleInformationServiceImpl.class);

	@Autowired
	private SampleInformationMapper SampleInformationMapper;

	@Autowired
	private SiLoginMapper SiLoginMapper;
	
	@Autowired
	private SiUserMapper SiUserMapper;

	@Autowired
	private  SampleReportMapper  sampleReportMapper;
	
	@Autowired
	private AppUserMapper AppUserMapper;
	
	@Autowired
	private SortMessageMapper  SortMessageMapper;
	
	@Autowired
	private LoginMapper LoginMapper;
	
	@Autowired
	private PropertiesConfig  propertiesConfig;
	

	
	@Override
	public Object findSampleInformationPage(SampleInformationVo record, int page, int pageSize) {
		List<SampleInformationVo> list = null;
		List<AppUserVo> aulist =null;
		try {
			if (page > 0 && pageSize > 0)
			  PageHelper.startPage(page, pageSize);
			list = SampleInformationMapper.selectByParam(record);
			if(list.size()>0) 
			 {
				//查询患者信息
				for (int i = 0; i < list.size(); i++) {
					SampleInformationVo siv = list.get(i);
					aulist = AppUserMapper.selectPatient(siv.gettSiId());
					siv.setList(aulist);//添加患者信息
				}
			 }
			
			if (page > 0 && pageSize > 0) {
				return new PageInfo<>(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(Constants.ERROR_FIND, e);
		}
		return Rsp.succ(Constants.SUCCESS_FIND, list);
	}

	@Override
	public Object findSampleInformation(String tId) {
		SampleInformation record = null;
		List<AppUserVo> aulist =null;
		List<SampleInformationVo>  slist =null;
		SampleInformationVo svo=null;
		if (StringUtils.isBlank(tId)) {
			return Rsp.fail(Constants.DEFAULT_KEY_ISNULL);
		}
		try {
			record = SampleInformationMapper.selectByPrimaryKey(Integer.valueOf(tId));
			//查找对应的患者人员信息
			if(record!=null) 
			 {
				slist =new LinkedList<SampleInformationVo>();
				svo =new SampleInformationVo();
				svo.settSiId(record.gettSiId());
				svo.settSiNumber(record.gettSiNumber());
				svo.settUserId(record.gettUserId());
				svo.settSaleId(record.gettSaleId());
				svo.settSiArea(record.gettSiArea());
				svo.settSiSource(record.gettSiSource());
				svo.settSiSampleBatch(record.gettSiSampleBatch());
				svo.settSiLabel(record.gettSiLabel());
				svo.settReciveUserId(record.gettReciveUserId());
				svo.settSiCreatedate(record.gettSiCreatedate());
				//查询患者信息
				aulist = AppUserMapper.selectPatient(record.gettSiId());
				svo.setList(aulist);//添加患者信息
			 }
		} catch (Exception e) {
			e.printStackTrace();
			log.error(Constants.ERROR_FIND, e);
			return Rsp.fail(Constants.ERROR_DELETE);
		}
		return Rsp.succ(Constants.SUCCESS_FIND, svo);
	}


	@Override
	public Object insertSelective(SampleInformation record) {
		try {
			
			if(StringUtils.isBlank(record.getPatient())) {
				return Rsp.fail("患者人人员信息为空");
			}
			
			log.info("患者信息："+record.getPatient());
			List<Patient> plist = getPatient(record.getPatient());
			
			if(StringUtils.isBlank(record.gettSaleId().toString())) {
				return Rsp.fail("销售人员编号为空");
			}
			
			//如果是团体  
			if(StringUtils.equals(Constants.reportCode.ORGANIZATION, record.gettSiLabel().toString())) {
				if(StringUtils.isBlank(record.gettUserId().toString()))return Rsp.fail("负责人编号为空");
			}
			
			int a = SampleInformationMapper.insertSelective(record);
			if (a > 0&&record.gettSiId()!=null) {
				
				//---------------保存样本信息和销售人员的关系------------
				insertSiLogin(record);
				//--------------------保存样本与患者关系--------------------
				insertBatchSiuser(record,plist);
				
				//-------------------生成报告信息（个人和团体）-----------------------------------------
				if(StringUtils.equals(Constants.reportCode.PERSION, record.gettSiLabel().toString())){
					//如果是个人样本,负责人就是自己
					List<SampleReport> list = new LinkedList<SampleReport>();
					if(plist.size()>0) {
						for (int i = 0; i < plist.size(); i++) {
							Patient p =plist.get(i);
							SampleReport sr = getSampleReport(record,p,0);
							list.add(sr);
							//-------------------插入短信信息数据---------------------
							getSortMessage(record,p,Constants.operationCode.INSERT,0);
						}
						sampleReportMapper.insertBatch(list);
					}
				}//团体
				else if(StringUtils.equals(Constants.reportCode.ORGANIZATION, record.gettSiLabel().toString())){
					//-------------------插入报告数据-----------------
					SampleReport sr = getSampleReport(record,null,0);
					sampleReportMapper.insertSelective(sr);
				    //-------------------插入短信信息数据---------------------
					getSortMessage(record,null,Constants.operationCode.INSERT,0);
				}
				return Rsp.succ(Constants.SUCCESS_INSERT);
			}else {
				return Rsp.fail("实体的编号为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(Constants.ERROR_INSERT, e);
		}
		return Rsp.fail(Constants.ERROR_INSERT);
	}
	
	
	

	@Override
	public Object updateByPrimaryKeySelective(SampleInformation record) {
		
		if(StringUtils.isBlank(record.gettSiId().toString())) {
			return Rsp.fail("样本信息主键为空");
		}
		
		if(StringUtils.isBlank(record.getPatient())) {
			
			return Rsp.fail("患者人人员信息为空");
		}
		
		if(StringUtils.isBlank(record.gettSaleId().toString())) {
			return Rsp.fail("销售人员编号为空");
		}
		
		//如果是团体  
		if(StringUtils.equals(Constants.reportCode.ORGANIZATION, record.gettSiLabel().toString())) {
			if(StringUtils.isBlank(record.gettUserId().toString()))return Rsp.fail("负责人编号为空");
		}
		
		try {
			//------------------获取患者信息------------------
			List<Patient> plist = getPatient(record.getPatient());
		
			//------------------查询旧的患者人员信息------------------
			List<SiUser> slist=SiUserMapper.selectByPrimaryKey(record.gettSiId());
			//----------------------------------个人或团体处理
			if(StringUtils.equals(Constants.reportCode.PERSION, record.gettSiLabel().toString())){
				
				//获取旧的患者负责人ID,封装数组
				List<Integer> oldlist =new LinkedList<Integer>();
				for (int i = 0; i < slist.size(); i++) {
					oldlist.add(slist.get(i).gettUserId());
				}
				Integer[] oldArry = ListSetArrayUtil.ListToArry(oldlist);
				log.info("患者修改数据的大小:{},"+plist.toString());
				if(plist.size()>0) {
					List<Integer> pl  =new LinkedList<Integer>();
					for (int i = 0; i < plist.size(); i++) {
						pl.add(plist.get(i).getId());
					}
					//获取修改的患者负责人ID
					Integer[] newArry =ListSetArrayUtil.ListToArry(pl);
					
					//--------------删除患者报告的数据（旧数据不包含原来的患者信息删除）--------------
					List<Integer> list =ListSetArrayUtil.compare(newArry,oldArry);
					log.info("需要删除的患者ID:{},"+list.toString());
					if(list.size()>0) {
						for(Integer userId:list) {
							//如果负责人与原来的不一样，删除原来的负责人的短信信息
							deleteReportBatchId(record.gettSiId(),userId);
							//删除原来的短信信息
							SortMessage sm = new SortMessage();
							sm.settUserId(userId);//负责人编号
							sm.settBatchId(record.gettSiId());//批次
							sm.settBatchType(record.gettSiLabel());//类型
							SortMessageMapper.deleteBatchType(sm);
							oldlist.remove(userId);
						}
					}
					
					//-----------------------删除后更新剩下原来的旧数据------------------------------	
					log.info("需要修改的患者数据:{},"+oldlist.toString());
				    if(oldlist.size()>0) {
				    	for (int i = 0; i < oldlist.size(); i++) {
				    		SampleReport report =  getSampleReport(record,null,0);
							sampleReportMapper.updateBatchById(report);
							getSortMessage(record,null,Constants.operationCode.UPDATE,oldlist.get(i));
						}
				    }
					
					//新增患者报告的数据（旧数据没有的患者信息新增）
					List<Integer> list1 =ListSetArrayUtil.compare(oldArry,newArry);
					log.info("需要新增的患者数据:{},"+list1.toString());
					if(list1.size()>0) {
						for(Integer userId:list1) {
							SampleReport sreport =  getSampleReport(record,null,userId);
							sampleReportMapper.insertSelective(sreport);
							//如果负责人与原来的不一样，删除原来的负责人的短信信息
							getSortMessage(record,null,Constants.operationCode.UPDATE,userId);
						}
					}
				}
				
			}//团体
			else if(StringUtils.equals(Constants.reportCode.ORGANIZATION, record.gettSiLabel().toString())){
				SampleReport sreport = getSampleReport(record,null,0);
				sampleReportMapper.updateBatchById(sreport);
				
				//-------------------插入短信信息数据---------------------
				SampleInformation sinfo=SampleInformationMapper.selectByPrimaryKey(record.gettSiId());
				//团体的负责人与修改的负责人不一样，重新插入数据
				if(!StringUtils.equals(record.gettUserId().toString(),sinfo.gettUserId().toString())||
						!StringUtils.equals(record.gettSaleId().toString(),sinfo.gettSaleId().toString())) {
					getSortMessage(record,null,Constants.operationCode.UPDATE,0);
				}
			}
			
			
			//--------------------------查询原来的数据---------------------------------------
			if(slist.size()>0) {
				//删除原来的关系
				SiUserMapper.deleteBatch(slist);
			}
			insertBatchSiuser(record,plist);
			
			//--------------------------查询销售人员/修改---------------------------------------
			List<SiLogin> sllist=	SiLoginMapper.selectByPrimaryKey(record.gettSiId());
			if(sllist.size()>0) {
				//删除原来的
				SiLoginMapper.deleteBatch(sllist);
			}
			//保存销售人员和样本信息的关系
			insertSiLogin(record);
			
			//------------------修改样本信息------------------
			SampleInformationMapper.updateByPrimaryKeySelective(record);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(Constants.ERROR_UPDATE, e);
			return Rsp.fail(Constants.ERROR_UPDATE);
		}
		return Rsp.succ(Constants.SUCCESS_UPDATE);
	}

	
	/***
	 * @param record
	 * @param list
	 */
	public void insertBatchSiuser(SampleInformation record,List<Patient> list){
		List<SiUser>  silist =new LinkedList<SiUser>();
		if(list.size()>0) {
			for (int i = 0; i < list.size(); i++) {
				Patient p =list.get(i);
				SiUser siu =new SiUser();
				siu.settSiId(record.gettSiId());
				siu.settSiProject(p.getItem());//检测项目
				siu.settUserId(p.getId());
				siu.settComeNumber(p.gettComeNumber());//来样编号
				siu.settTestingTime(record.gettSiCreatedate());//采样时间
				silist.add(siu);
			}
		}
		//患者为多个 调用批量添加
		SiUserMapper.insertBatch(silist);
	}
	
	
	
	
	/** 
	 * @param SampleInformation 样本信息
	 */
	public void insertSiLogin(SampleInformation record){
		SiLogin sil =new  SiLogin();
		sil.settSiId(record.gettSiId());
		sil.settMLoginId(record.gettSaleId());
		SiLoginMapper.insert(sil);
	}
	
	
	/** 
	 * @param SampleInformation  样本基本信息
	 * @param Patient  患者信息
	 * @return
	 */
	public SampleReport getSampleReport(SampleInformation record,Patient p,Integer userId){
		SampleReport sr = new SampleReport();
		sr.settBatchId(record.gettSiId());//批次ID
		sr.settSaleId(record.gettSaleId());//销售人员ID
		sr.settBatchType(record.gettSiLabel());//类型
		//如果是病人,负责人是自己 /如果是销售人员，填写自己的负责人
		if(p!=null){
			sr.settUserId(p.getId());
		}else if(userId!=0&&StringUtils.isNotBlank(String.valueOf(userId))){
			sr.settUserId(userId);
		}else if(record.gettUserId()!=null){
			sr.settUserId(record.gettUserId());
		}
		return sr;
	}
	
	
	/**
	 * @see  
	 * @param SampleInformation  样本信息
	 * @param Patient  患者信息
	 * @return
	 */
	public  void getSortMessage(SampleInformation record,Patient p,String operat,Integer userId){
		SortMessage sm = new SortMessage();
		sm.settBatchId(record.gettSiId());//批次ID
		sm.settBatchTopic(record.gettSiSampleBatch());//批次主题
		sm.settBatchType(record.gettSiLabel());//类型
		sm.settStatus(0);//默认为0
		Integer userId1=null;
		if(p!=null){
			userId1 =p.getId();
		}else if(userId>0) {
			userId1=userId;
		}else{
			userId1 = record.gettUserId();
		}
		//-----------团体的负责人如果与原来的不一样,删除之前的短信信息（修改操作执行此方法）--------------
		if(StringUtils.equals(Constants.operationCode.UPDATE,operat)){
			sm.settUserId(userId1);
			SortMessageMapper.deleteBatchType(sm);
		}
		//负责人是自己
		AppUser   u=	AppUserMapper.selectByPrimaryKey(userId1);
		if(u!=null) {
			sm.settUserId(u.getUserId());//登录ID
			sm.settUserName(u.getUserName());//姓名
			sm.settUserSex(u.getUserSex());//性别
			sm.settUserTelphone(u.getUserTelephone());//电话号码
			//sm.settUserEmail();
		}
		Login  lo =	LoginMapper.selectByPrimaryKey(record.gettSaleId());
		if(lo!=null) {
			sm.settSaleId(lo.gettId());//销售人员ID
			sm.setSaleEmail(lo.gettEmail());
		}
		
		//-----------------------插入两条信息 （一条接收信息 /一条检测中信息）
		sm.settType(Constants.mesType.RECEIVE);
		sm.settSendEmailTatus(1);
		sm.settSendContent(propertiesConfig.getReceive());//接收短信
		SortMessageMapper.insert(sm);
		//检测中的消息第二天发送
		sm.settSendTime(DateUtil.getNextDate());
		sm.settType(Constants.mesType.DETECTION);
		sm.settSendContent(propertiesConfig.getDetection());//检测中短信
		SortMessageMapper.insert(sm);
	}
	
	
	
	
	
	/**获取病人信息
	 * @param patient
	 * @return
	 */
	public  static  List<Patient> getPatient(String patient){
		JSONArray jsonArray = JSONArray.parseArray(patient);
		List<Patient> plist = null;
		if(jsonArray.size()>0) {
			 plist = new LinkedList<Patient>(); 
			for (int i = 0; i < jsonArray.size(); i++) {
				Patient p = new Patient();
				p.setId(jsonArray.getJSONObject(i).getInteger("id"));
				p.settComeNumber(jsonArray.getJSONObject(i).getString("tComeNumber"));
				p.setItem(jsonArray.getJSONObject(i).getString("item"));
				plist.add(p);
			}
		}
		return plist;
	}
	
	
	@Override
	public Object deleteByPrimaryKey(String tSiId) {

		try {
			if (StringUtils.isBlank(tSiId)) {
				return Rsp.fail(Constants.DEFAULT_KEY_ISNULL);
			}
			
			SampleInformation record = SampleInformationMapper.selectByPrimaryKey(Integer.valueOf(tSiId));
		    if(record==null) {
		    	return Rsp.succ(Constants.ERROR_DELETE);
		    }
		    
		  //查询患者人员
		  List<SiUser> slist=SiUserMapper.selectByPrimaryKey(Integer.valueOf(tSiId));
			
			//删除
			
			//个人
			if(StringUtils.equals(Constants.reportCode.PERSION, record.gettSiLabel().toString())){
				for (int i = 0; i < slist.size(); i++) {
					deleteReportBatchId(Integer.valueOf(tSiId),slist.get(i).gettUserId());
					deleteBatchType(Integer.valueOf(tSiId), slist.get(i).gettUserId(), record.gettSiLabel());
				 }
			}//团体
			else if(StringUtils.equals(Constants.reportCode.ORGANIZATION, record.gettSiLabel().toString())){
				  deleteReportBatchId(Integer.valueOf(tSiId),record.gettUserId());
				  deleteBatchType(Integer.valueOf(tSiId), record.gettUserId(), record.gettSiLabel());
			}

			
			//-------------------删除样本信息
			int a = SampleInformationMapper.deleteByPrimaryKey(Integer.valueOf(tSiId));
			
			
			//--------------------------------------删除样本与患者关系----------------------------
			if(slist.size()>0) {
				SiUserMapper.deleteBatch(slist);
			}
			
			//--------------------------------------删除样本与销售人员关系----------------------------
			List<SiLogin> sllist=	SiLoginMapper.selectByPrimaryKey(Integer.valueOf(tSiId));
			if(sllist.size()>0) {
				SiLoginMapper.deleteBatch(sllist);
			}
			return Rsp.succ(Constants.SUCCESS_DELETE);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(Constants.ERROR_DELETE, e);
			return Rsp.succ(Constants.ERROR_DELETE);
		}
	}
	
	/***
	 * 删除信息
	 * @param tSiId  样本id
	 * @param userId  接受负责人id
	 */
	public void  deleteReportBatchId(Integer  tSiId,Integer userId) {
		SampleReport sreport =  new SampleReport();
		sreport.settBatchId(tSiId);
		sreport.settUserId(userId);
		sampleReportMapper.deleteByBatchId(sreport);
	}

	
	/** 
	 * @param tSiId  批次
	 * @param userId  负责人
	 * @param type  类型（个人/团体）
	 */
	public void deleteBatchType(Integer  tSiId,Integer userId,Integer type) {
		SortMessage st =new SortMessage();
		st.settBatchId(tSiId);
		st.settUserId(userId);
		st.settBatchType(type);
		SortMessageMapper.deleteBatchType(st);
	}

}