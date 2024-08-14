package com.ra.demo_project_md3.service.impl;

import com.ra.demo_project_md3.model.Type;
import com.ra.demo_project_md3.repository.ITypeDao;
import com.ra.demo_project_md3.service.ITypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeServiceImpl implements ITypeService
{
	private final ITypeDao typeDao;
	
	@Override
	public List<Type> findAllType(int page, int size, String search)
	{
		return typeDao.findAllType(page, size,search);
	}
	
	@Override
	public Long totalType(String search)
	{
		return typeDao.totalType(search);
	}
	
	@Override
	public List<Type> findAll()
	{
		return typeDao.findAll();
	}
}
