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
import com.letu.healthplatform.platformmanage.home.mapper.VideoMapper;
import com.letu.healthplatform.platformmanage.home.model.Video;
import com.letu.healthplatform.platformmanage.home.service.VideoService;

@Service
@Transactional
public class VideoServiceImpl implements  VideoService{
	private final static Logger log=LoggerFactory.getLogger(VideoServiceImpl.class);
	@Autowired
	private VideoMapper  videoMapper;
	@Override
	public Object selectByParam(Video video, int currentPage, int pageSize) {
	  if(currentPage>0&&pageSize>0)
		  PageHelper.startPage(currentPage, pageSize);
		 List<Video> allinVideo=videoMapper.selectByParam(video);
		 if(currentPage>0&&pageSize>0){
		    return new PageInfo<>(allinVideo);
		 }
		 return Rsp.succ("查询成功", allinVideo);
	}

	@Override
	public Object insert(Video video) {
		try{
		int i= videoMapper.insertSelective(video);
		   if(i>0)
		  return Rsp.succ("修改成功");
		   else
		 return Rsp.fail("修改失败"); 
		}catch(Exception e){
          new HomeException("修改视频失败");
          log.error("VideoServiceImpl 修改失败");
          return Rsp.fail("修改失败");
		}
	}

	@Override
	public Object updateByPrimaryKey(Video video) {
		try{
		   int i=videoMapper.updateByPrimaryKeySelective(video);
		   if(i>0)
		  return Rsp.succ("修改成功");
		   else
				 return Rsp.fail("修改失败"); 
		}catch(Exception e){
          new HomeException("修改视频失败");
          log.error("VideoServiceImpl 修改失败");
          return Rsp.fail("修改失败");
		}
	}

}
