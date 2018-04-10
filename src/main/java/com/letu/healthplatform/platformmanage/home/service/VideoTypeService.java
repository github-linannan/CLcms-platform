package com.letu.healthplatform.platformmanage.home.service;

import com.letu.healthplatform.platformmanage.home.model.VideoType;

public interface VideoTypeService {

	/**
	 * 分页查询 
	 * @param proPackage
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public Object  selectByParam(VideoType videoTypeType,int currentPage,int pageSize);
	/***
	 * 新增
	 * @param proPackage
	 * @return
	 */
	public Object  insert(VideoType videoType);
	
	/***
	 * 修改
	 * @param proPackage
	 * @return
	 */
	public Object updateByPrimaryKey(VideoType videoType);
	/***
	 * 修改
	 * @param proPackage
	 * @return
	 */
	public Object delVideoTypeByPrimaryKey(String id);
}
