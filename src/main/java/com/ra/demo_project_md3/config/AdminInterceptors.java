package com.ra.demo_project_md3.config;

import com.ra.demo_project_md3.constants.RoleName;
import com.ra.demo_project_md3.model.Users;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminInterceptors implements HandlerInterceptor
{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		Users users = (Users) request.getSession().getAttribute("data_user");
		if (users != null)
		{
			if (users.getRole().stream().anyMatch(roles -> roles.getRoleName().equals(RoleName.ROLE_ADMIN)))
			{
				return true;
			}
			response.sendRedirect("/403");
			return false;
		}
		response.sendRedirect("/auth/login");
		return false;
	}
}
