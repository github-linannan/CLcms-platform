package com.letu.healthplatform.platformmanage.sys.mapper;

import java.util.List;

import com.letu.healthplatform.platformmanage.sys.model.Menu;

public interface MenuMapper {
	
	
	List<Menu> selectByLogin(Integer loginId);
	
	List<Menu> selectByParam(Menu record);
	
    int deleteByPrimaryKey(String[] tMenuIds);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer tMenuId);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
    
}