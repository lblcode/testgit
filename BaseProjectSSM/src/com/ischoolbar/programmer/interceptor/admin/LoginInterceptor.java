package com.ischoolbar.programmer.interceptor.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/**
 * 后台登陆拦截器
 * @author LBL
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		String requestURI =request.getRequestURI();
		Object admin =request.getSession().getAttribute("admin");
		if(admin == null){
			// 未登录或者失效
			System.out.println("链接："+requestURI+"进入拦截器！");
			String header=request.getHeader("X-Requested-With");
			// 判断是否为Ajax请求
			if("XMLHttpRequest".equals(header)){
				// 表示是Ajax请求
				Map<String, String> ret = new HashMap<String, String>();
				 ret.put("type", "error");
				 ret.put("msg", "会话超时或未登录，请重新登陆");
				 response.getWriter().write(JSONObject.fromObject(ret).toString());
			}
			// 表示普通链接跳转，直接重定向到登陆页面
			response.sendRedirect(request.getServletContext().getContextPath()+"/system/login");
			return false;
		}
		return true;
	}

}
