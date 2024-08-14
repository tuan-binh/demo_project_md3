package com.ra.demo_project_md3.service;

import com.ra.demo_project_md3.dto.request.TimeRequest;
import com.ra.demo_project_md3.model.Times;

import java.util.List;

public interface ITimeService
{
	List<Times> findAllTime(int page, int size, String search);
	
	Long totalPagesTime(String search);
	
	void save(TimeRequest timeRequest);
	
	void changeStatus(Long timeId);
	
	List<Times> findAll();
}
