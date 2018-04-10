package com.letu.healthplatform.platformmanage.sys.controller;import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpSession;import org.apache.log4j.LogManager;import org.apache.log4j.Logger;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.ModelAttribute;import org.springframework.web.bind.annotation.PathVariable;import org.springframework.web.bind.annotation.PostMapping;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RequestParam;import org.springframework.web.bind.annotation.RestController;import com.alibaba.fastjson.JSONObject;import com.letu.healthplatform.platformmanage.common.config.RedisUntil;import com.letu.healthplatform.platformmanage.common.util.Rsp;import com.letu.healthplatform.platformmanage.sys.model.Login;import com.letu.healthplatform.platformmanage.sys.service.LoginService;/** * @author dzb * @date 2017年12月26日 上午10:12:45 * @version 1.0 * @description   后台登陆用户 */@RestController@RequestMapping("/mlogin")public class LoginController {    private static final Logger log = LogManager.getLogger(LoginController.class);    @Autowired    private  LoginService  loginService;         @Autowired    private RedisUntil  redisUtil;        	@GetMapping(value="/login")    public  Object  findLoginCheckPwd(Login login,HttpServletRequest request){		return  loginService.findLoginByName(login);    }		@GetMapping(value="/findLoginName")    public  Object  findLoginName(Login login,HttpServletRequest request){		return  loginService.findLoginName(login);    }	    	 @GetMapping(value="/logout")    public  Object  logout(HttpServletRequest request){	    String  token=request.getParameter("token");	    redisUtil.removeValue(token);	    return Rsp.succ("退出成功");		    }	 @GetMapping(value="/findToken")	 public  Object  findToken(HttpServletRequest request){		 String  token=request.getParameter("token");		 if(token!=null){		  String u= redisUtil.getValue(token);		    if(u!=null){		    	 return Rsp.succ("token获取成功",JSONObject.parseObject(u, Login.class));		    }		 }		 return Rsp.fail("token获取失败");		 	 }                /***	 * 查询所有后台用户信息	 * @return	 */	 @GetMapping(value="/findLoginPage")	 public Object findLoginPage(@ModelAttribute Login login,				@RequestParam(required=false,defaultValue="0") int page,				@RequestParam(required=false,defaultValue="0") int pageSize){		  return loginService.findLoginPage(login,page,pageSize);	}	 	 	/***	 * 	 * 查询后台用户	 * @param tId 科室id	 * @return	 */	 @GetMapping(value="/findLogin/{tId}")	 public Object findLogin(@PathVariable String  tId){		  return loginService.findLogin(tId);	}	 	 	 /***	 * 	 * 查询后台用户	 * @param Login 	 * @return	 */	 @GetMapping(value="/findLoginDirector")	 public Object findLoginDirector(@ModelAttribute Login login){		  return loginService.findLoginDirector(login);	}	 	 	/***	 * 	 * 新增后台用户	 * @return	 */	 @PostMapping(value="/insert")	 public Object insert(@ModelAttribute Login login){		  return loginService.insertSelective(login);	}	 	 	 	/***	 * 	 * 修改后台用户	 * @return	 */	 @PostMapping(value="/update")	 public Object update(@ModelAttribute Login login){		  return loginService.updateByPrimaryKeySelective(login);	}	 /***	  * 	  * 修改后台用户	  * @return	  */	 @PostMapping(value="/updatePassword")	 public Object updatePassword(@ModelAttribute Login login){		 return loginService.updatePasswordByPrimaryKeySelective(login);	 }	 	 	/***	 * 	 * 删除后台用户	 * @param tIds  删除科室ID	 * @return	 */	 @GetMapping(value="/delete/{tIds}")	 public Object delete(@PathVariable String  tIds){		  return loginService.deleteByPrimaryKey(tIds);	}	 	 /***	 * 	 * 修改后台用户状态	 * @param tIds  医院ID	 * @return	 */	 @PostMapping(value="/updateByStatus/{tStatus}/{tIds}")	 public Object updateByStatus(@PathVariable String tStatus,@PathVariable String  tIds){		  return loginService.updateByStatus(tStatus,tIds);	}        }