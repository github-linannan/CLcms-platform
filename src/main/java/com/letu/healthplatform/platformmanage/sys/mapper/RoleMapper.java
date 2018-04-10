package com.letu.healthplatform.platformmanage.sys.mapper;

import java.util.List;
import java.util.Map;

import com.letu.healthplatform.platformmanage.sys.model.Role;

public interface RoleMapper {
	
    int deleteByPrimaryKey(String[] tIds);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer tId);
    
    List<Role>  selectByParam(Role record);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    int updateByStatus(Map<String, Object> params);
    
}