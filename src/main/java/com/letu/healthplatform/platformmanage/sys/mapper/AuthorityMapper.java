package com.letu.healthplatform.platformmanage.sys.mapper;

import java.util.List;

import com.letu.healthplatform.platformmanage.sys.model.Authority;

public interface AuthorityMapper {
	
	 List<Authority> selectByParam(Authority record);
	
    int deleteByPrimaryKey(String[] tIds);

    int insert(Authority record);

    int insertSelective(Authority record);

    Authority selectByPrimaryKey(Integer tId);

    int updateByPrimaryKeySelective(Authority record);

    int updateByPrimaryKey(Authority record);
    
    
}