package com.letu.healthplatform.platformmanage.home.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.letu.healthplatform.platformmanage.common.util.Rsp;
import com.letu.healthplatform.platformmanage.home.exception.HomeException;
import com.letu.healthplatform.platformmanage.home.mapper.InformationMapper;
import com.letu.healthplatform.platformmanage.home.model.Information;
import com.letu.healthplatform.platformmanage.home.service.InfomationService;

@Service
@Transactional
public class InfomationServiceImpl implements  InfomationService{
	private final static Logger log=LoggerFactory.getLogger(InfomationServiceImpl.class);
	@Autowired
	private InformationMapper  informationMapper;
	@Override
	public Object selectByParam(Information information, int currentPage, int pageSize) {
		
		  if(currentPage>0&&pageSize>0)
			  PageHelper.startPage(currentPage, pageSize);
			 List<Information> allinfomation=informationMapper.selectByParam(information);
		  if(currentPage>0&&pageSize>0)
			  return new PageInfo<>(allinfomation);
		  else
			 return Rsp.succ("查询成功", allinfomation);
	}

	@Override
	public Object insert(Information information) {
		try{
	     int i=	informationMapper.insertSelective(information);
	     if(i>0)
	     return Rsp.succ("修改成功");
		  else
			   return Rsp.fail("修改失败"); 
		}catch(Exception e){
          new HomeException("修改产品失败");
          log.error("InformationServiceImpl 修改失败");
          return Rsp.fail("修改失败");
		}
	}

	@Override
	public Object updateByPrimaryKey(Information information) {
		// TODO Auto-generated method stub
		try{
			int i= informationMapper.updateByPrimaryKeySelective(information);
			if(i>0)
	          return Rsp.succ("修改成功");
			  else
			   return Rsp.fail("修改失败"); 
			}catch(Exception e){
	          new HomeException("修改产品失败");
	          log.error("InformationServiceImpl 修改失败");
	          return Rsp.fail("修改失败");
		}
	}

}
