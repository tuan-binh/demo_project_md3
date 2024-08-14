package com.ra.demo_project_md3.service;

import com.ra.demo_project_md3.dto.request.FormLogin;
import com.ra.demo_project_md3.dto.request.FormRegister;
import com.ra.demo_project_md3.model.Users;

public interface IAuthService
{
	Users handleLogin(FormLogin formLogin);
	
	void handleRegister(FormRegister formRegister);
	
	void handleLogout();
}
