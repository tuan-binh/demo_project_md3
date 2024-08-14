package com.ra.demo_project_md3.service;

import com.ra.demo_project_md3.model.Address;

import java.util.List;

public interface IAddressService
{
	List<Address> findAllAdress(int page, int size, String search);
	
	Long totalAllAddress(String search);
	
	List<Address> findAll();
}
