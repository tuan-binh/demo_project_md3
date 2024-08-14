package com.ra.demo_project_md3.service.impl;

import com.ra.demo_project_md3.constants.RoleName;
import com.ra.demo_project_md3.dto.request.FormLogin;
import com.ra.demo_project_md3.dto.request.FormRegister;
import com.ra.demo_project_md3.model.Roles;
import com.ra.demo_project_md3.model.Users;
import com.ra.demo_project_md3.repository.IAuthDao;
import com.ra.demo_project_md3.repository.IRoleDao;
import com.ra.demo_project_md3.service.IAuthService;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService
{
	private final IAuthDao authDao;
	private final IRoleDao roleDao;
	private final HttpSession session;
	
	@Override
	public Users handleLogin(FormLogin formLogin)
	{
		Users users = authDao.handleLogin(formLogin);
		session.setAttribute("data_user", users);
		session.setAttribute("your_name", "<i class='fa-solid fa-crown'></i> " + users.getFullName());
		return users;
	}
	
	@Override
	public void handleRegister(FormRegister formRegister)
	{
		Set<Roles> roles = new HashSet<>();
		roles.add(roleDao.findByRoleName(RoleName.ROLE_USER));
		Users user = Users.builder()
				  .fullName(formRegister.getFullName())
				  .phone(formRegister.getPhone())
				  .password(BCrypt.hashpw(formRegister.getPassword(), BCrypt.gensalt(5)))
				  .role(roles)
				  .status(true)
				  .build();
		authDao.handleRegister(user);
	}
	
	@Override
	public void handleLogout()
	{
		session.invalidate();
	}
}
