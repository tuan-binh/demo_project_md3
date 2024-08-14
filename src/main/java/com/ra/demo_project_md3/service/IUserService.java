package com.ra.demo_project_md3.service;

import com.ra.demo_project_md3.model.Users;

import java.math.BigInteger;
import java.util.List;

public interface IUserService
{
	List<Users> findAllUsers(int page, int size, String search);
	
	Integer totalAllUsers(String search);
	
	boolean changeStatus(Long userId);
}
