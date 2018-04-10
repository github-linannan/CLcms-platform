package com.letu.healthplatform.platformmanage.sys.mapper;

import com.letu.healthplatform.platformmanage.sys.model.Invite;

public interface InviteMapper {
	
	
    int deleteByPrimaryKey(Integer inviteId);

    int insert(Invite record);

    int insertSelective(Invite record);

    Invite selectByPrimaryKey(Integer inviteId);

    int updateByPrimaryKeySelective(Invite record);

    int updateByPrimaryKey(Invite record);
    
}
