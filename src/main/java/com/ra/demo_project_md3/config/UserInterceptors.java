package com.ra.demo_project_md3.config;

import com.ra.demo_project_md3.model.Users;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInterceptors implements HandlerInterceptor
{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		Users users = (Users) request.getSession().getAttribute("data_user");
		if (users != null)
		{
			// because admin default has role user
			return true;
		}
		response.sendRedirect("/auth/login");
		return false;
	}
}
