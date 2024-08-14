package com.ra.demo_project_md3.repository;

import com.ra.demo_project_md3.model.Address;

import java.util.List;

public interface IAddressDao
{
	List<Address> findAllAddress(int page, int size, String search);
	
	Long totalAllAddress(String search);
	
	List<Address> findAll();
}
