package com.ra.demo_project_md3.repository;

import com.ra.demo_project_md3.model.Type;

import java.util.List;

public interface ITypeDao
{
	List<Type> findAllType(int page, int size, String search);
	
	Long totalType(String search);
	
	List<Type> findAll();
}
