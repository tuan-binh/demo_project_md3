package com.ra.demo_project_md3.repository;

import com.ra.demo_project_md3.model.Times;

import java.util.List;

public interface ITimeDao
{
	List<Times> findAllTime(int page, int size, String search);
	
	Long totalAllTime(String search);
	
	void save(Times times);
	
	Times findById(Long id);
	
	void changeStatus(Long timeId);
	
	List<Times> findAll();
}
