package com.ra.demo_project_md3.service.impl;

import com.ra.demo_project_md3.model.Users;
import com.ra.demo_project_md3.repository.IUserDao;
import com.ra.demo_project_md3.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService
{
	private final IUserDao userDao;
	
	@Override
	public List<Users> findAllUsers(int page, int size, String search)
	{
		return userDao.findAll(page, size, search);
	}
	
	@Override
	public Integer totalAllUsers(String search)
	{
		return userDao.totalAllUsers(search);
	}
	
	@Override
	public boolean changeStatus(Long userId)
	{
		return userDao.changeStatus(userId);
	}
}
