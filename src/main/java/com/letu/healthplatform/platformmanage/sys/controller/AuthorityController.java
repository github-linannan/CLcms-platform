package com.letu.healthplatform.platformmanage.sys.controller;import org.apache.log4j.LogManager;import org.apache.log4j.Logger;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.web.bind.annotation.RestController;import com.letu.healthplatform.platformmanage.sys.model.Authority;import com.letu.healthplatform.platformmanage.sys.service.AuthorityService;import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.ModelAttribute;import org.springframework.web.bind.annotation.PathVariable;import org.springframework.web.bind.annotation.PostMapping;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RequestParam;@RestController@RequestMapping("/authority")public class AuthorityController {    private static final Logger log = LogManager.getLogger(AuthorityController.class);    @Autowired    private  AuthorityService  authorityService;            /***   	 * 查询所有权限信息   	 * @return   	 */   	 @GetMapping(value="/findAuthorityPage")   	 public Object findAuthorityPage(@ModelAttribute Authority authority,   				@RequestParam(required=false,defaultValue="0") int page,   				@RequestParam(required=false,defaultValue="0") int pageSize){   		  return authorityService.findAuthorityPage(authority,page,pageSize);   	}   	    	    	/***   	 *    	 * 查询权限   	 * @param tId 科室id   	 * @return   	 */   	 @GetMapping(value="/findAuthority/{tId}")   	 public Object findAuthority(@PathVariable String  tId){   		  return authorityService.findAuthority(tId);   	}   	    	    	/***   	 *    	 * 新增权限   	 * @return   	 */   	 @PostMapping(value="/insert")   	 public Object insert(@ModelAttribute Authority authority){   		  return authorityService.insertSelective(authority);   	}   	    	/***   	 *    	 * 修改权限   	 * @return   	 */   	 @PostMapping(value="/update")   	 public Object update(@ModelAttribute Authority authority){   		  return authorityService.updateByPrimaryKeySelective(authority);   	}   	    	    	/***   	 *    	 * 删除权限   	 * @param tIds  删除科室ID   	 * @return   	 */   	 @GetMapping(value="/delete/{tIds}")   	 public Object delete(@PathVariable String  tIds){   		  return authorityService.deleteByPrimaryKey(tIds);   	}   	    }