package com.ra.demo_project_md3.service.impl;

import com.ra.demo_project_md3.dto.request.TimeRequest;
import com.ra.demo_project_md3.model.Times;
import com.ra.demo_project_md3.repository.ITimeDao;
import com.ra.demo_project_md3.service.ITimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeServiceImpl implements ITimeService
{
	private final ITimeDao timeDao;
	
	@Override
	public List<Times> findAllTime(int page, int size, String search)
	{
		return timeDao.findAllTime(page, size, search);
	}
	
	@Override
	public Long totalPagesTime(String search)
	{
		return timeDao.totalAllTime(search);
	}
	
	@Override
	public void save(TimeRequest timeRequest)
	{
		Times times = Times.builder()
				  .time(timeRequest.getTime())
				  .build();
		timeDao.save(times);
	}
	
	@Override
	public void changeStatus(Long timeId)
	{
		timeDao.changeStatus(timeId);
	}
	
	@Override
	public List<Times> findAll()
	{
		return timeDao.findAll();
	}
}
