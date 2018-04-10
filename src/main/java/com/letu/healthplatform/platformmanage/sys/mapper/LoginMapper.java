package com.letu.healthplatform.platformmanage.sys.mapper;

import java.util.List;
import java.util.Map;

import com.letu.healthplatform.platformmanage.sys.model.Login;

public interface LoginMapper {
	
    int deleteByPrimaryKey(String[] tIds);

    int insert(Login record);

    int insertSelective(Login record);

    Login selectByPrimaryKey(Integer tId);

    int updateByPrimaryKeySelective(Login record);

    int updateByPrimaryKey(Login record);
    
    List<Login> selectByParam(Login record);
    
    int updateByStatus(Map<String, Object> params);
    
    int updateDirector(Map<String, Object> params);
    /**
     * 查找是不是主任
     * **/
    Login  selectByLoginDirector(Login record);
    
    
    List<Login> selectDirector(Login record);
    
    Login  selectByModel(Login record);
}