package com.ra.demo_project_md3.repository;

import com.ra.demo_project_md3.dto.request.FormLogin;
import com.ra.demo_project_md3.model.Users;

public interface IAuthDao
{
	Users handleLogin(FormLogin formLogin);
	
	void handleRegister(Users users);
}
