package com.ischoolbar.programmer.controller.admin;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.util.StringUtils;
import com.ischoolbar.programmer.entity.admin.User;
import com.ischoolbar.programmer.service.admin.UserService;
import com.ischoolbar.programmer.util.CaptchaUtil;

/**
 * 系统类
 * 
 * @author LBL
 *
 */
@Controller
@RequestMapping("/system")
public class SystemController {
	
	@Autowired
	private UserService UserService;
	
   /**
    * 系统登陆后的欢迎页
    * @param model
    * @return
    */
	@RequestMapping(value = "/welcome",method=RequestMethod.GET)
	public ModelAndView welcome( ModelAndView model) {
		model.setViewName("system/welcome");
		return model;
	}
   
	 /**
	  * 系统登陆后的主页
	  * @param model
	  * @return
	  */
	@RequestMapping(value = "/index",method=RequestMethod.GET)
	public ModelAndView index( ModelAndView model) {
		model.setViewName("system/index");
		return model;
	}
	/**
	 * 登陆页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login",method=RequestMethod.GET)
	public ModelAndView login( ModelAndView model) {
		model.setViewName("system/login");
		return model;
	}
	/**
	 * 登陆表单提交处理控制器
	 * @return
	 */
	@RequestMapping(value = "/login",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> loginAct(User user,String  cpacha,HttpServletRequest request){
		 Map<String, String> ret = new HashMap<String, String>();
		 if(user == null){
			 ret.put("type", "error");
			 ret.put("msg", "请输入用户信息");
			 return ret;
		 }
		 if(StringUtils.isEmpty(cpacha)){
			 ret.put("type", "error");
			 ret.put("msg", "请输入验证码");
			 return ret;
		 }
		 if(StringUtils.isEmpty(user.getUsername())){
			 ret.put("type", "error");
			 ret.put("msg", "请输入用户名");
			 return ret;
		 }
		 if(StringUtils.isEmpty(user.getPassword())){
			 ret.put("type", "error");
			 ret.put("msg", "请输入用密码");
			 return ret;
		 }
		 Object  loginCaptcha=request.getSession().getAttribute("loginCaptcha");
		 if(loginCaptcha == null){
			 ret.put("type", "error");
			 ret.put("msg", "会话超时，请刷新页面");
			 return ret;
		 }
		 if(!cpacha.toUpperCase().equals(loginCaptcha.toString().toUpperCase())){
			 ret.put("type", "error");
			 ret.put("msg", "验证码错误");
			 return ret;
		 }
		 
		 User findByUsername= UserService.findByUsername(user.getUsername());
		 if(findByUsername == null ){
			 ret.put("type", "error");
			 ret.put("msg", "该用户不存在");
			 return ret;
		 }
		 if(!user.getPassword() .equals(findByUsername.getPassword()) ){
			 ret.put("type", "error");
			 ret.put("msg", "密码错误");
			 return ret;
		 }
		 // 把登陆用户放入session
		 request.getSession().setAttribute("admin", findByUsername);
		 ret.put("type", "success");
		 ret.put("msg", "登陆成功");
		 return ret;
	}
	
	
	/**
	 * 登陆验证码
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/get_cpacha",method=RequestMethod.GET)
	public void generateCpacha( 
			@RequestParam( value="vl",required=false,defaultValue="4")  String length,
			@RequestParam( value="w",required=false,defaultValue="100")  String picWidth,
			@RequestParam( value="h",required=false,defaultValue="40")  String picHeigth,
			@RequestParam( value="type",required=false,defaultValue="loginCaptcha")  String captchaType,
			HttpServletRequest request,
			HttpServletResponse response) {
		try {
			CaptchaUtil.outputCaptcha(length, picWidth, picHeigth, "20", "15", "25", "GIF", request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
