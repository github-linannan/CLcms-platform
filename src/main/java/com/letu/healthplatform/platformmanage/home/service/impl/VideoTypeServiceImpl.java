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
import com.letu.healthplatform.platformmanage.home.mapper.VideoTypeMapper;
import com.letu.healthplatform.platformmanage.home.model.VideoType;
import com.letu.healthplatform.platformmanage.home.service.VideoTypeService;

@Service
@Transactional
public class VideoTypeServiceImpl implements  VideoTypeService{
	private final static Logger log=LoggerFactory.getLogger(VideoTypeServiceImpl.class);
	@Autowired
	private VideoTypeMapper  videoTypeMapper;
	@Override
	public Object selectByParam(VideoType videoType, int currentPage, int pageSize) {
	  if(currentPage>0&&pageSize>0)
		  PageHelper.startPage(currentPage, pageSize);
		 List<VideoType> allVideoType=videoTypeMapper.selectByParam(videoType);
		 if(currentPage>0&&pageSize>0){
		    return new PageInfo<>(allVideoType);
		 }
		 return Rsp.succ("查询成功", allVideoType);
	}

	@Override
	public Object insert(VideoType videoType) {
		try{
		int i= videoTypeMapper.insertSelective(videoType);
		   if(i>0)
		  return Rsp.succ("修改成功");
		   else
		 return Rsp.fail("修改失败"); 
		}catch(Exception e){
          new HomeException("修改视频类型失败");
          log.error("VideoTypeServiceImpl 修改失败");
          return Rsp.fail("修改失败");
		}
	}

	@Override
	public Object updateByPrimaryKey(VideoType videoType) {
		try{
		   int i=videoTypeMapper.updateByPrimaryKeySelective(videoType);
		   if(i>0)
		  return Rsp.succ("修改成功");
		   else
				 return Rsp.fail("修改失败"); 
		}catch(Exception e){
          new HomeException("修改视频类型失败");
          log.error("VideoTypeServiceImpl 修改失败");
          return Rsp.fail("修改失败");
		}
	}
	
	/***
	 * 删除
	 * @param proPackage
	 * @return
	 */
	public Object delVideoTypeByPrimaryKey(String id){
		try{
			   int i=videoTypeMapper.deleteByPrimaryKey(Integer.parseInt(id));
			   if(i>0)
			  return Rsp.succ("删除成功");
			   else
					 return Rsp.fail("删除成功"); 
			}catch(Exception e){
	          new HomeException("删除成功视频类型失败");
	          log.error("VideoTypeServiceImpl 删除成功失败");
	          return Rsp.fail("删除失败");
			}
	}

}
