package com.letu.healthplatform.platformmanage.sys.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.letu.healthplatform.platformmanage.common.code.Constants;
import com.letu.healthplatform.platformmanage.common.config.RedisUntil;
import com.letu.healthplatform.platformmanage.common.util.Rsp;
import com.letu.healthplatform.platformmanage.sys.mapper.MenuMapper;
import com.letu.healthplatform.platformmanage.sys.model.Login;
import com.letu.healthplatform.platformmanage.sys.model.Menu;
import com.letu.healthplatform.platformmanage.sys.service.MenuService;

@Service
@Transactional(propagation = Propagation.REQUIRED,
isolation = Isolation.DEFAULT,
timeout=36000,
rollbackFor={RuntimeException.class, Exception.class})
public class MenuServiceImpl  implements MenuService {
	
    private static final Logger log = LogManager.getLogger(MenuServiceImpl.class);

    @Autowired 
    private MenuMapper menuMapper;
    
    @Autowired
    private RedisUntil  redisUtil;

	/* (non-Javadoc)
	 * @see com.letu.healthplatform.platformmanage.sys.service.MenuService#findMenuPage(com.letu.healthplatform.platformmanage.sys.model.Menu, int, int)
	 */
	@Override
	public Object findMenuPage(Menu menu, int page, int pageSize) {
		List<Menu> list=null;
		List<Menu> result=new LinkedList<Menu>();
		try{
			/*if(page>0&&pageSize>0)
		    PageHelper.startPage(page, pageSize);*/
		    list=menuMapper.selectByParam(menu);
		    for (int i = 0; i < list.size(); i++) {
		    	Menu m= list.get(i);
				if(m.gettMenuParentid()==0){
					getMenuTree(m, list);
					result.add(m);
				}
			}
		    /*if(page>0&&pageSize>0){
		    	 return new PageInfo<>(list);
		    }*/
		}catch (Exception e) {
			e.printStackTrace();
			log.error(Constants.ERROR_FIND, e);
		}
		return Rsp.succ(Constants.SUCCESS_FIND, JSON.toJSON(result));
	}
	
	
	

	/* (non-Javadoc)
	 * @see com.letu.healthplatform.platformmanage.sys.service.MenuService#findMenuAll(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Object findMenuAll(HttpServletRequest request) {
		List<Menu> list=null;
		String u =null;
		List<Menu> result=new LinkedList<Menu>();
		try{
			 String  token=request.getParameter("token");
			 if(token!=null){
			  u= redisUtil.getValue(token);
			    if(u==null){
			    	 return Rsp.fail("token获取失败，请重新登陆");
			    }
			 }
			Login login = JSONObject.parseObject(u, Login.class);
		    list=menuMapper.selectByLogin(login.gettId());
		    for (int i = 0; i < list.size(); i++) {
		    	Menu m= list.get(i);
				if(m.gettMenuParentid()==0){
					getMenuTree(m, list);
					result.add(m);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			log.error(Constants.ERROR_FIND, e);
		}
		return Rsp.succ(Constants.SUCCESS_FIND, JSON.toJSON(result));
	}
	
	
	/**
	 * 递归算法  
	 * **/
	private static  Menu getMenuTree(Menu menu,List<Menu> itemList){  
        List<Menu> sonList = new ArrayList<Menu>();  
       
        for(Menu item : itemList){  
            if(menu.gettMenuId().intValue()==item.gettMenuParentid().intValue()){  
                sonList.add(item);  
                getMenuTree(item,itemList);  
            }  
            
        }  
        menu.setChildrenList(sonList); 
        return menu;  
    } 
	

	/* (non-Javadoc)
	 * @see com.letu.healthplatform.platformmanage.sys.service.MenuService#deleteByPrimaryKey(java.lang.String)
	 */
	@Override
	public Object deleteByPrimaryKey(String tMenuIds) {
		int a=0;
		if(StringUtils.isBlank(tMenuIds)){
			return Rsp.fail(Constants.DEFAULT_KEY_ISNULL);
		}
		try{
			a = menuMapper.deleteByPrimaryKey(tMenuIds.split(","));
			return Rsp.succ(Constants.SUCCESS_DELETE);
		}catch (Exception e) {
			e.printStackTrace();
			log.error(Constants.ERROR_DELETE, e);
			return Rsp.fail(Constants.ERROR_DELETE);
		}
	}

	/* (non-Javadoc)
	 * @see com.letu.healthplatform.platformmanage.sys.service.MenuService#insertSelective(com.letu.healthplatform.platformmanage.sys.model.Menu)
	 */
	@Override
	public Object insertSelective(Menu record) {
		try{
			int a = menuMapper.insertSelective(record);
			 return Rsp.succ(Constants.SUCCESS_INSERT);
		}catch (Exception e) {
			e.printStackTrace();
			log.error(Constants.ERROR_INSERT, e);
			return Rsp.fail(Constants.ERROR_INSERT);
		}
	}

	/* (non-Javadoc)
	 * @see com.letu.healthplatform.platformmanage.sys.service.MenuService#findMenu(java.lang.String)
	 */
	@Override
	public Object findMenu(String tMenuId) {
		Menu  menu = null;
		if(StringUtils.isBlank(tMenuId)){
			return Rsp.fail(Constants.DEFAULT_KEY_ISNULL);
		}
		try{
			menu =menuMapper.selectByPrimaryKey(Integer.valueOf(tMenuId));
		}catch (Exception e) {
			e.printStackTrace();
			log.error(Constants.ERROR_FIND, e);
			return Rsp.fail(Constants.ERROR_FIND);
		}
		return Rsp.succ(Constants.SUCCESS_FIND,menu);
	}

	/* (non-Javadoc)
	 * @see com.letu.healthplatform.platformmanage.sys.service.MenuService#updateByPrimaryKeySelective(com.letu.healthplatform.platformmanage.sys.model.Menu)
	 */
	@Override
	public Object updateByPrimaryKeySelective(Menu record) {
		int a =0;
		try{
			 a = menuMapper.updateByPrimaryKeySelective(record);
			 return Rsp.succ(Constants.SUCCESS_UPDATE);
		}catch (Exception e) {
		    e.printStackTrace();
		    log.error(Constants.ERROR_UPDATE,e);
		    return Rsp.fail(Constants.ERROR_UPDATE);
		}
	}



    
    
}
