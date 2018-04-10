package com.letu.healthplatform.platformmanage.report.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.letu.healthplatform.platformmanage.common.code.Constants;
import com.letu.healthplatform.platformmanage.common.util.Rsp;
import com.letu.healthplatform.platformmanage.report.mapper.ExcelSampleMapper;
import com.letu.healthplatform.platformmanage.report.model.ExcelSample;
import com.letu.healthplatform.platformmanage.report.service.ExcelSampleService;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = {
		RuntimeException.class, Exception.class })
public class ExcelSampleServiceImpl implements ExcelSampleService {

	private static final Logger log = LogManager.getLogger(ExcelSampleServiceImpl.class);

	@Autowired
	private ExcelSampleMapper ExcelSampleMapper;

	@Override
	public Object findExcelSamplePage(ExcelSample record, int page, int pageSize) {
		List<ExcelSample> list = null;
		try {
			if (page > 0 && pageSize > 0)
				PageHelper.startPage(page, pageSize);
			list = ExcelSampleMapper.selectByParam(record);
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
	public Object findExcelSample(String tId) {
		ExcelSample record = null;
		if (StringUtils.isBlank(tId)) {
			return Rsp.fail(Constants.DEFAULT_KEY_ISNULL);
		}
		try {
			record = ExcelSampleMapper.selectByPrimaryKey(Integer.valueOf(tId));
		} catch (Exception e) {
			e.printStackTrace();
			log.error(Constants.ERROR_FIND, e);
			return Rsp.fail(Constants.ERROR_DELETE);
		}
		return Rsp.succ(Constants.SUCCESS_FIND, record);
	}

	@Override
	public Object deleteByPrimaryKey(String tId) {

		if (StringUtils.isBlank(tId)) {
			return Rsp.fail(Constants.DEFAULT_KEY_ISNULL);
		}
		try {
			int a = ExcelSampleMapper.deleteByPrimaryKey(Integer.valueOf(tId));
			return Rsp.succ(Constants.SUCCESS_DELETE);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(Constants.ERROR_DELETE, e);
			return Rsp.succ(Constants.ERROR_DELETE);
		}
	}

	@Override
	public Object insertSelective(ExcelSample record) {
		try {
			int a = ExcelSampleMapper.insertSelective(record);
			if (a > 0)
				return Rsp.succ(Constants.SUCCESS_INSERT);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(Constants.ERROR_INSERT, e);
		}
		return Rsp.fail(Constants.ERROR_INSERT);
	}

	@Override
	public Object updateByPrimaryKeySelective(ExcelSample record) {
		int a = 0;
		try {
			a = ExcelSampleMapper.updateByPrimaryKeySelective(record);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(Constants.ERROR_UPDATE, e);
			return Rsp.fail(Constants.ERROR_UPDATE);
		}
		return Rsp.succ(Constants.SUCCESS_UPDATE);
	}
	
	

	@Override
	public Object updateByStatus(ExcelSample record) {
		int a =0;
		try {
			 a = ExcelSampleMapper.updateByStatus(record);
		}catch (Exception e) {
		    e.printStackTrace();
		    log.error(Constants.ERROR_UPDATE,e);
		    return Rsp.fail(Constants.ERROR_UPDATE);
		}
		return Rsp.succ(Constants.SUCCESS_UPDATE);
	}

	
	@Override
	public Object insertBatch(List<ExcelSample> list) {
	
		int a =0;
		try {
			if(!list.isEmpty()){
				List<String> list2  = new LinkedList<String>();
				for (int i = 0; i < list.size(); i++) {
					list2.add(list.get(i).gettSiNumber());
				}
				if(!ExcelSampleMapper.selectBySiNumber(list2).isEmpty()){
					  return Rsp.fail("商务订单号已存在，不能重复导入。");
				}else{
					a = ExcelSampleMapper.insertBatch(list);
				};
			}
		}catch (Exception e) {
		    e.printStackTrace();
		    log.error(Constants.ERROR_INSERT,e);
		    return Rsp.fail(Constants.ERROR_INSERT);
		}
		return Rsp.succ(Constants.SUCCESS_INSERT);
	}
	
	

}
