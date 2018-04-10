package com.letu.healthplatform.platformmanage.home.service;

import com.letu.healthplatform.platformmanage.home.model.Video;

public interface VideoService {

	/**
	 * 分页查询 
	 * @param proPackage
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public Object  selectByParam(Video video,int currentPage,int pageSize);
	/***
	 * 新增
	 * @param proPackage
	 * @return
	 */
	public Object  insert(Video video);
	
	/***
	 * 修改
	 * @param proPackage
	 * @return
	 */
	public Object updateByPrimaryKey(Video video);
}
