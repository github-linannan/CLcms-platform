package com.letu.healthplatform.platformmanage.user.mapper;

import java.util.List;
import com.letu.healthplatform.platformmanage.user.model.AppUser;
import com.letu.healthplatform.platformmanage.user.vo.AppUserVo;

public interface AppUserMapper {
 
    int deleteByPrimaryKey(Integer userId);
   
    int insert(AppUser record);
   
    int insertSelective(AppUser record);

    AppUser selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(AppUser record);

    int updateByPrimaryKey(AppUser record);
     
    List<AppUser> selectByParam(AppUser user);
       
    List<AppUserVo>  selectPatient(Integer tSiId);
        
    
}