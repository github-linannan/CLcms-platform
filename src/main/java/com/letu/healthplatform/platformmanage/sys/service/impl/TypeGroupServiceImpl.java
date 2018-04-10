package com.letu.healthplatform.platformmanage.sys.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.ReturnedType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.letu.healthplatform.platformmanage.common.code.Constants;
import com.letu.healthplatform.platformmanage.common.util.Rsp;
import com.letu.healthplatform.platformmanage.sys.mapper.TypeGroupMapper;
import com.letu.healthplatform.platformmanage.sys.model.TypeGroup;
import com.letu.healthplatform.platformmanage.sys.service.TypeGroupService;


@Service
@Transactional(propagation = Propagation.REQUIRED,
	isolation = Isolation.DEFAULT,
	 timeout=36000,
	rollbackFor={RuntimeException.class, Exception.class})
public class TypeGroupServiceImpl  implements TypeGroupService { 
	private static final Logger log = LogManager.getLogger(TypeGroupServiceImpl.class);
	
	@Autowired 
	private TypeGroupMapper TypeGroupMapper;
	
	@Override
	public Object findTypeGroupPage(TypeGroup record,int page,int pageSize) {
	    List<TypeGroup> list=null;
	try{
	    if(page>0&&pageSize>0)
	       PageHelper.startPage(page, pageSize);
	       list=TypeGroupMapper.selectByParam(record);
	      if(page>0&&pageSize>0){
	 	    return new PageInfo<>(list);
	      }	
	   }catch (Exception e) {
	    e.printStackTrace();
	     log.error(Constants.ERROR_FIND, e);
	  }
	     return Rsp.succ(Constants.SUCCESS_FIND, list);
	}
	
	
	@Override
	public Object findTypeGroup(String id) {
		TypeGroup  record = null;
		if(StringUtils.isBlank(id)){
			return Rsp.fail(Constants.DEFAULT_KEY_ISNULL);
	  	}
		try{
			record =TypeGroupMapper.selectByPrimaryKey(Integer.valueOf(id));
		}catch (Exception e) {
			e.printStackTrace();
			log.error(Constants.ERROR_FIND, e);
			return Rsp.fail(Constants.ERROR_DELETE);
			}
		return Rsp.succ(Constants.SUCCESS_FIND,record);
    }
	
	@Override
	public Object deleteByPrimaryKey(String id) {
		
		if(StringUtils.isBlank(id)){
	   return Rsp.fail(Constants.DEFAULT_KEY_ISNULL);
		}
			try{
				TypeGroup  typeGroup =new  TypeGroup();
				typeGroup.setId(Integer.parseInt(id));
				typeGroup.setState(-1);
				TypeGroupMapper.updateByPrimaryKey(typeGroup);
			//int a = TypeGroupMapper.deleteByPrimaryKey(Integer.valueOf(id));
			return Rsp.succ(Constants.SUCCESS_DELETE);
			}catch (Exception e) {
				e.printStackTrace();
				log.error(Constants.ERROR_DELETE, e);
			return Rsp.succ(Constants.ERROR_DELETE);
			}
		}
	
	@Override
	public Object insertSelective(TypeGroup record) {
		try{
			int a = TypeGroupMapper.insertSelective(record);
			
			if(a>0) return Rsp.succ(Constants.SUCCESS_INSERT);
			}catch (Exception e) {
				e.printStackTrace();
				log.error(Constants.ERROR_INSERT, e);
			}
			return Rsp.fail(Constants.ERROR_INSERT);
	}
	
	@Override
	public Object updateByPrimaryKeySelective(TypeGroup record) {
	    int a =0;
		try{
		 a = TypeGroupMapper.updateByPrimaryKeySelective(record);
		}catch (Exception e) {
		    e.printStackTrace();
		    log.error(Constants.ERROR_UPDATE,e);
			    return Rsp.fail(Constants.ERROR_UPDATE);
		}
		return Rsp.succ(Constants.SUCCESS_UPDATE);
	}
	
	
	@Override
	public Object findAllTypeGroup(TypeGroup record) {
		List<String> list =null;
		Map<String, Object> map=null;
		try{
			list =TypeGroupMapper.selectAllTypeGroup(record);
			if(!list.isEmpty()){
				map =new HashMap<>();
				for (int i = 0; i < list.size(); i++) {
					map.put(list.get(i), list.get(i));
				}
				return   Rsp.succ(Constants.SUCCESS_FIND, map);
			}
		}catch (Exception e) {
			e.printStackTrace();
			log.error(Constants.ERROR_FIND, e);
			return Rsp.fail(Constants.ERROR_DELETE);
			}
		return Rsp.succ(Constants.SUCCESS_FIND,map);
	}
	
}
