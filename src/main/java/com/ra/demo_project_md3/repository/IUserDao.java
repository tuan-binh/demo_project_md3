package com.ra.demo_project_md3.repository;

import com.ra.demo_project_md3.model.Users;

import java.math.BigInteger;
import java.util.List;

public interface IUserDao
{
	
	List<Users> findAll(int page, int size, String search);
	
	Integer totalAllUsers(String search);
	
	Users findById(Long idFind);
	
	boolean changeStatus(Long userId);
}
