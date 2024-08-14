package com.ra.demo_project_md3.service;

import com.ra.demo_project_md3.model.Type;

import java.util.List;

public interface ITypeService
{
	List<Type> findAllType(int page, int size, String search);
	
	Long totalType(String search);
	
	List<Type> findAll();
}
