package com.ra.demo_project_md3.service.impl;

import com.ra.demo_project_md3.model.Address;
import com.ra.demo_project_md3.repository.IAddressDao;
import com.ra.demo_project_md3.repository.impl.AddressDaoImpl;
import com.ra.demo_project_md3.service.IAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements IAddressService
{
	private final IAddressDao addressDao;
	
	@Override
	public List<Address> findAllAdress(int page, int size, String search)
	{
		return addressDao.findAllAddress(page, size, search);
	}
	
	@Override
	public Long totalAllAddress(String search)
	{
		return addressDao.totalAllAddress(search);
	}
	
	@Override
	public List<Address> findAll()
	{
		return addressDao.findAll();
	}
}
