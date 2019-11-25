package com.fansongsong.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.fansongsong.common.ConstantClass;
import com.fansongsong.entity.User;

/**
 * 
 * @ClassName: UserInterceptor 
 * @Description: 拦截器
 * @author: Creazy丿绝情
 * @date: 2019年11月20日 下午2:09:12
 */
public class UserInterceptor  implements HandlerInterceptor {
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		User loginUser =  (User)request.getSession().getAttribute(ConstantClass.USER_KEY);
		
		// 用户没有登录
		if(loginUser==null) {
			response.sendRedirect("/user/login");
			return false;
		}
		
		/*
		 * 普通用户不能进入管理员页面
		 */
		if(request.getServletPath().contains("/admin/") 
				&& loginUser.getRole()==ConstantClass.USER_ROLE_GENERAL ) {
			request.setAttribute("errorMsg", "只有管理员才能访问这个页面");
			//response.sendRedirect("/user/login");
			request.getRequestDispatcher("/user/login").forward(request, response);
			return false;
		}
		return true;
	}
	
}
