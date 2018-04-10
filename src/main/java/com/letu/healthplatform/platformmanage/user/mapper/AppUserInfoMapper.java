package com.letu.healthplatform.platformmanage.user.mapper;

import java.util.Map;
import com.letu.healthplatform.platformmanage.user.model.AppUserInfo;

public interface AppUserInfoMapper {
   
    int deleteByPrimaryKey(Integer userinfoId);
   
    int insert(AppUserInfo record);
   
    int insertSelective(AppUserInfo record);
   
    AppUserInfo selectByPrimaryKey(Integer userinfoId);
  
    int updateByPrimaryKeySelective(AppUserInfo record);
    
    int updateByParamSelective(AppUserInfo record);

    int updateByPrimaryKey(AppUserInfo record);
    
    
   Map<String,Object> selectInfoByPrimaryKey(AppUserInfo record);
   
   int  updateByUserId(AppUserInfo record);

}